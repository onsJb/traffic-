package tn.enicarthage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicarthage.model.Traffic;
import tn.enicarthage.repo.TrafficRepo;

@Service
public class TrafficService {

	@Autowired
	private TrafficRepo repo;
	
	public List<Traffic> getAllTraffics() {
		return repo.findAll();
	}
	
	public List<Traffic> getTrafficByVille(String ville) {
		 return repo.findByVille(ville);
	 }
	
	public Traffic getTrafficByLatitudeAndLongitude(float latitude, float longitude) {
		System.out.println(latitude);
		System.out.println(longitude);
		System.out.println(repo.findByLatitudeAndLongitude(latitude,longitude));
		return repo.findByLatitudeAndLongitude(latitude,longitude);
	}
}
