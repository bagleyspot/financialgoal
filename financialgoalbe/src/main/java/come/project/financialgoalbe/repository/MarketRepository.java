package come.project.financialgoalbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import come.project.financialgoalbe.entities.Market;

@Repository
public interface MarketRepository extends JpaRepository<Market,Long>{
	
	@Query( value = "select * from market\r\n"
			+ "where id_country = (select c.id_country from country as c where c.short_name=?1)", nativeQuery = true)
	List<Market> findMarketByCountry(String shortName);

}
