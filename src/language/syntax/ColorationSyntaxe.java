/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language.syntax;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import modele.precompile.Donnee;
import modele.precompile.ListDonnees;
import modele.precompile.PCode;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.StyleSpans;
import org.fxmisc.richtext.StyleSpansBuilder;

/**
 *
 * @author Yann
 */
public class ColorationSyntaxe {

    private final static String KEYWORD_PATTERN = "\\b(" + String.join("|", KeyWord.initialisateurs) + ")\\b";
    private static String ROOM_PATTERN;
    private static String PERSO_PATTERN;
    private static String CHOSES_PATTERN;
    private static String ACTION_PATTERN;
    private static String VARIABLES_PATTERN;
    private static String FONCTIONS_PATTERN;
    private final static String DIESE_PATTERN = "#.*?\\s";
    private final static String DOLLAR_PATTERN = "\\$.*?\\s";
    private final static String STRING_PATTERN = ":.*?\\n";
    private final static String COMMENT_PATTERN = "//.*?\\n";

    private static Pattern PATTERN;

    private static void initTabs() {
	ROOM_PATTERN = "\\b(" + String.join("|", KeyWord.lieux) + ")\\b";
	PERSO_PATTERN = "\\b(" + String.join("|", KeyWord.perso) + ")\\b";
	CHOSES_PATTERN = "\\b(" + String.join("|", KeyWord.choses) + ")\\b";
	ACTION_PATTERN = "#(" + String.join("|", KeyWord.actions) + ")\\b";
	VARIABLES_PATTERN = "\\$(" + String.join("|", KeyWord.variables) + ")\\b";
	FONCTIONS_PATTERN = "\\b(" + String.join("|", KeyWord.fonctions) + ")\\b";
	PATTERN = Pattern.compile("(?<KEYWORD>" + KEYWORD_PATTERN + ")"
		+ "|(?<ROOM>" + ROOM_PATTERN + ")"
		+ "|(?<PERSO>" + PERSO_PATTERN + ")"
		+ "|(?<CHOSES>" + CHOSES_PATTERN + ")"
		+ "|(?<ACTION>" + ACTION_PATTERN + ")"
		+ "|(?<VARIABLES>" + VARIABLES_PATTERN + ")"
		+ "|(?<FONCTIONS>" + FONCTIONS_PATTERN + ")"
		+ "|(?<DIESE>" + DIESE_PATTERN + ")"
		+ "|(?<DOLLAR>" + DOLLAR_PATTERN + ")"
		+ "|(?<STRING>" + STRING_PATTERN + ")"
		+ "|(?<COMMENT>" + COMMENT_PATTERN + ")"
	);
    }

    public static void color(CodeArea textZone, String newValue) {
	textZone.setStyleSpans(0, computeHighlighting(newValue));
    }

    private static StyleSpans<Collection<String>> computeHighlighting(String text) {
	updateKeyWord(text);
	initTabs();
	Matcher matcher = PATTERN.matcher(text.toLowerCase());
	int lastKwEnd = 0;
	StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
	String styleClass;
	while (matcher.find()) {
	    styleClass = "";
	    if (matcher.group("KEYWORD") != null) {
		styleClass = "keyword";
	    } else if (matcher.group("ROOM") != null) {
		styleClass = "room";
	    } else if (matcher.group("PERSO") != null) {
		styleClass = "perso";
	    } else if (matcher.group("CHOSES") != null) {
		styleClass = "choses";
	    } else if (matcher.group("ACTION") != null) {
		styleClass = "actions";
	    } else if (matcher.group("VARIABLES") != null) {
		styleClass = "variables";
	    } else if (matcher.group("FONCTIONS") != null) {
		styleClass = "fonctions";
	    } else if (matcher.group("DIESE") != null) {
		styleClass = "diese";
	    } else if (matcher.group("DOLLAR") != null) {
		styleClass = "dollar";
	    } else if (matcher.group("STRING") != null) {
		styleClass = "string";
	    } else if (matcher.group("COMMENT") != null) {
		styleClass = "comment";
	    }
	    spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
	    spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
	    lastKwEnd = matcher.end();
	}
	spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
	return spansBuilder.create();
    }

    private static void updateKeyWord(String text) {
	ListDonnees donnees = new PCode(text).getRessources();
	
	//Lieux
	ListDonnees liste = ((ListDonnees) donnees.get("lieux"));

	liste.values().stream().forEach((lieu) -> {
	    KeyWord.lieux = append(KeyWord.lieux, lieu.getNom().toLowerCase());
	});

	//Personnages
	liste = ((ListDonnees) donnees.get("personnages"));

	liste.values().stream().forEach((perso) -> {
	    KeyWord.perso = append(KeyWord.perso, perso.getNom().toLowerCase());
	});

	//Choses
	liste = ((ListDonnees) donnees.get("choses"));

	liste.values().stream().forEach((chose) -> {
	    KeyWord.choses = append(KeyWord.choses, chose.getNom().toLowerCase());
	});

	//Actions
	liste = ((ListDonnees) donnees.get("actions"));

	liste.values().stream().forEach((action) -> {
	    KeyWord.actions = append(KeyWord.actions, action.getNom().toLowerCase());
	});

	//Variables
	liste = ((ListDonnees) donnees.get("variables"));

	liste.values().stream().forEach((Donnee variable) -> {
	    for (Donnee var : ((ListDonnees) variable).values()) {
		KeyWord.variables = append(KeyWord.variables, var.getNom().toLowerCase());
	    }
	});

	//Fonctions
	liste = ((ListDonnees) donnees.get("fonctions"));

	liste.values().stream().forEach((fonction) -> {
	    KeyWord.fonctions = append(KeyWord.fonctions, fonction.getNom().toLowerCase());
	});

    }

    private static <T> T[] append(T[] arr, T element) {
	final int N = arr.length;
	arr = Arrays.copyOf(arr, N + 1);
	arr[N] = element;
	return arr;
    }

}
