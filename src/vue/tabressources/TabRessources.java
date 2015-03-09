/*
 * 
 * 
 * 
 */
package vue.tabressources;

import controleur.General;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.stage.Stage;
import modele.precompile.Donnee;
import modele.precompile.ListDonnees;

/**
 * TabRessources.java
 *
 */
public class TabRessources extends Stage {

    private ListDonnees ressources;

    private TreeItem<Donnee> root;
    private TreeTableView<Donnee> treeTableView;

    public TabRessources() {
	root = new TreeItem();
	root.setExpanded(true);
	treeTableView = new TreeTableView<>(root);
	treeTableView.setMinSize(400 * General.RATIO_X, 400 * General.RATIO_Y);
	treeTableView.setShowRoot(false);

	this.setScene(new Scene(treeTableView));
	this.setTitle("Tableau des ressources");

	this.setX(10 * General.RATIO_X);
	this.setY(10 * General.RATIO_Y);
	this.show();
    }

    private TreeItem<Donnee> getTreeItem(Donnee donnee) {

	String classe = donnee.getClass().getSimpleName();
	TreeItem<Donnee> ret = new TreeItem<Donnee>(donnee);

	switch (classe) {
	    case "ListDonnees":
		((ListDonnees) donnee).values().stream().forEach((don) -> {
		    ret.getChildren().add(getTreeItem(don));
		});
		break;
	    case "Attribut":
		break;
	    case "TabAttribut":
		break;
	    default:
		throw new Error();
	}

	return ret;

    }

    public TabRessources(ListDonnees ress) {
	this();
	ressources = ress;
	root.setValue(ress);
	ress.values().stream().forEach((donnee) -> {
	    root.getChildren().add(getTreeItem(donnee));
	});

	TreeTableColumn<Donnee, String> donneeColonne = new TreeTableColumn<>("Données");
	TreeTableColumn<Donnee, String> valeurColonne = new TreeTableColumn<>("Valeur");
	donneeColonne.setMinWidth(199 * General.RATIO_X);
	valeurColonne.setMinWidth(199 * General.RATIO_X);
	donneeColonne.setCellValueFactory(
		(TreeTableColumn.CellDataFeatures<Donnee, String> param)
		-> new ReadOnlyStringWrapper(param.getValue().getValue().getNom())
	);
	valeurColonne.setCellValueFactory(
		(TreeTableColumn.CellDataFeatures<Donnee, String> param)
		-> new ReadOnlyStringWrapper(param.getValue().getValue().getValeur())
	);
	treeTableView.getColumns().setAll(donneeColonne, valeurColonne);

    }

}
