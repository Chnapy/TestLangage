/*
 * 
 * 
 * 
 */
package modele.precompile;

import java.util.ArrayList;

/**
 * Ligne.java
 *
 */
public class Ligne extends ArrayList<String> {

    private Propriete[] propriete;

    private enum Propriete {
	NULL, ATTRIBUT, LIST, TABLE, ENDLIST, ENDTABLE;
    }

    public Ligne(String[] tabMots) {
	add(tabMots);
	propriete = new Propriete[5];
	for (int i = 0; i < propriete.length; i++) {
	    propriete[i] = Propriete.NULL;
	}
	setPropriete();
    }

    /**
     * Gère : les commentaires en milieu/fin de ligne
     *
     * @param tabMots
     */
    public final void add(String[] tabMots) {
	String mot;
	for (String tmot : tabMots) {
	    mot = tmot.trim();
	    if (mot.startsWith("//") || mot.isEmpty()) {
		break;
	    }
	    this.add(mot);
	}
    }

    @Override
    public String toString() {
	String ret = new String();
	for (String mot : this) {
	    ret += "\t\t" + mot + "\n";
	}
	return ret;
    }

    /**
     * TODO
     * @return 
     */
    public String getNom() {
	if (!isAttribut() && !isList() && !isTable()) {
	    return null;
	}

	if ((isList() || isTable()) && this.size() == 2) {
	    return this.get(0);
	} else if (isAttribut()) {
	    if (this.get(1).equals(":")) {
		return this.get(0);
	    }
	    if (!this.get(0).contains(":")) {
		return this.get(0);
	    }
	    return this.get(0).substring(0, this.get(0).length() - 1);
	}

	return null;
    }

    private void setPropriete() {
	for (String mot : this) {
	    if (mot.contains(":")) {
		propriete[0] = Propriete.ATTRIBUT;
		break;
	    }
	}
	switch (this.getLast()) {
	    case "{":
		propriete[1] = Propriete.LIST;
		break;
	    case "[":
		propriete[2] = Propriete.TABLE;
		break;
	}
	switch (this.get(0)) {
	    case "}":
		propriete[3] = Propriete.ENDLIST;
		break;
	    case "]":
		propriete[4] = Propriete.ENDTABLE;
		break;
	}

    }

    public boolean isAttribut() {
	return propriete[0].equals(Propriete.ATTRIBUT);
    }

    public boolean isList() {
	return propriete[1].equals(Propriete.LIST);
    }

    public boolean isTable() {
	return propriete[2].equals(Propriete.TABLE);
    }

    public boolean isEndList() {
	return propriete[3].equals(Propriete.ENDLIST);
    }

    public boolean isEndTable() {
	return propriete[4].equals(Propriete.ENDTABLE);
    }

    public String getLast() {
	return this.get(this.size() - 1);
    }

    public String getValeur() {
	String ret = "";
	int i;
	if (!isAttribut()) {
	    return null;
	}
	if (!this.get(1).contains(":")) {
	    i = 1;
	} else if (this.get(1).equals(":")) {
	    i = 2;
	} else {
	    i = 2;
	    ret = this.get(1).substring(1, this.get(1).length()) + ' ';
	}
	for (; i < this.size(); i++) {
	    ret += this.get(i);
	    if (i < this.size() - 1) {
		ret += ' ';
	    }
	}
	return ret;
    }

}
