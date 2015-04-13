package edu.sjsu.cmpe295.dto;

import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONObject;
import org.json.XML;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class ApplicationMetrics {
	Client client = Client.create();
	WebResource webResource;
	ClientResponse response ;
	String output = null ;
	
	static String applicationID =  "6087294";
	static String xApiKey= "f09a3be01866a3be3c78e13f1cd5d172c1cb465a78c4b2e";
	
	public String getApplicationIDs()
	{

		try {

			webResource = client
					.resource("https://api.newrelic.com/v2/applications.json");


			response = webResource.accept("application/json")
					.header("X-Api-Key", xApiKey)
					.get(ClientResponse.class)
					;

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return output;
	}

	/*
	 * 
	 * curl -X GET 'https://api.newrelic.com/v2/applications/${APPID}/metrics/data.xml' \
     -H "X-Api-Key:${APIKEY}" -i \
     -d 'names[]=Apdex&names[]=EndUser/Apdex&values[]=score&from=2014-01-01T00:00:00+00:00&to=2014-01-02T00:00:00+00:00&summarize=true' 
	 */

// summarize : false --> to get all the  data
	
	public String getApdexScore() {

		try {

			webResource = client
					.resource("https://api.newrelic.com/v2/applications/"+ applicationID +"/metrics/data.xml");

			 MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		     queryParams.add("names[]", "Apdex");
		     queryParams.add("names[]", "EndUser/Apdex");
		     // optional
		    /* queryParams.add("values[]", "score");
		     queryParams.add("from",	"2015-01-01T00:00:00+00:00");
		     queryParams.add("to", "2016-01-02T00:00:00+00:00");
		     queryParams.add("summarize", "true");*/
		     
			response = webResource.queryParams(queryParams)
					.accept("application/json","application/xml" )
					.header("X-Api-Key", xApiKey)
					.get(ClientResponse.class)
					;

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}
		String jsonOutput= convertXmlToJson(output);
		return jsonOutput;
	}
	  public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	public String convertXmlToJson(String xmlString)
		{
		
		JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
        String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
        System.out.println(jsonPrettyPrintString);
        return jsonPrettyPrintString;
		}

	
		
		public String getAverageThroughputApp() {

		try {

			webResource = client
					.resource("https://api.newrelic.com/v2/applications/"+applicationID+"/metrics/data.json");

			 MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		     queryParams.add("names[]", "HttpDispatcher");
		     queryParams.add("values[]", "requests_per_minute");
		     // optional
		    /* 
		     queryParams.add("from",	"2015-01-01T00:00:00+00:00");
		     queryParams.add("to", "2016-01-02T00:00:00+00:00");
		     queryParams.add("summarize", "true");*/
		     
			response = webResource.queryParams(queryParams)
					.accept("application/json","application/xml" )
					.header("X-Api-Key", xApiKey)
					.get(ClientResponse.class)
					;

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}
		
		return output;
	}
		
	public static void main(String[] args)
	{
		ApplicationMetrics am = new ApplicationMetrics();
	//	am.getApplicationIDs();
	//	am.getApdexScore();
		am.getAverageThroughputApp();
	}


}
