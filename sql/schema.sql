CREATE TABLE IF NOT EXISTS game (
	id SERIAL NOT NULL PRIMARY KEY,
	answer SMALLINT NOT NULL,
	is_finished BOOLEAN NOT NULL DEFAULT false,
	start_time TIMESTAMP NOT NULL DEFAULT 'now'
);

CREATE TABLE IF NOT EXISTS round (
	id SERIAL NOT NULL PRIMARY KEY,
	guess SMALLINT NOT NULL,
	guess_time TIMESTAMP NOT NULL DEFAULT 'now',
	exact_matches SMALLINT NOT NULL,
	partial_matches SMALLINT NOT NULL,

	game_id INT NOT NULL,
	FOREIGN KEY (game_id) REFERENCES game(id)
);
