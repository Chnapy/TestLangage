/*
 * 
 * 
 * 
 */
package vue.accordeon;

import controleur.ControleurMain;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * TextFieldTreeCellImpl.java
 *
 */
public class TextFieldTreeCellImpl extends TreeCell<String> {

    private ControleurMain controleur;
    private TextField textField;
    private File oldFile;
    public static final String debutPath = new File(System.getProperty("user.dir") + "/Projets").toString();
    private final ContextMenu contextMenu1 = new ContextMenu();
    private final ContextMenu contextMenu2 = new ContextMenu();

    public TextFieldTreeCellImpl(ControleurMain control) {
	controleur = control;
	MenuItem cFichierMenuItem = new MenuItem("Créer un fichier");
	contextMenu1.getItems().add(cFichierMenuItem);
	cFichierMenuItem.setOnAction((ActionEvent t) -> {
	    creerFichier(controleur.creerFichier(debutPath + getPath(getTreeItem()), 0));
	});
	MenuItem cDossierMenuItem = new MenuItem("Créer un dossier");
	contextMenu1.getItems().add(cDossierMenuItem);
	cDossierMenuItem.setOnAction((ActionEvent t) -> {
	    creerDossier(controleur.creerDossier(debutPath + getPath(getTreeItem()), 0));
	});
	MenuItem oFichierMenuItem = new MenuItem("Ouvrir");
	contextMenu2.getItems().add(oFichierMenuItem);
	oFichierMenuItem.setOnAction((ActionEvent t) -> {
	    ouvrirFichier(debutPath + getPath(getTreeItem()));
	});
	MenuItem suppMenuItem1 = new MenuItem("Supprimer");
	MenuItem suppMenuItem2 = new MenuItem("Supprimer");
	contextMenu1.getItems().add(suppMenuItem1);
	contextMenu2.getItems().add(suppMenuItem2);
	suppMenuItem1.setOnAction((ActionEvent t) -> {
	    if (controleur.supprimer(new File(debutPath + getPath(getTreeItem())).toPath())) {
		supprimer();
	    }
	});
	suppMenuItem2.setOnAction((ActionEvent t) -> {
	    if (controleur.supprimer(new File(debutPath + getPath(getTreeItem())).toPath())) {
		supprimer();
	    }
	});
	MenuItem ouvMenuItem1 = new MenuItem("Ouvrir avec Windows");
	MenuItem ouvMenuItem2 = new MenuItem("Ouvrir avec Windows (bug)");
	contextMenu1.getItems().add(ouvMenuItem1);
	contextMenu2.getItems().add(ouvMenuItem2);
	ouvMenuItem1.setOnAction((ActionEvent t) -> {
	    try {
		Desktop.getDesktop().open(new File(debutPath + getPath(getTreeItem())));
	    } catch (IOException ex) {
		Logger.getLogger(TextFieldTreeCellImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	});
	ouvMenuItem2.setOnAction((ActionEvent t) -> {
	    try {
		Desktop.getDesktop().edit(new File(debutPath + getPath(getTreeItem())));
	    } catch (IOException ex) {
		Logger.getLogger(TextFieldTreeCellImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	});
    }

    @Override
    public void startEdit() {
	super.startEdit();
	oldFile = new File(debutPath + getPath(getTreeItem()));

	if (textField == null) {
	    createTextField();
	}
	setText(null);
	setGraphic(textField);
	textField.selectAll();
    }

    @Override
    public void cancelEdit() {
	super.cancelEdit();
	setText((String) getItem());
	setGraphic(getTreeItem().getGraphic());
    }

    @Override
    public void updateItem(String item, boolean empty) {
	super.updateItem(item, empty);

	if (empty) {
	    setText(null);
	    setGraphic(null);
	} else {
	    if (isEditing()) {
		if (textField != null) {
		    textField.setText(getString());
		}
		setText(null);
		setGraphic(textField);
	    } else {
		setText(getString());
		setGraphic(getTreeItem().getGraphic());
		if (new File(debutPath + getPath(getTreeItem())).isDirectory()) {
		    setContextMenu(contextMenu1);
		} else if (new File(debutPath + getPath(getTreeItem())).isFile()) {
		    setContextMenu(contextMenu2);
		}
	    }
	}
    }

    private void createTextField() {
	textField = new TextField(getString());
	textField.setOnKeyReleased((KeyEvent t) -> {
	    if (t.getCode() == KeyCode.ENTER) {
		commitEdit(textField.getText());
	    } else if (t.getCode() == KeyCode.ESCAPE) {
		cancelEdit();
	    }
	});
    }

    private String getString() {
	return getItem() == null ? "" : getItem();
    }

    @Override
    public void commitEdit(String t) {
	File fichierDest = new File(debutPath + getPath(getTreeItem()));
	if (oldFile.equals(fichierDest) || controleur.rename(oldFile, fichierDest)) {
	    super.commitEdit(t);
	} else {
	    System.err.println("Rename échoué : " + oldFile + " -> " + fichierDest);
	}
    }

    private String getPath(TreeItem<String> item) {
	if (item == null || item.getValue() == null) {
	    return "";
	}
	return getPath(item.getParent()) + "/" + item.getValue();
    }

    private void creerFichier(File fichier) {

	if (fichier != null) {
	    ArbreFichier nouvFichier = new ArbreFichier(fichier);
	    getTreeItem().getChildren().add(nouvFichier);
	    getTreeItem().setExpanded(true);
	}
    }

    private void creerDossier(File dossier) {

	if (dossier != null) {
	    ArbreFichier nouvDossier = new ArbreFichier(dossier);
	    getTreeItem().getChildren().add(nouvDossier);
	    getTreeItem().setExpanded(true);
	}

    }

    private void supprimer() {
	getTreeItem().getParent().getChildren().remove(getTreeItem());
    }

    private void ouvrirFichier(String path) {
	controleur.ouvrirFichier(new Fichier(new File(path)));
    }
}
