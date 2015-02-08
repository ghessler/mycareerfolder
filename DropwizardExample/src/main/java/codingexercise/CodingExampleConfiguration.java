package codingexercise;

import io.dropwizard.Configuration;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Basic configuration class for the password validator service
 *
 */
public class CodingExampleConfiguration extends Configuration {
    @NotEmpty
    private String passwordRegex;

    @JsonProperty
    public String getPasswordRegex() {
        return passwordRegex;
    }

    @JsonProperty
    public void setPasswordRegex(String passwordRegex) {
        this.passwordRegex = passwordRegex;
    }
}
