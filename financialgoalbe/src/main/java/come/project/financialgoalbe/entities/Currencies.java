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
@Table ( name = "Currencies")
public class Currencies {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	@Column ( name = "idCurrencies")
	private Long idCurrencies;
	
	@Column ( name = "shortName")
	private String shortName;
	
	@Column ( name = "longName")
	private String longName;
	
	@OneToMany(mappedBy="currencies")
	@JsonIgnore
	private List<Bond> bond;
	
	@OneToMany(mappedBy="currencies")
	@JsonIgnore
	private List<Equity> equity;
	
	@OneToMany(mappedBy="currencies")
	@JsonIgnore
	private List<GeneralMovement> generalMovement;
	
	@OneToMany(mappedBy="currencies")
	@JsonIgnore
	private List<Loan> loan;
	
	@OneToMany(mappedBy="currencies")
	@JsonIgnore
	private List<Market> market;

}
