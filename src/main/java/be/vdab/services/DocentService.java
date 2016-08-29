package be.vdab.services;

import java.math.BigDecimal;

import be.vdab.entities.Docent;
import be.vdab.repositories.DocentRepository;

public class DocentService extends AbstractService{
	private final DocentRepository docentRepository = new DocentRepository();

	public Docent read(long id) {
//		EntityManager entityManager = JPAFilter.getEntityManager();
//		try {
//			return docentRepository.read(id, entityManager);
//		} finally {
//			entityManager.close();
//		}
		return docentRepository.read(id);
	}

	public void create(Docent docent) {
//		EntityManager entityManager = JPAFilter.getEntityManager();
//		try {
//			entityManager.getTransaction().begin();
//			docentRepository.create(docent, entityManager);
//			entityManager.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			entityManager.getTransaction().rollback();
//			throw ex;
//		} finally {
//			entityManager.close();
//		}
		beginTransaction();
		docentRepository.create(docent);
		commit();
	}

	public void delete(long id) {
//		EntityManager entityManager = JPAFilter.getEntityManager();
//		try {
//			entityManager.getTransaction().begin();
//			docentRepository.delete(id, entityManager);
//			entityManager.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			entityManager.getTransaction().rollback();
//			throw ex;
//		} finally {
//			entityManager.close();
//		}
		beginTransaction();
		docentRepository.delete(id);
		commit();
	}

	public void opslag(long id, BigDecimal percentage) {
//		EntityManager entityManager = JPAFilter.getEntityManager();
//		try {
//			entityManager.getTransaction().begin();
//			docentRepository.read(id, entityManager).opslag(percentage);
//			entityManager.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			entityManager.getTransaction().rollback();
//			throw ex;
//		} finally {
//			entityManager.close();
//		}
		beginTransaction();
		docentRepository.read(id).opslag(percentage);
		commit();
	}
}