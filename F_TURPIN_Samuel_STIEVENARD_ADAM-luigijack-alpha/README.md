Luigi-Jackou
===========

Développé par <Samuel TURPIN> <Adam STIEVENARD>
Contacts : <samuel.turpin.etu@univ-lille.fr> , <adam.stievenard.etu@univ-lille.fr>

# Présentation de <le nom de votre jeu>

<Description de votre jeu>
       Bienvenue dans le Luigi Jack !

Dans ce jeux votre but est d'obtenir le meilleur score avec différentes carte tout en ne sachant pas celui de l'adversaire...
Les Différentes Cartes sont : Les PIQUES, CARREAUX, COEUR OU TREFFLE avec leurs nombre donne ce nombre de points, le Joker et le Roi donne chacun 10 points

Vous pouvez voire à tout moment votre nombre de point à l'écran...
Lorsque qu'un score est supérieur à l'autre, le jeu fait une soustraction entre le plus grand score et le plus petit (grand - petit = nombre de gain)
Le résultat de ce calcul est positif pour celui qui gagne et négatif pour celui qui perd ces étoiles.

Cependant si Luigi ou le Joueur obtienne un score supérieur à 21 une pénalité sera infligé en fonction de la différence entre les deux scores...

A chaque tour le joueur se vois poser une question de culture générale.
S'il y répond juste il gagne 3 étoiles, et s'il répond faux il perd 3 étoiles...


# Utilisation de <Luigi-Jackou>

Afin d'utiliser le projet, il suffit de taper les commandes suivantes dans un terminal :

```
./compile.sh
```
Permet la compilation des fichiers présents dans 'src' et création des fichiers '.class' dans 'classes'

```
./run.sh
```
Permet le lancement du jeu

Une fois lancée le jeu vous propose de commencer une nouvelle partie en entrant le nombre 1 ou de quitter le jeu avec 2.

Chaque manche est composé de quatre tours, la manche se lance avec 2 carte attribuer au joueur et à luigi, celle-ci peut se terminer à tout moment si le joueur montre ces cartes avec 'x', cependant avant de montrer ces cartes le joueur peut piocher des cartes avec 'a' est en avoir jusqu'a quatre pour maximiser les chances de gagner.
La partie s'arrête quand Luigi ou le Joueur on un nombre d'étoiles inférieur ou égal à 0.



# informations à savoir 

melangeTab(String[] tab){} n'est pas encore utilisé

nous prévoyons:
       -une optimisation du code
       -correction de certains bug qui empèchent l'implémentation quelques fonctionnalitées
       -ajout d'autre mini-jeux
       -ajout d'une interface