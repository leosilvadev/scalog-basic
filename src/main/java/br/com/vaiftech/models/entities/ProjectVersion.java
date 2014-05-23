package br.com.vaiftech.models.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class ProjectVersion implements Serializable {

	private static final long serialVersionUID = -4948965201565354966L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String version;
	
	@NotNull
	@Column(updatable=false)
	private Calendar registrationDate;
	
	@OneToMany(mappedBy="projectVersion", cascade={CascadeType.ALL})
	private Set<Log> logs;
	
	@ManyToOne
	private Project project;
	
	@NotEmpty
	@Column(updatable=false, unique=true)
	private String serial;
	
	public ProjectVersion() {
		this.registrationDate=Calendar.getInstance();
		this.serial = UUID.randomUUID().toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Calendar getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Calendar registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Set<Log> getLogs() {
		return logs;
	}

	public void setLogs(Set<Log> logs) {
		this.logs = logs;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
	
}
