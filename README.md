# Project 4.0
Men kan de hyperledger composer installeren op ubuntu of MacOS. Deze handleiding gaat over het installatieprocess op ubuntu.



## Opstellen van aws instance

Eerst hebben we een Ubuntu Linux 14.04 / 16.04 LTS (beide 64-bit)
server nodig.
Wij huren er een op de cloud van amazon, het is ook mogelijk er zelf een te installeren.

Log in met je aws account en ga naar de EC2 pagina.


![aws-pagina](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/aws-pagina.png "aws-pagina")

Klik vervolgens op de Launch Instance knop om de instance creatie wizard te starten.

![aws-pagina](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/launch-instance.png "aws-pagina")

Kies voor een Amazon Linux 2 AMI. 

![aws-pagina](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/ubuntu-selectie.png "aws-pagina")

Er is een minimum van **4gb** vrij geheugen nodig om de Hyperledger Composer en hyperledger Fabric te draaien. Hierdoor hebben we een t2.medium instance type nodig.

![aws-pagina](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/t2.medium-selectie.png "aws-pagina")

Hierna gaan we naar stap 4 "Add storage".

We geven de instance 20 Gb aan storage i.p.v. 8 Gb.

![aws-pagina](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/storage-keuze.png "aws-pagina")

Ten laatste gaan we naar stap 6 om de security group aan te passen.

Voeg volgende regels toe:

* poort 3000: Hyperledger fabric api poort
* Poort 8080: Hyperledger composer poort

![security-group](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/security-group.png "security-group")

Indien men nu op "launch" klikt, start er een instance op met de juiste instellingen om de installatie van de hyperledger composer te starten.

## Installatie hyperledger fabric

Voor het installatie process van de hyperledger fabric verwijzen we u door naar de officiele handleiding.

[https://hyperledger.github.io/composer/latest/installing/development-tools.html](https://hyperledger.github.io/composer/latest/installing/development-tools.html "hyperledger handleiding")

Voer volgende commando's uit na een geslaagde installatie:

```bash
cd ~/fabric-dev-servers
export FABRIC_VERSION=hlfv12
./startFabric.sh
sleep 15
./createPeerAdminCard.sh
composer-playground &
```
Browse naar het ip van je instance op poort 8080. Je komt uit op het volgende scherm:

![hyper-scherm](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/hyper-scherm.png "hyper-scherm]")

Klik op "Deploy a new business network" en upload het .bna file.
Hierna moet je de default credentials opgeven en "admin@ordina-coldchain-hyperledger" ingeven als network admin card. Klik op deploy.

### Hyperledger Composer REST server
Als volgende stap moet je de "Hyperledger Composer REST server" opzetten. Dit gaat d.m.v. het volgende commando.

```bash
sudo composer-rest-server --card admin@ordina-coldchain-hyperledger --namespaces always --port 3000 &
```

### Handige scripts

Opstart van de servers:

```bash
cd ~/fabric-dev-servers
export FABRIC_VERSION=hlfv12
./startFabric.sh
sleep 15
./createPeerAdminCard.sh
composer-playground &
```
Afsluiten van de servers:
```bash
cd ~/fabric-dev-servers
./stopFabric.sh
./teardownFabric.sh
```

## Opstellen van database

We hebben een relationele database nodig om alle gebruikers data zoals gebruikersnaam en paswoord in op te slaan.

Ga naar de amazon RDS pagina.
![rds-pagina-keuze](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/rds-pagina-keuze.png "rds-pagina-keuze")

We hebben een nieuwe databank nodig, dus klikken we op de "create database" knop.

![create-datababe-keuze](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/create-datababe-keuze.png "create-datababe-keuze")

We kunnen kiezen tussen een aantal databank engines. Kies voor MySql en ga naar de volgende pagina.

![selectie-mysql](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/selectie-mysql.png "selectie-mysql")

Kies voor de tweede optie "Production -MySql"

![rds-prod-keuze](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/rds-prod-keuze.png "rds-prod-keuze")

Neem onderstaande opties over. Geef de databank een gepaste naam en credentials. Klik next.

![rds-instellingen](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/rds-instellingen.png "rds-instellingen")

Ten laatste zet je de databank publiek open. Klik deploy.
De databank wordt opgezet en is over een 10 tal minuten klaar.

![rds-klaar](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/rds-klaar.png "rds-klaar")


## Online zetten van front-end

De front-end bestaat uit puur angular 7. 
We kunnen dus een S3 bucket gebruiken.

Ga naar de S3 pagina.

[https://s3.console.aws.amazon.com](https://s3.console.aws.amazon.com "S3")


![s3-selectie](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/s3-selectie.png "s3-selectie")

Klik op de "create bucket" knop.

![selectie-create-bucket](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/selectie-create-bucket.png "selectie-create-bucket")

Je krijgt een popup scherm met de s3 wizard te zien.

Geef je bucket een duidelijke en korte naam en kies je gewenste region. Klik next tot de wizard is afgesloten.

![s3-wizard-1](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/s3-wizard-1.png "s3-wizard-1")

Indien alles correct is verlopen, kan je nu je s3 bucket zien in de lijst. Klik op de bucket.

![s3-bucket-zichtbaar](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/s3-bucket-zichtbaar.png "s3-bucket-zichtbaar")

Je krijg een overview van je buckcet te zien. Klik op de upload knop en upload je angular project. Klik next en behouw alle standaard opties tot de wizard is afgesloten.

![s3-upload](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/s3-upload.png "s3-upload")

Vervolens wordt je project geupload op je s3 bucket.

![s3-uploaded](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/s3-uploaded.png "s3-uploaded")

Als laatste stap moeten we naar de "Properties" tab gaan. Klik op de "Static website hosting" block en selecteer de eerste optie "Use this bucket to host a website". Klik op save.

![s3-static-hosting](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/s3-static-hosting.png "s3-static-hosting")

Indien alles succesvol is verlopen zou je nu je project kunnen zien via de opgegeven link.

![s3-website-af](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/s3-website-af.png "s3-website-af")

# Java springboot installatie


Ga naar de elastic beanstalk pagina.

[https://eu-west-3.console.aws.amazon.com/elasticbeanstalk/](https://eu-west-3.console.aws.amazon.com/elasticbeanstalk/ "Elastic beanstalk")

![beanstalk-select](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/beanstalk-select.png "beanstalk-select")

Klik op de "Create New Application" knop. 

![beanstalk-select-new-app](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/beanstalk-select-new-app.png "beanstalk-select-new-app")

Geef vervolgens een gepaste naam op en klik op create.
Momenteel heeft onze applicatie nog geen environment deze maken we nu aan. Maak een environment aan via de "create environment" knop.

![beanstalk-select-create-env](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/beanstalk-select-create-env.png "beanstalk-select-create-env")

Laat de standaard optie geselecteerd staan en klik select.

![beanstalk-select-tier](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/beanstalk-select-tier.png "beanstalk-select-tier")

Je krijgt de instellingen pagina van je environment te zien. Geef hier je gewenste opties in, maar zet het platform zeker op Java.
Selecteer hierna de "upload your code" optie en klik op de upload knop. Geef vervolgens je .jar file op. 

![beanstalk-env-opties](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/beanstalk-env-opties.png "beanstalk-env-opties")

Klik op deploy.

![beanstalk-deploy](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/beanstalk-deploy.png "beanstalk-deploy")

Een paar minuten later zal je applicatie online staan.

![beanstalk-af](https://github.com/RDV1996/Coldchain-project/blob/Infrastructure/afbeeldingen/beanstalk-af.png "beanstalk-af")

