package come.project.financialgoalbe.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table ( name = "Equity")
public class Equity {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	@Column ( name = "idEquity")
	private Long idEquity;
	
	@Column ( name = "isin")
	private String isin;
	
	@Column ( name = "price")
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name="idCurrencies", nullable=false)
	private Currencies currencies;
	
	@Column ( name = "market")
	private String market;
	
	@Column ( name = "alfanumericCode")
	private String alfanumericCode;
	
	@Column ( name = "sector")
	private String sector;
	
	@OneToMany(mappedBy="equity")
	private List<EquityDeal> listEquityDeal;

}
