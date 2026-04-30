package com.traveljournal.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the authentication tokens returned by the identity provider.
 * This class encapsulates the access, refresh, and ID tokens, along with
 * metadata regarding the token type and expiration.
 */
public class TokenResponse {

	/**
	 * The access token used to authorize requests to protected resources.
	 */
	@JsonProperty("access_token")
	private String accessToken;

	/**
	 * The token used to obtain new access tokens when the current one expires.
	 */
	@JsonProperty("refresh_token")
	private String refreshToken;

	/**
	 * The OpenID Connect (OIDC) ID token containing claims about the authenticated user.
	 */
	@JsonProperty("id_token")
	private String idToken;

	/**
	 * The type of token issued (e.g., "Bearer").
	 */
	@JsonProperty("token_type")
	private String tokenType;

	/**
	 * The remaining lifetime of the access token in seconds.
	 */
	@JsonProperty("expires_in")
	private int expiresIn;

	/**
	 * Returns the access token.
	 *
	 * @return the access token string.
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Returns the refresh token.
	 *
	 * @return the refresh token string, or null if not provided.
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * Returns the ID token.
	 *
	 * @return the JWT ID token containing user identity information.
	 */
	public String getIdToken() {
		return idToken;
	}

	/**
	 * Returns the token type.
	 *
	 * @return the type of the token, typically "Bearer".
	 */
	public String getTokenType() {
		return tokenType;
	}

	/**
	 * Returns the expiration time of the access token.
	 *
	 * @return the number of seconds until the access token expires.
	 */
	public int getExpiresIn() {
		return expiresIn;
	}
}