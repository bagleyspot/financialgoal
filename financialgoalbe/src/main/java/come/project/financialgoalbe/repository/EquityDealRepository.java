package come.project.financialgoalbe.repository;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import come.project.financialgoalbe.entities.EquityDeal;

public interface EquityDealRepository extends JpaRepository<EquityDeal,Long>{
	
	public EquityDeal save(EquityDeal equityDeal);
	
	@Query ( value = "select * from equity_deal where id_user=?2 AND isin = ?1", nativeQuery = true)
	public List<EquityDeal> findDealByIsinAndOwner(String isin, long idOwner);

}
