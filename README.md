# table-data-dashboard
This Spring Boot application connects to a database at specific intervals or times, retrieves data, and saves it locally as a .JSON file. The data is then presented to the user as a table through a web interface.

## How to run
Before running the application, follow the steps below to set up the required environment.

### Prerequisites
- Java 17
- Maven
- Docker

### Installation
1. Clone this repository to your local machine.
2. Go to the project root directory and run the following command to build the application: `make clean-build-up`

![web-interface.gif](doc%2Fweb-interface.gif)


### Usage
1. Open your web browser and navigate to http://localhost:8080.
2. You will be prompted to log in. Use the following credentials:
   - Username: `dev`
   - Password: `dev`

   The application utilizes in-memory authentication with the provided credentials.

3. View the desired data through the web interface.
