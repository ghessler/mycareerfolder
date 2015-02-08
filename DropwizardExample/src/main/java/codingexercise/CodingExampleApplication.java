package codingexercise;

import healthcheck.CodingExerciseHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import codingexercise.resources.PasswordValidatorResource;

public class CodingExampleApplication  extends Application<CodingExampleConfiguration>{
    public static void main(String[] args) throws Exception {
        new CodingExampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "coding-example";
    }

    @Override
    public void initialize(Bootstrap<CodingExampleConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(CodingExampleConfiguration configuration,
                    Environment environment) {
        final PasswordValidatorResource resource = new PasswordValidatorResource(
                configuration.getPasswordRegex()
            );
        final CodingExerciseHealthCheck healthCheck =
                new CodingExerciseHealthCheck();
            environment.healthChecks().register("pw-validator", healthCheck);
            environment.jersey().register(resource);
    }

}
