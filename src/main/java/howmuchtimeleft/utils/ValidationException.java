package howmuchtimeleft.utils;

import jodd.vtor.Violation;

import java.util.List;

public class ValidationException extends Exception {
    private List<Violation> violations = null;

    public ValidationException(List<Violation> violations) {
        this.violations = violations;
    }

    public List<Violation> getViolations() {
        return violations;
    }
}
