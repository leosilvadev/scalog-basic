package br.com.vaiftech.models.requests;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.vaiftech.models.entities.Log;

public class LogSaveRequest {

	@NotEmpty(message="Login invalido")
	private String companyLogin;
	
	@NotEmpty(message="Senha invalida")
	private String companyPassword;

	@NotEmpty(message="Versao de projeto invalida")
	private String projectVersionSerial;

	@NotNull(message="Log invalido")
	private Log log;

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public String getProjectVersionSerial() {
		return projectVersionSerial;
	}

	public void setProjectVersionSerial(String projectVersionSerial) {
		this.projectVersionSerial = projectVersionSerial;
	}

	public String getCompanyLogin() {
		return companyLogin;
	}

	public void setCompanyLogin(String companyLogin) {
		this.companyLogin = companyLogin;
	}

	public String getCompanyPassword() {
		return companyPassword;
	}

	public void setCompanyPassword(String companyPassword) {
		this.companyPassword = companyPassword;
	}
	
}
