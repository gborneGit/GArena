

Ce plugin Minecraft permet aux joueurs du serveur EcoCraft de combattre dans des arènes multijoueurs. Les joueurs peuvent configurer plusieurs arènes en définissant leurs noms, leurs emplacements, le nombre de joueurs et les emplacements de spawn pour chaque joueur. Le plugin prend également en charge la perte de l'équipement lors de la mort du joueur et la mise à zéro de son inventaire.

Le fichier de configuration permet de personnaliser les arènes en spécifiant le monde où elles se trouvent, le nombre de joueurs maximum, ainsi que la perte d'équipement. Le plugin prend également en charge un système de score qui permet de suivre les victoires et les défaites des joueurs dans chaque arène.

En somme, ce plugin permet aux joueurs d'EcoCraft de s'engager dans des combats multijoueurs compétitifs dans des arènes personnalisables.

## COnfig

* `world`: Le nom du monde dans lequel les arènes seront créées.

* `arenas`: La liste des arènes disponibles pour les joueurs. Les identifiants uniques de chaque arène doivent être numériques et commencer à partir de zéro.

* `name`: Le nom de l'arène qui sera affiché aux joueurs.

* `loc1`, `loc2`: Les coordonnées des coins de l'arène. Elles sont définies sous la forme de chaînes de caractères séparées par des virgules, où la première valeur représente la coordonnée x, la deuxième représente la coordonnée y et la troisième représente la coordonnée z.

* `players`: Le nombre de joueurs qui peuvent participer à l'arène en même temps.

* `loss_stuff`: Un booléen qui indique si les joueurs perdront leur équipement lorsqu'ils mourront dans l'arène.

* `spawns`: Les coordonnées des points de réapparition des joueurs dans l'arène. Elles sont également définies sous la forme de chaînes de caractères séparées par des virgules, où les trois premières valeurs représentent les coordonnées x, y et z, et les deux dernières représentent l'angle de vue horizontal et vertical du joueur.
