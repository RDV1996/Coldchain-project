## Dit is het readme-bestand voor de Ordina Coldchain Hyperledger.
Dit bestand geeft aanvullende informatie over de 4 verschillende bestanden in deze samengestelde bna.


# model.cto:

Dit bestand bouwt het model van de blockchain.
De 3 belangrijkste delen in een blockchain zijn deelnemers, activa en transacties.

In dit prototype van een blockchain heeft geen deelnemer, omdat bedrijven vaak willen dat hun bedrijfsgegevens worden beveiligd in een andere database waar deze niet toegankelijk is. Om deze reden hebben we er op geantwoord om geen deelnemers aan dit protoype toe te voegen.

Dit model bevat 2 items. Het leesitem wordt gebruikt om alle gegevens op te slaan die door een LoRa-sensor naar de blockchain worden verzonden. Het omvat het type gegevens, de waarde, het tijdstip van verzending, de locatie, de persoon die verantwoordelijk is voor het transport op het moment van verzending, de status van het transport en het contract waarin de sensor actief was. De tweede is het activum van het contract, dat al deze meetwaarden aan elkaar koppelt. Het omvat de DevEUI, de afzender, de ontvanger, de primaire transporteur, de persoon die momenteel verantwoordelijk is voor het transport, de status van het transport, de minimum- en maximumtemperatuur, de beschrijving van het transport en de reden voor weigering in geval van transport wordt geweigerd.

Eindelijk hebben we 5 transactietypen. Met deze transacties kunnen we de activa in onze blockchain toevoegen en wijzigen. We zien meer over hoe deze werken in het scriptbestand.


# script.js:

Dit bestand beschrijft de interne functies van de blockchain.
Om de werkbelasting van de backend-ontwikkeling van ons project te verlagen, hebben we functies in de blockchain toegevoegd die het aanmaken van nieuwe assets automatiseren en vereenvoudigen. Dit betekent echter wel dat er extra transacties zijn om de activa te creëren.
In dit bestand vinden we 6 functies.

1. De functie create_uuid maakt een universeel unieke ID aan, waarmee we elk item uniek in zijn soort kunnen maken. 
1. De createContract-functie genereert een nieuw contract met de gegeven transactionele gegevens. Eerst wordt gecontroleerd of er al een ander contract bestaat onder de gegeven DevEUI. Als er geen actief contract is, schrijft het de gegevens in het contract en voegt de hoofdtransporteur toe als de huidige verantwoordelijke transporteur en voegt de status van IN_TRANSIT toe. Vervolgens wordt het nieuwe contract toegevoegd aan de blockchain.
1. De createReading-functie genereert een nieuwe meting met de gegeven transactionele gegevens. Eerst wordt gecontroleerd of een contract momenteel actief is onder de gegeven DevEUI. Als er een contract is, wordt het contract gekoppeld aan de meting en wordt gecontroleerd of de temperatuurwaarde tussen de toegestane waarden ligt. Als dit niet het geval is, worden zowel de status van de uitlezing als het contract gewijzigd in GEVAAR. Het selecteert ook de momenteel verantwoordelijke transporter uit het contract als verantwoordelijk vervoerder van de meting.
1. De changeResponsibility-functie stelt ons in staat om de vervoerder die verantwoordelijk is voor een zending te wijzigen. Eerst wordt gecontroleerd of een contract momenteel actief is onder de gegeven DevEUI. Als er een contract is, verandert dit de verantwoordelijke transporteur in de nieuw opgegeven transporter.
1. Met de endContract-functie kunnen ontvangers bevestigen dat ze een zending hebben ontvangen en geaccepteerd. Eerst wordt gecontroleerd of een contract momenteel actief is onder de gegeven DevEUI. Als er een contract is, wordt de status gewijzigd in EINDE.
1. Met de ThedenialContract-functie kunnen ontvangers een verzonden verzending weigeren. Eerst wordt gecontroleerd of een contract momenteel actief is onder de gegeven DevEUI. Als er een contract is, wordt de reden voor de weigering opgegeven en wordt de status gewijzigd in ONWETEND.


# querries.qry:

Dit bestand creëert vragen om het zoeken van gegevens in de blockchain gemakkelijker te maken.
Het bevat veel vragen, elk met hun eigen functie.

Er zijn 3 hoofdtypen van querries:
* Selecteer uitlezingen zoekt naar alle waarden die een bepaalde parameter bevatten en plaatst deze eerst op nieuwste.
* Selecteer contracten zoekt naar alle contracten die een bepaalde parameter bevatten.
* Selecteer actieve contracten zoekt naar alle contracten die nog gaande zijn en een bepaalde parameter bevatten.


# permissions.acl:

Dit bestand geeft toegang tot de verschillende deelnemers aan de blockchain.
Dit was niet opgenomen in dit prototype voor een coldchain hyperledger en is dus beperkt om voor iedereen toegankelijk te zijn.
