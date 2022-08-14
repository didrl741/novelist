package toyproject.novelist.annotation;


import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WriteValidator.class)
public @interface WriteLimit {
    String message() default "단어가 다 포함됐는지 확인해주세요.";

    Class[] groups() default {};
    Class[] payload() default {};
}
