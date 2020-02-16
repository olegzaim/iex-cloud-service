package com.iex.iexcloudservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.zankowski.iextrading4j.api.stocks.Chart;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PRICE")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "ID", nullable = false)
    private Company company;

    @Column(name = "RETRIVED_DATE")
    private String retrivedDate;

    @Column(name = "DATE")
    private String date;

    @Column(name = "OPEN")
    private BigDecimal open;

    @Column(name = "HIGH")
    private BigDecimal high;

    @Column(name = "LOW")
    private BigDecimal low;

    @Column(name = "CLOSE")
    private BigDecimal close;

    @Column(name = "VOLUME")
    private BigDecimal volume;

    @Column(name = "U_OPEN")
    private BigDecimal uOpen;

    @Column(name = "U_HIGH")
    private BigDecimal uHigh;

    @Column(name = "U_LOW")
    private BigDecimal uLow;

    @Column(name = "U_CLOSE")
    private BigDecimal uClose;

    @Column(name = "U_VOLUME")
    private BigDecimal uVolume;

    @Column(name = "UNADJUSTED_VOLUME")
    private BigDecimal unadjustedVolume;

    @Column(name = "PRICE_CHANGE")
    private BigDecimal priceChange;

    @Column(name = "CHANGE_PERCENT")
    private BigDecimal changePercent;

    @Column(name = "VWAP")
    private BigDecimal vwap;

    @Column(name = "LABEL")
    private String label;

    @Column(name = "CHANGE_OVER_TIME")
    private BigDecimal changeOverTime;

    public Price() {
    }

    public Price(Chart chart, Company company) {
        this.company = company;
        this.date = chart.getDate();
        this.open = chart.getOpen();
        this.high = chart.getHigh();
        this.low = chart.getLow();
        this.close = chart.getClose();
        this.volume = chart.getVolume();
        this.uOpen = chart.getuOpen();
        this.uHigh = chart.getuHigh();
        this.uLow = chart.getuLow();
        this.uClose = chart.getuClose();
        this.uVolume = chart.getuVolume();
        this.unadjustedVolume = chart.getUnadjustedVolume();
        this.priceChange = chart.getChange();
        this.changePercent = chart.getChangePercent();
        this.vwap = chart.getVwap();
        this.label = chart.getLabel();
        this.changeOverTime = chart.getMarketChangeOverTime();
        this.retrivedDate = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss").format(new Date()) ;
    }

    public Price(Price price) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getuOpen() {
        return uOpen;
    }

    public void setuOpen(BigDecimal uOpen) {
        this.uOpen = uOpen;
    }

    public BigDecimal getuHigh() {
        return uHigh;
    }

    public void setuHigh(BigDecimal uHigh) {
        this.uHigh = uHigh;
    }

    public BigDecimal getuLow() {
        return uLow;
    }

    public void setuLow(BigDecimal uLow) {
        this.uLow = uLow;
    }

    public BigDecimal getuClose() {
        return uClose;
    }

    public void setuClose(BigDecimal uClose) {
        this.uClose = uClose;
    }

    public BigDecimal getuVolume() {
        return uVolume;
    }

    public void setuVolume(BigDecimal uVolume) {
        this.uVolume = uVolume;
    }

    public BigDecimal getUnadjustedVolume() {
        return unadjustedVolume;
    }

    public void setUnadjustedVolume(BigDecimal unadjustedVolume) {
        this.unadjustedVolume = unadjustedVolume;
    }

    public BigDecimal getChange() {
        return priceChange;
    }

    public void setChange(BigDecimal change) {
        this.priceChange = change;
    }

    public BigDecimal getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(BigDecimal changePercent) {
        this.changePercent = changePercent;
    }

    public BigDecimal getVwap() {
        return vwap;
    }

    public void setVwap(BigDecimal vwap) {
        this.vwap = vwap;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRetrivedDate() {
        return retrivedDate;
    }

    public void setRetrivedDate(String retrivedDate) {
        this.retrivedDate = retrivedDate;
    }

    public BigDecimal getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(BigDecimal priceChange) {
        this.priceChange = priceChange;
    }

    public BigDecimal getChangeOverTime() {
        return changeOverTime;
    }

    public void setChangeOverTime(BigDecimal changeOverTime) {
        this.changeOverTime = changeOverTime;
    }
}
