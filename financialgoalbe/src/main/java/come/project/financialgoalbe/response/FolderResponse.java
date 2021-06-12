package come.project.financialgoalbe.response;

import come.project.financialgoalbe.entities.Folder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FolderResponse {
	
	Long idFolder;
	
	String shortName;
	
	String longName;
	
	public FolderResponse toResponse(Folder obj) {
		this.idFolder = obj.getIdFolder();
		this.shortName = obj.getShortName();
		this.longName = obj.getLongName();
		
		return this;
	}

}
