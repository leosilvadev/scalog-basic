package br.com.vaiftech.models.exceptions.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

public class ValidationError {
	
	private List<FieldError> errors = new ArrayList<>();

    public ValidationError() {}

    public void addFieldError(FieldError fieldError) {
    	getErrors().add(fieldError);
    }

	public List<FieldError> getErrors() {
		if(errors==null) errors = new ArrayList<>();
		return errors;
	}

	public void setErrors(List<FieldError> errors) {
		this.errors = errors;
	}
}
