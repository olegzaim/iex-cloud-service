package com.iex.iexcloudservice.resources;

import com.iex.iexcloudservice.models.Company;
import com.iex.iexcloudservice.models.Price;
import com.iex.iexcloudservice.repository.CompanyRepository;
import com.iex.iexcloudservice.repository.PriceRepository;
import com.iex.iexcloudservice.services.IEXCloudClientService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.util.CompositeIterator;
import org.springframework.web.bind.annotation.*;
import pl.zankowski.iextrading4j.api.stocks.Chart;
import pl.zankowski.iextrading4j.api.stocks.ChartRange;
import pl.zankowski.iextrading4j.api.stocks.Logo;
import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.api.stocks.v1.BatchStocks;
import pl.zankowski.iextrading4j.client.IEXCloudClient;
import pl.zankowski.iextrading4j.client.IEXCloudTokenBuilder;
import pl.zankowski.iextrading4j.client.IEXTradingApiVersion;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.ChartRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.CompanyRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.LogoRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/iex")
public class CompanyResource {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    IEXCloudClientService cloudClientService;

    @RequestMapping(
            value = "/{time}/company",
            params = {"symbol"},
            method = GET)
    public List<Company> getCompanies(@RequestParam("symbol") List<String> symbol, @PathVariable String time) throws IOException, JSONException {

        final List<Company> companies = new ArrayList<>();
        final List<Price> pricesToSave = new ArrayList<>();
        final List<Company> companyToSave = new ArrayList<>();
        for (String s :
                symbol) {

            final String companyName = cloudClientService.getCompanyNameFromIEX(s);

            Company company = companyRepository.findByName(companyName);

            boolean isCompanyExist = company != null;
            if (!isCompanyExist) {
                company = new Company();
                company.setLogoUrl(cloudClientService.getCompanyLogoFromIEX(s));
                company.setName(companyName);
            }

            final List<Chart> chart = cloudClientService.getCompanyChartFromIEX(s, time);
            final List<Price> prices = new ArrayList<>();
            for (Chart c :
                    chart) {
                prices.add(new Price(c, company));
            }
            company.setPrices(prices);

            if (!isCompanyExist) {
                companyToSave.add(company);
            }
            companies.add(company);
            pricesToSave.addAll(prices);
        }
        if (!companyToSave.isEmpty()) {
            companyRepository.saveAll(companyToSave);
        }
        if (!pricesToSave.isEmpty()) {
            priceRepository.saveAll(pricesToSave);
        }
        return companies;
    }

    @GetMapping("/history")
    public List<Company> getAll() {
        return companyRepository.findAll();
    }
}