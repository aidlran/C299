-- For PostgreSQL

------------
-- Set Up --
------------

-- Create blank DB
DROP DATABASE IF EXISTS c299_books;
CREATE DATABASE c299_books;

-- Connect to DB
\c c299_books;

------------
-- Tables --
------------

-- TYPES USED:

-- SERIAL  : Integer but with a sequence (auto increment) automatically created
-- VARCHAR : Varying length text with a length limit
-- CHAR    : Fixed length text
-- NUMERIC : Precision number (not floating point) used for currency

CREATE TABLE author (
	author_ID serial NOT NULL PRIMARY KEY,
	first_name varchar(25) NOT NULL,
	middle_name varchar(25) NULL,
	last_name varchar(50) NOT NULL,
	gender char(1) NULL,
	date_of_birth timestamp with time zone NOT NULL,
	date_of_death timestamp with time zone NULL
);

CREATE TABLE book (
	book_id serial NOT NULL PRIMARY KEY,
	title varchar(100) NOT NULL,
	publication_date timestamp with time zone NULL
);

CREATE TABLE format (
	format_id serial NOT NULL PRIMARY KEY,
	format_name varchar(12) NOT NULL
);

CREATE TABLE genre (
	genre_id serial NOT NULL PRIMARY KEY,
	genre_name varchar(25) NOT NULL
);

CREATE TABLE author_book (
	author_id integer NOT NULL,
	book_id integer NOT NULL,
	PRIMARY KEY (author_id, book_id),
	FOREIGN KEY (author_id) REFERENCES author(author_id),
	FOREIGN KEY (book_id) REFERENCES book(book_id)
);

CREATE TABLE BookFormat (
	book_id integer NOT NULL,
	format_id integer NOT NULL,
	price numeric(6,2) NULL, -- range: 0.00 to 9999.99
	quantity_on_hand integer NULL,
	PRIMARY KEY (book_id, format_id),
	FOREIGN KEY (book_id) REFERENCES book(book_id),
	FOREIGN KEY (format_id) REFERENCES format(format_id)
);

CREATE TABLE BookGenre (
	book_id integer NOT NULL,
	genre_id integer NOT NULL,
	PRIMARY KEY (book_id, genre_id),
	FOREIGN KEY (book_id) REFERENCES book(book_id),
	FOREIGN KEY (genre_id) REFERENCES genre(genre_id)
);
