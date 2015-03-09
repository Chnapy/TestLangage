/*
 * 
 * 
 * 
 */
package vue.accordeon;

import vue.accordeon.ArbreFichier;
import vue.accordeon.TextFieldTreeCellImpl;
import controleur.ControleurMain;
import java.io.File;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import static vue.accordeon.TextFieldTreeCellImpl.debutPath;

/**
 * Arborescence.java
 *
 */
public class Arborescence extends TitledPane {

    private final TreeItem<String> root;

    public Arborescence(ControleurMain controleur) {
	super();

	root = new TreeItem<>();
	TreeView<String> treeFile = new TreeView<>(root);
	treeFile.setEditable(true);
	treeFile.setCellFactory((TreeView<String> p) -> new TextFieldTreeCellImpl(controleur));
	treeFile.setShowRoot(false);

	initArbo();

	this.setText("ArboProjets - " + debutPath);
	this.setContent(treeFile);

    }

    public final void initArbo() {
	root.getChildren().clear();
	for (File file : new File(debutPath).listFiles()) {
	    root.getChildren().add(new ArbreFichier(file));
	}
    }

    public void ajouter(File projet) {
	if (projet != null) {
	    ArbreFichier arb = new ArbreFichier(projet);
	    root.getChildren().add(arb);
	    arb.setExpanded(true);
	    arb.getChildren().get(2).setExpanded(true);
	}
	this.setExpanded(true);
    }

}
