/*
 * 
 * 
 * 
 */
package vue.accordeon;

import controleur.ControleurMain;
import javafx.scene.control.TitledPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * Documentation.java
 *
 */
public class Documentation extends TitledPane {

    private final ControleurMain controleur;
    private final String site = "http://richardhaddad.fr/ide/doc/index.html";
    private final WebView browser;
    private final WebEngine webEngine;

    public Documentation(ControleurMain control) {
	controleur = control;

	browser = new WebView();
	webEngine = browser.getEngine();

	this.setContent(browser);
	this.setText("Documentation - " + site);
	update(site);
    }

    public final void update(String url) {
	webEngine.load(url);
    }

}
