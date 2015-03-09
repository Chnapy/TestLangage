/*
 * 
 * 
 * 
 */
package modele.precompile;

/**
 * Attribut.java
 *
 */
public class Attribut implements Donnee {

    private final String nom;

    private String valeur;

    public Attribut(String _nom) {
	nom = _nom;
    }

    public void set(String val) {
	valeur = val;
    }

    @Override
    public String getValeur() {
	return valeur;
    }

    @Override
    public String getNom() {
	return nom;
    }

    @Override
    public String toString() {
	return nom + " : " + valeur;
    }
}
