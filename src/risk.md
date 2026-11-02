# Revue technique de Jeu2

## Périmètre

Revue statique des classes présentes dans src. Aucun fichier source n’a été modifié. Le jeu n’a pas été exécuté.

## Synthèse

Le projet dispose d’une séparation initiale entre entités, systèmes, rendu et carte, adaptée à un prototype. Les principaux risques concernent le chargement et la validation des cartes, la cohérence de l’état des entités, la synchronisation de l’entrée clavier et le coût du rendu quand le monde grandira.

## Bugs potentiels et incohérences

### 1. Les entités désactivées restent animées et visibles — priorité élevée

Entity.deactivate() modifie le champ active, mais le système d’animation et le renderer ne vérifient pas cet état. Une entité désactivée peut donc rester visible et animée.

**Proposition :** filtrer les entités inactives dans les systèmes concernés ou les retirer du gestionnaire au moment approprié.

Références : src/entity/Entity.java:45, src/animation/AnimationSystem.java:16, src/animation/EntityAnimationRenderer.java:28.

### 2. Un identifiant de tuile invalide peut faire planter le jeu — priorité élevée

Le chargeur accepte les valeurs de la carte sans vérifier qu’elles correspondent à une tuile connue. Par exemple, l’identifiant 2 fait renvoyer null par TileSet.getTile(2). Le rendu ou la collision accède ensuite à .image ou .collision, ce qui peut provoquer une NullPointerException.

**Proposition :** valider chaque identifiant au chargement et signaler le numéro de ligne et de colonne de la valeur incorrecte.

Références : src/map/MapLoader.java:18, src/map/TileSet.java:43, src/map/MapRenderer.java:37, src/movement/EntityCollisionCalculator.java:86.

### 3. Le format du fichier de carte est peu tolérant — priorité élevée

MapLoader utilise split(" "), qui ne gère pas correctement les espaces répétés ou les tabulations. Il ne diagnostique pas explicitement une ligne manquante, un nombre de colonnes incorrect ou un fichier de dimensions différentes de celles des constantes. Le flux de lecture n’est pas non plus fermé explicitement.

**Proposition :** accepter les espaces blancs courants, valider les dimensions et les valeurs, fermer le flux avec try-with-resources et produire des messages d’erreur qui identifient la ligne concernée.

Référence : src/map/MapLoader.java:14.

### 4. L’état clavier est partagé entre threads sans synchronisation — priorité moyenne

Les événements clavier modifient un tableau de booléens tandis que la boucle du jeu le lit sur un autre thread. Ces accès ne sont ni synchronisés ni publiés par un mécanisme garantissant leur visibilité. getInputs() expose aussi directement le tableau interne.

**Conséquence possible :** une entrée peut être lue en retard ou rester bloquée.

**Proposition :** encapsuler l’état et utiliser des méthodes synchronisées, un instantané publié de façon sûre ou une structure atomique adaptée.

Références : src/core/input/InputState.java:10, src/core/input/InputListener.java:18.

### 5. Enemy et Npc ne sont pas des entités — priorité moyenne

Enemy et Npc sont des classes vides qui ne dérivent pas de Entity, alors que EntityManager.addEntity() n’accepte que des objets Entity. Les types ENEMY et NPC existent déjà, mais ces classes ne peuvent pas encore être gérées comme le joueur.

**Proposition :** aligner la hiérarchie des classes sur le modèle d’entités avant d’ajouter les systèmes de jeu correspondants.

Références : src/entity/Enemy.java:3, src/entity/Npc.java:3, src/entity/EntityManager.java:19.

### 6. Les animations déclarées ne sont pas toutes enregistrées — priorité moyenne

Les animations ATTACK_* sont déclarées dans AnimationType, mais AnimationManager n’enregistre que les animations du joueur au repos et en marche. AnimationSet.setAnimation() accepte malgré tout un type absent. getFrame() renvoie alors null, et le renderer ignore l’entité.

**Proposition :** refuser les types absents au moment du changement d’animation, ou vérifier au démarrage que tous les types annoncés disposent bien de leurs ressources.

Références : src/animation/AnimationType.java:22, src/animation/AnimationManager.java:92, src/animation/AnimationSet.java:28.

