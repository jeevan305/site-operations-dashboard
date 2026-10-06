# Site Operations Dashboard

A full-stack web application for managing sites, tracking installation activities, and viewing operational metrics.

## Tech Stack

### Frontend
- Angular
- TypeScript
- HTML/CSS
- Angular HttpClient
- Angular Router

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- REST APIs
- Bean Validation
- Maven

### Database
- PostgreSQL
- JPA/Hibernate
- Normalized relational schema

## Project Structure

```text
site-operations-dashboard/
├── backend/
│   └── Spring Boot application
├── frontend/
│   └── Angular application
├── database/
│   └── schema.sql
└── .gitignore

Features
Dashboard
- Total sites
- Total installations
- Completed installations
- In-progress installations
- Planned installations
Site Management
- View sites
- Search sites by name
- Filter sites by status
- Create a site
- Update a site
- Delete a site
Installation Management
- View installations
- Filter by status
- Filter by site
- Create an installation
- Update an installation
- Delete an installation
Backend
- RESTful CRUD APIs
- Request validation
- Centralized exception handling
- PostgreSQL persistence
- Entity relationships between users, sites, and installations
- Logging for important operations

Database Design
The application uses three main entities:
Users
  │
  ├── Sites
  │
  └── Installations
          │
          └── Sites

Tables
- users
- sites
- installations
Foreign-key relationships are used to maintain referential integrity.
API Endpoints
Sites
Method	Endpoint	Description
GET	/api/sites	Get sites
GET	/api/sites/{id}	Get site by ID
POST	/api/sites	Create site
PUT	/api/sites/{id}	Update site
DELETE	/api/sites/{id}	Delete site


Installations
Method	Endpoint	Description
GET	/api/installations	Get installations
GET	/api/installations/{id}	Get installation by ID
POST	/api/installations	Create installation
PUT	/api/installations/{id}	Update installation
DELETE	/api/installations/{id}	Delete installation


Dashboard
Method	Endpoint	Description
GET	/api/dashboard/summary	Get operational summary


Local Setup
Prerequisites
Install:
- Java 17+
- Maven
- Node.js
- Angular CLI
- PostgreSQL

Database
Create a PostgreSQL database:
CREATE DATABASE site_operations;

The database schema is available in:
database/schema.sql

Backend
Navigate to:
cd backend

Set the PostgreSQL password as an environment variable.
Git Bash:
export DB_PASSWORD="YOUR_POSTGRES_PASSWORD"

Then start the Spring Boot application from STS or run:
mvn spring-boot:run

The backend runs on:
http://localhost:8080

Frontend
Navigate to:
cd frontend/site-operations-frontend

Install dependencies:
npm install

Start Angular:
ng serve

The frontend runs on:
http://localhost:4200

API Configuration
During local development, the Angular application communicates with:
http://localhost:8080

For deployment, the API URL should be configured using the production environment configuration.
Validation and Error Handling
The backend uses Jakarta Bean Validation for request validation.
Examples:
- Required site name
- Required location
- Required installation type
- Required site ID
- Required status
A centralized GlobalExceptionHandler provides consistent error responses for validation failures, missing resources, and unexpected server errors.
Git
The project is maintained as a single Git repository containing both frontend and backend applications.
frontend + backend + database

Future Deployment
The application can be deployed using cloud hosting platforms such as:
- Angular frontend → Vercel / Azure Static Web Apps
- Spring Boot backend → Render / Azure App Service
- PostgreSQL → Managed PostgreSQL service
Environment variables should be used for production database credentials and API configuration.

Author
Jeevanandham S