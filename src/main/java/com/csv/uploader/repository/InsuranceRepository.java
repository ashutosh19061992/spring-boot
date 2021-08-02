package com.csv.uploader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csv.uploader.entity.InsuranceSample;

@Repository
public interface InsuranceRepository extends JpaRepository<InsuranceSample, Long> {

}
