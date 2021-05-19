package com.notice;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
@Transactional
public abstract class JPARunner implements ApplicationRunner{
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void run(ApplicationArguments args) throws Exception{
		entityManager.createNativeQuery("select 123 from dual").getSingleResult();
	}
}
