package lk.ijse;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) {
        try {
            System.out.println("Server Is Started !!!");
            ServerSocket serverSocket = new ServerSocket(3002);
            Socket localSocket = serverSocket.accept();
            System.out.println("Request Accepted !!!");
            DataInputStream dataInputStream = new DataInputStream(localSocket.getInputStream());

            DataOutputStream dataOutputStream = new DataOutputStream(localSocket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String message = "";
            String reply = "";

            while (!message.equals("end")) {
                message = dataInputStream.readUTF();
                System.out.println("Client: " + message);

                reply = bufferedReader.readLine();

                dataOutputStream.writeUTF(reply);
                dataOutputStream.flush();
            }

            dataInputStream.close();
            dataOutputStream.close();
            bufferedReader.close();
            localSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}