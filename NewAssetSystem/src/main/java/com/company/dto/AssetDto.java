package com.company.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AssetDto {
	
	
    @NotEmpty(message = "Asset Name can't be empty!")
	@Size(min = 3, max = 50, message = "Asset Name please enter a vaild asset Name!")
    private String assetName;
    
	
	

	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	} 
      
      
}
