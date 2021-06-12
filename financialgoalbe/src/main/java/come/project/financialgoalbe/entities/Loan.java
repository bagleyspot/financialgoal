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
@Table ( name = "Loan")
public class Loan {
	
	public Loan(Long idLoan, String typeOfLoan, String descriptionLoan, String typeOfRate, BigDecimal valueLoan, BigDecimal finalPriceLoan,
			int duration, String bank, BigDecimal monthlyPayment, BigDecimal rate, Calendar startDate,Currencies currencies, User owner)
		{
		this.idLoan=idLoan;
		this.typeOfLoan=typeOfLoan;
		this.descriptionLoan=descriptionLoan;
		this.typeOfRate=typeOfRate;
		this.valueLoan=valueLoan;
		this.finalPriceLoan=finalPriceLoan;
		this.duration=duration;
		this.bank=bank;
		this.monthlyPayment=monthlyPayment;
		this.rate=rate;
		this.startDate=startDate;
		this.endDate=startDate;
		this.endDate.add(Calendar.YEAR, duration);
		this.currencies=currencies;
		this.owner=owner;
		}
	
	public Loan() {
		this.idLoan= Long.getLong("0");
		this.typeOfLoan = "";
		this.descriptionLoan = "";
		this.typeOfRate = "";
		this.valueLoan = new BigDecimal("0");
		this.finalPriceLoan = new BigDecimal("0");
		this.duration = 0;
		this.bank = "";
		this.monthlyPayment = new BigDecimal("0");
		this.rate = new BigDecimal("0");
		this.startDate = Calendar.getInstance();
		this.endDate = Calendar.getInstance();
		this.currencies = new Currencies();
		this.owner = new User();
	}
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	@Column ( name = "idLoan")
	Long idLoan;
	
	@Column ( name = "typeofLoan")
	String typeOfLoan;
	
	@Column ( name = "descriptionLoan")
	String descriptionLoan;
	
	@Column ( name = "typeOfRate")
	private String typeOfRate;
	
	@Column ( name = "valueLoan")
	private BigDecimal valueLoan;
	
	@Column ( name = "finalPriceLoan")
	private BigDecimal finalPriceLoan;
	
	@Column ( name = "duration")
	private int duration;
	
	@Column ( name = "bank") 
	private String bank;
	
	@Column ( name = "monthlyPayment")
	private BigDecimal monthlyPayment;
	
	@Column ( name = "rate")
	private BigDecimal rate;
	
	@Column ( name = "startDate")
	private Calendar startDate;
	
	@Column ( name = "endDate")
	private Calendar endDate;
	
	@ManyToOne
	@JoinColumn(name="idCurrencies",nullable=false)
	private Currencies currencies;

	@ManyToOne
	@JoinColumn(name="idUser",nullable=false)
	private User owner;
	

}
