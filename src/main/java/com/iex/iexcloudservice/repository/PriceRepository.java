package com.iex.iexcloudservice.repository;

import com.iex.iexcloudservice.models.Company;
import com.iex.iexcloudservice.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends JpaRepository<Price, Integer> {
}
