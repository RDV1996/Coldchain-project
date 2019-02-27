# ChainReact - Cold Chain Monitoring

### 1. Background of the project

	This is a project in collaboration with Ordina. Ordina is the largest independent ICT service provider of the BeNeLux. Ordina is a consultancy company with throughout the BeNeLux nearly 3390 employees. The expertise of these eployees is to business processes and ICT sustainable improvement.  
	  
	This project is all about monitoring a Cold Chain, this is a supply chain for cold products. Currently a lot is measured by hand but these measurements can only be performed before and / or after transport. The fluctuations in temperature during transport can therefore not be tracked. That the fluctuations during the transport can not be tracked is a problem. It is not immediately clear where something has gone wrong and therefore it is more difficult to indicate the person responsible for this.  
	A second point about why the current system is a problem, is that these goods must be kept cool for a reason. This concerns nutrition, medication, blood bags and so on. As a consumer (or patient in the case of medication or blood bags) you still want the guarantee that everything is healthy.  
	  
	So basicly product will me monitored constantly by sensors during the transport to detect fluctuations. The sensors pass on their data to an application that will save and visualize the data. If something then goes wrong (eg the temperature is too high), you'll be able to be notified via the platform.  
	  
	The main goal of this project is for Ordina to get a better insight into Blockchain and the usablility of it. In this case we'll be using the Blockchain as a basic database and see how it performs.
	
1. Research van Blockchain  
1. Plan Van Aanpak
1. Realisatie
	1. Infrastructuur
		* Java API op Beanstalk
		* Blochchain hosting
		* Web app hosting
	1. Blockchain
		* Model
		* Functions
		* Querrying
	1. API
		* Sample data generator
		* Functionaliteiten	
	1. Front-end
		* Opmaak framework
		* Visualisatie
