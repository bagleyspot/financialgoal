package come.project.financialgoalbe.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table ( name = "Country")
public class Country {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	@Column ( name = "idCountry")
	private Long idCountry;
	
	@Column ( name = "shortName")
	private String shortName;
	
	@Column ( name = "longName")
	private String longName;
	
	@OneToMany(mappedBy="country")
	@JsonIgnore
	private List<Bond> bond;
	
	@OneToMany(mappedBy="country")
	@JsonIgnore
	private List<Market> market;
}
