:: Run tests on Windows

:: Windows versions of scripts are untested

set DB_NAME=c299_guess_the_number_test

:: Set up test DB
createdb %DB_NAME%
psql -f ../sql/schema.sql %DB_NAME%

:: Delete test DB
dropdb %DB_NAME%
