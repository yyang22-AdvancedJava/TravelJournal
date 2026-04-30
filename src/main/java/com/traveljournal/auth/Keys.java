package com.traveljournal.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Keys{

	/**
	 * A container class that holds a list of JSON Web Keys (JWK).
	 * This is typically used to map the response from a JWKS (JSON Web Key Set) endpoint.
	 */
	@JsonProperty("keys")
	private List<KeysItem> keys;

	/**
	 * Returns the list of JWK items.
	 *
	 * @return a {@link List} of {@link KeysItem} objects.
	 */
	public List<KeysItem> getKeys(){
		return keys;
	}
}