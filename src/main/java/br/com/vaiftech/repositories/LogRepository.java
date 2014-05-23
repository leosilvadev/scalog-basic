package br.com.vaiftech.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.vaiftech.models.entities.Log;

@Repository
public class LogRepository extends GenericRepository<Log> {

	public LogRepository() {
		super(Log.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Log> findAll(String projectVerionSerial) {
		Criteria criteria = createCriteria();
		criteria.createAlias("projectVersion", "projectVersion");
		criteria.add(Restrictions.eq("serial", projectVerionSerial));
		return criteria.list();
	}

}
