package edu.sjsu.cmpe295.dto;

import java.io.StringReader;












import javax.ws.rs.core.MultivaluedMap;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.codehaus.jettison.Node;
import org.json.JSONObject;
import org.json.XML;









import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

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
		
		/**
		 * 
		 * query params to be passed as parameters  from and to 
		 * @return
		 * 
		 * The default time range for an API call is the last 30 minutes.
		 *  To modify the time range, include both from= and to= values. For example:
		 *
		 *
		 *
		 *curl -X GET "https://api.newrelic.com/v2/applications/${APPID}/metrics/data.json" \
     -H "X-Api-Key:${APIKEY}" -i \
     -d 'names[]=Agent/MetricsReported/count&from=2014-08-11T14:42:00+00:00&to=2014-08-11T15:12:00+00:00'
		 *
		 */
		public String getTimeRange() {

			try {

				webResource = client
						.resource("https://api.newrelic.com/v2/applications/"+applicationID+"/metrics/data.json");

				 MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
			     queryParams.add("names[]", "Agent/MetricsReported/count");
			   
			     queryParams.add("from",	"2015-04-01T00:00:00+00:00");
			     queryParams.add("to", "2015-04-13T00:00:00+00:00");
			    
			     
				response = webResource.queryParams(queryParams)
						.accept("application/json")
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

		
		
		
		
		
		/**
		 * Calculating average metric values (summarize)
		 * 
		 * 
		 *  To prevent summarizing data, omit summarize in your API call. 
		 *  You do not need to specify &summarize=false.
		 * @return
		 */
		
		
		public String summarize() {
			try {

				webResource = client
						.resource("https://api.newrelic.com/v2/applications/"+applicationID+"/metrics/data.json");

				 MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
			     queryParams.add("names[]", "Agent/MetricsReported/count");
			   
			     queryParams.add("from",	"2015-04-01T00:00:00+00:00");
			     queryParams.add("to", "2015-04-13T00:00:00+00:00");
			    queryParams.add("summarize","true");
			     
				response = webResource.queryParams(queryParams)
						.accept("application/json")
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
		/**
		 * 
		 * Application error rate
		 * 
		 * 
		 * Application Error Rate = 100 * Errors/all:error_count / (HttpDispatcher:call_count + OtherTransaction/all:call_count)
		 * @return
		 */
		
		public String getErrorRate() {
			
			JSONObject json = new JSONObject(getCallCount());
			System.out.println("-----------------------------");
			int callCount =json.getJSONObject("metric_data_response").getJSONObject("metric_data").getJSONObject("metrics").getJSONObject("metric")
					.getJSONObject("timeslices").getJSONObject("timeslice").getJSONObject("values").getInt("call_count")
					;
			
			
			JSONObject json1 = new JSONObject(getErrorCount());
			System.out.println("-----------------------------");
			int errorCount =json1.getJSONObject("metric_data_response").getJSONObject("metric_data").getJSONObject("metrics").getJSONObject("metric")
					.getJSONObject("timeslices").getJSONObject("timeslice").getJSONObject("values").getInt("error_count")
					;
			
			JSONObject json2 = new JSONObject(getAverage());
			System.out.println("-----------------------------");
			int otherTrans =json2.getJSONObject("metric_data_response").getJSONObject("metric_data").getJSONObject("metrics").getJSONObject("metric")
					.getJSONObject("timeslices").getJSONObject("timeslice").getJSONObject("values").getInt("call_count")
					;
		int applicationErrorRate = 100* errorCount/(callCount+otherTrans);
			System.out.println(applicationErrorRate);
			//String applicationErrorRate = 100*
			JSONObject j = new JSONObject(); 
			j.put("errorRate",applicationErrorRate);
			return j.toString();
		}
		
		/**
		 * 
		 * Error Count
		 * 
		 * curl -X GET "https://api.newrelic.com/v2/applications/${APPID}/metrics/data.xml" \
    -H "X-Api-Key:${APIKEY}" -i \
   -d 'names[]=Errors/all&values[]=error_count&from=2014-04-01T00:00:00+00:00&to=2014-04-01T23:35:00+00:00&summarize=true'
		 * 
		 * 
		 * @return
		 */
		public String getErrorCount()
		{
			try {

				webResource = client
						.resource("https://api.newrelic.com/v2/applications/"+applicationID+"/metrics/data.xml");

				 MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
			     queryParams.add("names[]", "Errors/all");
			     queryParams.add("values[]", "error_count");
			    
			     queryParams.add("from",	"2015-01-01T00:00:00+00:00");
			     queryParams.add("to", "2015-04-13T00:00:00+00:00");
			     queryParams.add("summarize", "true");   
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
		
		/**
		 * Call Count
		 * 
		 * curl -X GET "https://api.newrelic.com/v2/applications/${APPID}/metrics/data.xml" \
    -H "X-Api-Key:${APIKEY}" -i \
   -d 'names[]=HttpDispatcher&values[]=call_count&from=2014-04-01T00:00:00+00:00&to=2014-04-01T23:35:00+00:00&summarize=true'
		 * 
		 * 
		 * @return
		 */
		public String getCallCount()
		{
			try {

				webResource = client
						.resource("https://api.newrelic.com/v2/applications/"+applicationID+"/metrics/data.xml");

				 MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
			     queryParams.add("names[]", "HttpDispatcher");
			     queryParams.add("values[]", "call_count");
			    
			     queryParams.add("from",	"2015-01-01T00:00:00+00:00");
			     queryParams.add("to", "2015-04-13T00:00:00+00:00");
			     queryParams.add("summarize", "true");   
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
						

			} catch (Exception e) {

				e.printStackTrace();

			}
			String jsonOutput= convertXmlToJson(output);
			return jsonOutput;
			
		}
		
		
		
		/**
		 * Average Value
		 * 
		 * curl -X GET "https://api.newrelic.com/v2/applications/${APPID}/metrics/data.xml" \
    -H "X-Api-Key:${APIKEY}" -i \
   -d 'names[]=OtherTransaction/all&values[]=call_count&from=2014-04-01T00:00:00+00:00&to=2014-04-01T23:35:00+00:00&summarize=true'
		 * 
		 * @return
		 */
		
		public String getAverage()
		{
			try {

				webResource = client
						.resource("https://api.newrelic.com/v2/applications/"+ applicationID+"/metrics/data.xml");

				 MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
			     queryParams.add("names[]", "OtherTransaction/all");
			     queryParams.add("values[]", "call_count");
			    
			     queryParams.add("from",	"2015-01-01T00:00:00+00:00");
			     queryParams.add("to", "2015-04-13T00:00:00+00:00");
			     queryParams.add("summarize", "true");   
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
		
		
		
		/**
		 * Average response time
		 * 
		 * Application Average response time = HttpDispatcher:average_call_time
		 *  + ((WebFrontend/Queue:call_count * WebFrontend/Queue:average_response_time)/ HttpDispatcher:call_count)
		 * 
		 * 
		 */
		
		public String getAverageResponseTime() {
			
			
			
			
		return null;
		}
		
		public String gethttpDisp() {
			try {

				webResource = client
						.resource("https://api.newrelic.com/v2/applications/"+applicationID+"/metrics/data.xml");

				 MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
			     queryParams.add("names[]", "HttpDispatcher");
			     queryParams.add("values[]", "average_call_time");
			     queryParams.add("values[]", "call_count");
			    
			     queryParams.add("from",	"2015-01-01T00:00:00+00:00");
			     queryParams.add("to", "2015-04-13T00:00:00+00:00");
			     queryParams.add("summarize", "true");   
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
		public String getWebFront() {
			try {

				webResource = client
						.resource("https://api.newrelic.com/v2/applications/"+applicationID+"/metrics/data.xml");

				 MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
			     queryParams.add("names[]", "WebFrontend/QueueTime");
			     queryParams.add("values[]", "call_count");
			     queryParams.add("values[]", "average_response_time");
			     queryParams.add("from",	"2015-01-01T00:00:00+00:00");
			     queryParams.add("to", "2015-04-13T00:00:00+00:00");
			     queryParams.add("summarize", "true");   
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
		
	public static void main(String[] args)
	{
		ApplicationMetrics am = new ApplicationMetrics();
	//	am.getApplicationIDs();
	//	am.getApdexScore();
		//am.getAverageThroughputApp();
		
		
	}

	

	




}
