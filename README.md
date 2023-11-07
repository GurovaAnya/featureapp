# Feature Switch Application

The Feature Switch Application allows you to manage user access to various features by enabling or disabling features based on a user's email and feature names.

## API Endpoints

### Retrieve Feature Access

**GET /feature?email=XXX&featureName=XXX**

- This endpoint receives an email (user's email) and featureName as request parameters.
- It returns the following response in JSON format.

**Example Response:**

```json
{
  "canAccess": true|false
}
```

## Update Feature Access

**POST /feature**

- This endpoint receives a request in JSON format and returns an empty response with an HTTP Status OK (200) when the database is updated successfully.
- If the update fails, it returns an HTTP Status Not Modified (304).

**Example Request:**

```json
{
  "featureName": "xxx",
  "email": "xxx",
  "enable": true|false
}
```

## Prerequisites

Before you begin, make sure you have the following prerequisites installed on your system:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

You can download and install these tools using the provided links. Ensure that they are set up and working correctly before proceeding with the setup and deployment of the Feature Switch Application.

## Getting Started

To get started with the Feature Switch Application, follow these steps:

1. Clone this repository to your local machine:

    ```bash
   git clone <repository-url>

2. Navigate to the repository directory:

    ```bash
    cd <repository-directory>

Replace <repository-directory> with the name of the directory where the repository was cloned. This is the directory where you'll find the project's files and Docker Compose configuration.

3. Build the Docker containers for the Feature Switch Application:

    ```bash
    docker-compose -f docker-compose.yml build

4. Start the services using Docker Compose:

    ```bash
    docker-compose -f docker-compose.yml up -d
   
This command will start the Feature Switch Application and the monitoring services in the background.

5. Access the Feature Switch Application by opening a web browser and navigating to:

    ```bash
    http://localhost:8080

You can interact with the application and manage feature switches.

## Monitoring

1. Navigate to the monitoring directory:

    ```bash
    cd monitoring

2. Build the Docker containers for the Monitoring Application:

    ```bash
    docker-compose -f monitoring.yml build

3. Start the services using Docker Compose:

    ```bash
    docker-compose -f monitoring.yml up -d

Access the monitoring services:

- Prometheus: http://localhost:9090
- Grafana: http://localhost:3000
- AlertManager: http://localhost:9093/

## Stopping the Services
To stop the services and remove the containers, run the following command in the repository directory:

```bash
docker-compose -f docker-compose.yml down
cd monitoring
docker-compose -f monitoring.yml down
```


This will stop the Feature Switch Application and the monitoring services.

Additional Configuration
You can customize the monitoring services, alerts, and Grafana dashboards by editing the configuration files in the prometheus/, alertmanager/, and grafana/ directories.

# Prometheus Configuration

In the setup, Prometheus scrapes important metrics from the application. It collects data from the `/actuator/prometheus` endpoint to help monitor and analyze the performance and behavior of our main application.

# Alert Rules

The alert rules are in place to monitor specific conditions in the application and trigger notifications when needed:

- **Service Down Alert:** This alert is activated when an instance becomes unreachable for over 2 minutes. Its purpose is to promptly highlight any service outages.

- **High 4xx Error Rate Alert:** This alert notifies about a significant increase in 4xx status responses compared to all responses, helping identify and address potential issues with client requests.

# Grafana Dashboard

The Grafana dashboard is designed to provide a visual representation of essential metrics from the application. It offers insights into various aspects of the application's performance. Key features of this dashboard include:

- **RPS by URI:** This panel displays the Requests Per Second (RPS) for different URIs, allowing you to monitor request traffic patterns.

- **RPS by Instance:** This panel shows the RPS breakdown by application instances, helping you identify performance variations.

- **Status Codes:** This panel visualizes different HTTP status codes, making it easy to spot errors and monitor the response outcomes.

- **Avg Latency (by instance):** This panel provides insights into the average latency experienced by application instances.

- **Avg Latency (by outcome):** Here, you can observe the average latency categorized by different outcomes.

## Running the Tests

Run the tests using the following Maven command:

```bash
mvn test
```

This command will execute the JUnit tests using Maven. You can also run the tests using your preferred IDE that supports Maven.

After the tests have completed, you will see the test results in the terminal or IDE.