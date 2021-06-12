package come.project.financialgoalbe.request;

import java.math.BigDecimal;
import java.util.Calendar;

import come.project.financialgoalbe.entities.Loan;
import come.project.financialgoalbe.repository.CurrenciesRepository;
import come.project.financialgoalbe.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanRequest {
	
	private Long idLoan;
	
	private String typeOfLoan;
	
	private String descriptionLoan;
	
	private String typeOfRate;
	
	private BigDecimal valueLoan;
	
	private BigDecimal finalPriceLoan;
	
	private int duration;
	
	private String bank;
	
	private BigDecimal monthlyPayment;
	
	private BigDecimal rate;
	
	private Calendar startDate;
	
	private Calendar endDate;
	
	private String currencies;
	
	private Long idOwner;
	
	public Loan toEntity(CurrenciesRepository rep, UserRepository usrep) {
		Loan loan = new Loan();
		loan.setIdLoan(this.idLoan);
		loan.setTypeOfLoan(this.typeOfLoan);
		loan.setDescriptionLoan(this.descriptionLoan);
		loan.setTypeOfRate(this.typeOfRate);
		loan.setValueLoan(this.valueLoan);
		loan.setFinalPriceLoan(this.finalPriceLoan);
		loan.setDuration(this.duration);
		loan.setBank(this.bank);
		loan.setMonthlyPayment(this.monthlyPayment);
		loan.setRate(this.rate);
		loan.setStartDate(this.startDate);
		loan.setEndDate(this.endDate);
		loan.setCurrencies(rep.findCurrenciesByShortName(this.currencies));
		loan.setOwner(usrep.findUserById(this.idOwner));
		
		return loan;
		
	}

}
