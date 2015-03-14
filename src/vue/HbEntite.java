/*
 * 
 * 
 * 
 */
package vue;

import controleur.General;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import vue.compile.VueCompile;

/**
 * HbEntite.java
 *
 */
public class HbEntite extends HBox {	//TODO: Préférer FlowPane

    public HbEntite() {
	setMinWidth(VueCompile.MAX_WIDTH - 200 * General.RATIO_X - 16);
	setHeight(130 * General.RATIO_Y);
	setAlignment(Pos.CENTER);
	getStyleClass().add("hbEntite");
    }

}
