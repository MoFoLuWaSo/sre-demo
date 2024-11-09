package com.cardinal.cidemo;

import java.math.BigDecimal;

public class Part {
    private String partNumber;
    private String partDescription;
    private BigDecimal partListPrice;
    private BigDecimal partCost;

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public BigDecimal getPartListPrice() {
        return partListPrice;
    }

    public void setPartListPrice(BigDecimal partListPrice) {
        this.partListPrice = partListPrice;
    }

    public BigDecimal getPartCost() {
        return partCost;
    }

    public void setPartCost(BigDecimal partCost) {
        this.partCost = partCost;
    }
}
