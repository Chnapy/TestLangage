/*
 * 
 * 
 * 
 */
package vue.accordeon;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

/**
 * ArbreFichier.java
 *
 */
public class ArbreFichier extends TreeItem<String> {

    public ArbreFichier(File chemin) {
	super();
	Path rootPath = chemin.toPath();
	this.setValue(chemin.getName());
	this.setGraphic(new ImageView("assets/img/arbreFichier/" + graphicPath(chemin)));
	if (Files.isDirectory(rootPath)) {
	    ajouter(rootPath, this);
	}
    }

    public void ajouter(Path chemin, ArbreFichier arb) {

	String[] listFichiers = chemin.toFile().list();
	if (listFichiers.length != 0) {
	    File chem;
	    for (String fichier : listFichiers) {
		chem = new File(chemin.toString() + "/" + fichier);
		arb.getChildren().add(new ArbreFichier(chem));
	    }
	}
    }

    private String graphicPath(File chemin) {

	if (chemin.isDirectory()) {
	    return "folder.png";
	}

	switch (chemin.getName().substring(chemin.getName().lastIndexOf(".") + 1)) {

	    case "txt":
		return "text-x-generic.png";
	    case "html":
		return "text-html.png";
	    case "png":
		return "image-x-generic.png";
	    case "jpg":
		return "image-x-generic.png";
	    case "jpeg":
		return "image-x-generic.png";
	    case "mp3":
		return "audio-x-generic.png";
	    case "wav":
		return "audio-x-generic.png";
	    default:
		return "inconnu.png";

	}
    }

}
