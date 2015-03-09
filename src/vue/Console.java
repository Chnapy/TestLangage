/*
 * 
 * 
 * 
 */
package vue;

import com.sun.glass.ui.Screen;
import controleur.General;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Console.java
 *
 */
public class Console extends Stage {

    private TextArea textZone;

    public Console() {
	super();

	textZone = new TextArea();
	textZone.setEditable(false);

	this.setTitle("Débuguage");
	this.setScene(new Scene(textZone, 400 * General.RATIO_X, 400 * General.RATIO_Y));

	this.setX(10 * General.RATIO_X);
	this.setY(Screen.getMainScreen().getHeight() - getScene().getHeight() - 90 * General.RATIO_Y);
	this.show();
    }

}