### 7. TileMap expose et accepte des grilles non vérifiées — priorité moyenne

getMap() renvoie le tableau interne directement. setMap() accepte aussi des tableaux vides ou irréguliers. Le contenu ou les dimensions peuvent ainsi devenir incohérents, puis provoquer des erreurs d’index dans le rendu ou les collisions.

**Proposition :** valider les dimensions à l’entrée et éviter d’exposer le tableau brut ; fournir des accesseurs contrôlés pour lire ou modifier les cases.

Référence : src/map/TileMap.java:16, src/map/TileMap.java:23.

### 8. TileSet est présenté comme un singleton, mais ne l’est pas — priorité faible

Le commentaire de TileSet annonce un singleton, mais chaque construction recharge les images. Game.initialize() crée un TileSet inutilisé ; le constructeur de TileMap en crée un autre et recharge les ressources.

**Proposition :** supprimer l’instance inutilisée et partager un jeu de ressources chargé une seule fois.

Références : src/core/Game.java:59, src/map/TileMap.java:13, src/map/TileSet.java:6.

### 9. Une seconde initialisation du jeu ajoute des systèmes et renderers en double

Game.initialize() ajoute des systèmes et des renderers aux gestionnaires existants sans les vider. Si la méthode est appelée une seconde fois, le rendu et les mises à jour sont dupliqués.

**Proposition :** rendre l’initialisation idempotente ou reconstruire explicitement les gestionnaires lors d’une réinitialisation.

Référence : src/core/Game.java:45.

## Risques de montée en charge

| Zone | Exemple concret | Proposition |
|---|---|---|
| **Rendu de la carte** | MapRenderer parcourt toutes les cases à chaque image. Son test de visibilité compare les coordonnées écran aux dimensions du monde, au lieu de la fenêtre. Sur une carte de 1 000 × 1 000 cases, il peut donc examiner un million de cases pour n’en afficher qu’une petite partie. | Calculer les bornes des tuiles visibles depuis la caméra et ne parcourir que cette zone. Référence : src/map/MapRenderer.java:31. |
| **Parcours des entités** | Le rendu et l’animation parcourent chacun toutes les entités. Avec 10 000 entités à 60 images/s, ces deux opérations représentent environ 1,2 million de visites par seconde, avant les autres systèmes. | Limiter le rendu aux entités visibles, traiter uniquement les entités actives et envisager un découpage spatial pour les recherches et collisions. Références : src/animation/EntityAnimationRenderer.java:28, src/animation/AnimationSystem.java:16. |
| **Boucle de jeu** | Le déplacement et l’animation progressent par image. Si le jeu tourne à 30 images/s au lieu de 60, le joueur se déplace environ deux fois moins vite ; un framerate supérieur peut au contraire l’accélérer. | Découpler la mise à jour du rendu au moyen d’un pas fixe, ou exprimer les vitesses par seconde et tenir compte du temps écoulé. Références : src/core/GameLoop.java:29, src/movement/PlayerMovementSystem.java:46. |
| **Ajout de personnages et d’animations** | Les ressources du joueur sont déclarées manuellement dans AnimationManager, avec une correspondance exacte par classe. Ajouter plusieurs personnages et variantes rendra ce registre long et sujet aux oublis. | Charger les définitions d’animation depuis une configuration, valider les animations requises et mettre les images en cache. Référence : src/animation/AnimationManager.java:26. |
| **Ordre d’affichage** | EntityManager stocke les entités dans une HashMap, dont l’ordre d’itération n’est pas garanti. Deux personnages qui se chevauchent peuvent être dessinés dans un ordre inattendu. | Définir un ordre de rendu stable, par exemple selon la profondeur ou la coordonnée verticale. Références : src/entity/EntityManager.java:8, src/animation/EntityAnimationRenderer.java:28. |

## Conclusion

Les corrections les plus urgentes concernent la validation des cartes, le filtrage des entités désactivées et la synchronisation des entrées. Pour préparer l’agrandissement du monde, le premier chantier de performance devrait être le rendu des seules tuiles visibles ; ensuite, la boucle de jeu et le parcours des entités gagneraient à être découpés indépendamment du nombre total d’objets.
