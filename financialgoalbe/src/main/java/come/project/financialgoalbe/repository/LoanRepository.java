package come.project.financialgoalbe.repository;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import come.project.financialgoalbe.entities.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long>{
	
	@Query( value ="select * from loan where id_user=?1", nativeQuery = true)
	public List<Loan> getAllLoansByOwner(Long id);
	
	@Query( value = "select -sum(monthly_payment) from loan\r\n"
			+ "where convert(varchar,start_date,103)>=?1 AND convert(varchar,end_date,103)<=?2", nativeQuery = true)
	public BigDecimal getMonthlyPaymentLoans(String start_date,String end_date);

}
