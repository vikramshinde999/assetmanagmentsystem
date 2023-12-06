package com.company.dto;

import javax.validation.constraints.Min;

public class AssetDto2 {
	@Min(value = 0,message = "Put valid Asset id ")
	private long assetId;

	public long getAssetId() {
		return assetId;
	}

	public void setAssetId(long assetId) {
		this.assetId = assetId;
	}
	

}
