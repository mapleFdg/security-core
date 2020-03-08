package com.maple.security.core.properties;

/**
 * 
 * @author hzc
 *
 */
public class OAuth2Properties {

	/**
	 * 使用jwt时为token签名的秘钥
	 * 
	 */
	private String jwtSigningKey = "maple";
	
	private String storeType;
	
	private OAuth2ClientProperties clients[];

	public OAuth2ClientProperties[] getClients() {
		return clients;
	}

	public void setClients(OAuth2ClientProperties clients[]) {
		this.clients = clients;
	}

	public String getJwtSigningKey() {
		return jwtSigningKey;
	}

	public void setJwtSigningKey(String jwtSigningKey) {
		this.jwtSigningKey = jwtSigningKey;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

}
