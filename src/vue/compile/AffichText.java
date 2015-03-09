/*
 * 
 * 
 * 
 */
package vue.compile;

import javafx.scene.text.Text;

/**
 * AffichText.java
 *
 */
public class AffichText extends Masque {

    private Text text;

    public AffichText() {
	text = new Text();
    }

    public AffichText(String txt) {
	this();
	text.setText(txt);
    }

}
