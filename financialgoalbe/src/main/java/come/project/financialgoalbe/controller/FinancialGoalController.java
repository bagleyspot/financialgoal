package come.project.financialgoalbe.controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import come.project.financialgoalbe.entities.Bond;
import come.project.financialgoalbe.request.BondDealRequest;
import come.project.financialgoalbe.request.EquityDealRequest;
import come.project.financialgoalbe.request.LoanRequest;
import come.project.financialgoalbe.response.BondDealResponse;
import come.project.financialgoalbe.response.BondResponse;
import come.project.financialgoalbe.response.CountryResponse;
import come.project.financialgoalbe.response.EquityDealResponse;
import come.project.financialgoalbe.response.EquityResponse;
import come.project.financialgoalbe.response.FolderResponse;
import come.project.financialgoalbe.response.LoanResponse;
import come.project.financialgoalbe.response.StatisticResponse;
import come.project.financialgoalbe.service.FinancialGoalService;

@RestController
@CrossOrigin("*")
public class FinancialGoalController {
	
	@Autowired
	FinancialGoalService financialGoalService;
	
	@GetMapping( value = "/allbonds", produces = "application/json")
	public ResponseEntity<List<BondResponse>>getAllBonds(){
		return new ResponseEntity<List<BondResponse>>(financialGoalService.getAllBonds(), HttpStatus.OK);
	}
	
	@GetMapping( value ="/allequity", produces = "application/json")
	public ResponseEntity<List<EquityResponse>> getAllEquity(){
		return new ResponseEntity<List<EquityResponse>>(financialGoalService.getAllEquity(),HttpStatus.OK);
	}
	
	@GetMapping( value ="/allequitybyowner/{id}/{folder}", produces = "application/json")
	public ResponseEntity<List<EquityResponse>> getAllEquityByOwner(@PathVariable("id") int id,@PathVariable("folder")String folder){
		return new ResponseEntity<List<EquityResponse>>(financialGoalService.getAllEquityByOwner(id,folder),HttpStatus.OK);
	}
	
	@GetMapping( value ="/allbondbyowner/{id}/{folder}", produces = "application/json")
	public ResponseEntity<List<BondResponse>> getAllBondByOwner(@PathVariable("id") int id, @PathVariable("folder")String folder){
		return new ResponseEntity<List<BondResponse>>(financialGoalService.getAllBondByOwner(id,folder),HttpStatus.OK);
	}
	
	@PostMapping( value = "/bondsdealowner", consumes = "application/json")
	public ResponseEntity<List<BondDealResponse>>getBondsDealsByOwner(@RequestBody long idOwner ){
		return new ResponseEntity<List<BondDealResponse>>(financialGoalService.getBondsDealByOwner(idOwner), HttpStatus.OK);
	}
	
	@GetMapping( value ="/equitydealbyisin/{isin}/{owner}", produces = "application/json")
	public ResponseEntity<List<EquityDealResponse>> getEquityDealByIsinAndOwner(@PathVariable("isin")String isin,@PathVariable("owner") long idOwner){
		return new ResponseEntity<List<EquityDealResponse>> (financialGoalService.getEquityDealByIsinAndOwner(isin,idOwner),HttpStatus.OK);
	}
	
	@GetMapping( value ="/bonddealbyisin/{isin}/{owner}", produces = "application/json")
	public ResponseEntity<List<BondDealResponse>> getBondDealByIsinAndOwner(@PathVariable("isin")String isin,@PathVariable("owner") long idOwner){
		return new ResponseEntity<List<BondDealResponse>> (financialGoalService.getBondDealByIsinAndOwner(isin,idOwner),HttpStatus.OK);
	}
	
	@GetMapping( value ="/allfolderowner/{id}", produces = "application/json")
	public ResponseEntity<List<String>>getFolderByOwner(@PathVariable("id") int id){
		return new ResponseEntity<List<String>>(financialGoalService.getFolderbyOwneer(id),HttpStatus.OK);
	}
	
	@GetMapping( value ="/allcountry", produces = "application/json")
	public ResponseEntity<List<CountryResponse>>getAllCountry(){
		return new ResponseEntity<List<CountryResponse>>(financialGoalService.getAllCountry(),HttpStatus.OK);
	}
	
	@PostMapping( value ="/operationbond", consumes = "application/json")
	public ResponseEntity<String>operationBond(@RequestBody BondDealRequest bondDeal){
		return new ResponseEntity<String>(financialGoalService.operationBond(bondDeal),HttpStatus.OK);
	}
	
	@PostMapping( value = "/operationequity", consumes = "application/json")
	public ResponseEntity<String> operationBond(@RequestBody EquityDealRequest equityDeal){
		return new ResponseEntity<String>(financialGoalService.operationEquity(equityDeal),HttpStatus.OK);
	}
	
	@GetMapping( value ="/allLoanByOwner/{id}", produces = "application/json")
	public ResponseEntity<List<LoanResponse>> getAllLoansByOwner(@PathVariable("id") Long id){
		return new ResponseEntity<List<LoanResponse>>(financialGoalService.getAllLoansByOwner(id),HttpStatus.OK);
	}
	
	@PostMapping( value = "/addLoan", consumes = "application/json")
	public ResponseEntity<Boolean> addEditLoan(@RequestBody LoanRequest loan){
		return new ResponseEntity<Boolean>(financialGoalService.addEditLoan(loan),HttpStatus.OK);
	}
	
	@GetMapping( value ="/getCurrencies", produces = "application/json")
	public ResponseEntity<List<String>> getCurrencies(){
		return new ResponseEntity<List<String>>(financialGoalService.getCurrencies(),HttpStatus.OK);
	}
	
	//Questo endPoint va a generare la creazione delle statistiche relative al proprio profilo tra un range di date
	@GetMapping( value = "/statistics/{startDate}/{endDate}", produces = "application/json")
	public ResponseEntity<StatisticResponse> getStatistics(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) throws ParseException{
		return new ResponseEntity<StatisticResponse>(financialGoalService.elaborationStatistics(startDate,endDate), HttpStatus.OK);
	}
	
	//Vado ad eliminare un Loan
	@GetMapping( value = "deleteloan/{id}", produces = "application/json")
	public ResponseEntity<String> deleteLoan(@PathVariable("id") Long id){
		return new ResponseEntity<String>(financialGoalService.deleteLoan(id), HttpStatus.OK);
	}

}
