package lk.ijse;

import java.io.*;
import java.net.Socket;

public class client {
    public static void main(String[] args) {

        try {
            Socket remoteSocket  = new Socket("localhost",3002);
            DataOutputStream dataOutputStream = new DataOutputStream(remoteSocket.getOutputStream());

            DataInputStream dataInputStream = new DataInputStream(remoteSocket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String message = "";
            String reply = "";

            while (!message.equals("end")) {
                reply = bufferedReader.readLine();
                dataOutputStream.writeUTF(reply);
                dataOutputStream.flush();
                message = dataInputStream.readUTF();
                System.out.println("Server: " + message);
            }

            dataInputStream.close();
            dataOutputStream.close();
            bufferedReader.close();
            remoteSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
