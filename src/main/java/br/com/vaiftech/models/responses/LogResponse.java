package br.com.vaiftech.models.responses;

public class LogResponse {
	
	private int status;
	private String mensagem;

	public LogResponse() {}

	public LogResponse(int status, String mensagem) {
		this.status = status;
		this.mensagem = mensagem;
	}
	
	public int getStatus() {
		return status;
	}
	public String getMensagem() {
		return mensagem;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
