package com.annexcloud.service.impl;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.annex.cloud.v3.api.response.ACResponse;

public class AbstractV3RestIntegrationService {
	/** The rest template. */
	private RestTemplate restTemplate;

	private ResponseErrorHandler responseErrorHandler;

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public ResponseErrorHandler getResponseErrorHandler() {
		return responseErrorHandler;
	}

	public void setResponseErrorHandler(ResponseErrorHandler responseErrorHandler) {
		this.responseErrorHandler = responseErrorHandler;
	}
}
