package c299.guessthenumber.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Round {

    private Integer id;

    private Integer guess;

	@JsonFormat(pattern="dd MMM yyyy hh:mm:ss")
    private Date guessTime;

    private Integer exactMatches;
    private Integer partialMatches;

    private Integer gameId;
}
