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
	
	/**
	 * 
	 * Average Throughput 
	 * @return
	 */
	
	@GET
	@Path("/averageThroughput")
	@Produces( { MediaType.APPLICATION_JSON})
	public String getAverageThroughputApp() {
	ApplicationMetrics am = new ApplicationMetrics();
	return am.getAverageThroughputApp();
			
	}
	
	@GET
	@Path("/timeRange")
	@Produces({ MediaType.APPLICATION_JSON})
	public String getTimeRange() {
		ApplicationMetrics am = new ApplicationMetrics();
		return am.getTimeRange();
				
		}
	
	@GET
	@Path("/summarize")
	@Produces({ MediaType.APPLICATION_JSON})
	public String summarize() {
		ApplicationMetrics am = new ApplicationMetrics();
		return am.summarize();
				
		}
	
	
	//Application error rate 
	
	@GET
	@Path("/errorRate")
	@Produces({ MediaType.APPLICATION_JSON})
	public String getErrorRate() {
		ApplicationMetrics am = new ApplicationMetrics();
		return am.getErrorRate();
				
		}
	
	@GET
	@Path("/errorCount")
	@Produces({ MediaType.APPLICATION_JSON})
	public String getErrorCount() {
		ApplicationMetrics am = new ApplicationMetrics();
		return am.getErrorCount();
				
		}
	@GET
	@Path("/average")
	@Produces({ MediaType.APPLICATION_JSON})
	public String getAverage() {
		ApplicationMetrics am = new ApplicationMetrics();
		return am.getAverage();
				
		}
	
	@GET
	@Path("/callCount")
	@Produces({ MediaType.APPLICATION_JSON})
	public String getCallCount() {
		ApplicationMetrics am = new ApplicationMetrics();
		return am.getCallCount();
				
		}
	@GET
	@Path("/avgResponseTime")
	@Produces({ MediaType.APPLICATION_JSON})
	public String getAverageResponseTime() {
		ApplicationMetrics am = new ApplicationMetrics();
		return am.getAverageResponseTime();
				
		}
	
}
