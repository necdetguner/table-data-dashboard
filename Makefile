DOCKER_COMPOSE_DIR=./.docker
DOCKER_COMPOSE_FILE=$(DOCKER_COMPOSE_DIR)/docker-compose.yaml
DOCKER_COMPOSE=docker-compose -f $(DOCKER_COMPOSE_FILE) --project-directory $(DOCKER_COMPOSE_DIR)

.PHONY: build
build:
	docker-compose -f $(DOCKER_COMPOSE_FILE) build --no-cache

.PHONY: up
up:
	docker-compose -f $(DOCKER_COMPOSE_FILE) up -d --force-recreate

.PHONY: down
down:
	docker-compose -f $(DOCKER_COMPOSE_FILE) down

.PHONY: restart
restart: down up

.PHONY: logs
logs:
	docker-compose -f $(DOCKER_COMPOSE_FILE) logs -f

.PHONY: clean
clean:
	docker-compose -f $(DOCKER_COMPOSE_FILE) down -v --remove-orphans
	rm -f target/success-board-0.0.1-SNAPSHOT.jar

.PHONY: clean-build-up
clean-build-up: clean
	mvn clean package && \
    rm -rf ./.docker/mysql/data && \
	$(MAKE) build && \
	$(MAKE) up

.PHONY: help
help:
	@echo "Available targets:"
	@echo "  make build       - Build Docker containers"
	@echo "  make up          - Run Docker containers in the background"
	@echo "  make down        - Stop and remove Docker containers"
	@echo "  make restart     - Restart Docker containers"
	@echo "  make logs        - View logs for Docker containers"
	@echo "  make clean       - Clean up Docker resources (volumes, networks, etc.)"
	@echo "  make clean-build - Clean and build"
	@echo "  make help        - Show this help message"

.DEFAULT_GOAL := help