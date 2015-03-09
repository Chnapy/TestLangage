/*
 * 
 * 
 * 
 */
package modele;

import controleur.General;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static modele.Network.get;
import vue.accordeon.Fichier;
import vue.accordeon.TextFieldTreeCellImpl;
import static vue.accordeon.TextFieldTreeCellImpl.debutPath;

/**
 * ModeleMain.java
 *
 */
public class ModeleMain {

    public static void init() {
	try {
	    Files.createDirectory(new File(debutPath).toPath());
	} catch (FileAlreadyExistsException ex) {
	} catch (IOException ex) {
	    Logger.getLogger(ModeleMain.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public static File creerFichier(String path, int tentatives) {

	String num = "";
	if (tentatives > 20) {
	    throw new Error("ModeleMain.java : creerFichier() : " + path);
	} else if (tentatives != 0) {
	    num = Integer.toString(tentatives);
	}

	try {
	    File fichier = new File(path + "\\nouveau_fichier" + num);
	    Files.createFile(fichier.toPath());
	    General.pln("Fichier créé : " + fichier.getPath());
	    return fichier;
	} catch (FileAlreadyExistsException ex) {
	    return creerFichier(path, tentatives + 1);
	} catch (IOException ex) {
	    Logger.getLogger(TextFieldTreeCellImpl.class.getName()).log(Level.SEVERE, null, ex);
	}

	return null;
    }

    public static File creerDossier(String path, int tentatives) {

	String num = "";
	if (tentatives > 20) {
	    throw new Error("ModeleMain.java : creerDossier() : " + path);
	} else if (tentatives != 0) {
	    num = Integer.toString(tentatives);
	}

	try {
	    File dossier = new File(path + "\\nouveau_dossier" + num);
	    Files.createDirectory(dossier.toPath());
	    General.pln("Dossier créé : " + dossier.getPath());
	    return dossier;
	} catch (FileAlreadyExistsException ex) {
	    return creerDossier(path, tentatives + 1);
	} catch (IOException ex) {
	    Logger.getLogger(TextFieldTreeCellImpl.class.getName()).log(Level.SEVERE, null, ex);
	}

	return null;
    }

    public static boolean supprimer(Path path) {
	try {
	    Files.deleteIfExists(path);
	} catch (DirectoryNotEmptyException e) {
	    System.err.println("Videz le dossier avant sa suppression");
	    return false;
	} catch (FileSystemException e) {
	    System.err.println("Fichier en cours d'utilisation\n" + e);
	    return false;
	} catch (IOException ex) {
	    Logger.getLogger(ModeleMain.class.getName()).log(Level.SEVERE, null, ex);
	}
	General.pln("Supprimé : " + path);
	return true;
    }

    public static boolean rename(File fichSour, File fichDest) {
	return fichSour.renameTo(fichDest);
    }

    public static String lireFichier(File file) {
	String ret = new String();
	try {
	    Scanner s = new Scanner(file, "UTF-8");

	    while (s.hasNextLine()) {
		ret = ret.concat(s.nextLine());
		if (s.hasNextLine()) {
		    ret = ret.concat("\n");
		}
	    }
	    s.close();
	} catch (FileNotFoundException ex) {
	    System.err.println(ex);
	}
	General.pln("Lu : " + file.getPath());
	return ret;
    }

    public static boolean enregistrer(Fichier fichier) {

	File fich = fichier.getFichier();
	BufferedWriter output;
	try {
	    output = new BufferedWriter(new FileWriter(fich));
	    output.write(fichier.getContenu());
	    output.flush();
	    output.close();
	    General.pln("Fichier enregistré : " + fichier.getFichier().getPath());
	    return true;
	    //supprimer
	} catch (IOException ex) {
	    Logger.getLogger(TextFieldTreeCellImpl.class.getName()).log(Level.SEVERE, null, ex);
	}

	return false;
    }

    public static File creerProjet(File file, int tentatives) {

	String num = "";
	if (tentatives > 20) {
	    throw new Error("ModeleMain.java : creerProjet() : " + file);
	} else if (tentatives != 0) {
	    num = Integer.toString(tentatives);
	}

	String nom = "nouveau_projet" + num;
	String path = file + "\\" + nom;
	try {
	    File projet = new File(path);
	    Files.createDirectory(projet.toPath());
	    Files.createDirectory(new File(path + "/src").toPath());
	    Files.createDirectory(new File(path + "/assets").toPath());
	    Files.createDirectory(new File(path + "/assets/img").toPath());
	    Files.createDirectory(new File(path + "/assets/sounds").toPath());
	    Files.createDirectory(new File(path + "/assets/videos").toPath());
	    Files.createFile(new File(path + "/" + nom + ".abc").toPath());
	    Files.createFile(new File(path + "/src/" + nom + "Main").toPath());

	    General.pln("Projet créé : " + projet.getPath());
	    return projet;
	} catch (FileAlreadyExistsException ex) {
	    return creerProjet(file, tentatives + 1);
	} catch (IOException ex) {
	    Logger.getLogger(TextFieldTreeCellImpl.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }

    public static String getDocumentation(String url) throws IOException {
	return get(url);
    }

}
