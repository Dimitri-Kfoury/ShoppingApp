package Server;

import com.dimizios.CartEntry;
import com.dimizios.Product;
import com.dimizios.PurchaseHistoryEntry;

import java.sql.*;
import java.util.List;

public class DataBaseOperations {

    private Connection databaseConnection;
    private static DataBaseOperations dataBaseOperationsInstance = new DataBaseOperations();

    public static void main(String[] args) {



    }

    private DataBaseOperations() {

        connectToDB();

    }

protected List<Product> searchProducts(String key){

        return null;


}
    protected static DataBaseOperations getInstance() {
        return dataBaseOperationsInstance;
    }

    protected boolean login(String username, String password) throws SQLException {


        return false;
    }

    protected List<PurchaseHistoryEntry> getPurchaseHistory() throws SQLException {


        return null;
    }


    protected boolean edit(String username) {


        return false;
    }

    protected boolean buy(String username, List<CartEntry> cart) {

        return false;


    }
protected boolean createAccount(String username,String password,String firstName,String lastName,String address,String telephoneNumber){

        return false;
}

    private void connectToDB() {

        try {

            Class.forName("org.postgresql.Driver");
            databaseConnection = DriverManager.getConnection("jdbc:postgresql://192.168.1.223:5432/msc_project_recommender_system","postgres","niwxz714");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
