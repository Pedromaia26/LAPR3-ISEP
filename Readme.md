# Project Report Lapr3 - Sprint 3

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

### Abstract ###
In this report we start by explaining the initial problem that was presented to us, which consists of building a Java application that tracked the fastest routes from one point to another in the world so that the delivery of containers is more efficient.
The application must follow a Test Driven Development approach using SonarQube® and Jenkins® servers.
Before coding development, we focused our work on the engineering software artifacts that would be relevant during coding.
All our work was distributed among the seven members of the group and we tried to follow an even distribution, however imperfect.

### Theorical Introduction ###
In first sprint of the project that was presented to us, it required that we elaborate a product that would support the delivery of containers only by sea. In this service we should be able to import a file that contains a list of ships, and from this list return it to a BST, and basically it should allow the management of ships, dynamic data of ships and containers.

In second sprint, the previously elaborated product would have to contain a coherent connection between the database and the implementation carried out in Java. In this service we should also be able to import a file that contains a list of ports and be able to find the closest port given its call sign. Then, with the help of the Pl/Sql language, the product should be able to determine some information related to the transport of goods (loading and unloading of containers) and information related to the ship, such as the current weight.

In this third sprint, the product that was requested and that has been developed over the last few weeks should be able to import data related to countries, ports, borders and some data referring to the database so that it would be possible to create a freight network. In this service, in addition to the aforementioned, the product should be able to identify which ports or cities are closest to all other locations and, finally, it should also be able to avoid any invalid data entered or updated within the database.


### Work Organization, Planning and Methodology ###
The methodology followed by our group is based on Scrum®, so all our work was developed in a certain way, starting with planning who and what each member of the group would do.
One of the central parts of our team was mutual help, as without it it would be almost impossible to work as a team. In this third sprint, in addition to the first and second sprints, the team was much more united in solving the requested problems during these three weeks. We believe that one of the keys to the success of this sprint was being more up-to-date on the pace at which the work was developing, this of course with the help of the daily meetings. To help organize the sprint, we use Jira software.

We first analyze the user stories and then build the Use-Case Diagram shown below.
After that, and following a Scrum methodology, we gave an estimated value so that we could know how long each use case would take.
In the next step, the use cases were distributed among the group members.
Over the next few days, each member worked on their use case, and whenever someone needed to ask a question or needed help with something, the group would try to help.

Next, we start by elaborating the Use Case diagram, which can be seen below:
### Use-case diagram for this current sprint: ###
![Use-Case](./Documentation/Sprint3/Requirements_Engineering/UseCase.jpg)


With these Use Cases in mind, we strive to build the Software Engineering diagrams.

We created the Domain Model that we would follow in the development of our sprint, which can be seen below:
### Domain template for this current sprint: ###
![Domain-Model](./Documentation/Sprint3/Engineering_Analysis/DomainModel.svg)

We created the Global Class Diagram that we would follow in the development of our sprint, which can be seen below:
### Domain template for this current sprint: ###
![CD_Global](./Documentation/Sprint3/Engineering_Analysis/CD_Global.svg)


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

### Use Case 202 - Find the closest port of a ship given its CallSign, on a certain DateTime. 

![US202_SSD](./Documentation/Sprint2/Requirements_Engineering/US202/US202_SSD.svg)
![US202_SD](./Documentation/Sprint2/Requirements_Engineering/US202/US202_SD.svg)

### Use Case 203 - Want the team to review the relational data model in view of the new user stories so it can support all the requirements to fulfil the purpose of the system being developed. 

![US203_PDM](./Documentation/Sprint2/Requirements_Engineering/US203/physical_data_model.jpg)

### Use Case 204 - Want to know the current situation of a specific container being used to transport my goods.

![US204_SSD](./Documentation/Sprint2/Requirements_Engineering/US204/US204_SSD.svg)
![US204_SD](./Documentation/Sprint2/Requirements_Engineering/US204/US204_SD.svg)

### Use Case 205 - Want the list of containers to be offloaded in the next port, including container identifier, type, position, and load.

![US205_SSD](./Documentation/Sprint2/Requirements_Engineering/US205/US205_SSD.svg)
![US205_SD](./Documentation/Sprint2/Requirements_Engineering/US205/US205_SD.svg)

### Use Case 206 - Want the list of containers to be loaded in the next port, including container identifier, type, and load.

![US206_SSD](./Documentation/Sprint2/Requirements_Engineering/US206/US206_SSD.svg)
![US206_SD](./Documentation/Sprint2/Requirements_Engineering/US206/US206_SD.svg)

### Use Case 207 - Want to know how many cargo manifests I have transported during a given year and the average number of containers per manifest.

![US207_SSD](./Documentation/Sprint2/Requirements_Engineering/US207/US207_SSD.svg)
![US207_SD](./Documentation/Sprint2/Requirements_Engineering/US207/US207_SD.svg)

### Use Case 208 - Want to know the occupancy rate (percentage) of a given ship for a given cargo manifest. Occupancy rate is the ratio between total number of containers in the ship coming from a given manifest and the total capacity of the ship, i.e.,the maximum number of containers the ship can load.

