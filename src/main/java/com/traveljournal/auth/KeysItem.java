package com.traveljournal.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an individual JSON Web Key (JWK) within a key set.
 * This class contains the cryptographic properties required to verify
 * JSON Web Tokens (JWT).
 */
public class KeysItem{

	/**
	 * The family of cryptographic algorithms used with the key (e.g., "RSA").
	 */
	@JsonProperty("kty")
	private String kty;

	/**
	 * The exponent value for the RSA public key.
	 */
	@JsonProperty("e")
	private String E;

	/**
	 * The intended use of the public key (e.g., "sig" for signature).
	 */
	@JsonProperty("use")
	private String use;

	/**
	 * The unique identifier for the key.
	 */
	@JsonProperty("kid")
	private String kid;

	/**
	 * The specific algorithm intended for use with the key (e.g., "RS256").
	 */
	@JsonProperty("alg")
	private String alg;

	/**
	 * The modulus value for the RSA public key.
	 */
	@JsonProperty("n")
	private String N;

	/**
	 * Returns the key type (kty).
	 * @return the cryptographic algorithm family.
	 */
	public String getKty() {
		return kty;
	}

	/**
	 * Returns the RSA public exponent (e).
	 * @return the exponent string.
	 */
	public String getE() {
		return E;
	}

	/**
	 * Returns the intended use of the key (use).
	 * @return the usage type, such as "sig".
	 */
	public String getUse() {
		return use;
	}

	/**
	 * Returns the unique key ID (kid).
	 * @return the unique identifier for this key.
	 */
	public String getKid() {
		return kid;
	}

	/**
	 * Returns the specific algorithm (alg) for this key.
	 * @return the algorithm name.
	 */
	public String getAlg() {
		return alg;
	}

	/**
	 * Returns the RSA modulus (n).
	 * @return the modulus string.
	 */
	public String getN() {
		return N;
	}
}