[![YourActionName Actions Status](https://github.com/PixelDream/PTS3/workflows/Build/badge.svg)](https://github.com/PixelDream/PTS3/actions)

<br/>
<br/>

# PTS3 - Mon Potager

Étudiants de deuxième année en informatique à l’IUT de Laval, nous devons mettre en œuvre les méthodes de conduite d’un projet et ce dans le cadre de notre projet tutoré. Le projet se déroule sur l’ensemble du semestre 3, il consiste à concevoir et réaliser une application mobile.


## Etudiants

* [Morgan Nehdi](https://morgan-nehdi.com/)
* Baptiste Buvron
* Antoine Lancelot
* Lenny Louis
* Ludovic Cheron


## Travailler sur le projet

Afin de travailler dans les meillieurs conditions, nous appliquerons les mêmes conventions.

## Travailler sur le code

Créer une nouvelle branche
```
git branch <nom de la branche>
```

Aller sur la nouvelle branche
```
git checkout <nom de la branche>
```

Une fois sur la branche, il est possible d'effectuer des ajouts et modifications.
Durant le processus de développement, vous pouvez faire des commits.

```
git add *
git commit -m "message du commit Ex. Feat: Add SideMenu" 
```

Envoie de la branche sur le dépot distant
```
git push origin <nom de la branche>
```

Creer une pull request
* Aller sur https://github.com/PixelDream/PTS3/pulls
* Aller sur new pull request
* Comparer la branche "dev" avec la branche envoyée précédemment
* Remplir les champs nécessaires
* Mettre deux correcteurs
* Créer la pull request

Une fois les modifications envoyées sur la branche "dev", il est possible de supprimer la branche local

```
git branch -d <nom de la branche>
```


### Les packages 📌

Les packages doivent être nommés en minuscule, et doivent correspondre au modèle de conception MVC (Model-View-Controller).

Ex. ```fr.iut.monpotager.model```

### Les fichiers 📌

Les fichiers doivent être nommés en camel case.

Ex. ```MainActivity.java```


### Les commentaires 📌

Les commentaires doivent être en anglais

### Les commits 📌

Les commits doivent être en anglais

Format :
    ```<type>: <sujet>```

Les types :
- build : changements qui affectent le système de build ou des dépendances externes (npm, make…)
- ci : changements concernant les fichiers et scripts d’intégration ou de configuration (Travis, Ansible, BrowserStack…)
- feat : ajout d’une nouvelle fonctionnalité
- fix : correction d’un bug
- perf : amélioration des performances
- refactor : modification qui n’apporte ni nouvelle fonctionalité ni d’amélioration de performances
- style : changement qui n’apporte aucune alteration fonctionnelle ou sémantique (indentation, mise en forme, ajout d’espace, renommante d’une variable…)
- docs : rédaction ou mise à jour de documentation
- test : ajout ou modification de tests


### La Javadoc
La javadoc est automatiquement modifiée lors d'un push sur la branche "master" et envoyée sur github pages par la suite : https://pixeldream.github.io/PTS3


<br/>
<br/>
<br/>


![logo](https://upload.wikimedia.org/wikipedia/commons/f/f8/LOGO-ORIGINAL_WEB.jpg "Logo IUT Laval")