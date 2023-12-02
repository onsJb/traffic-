package tn.enicarthage.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.enicarthage.model.Traffic;

@Repository
public interface TrafficRepo extends JpaRepository<Traffic, Long> {

	public List<Traffic> findAll();
	public List<Traffic> findByVille(String ville);
	@Query("SELECT t FROM Traffic t WHERE t.latitude = :latitude AND t.longitude = :longitude")
	Traffic findByLatitudeAndLongitude(@Param("latitude") float latitude, @Param("longitude") float longitude);

	//public Traffic findByLatitudeAndLongitude(float latitude, float longitude);
}
