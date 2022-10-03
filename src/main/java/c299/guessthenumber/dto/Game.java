package c299.guessthenumber.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Game {
    private int id;
	private int answer;
    private boolean isFinished;
    private Date startTime;
}
