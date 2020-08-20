package GUI;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import org.apache.commons.lang3.StringUtils.*;

import Client.ClientSession;
import com.dimizios.CartEntry;
import com.dimizios.DetailedProduct;

import com.dimizios.PurchaseHistoryEntry;
import javafx.application.Platform;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.*;


import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import javafx.scene.paint.Color;
import javafx.util.Callback;


import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Optional;


/**
 * @author Dimitri
 */

public class UserPage {


    @FXML
    private TextField firstNameField, lastNameField, addressField, telephoneNumberField, quantityField;
    @FXML
    private Label addSuccessLabel, firstNameLabel, lastNameLabel, addressLabel, telephoneNumberLabel, buySuccessLabel;
    @FXML
    private Button editFirstName, editLastName, editPassword, editAddress, editTelephoneNumber, saveChangesButton, addToCartButton;
    @FXML
    private ImageView rightSideImage;
    @FXML
    private ListView<DetailedProduct> productsListView;

    @FXML
    private ListView<CartEntry> cartListView;
    @FXML
    private ListView<PurchaseHistoryEntry> historyListView;

    private ObservableList<DetailedProduct> productObservableList;

    private ObservableList<PurchaseHistoryEntry> purchaseHistoryEntries;

    private ObservableList<CartEntry> cartEntriesObservableList;

    @FXML
    private TextField productSearchField;

    @FXML
    private TabPane tabPane;

    @FXML
    public void initialize() {


        cartEntriesObservableList = FXCollections.observableArrayList();
        cartListView.setItems(cartEntriesObservableList);

        purchaseHistoryEntries = FXCollections.observableArrayList();
        historyListView.setItems(purchaseHistoryEntries);
        historyListView.setCellFactory(new Callback<ListView<PurchaseHistoryEntry>, ListCell<PurchaseHistoryEntry>>() {
            @Override
            public ListCell<PurchaseHistoryEntry> call(ListView<PurchaseHistoryEntry> param) {
                return new PreviousPurchasesListViewCell();
            }
        });

        productObservableList = FXCollections.observableArrayList();
        productsListView.setItems(productObservableList);
        productsListView.setCellFactory(new Callback<ListView<DetailedProduct>, ListCell<DetailedProduct>>() {
            @Override
            public ListCell<DetailedProduct> call(ListView<DetailedProduct> param) {

                try {
                    return new DetailedProductListViewCell();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });

        purchaseHistoryEntries = FXCollections.observableArrayList();
        historyListView.setItems(purchaseHistoryEntries);


        new Thread(() -> {
            ObservableList<DetailedProduct> fetchedDetailedProductObservableList = FXCollections.observableArrayList(ClientSession.getInstance().search("%"));
            Platform.runLater(() -> productObservableList.addAll(fetchedDetailedProductObservableList));
        }).start();


    }

    @FXML
    public void loadPersonalDetails() {


        new Thread(() ->
        {

            String[] personalDetails = ClientSession.getInstance().loadPersonalDetails("muriel");
            Platform.runLater(() -> {

                firstNameLabel.setText(personalDetails[0]);
                lastNameLabel.setText(personalDetails[1]);
                addressLabel.setText(personalDetails[2]);
                telephoneNumberLabel.setText(personalDetails[3]);
            });

        }).start();

    }

    @FXML
    public void loadCart() {


        new Thread(() -> {

            Platform.runLater(() -> this.cartEntriesObservableList.setAll(ClientSession.getInstance().loadCart("hey")));

        }).start();


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

        new Thread(() -> {
            ObservableList<DetailedProduct> fetchedDetailedProductObservableList =
                    FXCollections.observableArrayList(ClientSession.getInstance().search(productSearchField.getText()));
            Platform.runLater(() -> productObservableList.setAll(fetchedDetailedProductObservableList));
        }).start();

    }


    @FXML
    public void addToCart() {

        new Thread(() -> {


            String productId = productsListView.getSelectionModel().getSelectedItem().getProductId();
            CartEntry cartEntry = new CartEntry(productId, Integer.parseInt(quantityField.getText()));

            boolean addSuccess = ClientSession.getInstance().addToCart(cartEntry);

            Platform.runLater(() -> {


                if (addSuccess) {

                    addSuccessLabel.setText("Added " + productId + " to cart");
                    addSuccessLabel.setTextFill(Color.GREEN);


                } else {

                    addSuccessLabel.setText("Could not add item to cart");
                    addSuccessLabel.setTextFill(Color.RED);

                }

                addSuccessLabel.setVisible(true);

            });


        }).start();
    }

    @FXML
    public void handleEditButton() {

        firstNameField.setDisable(false);
        lastNameField.setDisable(false);
        addressField.setDisable(false);
        telephoneNumberField.setDisable(false);
        saveChangesButton.setVisible(true);
        saveChangesButton.setDisable(false);


    }

    @FXML
    public void buy() {

        new Thread(() ->

        {

            boolean purchaseSuccess = ClientSession.getInstance().buy("muriel");

            if (purchaseSuccess) {


                loadCart();
                Platform.runLater(() -> {
                    buySuccessLabel.setText("Success");
                    buySuccessLabel.setTextFill(Color.GREEN);

                    if (!buySuccessLabel.isVisible()) {
                        buySuccessLabel.setVisible(true);
                    }
                });
            } else {

                Platform.runLater(() -> {
                    buySuccessLabel.setText("Purchase Operation Failed");
                    buySuccessLabel.setTextFill(Color.RED);
                    if (!buySuccessLabel.isVisible()) {
                        buySuccessLabel.setVisible(true);
                    }
                });


            }


        }

        ).

                start();


    }

    @FXML
    public void handleSaveChanges() {


    }


    public void loadPurchaseHistory() {


        new Thread(() -> {
            ObservableList<PurchaseHistoryEntry> fetchedPurchaseHistoryObservableList =
                    FXCollections.observableArrayList(ClientSession.getInstance().getPurchaseHistory("dimizios"));
            Platform.runLater(() -> purchaseHistoryEntries.setAll(fetchedPurchaseHistoryObservableList));
        }).start();


    }

    private class CartListViewCell extends ListCell<PurchaseHistoryEntry> {

        public CartListViewCell() {

        }

        @Override
        protected void updateItem(PurchaseHistoryEntry item, boolean empty) {
            super.updateItem(item, empty);
        }
    }

    private class PreviousPurchasesListViewCell extends ListCell<PurchaseHistoryEntry> {

        private Label name = new Label(), purchaseDate = new Label(), quantity = new Label();
        private HBox hBox = new HBox();


        public PreviousPurchasesListViewCell() {


            hBox.setSpacing(10);
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.getChildren().addAll(name, purchaseDate, quantity);

        }

        @Override
        protected void updateItem(PurchaseHistoryEntry item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) {

                name.setText(item.getProductName());
//                purchaseDate.setText(item.getPurchaseDate().toString());
                quantity.setText(" quantity: " + item.getQuantity());
                setGraphic(hBox);


            }
        }
    }

}