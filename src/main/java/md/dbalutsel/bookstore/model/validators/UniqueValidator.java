package md.dbalutsel.bookstore.model.validators;

import org.hibernate.Session;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    private Unique unique;

    @PersistenceContext
    private Session session;

    @Override
    public void initialize(Unique unique) {
        this.unique = unique;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW, readOnly = true)
    @Override
    public boolean isValid(Object fieldValue, ConstraintValidatorContext constraintValidatorContext) {
        if (fieldValue instanceof CharSequence) {
            fieldValue = "\'" + fieldValue + "\'";
        }

        return session
                .createQuery("from " + unique.entityType().getSimpleName() +
                                        " where " + unique.property() + "=" + fieldValue)
                .getResultList().isEmpty();
    }
}