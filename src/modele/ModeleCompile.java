/*
 * 
 * 
 * 
 */
package modele;

import modele.precompile.ListDonnees;
import modele.compile.ListEntites;
import modele.precompile.PCode;
import modele.compile.MScene;
import modele.compile.Univers;

/**
 * ModeleCompile.java
 *
 */
public class ModeleCompile {

    private PCode pcode;
    private Univers univers;
    private ListEntites listEntites;
    private MScene debut;

    private ListDonnees ressources;
    private ListDonnees[] tabRessources;

    public ModeleCompile(String oldCode) {
	tabRessources = new ListDonnees[8];
	pcode = new PCode(oldCode);
	compiler();
	debut = new MScene(univers.get("Hall"), listEntites.getEntites(univers.get("Hall")));
    }

    private void compiler() {
	ressources = pcode.getRessources();

	tabRessources[0] = getListDonnees("informations");
	tabRessources[1] = getListDonnees("fenetre");
	tabRessources[2] = getListDonnees("lieux");
	tabRessources[3] = getListDonnees("personnages");
	tabRessources[4] = getListDonnees("choses");
	tabRessources[5] = getListDonnees("actions");
	tabRessources[6] = getListDonnees("variables");
	tabRessources[7] = getListDonnees("fonctions");

	univers = new Univers(getLieux());  //Map permettant de récuppérer un Lieu depuis son nom
	listEntites = new ListEntites(getChoses(), getPersonnages());	//List permettant de récupérer une List d'Entite d'après son Lieu
	listEntites.setLieux(univers);
//	System.out.println(univers);
//	System.out.println(listEntites);
    }

    private ListDonnees getListDonnees(String nom) {
	return (ListDonnees) ressources.get(nom);
    }

    public PCode getPcode() {
	return pcode;
    }

    public ListDonnees getRessources() {
	return ressources;
    }

    public ListDonnees getInformations() {
	return tabRessources[0];
    }

    public ListDonnees getFenetre() {
	return tabRessources[1];
    }

    public ListDonnees getLieux() {
	return tabRessources[2];
    }

    public ListDonnees getPersonnages() {
	return tabRessources[3];
    }

    public ListDonnees getChoses() {
	return tabRessources[4];
    }

    public ListDonnees getActions() {
	return tabRessources[5];
    }

    public ListDonnees getVariables() {
	return tabRessources[6];
    }

    public ListDonnees getFonctions() {
	return tabRessources[7];
    }

    public Univers getUnivers() {
	return univers;
    }

    public ListEntites getListEntites() {
	return listEntites;
    }

    public MScene getDebut() {
	return debut;
    }

}
