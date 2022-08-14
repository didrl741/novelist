package toyproject.novelist.annotation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WriteValidator implements ConstraintValidator<WriteLimit, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

//        value = value.strip();
//        String[] result = value.split(" ");
//
//        if (result.length == 6){
//            return true;
//        } else {
//            return false;
//        }

        if ( !value.contains("사과") || !value.contains("바나나") ||!value.contains("과자") ) {
            return false;
        }
        return true;

    }
}