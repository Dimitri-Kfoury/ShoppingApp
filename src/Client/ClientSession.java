package Client;

import Server.Message;
import Server.ServerSession.Command;
import com.dimizios.CartEntry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.dimizios.DetailedProduct;
import com.dimizios.PurchaseHistoryEntry;


public class ClientSession {

    private String username;
    private int sessionId;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private static ClientSession instance = new ClientSession();


    public static void main(String[] args) {




    }

    private ClientSession() {


        try {
            socket = new Socket("localhost", 8888);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public synchronized boolean login(String username, String password) {

        try {

            outputStream.writeObject(new Message(Command.LOGIN, new String[]{username, password}));

            boolean loginSuccess = (boolean) inputStream.readObject();

            return loginSuccess;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public synchronized  boolean createAccount(String username, String password, String firstName, String lastName, String address, String telephoneNumber) {

        try {

            outputStream.writeObject(new Message(Command.CREATE_ACCOUNT, new String[]{username, password, firstName, lastName, address, telephoneNumber}));

            return (boolean) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public synchronized boolean addToCart(CartEntry cartEntry) {

        try {

            outputStream.writeObject(new Message(Command.ADD_TO_CART, cartEntry));

            return (boolean) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }


    }

    public synchronized  List<PurchaseHistoryEntry> getPurchaseHistory(String username) {

        try {

            outputStream.writeObject(new Message(Command.GET_PURCHASE_HISTORY, username));

            return (ArrayList<PurchaseHistoryEntry>) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }


    }

    public synchronized boolean editPassword(String username, String password) {

        try {

            outputStream.writeObject(new Message(Command.EDIT_PASSWORD, new String[]{username, password}));

            return (boolean) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }


    }

    public synchronized boolean editAddress(String username, String address) {

        try {

            outputStream.writeObject(new Message(Command.EDIT_ADDRESS, new String[]{username, address}));

            return (boolean) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }


    }

    public synchronized boolean editFirstName(String username, String firstName) {

        try {

            outputStream.writeObject(new Message(Command.EDIT_FIRST_NAME, new String[]{username, firstName}));

            return (boolean) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }


    }

    public synchronized boolean editLastName(String username, String lastName) {

        try {

            outputStream.writeObject(new Message(Command.EDIT_LAST_NAME, new String[]{username, lastName}));

            return (boolean) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }


    }

    public synchronized boolean editTelephoneNumber(String username, String telephoneNumber) {

        try {

            outputStream.writeObject(new Message(Command.EDIT_TELEPHONE_NUMBER, new String[]{username, telephoneNumber}));

            return (boolean) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public synchronized boolean buy(String username) {

        try {

            outputStream.writeObject(new Message(Command.BUY, username));

            return (boolean) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public synchronized boolean logout(String username) {

        try {

            outputStream.writeObject(new Message(Command.LOGOUT, username));

            return (boolean) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public synchronized ArrayList<DetailedProduct> search(String key) {

        try {

            outputStream.writeObject(new Message(Command.SEARCH_PRODUCTS, key));

            return (ArrayList<DetailedProduct>) inputStream.readObject();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }


    }

    public synchronized List<CartEntry> loadCart(String username) {


        try {

            outputStream.writeObject(new Message(Command.LOAD_CART, username));


            ArrayList<CartEntry> lol = (ArrayList<CartEntry>) inputStream.readObject();

            System.out.println(lol);
            return lol;


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }


    }

    public synchronized String[] loadPersonalDetails(String username) {

        try {

            outputStream.writeObject(new Message(Command.LOAD_PERSONAL_DETAILS, username));

            return (String[]) inputStream.readObject();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }


    }

    public  static ClientSession getInstance() {
        return instance;
    }
}
