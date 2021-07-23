/**

*********  ****     ***   ****     ***  ********* **       **
*********  *****    ***   *****    ***  *********  **     **
***   ***  *** **   ***   *** **   ***  ***         **   **
***   ***  *** **   ***   *** **   ***  *********    ** **
*********  ***  **  ***   ***  **  ***  *********     **
***   ***  ***   ** ***   ***   ** ***  ***         ** **
***   ***  ***    * ***   ***    * ***  *********  **   **
***   ***  ***     ****   ***     ****  ********* **     **


 ********   ***          *******    ***   ***   ********
*********   ***         *********   ***   ***   *********
***         ***         ***   ***   ***   ***   ***   ***
***         ***         ***   ***   ***   ***   ***   ***
***         ***         ***   ***   ***   ***   ***   ***
***         ***         ***   ***   ***   ***   ***   ***
*********   *********   *********   *********   *********
 ********   *********    *******     *******    ********


 Annex cloud Copyright (c) 2019
 All software and accompanying documents that you download from Annex Cloud
 are the copyrighted work of Annex Cloud and/or its suppliers. Your use of
 the Software is governed by the terms of the software license agreement
 applicable to the Software ("License Agreement"). You are not authorized to
 install or use any Software unless you first agree to the License Agreement
 terms. All rights, title, and interest to the Software not expressly granted
 are reserved.*/
package com.annexcloud.impl;


import static com.annexcloud.constants.AnnexCloudV3Constants.ALG;
import static com.annexcloud.constants.AnnexCloudV3Constants.ANNEXCLOUD_SITE_API_TOKEN;
import static com.annexcloud.constants.AnnexCloudV3Constants.HMAC;
import static com.annexcloud.constants.AnnexCloudV3Constants.HS256;
import static com.annexcloud.constants.AnnexCloudV3Constants.JWT;
import static com.annexcloud.constants.AnnexCloudV3Constants.SITEID;
import static com.annexcloud.constants.AnnexCloudV3Constants.TYP;
import static com.annexcloud.constants.AnnexCloudV3Constants.UTF;

import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.Config;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import com.annex.cloud.loyalty.jwt.ACJwtRequest;
import com.annexcloud.ACRequestEncodeService;
import com.annexcloud.AnnexCloudJWTService;
import com.annexcloud.model.AnnexCloudModel;
import com.annexcloud.service.AnnexCloudLoyaltyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.annex.cloud.backoffice.jwt.ACBackofficeJwtRequest;
import com.annexcloud.ACBackofficeJwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * The Class DefaultAnnexCloudJWTService.
 */
public class DefaultAnnexCloudJWTService implements AnnexCloudJWTService, ACRequestEncodeService, ACBackofficeJwtService
{

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(DefaultAnnexCloudJWTService.class);

	/** The object mapper. */
	private ObjectMapper objectMapper;

	/** The base store service. */
	private BaseStoreService baseStoreService;

	/** The common I 18 N service. */
	private CommonI18NService commonI18NService;

	/** The annex cloud loyalty service. */
	private AnnexCloudLoyaltyService annexCloudLoyaltyService;

	/**
	 * Abstract hmac creation.
	 *
	 * @param encodedPayload
	 *           the encoded payload
	 * @param annexCloudModel
	 *           the annex cloud model
	 * @return the string
	 */
	private String abstractHmacCreation(final String encodedPayload, final AnnexCloudModel annexCloudModel)
	{
		final String accessToken = Config.getParameter(annexCloudModel.getCode().toLowerCase() + "." + ANNEXCLOUD_SITE_API_TOKEN);
		final String hmac = hmacEncode(encodedPayload, accessToken);
		LOG.info("hmac : " + hmac);
		try
		{
			final Calendar now = Calendar.getInstance();
			now.add(Calendar.MINUTE, 3);
			final long timeInmillis = now.getTimeInMillis();

			final String token = Jwts.builder().setHeaderParam(TYP, JWT).setHeaderParam(ALG, HS256)
					.setSubject(annexCloudModel.getCode()).setExpiration(Date.from(Instant.ofEpochSecond(timeInmillis)))
					.claim(SITEID, annexCloudModel.getSiteId()).claim(HMAC, hmac)
					.signWith(SignatureAlgorithm.HS256, accessToken.getBytes(UTF)).compact();
			if(LOG.isDebugEnabled())
			{
				LOG.info("timeInmillis : " + timeInmillis);
				LOG.info("Token : " + token);
			}
			return token;
		}
		catch (final Exception e)
		{
			LOG.error(e);
			return "";
		}
	}


	private String backofficeHmacCreation(final String encodedPayload, final AnnexCloudModel annexCloudModel, final ACBackofficeJwtRequest jwtRequest)
	{
		final String accessToken = Config.getParameter(annexCloudModel.getCode().toLowerCase() + "." + ANNEXCLOUD_SITE_API_TOKEN);
		final String hmac = hmacEncode(encodedPayload, accessToken);
		LOG.info("hmac : " + hmac);
		try
		{
			final Calendar now = Calendar.getInstance();
			now.add(Calendar.MINUTE, 3);
			final long timeInmillis = now.getTimeInMillis();
			final String token = Jwts.builder().setHeaderParam(TYP, JWT).setHeaderParam(ALG, HS256)
					.setSubject(annexCloudModel.getCode()).setExpiration(Date.from(Instant.ofEpochSecond(timeInmillis)))
					.claim(SITEID, annexCloudModel.getSiteId())
					.claim("siteName",jwtRequest.getSiteName())
					.claim("userId",jwtRequest.getUserId())
					.claim("role",jwtRequest.getRole())
					.claim(HMAC, hmac)
					.signWith(SignatureAlgorithm.HS256, accessToken.getBytes(UTF)).compact();
			if(LOG.isDebugEnabled())
			{
				LOG.info("timeInmillis : " + timeInmillis);
				LOG.info("Token : " + token);
			}
			return token;
		}
		catch (final Exception e)
		{
			LOG.error("Exception occurred while creating bearer token ", e);
			return "";
		}
	}

