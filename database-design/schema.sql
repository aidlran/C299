-- UPDATE_TIMESTAMP function
-- to implement ON UPDATE CURRENT_TIMESTAMP functionality like from MySQL
CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
	NEW.updated = now(); 
	RETURN NEW;
END;
$$ language 'plpgsql';



CREATE TABLE IF NOT EXISTS user_account (
	id SERIAL NOT NULL PRIMARY KEY,
	username TEXT NOT NULL,
	password_hash TEXT NOT NULL,
	added TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
	updated TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);

CREATE TRIGGER update_timestamp_user_account BEFORE UPDATE
	ON user_account FOR EACH ROW EXECUTE PROCEDURE 
	update_timestamp();



CREATE TABLE IF NOT EXISTS subscription (
	id SERIAL NOT NULL PRIMARY KEY,
	label TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS user_subscription (
	user_id INT NOT NULL PRIMARY KEY,
	subscription_id INT NOT NULL,
	started TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
	expiration TIMESTAMP WITH TIME ZONE NOT NULL,

	FOREIGN KEY (user_id) REFERENCES user_account(id),
	FOREIGN KEY (subscription_id) REFERENCES subscription(id)
);



CREATE TABLE IF NOT EXISTS series (
	id SERIAL NOT NULL PRIMARY KEY,
	title TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS season (
	id SERIAL NOT NULL PRIMARY KEY,
	series_id INT NOT NULL,
	series_order SMALLINT NULL,
	title TEXT null,

	FOREIGN KEY (series_id) REFERENCES series(id),
	UNIQUE (series_id, series_order)
);



CREATE TABLE IF NOT EXISTS episode (
	id SERIAL NOT NULL PRIMARY KEY,
	season_id INT NOT NULL,
	season_order SMALLINT NULL,
	inode INT NOT NULL UNIQUE,
	is_movie BOOLEAN NOT NULL,
	age_rating SMALLINT NULL,
	title TEXT NULL,
	release_date date NULL,
	added TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),

	FOREIGN KEY (season_id) REFERENCES season(id),
	UNIQUE (season_id, season_order)
);

CREATE TABLE IF NOT EXISTS user_episode_rating (
	user_id INT NOT NULL,
	episode_id INT NOT NULL,
	rating SMALLINT NOT NULL,

	PRIMARY KEY (user_id, episode_id),
	FOREIGN KEY (user_id) REFERENCES user_account(id),
	FOREIGN KEY (episode_id) REFERENCES episode(id)
);
