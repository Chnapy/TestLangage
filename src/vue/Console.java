/*
 * 
 * 
 * 
 */
package vue;

import com.sun.glass.ui.Screen;
import controleur.General;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Console.java
 *
 */
public class Console extends Stage {
    
    private TextArea textZone;
    private final DateFormat df = new SimpleDateFormat("HH:mm:ss");
    
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
    
    public void println(String text) {
	textZone.setText(textZone.getText() + df.format(new Date()) + ' ' + text + '\n');
    }

    public void perror(String text) {
	textZone.setText(textZone.getText() + "[ERREUR] " + df.format(new Date()) + ' ' + text + '\n');
    }
    
}
