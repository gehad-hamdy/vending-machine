package flapkap.vendingmachine.api.v1.resources;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import flapkap.vendingmachine.api.v1.resources.ViolationResource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ErrorResource
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-23T03:50:34.703457211+02:00[Africa/Cairo]")
public class ErrorResource   {
  @JsonProperty("code")
  private String code;

  @JsonProperty("message")
  private String message;

  @JsonProperty("details")
  private String details;

  @JsonProperty("violations")
  @Valid
  private List<ViolationResource> violations = null;

  public ErrorResource code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Error code if any.
   * @return code
  */
  @ApiModelProperty(value = "Error code if any.")


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ErrorResource message(String message) {
    this.message = message;
    return this;
  }

  /**
   * General error message
   * @return message
  */
  @ApiModelProperty(value = "General error message")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ErrorResource details(String details) {
    this.details = details;
    return this;
  }

  /**
   * Error details.
   * @return details
  */
  @ApiModelProperty(value = "Error details.")


  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public ErrorResource violations(List<ViolationResource> violations) {
    this.violations = violations;
    return this;
  }

  public ErrorResource addViolationsItem(ViolationResource violationsItem) {
    if (this.violations == null) {
      this.violations = new ArrayList<>();
    }
    this.violations.add(violationsItem);
    return this;
  }

  /**
   * Get violations
   * @return violations
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<ViolationResource> getViolations() {
    return violations;
  }

  public void setViolations(List<ViolationResource> violations) {
    this.violations = violations;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorResource error = (ErrorResource) o;
    return Objects.equals(this.code, error.code) &&
        Objects.equals(this.message, error.message) &&
        Objects.equals(this.details, error.details) &&
        Objects.equals(this.violations, error.violations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, details, violations);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorResource {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
    sb.append("    violations: ").append(toIndentedString(violations)).append("\n");
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

