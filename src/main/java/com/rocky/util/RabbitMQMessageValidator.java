package com.rocky.util;

import com.rocky.model.RabbitMQMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;

@Service
public class RabbitMQMessageValidator {

    @Value("${validCustomerTypes}")
    private List<String> validCustomerTypes;

    public Boolean isValidMessage(RabbitMQMessage rabbitMQMessage) {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        if (rabbitMQMessage != null &&
           validator.validate(rabbitMQMessage).size() == 0 &&
           validateFields(rabbitMQMessage)) {

            return Boolean.TRUE;
        }
        validator.validate(rabbitMQMessage).stream().forEach(RabbitMQMessageValidator::printMessageValidations);
        return Boolean.FALSE;
    }

    private Boolean validateFields(RabbitMQMessage rabbitMQMessage) {

        if(!CommonUtil.isValidEmail(rabbitMQMessage.getCustomerContact())){
            return Boolean.FALSE;
        }
        if(!validCustomerTypes.contains(rabbitMQMessage.getCustomerType())){
            return Boolean.FALSE;
        }
        if(rabbitMQMessage.getCustomerID().length()!=10){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private static void printMessageValidations(ConstraintViolation<RabbitMQMessage> violation) {
        System.out.println("Validation Constraint Failure -->" + violation.getMessage());
    }
}
