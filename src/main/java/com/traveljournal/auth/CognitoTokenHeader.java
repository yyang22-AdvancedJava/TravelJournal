package com.traveljournal.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the header section of a Cognito JSON Web Token (JWT).
 * This class captures the key ID and algorithm used for token signing.
 */
public class CognitoTokenHeader{

	/**
	 * The Key ID (kid) that indicates which public key was used to secure the JWS.
	 */
	@JsonProperty("kid")
	private String kid;

	/**
	 * The cryptographic algorithm (alg) used to secure the JWS.
	 */
	@JsonProperty("alg")
	private String alg;

	/**
	 * Returns the Key ID (kid).
	 *
	 * @return a string representing the key identifier.
	 */
	public String getKid(){
		return kid;
	}

	/**
	 * Returns the signing algorithm (alg).
	 *
	 * @return a string representing the cryptographic algorithm used.
	 */
	public String getAlg(){
		return alg;
	}
}