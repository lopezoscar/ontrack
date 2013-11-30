package com.sappe.ontrack.soa.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;


@Path("places")
@Component
public class PlacesService {
	
	String csvFile = "C:/iglesias.csv";
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";

	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("getAllPlaces")
	public Response getAllPlaces(){
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		try {
			
			File file = new File(csvFile);
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
	 
			        // use comma as separator
				String[] place = line.split(cvsSplitBy);
				PlaceModel placeModel = new PlaceModel();
				try{
					placeModel.setPrecint(place[0]);
					placeModel.setName(place[1]);
					placeModel.setAddress(place[2]);
					placeModel.setPostalCode(place[3]);
					placeModel.setNumber(place[4]);
				}catch(IndexOutOfBoundsException noExistDataException){
//					System.out.println(placeModel);
					sb.append(toJSON(placeModel)+",");
					System.out.println(toJSON(placeModel)+",");
					
					continue;
				}
//				System.out.println(placeModel);
				System.out.println(toJSON(placeModel)+",");
				sb.append(toJSON(placeModel)+",");
				
//				System.out.println("Country [code= " + place[0] 
//	                                 + " , name=" + place[1] + "]");
	 
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		sb.append("{}");
		sb.append("]");
		String json = sb.toString();
		return Response.ok().header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept").header("Access-Control-Allow-Methods","POST,GET").entity(json).build();
		
	}

	private String toJSON(Object obj){
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(obj);
			return json;
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
