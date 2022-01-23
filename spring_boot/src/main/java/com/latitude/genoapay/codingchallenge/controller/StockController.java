package com.latitude.genoapay.codingchallenge.controller;

import com.latitude.genoapay.codingchallenge.errors.LessThanTwoPricesException;
import com.latitude.genoapay.codingchallenge.errors.NegativePricesException;
import com.latitude.genoapay.codingchallenge.model.StockRequest;
import com.latitude.genoapay.codingchallenge.model.StockResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.stream.IntStream;


@RestController

public class StockController {
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/getMaxProfit")
	public ResponseEntity getMaxProfit(@RequestBody StockRequest stockRequest) {
		int[] stock = stockRequest.getStockPrices();
		//as we need to buy then sell minimum two numbers are required
		if (stock == null || stock.length < 2) {
			throw new LessThanTwoPricesException("Please provide at least 2 prices");
		}


		//no negative numbers are allowed
		boolean negativePrices = IntStream.of(stock).anyMatch(x -> x < 0);
		if (negativePrices) {
			throw new NegativePricesException("Please only positive prices!");
		}

		// initialize sellValue, buyValue and maxProfit
		int buyValue = stock[0];
		int sellValue = stock[1];
		int maxProfit = 0;

		// start at i = 1 as we need to buy first
		// return a negative max profit if the prices only decreases
		// no buy or sell if price = 0
		for (int i = 1; i < stock.length; i++) {
			int potentialSellValue = stock[i];

			if ((buyValue == 0) && (potentialSellValue != 0)) {
				buyValue = potentialSellValue;
				potentialSellValue = 0;
			}
			// check potential profit
			int potentialProfit = potentialSellValue - buyValue;

			// update maxProfit
			if (maxProfit == 0)
				maxProfit = potentialProfit;
			else
				maxProfit = Math.max(maxProfit, potentialProfit);

			// update sellValue
			sellValue = Math.max(sellValue, potentialSellValue);

			// update buyValue if it's not the last price in array (buy then sell)
			if ((i != stock.length - 1) && (buyValue > 0) && (potentialSellValue > 0))
				buyValue = Math.min(buyValue, potentialSellValue);
		}

		StockResponse stockResponse = new StockResponse(stockRequest, new Date(), maxProfit, buyValue, sellValue);
		return new ResponseEntity(stockResponse, HttpStatus.OK);
	}
}