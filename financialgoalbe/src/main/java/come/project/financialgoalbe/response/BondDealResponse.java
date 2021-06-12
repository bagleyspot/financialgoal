package come.project.financialgoalbe.response;

import java.math.BigDecimal;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;

import come.project.financialgoalbe.entities.BondDeal;
import come.project.financialgoalbe.entities.Folder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BondDealResponse {

	private Long idDeal;
	
	private String isin;
	
	private BigDecimal price;
	
	private BigDecimal quantity;
	
	@JsonIgnore
	private Folder folder;
	
	private Calendar movementDate;
	
	private String typeOfDeal;
	
	private String note;
	
	public BondDeal toEntity() {
		BondDeal bondDeal = new BondDeal(idDeal,isin,price,quantity,folder,movementDate,typeOfDeal,note);
		return bondDeal;
	}
	
	public BondDealResponse toResponse (BondDeal obj) {
		BondDealResponse bdr = new BondDealResponse();
		bdr.setIdDeal(obj.getIdDeal());
		bdr.setIsin(obj.getIsin());
		bdr.setPrice(obj.getPrice());
		bdr.setQuantity(obj.getQuantity());
		bdr.setFolder(obj.getFolder());
		bdr.setMovementDate(obj.getMovementDate());
		bdr.setTypeOfDeal(obj.getTypeOfDeal());
		bdr.setNote(obj.getNote());
		return bdr;
	}
}
