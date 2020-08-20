package Server;


import DataBase.DataBaseOperations;
import com.dimizios.CartEntry;
import com.dimizios.DetailedProduct;
import com.dimizios.PurchaseHistoryEntry;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarOutputStream;


public class ServerSession extends Thread {

    public enum Command {
        LOGIN, CREATE_ACCOUNT, EDIT_PASSWORD, EDIT_ADDRESS, EDIT_FIRST_NAME, EDIT_LAST_NAME, EDIT_TELEPHONE_NUMBER, BUY, GET_PURCHASE_HISTORY, SEARCH_PRODUCTS, ADD_TO_CART, LOGOUT, LOAD_CART, LOAD_PERSONAL_DETAILS
    }

    private Socket socket;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    private int sessionId;
    private ArrayList<CartEntry> cart;
    private ArrayList<Integer> integerArrayList;


    public ServerSession(Socket socket) {
        this.socket = socket;
        this.cart = new ArrayList<>();
        this.integerArrayList = new ArrayList<>();
    }

    @Override
    public void run() {

        System.out.println("connected");

        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());


            while (!socket.isClosed()) {


                Message message = (Message) inputStream.readObject();
                Command command = message.getCommand();
                Object data = message.getData();

                switch (command) {

                    case LOGIN: {

                        boolean loginSuccess = DataBaseOperations.getInstance().login(((String[]) data)[0], ((String[]) data)[1]);
                        outputStream.writeObject(loginSuccess);
                        break;
                    }
                    case CREATE_ACCOUNT: {

                        boolean createSuccess = DataBaseOperations.getInstance().createAccount(((String[]) data)[0], ((String[]) data)[1], ((String[]) data)[2], ((String[]) data)[3], ((String[]) data)[4], ((String[]) data)[5]);
                        outputStream.writeObject(createSuccess);
                        break;
                    }
                    case GET_PURCHASE_HISTORY: {

                        List<PurchaseHistoryEntry> purchaseHistoryEntries = DataBaseOperations.getInstance().getPurchaseHistory((String) data);
                        outputStream.writeObject(purchaseHistoryEntries);
                        break;
                    }
                    case BUY: {
                        if (this.cart == null)
                            outputStream.writeObject(false);
                        else {

                            boolean purchaseSuccess = DataBaseOperations.getInstance().buy((String) data, this.cart);
                            if (purchaseSuccess) {
                                this.cart.clear();
                            }

                            outputStream.writeObject(purchaseSuccess);
                        }
                        break;
                    }
                    case ADD_TO_CART: {

                        int productQuantity = ((CartEntry) data).getQuantity();

                        if (productQuantity > 0) {
                            boolean addSuccess = this.cart.add((CartEntry) data);
                            outputStream.writeObject(addSuccess);
                        } else {

                            outputStream.writeObject(false);

                        }
                        break;

                    }

                    case LOGOUT: {

                        Server.removeActiveSession(this.sessionId);
                        outputStream.writeBoolean(true);
                        break;
                    }

                    case EDIT_FIRST_NAME: {

                        boolean editSuccess = DataBaseOperations.getInstance().editFirstName(((String[]) data)[0], ((String[]) data)[1]);
                        outputStream.writeBoolean(editSuccess);
                        break;
                    }
                    case EDIT_LAST_NAME: {
                        boolean editSuccess = DataBaseOperations.getInstance().editLastName(((String[]) data)[0], ((String[]) data)[1]);
                        outputStream.writeBoolean(editSuccess);
                        break;
                    }

                    case EDIT_PASSWORD: {
                        boolean editSuccess = DataBaseOperations.getInstance().editPassword(((String[]) data)[0], ((String[]) data)[1]);
                        outputStream.writeBoolean(editSuccess);
                        break;
                    }

                    case EDIT_ADDRESS: {
                        boolean editSuccess = DataBaseOperations.getInstance().editAddress(((String[]) data)[0], ((String[]) data)[1]);
                        outputStream.writeBoolean(editSuccess);
                        break;
                    }
                    case EDIT_TELEPHONE_NUMBER: {
                        boolean editSuccess = DataBaseOperations.getInstance().editTelephoneNumber(((String[]) data)[0], ((String[]) data)[1]);
                        outputStream.writeBoolean(editSuccess);
                        break;
                    }
                    case SEARCH_PRODUCTS: {

                        List<DetailedProduct> products = null;

                        products = DataBaseOperations.getInstance().searchProducts((String) data);

                        outputStream.writeObject(products);
                        break;
                    }

                    case LOAD_CART: {

                        outputStream.reset();
                        outputStream.writeObject(cart);

                        break;
                    }

                    case LOAD_PERSONAL_DETAILS: {

                        String[] personalDetails = DataBaseOperations.getInstance().getPersonalDetails((String) data);

                        outputStream.writeObject(personalDetails);
                        break;


                    }

                }

            }
        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
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

