package come.project.financialgoalbe.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table ( name = "Bond")
public class Bond {
	
	public Bond() {
		this.idBond = new Long(0);
		this.isin = "";
		this.description = "";
		this.price = new BigDecimal("0");
		this.currencies = new Currencies();
		this.cedola = new BigDecimal("0");
		this.expire = Calendar.getInstance();
		this.country = new Country();
		this.listBondsDeal = new ArrayList();
	}
	
	public Bond(Long idBond2, String isin2, String description2, BigDecimal price2, Currencies currencies2,
			BigDecimal cedola2, Calendar expire2, Country country2, List<BondDeal> listBondsDeal2) {
		this.idBond=idBond2;
		this.isin=isin2;
		this.description=description2;
		this.price=price2;
		this.currencies=currencies2;
		this.cedola=cedola2;
		this.expire=expire2;
		this.country=country2;
		this.listBondsDeal=listBondsDeal2;
	}

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column( name = "idBond")
	private Long idBond;
	
	@Column( name = "isin")
	private String isin;
	
	@Column( name = "description")
	private String description;
	
	@Column( name = "price")
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name="idCurrencies", nullable=false)
	private Currencies currencies;
	
	@Column( name = "cedola")
	private BigDecimal cedola;
	
	@Column( name = "expire")
	private Calendar expire;
	
	@ManyToOne
	@JoinColumn(name="idCountry", nullable=false)
	private Country country;
	
	@OneToMany(mappedBy="bond")
	private List<BondDeal> listBondsDeal;

	public Long getIdBond() {
		return idBond;
	}

	public void setIdBond(Long idBond) {
		this.idBond = idBond;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Currencies getCurrencies() {
		return currencies;
	}

	public void setCurrencies(Currencies currencies) {
		this.currencies = currencies;
	}

	public BigDecimal getCedola() {
		return cedola;
	}

	public void setCedola(BigDecimal cedola) {
		this.cedola = cedola;
	}

	public Calendar getExpire() {
		return expire;
	}

	public void setExpire(Calendar expire) {
		this.expire = expire;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<BondDeal> getListBondsDeal() {
		return listBondsDeal;
	}

	public void setListBondsDeal(List<BondDeal> listBondsDeal) {
		this.listBondsDeal = listBondsDeal;
	}
	
	


}
