# Katie Yang Individual Project

## Problem Statement
For many travelers, capturing memories is a vital part of the journey. Currently, most people rely on photo-centric social media or standard gallery apps to record their trips. These platforms focus heavily on the visual aspect—the "where" and "what"—but often fail to capture the "feeling" or the atmospheric context of a specific moment.

The problem is that travel memories are more than just images; they are tied to the environment. We remember the distinct vibe of a humid afternoon in Bangkok or the crisp, chilly air of a winter morning in Sapporo. Currently, there is no simple way to archive these environmental conditions without manually typing them out. Over time, these sensory details fade, and scattered photos in a phone gallery aren't enough to relive the actual mood of the day. I aim to create a Minimalist, Weather-Driven Travel Diary that serves as a sensory archive for travelers. Instead of focusing on photo uploads, my app allows users to search for a city and instantly retrieve precise weather data for that entry.

## Project Technologies/Techniques

* Security/Authentication
  * **AWS Cognito**
* Database
  * **MySQL 8.x** (Local development - *travel_journal* schema)
* ORM Framework
  * **Hibernate 5.x / 6.x**
* Dependency Management
  * **Maven**
* Web Services consumed using Java
  * **Weather API (weatherapi.com)** - for real-time weather data
* Front End & UI
  * **Bootstrap 5**: Modern responsive design and UI components.
  * **Vanilla JavaScript**: Implementation of client-side logic without external library dependencies.
  * **CSS3 & HTML5**: Custom styling for enhanced user experience.
* Data Validation
  * **Hibernate Validator** (Bean Validation API)
  * **Bootstrap 5 form validation**
* Logging
  * **Log4J2** (Console and File Appenders)
  * **Debug level monitoring** and pattern layout configuration
* Tech explored as part of this work
  * **AWS Cognito SDK for Java integration**
  * **Hibernate Configuration**: XML-based session factory and property management.
  * **Log4j2 Pattern Layout**: Customizing log formats for effective troubleshooting.
  * **Building Clean Servlet Architecture**
* Unit Testing
  * **JUnit 5**: Core logic testing.
  * **SoapUI**: API endpoint and JSON response verification.
* IDE
  * **IntelliJ 2025.03**


## Design
- [User Stories](https://github.com/yyang22-AdvancedJava/TravelJournal/blob/main/DesignDocuments%20/userStories.md)
- [Screen Design](https://github.com/yyang22-AdvancedJava/TravelJournal/blob/main/DesignDocuments%20/Screens.md)
- [Database Design](https://github.com/yyang22-AdvancedJava/TravelJournal/blob/main/DesignDocuments%20/databaseDesign.md)


## [Project Plan](https://github.com/yyang22-AdvancedJava/TravelJournal/blob/main/ProjectPlan.md)

## [Time Log](https://github.com/yyang22-AdvancedJava/TravelJournal/blob/main/TimeLog.md)

