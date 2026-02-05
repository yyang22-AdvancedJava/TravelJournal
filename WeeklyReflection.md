# Weekly Reflection

## Week 2
1/28/2026 - 5 hours

Tasks Completed:

- Chose a project goal
- Created this repository
- Wrote the problem statement
- Started documenting the project plan
- Completed user stories
- Drew screen design

Last week, I only had a broad concept for my app, but today, the project's direction became much clearer through the process of writing the Problem Statement and defining the User Stories. 
In particular, working on the Screen Design allowed me to visually map out how the interface color will change according to the weather when a user searches for a city. 
This visual exercise helped me realize the core value of the app: capturing the "atmosphere" of a trip beyond just text.

By organizing the User Stories and identifying the MVP (Minimum Viable Product) features, 
I realized that I need to prioritize the core logic of 'Weather API Integration' and 'Dynamic Background Color Adjustment' over the more vague ideas I initially had. 
Once the screen layouts are fully finalized next week, I expect to create a more detailed technical plan for how the backend will process and store this environmental data.

## Week 3
2/4/2026 - 2 hours

Tasks Completed:

- First cut at database design (User, Journal tables)
- Create the dev version of the database
- Create Journal entity
- Create a class to perform CRUD on journal - JournalDao
- Create the config files for the DB connection info (dev and test)
- Create a test version of the database for unit testing
- Create unit tests for the JournalDao

This week, I focused on setting up the database layer for my project. I created the journal table in MySQL and connected it to the Java Journal entity using Hibernate. During the process, I encountered some issues where the table could not be found; however, I resolved them by matching the @Table name and adding a default constructor to the entity. I also implemented a search feature in JournalDao using the Criteria API to find entries by location. Finally, I verified everything was working correctly by running JUnit tests for the search logic.
