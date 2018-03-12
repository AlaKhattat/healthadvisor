package com.healthadvisor.entities;

import Decoder.BASE64Encoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class AccessToken {
	
	/// <summary>
    /// Token string
    /// </summary>
	public String Token;
    
	/// <summary>
    /// Valid period of token in seconds
    /// </summary>
	public int ValidThrough;
        
        
        public String getToken(){
        String password="ftHALLrv83JCfdA5";
String url="https://authservice.priaid.ch/login";
String username="firas_mrad";
CloseableHttpClient httpclient=HttpClients.createDefault();
AccessToken token=new AccessToken();
		SecretKeySpec keySpec = new SecretKeySpec(password.getBytes(),"HmacMD5");

		String computedHashString = "";
		try {
			Mac mac = Mac.getInstance("HmacMD5");
			mac.init(keySpec);
			byte[] result = mac.doFinal(url.getBytes());
			
	        BASE64Encoder encoder = new BASE64Encoder();
	        computedHashString = encoder.encode(result); 
	        
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			                 System.out.println("Can not create token (NoSuchAlgorithmException)");
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Can not create token (InvalidKeyException)");
		}
		
		HttpPost httpPost = new HttpPost(url);	
		httpPost.setHeader("Authorization", "Bearer " + username + ":" + computedHashString);
		
		try {
			CloseableHttpResponse response = httpclient.execute(httpPost);
			
			ObjectMapper objectMapper = new ObjectMapper();
			if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
				//RetrieveException(response, objectMapper);
                                System.out.println("Errrrrrrrrreeeeeeeeeeeeeeeuuuuuuuuuuuuur");
			}
			AccessToken accessToken = objectMapper.readValue(response.getEntity().getContent(), AccessToken.class);
			token = accessToken;
                        return token.Token;
		} 
		catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Can not create token (ClientProtocolException)");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Can not create token (IOException)");
		}
                return "";
        }
}
