/*
 * 
 * 
 * 
 */
package vue.accordeon;

import java.io.File;
import javafx.scene.control.Tab;

/**
 * Fichier.java
 *
 */
public class Fichier extends Tab {

    private String contenu;
    private File fichier;

    private boolean modif;

    public Fichier() {
	super();

	modif = false;
	contenu = new String();
    }

    public Fichier(File file) {
	this();

	this.fichier = file;
	this.setText(file.getName());
    }

    public String getContenu() {
	return contenu;
    }

    public void setContenu(String contenu) {
	this.contenu = contenu;
    }

    public File getFichier() {
	return fichier;
    }

    public void setFichier(File fichier) {
	this.fichier = fichier;
    }

    public void modif(boolean modif) {
	this.modif = modif;
	this.setClosable(!modif);
	if (modif) {
	    this.setStyle("-fx-font-weight: bold;");
	} else {
	    this.setStyle("-fx-font-weight: normal;");
	}
    }

    public boolean isModif() {
	return modif;
    }

}
