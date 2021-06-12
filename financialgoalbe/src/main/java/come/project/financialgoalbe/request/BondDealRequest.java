package come.project.financialgoalbe.request;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import come.project.financialgoalbe.entities.Bond;
import come.project.financialgoalbe.entities.BondDeal;
import come.project.financialgoalbe.entities.Folder;
import come.project.financialgoalbe.entities.User;
import come.project.financialgoalbe.repository.BondRepository;
import come.project.financialgoalbe.repository.FolderRepository;
import come.project.financialgoalbe.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BondDealRequest {
	
	private String isin;
	
	private BigDecimal price;
	
	private BigDecimal quantity;
	
	private String folder;
	
	private String note;
	
	private String typeOfDeal;
	
	private Long owner;
	
	private Calendar executionDate;
	
	public BondDeal toEntity(UserRepository usr, BondRepository brs, FolderRepository folderRepository) {
		BondDeal bondDeal = new BondDeal();
		bondDeal.setIsin(this.isin);
		bondDeal.setPrice(this.price);
		bondDeal.setQuantity(this.quantity);
		bondDeal.setTypeOfDeal(this.typeOfDeal);
		bondDeal.setNote(this.note);
		bondDeal.setMovementDate(this.executionDate);
		User user = usr.findUserById(this.owner);
		bondDeal.setOwner(user);
		Bond bond = brs.findBondByIsin(this.isin);
		bondDeal.setBond(bond);
		Folder folder = folderRepository.findFolderByShortNameAndOwner(this.owner, this.folder);
		bondDeal.setFolder(folder);
		return bondDeal;
	}

}
