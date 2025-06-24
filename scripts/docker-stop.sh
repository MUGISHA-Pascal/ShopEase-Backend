#!/bin/bash

# ShopEase Backend Docker Stop Script

set -e

echo "🛑 Stopping ShopEase Backend Docker Environment..."

# Stop the development environment
docker-compose -f docker-compose.dev.yml down

echo "✅ All containers stopped successfully!"

echo ""
echo "🧹 To remove volumes and clean up completely, run:"
echo "   docker-compose -f docker-compose.dev.yml down -v"
echo ""
echo "🔄 To start again, run:"
echo "   ./scripts/docker-start.sh" 