#!/usr/bin/env bash

# Set up the DB on Linux (bash)

DB_NAME=c299_superhero_sightings

createdb $DB_NAME
psql -f ../sql/schema.sql $DB_NAME
