# Trader G

## Setting up

1. Clone repo
2. Setup local database and api

** Use WSL as the mssql docker image used requires linux.

## Running Database locally

1. cd into /database
2. Run `chmod +x /scripts/db-init.sh` to make the script executable
3. cd out into the root folder
4. Run `docker compose up`

Database should be running now. To stop the database but preserve data, click `Ctrl+c`. To stop and clear all data run `docker compose down --volumes`.

The database will run on port `1433`. This same port is forwaded to the machine hosting the docker image.
