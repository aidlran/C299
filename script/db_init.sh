#!/usr/bin/env bash

# Set up the DB on Linux (bash)

DB_NAME=c299_guess_the_number

createdb $DB_NAME
psql -f ../sql/schema.sql $DB_NAME
