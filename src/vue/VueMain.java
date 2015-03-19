/*
 * 
 * 
 * 
 */
package vue;

import vue.accordeon.Fichier;
import vue.accordeon.Arborescence;
import vue.accordeon.Documentation;
import com.sun.javafx.scene.control.behavior.TextInputControlBehavior;
import com.sun.javafx.scene.control.skin.TextInputControlSkin;
import controleur.ControleurMain;
import controleur.General;
import java.io.File;
import java.nio.file.Path;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import static vue.accordeon.TextFieldTreeCellImpl.debutPath;

/**
 * VueMain.java
 *
 */
public class VueMain {

    public static final double ACCORWIDTH = 400 * General.RATIO_X;
    public static final double BUTTONHEIGHT = 34 * General.RATIO_Y;
    public static final double ONGLETHEIGHT = 30;
    public static final double MARGEHAUTE = BUTTONHEIGHT + ONGLETHEIGHT;

    private final ControleurMain controleur;

    private final Stage stage;
    private final Group groupe;
    private final Scene scene;

    private TabPane listOnglets;
    private TextArea textZone;
    private HBox hList;
    private Accordion accor;
    private Arborescence arbo;
    private Documentation doc;

    private Button creProjet;
    private Button enregistrer;
    private Button enrTout;
    private Button annulerAction;
    private Button refaireAction;
    private Button lancer;
    private Button actuArbo;

    private Fichier fichActu;

    public VueMain(Stage st, ControleurMain control) {
	controleur = control;

	stage = st;
	stage.setTitle("IDE de la mort");
	stage.setWidth(General.WINDOW_WIDTH + 16);
	stage.setHeight(General.WINDOW_HEIGHT + 38);

	groupe = new Group();
	scene = new Scene(groupe);
	stage.setScene(scene);

	hList = new HBox();
	groupe.getChildren().add(hList);

	listOnglets = new TabPane();
	listOnglets.setLayoutY(BUTTONHEIGHT + 1);
	listOnglets.setMinWidth(General.WINDOW_WIDTH);
	listOnglets.setMinHeight(ONGLETHEIGHT);
	listOnglets.setMaxHeight(ONGLETHEIGHT);
	groupe.getChildren().add(listOnglets);

	initButton();
	initButton2();

	textZone = new TextArea();
	textZone.setLayoutY(MARGEHAUTE);
	textZone.setMinSize(General.WINDOW_WIDTH - ACCORWIDTH, General.WINDOW_HEIGHT - MARGEHAUTE);
	textZone.setMaxSize(General.WINDOW_WIDTH - ACCORWIDTH, General.WINDOW_HEIGHT - MARGEHAUTE);
	groupe.getChildren().add(textZone);
	textZone.setDisable(true);
	textZone.textProperty().addListener((final ObservableValue<? extends String> observable, final String oldValue, final String newValue) -> {
	    // Va être lancé à chaque changement dans le texte
	    if (!oldValue.isEmpty()) {
		fichActu.setContenu(textZone.getText());
		fichActu.modif(true);
		if (enregistrer.isDisabled()) {
		    enregistrer.setDisable(false);
		    enrTout.setDisable(false);
		}
	    }
	    //toto
	});

	accor = new Accordion();
	accor.setMinHeight(General.WINDOW_HEIGHT - 19);
	accor.setMinWidth(ACCORWIDTH);
	accor.setMaxWidth(ACCORWIDTH);
	accor.setLayoutX(General.WINDOW_WIDTH - ACCORWIDTH);
	accor.setLayoutY(MARGEHAUTE - 24);

	arbo = new Arborescence(controleur);
	arbo.setTextOverrun(OverrunStyle.CENTER_WORD_ELLIPSIS);
	accor.getPanes().add(arbo);

	doc = new Documentation(controleur);
	accor.getPanes().add(doc);
	accor.setExpandedPane(arbo);

	groupe.getChildren().add(accor);

//	stage.setResizable(false);
	stage.show();
    }