![US208_SSD](./Documentation/Sprint2/Requirements_Engineering/US208/US208_SSD.svg)
![US208_SD](./Documentation/Sprint2/Requirements_Engineering/US208/US208_SD.svg)

### Use Case 209 - Want to know the occupancy rate of a given ship at a given moment.

![US209_SSD](./Documentation/Sprint2/Requirements_Engineering/US209/US209_SSD.svg)
![US209_SD](./Documentation/Sprint2/Requirements_Engineering/US209/US209_SD.svg)

### Use Case 210 -  Need to know which ships will be available on Monday next week and their location.

![US210_SSD](./Documentation/Sprint2/Requirements_Engineering/US210/US210_SSD.svg)
![US210_SD](./Documentation/Sprint2/Requirements_Engineering/US210/US210_SD.svg)


### SPRINT 3 ###

### Use Case 301 - I which to import data from countries, ports, borders and seadists from the database to build a freight network. 

![US301_SSD](./Documentation/Sprint3/Requirements_Engineering/US301/US301_SSD.svg)
![US301_SD](./Documentation/Sprint3/Requirements_Engineering/US301/US301_SD.svg)

### Use Case 302 - I wish to colour the map using as few colours as possible.

![US302_SSD](./Documentation/Sprint3/Requirements_Engineering/US302/US302_SSD.svg)
![US302_SD](./Documentation/Sprint3/Requirements_Engineering/US302/US302_SD.svg)

### Use Case 303 - I wish to know which places (cities or ports) are closest to all other places (closeness places).

![US303_SSD](./Documentation/Sprint3/Requirements_Engineering/US303/US303_SSD.svg)
![US303_SD](./Documentation/Sprint3/Requirements_Engineering/US303/US303_SD.svg)

### Use Case 304 -  I want to have access to audit trails for a given container of a given cargo manifest, that is, I want to have access to a list of all operations performed on a given container of a given manifest, in chronological order. For each operation I want to know: the user/login that performed it, the date and time the operation was performed, the type of operation (INSERT, UPDATE, DELETE), the container identifier and the 
cargo manifest identifier.

![US304_SSD](./Documentation/Sprint3/Requirements_Engineering/US304/US304_SSD.svg)
![US304_SD](./Documentation/Sprint3/Requirements_Engineering/US304/US304_SD.svg)

### Use Case 305 - I want to know the route of a specific container I am leasing.

![US305_SSD](./Documentation/Sprint3/Requirements_Engineering/US305/US305_SSD.svg)
![US305_SD](./Documentation/Sprint3/Requirements_Engineering/US305/US305_SD.svg)

### Use Case 306 - I want to know the occupancy rate of each warehouse and an estimate of the containers leaving the warehouse during the next 30 days.

![US306_SSD](./Documentation/Sprint3/Requirements_Engineering/US306/US306_SSD.svg)
![US306_SD](./Documentation/Sprint3/Requirements_Engineering/US306/US306_SD.svg)

### Use Case 307 -  I intend to get a warning whenever I issue a cargo manifest destined for a warehouse whose available capacity is insufficient to accommodate the new manifest.

![US307_SSD](./Documentation/Sprint3/Requirements_Engineering/US307/US307_SSD.svg)
![US307_SD](./Documentation/Sprint3/Requirements_Engineering/US307/US307_SD.svg)

### Use Case 308 - I want to have a system that ensures that the number of containers in a manifest does not exceed the ship's available capacity. 

![US308_SSD](./Documentation/Sprint3/Requirements_Engineering/US308/US308_SSD.svg)
![US308_SD](./Documentation/Sprint3/Requirements_Engineering/US308/US308_SD.svg)

### Use Case 309 - I do not allow a cargo manifest for a particular ship to be registered in the system on a date when the ship is already occupied.

![US309_SSD](./Documentation/Sprint3/Requirements_Engineering/US309/US309_SSD.svg)
![US309_SD](./Documentation/Sprint3/Requirements_Engineering/US309/US309_SD.svg)

### Use Case 310 - I intend to have a map of the occupation of the existing resources in the port during a given month.

![US310_SSD](./Documentation/Sprint3/Requirements_Engineering/US310/US310_SSD.svg)
![US310_SD](./Documentation/Sprint3/Requirements_Engineering/US310/US310_SD.svg)

### Use Case 311 - I want to provide a database access account, with login “crew” and password “bd7wd5aF”, which gives access exclusively to the information of the containers that are loaded on my ship. The information about each container to be made available is: identifier, type, position and load.

![US311_SSD](./Documentation/Sprint3/Requirements_Engineering/US311/US311_SSD.svg)
![US311_SD](./Documentation/Sprint3/Requirements_Engineering/US311/US311_SD.svg)

### Use Case 312 -  I want to know the current situation of a specific container being used to transport my goods – US204.

![US312_SSD](./Documentation/Sprint3/Requirements_Engineering/US312/US312_SSD.svg)
![US312_SD](./Documentation/Sprint3/Requirements_Engineering/US312/US312_SD.svg)

#################################################################################