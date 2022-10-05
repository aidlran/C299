package c299.guessthenumber.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Round {
    private int id;
    private int guess;
    private Date guessTime;
    private int exactMatches;
    private int partialMatches;
    private int gameId;
}
