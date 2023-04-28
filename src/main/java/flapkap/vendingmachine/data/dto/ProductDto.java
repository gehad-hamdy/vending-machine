package flapkap.vendingmachine.data.dto;

public class ProductDto {
    private String name;
    private Integer amountAvailable;
    private Double cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(Integer amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
