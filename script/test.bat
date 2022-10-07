:: Run tests on Windows

:: Windows versions of scripts are untested

set DB_NAME=c299_superhero_sightings_test

:: Go to project root
cd ../

:: Set up test DB
createdb %DB_NAME%
psql -f ../sql/schema.sql %DB_NAME%

:: Run tests
mvn test

:: Delete test DB
dropdb %DB_NAME%
