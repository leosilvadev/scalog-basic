package br.com.vaiftech.repositories;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.vaiftech.models.entities.SystemUser;

@Repository
@Transactional
public class UserRepository extends GenericRepository<SystemUser> {

	public UserRepository() {
		super(SystemUser.class);
	}

	public boolean isInvalidUser(SystemUser user) {
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("username", user.getUsername()));
		criteria.add(Restrictions.eq("password", user.getPassword()));
		boolean invalidUser = (criteria.uniqueResult() == null);
		return invalidUser;
	}
	
}
