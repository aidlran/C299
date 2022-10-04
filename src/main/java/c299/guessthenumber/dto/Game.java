package c299.guessthenumber.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Game {

    private int id;

    private Integer answer;

    private boolean finished;

	@JsonFormat(pattern="dd MMM yyyy hh:mm:ss")
    private Date startTime;
}
