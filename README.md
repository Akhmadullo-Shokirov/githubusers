# GitHub Repositories API

This project provides a RESTful API to retrieve information about a user's GitHub repositories.

## Acceptance Criteria

As an API consumer, given a username and header "Accept: application/json", you can list all GitHub repositories for the user that are not forks. The response includes the following information:

- Repository Name
- Owner Login
- For each branch: Name and last commit SHA

If the provided GitHub user does not exist, a 404 response is returned in the following format:

`{"status": 404, "message": "User not found: ${username}"}`

For requests with the header "Accept: application/xml", a 406 response is returned in the following format:
`{"status": 406, "message": "Unsupported media type: ${mediaType}"}`

## Setup

Before running the application, you need to set the value of the `github.api.token` variable in the `application.properties` file. Follow the instructions in the [GitHub API Authentication Guide](https://docs.github.com/en/rest/overview/authenticating-to-the-rest-api?apiVersion=2022-11-28) to learn how to generate and use an authentication token.

## Running the Application

To run the application, follow these steps:

    Clone the repository: git clone https://github.com/akhmadullo-shokirov/githubusers.git
    Navigate to the project directory: cd githubusers
    Build the project: mvn clean install
    Run the application: java -jar target/githubusers.jar

The application will start on the default port (8080). You can access the API using the endpoint /repositories.
Technologies Used

    Spring Boot
    RestTemplate
    Jackson
    Maven

## Author

Akhmadullo Shokirov

Contact: ahmadillo0610@gmail.com