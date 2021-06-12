package come.project.financialgoalbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import come.project.financialgoalbe.entities.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
	
	List<Country> findAll();

}
