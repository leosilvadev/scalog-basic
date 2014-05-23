package br.com.vaiftech.models.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Project implements Serializable {

	private static final long serialVersionUID = 9139980897862296546L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;

	@NotEmpty
	private String description;
	
	@OneToMany(mappedBy="project", cascade={CascadeType.ALL}, orphanRemoval=true)
	private Set<ProjectVersion> projectVersions;
	
	@ManyToOne
	private Company company;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<ProjectVersion> getProjectVersions() {
		return projectVersions;
	}

	public void setProjectVersions(Set<ProjectVersion> projectVersions) {
		this.projectVersions = projectVersions;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}
