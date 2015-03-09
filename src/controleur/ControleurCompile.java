/*
 * 
 * 
 * 
 */
package controleur;

import modele.ModeleCompile;
import modele.compile.BoucleJeu;
import vue.Console;
import vue.tabressources.TabRessources;
import vue.compile.VueCompile;

/**
 * ControleurCompile.java
 *
 */
public class ControleurCompile {

    private ModeleCompile modele;
    private VueCompile vue;
    private TabRessources tabRess;
    private Console console;
    private BoucleJeu bouclejeu;

    public ControleurCompile(ModeleCompile model) {
	modele = model;
	tabRess = new TabRessources(modele.getPcode().getRessources());
	console = new Console();
	vue = new VueCompile(modele);
	vue.setOnCloseRequest((event) -> {
	    tabRess.close();
	    console.close();
	    bouclejeu.stop();
	});
	bouclejeu = new BoucleJeu(modele.getUnivers(), modele.getListEntites());
	bouclejeu.addObserver(vue);
	bouclejeu.start(modele.getDebut());

    }

}
