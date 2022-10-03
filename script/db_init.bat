:: Run tests on Windows

:: Windows versions of scripts are untested

set DB_NAME=c299_guess_the_number

createdb %DB_NAME%
psql -f ../sql/schema.sql %DB_NAME%
