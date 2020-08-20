package GUI;

import com.dimizios.DetailedProduct;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DetailedProductListViewCell extends ListCell<DetailedProduct> {


    HBox hbox = new HBox();
    Label label = new Label();
    ImageView imageView = new ImageView();

    public DetailedProductListViewCell() throws FileNotFoundException {

        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        hbox.setSpacing(10);
        hbox.getChildren().addAll(imageView, label);
        hbox.setAlignment(Pos.CENTER_LEFT);


    }

    @Override
    protected void updateItem(DetailedProduct item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);
        if (item != null && !empty) {
            label.setText(item.getDescription());
            byte[] imageBytes = item.getImageBytes();
            imageView.setImage(new Image(new ByteArrayInputStream(imageBytes)));
            setGraphic(hbox);
        }
    }

}
