/*
 * 
 * 
 * 
 */
package modele.precompile;

import modele.precompile.Donnee;
import java.util.ArrayList;

/**
 * TabAttribut.java
 *
 */
public class TabAttribut implements Donnee {

    private String nom;

    private ArrayList<String> listValeurs;

    public TabAttribut(String _nom) {
	nom = _nom;
	listValeurs = new ArrayList<String>();
    }

    @Override
    public String toString() {
	String ret = nom + " [\n";
	for (String valeur : listValeurs) {
	    ret += valeur + '\n';
	}
	return ret + ']';
    }

    public void add(String valeur) {
	listValeurs.add(valeur);
    }

    @Override
    public String getValeur() {
	String ret = "";
	for (int i = 0; i < listValeurs.size(); i++) {
	    ret += listValeurs.get(i);
	    if (i < listValeurs.size() - 1) {
		ret += "\n";
	    }
	}
	return ret;
    }

    @Override
    public String getNom() {
	return nom;
    }

}
