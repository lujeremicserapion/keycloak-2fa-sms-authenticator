package dasniko.keycloak.authenticator.gateway;

// Import classes:
import sendinblue.*;
import sendinblue.auth.*;
import sibModel.*;
import sibApi.*;

import java.io.File;
import java.util.*;



//import java.util.HashMap;
import java.util.Map;

/**
 * @author Luka Jeremic, https://www.serapion.net
 */
public class SendInBlueSmsService implements SmsService {
	private final TransactionalSmsApi smsApi = new TransactionalSmsApi();

	private final String senderId;

	SendInBlueSmsService(Map<String, String> config) {
		senderId = config.get("senderId");
	}

	@Override
	public void send(String phoneNumber, String message) {
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		// Configure API key authorization: api-key
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
	    apiKey.setApiKey(senderId);
        // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
        //apiKey.setApiKeyPrefix("Token");

        // Configure API key authorization: partner-key
        //ApiKeyAuth partnerKey = (ApiKeyAuth) defaultClient.getAuthentication("partner-key");
        //partnerKey.setApiKey("YOUR PARTNER KEY");
        // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
        //partnerKey.setApiKeyPrefix("Token");


		smsApi.setApiClient(defaultClient);

		SendTransacSms sendTransacSms = new SendTransacSms(); // SendTransacSms | Values to send a transactional SMS
		sendTransacSms.setSender("test");
		sendTransacSms.setRecipient(phoneNumber);
		sendTransacSms.setContent(message);
		try {
			SendSms result = smsApi.sendTransacSms(sendTransacSms);
			System.out.println(result);
		} catch (ApiException e) {
			System.err.println("Exception when calling TransactionalSmsApi#sendTransacSms");
			e.printStackTrace();
		}
	}

}
