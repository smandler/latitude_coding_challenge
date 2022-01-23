package com.latitude.genoapay.codingchallenge;

import com.latitude.genoapay.codingchallenge.model.StockRequest;
import com.latitude.genoapay.codingchallenge.model.StockResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CodingChallengeApplicationTests {
	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;

	@Test
	void contextLoads(ApplicationContext context) {
		assertThat(context).isNotNull();
	}

	@Test
	public void whenArrayLengthLessThan2() {
		StockRequest request = new StockRequest("1", new Date(), new Date(), new int[]{10});
		ResponseEntity<StockResponse> response = testRestTemplate.postForEntity("http://localhost:" + port + "/getMaxProfit", request, StockResponse.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Test
	public void checkMaxProfit() {
		StockRequest request = new StockRequest("1", new Date(), new Date(), new int[]{10, 7, 5, 8, 11, 4});
		ResponseEntity<StockResponse> response = testRestTemplate.postForEntity("http://localhost:" + port + "/getMaxProfit", request, StockResponse.class);

		assertThat(response.getBody().getMaxProfit()).isEqualTo(6);

	}

	@Test
	public void checkNegativeProfit() {
		StockRequest request = new StockRequest("1", new Date(), new Date(), new int[]{10, 7, 5, 4, 3, -2});
		ResponseEntity<StockResponse> response = testRestTemplate.postForEntity("http://localhost:" + port + "/getMaxProfit", request, StockResponse.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Test
	public void checkSellValue() {
		StockRequest request = new StockRequest("1", new Date(), new Date(), new int[]{10, 7, 5, 8, 11, 4});
		ResponseEntity<StockResponse> response = testRestTemplate.postForEntity("http://localhost:" + port + "/getMaxProfit", request, StockResponse.class);

		assertThat(response.getBody().getSellValue()).isEqualTo(11);

	}

	@Test
	public void checkBuyValue() {
		StockRequest request = new StockRequest("1", new Date(), new Date(), new int[]{10, 7, 5, 8, 11, 4});
		ResponseEntity<StockResponse> response = testRestTemplate.postForEntity("http://localhost:" + port + "/getMaxProfit", request, StockResponse.class);

		assertThat(response.getBody().getBuyValue()).isEqualTo(5);

	}

	@Test
	public void checkRequest() {
		StockRequest request = new StockRequest("1", new Date(), new Date(), new int[]{10, 7, 5, 8, 11, 4});
		ResponseEntity<StockResponse> response = testRestTemplate.postForEntity("http://localhost:" + port + "/getMaxProfit", request, StockResponse.class);

		assertThat(response.getBody().getRequest()).isNotNull();
	}

	@Test
	public void checkMaxProfitWith0Prices() {
		StockRequest request = new StockRequest("1", new Date(), new Date(), new int[]{0,0,0,8,0,2});
		ResponseEntity<StockResponse> response = testRestTemplate.postForEntity("http://localhost:" + port + "/getMaxProfit", request, StockResponse.class);

		assertThat(response.getBody().getMaxProfit()).isEqualTo(-6);

	}

	@Test
	public void checkSellValueWith0Prices() {
		StockRequest request = new StockRequest("1", new Date(), new Date(), new int[]{0,0,0,8,0,2});
		ResponseEntity<StockResponse> response = testRestTemplate.postForEntity("http://localhost:" + port + "/getMaxProfit", request, StockResponse.class);

		assertThat(response.getBody().getSellValue()).isEqualTo(2);

	}

	@Test
	public void checkBuyValueWith0Prices() {
		StockRequest request = new StockRequest("1", new Date(), new Date(), new int[]{0,0,0,8,0,2});
		ResponseEntity<StockResponse> response = testRestTemplate.postForEntity("http://localhost:" + port + "/getMaxProfit", request, StockResponse.class);

		assertThat(response.getBody().getBuyValue()).isEqualTo(8);

	}

}
