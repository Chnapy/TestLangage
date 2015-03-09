/*
 * 
 * 
 * 
 */
package modele.compile;

import modele.precompile.ListDonnees;
import java.util.HashMap;

/**
 * Univers.java
 *
 */
public class Univers extends HashMap<String, Lieu> {

    public Univers(ListDonnees lieux) {
	ListDonnees ldonnee;
	for (String nomDonnee : lieux.keySet()) {
	    ldonnee = (ListDonnees) lieux.get(nomDonnee);
	    this.put(nomDonnee, new Lieu(nomDonnee, ldonnee));
	}

	String lnom;
	for (Lieu lieu : values()) {
	    for (Lieu lieu2 : values()) {
		if (!lieu.equals(lieu2)) {
		    ldonnee = lieu2.getLdonnee();
		    lnom = lieu.getNom();
		    if (ldonnee.get("devant") != null && ldonnee.get("devant").getValeur().equals(lnom)) {
			lieu2.setlDevant(lieu);
			lieu.setlDerriere(lieu2);
		    }
		    if (ldonnee.get("derriere") != null && ldonnee.get("derriere").getValeur().equals(lnom)) {
			lieu2.setlDerriere(lieu);
			lieu.setlDevant(lieu2);
		    }
		    if (ldonnee.get("gauche") != null && ldonnee.get("gauche").getValeur().equals(lnom)) {
			lieu2.setlGauche(lieu);
			lieu.setlDroite(lieu2);
		    }
		    if (ldonnee.get("droite") != null && ldonnee.get("droite").getValeur().equals(lnom)) {
			lieu2.setlDroite(lieu);
			lieu.setlGauche(lieu2);
		    }
		    if (ldonnee.get("dessus") != null && ldonnee.get("dessus").getValeur().equals(lnom)) {
			lieu2.setlDessus(lieu);
			lieu.setlDessous(lieu2);
		    }
		    if (ldonnee.get("dessous") != null && ldonnee.get("dessous").getValeur().equals(lnom)) {
			lieu2.setlDessous(lieu);
			lieu.setlDessus(lieu2);
		    }
		}
	    }
	}

    }

    @Override
    public String toString() {
	String ret = "Univers {\n";
	for (Lieu lieu : values()) {
	    ret += lieu + "\n";
	}
	return ret + '}';
    }

}
