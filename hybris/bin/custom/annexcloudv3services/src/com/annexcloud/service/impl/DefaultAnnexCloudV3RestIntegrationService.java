/*
 *
 */
package com.annexcloud.service.impl;

import com.annex.cloud.loyalty.ACRequestDetails;
import com.annex.cloud.v3.api.response.ACResponse;
import com.annexcloud.service.AnnexCloudRestIntegrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import de.hybris.platform.util.Config;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

import static com.annexcloud.constants.AnnexCloudV3Constants.ANNEXCLOUD_SITE_API_URL;

/**
 * Annex Cloud Integration Service.
 */
public class DefaultAnnexCloudV3RestIntegrationService extends AbstractV3RestIntegrationService implements AnnexCloudRestIntegrationService
{
	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(DefaultAnnexCloudV3RestIntegrationService.class);

	/** The Constant CONNECT_TIMEOUT. */
	// Determines the timeout in milliseconds until a connection is established.
	private static final int CONNECT_TIMEOUT = 30000;

	/** The Constant REQUEST_TIMEOUT. */
	// The timeout when requesting a connection from the connection manager.
	private static final int REQUEST_TIMEOUT = 30000;

	/** The rest template. */
	private RestTemplate restTemplate;

	/** The response. */
	private ACResponse response;

	/** The http method. */
	private HttpMethod httpMethod;

	private ResponseErrorHandler responseErrorHandler;

	private String url_properties;

	/**
	 * Execute annex cloud response.
	 *
	 * @param requestObject
	 *           the request object
	 *
	 *  the api name
	 * @return the annex cloud response
	 */

	public ACResponse execute(final ACRequestDetails requestObject)
	{
 		ACResponse annexCloudResponse = getResponse();
 		try
		{
			final ResponseEntity<Object> responseEntity = upload(requestObject);
			final String json = (new Gson()).toJson(responseEntity.getBody());
			LOG.info("Response-------" + toString(responseEntity.getBody()));
			annexCloudResponse = (new Gson()).fromJson(json, getResponse().getClass());
			if (responseEntity.getStatusCode().is2xxSuccessful())
			{
				annexCloudResponse.setRequestHasSucceeded(true);
			}
			else
			{
				annexCloudResponse.setRequestHasSucceeded(false);
				LOG.error("Error in request " + annexCloudResponse.getErrorCode() + "  " + annexCloudResponse.getErrorMessage());
			}
		}
		catch (final Exception exception)
		{
			annexCloudResponse.setRequestHasSucceeded(false);
			annexCloudResponse.setErrorCode("0");
			annexCloudResponse.setErrorMessage(exception.getMessage());
			LOG.error(exception.getMessage());
		}
		return annexCloudResponse;
	}

	public ResponseEntity<Object> upload(final ACRequestDetails requestObject) {
		String api = Config.getParameter(ANNEXCLOUD_SITE_API_URL);
		String url_new = api + Config.getParameter(url_properties);
		final String url = UriComponentsBuilder.fromHttpUrl(url_new).buildAndExpand(requestObject.getParams()).toUri().toString();
		LOG.info("URL---------" + url);
		//create request entity
		final HttpEntity<MultiValueMap<String, String>> requestEntity = getHttpRequrest(requestObject);
		LOG.info("Request-------" + toString(requestObject));
		//create response entity
		return getRestTemplate().exchange(url, getHTTPmethod(),	requestEntity, Object.class);
	}

	/**
	 * Gets the http requrest.
	 *
	 * @param requestObject
	 *           the request object
	 * @return the http requrest
	 */
	public HttpEntity getHttpRequrest(final ACRequestDetails requestObject)
	{
		if (requestObject.getRequest_object() != null)
		{
			return new HttpEntity(requestObject.getRequest_object(), getHeader(requestObject));
		}
		else
		{
			return new HttpEntity(getHeader(requestObject));
		}
	}

