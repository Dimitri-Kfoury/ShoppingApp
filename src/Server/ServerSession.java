package Server;


import java.io.*;
import java.net.Socket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dimizios.*;

public class ServerSession extends Thread {

    private Socket socket;

    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;

    public ServerSession(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {


        System.out.println("Client connected");


        try {

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());


            while (!socket.isClosed()) {

                Object command;

                command = inputStream.readObject();

                if (command instanceof String) {

                    switch ((String) command) {

                        case "login": {

                            boolean loginSuccess = DataBaseOperations.getInstance().login();
                            outputStream.writeObject(loginSuccess);
                            break;
                        }

                        case "create": {

                            //boolean createAccountSuccess = DataBaseOperations.getInstance().createAccount();
                            //  outputStream.writeObject(createAccountSuccess);

                            break;
                        }
                        case "updatePersonalInfo": {
                            //boolean updatePersonalInfoSuccess = DataBaseOperations.getInstance().edit();

                            //outputStream.writeObject(updatePersonalInfoSuccess);
                            // break;
                        }
                        case "searchProducts": {

                            //ArrayList<Product> products = (ArrayList<Product>) DataBaseOperations.getInstance().searchProducts();
                            //  outputStream.writeObject(products);
                            break;
                        }
                        case "showHistory": {
                            // ArrayList<PurchaseHistoryEntry> purchaseHistoryEntries = DataBaseOperations.getInstance().getPurchaseHistory();
                            // outputStream.writeObject(purchaseHistoryEntries);
                            break;
                        }
                        case "purchase": {

                            boolean purchaseSuccess = DataBaseOperations.getInstance().buy((String) username, (List<CartEntry>) cart);
                            // outputStream.writeObject(purchaseSuccess);
                            break;
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String[] getData() {

        Object data = null;

        try {

            data = this.inputStream.readObject();
            ;
            assert data instanceof String;
            return ((String) data).split(",");


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }


    }

    public void closeConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

