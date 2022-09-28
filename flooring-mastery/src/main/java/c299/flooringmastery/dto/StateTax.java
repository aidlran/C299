package c299.flooringmastery.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StateTax {
	String stateCode;
	String stateName;
	BigDecimal taxRate;
}
