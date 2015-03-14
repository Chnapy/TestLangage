/*
 * 
 * 
 * 
 */
package vue.compile;

import controleur.General;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * ButMove.java
 *
 */
public class ButMove extends Button {

    private Pane box;
    private ImageView node;
    private Label text;

    public ButMove(int placement, ImageView _node) {
	getStyleClass().add("butMove");
	node = _node;
	text = new Label();
	switch (placement) {
	    case 0:
		box = new VBox(node, text);
		text.setAlignment(Pos.CENTER);
		this.setNodeSize(100, false);
		break;
	    case 1:
		box = new VBox(text, node);
		text.setAlignment(Pos.CENTER);
		this.setNodeSize(100, false);
		break;
	    case 2:
		box = new HBox(node, text);
		text.setAlignment(Pos.CENTER_LEFT);
		this.setNodeSize(100, true);
		break;
	    case 3:
		box = new HBox(text, node);
		text.setAlignment(Pos.CENTER_RIGHT);
		this.setNodeSize(100, true);
		break;
	    default:
		throw new Error();
	}
	text.setWrapText(false);
	this.setGraphic(box);
	this.setNodeSize(100, true);
	this.setPadding(Insets.EMPTY);
    }

    private void setNodeSize(double width, boolean horizontal) {
	if (horizontal) {
	    box.setMaxWidth(width * General.RATIO_X);
	    box.setMaxHeight(width * General.RATIO_Y / 2);
	    node.setFitWidth(width * General.RATIO_X / 2);
	    node.setPreserveRatio(true);
	    text.setMinWidth(width * General.RATIO_X / 2);
	} else {
	    box.setMaxHeight(width * General.RATIO_Y);
	    box.setMaxWidth(width * General.RATIO_X / 2);
	    node.setFitHeight(width * General.RATIO_Y / 2);
	    node.setPreserveRatio(true);
	    text.setMinHeight(width * General.RATIO_Y / 2);
	}
    }

    public void setLabText(String text) {
	this.text.setText(text);
    }

    public void disable() {
	setDisabled(true);
	text.setTextFill(Color.web("white", 0.25));
	node.setOpacity(0.25);
    }

    public void enable() {
	setDisabled(false);
	text.setTextFill(Color.web("white", 1));
	node.setOpacity(1);
    }

}
