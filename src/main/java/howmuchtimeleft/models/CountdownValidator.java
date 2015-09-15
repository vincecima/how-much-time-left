package howmuchtimeleft.models;

import jodd.vtor.Check;
import jodd.vtor.ValidationContext;
import jodd.vtor.Violation;
import jodd.vtor.Vtor;
import jodd.vtor.constraint.MinLengthConstraint;
import jodd.vtor.constraint.NotNullConstraint;

import java.util.List;

public class CountdownValidator {
    private Vtor vtor = new Vtor();
    private ValidationContext vctx = new ValidationContext();

    public List<Violation> validate(Countdown countdown) {
        vctx.add(new Check("name", new NotNullConstraint()));
        vctx.add(new Check("targetDateTime", new NotNullConstraint()));
        vctx.add(new Check("name", new MinLengthConstraint(2)));

        vtor.validate(vctx, countdown);
        return vtor.getViolations();
    }
}
