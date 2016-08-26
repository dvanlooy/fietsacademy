package be.vdab.repositories;

import javax.persistence.EntityManager;

import be.vdab.entities.Docent;
import be.vdab.filters.JPAFilter;

public class DocentRepository {
	public Docent read(long id) {
		EntityManager entityManager = JPAFilter.getEntityManager();
		try {
			return entityManager.find(Docent.class, id);
		} finally {
			entityManager.close();
		}
	}
}