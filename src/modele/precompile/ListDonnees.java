/*
 * 
 * 
 * 
 */
package modele.precompile;

import java.util.HashMap;

/**
 * ListDonnees.java
 *
 */
public class ListDonnees extends HashMap<String, Donnee> implements Donnee {

    private String nom;

    public ListDonnees(String _nom) {
	nom = _nom;
    }

    @Override
    public String toString() {
	String ret = nom + " {\n";
	for (Donnee donnee : this.values()) {
	    ret += donnee.toString() + '\n';
	}
	return ret + '}';
    }

    public String[] getStringValeur() {
	String[] ret = new String[keySet().size()];
	int i = 0;
	for (Donnee don : values()) {
	    ret[i] = don.getNom() + " : " + don.getValeur();
	    i++;
	}
	return ret;
    }

    @Override
    public String getValeur() {
	return "";
    }

    @Override
    public String getNom() {
	return nom;
    }

}
