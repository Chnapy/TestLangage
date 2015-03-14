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

	setMinSize(100 * General.RATIO_X, 130 * General.RATIO_Y);
	img.setFitWidth(100 * General.RATIO_X - 10);
	img.setPreserveRatio(true);
	nom.setWrapText(false);
	nom.setMinWidth(100 * General.RATIO_X - 10);
	nom.setAlignment(Pos.CENTER);

	vb.setSpacing(10 * General.RATIO_Y);
	getStyleClass().add("butEntite");
    }

}