	/**
	 * To string.
	 *
	 * @param requestObject
	 *           the request object
	 * @return the string
	 */
	public String toString(final ACRequestDetails requestObject)
	{
		final ObjectMapper obj = new ObjectMapper();
		String requestString = "";
		if (requestObject.getRequest_object() != null)
		{
			try
			{
				requestString = toString(requestObject.getRequest_object());
			}
			catch (final JsonProcessingException e)
			{
				LOG.error(e.getMessage());
			}
		}
		return "AnnexCloudRequest [httpMethod=" + getHttpMethod() + ", access_code=" + requestObject.getAccess_code() + ", siteId="
				+ requestObject.getSiteId() + ", " + Arrays.asList(requestObject.getParams()) + ", content_type="
				+ MediaType.APPLICATION_JSON + ",\n ----------- request_object=" + requestString + "]";
	}

	public static String toString(final Object object) throws JsonSyntaxException, JsonProcessingException
	{
		final ObjectMapper obj = new ObjectMapper();
		final Gson gson = new GsonBuilder().setPrettyPrinting().create();
		final JsonParser jp = new JsonParser();
		final JsonElement je = jp.parse(obj.writeValueAsString(object));
		return gson.toJson(je);
	}


	/**
	 * Gets the HTT pmethod.
	 *
	 * @return the HTT pmethod
	 */
	private HttpMethod getHTTPmethod()
	{
		if (getHttpMethod().equals(HttpMethod.PATCH))
		{
			final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			requestFactory.setConnectTimeout(CONNECT_TIMEOUT);
			requestFactory.setReadTimeout(REQUEST_TIMEOUT);
			restTemplate.setRequestFactory(requestFactory);
			return getHttpMethod();
		}
		else
		{
			return getHttpMethod();
		}
	}

	/**
	 * Gets the header.
	 *
	 * @param requestObject
	 *           the request object
	 * @return the header
	 */
	protected HttpHeaders getHeader(final ACRequestDetails requestObject)
	{
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", requestObject.getAccess_code());
		httpHeaders.set("X-AnnexCloud-Site", requestObject.getSiteId());
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
	}

	/**
	 * Gets the response.
	 *
	 * @return the response
	 */
	public ACResponse getResponse()
	{
		response.setRequestHasSucceeded(false);
		return response;
	}

	/**
	 * Sets the response.
	 *
	 * @param response
	 *           the response to set
	 */
	public void setResponse(final ACResponse response)
	{
		this.response = response;
	}

	/**
	 * Gets the rest template.
	 *
	 * @return the rest template
	 */
	public RestTemplate getRestTemplate()
	{
		restTemplate.setErrorHandler(getResponseErrorHandler());
		return restTemplate;
	}

	/**
	 * Sets the rest template.
	 *
	 * @param restTemplate
	 *           the new rest template
	 */
	@Required
	public void setRestTemplate(final RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}

	

	

	/**
	 * Gets the http method.
	 *
	 * @return the httpMethod
	 */
	public HttpMethod getHttpMethod()
	{
		return httpMethod;
	}

	/**
	 * Sets the http method.
	 *
	 * @param httpMethod
	 *           the httpMethod to set
	 */

	@Required
	public void setHttpMethod(final HttpMethod httpMethod)
	{
		this.httpMethod = httpMethod;
	}

	/**
	 * @return the responseErrorHandler
	 */
	public ResponseErrorHandler getResponseErrorHandler()
	{
		return responseErrorHandler;
	}

	/**
	 * @param responseErrorHandler
	 *           the responseErrorHandler to set
	 */
	@Required
	public void setResponseErrorHandler(final ResponseErrorHandler responseErrorHandler)
	{
		this.responseErrorHandler = responseErrorHandler;
	}
	
	public String getUrl_properties() {
		return url_properties;
	}

	public void setUrl_properties(String url_properties) {
		this.url_properties = url_properties;
	}

}
