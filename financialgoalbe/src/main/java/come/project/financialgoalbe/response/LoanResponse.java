package come.project.financialgoalbe.response;

import java.math.BigDecimal;
import java.util.Calendar;

import come.project.financialgoalbe.entities.Loan;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanResponse {
	
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
	
	public void toResponse(Loan obj) {
		this.idLoan=obj.getIdLoan();
		this.typeOfLoan=obj.getTypeOfLoan();
		this.descriptionLoan=obj.getDescriptionLoan();
		this.typeOfRate=obj.getTypeOfRate();
		this.valueLoan=obj.getValueLoan();
		this.finalPriceLoan=obj.getFinalPriceLoan();
		this.duration=obj.getDuration();
		this.bank=obj.getBank();
		this.monthlyPayment=obj.getMonthlyPayment();
		this.rate=obj.getRate();
		this.startDate=obj.getStartDate();
		this.endDate=obj.getEndDate();
		this.currencies=obj.getCurrencies().getShortName();
	}

}
