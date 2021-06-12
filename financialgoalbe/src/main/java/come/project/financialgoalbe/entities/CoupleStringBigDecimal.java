package come.project.financialgoalbe.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CoupleStringBigDecimal {
	
	@Id
	@Column ( name ="isin")
	String string;
	
	@Column ( name = "value")
	BigDecimal bigDecimal;

}
