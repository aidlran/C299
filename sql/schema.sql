-- Database creation script for PSQL

-- For case insensitive text
CREATE EXTENSION IF NOT EXISTS citext;

CREATE TABLE IF NOT EXISTS character_type (
	id SMALLSERIAL NOT NULL PRIMARY KEY,
	name CITEXT NOT NULL
);

INSERT INTO character_type (name) VALUES
	('hero'),
	('villain');

CREATE TABLE IF NOT EXISTS location (
	id SERIAL NOT NULL PRIMARY KEY,
	name TEXT NOT NULL,
	longitude DOUBLE PRECISION NOT NULL,
	latitude DOUBLE PRECISION NOT NULL,
	description TEXT NULL,
	street TEXT NULL,
	postal_code TEXT NULL
);

CREATE TABLE IF NOT EXISTS contact_details (
	id SERIAL NOT NULL PRIMARY KEY,
	location_id INT NULL,
	phone_number TEXT NULL,
	email_address TEXT NULL,
	note TEXT NULL,

	FOREIGN KEY (location_id) REFERENCES location(id)
);

CREATE TABLE IF NOT EXISTS character (
	id SERIAL NOT NULL PRIMARY KEY,
	type_id SMALLINT NOT NULL,
	contact_details_id INT NULL,
	name TEXT NOT NULL,
	description TEXT NULL,
	superpower TEXT NULL,

	FOREIGN KEY (type_id) REFERENCES character_type(id),
	FOREIGN KEY (contact_details_id) REFERENCES contact_details(id)
);

CREATE TABLE IF NOT EXISTS organisation (
	id SERIAL NOT NULL PRIMARY KEY,
	contact_details_id INT NULL,
	name TEXT NOT NULL,
	description TEXT NULL,

	FOREIGN KEY (contact_details_id) REFERENCES contact_details(id)
);

CREATE TABLE IF NOT EXISTS organisation_member (
	organisation_id INT NOT NULL,
	character_id INT NOT NULL,

	FOREIGN KEY (organisation_id) REFERENCES organisation(id),
	FOREIGN KEY (character_id) REFERENCES character(id)
);

CREATE TABLE IF NOT EXISTS sighting (
	id SERIAL NOT NULL PRIMARY KEY,
	character_id INT NOT NULL,
	location_id INT NOT NULL,
	timestamp TIMESTAMP NOT NULL DEFAULT now(),
	description TEXT NULL,

	FOREIGN KEY (character_id) REFERENCES character(id),
	FOREIGN KEY (location_id) REFERENCES location(id)
);
