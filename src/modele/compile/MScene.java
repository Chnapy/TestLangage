/*
 * 
 * 
 * 
 */
package modele.compile;

import controleur.General;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * MScene.java
 *
 */
public class MScene {

    private Lieu lieu;
    private HashMap<String, Personnage> persos;
    private HashMap<String, Entite> choses;

    public MScene(Lieu l, ArrayList<Entite> entite) {
	lieu = l;
	persos = new HashMap<String, Personnage>();
	choses = new HashMap<String, Entite>();
	for (Entite ent : entite) {
	    if (ent.getClass().equals(Personnage.class)) {
		persos.put(ent.getNom(), (Personnage) ent);
	    } else {
		choses.put(ent.getNom(), ent);
	    }
	}
    }

    public void start() {

    }

    public void end() {

    }

    public Lieu getLieu() {
	return lieu;
    }

    public HashMap<String, Personnage> getPersos() {
	return persos;
    }

    public HashMap<String, Entite> getChoses() {
	return choses;
    }

    @Override
    public String toString() {
	return "Scene{" + "lieu=" + lieu + ", persos=" + persos + ", choses=" + choses + '}';
    }

}
