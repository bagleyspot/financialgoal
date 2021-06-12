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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "BondDeal")
public class BondDeal {
	
	public BondDeal (Long idDeal, String isin , BigDecimal price, BigDecimal quantity, Folder folder, Calendar movementDate,
			String typeOfDeal, String note)
		{
		this.idDeal=idDeal;
		this.isin=isin;
		this.price=price;
		this.quantity=quantity;
		this.folder=folder;
		this.movementDate=movementDate;
		this.typeOfDeal=typeOfDeal;
		this.note=note;
		/*TODO search the owner of Deal from the idDeal*/
		//this.owner= <result of the search>
		}
	
	public BondDeal() {
		this.idDeal = new Long(0);
		this.isin = "";
		this.price = new BigDecimal("0");
		this.quantity = new BigDecimal("0");
		this.folder = new Folder();
		this.movementDate = Calendar.getInstance();
		this.typeOfDeal = "";
		this.note = "";
	}
	
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
	@JoinColumn(name="idFolder", nullable=false)
	private Folder folder;
	
	@Column ( name = "movementDate")
	private Calendar movementDate;
	
	@Column ( name = "typeOfDeal")
	private String typeOfDeal;
	
	@Column ( name = "note")
	private String note;
	
	@ManyToOne
	@JoinColumn(name="idUser", nullable=false)
	private User owner;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="idBond",nullable=false)
	private Bond bond;
}
