<p>&nbsp;</p>
<h1>📋 Budget API</h1>
<p>&nbsp;</p>

A RESTful API for personal and team budget management, built with Java and Spring Boot. This API provides endpoints to track expenses, manage accounts, set budgets, and generate financial reports.

## 💡 Features

- User Authentication: Secure register, login, and session management.

- JWT Security: Authentication and authorization using JWT tokens for protected routes.

- Budget Management: Create, update, and handle budgets over time.

- Database Integration: Built using Spring Data JPA for persistence.

## 🔨 Tools

- ☕ Java

- 🌿 Spring Boot

- 🐘 PostgreSQL

## ⚙️ Setup

### Prerequisites
Ensure the following are installed:

- Java 17+ (recommendation based on Spring Boot trends)

- PostgreSQL

- Maven

### Installation Steps

1. **Clone the repository:**

```bash
git clone git@github.com:Jaoovit/budget-api.git

cd budget-api
```

2. **Prepare environment variables:**

- Rename the _application.properties file to application.properties


- Define the following variables in the

    - spring.application.name
    - spring.datasource.url
    - spring.datasource.username
    - spring.datasource.password
    - app.jwt.secret
    - app.jwt.expiration