	/**
	 * Abstract token creation.
	 *
	 * @param payload
	 *           the payload
	 * @param annexCloudModel
	 *           the annex cloud model
	 * @return the string
	 */
	private String abstractTokenCreation(final Object payload, final AnnexCloudModel annexCloudModel)
	{

		final String encodedPayload = encodePayLoad(payload);
		if(LOG.isDebugEnabled())
		{
			LOG.info(" Site Id :" + annexCloudModel.getSiteId());
			LOG.info("encoded Payload : " + encodedPayload);
		}
		return abstractHmacCreation(encodedPayload, annexCloudModel);
	}

	@Override
	public String backoffieTokenCreation(final ACBackofficeJwtRequest payload, final AnnexCloudModel annexCloudModel)
	{

		final String encodedPayload = encodePayLoad(payload);
		if(LOG.isDebugEnabled())
		{
			LOG.info("encoded Payload : " + encodedPayload);
			LOG.info(" Site Id :" + annexCloudModel.getSiteId());
		}
		return backofficeHmacCreation(encodedPayload, annexCloudModel,payload);
	}

	/**
	 * Creates the token.
	 *
	 * @param payload
	 *           the payload
	 * @param annexCloudModel
	 *           the annex cloud model
	 * @return the string
	 */
	@Override
	public String createToken(final String payload, final AnnexCloudModel annexCloudModel)
	{

		final String encodedPayload = encodePayLoad(payload);
			if(LOG.isDebugEnabled()){
				LOG.info(" Site Id :" + annexCloudModel.getSiteId());
				LOG.info("encoded Payload : " + encodedPayload);
			}
		return abstractHmacCreation(encodedPayload, annexCloudModel);
	}

	/**
	 * Creates the bearer token.
	 *
	 * @param payload
	 *           the payload
	 * @return the string
	 */
	@Override
	public String createBearerToken(final ACJwtRequest payload)
	{
		final AnnexCloudModel annexCloudModel = annexCloudLoyaltyService.getAnnexCloudCredential();
		return "Bearer " + abstractTokenCreation(payload, annexCloudModel);
	}



	/**
	 * Creates the bearer token.
	 *
	 * @param payload
	 *           the payload
	 * @param annexCloudModel
	 *           the annex cloud model
	 * @return the string
	 */
	@Override
	public String createBearerToken(final Object payload, final AnnexCloudModel annexCloudModel)
	{
		return "Bearer " + abstractTokenCreation(payload, annexCloudModel);
	}


	/**
	 * Encode pay load.
	 *
	 * @param payload
	 *           the payload
	 * @return the string
	 */
	private String encodePayLoad(final String payload)
	{
		try
		{
			return Base64.getEncoder()
					.encodeToString(getObjectMapper().writeValueAsString(payload).getBytes(StandardCharsets.UTF_8.toString()));
		}
		catch (final Exception ex)
		{
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Encode pay load.
	 *
	 * @param payload
	 *           the payload
	 * @return the string
	 */
	@Override
	public String encodePayLoad(final Object payload)
	{
		try
		{
			return Base64.getEncoder()
					.encodeToString(getObjectMapper().writeValueAsString(payload).getBytes(StandardCharsets.UTF_8.toString()));
		}
		catch (final Exception ex)
		{
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Hmac encode.
	 *
	 * @param message
	 *           the message
	 * @param secret
	 *           the secret
	 * @return the string
	 */
	private String hmacEncode(final String message, final String secret)
	{
		try
		{
			final Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			final SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
			sha256_HMAC.init(secret_key);
			return org.apache.commons.codec.binary.Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
		}
		catch (final Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the object mapper.
	 *
	 * @return the object mapper
	 */
	public ObjectMapper getObjectMapper()
	{
		return objectMapper;
	}

	/**
	 * Sets the object mapper.
	 *
	 * @param objectMapper
	 *           the new object mapper
	 */
	public void setObjectMapper(final ObjectMapper objectMapper)
	{
		this.objectMapper = objectMapper;
	}

	/**
	 * Gets the base store service.
	 *
	 * @return the base store service
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/**
	 * Sets the base store service.
	 *
	 * @param baseStoreService
	 *           the new base store service
	 */
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	/**
	 * Gets the common I 18 N service.
	 *
	 * @return the common I 18 N service
	 */
	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	/**
	 * Sets the common I 18 N service.
	 *
	 * @param commonI18NService
	 *           the new common I 18 N service
	 */
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	/**
	 * Gets the annex cloud loyalty service.
	 *
	 * @return the annex cloud loyalty service
	 */
	public AnnexCloudLoyaltyService getAnnexCloudLoyaltyService()
	{
		return annexCloudLoyaltyService;
	}

	/**
	 * Sets the annex cloud loyalty service.
	 *
	 * @param annexCloudLoyaltyService
	 *           the new annex cloud loyalty service
	 */
	public void setAnnexCloudLoyaltyService(final AnnexCloudLoyaltyService annexCloudLoyaltyService)
	{
		this.annexCloudLoyaltyService = annexCloudLoyaltyService;
	}


}
