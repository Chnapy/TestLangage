/*
 * 
 * 
 * 
 */
package vue.compile;

import controleur.General;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * AffichEntite.java
 *
 */
public class AffichEntite extends Masque {

    private BorderPane pane;
    private Label lNom;
    private ImageView img;
    private Label lAttributs;
    private HBox listButtons;

    public AffichEntite() {
	getStyleClass().add("affichEntite");
	double xSize = VueCompile.MAX_WIDTH - 200 * General.RATIO_X;
	double ySize = VueCompile.MAX_HEIGHT - 200 * General.RATIO_Y;
	pane = new BorderPane();
	pane.getStyleClass().add("pane");
	pane.setLayoutX(90 * General.RATIO_X);
	pane.setLayoutY(90 * General.RATIO_Y);
	pane.setMaxSize(xSize, ySize);
	this.getChildren().add(pane);
	lNom = new Label();
	lNom.getStyleClass().add("lNom");
	lNom.setMinWidth(xSize);
	lNom.setMinHeight(ySize / 6);
	pane.setTop(lNom);
	img = new ImageView();
	img.getStyleClass().add("img");
	img.setFitWidth(xSize / 4);
	img.setPreserveRatio(true);
	pane.setLeft(new HBox(img));
	((HBox) pane.getLeft()).getStyleClass().add("paneImg");
	((HBox) pane.getLeft()).setMinWidth(xSize / 2);
	((HBox) pane.getLeft()).setMinHeight(ySize * 2 / 3);
	((HBox) pane.getLeft()).setAlignment(Pos.CENTER);
	lAttributs = new Label();
	lAttributs.getStyleClass().add("lAttributs");
	lAttributs.setMinWidth(xSize / 2);
	lAttributs.setMinHeight(ySize * 2 / 3);
	pane.setCenter(lAttributs);
	listButtons = new HBox();
	listButtons.getStyleClass().add("listButtons");
	listButtons.setMinHeight(ySize / 6);
	pane.setBottom(listButtons);

	setOnMouseClicked((MouseEvent event) -> {
	    double mx = event.getX();
	    double my = event.getY();
	    if (mx < 90 * General.RATIO_X || mx > 90 * General.RATIO_X + xSize || my < 90 * General.RATIO_Y || my > 90 * General.RATIO_Y + ySize) {
		setVisible(false);
	    }
	});
    }

    public void setEntite(String nom, String lienImg, String[] attributs) {
	lNom.setText(nom);
	img.setImage(new Image(lienImg));
	String txt = attributs[0];
	for (int i = 1; i < attributs.length; i++) {
	    txt += "\n" + attributs[i];
	}
	lAttributs.setText("Attributs : \n" + txt);
    }

}
