package com.company.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.company.entity.Assets;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
@Repository
public interface AssetRepository extends JpaRepository<Assets,Long> {
	
	@Transactional
	@Modifying
	List<Assets>findByAssetName(String assetName);
}
