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
    public static Console console;
    private BoucleJeu bouclejeu;

    public ControleurCompile(String code) {
	console = new Console();
	modele = new ModeleCompile(code);
	tabRess = new TabRessources(modele.getPcode().getRessources());
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
