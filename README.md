![logo](https://github.com/Ptiga/nac-backend/blob/main/img/NAC.png)

**Partie backend**


**Sommaire**
* I - Présentation du projet
* II - Langage
* III - Lancement du projet
* IV - Crédits



## <u>I - Présentation du projet</u>

Ce projet fait office de POC afin d'évaluer la faisabilité de reproduire un des contrôle d'une macro de vérification post-valorisation et de valider l'orientation technique choisie.


## <u>II - Langage</u>

Ce projet est développé en langage Java.

![logo](https://github.com/Ptiga/nac-backend/blob/main/img/logo-java.png=100x100)
<img src="https://github.com/Ptiga/nac-backend/blob/main/img/logo-java.png" data-canonical-src="https://github.com/Ptiga/nac-backend/blob/main/img/logo-java.png" width="100" height="100" />



### 2) Librairies à installer

Avant de décompresser le projet, vous devez, en premier lieu, installer la lirairie REACT dans le répertoire de votre choix (que nous appellerons [Adresse]). Ouvrez une invite de commande, rendez-vous dans votre répertoire [Adresse] et tapez la commande suivante :
```jsx
"npx create-react-app metal-e-shop"
```

Une fois l'installation, toujours sur l'invite de commande, rendez-vous dans le dossier du projet ("cd metal-e-shop") puis installez les librairies complémentaire suivantes :

* Express :
```jsx
"npm install express"
```

* Express-session :
```jsx
"npm install express-session"
```

* Cors
```jsx
"npm install cors"
```

* cookie-parser
```jsx
"npm install cookie-parser"
```

* uuid
```jsx
"npm install --save uid"
```

* mysql
```jsx
"npm install mysql"
```



Une fois les librairies installées, vous pouvez décompresser le projet téléchargé dans votre répertoire dédié :
```jsx
"[Adresse]/metal-e-shop/"
```

L'arborescence doit être la suivante :
```jsx
metal-e-shop (répertoire principal [main_folder])
    .gitignore
    app-server-backend.js
    DatabaseManager.js
    package.json
    package-lock.json
    README.md
    [public]
        remplacer les fichiers existants
    [src]
        remplacer les fichiers existants
```


## <u>III - Lancement du projet</u>


Pour tester le projet, ouvrez une invite de commande (ou un terminal), allez dans le dossier du projet et tapez : 
```jsx
"node app-server-backend.js"
```

Le serveur backend va démarrer (sur le port 4000).


Ouvrez une autre invite de commande ou un second terminal, retournez dans le dossier du projet et saisissez :
```jsx
"npm start"
```

La partie front end va démarrer (sur le port 3000).

Dans un navigateur, pour se rendre à la page principale, allez à l'adresse suivantte : [http://localhost:3000/](http://localhost:3000/)


Vous êtes désormais sur la page principale du magasin.


## <u>IV - Credits</u>

Projet scolaire effectué en juin 2022 dans le cadre d'un formation reskilling (module Web II) avec l'EFREI.

**Auteur**: Aurélien
**Version**: 0


