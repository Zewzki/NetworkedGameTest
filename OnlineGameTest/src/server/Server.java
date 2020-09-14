package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int N_CONCURRENT_CLIENTS = 100;
    private static final int PORT_NUM = 25999;

    private ServerLogger logger;

    private ExecutorService executor;
    private ServerSocket server;
    private SockServer[] sockServer;
    private int counter = 0;
    private int nClientsActive = 0;

    public Server() {

        logger = new ServerLogger();
        logger.log("helo");
        logger.log("sex with dudes");

        sockServer = new SockServer[N_CONCURRENT_CLIENTS];
        executor = Executors.newFixedThreadPool(N_CONCURRENT_CLIENTS);

    }

    public void startServer() {

        try {

            server = new ServerSocket(PORT_NUM, N_CONCURRENT_CLIENTS);

            while(true) {

                try {

                    sockServer[counter] = new SockServer(counter);
                    sockServer[counter].waitForConnection();
                    nClientsActive++;
                    executor.execute(sockServer[counter]);

                } catch (EOFException e) {
                    logger.log("Server terminated connection");
                } finally {
                    counter = ++counter % N_CONCURRENT_CLIENTS;
                }

            }

        } catch (IOException e) {
            logger.log(e.toString());
        }

    }

    private class SockServer implements Runnable {

        private ObjectOutputStream output;
        private ObjectInputStream input;
        private Socket connection;
        private int myConID;
        private boolean alive = false;

        public SockServer(int conID) {
            myConID = conID;
        }

        @Override
        public void run() {

            alive = true;

            while(alive) {

                try {

                    getStreams();

                    while(alive) {

                        try {
                            processConnection();
                        } catch (EOFException e) {
                            logger.log("Server " + myConID + " terminated connection");
                            alive = false;
                        }

                    }

                    nClientsActive--;

                } catch (IOException e) {
                    logger.log(e.toString());
                }

            }

        }

        private void waitForConnection() throws IOException {

            logger.log("Waiting for connection " + myConID);
            connection = server.accept();
            logger.log("Connection " + myConID + " received from: " + connection.getInetAddress().getHostAddress());

        }

        private void getStreams() throws IOException {

            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());

        }

        private void processConnection() throws IOException {

            try {

                String command = (String) input.readObject();

                if(command.equals("TERMINATE")) {
                    closeConnection();
                    return;
                }

            } catch (ClassNotFoundException e) {
                logger.log("Server " + myConID + " received unknown object type");
            }

        }

        private void closeConnection() {

            alive = false;
            sendData("TERMINATE");
            logger.log("Terminating connection " + myConID);

            try {
                output.close();
                input.close();
                connection.close();
            } catch (IOException e) {
                logger.log(e.toString());
            }

        }

        private void sendData(Object data) {

            try {
                output.writeObject(data);
                output.flush();
                logger.log("Server " + myConID + " sent " + data.toString());
            } catch (IOException e) {
                logger.log("Server " + myConID + " failed to write data " + data.toString());
            }

        }

    }

}
