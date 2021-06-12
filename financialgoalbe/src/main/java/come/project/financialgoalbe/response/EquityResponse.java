package come.project.financialgoalbe.response;

import java.math.BigDecimal;

import come.project.financialgoalbe.entities.Currencies;
import come.project.financialgoalbe.entities.Equity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquityResponse {
	
	private Long idEquity;
	
	private String isin;

	private BigDecimal price;
	
	private Currencies currencies;
	
	private String market;
	
	private String alfanumericCode;

	private String sector;
	
	public EquityResponse toResponse(Equity obj) {
		this.idEquity = obj.getIdEquity();
		this.isin = obj.getIsin();
		this.price = obj.getPrice();
		this.currencies = obj.getCurrencies();
		this.market = obj.getMarket();
		this.alfanumericCode = obj.getAlfanumericCode();
		this.sector = obj.getSector();
		return this;
	}

}
