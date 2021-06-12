package come.project.financialgoalbe.entities;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class DataChart {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	@Column( name = "id")
	Long id;
	
	@Column( name = "dates")
	Calendar data;
	
	@Column( name = "value")
	BigDecimal value;

}
