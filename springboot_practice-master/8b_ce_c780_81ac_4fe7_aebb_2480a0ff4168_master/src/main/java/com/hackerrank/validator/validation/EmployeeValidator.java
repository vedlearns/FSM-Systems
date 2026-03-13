package com.hackerrank.validator.validation;

import com.hackerrank.validator.model.Employee;
import org.springframework.expression.ParseException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class EmployeeValidator implements Validator {

    // Regex pattern for checking 10 digits
    private static final Pattern MOBILE_NUMBER_PATTERN = Pattern.compile("\\d{10}");
    // Date format for YYYY-MM-DD check
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    @Override
    public boolean supports(Class<?> aClass) {
        return Employee.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object employeeObject, Errors errors) {
        // write validation logic here

        Employee employee = (Employee) employeeObject;

        // --- 1. fullName ---
        // Constraints: 1. check if it's null or empty
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "fullName",
                "fullName.mandatory",
                "The fullName is a mandatory field"
        );
//        if(employee.getFullName()==null || employee.getFullName().trim().isEmpty()){
//            errors.rejectValue("fullName","","The fullName is a mandatory field");
//        }

        // --- 2. mobileNumber ---
        Long mobileNumberLong = employee.getMobileNumber();

// Constraint: 1. check if it's null
        if (mobileNumberLong == null) {
            errors.rejectValue("mobileNumber", "mobileNumber.mandatory", "The mobileNumber is a mandatory field");
        } else {
            // Convert Long to String for digit count check
            String mobileNumberString = mobileNumberLong.toString();

            // Constraint: 2. check if it's not of 10 digits
            // The previous MOBILE_NUMBER_PATTERN check (e.g., "\\d{10}") is suitable for Strings.
            // Checking the length is simpler and more direct for a known Long value.
            if (mobileNumberString.length() != 10) {
                errors.rejectValue("mobileNumber", "mobileNumber.invalid", "The mobileNumber is a mandatory field");
            }
        }

        // --- 3. emailId ---
        String emailId = employee.getEmailId();

        // Constraint: 1. check if it's null or empty
        if (emailId == null || emailId.trim().isEmpty()) {
            errors.rejectValue("emailId", "emailId.mandatory", "The emailId is a mandatory field");
        } else {
            // Constraint: 2. check if it doesn't contain the @ sign
            if (!emailId.contains("@")) {
                errors.rejectValue("emailId", "emailId.format", "The emailId should be in a valid email format");
            }
        }

        // --- 4. dateOfBirth ---
        String dateOfBirth = employee.getDateOfBirth();

        // Constraint: 1. check if it's null or empty
        if (dateOfBirth == null || dateOfBirth.trim().isEmpty()) {
            errors.rejectValue("dateOfBirth", "dateOfBirth.mandatory", "The dateOfBirth is a mandatory field");
        } else {
            // Constraint: 2. check if it's not in YYYY-MM-DD format
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            sdf.setLenient(false); // Crucial: Prevents invalid dates like 2023-02-30 from being parsed

            try {
                sdf.parse(dateOfBirth);
            } catch (ParseException | java.text.ParseException e) {
                errors.rejectValue("dateOfBirth", "dateOfBirth.format", "The dateOfBirth should be in YYYY-MM-DD format");
            }
        }

//        if(employee.getDateOfBirth()==null ||employee.getDateOfBirth().trim().isEmpty()){
//            errors.rejectValue("dateOfBirth", "","The dateOfBirth is a mandatory field");
//        }else {
//            try{
//                DateTimeFormatter fmt = DateTimeFormatter.ofPattern(DATE_FORMAT);
//                LocalDate.parse(employee.getDateOfBirth(),fmt);
//            }catch (Exception e){
//                errors.rejectValue("dateOfBirth","","The dateOfBirth should be in YYYY-MM-DD format");
//            }
//        }

    }
}
