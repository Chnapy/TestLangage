/*
 * 
 * 
 * 
 */
package controleur;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javafx.stage.Stage;
import modele.ModeleCompile;
import modele.ModeleMain;
import vue.accordeon.Fichier;
import vue.VueMain;

/**
 * ControleurMain.java
 *
 */
public class ControleurMain {

    private final VueMain vMain;

    public ControleurMain(Stage primaryStage) {
	ModeleMain.init();
	vMain = new VueMain(primaryStage, this);
    }

    public File creerFichier(String path, int tentatives) {
	return ModeleMain.creerFichier(path, tentatives);
    }

    public File creerDossier(String path, int tentatives) {
	return ModeleMain.creerDossier(path, tentatives);
    }

    public boolean supprimer(Path path) {
	if (ModeleMain.supprimer(path)) {
	    vMain.supprimer(path);
	    return true;
	}
	return false;
    }

    public boolean rename(File fichSour, File fichDest) {
	return ModeleMain.rename(fichSour, fichDest);
    }

    public void ouvrirFichier(Fichier fichier) {
	fichier.setContenu(ModeleMain.lireFichier(fichier.getFichier()));
	vMain.ouvrirFichier(fichier);
    }

    public boolean enregistrer(Fichier fichActu) {
	if (fichActu.isModif()) {
	    return ModeleMain.enregistrer(fichActu);
	}
	return false;
    }

    public File creerProjet(String debutPath) {
	File dossierProjets = new File(debutPath);
	if (dossierProjets.isDirectory()) {
	    return ModeleMain.creerProjet(dossierProjets, 0);
	}
	General.pln("Veuillez choisir un dossier");
	return null;
    }

    public static String getDocumentation(String url) {
	try {
	    return ModeleMain.getDocumentation(url);
	} catch (IOException ex) {
	    return "Connexion au serveur impossible.";
	}
    }

    public void lancer(String code) {
	if (!code.isEmpty()) {
	    ControleurCompile controleurCompile = new ControleurCompile(code);
	}
    }

}
