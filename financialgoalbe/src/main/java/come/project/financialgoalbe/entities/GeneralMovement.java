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
@Table ( name = "GeneralMovement")
public class GeneralMovement {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	@Column ( name = "idMovement")
	Long idMovement;
	
	@Column ( name = "CategoryMovement")
	String CategoryMovement;
	
	@Column ( name = "infoMovement")
	String infoMovement;
	
	@Column ( name = "value")
	BigDecimal value;
	
	@Column ( name = "typeOfMovement")
	String typeOfMovement; //IF EARN OR EXPENSE
	
	@ManyToOne
	@JoinColumn(name = "idCurrencies",nullable=false)
	Currencies currencies;
	
	@Column ( name = "dateOfMovement")
	Calendar dateOfMovement;
	
	@ManyToOne
	@JoinColumn(name = "idUser", nullable=false)
	User owner;
	
	/*TODO
	 * Add new features to manage a periodic EARN or EXPENSE 
	 * for example every week, month, year or only in January and August 
	boolean isPeriodic;
	*/
}
