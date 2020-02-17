package com.iex.iexcloudservice.services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.zankowski.iextrading4j.api.stocks.Chart;
import pl.zankowski.iextrading4j.api.stocks.ChartRange;
import pl.zankowski.iextrading4j.client.IEXCloudClient;
import pl.zankowski.iextrading4j.client.IEXCloudTokenBuilder;
import pl.zankowski.iextrading4j.client.IEXTradingApiVersion;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.ChartRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.CompanyRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.LogoRequestBuilder;

import java.util.List;

@Component
public class IEXCloudClientService {

    final static String PUBLIC_TOKEN = "pk_387b905493f24084ab489672944d2683";
    final static String SECRET_TOKEN = "sk_6e53d0fe05724d3a993d20223741bdd9";

    final static IEXCloudClient cloudClient = IEXTradingClient.create(IEXTradingApiVersion.IEX_CLOUD_V1,
            new IEXCloudTokenBuilder()
                    .withPublishableToken(PUBLIC_TOKEN)
                    .withSecretToken(SECRET_TOKEN)
                    .build());

    public String getCompanyLogoFromIEX(String symbol){
        return cloudClient.executeRequest(new LogoRequestBuilder().withSymbol(symbol).build()).getUrl();
    }

    public String getCompanyNameFromIEX(String symbol){
        return cloudClient.executeRequest(new CompanyRequestBuilder().withSymbol(symbol).build()).getCompanyName();
    }

    public List<Chart> getCompanyChartFromIEX(String symbol, String time){
        return cloudClient.executeRequest(new ChartRequestBuilder().withSymbol(symbol).withChartRange(ChartRange.getValueFromCode(time)).build());

    }
}
