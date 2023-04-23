package flapkap.vendingmachine.api.v1.resources;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Product Item
 */
@ApiModel(description = "Product Item")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-23T03:50:34.703457211+02:00[Africa/Cairo]")
public class ProductResource   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("user_id")
  private Long userId;

  @JsonProperty("cost")
  private Long cost;

  @JsonProperty("amount_available")
  private Integer amountAvailable;

  @JsonProperty("name")
  private String name;

  public ProductResource id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ProductResource userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  @ApiModelProperty(value = "")


  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public ProductResource cost(Long cost) {
    this.cost = cost;
    return this;
  }

  /**
   * Get cost
   * @return cost
  */
  @ApiModelProperty(value = "")


  public Long getCost() {
    return cost;
  }

  public void setCost(Long cost) {
    this.cost = cost;
  }

  public ProductResource amountAvailable(Integer amountAvailable) {
    this.amountAvailable = amountAvailable;
    return this;
  }

  /**
   * Get amountAvailable
   * @return amountAvailable
  */
  @ApiModelProperty(value = "")


  public Integer getAmountAvailable() {
    return amountAvailable;
  }

  public void setAmountAvailable(Integer amountAvailable) {
    this.amountAvailable = amountAvailable;
  }

  public ProductResource name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductResource product = (ProductResource) o;
    return Objects.equals(this.id, product.id) &&
        Objects.equals(this.userId, product.userId) &&
        Objects.equals(this.cost, product.cost) &&
        Objects.equals(this.amountAvailable, product.amountAvailable) &&
        Objects.equals(this.name, product.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, cost, amountAvailable, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductResource {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    cost: ").append(toIndentedString(cost)).append("\n");
    sb.append("    amountAvailable: ").append(toIndentedString(amountAvailable)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

