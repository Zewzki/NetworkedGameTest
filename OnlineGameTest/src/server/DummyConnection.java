package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.InetAddress;

public class DummyConnection {

    private static final int PORT = 25999;
    private static final String IP = "73.228.216.254";

    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public DummyConnection() {

        try {

            socket = new Socket(IP, PORT);
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendData(String message) {

        try {
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void close() {

        sendData("TERMINATE");

        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        DummyConnection d1 = new DummyConnection();
        DummyConnection d2 = new DummyConnection();
        DummyConnection d3 = new DummyConnection();

        d1.sendData("START");

        d3.close();
        d2.close();
        d1.close();



    }

}
