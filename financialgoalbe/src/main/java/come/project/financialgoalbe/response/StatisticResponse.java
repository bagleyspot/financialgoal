package come.project.financialgoalbe.response;

import java.math.BigDecimal;
import java.util.List;

import come.project.financialgoalbe.entities.DataChart;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticResponse {

	BigDecimal valuePositionBond;
	
	BigDecimal valuePositionEquity;
	
	List<DataChart> dataChart;
	
}
