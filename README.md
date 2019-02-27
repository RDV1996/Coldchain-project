# Project 4.0

Het gebruik van de API voor de coldchain applicatie.

Alle bodies moeten **x-www-form-urlencoded** te zijn. Geen raw JSON.

Als er iets tussen accolades staat (bv. {voorbeeld}) is dit een variable die zelf moet invullen. dit is ook altijd de naam van de variabele in de code.




## Accounts

Het model Account bestaan uit volgende attributen:

* String id
* int version
* String email
* String password
* String bedrijfsnaam
* String straat
* int huisnummer
* String stad
* int postcode
* String btwNummer
* AccountType accountType
* Bedrijfsvorm bedrijfsvorm




### Gebruik van de API i.v.b. met Accounts

Volgende titels volgen de basis url.

Bv. bij onderstaade titel is het als de basis url "https://www.voorbeeld.com" is, is de volledige url:  "https://www.voorbeeld.com/accounts/add"


#### /accounts/add

Dit is een POST methode om een nieuw account aan te maken.

Wat je _moet_ mee te geven in de body is:

    "email":          String email
    "password":       String password
    "bedrijfsnaam":   String bedrijfsnaam
    "straat":         String straat
    "huisnummer":     int huisnummer
    "stad":           String stad
    "postcode":       int postcode
    "btwNummer":      String btwNummer
    "accountTypeId":  String accountTypeId
    "bedrijfsvormId": String bedrijfsvormId

Je krijgt het nieuwe account volledig terug.


#### /accounts/patch

Dit is een PATCH methode om een account aan te passen.

Wat je _moet_ meegeven in de body is:

    "id":             String id
    "version":        String version
    "email":          String email
    "confirmPassword":String password
    "bedrijfsnaam":   String bedrijfsnaam
    "straat":         String straat
    "huisnummer":     int huisnummer
    "stad":           String stad
    "postcode":       int postcode
    "btwNummer":      String btwNummer
    "accountTypeId":  String accountTypeId
    "bedrijfsvormId": String bedrijfsvormId

Wat optioneel meegegeven word is:

    "password": String password

het "confirmPassword" is her oude wachtwoord, het gewoon, optioneel "password" is een nieuw wachtwoord.


#### /

Een GET methode om alle accounts te krijgen

#### /accounts/id/{id}

Een GET methode om een account op te vragen met het id. 

Als het een correct ID is krijg je een account terug, zonder password.

#### /accounts/login

Dit is een POST methode waarmee je inlogd. 

Wat je _moet_ meegeven in de body is:

    "email":    String email
    "password": String password
Als het een correcte combinatie is,krijg je een account terug zonder password.


## AccountType

Het model AccountType bestaan uit volgende attributen:

* String id
* int version
* String naam

### Gebruik van de API i.v.b. met AccountType

Volgende titels volgen de basis url.

Bv. bij onderstaade titel is het als de basis url "https://www.voorbeeld.com" is, is de volledige url:  "https://www.voorbeeld.com/accountTypes/add"


#### /accountTypes/add

Dit is een POST methode om een nieuw accountType aan te maken.

Wat je _moet_ mee te geven in de body is:

    "naam":          String naam

Je krijgt het nieuwe accountType volledig terug.

#### /accountTypes/patch

Dit is een PATCH methode om een accountType aan te passen.

Wat je _moet_ meegeven in de body is:

    "id":             String id
    "version":        String version
    "naam":           String naam

Je krijgt het aangepaste accountType volledig terug.


#### /

Een GET methode om alle accountTypes te krijgen

#### /accountTypes/id/{id}

Een GET methode om een accountType op te vragen met het id. 

Als het een correct ID is krijg je een accountType terug.

#### /accountTypes/delete

Dit is een DELETE methode waarmee je een accountType verweiderd

Wat je _moet_ meegeven in de body is:

    "id":             String id

## Bedrijfsvorm

Het model Bedrijfsvorm bestaan uit volgende attributen:

* String id
* int version
* String vorm

### Gebruik van de API i.v.b. met Bedrijfsvorm

Volgende titels volgen de basis url.

Bv. bij onderstaade titel is het als de basis url "https://www.voorbeeld.com" is, is de volledige url:  "https://www.voorbeeld.com/bedrijfsvormen/add"

#### /bedrijfsvormen/add

Dit is een POST methode om een nieuw bedrijfsvorm aan te maken.

Wat je _moet_ mee te geven in de body is:

    "vorm":          String vorm

Je krijgt het nieuwe bedrijfsvorm volledig terug.


#### /bedrijfsvormen/patch

Dit is een PATCH methode om een bedrijfsvorm aan te passen.

Wat je _moet_ meegeven in de body is:

    "id":             String id
    "version":        String version
    "vorm":           String vorm

Je krijgt het aangepaste bedrijfsvorm volledig terug.


#### /

Een GET methode om alle bedrijfsvormen te krijgen

#### /bedrijfsvormen/delete

Dit is een DELETE methode waarmee je een bedrijfsvorm verweiderd

Wat je _moet_ meegeven in de body is:

    "id":             String id

## Blockchain

Dit is een geval appart omdat er geen model voor bestaat. Dit word ook niet in de database opgeslagen, maar word verwerkt en doorgestuurd naar de blockchain.

### Gebruik van de API i.v.b. met de blockchain

Bv. bij onderstaade titel is het als de basis url "https://www.voorbeeld.com" is, is de volledige url:  "https://www.voorbeeld.com/lorainput/GenerateJson"

#### /lorainput/GenerateJson

Dit is een GET methode genereert nieuwe test data voor in de blockchain. Dit gebeurt met vaste data, dus werkt enkel in onze een testomgeving.

Dit geeft niets terug, dus éénmaal naar surfen en het tabblad kan gesloten worden.


#### /lorainput/sensordata

Dit is een nodige tussenstap voor de data die word doorgestuurd van de Lora sensor

Dit is een POST methode die de nieuwe sensordate voort duuwt naar de blockchain API.

Wat je _moet_ meegeven in de body is:


    "DevEUI":     String DevEUI
    "container":  String container
    "value":      String value
    ""timestamp": String timestamp
    "Lrcid":      String Lrcid

#### /lorainput/addTransaction

Dit is een POST methode die een nieuw contract aanmaakt in de blockchain API.

Wat je _moet_ meegeven in de body is:


    "DevEUI":         String DevEUI
    "sender":         String sender
    "receiver":       String receiver
    "minTemperature": String minTemperature
    "maxTemperature": String maxTemperature
    "transporter":    String transporter
    "description":    String description

#### /lorainput/changeResponsibility

Dit is een POST methode die de verantwoordelijke voor een tracking veranderd in de blockchain API.

Wat je _moet_ meegeven in de body is:


    "DevEUI":         String DevEUI
    "responsible":    String responsible

#### /lorainput/acceptContract

Dit is een POST methode die de een tracking accepteerd in de blockchain API.

Wat je _moet_ meegeven in de body is:


    "DevEUI":         String DevEUI

#### /lorainput/DenyContract

Dit is een POST methode die de een tracking afwijst in de blockchain API.

Wat je _moet_ meegeven in de body is:


    "DevEUI":         String DevEUI
    "reason":         String reason
