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
    private static String ROOM_PATTERN = "\\b(" + String.join("|", KeyWord.rooms ) + ")\\b";
    private static String PERSO_PATTERN = "\\b(" + String.join("|", KeyWord.perso ) + ")\\b";
    private static String CHOSES_PATTERN = "\\b(" + String.join("|", KeyWord.choses ) + ")\\b";
    private static String ACTION_PATTERN = "\\b(" + String.join("|", KeyWord.actions ) + ")\\b";
    private static String VARIABLES_PATTERN = "\\b(" + String.join("|", KeyWord.variables ) + ")\\b";
    private static String FONCTIONS_PATTERN = "\\b(" + String.join("|", KeyWord.fonctions ) + ")\\b";
    
    private static Pattern PATTERN = Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
            + "|(?<ROOM>" + ROOM_PATTERN + ")"
            + "|(?<PERSO>" + PERSO_PATTERN + ")"
            + "|(?<CHOSES>" + CHOSES_PATTERN + ")"
            + "|(?<ACTION>" + ACTION_PATTERN + ")"
            + "|(?<VARIABLES>" + VARIABLES_PATTERN + ")"
            + "|(?<FONCTIONS>" + FONCTIONS_PATTERN + ")"
    );
    
    public static void color(CodeArea textZone, String newValue) {
        textZone.setStyleSpans(0, computeHighlighting(newValue));
    }
    private static StyleSpans<Collection<String>> computeHighlighting(String text) {
        updateKeyWord(text);
        initTabs();
        Matcher matcher = PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while(matcher.find()) {
            String styleClass =
                    matcher.group("KEYWORD") != null ? "keyword" :
                    matcher.group("ROOM") != null ? "room" :
                    matcher.group("PERSO") != null ? "perso" :
                    matcher.group("CHOSES") != null ? "choses" :
                    matcher.group("ACTION") != null ? "actions" :
                    matcher.group("VARIABLES") != null ? "variables" :
                    matcher.group("FONCTIONS") != null ? "fonctions" :
                    null; /* never happens */ assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }

    private static void updateKeyWord(String text) {
        //ROOM
        ListDonnees donnees = new PCode(text).getRessources();
        ListDonnees liste = ((ListDonnees)donnees.get("lieux"));
        
        liste.values().stream().forEach((lieu) -> {
            KeyWord.rooms = append(KeyWord.rooms,lieu.getNom());
        });
        
        //personnages
        
        liste = ((ListDonnees)donnees.get("personnages"));
        
        liste.values().stream().forEach((lieu) -> {
            KeyWord.perso = append(KeyWord.perso,lieu.getNom());
        });
        
        //choses
        
        liste = ((ListDonnees)donnees.get("choses"));
        
        liste.values().stream().forEach((lieu) -> {
            KeyWord.choses = append(KeyWord.choses,lieu.getNom());
        });
        
        //actions
        
        liste = ((ListDonnees)donnees.get("actions"));
        
        liste.values().stream().forEach((lieu) -> {
            KeyWord.actions = append(KeyWord.actions,lieu.getNom());
        });
        
        
        //variables
        
        liste = ((ListDonnees)donnees.get("variables"));
        
        liste.values().stream().forEach((lieu) -> {
            KeyWord.variables = append(KeyWord.variables,lieu.getNom());
        });
        
        //variables
        
        liste = ((ListDonnees)donnees.get("fonctions"));
        
        liste.values().stream().forEach((lieu) -> {
            KeyWord.fonctions = append(KeyWord.fonctions
                    ,lieu.getNom());
        });
        
    }
    private static <T> T[] append(T[] arr, T element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }

    private static void initTabs() {
        ROOM_PATTERN = "\\b(" + String.join("|", KeyWord.rooms ) + ")\\b";
        PERSO_PATTERN = "\\b(" + String.join("|", KeyWord.perso ) + ")\\b";
        CHOSES_PATTERN = "\\b(" + String.join("|", KeyWord.choses ) + ")\\b";
        ACTION_PATTERN = "\\b(" + String.join("|", KeyWord.actions ) + ")\\b";
        VARIABLES_PATTERN = "\\b(" + String.join("|", KeyWord.variables ) + ")\\b";
        FONCTIONS_PATTERN = "\\b(" + String.join("|", KeyWord.fonctions ) + ")\\b";
        PATTERN = Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
            + "|(?<ROOM>" + ROOM_PATTERN + ")"
            + "|(?<PERSO>" + PERSO_PATTERN + ")"
            + "|(?<CHOSES>" + CHOSES_PATTERN + ")"
            + "|(?<ACTION>" + ACTION_PATTERN + ")"
            + "|(?<VARIABLES>" + VARIABLES_PATTERN + ")"
            + "|(?<FONCTIONS>" + FONCTIONS_PATTERN + ")"
    );
    }
    
}
