/*
 * 
 * 
 * 
 */
package vue.compile;

import controleur.General;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.ModeleCompile;
import modele.compile.BoucleJeu;
import modele.compile.Entite;
import modele.precompile.ListDonnees;
import modele.compile.MScene;
import modele.compile.Personnage;
import vue.HbEntite;

/**
 * VueCompile.java
 *
 */
public class VueCompile extends Stage implements Observer {

    private static final ImageView IMGUP = new ImageView("/assets/img/scene/arrow_up.png");
    private static final ImageView IMGDOWN = new ImageView("/assets/img/scene/arrow_down.png");
    private static final ImageView IMGLEFT = new ImageView("/assets/img/scene/arrow_left.png");
    private static final ImageView IMGRIGHT = new ImageView("/assets/img/scene/arrow_right.png");

    public static double MAX_WIDTH;
    public static double MAX_HEIGHT;
    private final ModeleCompile modele;

    private Scene scene;
    private Group groupe;
    private Masque masque;
    private ScrollPane sp;
    private HbEntite hb;

    private ButMove[] directions;
    private Label lLieu;
    private DescriptionBox descbox;

    public VueCompile(ModeleCompile model) {
	modele = model;

	ListDonnees fenetre = modele.getFenetre();
	ListDonnees informations = modele.getInformations();

	String titre;
	try {
	    titre = fenetre.get("titre").getValeur();
	} catch (NullPointerException e) {
	    titre = informations.get("nom").getValeur() + " by " + informations.get("auteur").getValeur();
	}
	VueCompile.MAX_WIDTH = Double.parseDouble(fenetre.get("largeur").getValeur()) * General.RATIO_X;
	VueCompile.MAX_HEIGHT = Double.parseDouble(fenetre.get("longueur").getValeur()) * General.RATIO_Y;

	this.setWidth(MAX_WIDTH + 16);
	this.setHeight(MAX_HEIGHT + 38);
	this.setTitle(titre);

	groupe = new Group();
	scene = new Scene(groupe);
	this.setScene(scene);
	scene.getStylesheets().add("/style/style.css");
	scene.setFill(Color.web("#444"));

	initHUD();

	init();

	this.setX(450 * General.RATIO_X);
	this.show();
    }

    private void initHUD() {
	directions = new ButMove[4];
	directions[0] = new ButMove(0, IMGUP);
	directions[1] = new ButMove(1, IMGDOWN);
	directions[2] = new ButMove(2, IMGLEFT);
	directions[3] = new ButMove(3, IMGRIGHT);
	setLayout(getButDevant(), (MAX_WIDTH / General.RATIO_X - 50) / 2, 0);
	setLayout(getButDerriere(), (MAX_WIDTH / General.RATIO_X - 50) / 2, MAX_HEIGHT / General.RATIO_Y - 100);
	setLayout(getButGauche(), 0, (MAX_HEIGHT / General.RATIO_Y - 100) / 2);
	setLayout(getButDroite(), MAX_WIDTH / General.RATIO_X - 100, (MAX_HEIGHT / General.RATIO_Y - 50) / 2);
	groupe.getChildren().addAll(directions);

	lLieu = new Label();
	lLieu.getStyleClass().add("labLieu");
	lLieu.setLayoutX(100 * General.RATIO_X);
	lLieu.setLayoutY(200 * General.RATIO_Y);
	lLieu.setMinWidth(MAX_WIDTH - 200 * General.RATIO_X - 16);
	lLieu.setMaxWidth(MAX_WIDTH - 200 * General.RATIO_X - 16);
	lLieu.setAlignment(Pos.CENTER);
	groupe.getChildren().add(lLieu);

	hb = new HbEntite();
	hb.setSpacing(20 * General.RATIO_X);
	sp = new ScrollPane(hb);
	sp.setLayoutX(100 * General.RATIO_X);
	sp.setLayoutY((MAX_HEIGHT - 130 * General.RATIO_Y) / 2);
	sp.setPrefViewportWidth(MAX_WIDTH - 200 * General.RATIO_X - 16);
	sp.setPrefViewportHeight(130 * General.RATIO_Y + 38);
	sp.getStyleClass().add("hbEntiteScroll");
	groupe.getChildren().add(sp);

	descbox = new DescriptionBox();
	groupe.getChildren().add(descbox);
    }

    private void init() {

	masque = new AffichEntite();
	groupe.getChildren().add(masque);

    }

    private ButMove getButDevant() {
	return directions[0];
    }

    private ButMove getButDerriere() {
	return directions[1];
    }

    private ButMove getButGauche() {
	return directions[2];
    }

    private ButMove getButDroite() {
	return directions[3];
    }

    private void setSize(Region region, double width, double height) {
	region.setMinSize(width * General.RATIO_X, height * General.RATIO_Y);
	region.setMaxSize(width * General.RATIO_X, height * General.RATIO_Y);
    }

    private void setLayout(Region region, double x, double y) {
	region.setLayoutX(x * General.RATIO_X);
	region.setLayoutY(y * General.RATIO_Y);
    }

    @Override
    public void update(Observable o, Object arg) {
	BoucleJeu bj = (BoucleJeu) o;
	MScene mscene = (MScene) arg;

	lLieu.setText(mscene.getLieu().getNom());

	try {
	    getButDevant().setLabText(mscene.getLieu().getlDevant().getNom());
	    getButDevant().setOnAction((event) -> {
		bj.update(mscene.getLieu().getlDevant().getNom());
	    });
	    getButDevant().enable();
	} catch (Exception e) {
	    getButDevant().setLabText("");
	    getButDevant().disable();
	}
	try {
	    getButDerriere().setLabText(mscene.getLieu().getlDerriere().getNom());
	    getButDerriere().setOnAction((event) -> {
		bj.update(mscene.getLieu().getlDerriere().getNom());
	    });
	    getButDerriere().enable();
	} catch (Exception e) {
	    getButDerriere().setLabText("");
	    getButDerriere().disable();
	}
	try {
	    getButGauche().setLabText(mscene.getLieu().getlGauche().getNom());
	    getButGauche().setOnAction((event) -> {
		bj.update(mscene.getLieu().getlGauche().getNom());
	    });
	    getButGauche().enable();
	} catch (Exception e) {
	    getButGauche().setLabText("");
	    getButGauche().disable();
	}
	try {
	    getButDroite().setLabText(mscene.getLieu().getlDroite().getNom());
	    getButDroite().setOnAction((event) -> {
		bj.update(mscene.getLieu().getlDroite().getNom());
	    });
	    getButDroite().enable();
	} catch (Exception e) {
	    getButDroite().setLabText("");
	    getButDroite().disable();
	}
	hb.getChildren().clear();

	for (Entite ent : mscene.getChoses().values()) {
	    VueEntite ve = new VueEntite(ent.getNom(), ent.getLienImg());
	    ve.setOnAction((event) -> {
		((AffichEntite) masque).setEntite(ent.getNom(), ent.getLienImg(), ent.getStringAttributs());
		masque.setVisible(true);
	    });
	    hb.getChildren().add(ve);
	}

	for (Personnage perso : mscene.getPersos().values()) {
	    VueEntite ve = new VueEntite(perso.getNom(), perso.getLienImg());
	    ve.setOnAction((event) -> {
		((AffichEntite) masque).setEntite(perso.getNom(), perso.getLienImg(), perso.getStringAttributs());
		masque.setVisible(true);
	    });
	    hb.getChildren().add(ve);
	}

    }

}
