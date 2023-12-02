package tn.enicarthage.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="traffic")
public class Traffic implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="id") 
	private int id;
	
	@Column(name="latitude") 
	private float latitude;
	
	@Column(name="longitude") 
	private float longitude;
	
	@Column(name="etat") 
	private String etat;
	
	@Column(name="nom_rue") 
	private String nomRue;
	
	@Column(name="ville") 
	private String ville;
	
	@Column(name="municipalite") 
	private String municipalite;
	
	
	
}
