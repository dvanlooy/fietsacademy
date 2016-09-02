package be.vdab.repositories;

import java.util.Collections;
import java.util.List;

import be.vdab.entities.Campus;

public class CampusRepository extends AbstractRepository {
	public List<Campus> findByGemeente(String gemeente) {
		return getEntityManager().createNamedQuery("Campus.findByGemeente", Campus.class)
				.setParameter("gemeente", gemeente).getResultList();
	}

	public List<Campus> findAll() { 
		return getEntityManager().createNamedQuery("Campus.findAll", Campus.class).getResultList();
	}
	// deze nog nakijken om een lijst met beschikbare locaties te krijgen
	public List<String> findAllLocations() {
		// wordt gebruikt om dropdown list te krijgen om campus te selecteren, geen value object nodig.
		@SuppressWarnings("unchecked")
		List<String> locations = getEntityManager().createNamedQuery("Campus.findAllLocations").getResultList();
		return Collections.unmodifiableList(locations);
	}

	public Campus read(long id) { // voor later in de cursus
		return getEntityManager().find(Campus.class, id);
	}
}