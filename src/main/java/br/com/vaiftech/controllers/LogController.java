package br.com.vaiftech.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.vaiftech.controllers.exceptions.BadRequestException;
import br.com.vaiftech.controllers.exceptions.InvalidProjectVersionSerialException;
import br.com.vaiftech.models.entities.Log;
import br.com.vaiftech.models.entities.SystemUser;
import br.com.vaiftech.models.requests.LogSaveRequest;
import br.com.vaiftech.models.responses.LogListResponse;
import br.com.vaiftech.models.responses.LogResponse;
import br.com.vaiftech.repositories.LogRepository;
import br.com.vaiftech.repositories.UserRepository;
import br.com.vaiftech.services.LogService;

@Controller
public class LogController {

	@Autowired LogService logService;
	@Autowired LogRepository logRepository;
	@Autowired UserRepository userRepository;
	
	@RequestMapping(value="/api/logs/{projectVerionSerial}", produces={"application/json"})
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public LogListResponse list(@PathVariable("projectVerionSerial") String projectVerionSerial){
		return new LogListResponse(logService.findAll(projectVerionSerial));
	}
	
	@RequestMapping(value="/api/logs/find/{id}", produces={"application/json"})
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody Log find(@PathVariable("id") long id){
		return logService.find(id);
	}

	@RequestMapping(
			value="/api/logs/save", 
			method=RequestMethod.POST,
			consumes={"application/json"},
			produces={"application/json"}
	)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody LogResponse save(@Valid @RequestBody LogSaveRequest request){
		try {
			logService.save(request);
			return new LogResponse(HttpStatus.OK.value(), "Log salvo com sucesso");
			
		} catch (InvalidProjectVersionSerialException e) {
			e.printStackTrace();
			throw new BadRequestException(e.getLocalizedMessage());
			
		}
	}
	
	@RequestMapping(value="/api/logs/remove/{id}", produces={"application/json"})
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody LogResponse remove(@PathVariable("id") String id){
		logRepository.delete(logRepository.find(id));
		return new LogResponse(200, "Log removed succefully!");
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public @ResponseBody LogResponse createUser(){
		SystemUser user = new SystemUser();
		user.setUsername("root");
		user.setPassword("root");
		userRepository.save(user);
		return new LogResponse(200, "User saved!");
	}
	
}
