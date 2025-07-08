# ShopEase Backend

ShopEase Backend is a Spring Boot-based RESTful and GraphQL API for an e-commerce platform. It provides user authentication, product management, shopping cart, purchase processing, and more. The backend is designed for scalability, security, and ease of integration with frontend clients.

## Features
- User registration, authentication (JWT), and role management
- Product CRUD operations
- Shopping cart management
- Purchase and checkout processing
- Quantity management for inventory
- RESTful API endpoints
- GraphQL support
- OAuth2 login (Google)
- Dockerized for easy deployment
- PostgreSQL database integration
- Swagger/OpenAPI documentation

## Technology Stack
- Java 21
- Spring Boot 3.4+
- Spring Data JPA
- Spring Security (JWT & OAuth2)
- PostgreSQL
- GraphQL (spring-graphql, graphql-java)
- Swagger/OpenAPI (springdoc)
- Docker & Docker Compose
- Maven

## Getting Started

### Prerequisites
- Java 21+
- Maven 3.9+
- Docker & Docker Compose (for containerized setup)
- PostgreSQL (if running locally without Docker)

### Local Development Setup
1. **Clone the repository:**
   ```bash
   git clone <repo-url>
   cd ShopEase-Backend
   ```
2. **Configure the database:**
   - Update `src/main/resources/application.properties` if needed:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
     spring.datasource.username=postgres
     spring.datasource.password=postgres
     ```
   - Ensure PostgreSQL is running and a database named `ecommerce` exists.
3. **Build and run the application:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   The backend will start on `http://localhost:8081` by default.

### Running with Docker
1. **Build and start all services:**
   ```bash
   docker-compose up --build
   ```
   - The backend will be available at `http://localhost:8080`
   - PostgreSQL at `localhost:5432` (user: `shopease_user`, password: `shopease_password`)
   - pgAdmin at `http://localhost:5050` (email: `admin@shopease.com`, password: `admin123`)

2. **For development mode:**
   ```bash
   docker-compose -f docker-compose.dev.yml up --build
   ```

### Environment Variables
- `GOOGLE_CLIENT_ID` and `GOOGLE_CLIENT_SECRET` (for Google OAuth2)
- See `application.properties` for more configuration options

## API Overview

### Authentication
- `POST /api/auth/signin` — User login (returns JWT)
- `POST /api/auth/signup` — User registration

### Users
- `GET /api/users` — List users (paginated)
- `GET /api/users/{userId}` — Get user by ID
- `PUT /api/users/{userId}` — Update user
- `DELETE /api/users/{userId}` — Delete user

### Products
- `POST /api/products/add` — Add product
- `GET /api/products/get-all` — List all products
- `GET /api/products/get/{code}` — Get product by code

### Shopping Cart
- `POST /api/cart/add` — Add item to cart
- `GET /api/cart/get-all` — View cart items

### Purchases
- `POST /api/purchases/checkout` — Checkout cart
- `POST /api/purchases/cart/{email}` — Add multiple items to cart
- `GET /api/purchases/get-all` — List all purchases

### Quantities
- `POST /api/quantities/add` — Add quantity
- `GET /api/quantities/get-all` — List all quantities

### GraphQL
- GraphQL endpoint: `/graphql`
- GraphiQL UI: `/graphiql` (if enabled)

### API Documentation
- Swagger UI: `/swagger-ui.html` (if enabled)

## Development
- Source code: `src/main/java/com/starter/mugisha/`
- Configuration: `src/main/resources/application.properties`
- Scripts: `scripts/` (for Docker helpers)
- Tests: `src/test/java/com/starter/mugisha/`

## Testing
- Run tests with:
  ```bash
  mvn test
  ```

## License
This project is for educational/demo purposes. Please adapt for production use. 