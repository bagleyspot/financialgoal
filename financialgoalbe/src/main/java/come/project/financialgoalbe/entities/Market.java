package come.project.financialgoalbe.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table ( name = "Market")
public class Market {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	@Column ( name = "idMarket")
	Long idMarket;
	
	@Column ( name = "shortName")
	String shortName;
	
	@Column ( name = "longName")
	String longName;
	
	@ManyToOne
	@JoinColumn(name="idCurrencies",nullable=false)
	Currencies currencies;
	
	@ManyToOne
	@JoinColumn(name="idCountry",nullable=false)
	Country country;

}
