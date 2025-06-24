#!/bin/bash

# ShopEase Backend Docker Clean Script

set -e

echo "🧹 Cleaning up ShopEase Backend Docker Environment..."

# Stop and remove containers, networks, and volumes
echo "📦 Stopping and removing containers..."
docker-compose -f docker-compose.dev.yml down -v

# Remove any dangling images
echo "🗑️  Removing dangling images..."
docker image prune -f

# Remove any unused networks
echo "🌐 Removing unused networks..."
docker network prune -f

# Remove any unused volumes
echo "💾 Removing unused volumes..."
docker volume prune -f

echo "✅ Cleanup completed successfully!"

echo ""
echo "🔄 To start fresh, run:"
echo "   ./scripts/docker-start.sh" 