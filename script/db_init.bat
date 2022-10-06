:: Set up the DB on Windows

:: Windows versions of scripts are untested

set DB_NAME=c299_superhero_sightings

createdb %DB_NAME%
psql -f ../sql/schema.sql %DB_NAME%
