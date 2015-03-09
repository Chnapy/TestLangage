/*
 * 
 * 
 * 
 */
package vue.compile;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * AffichEntite.java
 *
 */
public class AffichEntite extends Masque {

    private Label lNom;
    private ImageView img;
    private Label lAttributs;

    public AffichEntite() {
	lNom = new Label();
	img = new ImageView();
	lAttributs = new Label();
	this.getChildren().addAll(lNom, img, lAttributs);
    }

    public void setEntite(String nom, String lienImg, String[] attributs) {
	lNom.setText(nom);
	img.setImage(new Image(lienImg));
	String txt = attributs[0];
	for (int i = 1; i < attributs.length; i++) {
	    txt += "\n" + attributs[i];
	}
	lAttributs.setText(txt);
    }

}
