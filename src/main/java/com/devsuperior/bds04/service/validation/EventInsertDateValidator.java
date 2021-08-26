package com.devsuperior.bds04.service.validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import com.devsuperior.bds04.controllers.exceptions.FieldMessage;
import com.devsuperior.bds04.dto.EventDTO;

//classe que retorna para bean
public class EventInsertDateValidator implements ConstraintValidator<EventDateValid, EventDTO> {
	
	
	
	@Override
	public void initialize(EventDateValid ann) {
	}
	
	
	//retorna verdadeiro o valor para a bean
	@Override
	public boolean isValid(EventDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		
		LocalDate dateVerificada = dto.getDate();
		LocalDate hoje = LocalDate.now();
		
		if(dateVerificada.compareTo(hoje) <= 0) {
			list.add(new FieldMessage("date", "A data do evento não pode ser passada"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty(); 
	}


}

