package come.project.financialgoalbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import come.project.financialgoalbe.entities.Equity;

@Repository
public interface EquityRepository extends JpaRepository<Equity,Long> {
	@Query( value = "select * from equity", nativeQuery = true)
	public List<Equity> findAll();
	
	@Query( value = "select * from equity where isin = ?1", nativeQuery = true)
	public Equity findEquityByIsin(String isin);

	@Query( value = "select * from equity\r\n"
			+ "where isin in (select isin from equity_deal where id_user=?1 AND id_folder = (select id_folder from folder where short_name=?2))", nativeQuery = true)
	public List<Equity> findAllByOwner(int id,String shortName);

}
