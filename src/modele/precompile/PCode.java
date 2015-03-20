/*
 * 
 * 
 * 
 */
package modele.precompile;

import static controleur.ControleurCompile.console;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Code.java Code précompilé
 *
 */
public class PCode extends ArrayList<Ligne> {

    private ListDonnees ressources;

    public PCode() {
    }

    public PCode(String oldCode) {
	setCode(oldCode);
	ressources = setRessources();
    }

    /**
     * Gère : les espaces aux extremes les lignes vides les commentaires en
     * debut de ligne
     *
     * @param code
     */
    public final void setCode(String code) {
	String textLigne;
	for (String ligne : code.split("\\n")) {
	    textLigne = ligne.trim();
	    if (!textLigne.isEmpty() && !textLigne.startsWith("//")) {
		this.add(new Ligne(textLigne.split("\\s")));
	    }
	}
	//console.println("Code précompilé.");
    }

    @Override
    public String toString() {
	String ret = "{\n";
	for (Ligne ligne : this) {
	    ret += "\t[\n";
	    ret += ligne.toString();
	    ret += "\t]\n";
	}
	return ret + "}";
    }

    public ListDonnees getRessources() {
	return ressources;
    }

    private ListDonnees setRessources() {
	LinkedList<ListDonnees> listAjouts = new LinkedList<ListDonnees>();
	ListDonnees ress = null;
	ListDonnees listActu = null;
	TabAttribut tabAttribut = null;
	String ligneNom;
	boolean tab = false;
	int i = 0;  //Debug

	for (Ligne ligne : this) {
//	    System.out.print(i);
	    ligneNom = ligne.getNom();
	    if (!listAjouts.isEmpty()) {
		if (ligne.isList()) {
		    ListDonnees nouvList = new ListDonnees(ligneNom);
		    listActu.putIfAbsent(ligneNom, nouvList);
		    listAjouts.add(nouvList);
		    listActu = nouvList;

//		    System.out.print(" b+");
		} else if (ligne.isEndList()) {
		    listAjouts.pollLast();
		    if (!listAjouts.isEmpty()) {
			listActu = listAjouts.getLast();
		    } else {
			listActu = null;
		    }

//		    System.out.print(" b-");
		} else if (ligne.isEndTable()) {

		    tab = false;

//		    System.out.print(" t-");
		} else if (tab) {

		    tabAttribut.add(ligne.get(0));

//		    System.out.print(" ta");
		} else if (ligne.isTable()) {
		    TabAttribut nouvTab = new TabAttribut(ligneNom);
		    listAjouts.getLast().putIfAbsent(ligneNom, nouvTab);
		    tabAttribut = nouvTab;
		    tab = true;

//		    System.out.print(" t+");
		} else if (ligne.isAttribut()) {
		    Attribut nouvAtt = new Attribut(ligneNom);
		    nouvAtt.set(ligne.getValeur());
		    listAjouts.getLast().putIfAbsent(ligneNom, nouvAtt);

//		    System.out.print(" a");
		}

		if (listAjouts.isEmpty()) {
		   // System.out.println("Ressources définies.");
//		    System.out.print(" break");
		    return ress;
		}

	    } else if (ligne.isList() && "ressources".equals(ligneNom)) {
		ress = new ListDonnees(ligneNom);
		listAjouts.add(ress);
		listActu = ress;

//		System.out.print(" p b+");
	    }
	    i++;

//	    General.pln();
	}
	console.perror("Création des ressources : accolade fermante manquante pour Ressources {");
	return null;
    }

}
