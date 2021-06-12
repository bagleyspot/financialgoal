package come.project.financialgoalbe.response;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import come.project.financialgoalbe.entities.Bond;
import come.project.financialgoalbe.entities.BondDeal;
import come.project.financialgoalbe.entities.Country;
import come.project.financialgoalbe.entities.Currencies;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BondResponse {
	
	Long idBond;
	
	String isin;
	
	String description;
	
	BigDecimal price;
	
	Currencies currencies;
	
	BigDecimal cedola;
	
	Calendar expire;
	
	Country country;
	
	@JsonIgnore
	List<BondDeal> listBondsDeal;
	
	public Bond toEntity() {
		Bond bond = new Bond(this.idBond, this.isin, this.description, this.price, this.currencies, this.cedola, this.expire, this.country, this.listBondsDeal);
		return bond;
	}
	
	public BondResponse toResponse(Bond obj) {
		this.idBond=obj.getIdBond();
		this.isin=obj.getIsin();
		this.description=obj.getDescription();
		this.price=obj.getPrice();
		this.currencies=obj.getCurrencies();
		this.cedola=obj.getCedola();
		this.expire=obj.getExpire();
		this.country=obj.getCountry();
		this.listBondsDeal=obj.getListBondsDeal();
		return this;
	}

}
