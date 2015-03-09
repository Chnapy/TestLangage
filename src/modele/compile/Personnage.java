/*
 * 
 * 
 * 
 */
package modele.compile;

import modele.precompile.ListDonnees;
import modele.precompile.Donnee;

/**
 * Personnage.java
 *
 */
public class Personnage extends Entite {

    private boolean joueur;

    public Personnage(String _nom, ListDonnees pdonnee) {
	super(_nom, pdonnee);
	if (attributs.get("joueur") != null && attributs.get("joueur").getValeur().equals("oui")) {
	    joueur = true;
	}
    }

    @Override
    public String toString() {
	String ret = nom + " {\n";
	if (joueur) {
	    ret += "j=" + joueur + '\n';
	}
	if (lieu != null) {
	    ret += "l=" + lieu.getNom() + '\n';
	}
	for (Donnee attribut : attributs.values()) {
	    ret += attribut + "\n";
	}
	return ret + "}\n";
    }

}
