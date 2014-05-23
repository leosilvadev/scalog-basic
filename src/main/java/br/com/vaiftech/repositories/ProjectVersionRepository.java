package br.com.vaiftech.repositories;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.vaiftech.models.entities.ProjectVersion;

@Repository
public class ProjectVersionRepository extends GenericRepository<ProjectVersion> {

	public ProjectVersionRepository() {
		super(ProjectVersion.class);
	}

	public ProjectVersion findBySerial(String serial) {
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("serial", serial));
		return (ProjectVersion) criteria.uniqueResult();
	}

}
