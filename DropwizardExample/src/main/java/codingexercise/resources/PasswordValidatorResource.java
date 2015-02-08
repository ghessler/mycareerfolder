package codingexercise.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import codingexercise.core.PasswordValidator;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

@Path("/validate")
@Produces(MediaType.APPLICATION_JSON)
public class PasswordValidatorResource {
    static final MetricRegistry metrics = new MetricRegistry();
	private final Counter passwordsValidated = metrics.counter(
			MetricRegistry.name(PasswordValidatorResource.class,
			"passwords-validated"));
	private final String regex;

    public PasswordValidatorResource(String regex) {
        this.regex = regex;
    }

    @GET
    @Timed
    public PasswordValidator validate(@QueryParam("password") Optional<String> password ) {
    	System.out.println("Password = " + password.get());
    	// Increment the password validation counter
    	passwordsValidated.inc();
        return new PasswordValidator(this.regex, password.get());
    }
}