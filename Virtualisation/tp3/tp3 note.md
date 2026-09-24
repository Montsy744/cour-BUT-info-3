# **TP n°3 – Cloud-init**

# **1\. Préparation de la machine virtuelle**

Pour manipuler `cloud-init`, nous allons utiliser l’hyperviseur `qemu-kvm` et une image `debian` pré-configurée pour utiliser `cloud-init`.

**Pour éviter de surcharger votre quota, il vous est conseillé de stocker les différentes images manipulées dans ce TP dans `/usr/local/virtual_machine/infoetu/$USER/cloud-init/`**

### **1.1. Récupération de l’image debian**

Le projet Debian propose, comme nombre d’autres distributions, une image dédiée à l’utilisation en environnement cloud dans laquelle `cloud-init` est installé et pré-configuré.

Ces images peuvent être obtenues sur [https://cloud.debian.org](https://cloud.debian.org) et sont comparées sur une page du [wiki de Debian](https://wiki.debian.org/Cloud/SystemsComparison). Nous utiliserons les images du dossier `latest`.

1. Télécharger la dernière image debian `trixie` en version `genericcloud` pour l’architecture `amd64`. Cette version n’est pas spécialisée pour un fournisseur particulier comme openstack par exemple. Cette image pèse un peu plus de 300 MB, attention à votre quota.

- Création du dossier de travail

```bash
mkdir -p /usr/local/virtual_machine/infoetu/$USER/cloud-init/
cd /usr/local/virtual_machine/infoetu/$USER/cloud-init/
```

- Téléchargement de l'image Debian Trixie

```bash
wget https://cloud.debian.org/images/cloud/trixie/latest/debian-13-genericcloud-amd64.qcow2

```

- Vérification du fichier

```bash
ls -lh
```

2. Vérifier l’intrégité de l’image téléchargée grâce aux sommes de contrôle fourni par Debian dans le fichier `SHA512SUMS` (une lecture de `sha512sum(1)` vous sera utile)

Automatisation complète

- Téléchargez le fichier de sommes de contrôle

```bash
wget https://cloud.debian.org/images/cloud/trixie/latest/SHA512SUMS
``` 

- Lancez la vérification automatique

```bash
sha512sum --ignore-missing -c SHA512SUMS
```

`-c SHA512SUMS` : Demande à l'outil de valider les fichiers listés à l'intérieur du document.
`--ignore-missing` : Indispensable ici, car le fichier contient les empreintes de toutes les images du dossier cloud (OpenStack, Azure, EC2...). Cette option évite d'afficher des alertes d'erreur pour les fichiers que vous n'avez pas téléchargés.

- Résultat attendu :

```bash
debian-13-genericcloud-amd64.qcow2: Réussi
```

3. Déplacer le fichier téléchargé (conserver le fichier d’origine) dans `/usr/local/virtual_machine/infoetu/$USER/cloud-init/`

### **1.2. Lancement de la VM dans `qemu-kvm`**

Copier l’image téléchargée sous le nom `disk.qcow2`.

```bash
cp debian-13-generic-amd64.qcow2 disk.qcow2
```

Pour exécuter la VM, nous allons utiliser la commande suivante :

```bash
kvm -m 1024 -device virtio-net,netdev=net0 -netdev user,id=net0,hostfwd=tcp::2222-:22 -hda disk.qcow2
```

Cette commande a pour effet de lancer `qemu-kvm` en attachant une carte réseau et un disque (l’image debian cloud) à la VM. Une redirection du port 2222 de la machine physique vers le port 22 de la VM est également mise en place, ce qui permettra d’accéder à la VM par SSH.

Une fenêtre doit s’afficher présentant l’écran de la machine virtuelle dans lequel la distribution Debian démarre.

Pour l’instant, l’image n’est pas configurée, vous ne pouvez même pas vous y connecter. Nous allons y remédier à l’aide de `cloud-init`. Vous pouvez arrêter la machine virtuelle (`Ctrl-C` dans le terminal dans lequel vous avez lancé la commande `kvm`)

## **2\. cloud-init**

### **2.1. Préparation de l’image cloud-init**

`cloud-init` est un logiciel permettant de spécialiser une machine virtuelle lors de son premier démarrage. En pratique, `cloud-init` est utilisé par la majorité des opérateurs cloud pour automatiser la création d’instance d’images virtuelles pour les utilisateurs finaux.

`cloud-init` est simplement un service qui est exécuté au démarrage de la machine et réalise une sélection de tâches de configuration (réseau, utilisateurs, clé SSH, etc.). Les tâches exécutées sont définies par la configuration de cloud-init. Cette configuration est constituée :

1. d’une configuration de base, définie dans les différents fichiers et répertoire de `/etc/cloud`. Cette configuration est généralement fixée par le mainteneur de la distribution (l’équipe cloud du projet Debian dans notre cas)
2. d’une configuration _vendor data_ fournie par l’opérateur cloud
3. d’une configuration _user data_ fournie par l’utilisateur final
4. d’une configuration réseau

Si la configuration de base est déjà dans l’image, pour la configuration _vendor data_, _user data_ et réseau, nous devons la fournir à l’instance. Il existe plusieurs méthodes pour fournir cette configuration qui varient selon l’opérateur cloud.

Dans notre cas, nous allons générer une image disque qui sera connectée à la machine virtuelle. `cloud-init` détectera le disque et ira y prendre sa configuration au démarrage. Pour cela, nous allons utiliser la commande `cloud-localds` qui prend en entrée les différentes configurations et produit une image disque.

#### **2.1.1. Premier fichier _user data_ : création d’un mot de passe pour l’utilisateur**

Pour définir le fichier _user data_, nous allons utiliser le format `cloud-config`. Il s’agit d’un fichier au format [`YAML`](https://yaml.org/). Il est possible d’utiliser d’autres formats (comme un simple script par exemple), mais nous n’allons pas les utiliser ici.

Un fichier `cloud-config` est donc un fichier YAML de la forme:

\#cloud-config  
cle1: valeur1  
cle2: valeur2  
dictionnaire:  
 cle1: valeur1  
 tableau:  
 \- valeur  
 \- dictionnaire:  
 cle1: valeur1

Ce que nous allons indiquer dans ce fichier dépend des différents modules de cloud-init. La référence des modules est disponible dans la [documentation](https://cloudinit.readthedocs.io/en/latest/reference/modules.html#) officielle.

Pour changer le mot de passe de l’utilisateur par défaut, nous allons utiliser le module _Set Passwords_ qui utilise les clés de configuration globales : `password`, `ssh_pwauth` et `chpasswd`.

Nous pouvons donc écrire notre fichier `user-data`. La valeur de la clé `password` peut être donnée en clair ou (c’est mieux) en version hachée. On peut générer la version hachée d’un mot de passe à l’aide de la commande `mkpasswd -m sha-512`.

> **Attention** ne pas oublier de mettre `#cloud-config` en première ligne

\#cloud-config  
password: $6$sJKeKIAnF84a15W.$0k2J2wAw.TwJd1dViDyI1KxsR.hZXtqDLWqdsbCCACPKqGUjqRImjZsV2wsxnt1Sol.AoW9bGnjDow7k6AJm90  
chpasswd:  
 expire: False

1. Utiliser la commande `cloud-localds` pour fabriquer un fichier `seed.img` à partir du fichier `user-data.yml`

```bash
cloud-localds seed.img user-data.yml
```

2. Copier à nouveau l’image debian que vous avez téléchargée sur `disk.qcow2` (écraser l’ancienne version)

```bash
cp debian-13-genericcloud-amd64.qcow2 disk.qcow2
```

3. Lancer la machine virtuelle à l’aide de la commande précédente, **en y ajoutant** l’option `-hdb seed.img`. Cette option va utiliser l’image disque fabriquée précédemment pour attacher un deuxième disque virtuel à la machine. C’est dans ce disque que `cloud-init` ira chercher sa configuration
4. Vérifier que la connexion avec mot de passe depuis la console est possible

```bash
kvm -m 1024 -device virtio-net,netdev=net0 -netdev user,id=net0,hostfwd=tcp::2222-:22 -hda disk.qcow2 -hdb seed.img
```

5. Éteindre la machine virtuelle (`sudo poweroff`)

Vous allez refaire ces 3 premières étapes de nombreuses fois pendant le TP. Il serait sans doute utile de mettre en place une manière de les automatiser (via un simple script shell par exemple).

```bash
#!/bin/bash
echo "Génération de la configuration cloud-init..."
cloud-localds seed.img user-data.yml
echo "Réinitialisation du disque principal..."
cp debian-13-genericcloud-amd64.qcow2 disk.qcow2
echo "Lancement de la machine virtuelle..."
qemu-system-x86_64 -m 1024 -hda disk.qcow2 -hdb seed.img -net nic -net user,hostfwd=tcp::2222-:22
```

#### **2.1.2. Connexion SSH par clé**

1. Lire la documentation officielle de cloud-init et modifier le fichier `user-data.yml` de façon à ajouter votre clé publique SSH à l’utilisateur par défaut

```
ssh_authorized_keys:
    [clé ssh pub]

Ouvrir un terminal > cat ./ssh/id_rsa.pub
```

2. Tester la connexion SSH à la VM (via une connexion sur le port 2222 de la machine physique)

```bash 
ssh -p 2222 debian@localhost
```

#### **2.1.3. Configuration diverses**

Modifier (et tester à chaque étape) le fichier `user-data.yml` de façon à ce que :

1. la VM utilise `ntp.univ-lille.fr` comme serveur de temps (NTP)
2. les paquets `vim`, `less`, `net-tools`, `dnsutils` soient installés
3. la machine virtuelle soit mise à jour et redémarrée si nécessaire
4. le nom d’hôte de la VM soit `vm1`
5. le fuseau horaire soit configuré sur `Europe/Paris`

## **3\. Test en condition cloud _réelle_**

Nous allons maintenant utiliser notre configuration dans un environnement cloud réel. Pour cela, nous allons utiliser la plateforme cloud de l’université, basée sur [openstack](https://www.openstack.org/).

1. Se connecter sur [https://cloud.univ-lille.fr](https://cloud.univ-lille.fr) avec les identifiants université habituels
2. Dans le menu _Instances_, choisir l’option _Lancer une instance_ et créer une instance de machine virtuelle basée sur l’image `debian13` (fournie par l’openstack de l’université) en utilisant le gabarit _normale_, et en ajoutant votre fichier `user-data` dans la section _Configuration_. La nouvelle instance apparaîtra dans le menu _Instances_. Noter son adresse IP.
3. Se connecter en SSH à l’instance, en tant qu’utilisateur `debian`. Vous devez constater que vous pouvez vous connecter sans saisir de mot de passe, grâce à la clé SSH que vous avez indiquée dans votre fichier `user-data`. Vous pouvez également vérifier que les paquets souhaités ont bien été installés.
