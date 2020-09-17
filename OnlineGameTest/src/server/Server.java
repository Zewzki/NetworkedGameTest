package server;

import game.Game;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;
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

        private ArrayList<ClientConnection> connections;
        private int myConID;
        private boolean alive = false;
        private boolean waitForConnections = true;

        public SockServer(int conID) {
            myConID = conID;
        }

        @Override
        public void run() {

            waitForConnections = true;

            while(waitForConnections) {

                try {
                    waitForConnection();
                } catch (IOException e) {
                    System.err.println("Error receiving new connection");
                }

                String[] connectionData = getConnectionData();

                for(String s : connectionData) {
                    if(s.contains("START")) waitForConnections = false;
                }

            }

            Game game = new Game(getNumActiveConnections());

            alive = true;

            while(alive && !game.isOver()) {

                String[] connectionData = getConnectionData();



                int activeConnections = getNumActiveConnections();
                if(activeConnections <= 0) alive = false;

            }

            nClientsActive--;

        }

        private void waitForConnection() throws IOException {

            logger.log("Waiting for connection " + myConID);
            Socket connection = server.accept();
            connections.add(new ClientConnection(connection, logger, myConID));
            logger.log("Connection " + myConID + " received from: " + connection.getInetAddress().getHostAddress());

        }

        private String[] getConnectionData() {

            String[] connectionData = new String[connections.size()];

            for(int i = 0; i < connections.size(); i++) connectionData[i] = connections.get(i).getConnectionData();

            return connectionData;

        }

        private void processConnectionData() {

        }

        private int getNumActiveConnections() {

            int activeConnections = 0;
            for(ClientConnection cc : connections) if(cc.isAlive()) activeConnections++;

            return activeConnections;

        }

    }

}
