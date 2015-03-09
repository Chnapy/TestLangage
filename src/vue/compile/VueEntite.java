/*
 * 
 * 
 * 
 */
package vue.compile;

import controleur.General;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * VueEntite.java
 *
 */
public class VueEntite extends Button {

    private Label nom;
    private ImageView img;
    private VBox vb;

    public VueEntite(String _nom, String lienImg) {
	nom = new Label(_nom);
	img = new ImageView(lienImg);
	vb = new VBox(img, nom);
	setGraphic(vb);

	setMinSize(200 * General.RATIO_X, 265 * General.RATIO_Y);
	img.setFitWidth(200 * General.RATIO_X - 20);
	img.setPreserveRatio(true);
	nom.setWrapText(false);
	nom.setMinWidth(200 * General.RATIO_X - 20);
	nom.setAlignment(Pos.CENTER);

	vb.setSpacing(20);
	getStyleClass().add("butEntite");
    }

}
