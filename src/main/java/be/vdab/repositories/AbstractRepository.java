package be.vdab.repositories;

import javax.persistence.EntityManager;

import be.vdab.filters.JPAFilter;

public abstract class AbstractRepository {

	protected EntityManager getEntityManager() {
		return JPAFilter.getEntityManager();
	}
}
