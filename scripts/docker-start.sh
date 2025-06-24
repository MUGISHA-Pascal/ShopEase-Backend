#!/bin/bash

# ShopEase Backend Docker Start Script

set -e

echo "🚀 Starting ShopEase Backend Docker Environment..."

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    echo "❌ Docker is not running. Please start Docker first."
    exit 1
fi

# Create init-scripts directory if it doesn't exist
mkdir -p init-scripts

# Start the development environment
echo "📦 Building and starting containers..."
docker-compose -f docker-compose.dev.yml up --build -d

echo "⏳ Waiting for services to be ready..."
sleep 10

# Check if services are healthy
echo "🔍 Checking service health..."

# Check PostgreSQL
if docker-compose -f docker-compose.dev.yml exec -T postgres pg_isready -U shopease_user -d shopease_dev > /dev/null 2>&1; then
    echo "✅ PostgreSQL is ready"
else
    echo "⚠️  PostgreSQL is still starting up..."
fi

# Check Spring Boot application
if curl -f http://localhost:8080/actuator/health > /dev/null 2>&1; then
    echo "✅ Spring Boot application is ready"
else
    echo "⚠️  Spring Boot application is still starting up..."
fi

echo ""
echo "🎉 ShopEase Backend is starting up!"
echo ""
echo "📋 Service URLs:"
echo "   🌐 Application: http://localhost:8080"
echo "   🗄️  pgAdmin: http://localhost:5050"
echo "   📊 Redis: localhost:6379"
echo ""
echo "🔧 Useful commands:"
echo "   📝 View logs: docker-compose -f docker-compose.dev.yml logs -f"
echo "   🛑 Stop services: docker-compose -f docker-compose.dev.yml down"
echo "   🔄 Restart: docker-compose -f docker-compose.dev.yml restart"
echo ""
echo "📚 Database credentials:"
echo "   Host: localhost:5432"
echo "   Database: shopease_dev"
echo "   Username: shopease_user"
echo "   Password: shopease_password"
echo ""
echo "🔍 pgAdmin credentials:"
echo "   Email: admin@shopease.com"
echo "   Password: admin123" 