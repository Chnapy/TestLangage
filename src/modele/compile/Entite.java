/*
 * 
 * 
 * 
 */
package modele.compile;

import controleur.General;
import modele.precompile.ListDonnees;
import modele.precompile.Donnee;

/**
 * Personnage.java
 *
 */
public class Entite {

    private final static String LIENIMGBASIC = "/assets/img/scene/send_crate2.png";
    private final static String LIENIMGPERSO = "/assets/img/scene/user.png";

    protected final String nom;

    protected ListDonnees attributs;
    protected Lieu lieu;
    protected String lienImg;

    public Entite(String _nom, ListDonnees pdonnee) {
	nom = _nom;
	attributs = pdonnee;
	lieu = null;
	try {
	    lienImg = attributs.get("image").getValeur();
	} catch (Exception e) {
	    if (this.getClass().equals(Personnage.class)) {
		lienImg = LIENIMGPERSO;
	    } else {
		lienImg = LIENIMGBASIC;
	    }
	}
    }

    @Override
    public String toString() {
	String ret = nom + " {\n";
	if (lieu != null) {
	    ret += "l=" + lieu.getNom() + '\n';
	}
	for (Donnee attribut : attributs.values()) {
	    ret += attribut + "\n";
	}
	return ret + "}\n";
    }

    public String getTextLieu() {
	Donnee don = attributs.get("lieu");
	if (don == null) {
	    return null;
	}
	return don.getValeur();
    }

    public void setLieu(Lieu lie) {
	lieu = lie;
    }

    public String getNom() {
	return nom;
    }

    public ListDonnees getAttributs() {
	return attributs;
    }

    public String[] getStringAttributs() {
	return attributs.getStringValeur();
    }

    public String getLienImg() {
	return lienImg;
    }

    public Lieu getLieu() {
	return lieu;
    }

}
