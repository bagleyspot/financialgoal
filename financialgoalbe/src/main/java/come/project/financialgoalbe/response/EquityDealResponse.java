package come.project.financialgoalbe.response;

import java.math.BigDecimal;
import java.util.Calendar;

import come.project.financialgoalbe.entities.EquityDeal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquityDealResponse {
	
	private Long idEquityDeal;
	
	private String isin;
	
	private Calendar movementDate;
	
	private BigDecimal price;
	
	private BigDecimal quantity;
	
	private String typeOfDeal;
	
	public EquityDealResponse toResponse(EquityDeal obj) {
		EquityDealResponse eqd = new EquityDealResponse();
		eqd.setIdEquityDeal(obj.getIdDeal());
		eqd.setIsin(obj.getIsin());
		eqd.setMovementDate(obj.getMovementDate());
		eqd.setPrice(obj.getPrice());
		eqd.setQuantity(obj.getQuantity());
		eqd.setTypeOfDeal(obj.getTypeOfDeal());
		
		return eqd;
	}

}
