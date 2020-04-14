#!/bin/bash
################################
# setup script for postgres and payara
# * add a new user to postgres
# * create a new domain and connect to postgres
# adjust the parameters below
################################
# parameters

PAYARA_HOME=../payara
BASE_PORT=61210
# DOMAIN must not contain -
DOMAIN=payara_test

################################

ASADMIN="$PAYARA_HOME/bin/asadmin"
ADMIN_PORT=$((BASE_PORT + 48))
POSTGRES_PORT=5432

################################

psql -U postgres -p $POSTGRES_PORT -c "CREATE USER ${DOMAIN} WITH PASSWORD '${DOMAIN}' LOGIN SUPERUSER INHERIT CREATEDB CREATEROLE REPLICATION;"

"${ASADMIN}" create-domain --nopassword --savemasterpassword true --portbase $BASE_PORT $DOMAIN
"${ASADMIN}" start-domain $DOMAIN
"${ASADMIN}" --port $ADMIN_PORT create-jdbc-connection-pool --restype javax.sql.DataSource --datasourceclassname org.postgresql.ds.PGSimpleDataSource --property "User=${DOMAIN}:Password=${DOMAIN}:Url=jdbc\:postgresql\://localhost\:${POSTGRES_PORT}/postgres" ${DOMAIN}.postgres.pool
"${ASADMIN}" --port $ADMIN_PORT create-jdbc-resource --connectionpoolid ${DOMAIN}.postgres.pool jdbc/db.${DOMAIN}
"${ASADMIN}" --port $ADMIN_PORT ping-connection-pool ${DOMAIN}.postgres.pool
"${ASADMIN}" stop-domain $DOMAIN