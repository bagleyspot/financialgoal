package come.project.financialgoalbe.request;

import java.math.BigDecimal;
import java.util.Calendar;

import come.project.financialgoalbe.entities.EquityDeal;
import come.project.financialgoalbe.entities.Folder;
import come.project.financialgoalbe.entities.User;
import come.project.financialgoalbe.repository.EquityRepository;
import come.project.financialgoalbe.repository.FolderRepository;
import come.project.financialgoalbe.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquityDealRequest {
	
	private String isin;

	private BigDecimal price;

	private BigDecimal quantity;
	
	private String folder;

	private Calendar movementDate;

	private String typeOfDeal;
	
	private String note;
	
	private Long owner;
	
	public EquityDeal toEntity(EquityRepository eqr, UserRepository usr, FolderRepository frs) {
		EquityDeal equityDeal = new EquityDeal();
		equityDeal.setIsin(this.isin);
		equityDeal.setPrice(this.price);
		equityDeal.setQuantity(this.quantity);
		equityDeal.setMovementDate(this.movementDate);
		equityDeal.setFolder(frs.findFolderByShortNameAndOwner(this.owner,this.folder));
		equityDeal.setTypeOfDeal(this.typeOfDeal);
		equityDeal.setNote(this.note);
		equityDeal.setOwner(usr.findUserById(this.owner));
		equityDeal.setEquity(eqr.findEquityByIsin(this.isin));
		
		return equityDeal;
		
	}

	
}
