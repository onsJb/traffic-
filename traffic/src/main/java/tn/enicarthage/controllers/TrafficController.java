package tn.enicarthage.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import tn.enicarthage.model.Traffic;
import tn.enicarthage.model.TrafficSearch;
import tn.enicarthage.services.TrafficService;

@RestController
@RequestMapping("/traffic")
public class TrafficController {
	
	@Autowired
	private TrafficService trafficService;
	
	@GetMapping("/all")
	public List<Traffic> getAllTraffics() {
		return trafficService.getAllTraffics();
	}
	
	 @GetMapping("/{ville}")
	 public List<Traffic> getTrafficByVille(@PathVariable String ville) {
		 return trafficService.getTrafficByVille(ville);
	 }
	 
	 @PostMapping("/search")
	 public Traffic getTrafficByLatitudeAndLongitude(@RequestBody TrafficSearch traffic) {
		 float latitude = traffic.getLatitude();
		 float longitude = traffic.getLongitude();
		 Traffic trafficResult = trafficService.getTrafficByLatitudeAndLongitude(latitude,longitude);
		 if (trafficResult == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Localisation non trouvée !");
			}
		 return trafficResult;
	 }

}
