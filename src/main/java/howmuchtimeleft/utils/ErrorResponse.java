package howmuchtimeleft.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ErrorResponse {
    private List<Error> errors = null;

    public ErrorResponse(ValidationException ex) {
        errors = ex.getViolations().stream().map(violation -> {
            String badValue = violation.getInvalidValue() != null ? violation.getInvalidValue().toString() : null;
            return new Error(violation.getName(), badValue);
        }).collect(Collectors.toList());
    }

    public List<Error> getErrors() {
        return errors;
    }

    public class Error {
        private String field = null;
        private String badValue = null;

        public Error(String field, String badValue) {
            this.field = field;
            this.badValue = badValue;
        }

        public String getBadValue() {
            return badValue;
        }

        public String getField() {
            return field;
        }
    }
}
