package flapkap.vendingmachine.api.v1.resources;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Violation in API request
 */
@ApiModel(description = "Violation in API request")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-23T03:50:34.703457211+02:00[Africa/Cairo]")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = ConstraintViolationResource.class, name = "ConstraintViolation"),
})

public class ViolationResource   {
  @JsonProperty("type")
  private String type;

  @JsonProperty("message")
  private String message;

  public ViolationResource type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Violation type
   * @return type
  */
  @ApiModelProperty(value = "Violation type")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ViolationResource message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Violation message
   * @return message
  */
  @ApiModelProperty(value = "Violation message")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ViolationResource violation = (ViolationResource) o;
    return Objects.equals(this.type, violation.type) &&
        Objects.equals(this.message, violation.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ViolationResource {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

