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

@Getter
@Setter
@Entity
@Table ( name = "Goal")
public class Goal {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	@Column ( name = "idGoal")
	Long idGoal;
	
	@Column ( name = "title")
	String title;
	
	@Column ( name = "description")
	String description;
	
	@Column ( name = "startDate")
	Calendar startDate;
	
	@Column ( name = "endDate")
	Calendar endDate;
	
	@Column ( name = "finalValueGoal")
	BigDecimal finalValueGoal;
	
	@Column ( name = "currentValueGoal")
	BigDecimal currentValueGoal;
}
