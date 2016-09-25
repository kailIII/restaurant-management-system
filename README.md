# Restaurant Management System

The Restaurant Management System is an app that allows hotel managers to assign waiters to any table to any restaurant at that establishment. Waiters are only allowed to be assigned to one restaurant four times.

Waiters can use the app to view their table assignments for the day.

## Backend

The backend uses the Spring framework. I chose to use REST controllers for CRUD operations because that would allow me to use React for the frontend.

### Database

I chose to use H2 for the database because that was fastest to get up and running! There is a command line that automatically seeds the database every time we build the application.

### Build

You can build the project by opening up the project in IntelliJ IDEA and then pressing `Shift + F10`.

When IntelliJ launches the application, you can access the site using the URL:

    http://localhost:8080/

## Frontend

The frontend is built using React and SASS. The source files are located in:

    /src/main/javascript
    /src/main/scss
    
You can do the following things with the frontend app:

- Select whether you are a Manager or a Waiter
- Switch between Manager and Waiter mode
- (Manager) Switch between different restaurants
- (Manager) Review a restaurant's tables and who are assigned to those tables
- (Manager) Assign a waiter to a table
- (Manager) Unassign a waiter from a table
- (Waiter) Select your account
- (Waiter) Review your table assignments for each restaurant

### Build

You can compile any modifications you make to the JavaScript or SASS using Gulp. The Gulp and NPM files are located in the root directory. You will need to run the following commands:

    npm install
    gulp

This will compile all the frontend source files and copy them over tho

    /src/main/resources/static/js
    /src/main/resources/static/css

## Unit Tests

This project comes with a couple of unit tests to test the services that I made. They check whether the way I handled the 4 table per restaurant requirement works.
