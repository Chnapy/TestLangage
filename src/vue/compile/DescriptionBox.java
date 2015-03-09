/*
 * 
 * 
 * 
 */
package vue.compile;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Pane;

/**
 * DescriptionBox.java
 *
 */
public class DescriptionBox extends Pane {

    public DescriptionBox() {
	setOpacity(0);
	this.setId("descBox");
    }

    public void show(double milisecondes) {
	try {
	    for (int i = 0; i < milisecondes; i += 50) {
		sleep(50);
		setOpacity(1 / milisecondes);
	    }
	} catch (InterruptedException ex) {
	    Logger.getLogger(DescriptionBox.class.getName()).log(Level.SEVERE, null, ex);
	}
	setOpacity(1);
    }

    public void hide(double milisecondes) {
	try {
	    for (int i = 0; i < milisecondes; i += 50) {
		sleep(50);
		setOpacity(1 - 1 / milisecondes);
	    }
	} catch (InterruptedException ex) {
	    Logger.getLogger(DescriptionBox.class.getName()).log(Level.SEVERE, null, ex);
	}
	setOpacity(0);
    }
}
