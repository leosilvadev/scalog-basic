package br.com.vaiftech.services;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vaiftech.controllers.exceptions.InvalidProjectVersionSerialException;
import br.com.vaiftech.models.entities.Log;
import br.com.vaiftech.models.entities.ProjectVersion;
import br.com.vaiftech.models.requests.LogSaveRequest;
import br.com.vaiftech.repositories.LogRepository;
import br.com.vaiftech.repositories.ProjectVersionRepository;

@Service
public class LogService extends SystemService {

	@Autowired LogRepository logRepository;
	@Autowired ProjectVersionRepository projectVersionRepository;
	
	
	@Transactional
	public Serializable save(LogSaveRequest request) throws InvalidProjectVersionSerialException{
		Log log = request.getLog();
		ProjectVersion projectVersion = validateAndFindProjectVersion(request.getProjectVersionSerial());
		log.setProjectVersion(projectVersion);
		return logRepository.save(log);
	}

	private ProjectVersion validateAndFindProjectVersion(String serial) throws InvalidProjectVersionSerialException {
		if(serial==null || serial.isEmpty()) throw new InvalidProjectVersionSerialException("Versão de projeto inválida");
		ProjectVersion projectVersion = projectVersionRepository.findBySerial(serial);
		if(projectVersion==null) throw new InvalidProjectVersionSerialException("Versão de projeto inválida");
		return projectVersion;
	}

	@Transactional(readOnly=true)
	public Collection<Log> findAll(String projectVerionSerial) {
		return logRepository.findAll();
	}

	@Transactional(readOnly=true)
	public Log find(long id) {
		return logRepository.find(id);
	}
	
	
}
