![Front-End Technologies](https://raw.githubusercontent.com/RDV1996/Coldchain-project/Front-End/repo-images/front-end.png "Front-End Technologies")
# Opmaak Framework
Bij het maken van de webapplicatie is er gebruik gemaakt van Angular 7 in combinatie met Bootstrap 4.7.0 Daarbij aanvullend is er gebruik gemaakt van MDBootstrap en NGBootstrap, om pre-defined componenten tot onze beschikking te hebben die het werk zouden verzachten.

Verder werd er gebruik gemaakt van de aanvullende jQuery en javascript versies.

## Concept

Het concept van de webapplicatie was om ze zo slim mogelijk te houden zodat het overzichtelijk kon blijven voor een gebruiker en dat alles duidelijk zou zijn. Hiervoor is de webapplicatie hoofdzakkelijk gemaakt uit cards.

Verder zijn er componenten gebruikt zoals een stepper om een duidelijk beeld te krijgen van waar trackings zich bevinden. Hiervoor zijn er externe javascripts gebruikt.
Het kleurenpalet hebben we behouden tot de huisstijl kleuren van Ordina. 

## Visualisatie
### Algemeen
De visualisaties zijn heel basic gehouden. Het enige wat gevisualiseerd is, zijn de readings per contract. Als er uit de tabel een contract wordt geselecteerd, worden all readings van dit contract opgehaald en de temperatuur wordt verwerkt in een eenvoudige lijngrafiek. Zo is er een visueel overzicht van de temperatuur schommelingen.
### Code
Het eerste wat er gebeurt, is het ophalen van de readings. Dit gebeurt als volgt:
```javascript
  this.readingService.getReadings(this.contract.contractId).subscribe(datar =>
  {
      this.readings = datar;
      this.readings.reverse();
```

Vervolgens wordt de data klaargemaakt om in de grafiek te tonen en worden er eigenschappen van de grafiek vastgelegd:
```javascript
  const dataPoints = [];
  let dpsLength = 0;
  const chart = new CanvasJS.Chart('chartContainer', {
      exportEnabled: true,
      title: {
          text: 'Readings per Contract'
      },
      data: [{
          markerType: 'none',
          type: 'line',
          lineColor: 'rgba(244,130,33,1)',
          dataPoints: dataPoints,
      }]
  });
  let i = 1;
  for (const reading of datar) {
      dataPoints.push({x: i, y: reading.value});
      i++;
  }
  dpsLength = dataPoints.length;
```

Tot slot wordt met 1 lijn code de grafiek getekend:
```javascript
chart.render();
```



