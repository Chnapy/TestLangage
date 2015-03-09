/*
 * 
 * 
 * 
 */
package modele.compile;

import modele.precompile.ListDonnees;
import java.util.ArrayList;

/**
 * ListPersonnages.java
 *
 */
public class ListEntites extends ArrayList<Entite> {

    public ListEntites(ListDonnees listChoses, ListDonnees listPersos) {
	ListDonnees pdonnee;
	for (String nomDonnee : listChoses.keySet()) {
	    pdonnee = (ListDonnees) listChoses.get(nomDonnee);
	    this.add(new Entite(nomDonnee, pdonnee));
	}
	for (String nomDonnee : listPersos.keySet()) {
	    pdonnee = (ListDonnees) listPersos.get(nomDonnee);
	    this.add(new Personnage(nomDonnee, pdonnee));
	}
    }

    @Override
    public String toString() {
	String ret = "Liste entités {\n";
	for (Entite en : this) {
	    ret += en + "\n";
	}
	return ret + '}';
    }

    public void setLieux(Univers univers) {
	String textLieu;
	for (Entite en : this) {
	    textLieu = en.getTextLieu();
	    if (textLieu != null) {
		en.setLieu(univers.get(textLieu));
	    }
	}
    }

    public ArrayList<Entite> getEntites(Lieu lieu) {
	ArrayList<Entite> ret = new ArrayList<Entite>();
	for (Entite en : this) {
	    if (lieu.equals(en.getLieu())) {
		ret.add(en);
	    }
	}
	return ret;
    }

}
