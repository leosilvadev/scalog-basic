package br.com.vaiftech.repositories;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericRepository<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Class<?> clazz;
	
	public GenericRepository(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	public Serializable save(T object){
		return getCurrentSession().save(object);
	}
	
	public void merge(T object){
		getCurrentSession().merge(object);
	}
	
	public void delete(T object){
		getCurrentSession().delete(object);
	}
	
	@SuppressWarnings("unchecked")
	public T find(Serializable id){
		return (T) getCurrentSession().get(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		Criteria criteria = createCriteria();
		return criteria.list();
	}
	
	public Criteria createCriteria(){
		return getCurrentSession().createCriteria(clazz);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
