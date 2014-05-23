package br.com.vaiftech.models.responses;

import java.text.SimpleDateFormat;

import br.com.vaiftech.models.entities.Log;
import br.com.vaiftech.models.entities.LogType;

public class LogWS {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private String description;
	private String details;
	private LogType logType;
	private String registrationDate;
	
	public LogWS(Log log) {
		super();
		this.description = log.getDescription();
		this.details = log.getDetails();
		this.logType = log.getLogType();
		this.registrationDate = sdf.format(log.getRegistrationDate().getTime());
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public LogType getLogType() {
		return logType;
	}
	public void setLogType(LogType logType) {
		this.logType = logType;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

}
