package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.OptimisticLockException;
import javax.persistence.RollbackException;

import be.vdab.entities.Docent;
import be.vdab.exceptions.DocentBestaatAlException;
import be.vdab.exceptions.RecordAangepastException;
import be.vdab.repositories.CampusRepository;
import be.vdab.repositories.DocentRepository;
import be.vdab.valueobjects.AantalDocentenPerWedde;
import be.vdab.valueobjects.VoornaamEnId;

public class DocentService extends AbstractService {
	private final DocentRepository docentRepository = new DocentRepository();
	private final CampusRepository campusRepository = new CampusRepository();

	public Docent read(long id) {
		// EntityManager entityManager = JPAFilter.getEntityManager();
		// try {
		// return docentRepository.read(id, entityManager);
		// } finally {
		// entityManager.close();
		// }
		return docentRepository.read(id);
	}

	public void create(Docent docent) {
		// EntityManager entityManager = JPAFilter.getEntityManager();
		// try {
		// entityManager.getTransaction().begin();
		// docentRepository.create(docent, entityManager);
		// entityManager.getTransaction().commit();
		// } catch (RuntimeException ex) {
		// entityManager.getTransaction().rollback();
		// throw ex;
		// } finally {
		// entityManager.close();
		// }
		if (docentRepository.findByRijksRegisterNr(docent.getRijksRegisterNr()) != null) {
			throw new DocentBestaatAlException();
		}
		beginTransaction();
		docentRepository.create(docent);
		commit();
	}

	public void delete(long id) {
		// EntityManager entityManager = JPAFilter.getEntityManager();
		// try {
		// entityManager.getTransaction().begin();
		// docentRepository.delete(id, entityManager);
		// entityManager.getTransaction().commit();
		// } catch (RuntimeException ex) {
		// entityManager.getTransaction().rollback();
		// throw ex;
		// } finally {
		// entityManager.close();
		// }
		beginTransaction();
		docentRepository.delete(id);
		commit();
	}

	// public void opslag(long id, BigDecimal percentage) {
	// // EntityManager entityManager = JPAFilter.getEntityManager();
	// // try {
	// // entityManager.getTransaction().begin();
	// // docentRepository.read(id, entityManager).opslag(percentage);
	// // entityManager.getTransaction().commit();
	// // } catch (RuntimeException ex) {
	// // entityManager.getTransaction().rollback();
	// // throw ex;
	// // } finally {
	// // entityManager.close();
	// // }
	// beginTransaction();
	//// docentRepository.read(id).opslag(percentage);
	// docentRepository.readWithLock(id).opslag(percentage);
	// commit();
	// }

	public void opslag(long id, BigDecimal percentage) {
		beginTransaction();
		docentRepository.read(id).opslag(percentage);
		try {
			commit();
		} catch (RollbackException ex) {
			if (ex.getCause() instanceof OptimisticLockException) {
				throw new RecordAangepastException();
			}
		}
	}

	public List<Docent> findByWeddeBetween(BigDecimal van, BigDecimal tot, int vanafRij, int aantalRijen) {
		return docentRepository.findByWeddeBetween(van, tot, vanafRij, aantalRijen);
	}

	public List<VoornaamEnId> findVoornamen() {
		return docentRepository.findVoornamen();
	}

	public BigDecimal findMaxWedde() {
		return docentRepository.findMaxWedde();
	}

	public List<AantalDocentenPerWedde> findAantalDocentenPerWedde() {
		return docentRepository.findAantalDocentenPerWedde();
	}

	public void algemeneOpslag(BigDecimal percentage) {
		BigDecimal factor = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
		beginTransaction();
		docentRepository.algemeneOpslag(factor);
		commit();
	}

	public void bijnaamToevoegen(long id, String bijnaam) {
		beginTransaction();
		docentRepository.read(id).addBijnaam(bijnaam);
		commit();
	}

	public void bijnamenVerwijderen(long id, String[] bijnamen) {
		beginTransaction();
		Docent docent = docentRepository.read(id);
		for (String bijnaam : bijnamen) {
			docent.removeBijnaam(bijnaam);
		}
		commit();
	}

	public List<Docent> findBestBetaaldeVanEenCampus(long id) {
		return docentRepository.findBestBetaaldeVanEenCampus(campusRepository.read(id));
	}
}