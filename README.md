# Check out The Project Demo Here:
https://docs.google.com/presentation/d/1RrRe4xi-R6RlZIFk_-yq1CUIz6aus2wF

# Online Shopping Microservices Application

## Overview

This Spring Boot application simulates an online shopping platform using a microservices architecture. The system is designed to handle various aspects of online shopping, such as product management, user authentication, user management, order processing, and more. Each functionality is implemented as an independent microservice to promote scalability, maintainability, and flexibility.

## Microservices

1. **Product Service:**
   - Manages the product , including product details, categories, and availability.

2. **User Service:**
   - Handles user profile management.

4. **Order Service:**
   - Processes user orders, and manages notifications.

5. **Inventory Service:**
   - Handles quantity processing for completed orders.

## Architecture

The application follows a microservices architecture, allowing each service to run independently, communicate through APIs, and be deployed and scaled individually.

![Microservices Architecture](docs/microservices_architecture.png)

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later
- Docker and Docker Compose (optional for containerization)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/online-shopping-microservices.git
