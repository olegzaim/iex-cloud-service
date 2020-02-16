package com.iex.iexcloudservice.resources;

import com.iex.iexcloudservice.models.Company;
import com.iex.iexcloudservice.models.Price;
import com.iex.iexcloudservice.repository.CompanyRepository;
import com.iex.iexcloudservice.repository.PriceRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.util.CompositeIterator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

@RestController
@RequestMapping("/company")
public class CompanyResource {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    PriceRepository priceRepository;

    final static String PUBLIC_TOKEN = "pk_387b905493f24084ab489672944d2683";
    final static String SECRET_TOKEN = "sk_6e53d0fe05724d3a993d20223741bdd9";

    @GetMapping("/{name}")
    public List<Company> getCompanies(@PathVariable String name) throws IOException, JSONException {

        final IEXCloudClient cloudClient = IEXTradingClient.create(IEXTradingApiVersion.IEX_CLOUD_V1,
                new IEXCloudTokenBuilder()
                        .withPublishableToken(PUBLIC_TOKEN)
                        .withSecretToken(SECRET_TOKEN)
                        .build());

        final String logoUrl = cloudClient.executeRequest(new LogoRequestBuilder().withSymbol(name).build()).getUrl();
        final String companyName = cloudClient.executeRequest(new CompanyRequestBuilder().withSymbol(name).build()).getCompanyName();
        final List<Chart> chart = cloudClient.executeRequest(new ChartRequestBuilder().withSymbol(name).withChartRange(ChartRange.getValueFromCode("1m")).build());

        Company company = companyRepository.findByName(companyName);
        boolean companyAlreadyExist = company != null;
        if (!companyAlreadyExist) {
            company = new Company();
            company.setLogoUrl(logoUrl);
            company.setName(companyName);
        }

        List<Price> prices = new ArrayList<>();

        for (Chart c :
                chart) {
            prices.add(new com.iex.iexcloudservice.models.Price(c, company));
        }

        company.setPrices(prices);
        if(!companyAlreadyExist) {
            companyRepository.save(company);
        }
        priceRepository.saveAll(prices);
        return Collections.singletonList(company);
    }

    @GetMapping("/history")
    public List<Company> getAll() {
        return companyRepository.findAll();
    }
}