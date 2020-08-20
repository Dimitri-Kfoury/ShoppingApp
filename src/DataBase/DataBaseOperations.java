package DataBase;

import com.dimizios.CartEntry;
import com.dimizios.DetailedProduct;
import com.dimizios.PurchaseHistoryEntry;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class DataBaseOperations {

    private Connection databaseConnection;
    private static DataBaseOperations dataBaseOperationsInstance = new DataBaseOperations();

    public static void main(String[] args) {

    }

    private DataBaseOperations() {

        connectToDB();

    }

    public List<DetailedProduct> searchProducts(String key) throws SQLException {


        List<DetailedProduct> searchResult = new ArrayList<DetailedProduct>();
        PreparedStatement statement = null;


        statement = databaseConnection.prepareStatement("select * from products where product_name like ?");
        statement.setString(1, "%" + key + "%");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {

            searchResult.add(new DetailedProduct(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBytes(4)));

        }


        return searchResult;


    }

    public static DataBaseOperations getInstance() {
        return dataBaseOperationsInstance;
    }

    public boolean login(String username, String password) throws SQLException {

        PreparedStatement statement = null;
        statement = databaseConnection.prepareStatement("select password from users where username = ? ");
        statement.setString(1, username);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            return rs.getString(1).equals(password);
        }

        return false;


    }


    public String[] getPersonalDetails(String username) throws SQLException {


        String[] personalDetails = new String[4];

        PreparedStatement statement = databaseConnection.prepareStatement("select first_name,last_name,address,telephone_number from users where username = ? ");
        statement.setString(1, username);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            for (int i = 0; i < 4; i++) {

                personalDetails[i] = rs.getString(i + 1);

            }
        }

        return personalDetails;
    }

    public List<PurchaseHistoryEntry> getPurchaseHistory(String username) throws SQLException {

        List<PurchaseHistoryEntry> purchaseHistory = new ArrayList<>();


        PreparedStatement statement = databaseConnection.prepareStatement("select * from purchase_history inner join products on purchase_history.product_id = products.product_id where purchase_history.username = ?");
        statement.setString(1, username);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {

            purchaseHistory.add(new PurchaseHistoryEntry(rs.getString(1), rs.getString(6), rs.getString(7), rs.getBytes(8), rs.getInt(4), rs.getDate(3)));


        }


        return purchaseHistory;
    }

    public boolean editFirstName(String username, String firstName) throws SQLException {


        PreparedStatement statement = databaseConnection.prepareStatement("update users set first_name = ? where username = ?");
        statement.setString(1, firstName);
        statement.setString(2, username);
        statement.executeUpdate();
        return true;


    }

    public boolean editPassword(String username, String password) throws SQLException {


        PreparedStatement statement = databaseConnection.prepareStatement("update users set password = ? where username = ?");
        statement.setString(1, password);
        statement.setString(2, username);
        statement.executeUpdate();
        return true;


    }

    public boolean editAddress(String username, String address) throws SQLException {


        PreparedStatement statement = databaseConnection.prepareStatement("update users set address = ? where username = ?");
        statement.setString(1, address);
        statement.setString(2, username);
        statement.executeUpdate();
        return true;
    }


    public boolean editLastName(String username, String lastName) throws SQLException {


        PreparedStatement statement = databaseConnection.prepareStatement("update users set last_name = ? where username = ?");
        statement.setString(1, lastName);
        statement.setString(2, username);
        statement.executeUpdate();
        return true;


    }

    public boolean editTelephoneNumber(String username, String telephoneNumber) throws SQLException {

        PreparedStatement statement = databaseConnection.prepareStatement("update users set telephone_number = ? where username = ?");
        statement.setString(1, telephoneNumber);
        statement.setString(2, username);
        statement.executeUpdate();
        return true;

    }

    public boolean buy(String username, List<CartEntry> cart) throws SQLException {


        PreparedStatement statement = databaseConnection.prepareStatement("insert into purchase_history values(?,?,?,?)");
        statement.setString(2, username);
        for (CartEntry cartEntry : cart
        ) {

            statement.setString(1, cartEntry.getProductId());
            statement.setDate(3, null);
            statement.setInt(4, cartEntry.getQuantity());
            statement.executeUpdate();

        }

        return true;


    }

    public boolean createAccount(String username, String password, String firstName, String lastName, String address, String telephoneNumber) throws SQLException {


        PreparedStatement statement = databaseConnection.prepareStatement("insert into users values(?,?,?,?,?,?)");
        statement.setString(1, username);
        statement.setString(2, firstName);
        statement.setString(3, lastName);
        statement.setString(4, address);
        statement.setString(5, password);
        statement.setString(6, telephoneNumber);
        statement.executeUpdate();
        return true;

    }

    private void connectToDB() {

        try {

            Class.forName("org.postgresql.Driver");
            databaseConnection = DriverManager.getConnection("jdbc:postgresql://192.168.1.223:5432/msc_project_recommender_system", "postgres", "niwxz714");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void uploadProduct() throws SQLException {


        PreparedStatement statement = databaseConnection.prepareStatement("insert into products values(?,?,?,?)");


        byte[] image = new byte[]{};
        try {
            image = FileUtils.readFileToByteArray(new File("Resources/productImages/dogtoy1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        statement.setString(1,"image test item");
        statement.setString(2,"image test item");
        statement.setString(3,"image test item");
        statement.setBytes(4,image);
        statement.executeUpdate();


    }
}
