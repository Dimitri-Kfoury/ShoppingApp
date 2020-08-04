package GUI;

import com.dimizios.Product;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProductListviewCell extends ListCell<Product> {


    HBox hbox = new HBox();
    Label label = new Label("hey there");
    ImageView imageView = new ImageView(new Image(new FileInputStream("Resources/productImages/dogtoy1.jpg")));

    public ProductListviewCell() throws FileNotFoundException {

        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        hbox.setSpacing(10);
        hbox.getChildren().addAll(imageView, label);
        hbox.setAlignment(Pos.CENTER_LEFT);


    }

    @Override
    protected void updateItem(Product item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);
        if (item != null && !empty) {
            label.setText(item.getDescription());
            setGraphic(hbox);
        }
    }

}
