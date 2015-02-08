package healthcheck;

import codingexercise.core.PasswordValidator;

import com.codahale.metrics.health.HealthCheck;

public class CodingExerciseHealthCheck extends HealthCheck{
    private final String regex;

    public CodingExerciseHealthCheck() {
        this.regex = "(?!^[0-9]*$)(?!^[a-z]*$)^([a-z0-9]{5,12})$;";
    }

    @Override
    protected Result check() throws Exception {
    	final String goodPassword = "thisisagoodpw2";
    	final String badPassword = "thisisabadpw2!";
    	final String badPassword2 = "ab123ab123";
        final PasswordValidator validator1 = new PasswordValidator(this.regex, goodPassword);
        if (!validator1.getValid()) {
            return Result.unhealthy("Valid password failed!");
        }
        final PasswordValidator validator2 = new PasswordValidator(this.regex, badPassword);
        if (validator2.getValid()) {
            return Result.unhealthy("Bad password with special character accepted!");
        }
        final PasswordValidator validator3 = new PasswordValidator(this.regex, badPassword2);
        if (validator3.getValid()) {
            return Result.unhealthy("Bad password with repeating characters accepted!");
        }
        return Result.healthy();
    }
}