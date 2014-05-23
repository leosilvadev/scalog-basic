package br.com.vaiftech.models.responses;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.vaiftech.models.entities.Log;

public class LogListResponse {
	
	private List<LogWS> logs;
	
	public LogListResponse(Collection<Log> logs) {
		this.logs = new ArrayList<>();
		for(Log log : logs){
			this.logs.add(new LogWS(log));
		}
	}

	public List<LogWS> getLogs() {
		return logs;
	}

	public void setLogs(List<LogWS> logs) {
		this.logs = logs;
	}
	
}
