/*
 * 
 * 
 * 
 */
package vue.compile;

import javafx.scene.layout.Pane;

/**
 * Masque.java
 *
 */
public abstract class Masque extends Pane {

    public Masque() {
	setMinWidth(VueCompile.MAX_WIDTH - 20);
	setMinHeight(VueCompile.MAX_HEIGHT - 20);
	setLayoutX(10);
	setLayoutY(10);
	getStyleClass().add("masque");

	setVisible(false);
    }

}
