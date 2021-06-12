package come.project.financialgoalbe.entities;

import java.math.BigDecimal;
import java.util.Calendar;

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
@Table ( name = "EquityDeal")
public class EquityDeal {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	@Column ( name = "idDeal")
	private Long idDeal;
	
	@Column ( name = "isin")
	private String isin;
	
	@Column ( name = "price")
	private BigDecimal price;
	
	@Column ( name = "quantity")
	private BigDecimal quantity;
	
	@ManyToOne
	@JoinColumn(name = "idFolder", nullable=false)
	private Folder folder;
	
	@Column ( name = "movementDate")
	private Calendar movementDate;
	
	@Column ( name = "typeOfDeal")
	private String typeOfDeal;
	
	@Column ( name = "note")
	private String note;
	
	@ManyToOne
	@JoinColumn(name = "idUser", nullable=false)
	private User owner;
	
	@ManyToOne
	@JoinColumn(name="idEquity", nullable=false)
	private Equity equity;

}
