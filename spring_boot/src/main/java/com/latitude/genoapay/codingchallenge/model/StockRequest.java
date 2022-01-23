package com.latitude.genoapay.codingchallenge.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StockRequest {
    private String id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date endDate;
    private int[] stockPrices;

    public StockRequest(String id, Date startDate, Date endDate, int[] stockPrices) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.stockPrices = stockPrices;
    }

    public String getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStockPrices(int[] stockPrices) {
        this.stockPrices = stockPrices;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int[] getStockPrices() {
        return stockPrices;
    }
}
