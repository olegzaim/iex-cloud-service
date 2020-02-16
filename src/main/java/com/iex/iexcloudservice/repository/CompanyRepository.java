package com.iex.iexcloudservice.repository;

import com.iex.iexcloudservice.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findByName(String name);
}
