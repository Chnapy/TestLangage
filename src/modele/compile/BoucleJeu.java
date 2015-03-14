/*
 * 
 * 
 * 
 */
package modele.compile;

import static controleur.ControleurCompile.console;
import controleur.General;
import static java.lang.Thread.sleep;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 * BoucleJeu.java
 *
 */
public class BoucleJeu extends Observable implements Runnable {

    private Thread th;
    private MScene scene;
    private Univers univers;
    private ListEntites listEntites;
    private boolean run;
    private boolean update;

    public BoucleJeu(Univers univ, ListEntites listEnt) {

	univers = univ;
	listEntites = listEnt;

	th = new Thread(this);
	th.setName("Boucle de jeu");
    }

    public void start(MScene debut) {
	scene = debut;
	th.start();
	console.println("Boucle de jeu lancée !");
    }

    @Override
    public void run() {
	run = true;
	update = false;

	while (run) {
	    Platform.runLater(() -> {
		setChanged();
		notifyObservers(scene);
		console.println("Nouvelle scene lancée.");
	    });
	    scene.start();

	    try {
		while (!update) {
		    sleep(100);
		}
	    } catch (InterruptedException ex) {
		Logger.getLogger(BoucleJeu.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    update = false;

	    scene.end();
	}

    }

    public void update(String lieu) {
	scene = getNewScene(lieu);
	General.pln(lieu);
	update = true;
    }

    public void stop() {
	run = false;
	update = true;
	console.println("Boucle de jeu terminée.");
    }

    public MScene getNewScene(String lieu) {
	return new MScene(univers.get(lieu), listEntites.getEntites(univers.get(lieu)));
    }

}
