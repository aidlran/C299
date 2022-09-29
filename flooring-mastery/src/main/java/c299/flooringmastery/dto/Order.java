package c299.flooringmastery.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
public class Order {

    private int orderNumber;
    private String customerName;
    private StateTax state;
    private String product;
    private BigDecimal Area;
    private BigDecimal CostPerSquareFoot;
    private BigDecimal LaborCostPerSquareFoot;
    private BigDecimal MaterialCost;
    private BigDecimal LaborCost;
    private BigDecimal Tax;
    private BigDecimal Total;
    private Date orderDate;

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
