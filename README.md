# TestLangage
Un langage et son IDE afin de créer des fictions intéractives.
Il utilise JavaFX pour l'interface graphique.

# Dépendances
Il est nécessaire de posséder le [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html#javasejdk). Il est conseillé de posséder la version 1.8u40 ou supérieur.

# Utilisation
Sur l'arborescence des fichiers (à droite) est affiché le chemin vers l'emplacement des projets (le dossier se nomme Projets). C'est dans ce dossier que doit se trouver l'ensemble de vos fichiers.
Le fichier test à utiliser est nommé "testSyntaxe" (dans le dossier Projets du git). Il présente la syntaxe à utiliser.

# RichTextFX
L'ide utilise l'objet [RichTextFX](https://github.com/TomasMikula/RichTextFX).
Cet objet permet l'utilisation de la coloration syntaxique (contrairement à l'objet TextArea).

Si la compilation du code est impossible à cause de certains import ou de l'objet CodeArea c'est qu'il vous manque la librairie RichTextFX. Elle se trouve dans le dossier ./lib du projet, mais votre IDE ne la détecte pas forcément. Voici comment procéder :
 * Netbeans : Dans l'arborescence du projet, faîtes un clic droit sur le dossier Librairies > Add JAR/Folder > lib/richtextfx-fat-0.6.3.jar
 * Eclipse : todo
 * IntelliJ : todo

