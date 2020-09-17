package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnection {

    private Socket connection;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    private ServerLogger logger;
    private int serverID;
    private boolean alive;

    public ClientConnection(Socket connection, ServerLogger logger, int serverID) {

        this.logger = logger;
        this.serverID = serverID;

        this.connection = connection;
        getStreams();

        alive = true;

    }

    private void getStreams() {

        try {

            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());

        } catch (IOException e) {
            System.err.println("Error getting streams for socket: " + connection.getInetAddress());
            e.printStackTrace();
        }

    }

    public String getConnectionData() {

        String command = "";

        try {

            command = (String) input.readObject();

            if(command.contains("TERMINATE")) {
                closeConnection();
                return command;
            }

        } catch (ClassNotFoundException e) {
            logger.log("Server " + serverID + " received unknown object type from " + connection.getInetAddress());
        } catch (IOException e) {
            logger.log("Server " + serverID + " encountered IOException from " + connection.getInetAddress());
        }

        return command;

    }

    private void sendData(Object data) {

        try {
            output.writeObject(data);
            output.flush();
            logger.log("Server " + serverID + " sent " + data.toString());
        } catch (IOException e) {
            logger.log("Server " + serverID + " failed to write data " + data.toString());
        }

    }

    private void closeConnection() {

        sendData("TERMINATE");
        logger.log("Terminating connection " + serverID);

        try {
            output.close();
            input.close();
            connection.close();
            alive = false;
        } catch (IOException e) {
            logger.log(e.toString());
        }

    }

    public Socket getConnection() { return connection; }
    public ObjectOutputStream getOutput() { return output; }
    public ObjectInputStream getInput() { return input; }
    public boolean isAlive() { return alive; }

}
