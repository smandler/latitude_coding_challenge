package com.latitude.genoapay.codingchallenge.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StockResponse {
    private StockRequest stockRequest;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date processedDate;
    private int maxProfit;
    private int buyValue;
    private int sellValue;

    public StockResponse(StockRequest stockRequest, Date processedDate, int maxProfit, int buyValue, int sellValue) {
        this.stockRequest = stockRequest;
        this.processedDate = processedDate;
        this.maxProfit = maxProfit;
        this.buyValue = buyValue;
        this.sellValue = sellValue;
    }

    public StockRequest getRequest() {
        return stockRequest;
    }

    public void setRequest(StockRequest stockRequest) {
        this.stockRequest = stockRequest;
    }

    public void setProcessedDate(Date processedDate) {
        this.processedDate = processedDate;
    }

    public void setMaxProfit(int maxProfit) {
        this.maxProfit = maxProfit;
    }

    public void setBuyValue(int buyValue) {
        this.buyValue = buyValue;
    }

    public void setSellValue(int sellValue) {
        this.sellValue = sellValue;
    }

    public Date getProcessedDate() {
        return processedDate;
    }

    public int getMaxProfit() {
        return maxProfit;
    }

    public int getBuyValue() {
        return buyValue;
    }

    public int getSellValue() {
        return sellValue;
    }
}
