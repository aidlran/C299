# Superhero Sightings

This project is a full-stack web application for tracking superhero & supervillain sightings built using Spring Boot Web.

---

**Both parts of the original assessment spec follow:**

## Data Layer

In this milestone, you have learned how to integrate Java code with relational databases using Spring JdbcTemplates. With this in mind, we would like you to try your hand at designing a database and JdbcTemplate-based DAO for a superhero sightings application.

### Requirements

With the rising popularity of superhero movies, there has been a heightened awareness of superheroes in our midst. The frequency of superhero (and supervillain) sightings is increasing at an alarming rate. Given this development, the Hero Education and Relationship Organization (HERO) has asked our company to develop a database and data layer for their new superhero sightings web application.

The system has the following requirements:

- It must keep track of all superhero/supervillain information.
	- Heroes have names, descriptions, and a superpower.
	- Heroes are affiliated with one or more superhero/supervillain organizations.
- It must keep track of all location information:
	- Locations have names, descriptions, address information, and latitude/longitude coordinates.
- It must keep track of all superhero/supervillain organization information:
	- Organizations have names, descriptions, and address/contact information.
	- Organizations have members.
- A user must be able to record a superhero/supervillain sighting for a particular location and date.
- The system must be able to report all of the superheroes sighted at a particular location.
- The system must be able to report all of the locations where a particular superhero has been seen.
- The system must be able to report all sightings (hero and location) for a particular date.
- The system must be able to report all of the members of a particular organization.
- The system must be able to report all of the organizations a particular superhero/villain belongs to.

### Deliverables

To complete this assignment, you must deliver the following items:

- An Entity-Relationship-Diagram
	- You may use MySQL Workbench to create a diagram or your choice of alternative tools such as Pencil, Draw.IO, or LucidChart.
	- The database must achieve 2nd normal form at minimum.
	- Proper naming conventions should be used.
	- The ERD should be very easy to read, with all components clearly labeled.
	- Use a common and appropriate file format, such as a png or jpg image or a PDF document.
- A database creation script
	- The script should create the database with all tables, columns, and relationships.
	- Make reasonable assumptions about column data types; be prepared to justify your decisions.
	- The script should be re-runnable. This means it should drop the database and all objects if they exist and recreate them. You should be able to execute the script many times in a row without error. See the scripts provided for the databases used in the Relational Database unit for examples.
- DAO Implementation and Unit Tests
	- DAO should have an interface and an implementation.
	- DAO and DTOs must fully represent all data and relationships contained in the database design.
	- Implementation must make proper use of transactions.
	- Unit tests must fully test all create, read, update, and delete functionality for all entities and test all many-to-many and one-to-many relationships in the database.

*(Removed information about group discussion and submission guidance.)*

## Spring Boot Web App

You have learned how to integrate your code with relational databases using an application data, and you have learned how to build a Full Stack Spring Boot web application. With this in mind, we would like you to try your hand at building an Spring Boot web application on top of the Superhero Sightings Data Layer.

### Requirements

The Hero Education and Relationship Organization (HERO) is so impressed with the work we did on the Superhero Sightings Data Layer project that they have awarded us a contract to build a Spring Boot web application.

The system has the following requirements:

1. It must have a screen(s) to create, view, edit, and delete superheroes/supervillains in the system.
2. It must have a screen(s) to create, view, edit, and delete superpowers in the system.
3. It must have a screen(s) to create, view, edit, and delete locations in the system.
4. It must have a screen(s) to create, view, edit, and delete superhero/supervillain organizations in the system.
5. It must have a screen(s) to create, view, edit, and delete superhero/supervillain sighting (superhero/supervillain, location, and time) in the system.
6. It must have a home page that displays general information about the application, navigation to all the other pages, and a newsfeed of the latest 10 sightings in the database.
7. CHALLENGE 1: Allow users to upload a picture for each superhero/supervillain and then display the picture when the sighting(s) of that superhero/supervillain is displayed.
8. CHALLENGE 2: Incorporate Google Maps into the home/landing page and show the location of each of the sightings in the sightings news feed (this is a stretch goal - completely on your own. We have no material about incorporating Google maps into your application).

### Deliverables

To complete this assignment, you must deliver the following items:

1. Wireframes for all pages in the web app:
	1. You may use tools such as Balsamiq, Pencil, Draw.IO, or LucidChart.
	2. Include notes on endpoints, path variables / query string / form parameters as appropriate.
2. Spring Boot Web App Implementation:
	1. Web application must be built following the MVC patterns presented in the course.
	2. Implementation must make proper use of dependency injection.
	3. Web application must fully integrate the Superhero Sighting Data Layer.

*(Removed information about group discussion and submission guidance.)*
