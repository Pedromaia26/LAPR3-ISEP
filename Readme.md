# Project Report Lapr3 - Sprint 2

## Delivery of Containers ##

### Teachers/Advisors ###

Ana Isabel Gaspar Freitas - AIF\


### Client ###
Nuno Bettencourt - NMB

## Classes DE_DF – Group 041 ##
1170504 Hugo Nogueira\
1201276 Tiago Marques 1201276\
1201381	Pedro Alves\
1201384 Pedro Fernandes\
1200801 Daniel Braga\
1180727 Ruben Martins\
	

### Abstract ###
In this report we start by explaining the initial problem that was presented to us, which consists of building a Java application that tracked the fastest routes from one point to another in the world so that the delivery of containers is more efficient.
The application must follow a Test Driven Development approach using SonarQube® and Jenkins® servers.
Before coding development, we focused our work on the engineering software artifacts that would be relevant during coding.
All our work was distributed among the seven members of the group and we tried to follow an even distribution, however imperfect.

### Theorical Introduction ###
In first sprint of the project that was presented to us, it required that we elaborate a product that would support the delivery of containers only by sea. In this service we should be able to import a file that contains a list of ships, and from this list return it to a BST, and basically it should allow the management of ships, dynamic data of ships and containers.

In this second sprint, the previously elaborated product would have to contain a coherent connection between the database and the implementation carried out in Java. In this service we should also be able to import a file that contains a list of ports and be able to find the closest port given its call sign. Then, with the help of the Pl/Sql language, the product should be able to determine some information related to the transport of goods (loading and unloading of containers) and information related to the ship, such as the current weight.


### Work Organization, Planning and Methodology ###
The methodology followed by our group is based on Scrum®, so all our work was developed in a certain way, starting with planning who and what each member of the group would do.
One of the central parts of our team was mutual help, as without it it would be almost impossible to work as a team. In this second sprint, unlike the first sprint, it was already possible to have a more active and more efficient communication and therefore, we believe that over these weeks the spirit of mutual help was much greater and much more consistent.
To help organize the sprint, we use Jira software.

We first analyze the user stories and then build the Use-Case Diagram shown below.
After that, and following a Scrum methodology, we gave an estimated value so that we could know how long each use case would take.
In the next step, the use cases were distributed among the group members.
Over the next few days, each member worked on their use case, and whenever someone needed to ask a question or needed help with something, the group would try to help.

Next, we start by elaborating the Use Case diagram, which can be seen below:
### Use-case diagram for this current sprint: ###
![Use-Case](./Documentation/Sprint2/Requirements_Engineering/UseCase.jpg)


With these Use Cases in mind, we strive to build the Software Engineering diagrams.

We created the Domain Model that we would follow in the development of our sprint, which can be seen below:
### Domain template for this current sprint: ###
![Domain-Model](./Documentation/Sprint2/Engineering_Analysis/DomainModel.svg)

We created the Global Class Diagram that we would follow in the development of our sprint, which can be seen below:
### Domain template for this current sprint: ###
![CD_Global](./Documentation/Sprint2/Engineering_Analysis/CD_Global.jpg)


### This phase of our report is where all the diagrams referring to the project's user stories are located, whether **SSDs**, **SDs** or **CD**.

### SPRINT 1 ###

### Use Case 101 - Import ships from a text file into a BST.

![US101_SSD](./Documentation/Sprint1/Requirements_Engineering/US101/US101_SSD.jpg)
![US101_SD](./Documentation/Sprint1/Engineering_Design/US101/US101_SD.jpg)
![US101_CD](./Documentation/Sprint1/Engineering_Design/US101/US101_CD.jpg)

### Use Case 102 - Search the details of a ship using any of its codes: MMSI, IMO or Call Sign.

![US102_SSD](./Documentation/Sprint1/Requirements_Engineering/US102/US102_SSD.svg)
![US102_SD](./Documentation/Sprint1/Engineering_Design/US102/US102_SD.png)
![US102_CD](./Documentation/Sprint1/Engineering_Design/US102/US102_CD.svg)

### Use Case 103 - Have the positional messages temporally organized and associated with each of the ships

![SSD_US103](./Documentation/Sprint1/Requirements_Engineering/US103/SSD_US103.svg)
![SD_US013](./Documentation/Sprint1/Engineering_Design/US103/SD_US013.svg)
![US103_CD](./Documentation/Sprint1/Engineering_Design/US103/US103_CD.svg)

### Use Case 104 -  Make a Summary of a ship's movements.

![US104_SSD](./Documentation/Sprint1/Requirements_Engineering/US104/US104_SSD.svg)
![US104_SD](./Documentation/Sprint1/Engineering_Design/US104/US104_SD.svg)
![US104_CD](./Documentation/Sprint1/Engineering_Design/US104/US104_CD.svg)

### Use Case 105 - List for all ships the MMSI, the total number of movements, Travelled Distance and Delta Distance.

![US105_CD](./Documentation/Sprint1/Engineering_Design/US105/US105_CD.svg)

### Use Case 106 - Get the top-N ships with the most kilometres travelled and their average speed (MeanSOG).

