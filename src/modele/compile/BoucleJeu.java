/*
 * 
 * 
 * 
 */
package modele.compile;

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
    }

    @Override
    public void run() {
	run = true;
	update = false;

	while (run) {
	    Platform.runLater(() -> {
		setChanged();
		notifyObservers(scene);
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
    }

    public MScene getNewScene(String lieu) {
	return new MScene(univers.get(lieu), listEntites.getEntites(univers.get(lieu)));
    }

}
