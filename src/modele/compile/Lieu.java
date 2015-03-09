/*
 * 
 * 
 * 
 */
package modele.compile;

import modele.precompile.ListDonnees;

/**
 * Lieu.java
 *
 */
public class Lieu {

    private ListDonnees ldonnee;
    private String nom;

    private Lieu[] lieuxAdjacents;

    public Lieu(String _nom, ListDonnees ldonnee) {
	this.ldonnee = ldonnee;
	nom = _nom;
	lieuxAdjacents = new Lieu[6];
	for (Lieu lieu : lieuxAdjacents) {
	    lieu = null;
	}
    }

    //Action au moment d'entrer
    public void entree() {

    }

    //Action au moment de sortir
    public void sortir() {

    }

    public ListDonnees getLdonnee() {
	return ldonnee;
    }

    public String getNom() {
	return nom;
    }

    public void setlDevant(Lieu lDevant) {
	lieuxAdjacents[0] = lDevant;
    }

    public void setlDerriere(Lieu lDerriere) {
	lieuxAdjacents[1] = lDerriere;
    }

    public void setlGauche(Lieu lGauche) {
	lieuxAdjacents[2] = lGauche;
    }

    public void setlDroite(Lieu lDroite) {
	lieuxAdjacents[3] = lDroite;
    }

    public void setlDessus(Lieu lDessus) {
	lieuxAdjacents[4] = lDessus;
    }

    public void setlDessous(Lieu lDessous) {
	lieuxAdjacents[5] = lDessous;
    }

    public Lieu getlDevant() {
	return lieuxAdjacents[0];
    }

    public Lieu getlDerriere() {
	return lieuxAdjacents[1];
    }

    public Lieu getlGauche() {
	return lieuxAdjacents[2];
    }

    public Lieu getlDroite() {
	return lieuxAdjacents[3];
    }

    public Lieu getlDessus() {
	return lieuxAdjacents[4];
    }

    public Lieu getlDessous() {
	return lieuxAdjacents[5];
    }

    @Override
    public String toString() {
	String ret = nom + " {\n";
	if (lieuxAdjacents[0] != null) {
	    ret += "Devant : " + lieuxAdjacents[0].getNom() + '\n';
	}
	if (lieuxAdjacents[1] != null) {
	    ret += "Derriere : " + lieuxAdjacents[1].getNom() + '\n';
	}
	if (lieuxAdjacents[2] != null) {
	    ret += "Gauche : " + lieuxAdjacents[2].getNom() + '\n';
	}
	if (lieuxAdjacents[3] != null) {
	    ret += "Droite : " + lieuxAdjacents[3].getNom() + '\n';
	}
	if (lieuxAdjacents[4] != null) {
	    ret += "Dessus : " + lieuxAdjacents[4].getNom() + '\n';
	}
	if (lieuxAdjacents[5] != null) {
	    ret += "Dessous : " + lieuxAdjacents[5].getNom() + '\n';
	}
	return ret + '}';
    }

}
