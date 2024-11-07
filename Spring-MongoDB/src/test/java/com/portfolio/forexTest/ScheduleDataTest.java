package com.portfolio.forexTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.component.ScheduleData;
import com.portfolio.entity.ForexData;
import com.portfolio.repository.ForexDataRepository;

public class ScheduleDataTest {

	@InjectMocks
	private ScheduleData scheduleData;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private ForexDataRepository forexDataRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetData() throws Exception {
		List<Map<String, String>> testData = new ArrayList<>();
		Map<String, String> forexRate = new HashMap<>();
		forexRate.put("Date", "2024-10-01");
		forexRate.put("USD/NTD", "31.837");
		forexRate.put("RMB/NTD", "4.533239");
		forexRate.put("EUR/USD", "1.11025");
		forexRate.put("USD/JPY", "143.815");
		forexRate.put("GBP/USD", "1.33305");
		forexRate.put("AUD/USD", "0.69025");
		forexRate.put("USD/HKD", "7.77435");
		forexRate.put("USD/RMB", "7.023");
		forexRate.put("USD/ZAR", "17.32885");
		forexRate.put("NZD/USD", "0.63075");
		testData.add(forexRate);

		ObjectMapper objectMapper = new ObjectMapper();
		byte[] responseBody = objectMapper.writeValueAsBytes(testData);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);
		when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(byte[].class)))
				.thenReturn(responseEntity);

		scheduleData.getData();

		verify(restTemplate, times(1)).exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class),
				eq(byte[].class));
		verify(forexDataRepository, times(1)).save(any(ForexData.class));
	}

	/** No data received from API */
	@Test
	void testGetDataWithEmptyResponse() throws Exception {
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(new byte[0], HttpStatus.OK);
		when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(byte[].class)))
				.thenReturn(responseEntity);

		scheduleData.getData();

		verify(restTemplate, times(1)).exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class),
				eq(byte[].class));
		verify(forexDataRepository, never()).save(any(ForexData.class));
	}

	/** 測試 Exception
	 * java.lang.RuntimeException: API Error */
	@Test
	void testGetDataWithException() throws Exception {
		when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(byte[].class)))
				.thenThrow(new RuntimeException("API Error"));

		scheduleData.getData();

		verify(restTemplate, times(1)).exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class),
				eq(byte[].class));
		verify(forexDataRepository, never()).save(any(ForexData.class));
	}
}