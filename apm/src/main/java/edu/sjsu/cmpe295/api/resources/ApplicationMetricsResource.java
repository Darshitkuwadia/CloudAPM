package edu.sjsu.cmpe295.api.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.sjsu.cmpe295.dto.ApplicationMetrics;
//import org.codehaus.jettison.json.JSONObject;

@Path("ametrics")
public class ApplicationMetricsResource {
	
	ApplicationMetrics am = new ApplicationMetrics();
	
	@GET
	@Path("/aids")
	@Produces( { MediaType.APPLICATION_JSON})
	public String getApplicationIDs() {
	ApplicationMetrics am = new ApplicationMetrics();
	return am.getApplicationIDs();
			
	}
	

	/*
	 * 
	 * Apdex Score 
	 * 
	 * 
	 */
	@GET
	@Path("/apdex")
	@Produces( { MediaType.APPLICATION_JSON})
	public String getApdexScore() {
	ApplicationMetrics am = new ApplicationMetrics();
	return am.getApdexScore();		
	}
	
	@GET
	@Path("/averageThroughput")
	@Produces( { MediaType.APPLICATION_JSON})
	public String getAverageThroughputApp() {
	ApplicationMetrics am = new ApplicationMetrics();
	return am.getAverageThroughputApp();
			
	}
	
	
	
}
