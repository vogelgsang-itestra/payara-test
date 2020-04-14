#!/bin/bash
################################
# execute after server started successfully
# this script create some test data and dispaly the result in the db
################################
# parameters

# should be the same as in setup.sh
BASE_PORT=61210

################################

POSTGRES_PORT=5432
HTTP_PORT=$((BASE_PORT + 80))

################################

curl localhost:${HTTP_PORT}/entity/create \
  -H "content-type: application/json; charset=utf-8" \
  -d '{"value": "Entity", "assignments": ["ASGMT_1", "ASGMT_2"]}'

psql -U postgres -p $POSTGRES_PORT -c "SELECT * FROM assignment_history where entity_id is null"