    private void initButton() {

	creProjet = new Button("CréerProjet");
	creProjet.setMinHeight(BUTTONHEIGHT);
	hList.getChildren().add(creProjet);

	enregistrer = new Button("Enregistrer");
	enregistrer.setMinHeight(BUTTONHEIGHT);
	enregistrer.setDisable(true);
	hList.getChildren().add(enregistrer);

	enrTout = new Button("EnregistrerTout");
	enrTout.setMinHeight(BUTTONHEIGHT);
	enrTout.setDisable(true);
	hList.getChildren().add(enrTout);

	annulerAction = new Button("AnnulerAction");
	annulerAction.setMinHeight(BUTTONHEIGHT);
	hList.getChildren().add(annulerAction);

	refaireAction = new Button("RefaireAction");
	refaireAction.setMinHeight(BUTTONHEIGHT);
	hList.getChildren().add(refaireAction);

	lancer = new Button("Lancer");
	lancer.setMinHeight(BUTTONHEIGHT);
	hList.getChildren().add(lancer);

	actuArbo = new Button("ActualiserArborescence");
	actuArbo.setMinHeight(BUTTONHEIGHT);
	hList.getChildren().add(actuArbo);
    }

    private void initButton2() {
	scene.getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN), (Runnable) () -> {
	    if (!enregistrer.isDisabled()) {
		enregistrer.setDisable(enregistrer(fichActu));
	    }
	});
	enregistrer.setMnemonicParsing(true);
	enregistrer.setOnAction((event) -> {
	    enregistrer.setDisable(enregistrer(fichActu));
	});
	enrTout.setOnAction((event) -> {
	    enrTout();
	});
	creProjet.setOnAction((event) -> {
	    File file = controleur.creerProjet(debutPath);
	    arbo.ajouter(file);
	    ouvrirFichier(new Fichier(
		    new File(file + "/src/"
			    + file.toString().substring(file.toString().lastIndexOf("\\") + 1)
			    + "Main")
	    ));
	});
	actuArbo.setOnAction((event) -> {
	    arbo.initArbo();
	});
	lancer.setOnAction((event) -> {
	    enrTout();
	    controleur.lancer(textZone.getText());
	});
	annulerAction.setOnAction((event) -> {
	    ((TextInputControlBehavior) ((TextInputControlSkin) textZone.getSkin()).getBehavior()).callAction("Undo");
	});
	refaireAction.setOnAction((event) -> {
	    ((TextInputControlBehavior) ((TextInputControlSkin) textZone.getSkin()).getBehavior()).callAction("Redo");
	});
    }

    private boolean enregistrer(Fichier fichier) {
	if (fichier != null && controleur.enregistrer(fichier)) {
	    fichier.modif(false);
	    return true;
	}
	return false;
    }

    private void enrTout() {
	listOnglets.getTabs().stream().forEach((fichier) -> {
	    enregistrer((Fichier) fichier);
	});
	enrTout.setDisable(true);
	enregistrer.setDisable(true);
    }

    public void ouvrirFichier(Fichier fichier) {
	listOnglets.getTabs().add(fichier);
	listOnglets.getSelectionModel().select(fichier);
	fichier.setOnSelectionChanged((event) -> {
	    fichActu = fichier;
	    textZone.setText(fichier.getContenu());
	    enregistrer.setDisable(!fichActu.isModif());
	});
	fichier.setOnClosed((event) -> {
	    if (listOnglets.getTabs().isEmpty()) {
		textZone.setText("");
		textZone.setDisable(true);
		enrTout.setDisable(true);
		enregistrer.setDisable(true);
	    }
	});
	fichActu = fichier;
	textZone.setText(fichier.getContenu());
	textZone.setDisable(false);
    }

    public void supprimer(Path path) {
	if (fichActu != null && path.equals(fichActu.getFichier().toPath())) {
	    listOnglets.getTabs().remove(fichActu);
	    if (listOnglets.getTabs().isEmpty()) {
		textZone.setText("");
		textZone.setDisable(true);
		enrTout.setDisable(true);
		enregistrer.setDisable(true);
	    }
	}
    }
}