![UC106_SSD](./Documentation/Sprint1/Requirements_Engineering/US106/UC106_SSD.jpg)
![UC106_SD](./Documentation/Sprint1/Engineering_Design/US106/UC106_SD.jpg)
![US106_CD](./Documentation/Sprint1/Engineering_Design/US106/US106_CD.svg)

### Use Case 107 - Return pairs of ships with routes with close departure/arrival coordinates (no more than 5 Kms away) and with different Travelled Distance.

![SSD_US107](./Documentation/Sprint1/Requirements_Engineering/US107/SSD_US107.svg)
![SD_US107](./Documentation/Sprint1/Engineering_Design/US107/SD_US107.svg)
![US107_CD](./Documentation/Sprint1/Engineering_Design/US107/US107_CD.svg)


### SPRINT 2 ###

### Use Case 201 - Import ports from a text file and create a 2D-tree with port locations.

![US201_SSD](./Documentation/Sprint2/Requirements_Engineering/US201/US201_SSD.svg)
![US201_SD](./Documentation/Sprint2/Requirements_Engineering/US201/US201_SD.svg)
![US201_CD](./Documentation/Sprint2/Requirements_Engineering/US201/US201_CD.svg)

### Use Case 202 - Find the closest port of a ship given its CallSign, on a certain DateTime. 

![US202_SSD](./Documentation/Sprint2/Requirements_Engineering/US202/US202_SSD.svg)
![US202_SD](./Documentation/Sprint2/Requirements_Engineering/US202/US202_SD.svg)
![US202_CD](./Documentation/Sprint2/Requirements_Engineering/US202/US202_CD.svg)

### Use Case 203 - Want the team to review the relational data model in view of the new user stories so it can support all the requirements to fulfil the purpose of the system being developed. 

![US203_SSD](./Documentation/Sprint2/Requirements_Engineering/US203/US203_SSD.svg)
![US203_SD](./Documentation/Sprint2/Requirements_Engineering/US203/US203_SD.svg)
![US203_CD](./Documentation/Sprint2/Requirements_Engineering/US203/US203_CD.svg)

### Use Case 204 - Want to know the current situation of a specific container being used to transport my goods.

![US204_SSD](./Documentation/Sprint2/Requirements_Engineering/US204/US204_SSD.svg)
![US204_SD](./Documentation/Sprint2/Requirements_Engineering/US204/US204_SD.svg)
![US204_CD](./Documentation/Sprint2/Requirements_Engineering/US204/US204_CD.svg)

### Use Case 205 - Want the list of containers to be offloaded in the next port, including container identifier, type, position, and load.

![US205_SSD](./Documentation/Sprint2/Requirements_Engineering/US205/US205_SSD.svg)
![US205_SD](./Documentation/Sprint2/Requirements_Engineering/US205/US205_SD.svg)
![US205_CD](./Documentation/Sprint2/Requirements_Engineering/US205/US205_CD.svg)

### Use Case 206 - Want the list of containers to be loaded in the next port, including container identifier, type, and load.

![US206_SSD](./Documentation/Sprint2/Requirements_Engineering/US206/US206_SSD.svg)
![US206_SD](./Documentation/Sprint2/Requirements_Engineering/US206/US206_SD.svg)
![US206_CD](./Documentation/Sprint2/Requirements_Engineering/US206/US206_CD.svg)

### Use Case 207 - Want to know how many cargo manifests I have transported during a given year and the average number of containers per manifest.

![US207_SSD](./Documentation/Sprint2/Requirements_Engineering/US207/US207_SSD.svg)
![US207_SD](./Documentation/Sprint2/Requirements_Engineering/US207/US207_SD.svg)
![US207_CD](./Documentation/Sprint2/Requirements_Engineering/US207/US207_CD.svg)

### Use Case 208 - Want to know the occupancy rate (percentage) of a given ship for a given cargo manifest. Occupancy rate is the ratio between total number of containers in the ship coming from a given manifest and the total capacity of the ship, i.e.,the maximum number of containers the ship can load.

![US208_SSD](./Documentation/Sprint2/Requirements_Engineering/US208/US208_SSD.svg)
![US208_SD](./Documentation/Sprint2/Requirements_Engineering/US208/US208_SD.svg)
![US208_CD](./Documentation/Sprint2/Requirements_Engineering/US208/US208_CD.svg)

### Use Case 209 - Want to know the occupancy rate of a given ship at a given moment.

![US209_SSD](./Documentation/Sprint2/Requirements_Engineering/US209/US209_SSD.svg)
![US209_SD](./Documentation/Sprint2/Requirements_Engineering/US209/US209_SD.svg)
![US209_CD](./Documentation/Sprint2/Requirements_Engineering/US209/US209_CD.svg)

### Use Case 210 -  Need to know which ships will be available on Monday next week and their location.

![US210_SSD](./Documentation/Sprint2/Requirements_Engineering/US210/US210_SSD.svg)
![US210_SD](./Documentation/Sprint2/Requirements_Engineering/US210/US210_SD.svg)
![US210_CD](./Documentation/Sprint2/Requirements_Engineering/US210/US210_CD.svg)

#################################################################################