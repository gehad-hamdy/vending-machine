package flapkap.vendingmachine.api.v1.resources;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import flapkap.vendingmachine.api.v1.resources.ConstraintViolationAllOfResource;
import flapkap.vendingmachine.api.v1.resources.ViolationResource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ConstraintViolationResource
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-23T03:50:34.703457211+02:00[Africa/Cairo]")
public class ConstraintViolationResource extends ViolationResource  {
  @JsonProperty("property")
  private String property;

  public ConstraintViolationResource property(String property) {
    this.property = property;
    return this;
  }

  /**
   * Property name
   * @return property
  */
  @ApiModelProperty(value = "Property name")


  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConstraintViolationResource constraintViolation = (ConstraintViolationResource) o;
    return Objects.equals(this.property, constraintViolation.property) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(property, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConstraintViolationResource {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    property: ").append(toIndentedString(property)).append("\n");
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
