package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;

import javax.swing.text.html.ImageView;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class BoatGUI implements Initializable {
    @FXML
    private ImageView image;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        File f = new File("src/boat1.png");
        Image image = new Image(f.toURI().toString());
    }
}