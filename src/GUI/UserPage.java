package GUI;


import com.dimizios.Product;
import com.dimizios.PurchaseHistoryEntry;
import javafx.application.Platform;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Dimitri
 */

public class UserPage {

    @FXML
    private TextField firstNameField, lastNameField, emailField, addressField, telephoneNumberField;

    @FXML
    private Button editButton, saveChangesButton, addToCartButton;
    @FXML
    private ImageView rightSideImage;
    @FXML
    private ListView<Product> productsListView;

    @FXML
    private ListView<PurchaseHistoryEntry> historyListView;

    private ObservableList<Product> productObservableList;

    private ObservableList<PurchaseHistoryEntry> purchaseHistoryEntries;

    @FXML
    private TextField bookInfoSearchField;

    @FXML
    private TabPane tabPane;

    @FXML
    public void initialize() {


        productObservableList = FXCollections.observableArrayList();
        productsListView.setItems(productObservableList);
        productsListView.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> param) {

                try {
                    return new ProductListviewCell();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });


        productObservableList.add(new Product(2, "description"));
        productObservableList.add(new Product(2, "lala"));
        productObservableList.add(new Product(2, "partymaker 1111111111"));
        purchaseHistoryEntries = FXCollections.observableArrayList();
        historyListView.setItems(purchaseHistoryEntries);

    /*    new Thread(() -> {
            ObservableList<BookInfo> fetchedBookInfoObservableList = FXCollections.observableArrayList(clientOperator.getInstance().queryBookListByTitle("%"));
            Platform.runLater(() -> this.productObservableList = fetchedBookInfoObservableList);
        }).start();
*/

        /*bookInfoObservableList.add(new BookInfo(3, "12 rules for life",
                new ArrayList<String>(Arrays.asList("Jordan peterson")), "999000",
                "Penguin books", new Date(), "Self help"));
        bookInfoObservableList.add(new BookInfo(3, "Murder in the corner",
                new ArrayList<String>(Arrays.asList("Dick Jobson")), "999010",
                "Penguin books", new Date(), "Novel"));
        bookInfoObservableList.add(new BookInfo(3, "Brothers karamazov",
                new ArrayList<String>(Arrays.asList("Fyodor Dostoyevsky")), "999002",
                "Penguin books", new Date(), "Novel"));
        bookInfoObservableList.add(new BookInfo(3, "Les miserables",
                new ArrayList<String>(Arrays.asList("Victor Hugo")), "999003",
                "Penguin books", new Date(), "Novel"));
        bookInfoListView.getSelectionModel().selectFirst();*/

    }

    public void showConfirmationDialog() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(tabPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ConfirmationDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {

            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            handleSaveChanges();

        } else {

return;
        }

    }

    @FXML
    public void productSearch() {

      /*  new Thread(() -> {
            ObservableList<BookInfo> fetchedBookInfoObservableList =
                    FXCollections.observableArrayList(clientOperator.getInstance().queryBookList(bookInfoSearchField.getText()));
            Platform.runLater(() -> productObservableList = fetchedBookInfoObservableList);
        }).start();

*/
    }

    @FXML
    public void addToCart() {
     /*   new Thread(() -> {

            BookInfo bookInfo = productsListView.getSelectionModel().getSelectedItem();

            boolean loanSuccess = clientOperator.getInstance().loanBook(GUIMain.getInstance().getUserAccount().getUsername()
                    , bookInfo.getIsbn());

            if (loanSuccess) {
                ObservableList<BookInfo> fetchedBookInfoObservableList =
                        FXCollections.observableArrayList(clientOperator.getInstance().queryBookList(bookInfoSearchField.getText()));
                Platform.runLater(() -> productObservableList = fetchedBookInfoObservableList);

            }


        }).start();
*/
    }

    @FXML
    public void handleEditButton() {

        firstNameField.setDisable(false);
        lastNameField.setDisable(false);
        emailField.setDisable(false);
        addressField.setDisable(false);
        telephoneNumberField.setDisable(false);
        saveChangesButton.setVisible(true);
        saveChangesButton.setDisable(false);


    }

    @FXML
    public void handleSaveChanges() {

        String newFirstName = firstNameField.getText();
        String newLastName = lastNameField.getText();
        String newEmail = emailField.getText();
        String newAddress = addressField.getText();
        String newTelephoneNumber = telephoneNumberField.getText();
        editButton.setDisable(true);
        saveChangesButton.setDisable(true);

       /* new Thread(() ->
        {
            String username = GUIMain.getInstance().getUserAccount().getUsername();
            boolean updatePersonalInfoSuccess = clientOperator.getInstance().updatePersonalInfo(username,
                    newFirstName, newLastName, newEmail, newAddress, newTelephoneNumber);
            if (updatePersonalInfoSuccess) {
                Account account = clientOperator.getInstance().queryAccount(username);
                GUIMain.getInstance().setUserAccount(account);

                if (account != null) {

                    Platform.runLater(() -> {

                        firstNameField.setText(newFirstName);
                        lastNameField.setText(newLastName);
                        emailField.setText(newEmail);
                        addressField.setText(newAddress);
                        telephoneNumberField.setText(newTelephoneNumber);
                        firstNameField.setDisable(true);
                        lastNameField.setDisable(true);
                        emailField.setDisable(true);
                        addressField.setDisable(true);
                        telephoneNumberField.setDisable(true);
                        editButton.setDisable(false);
                        saveChangesButton.setVisible(false);

                    });
                }
            }
        }
        ).start();
*/
    }

    private class PreviousPurchasesListviewCell extends ListCell<PurchaseHistoryEntry> {

        public PreviousPurchasesListviewCell() {

        }

        @Override
        protected void updateItem(PurchaseHistoryEntry item, boolean empty) {
            super.updateItem(item, empty);
        }
    }

    private class CartListViewCell extends ListCell<PurchaseHistoryEntry> {

        public CartListViewCell() {

        }

        @Override
        protected void updateItem(PurchaseHistoryEntry item, boolean empty) {
            super.updateItem(item, empty);
        }
    }

}