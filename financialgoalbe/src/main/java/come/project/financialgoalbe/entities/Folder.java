package come.project.financialgoalbe.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table ( name = "Folder")
public class Folder {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	@Column ( name = "idFolder")
	Long idFolder;
	
	@Column ( name = "shortName")
	String shortName;
	
	@Column ( name = "longName")
	String longName;
	
	@ManyToOne
	@JoinColumn(name="idUser",nullable=false)
	User owner;
	
	@OneToMany(mappedBy="folder")
	@JsonIgnore
	private List<BondDeal> bondDeal;
	
	@OneToMany(mappedBy="folder")
	@JsonIgnore
	private List<EquityDeal> equityDeal;

}
