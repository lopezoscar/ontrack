package com.sappe.ontrack.sdk.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HTTPManager implements Serializable{



	/**
	 * Basic HTTP client using low level API for better performance
	 */

	private static final long serialVersionUID = 2680484725007890050L;

	/** Class logger **/
	protected static final Logger logger = Logger.getLogger(HTTPManager.class.getName());

	/** ISB Base URL address **/

	private static String baseURL = "http://localhost:8080/OnTrack-SOA";

	public static String get(String url){
		URL endpoint;
		HttpURLConnection connection = null;

		try {
			String resourceURL = baseURL+ url;
			endpoint = new URL(resourceURL);
			connection = (HttpURLConnection) endpoint.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Cache-Control", "no-cache");
			connection.setDoOutput(true);
//			connection.setConnectTimeout(Integer.parseInt(Configuration.getInstance().getConfig("bus.http.timeout")));
			connection.connect();
			return getHTTPResponse(connection.getInputStream());
		} catch (MalformedURLException e) {
			logger.log(Level.SEVERE, "Error getting resource URL",e);
		} catch (ProtocolException e) {
			logger.log(Level.SEVERE, "Error processing protocol resource URL",e);			
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error reading resource URL",e);
		} catch(Exception e){
			logger.log(Level.SEVERE, "Error getting resource URL",e);
		} finally {
			connection.disconnect();
		}
		return null;
	}

	/**
	 * Gets all HTTP response and returns the message as String
	 * @param is - HTTP response body as InputStream
	 * @return String
	 */
	private static String getHTTPResponse(InputStream is){
		try {
			BufferedReader rd  = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			StringBuffer response = new StringBuffer();
			String line = null;
			while ((line = rd.readLine()) != null){
				response.append(line);
				response.append('\n');
			}
			rd.close();
			return response.toString();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error reading HTTP response",e);
			return null;
		}
	}

	protected static void postNonBlocking(String url, String content) {
		post(url, content, false);
	}

	protected static String post(String url, String content, boolean nonBlocking){
		URL endpoint;
		HttpURLConnection connection = null;

		try {
			String resourceURL = baseURL+ url;
			logger.info("resourceURL: " + resourceURL);
			endpoint = new URL(resourceURL);
			connection = (HttpURLConnection) endpoint.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "application/xml");
			connection.setRequestProperty("Content-Type", "application/xml;charset=UTF-8");
			connection.setRequestProperty("Content-Length", ""+ content.getBytes().length);
			connection.setRequestProperty("Cache-Control", "no-cache");
			connection.setUseCaches (false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
//			connection.setConnectTimeout(Integer.parseInt(Configuration.getInstance().getConfig("bus.http.timeout")));
			//Send request
			DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
			wr.write(content.getBytes("UTF-8"));
			wr.flush();
			wr.close();
			if (nonBlocking) {
				return "0";
			} else {
				return getHTTPResponse(connection.getInputStream());
			}
		} catch (MalformedURLException e) {
			logger.log(Level.SEVERE, "Error getting resource URL",e);
		} catch (ProtocolException e) {
			logger.log(Level.SEVERE, "Error processing protocol resource URL",e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error reading resource URL",e);
		} catch (Exception e){
			logger.log(Level.SEVERE, "Error posting resource URL",e);
		} finally {
			connection.disconnect();
		}
		return null;
	}


	protected static byte[] postForm(String url, String content){
		URL endpoint;
		HttpURLConnection connection = null;
		try {
			String resourceURL = baseURL+ url;
			endpoint = new URL(resourceURL);
			connection = (HttpURLConnection) endpoint.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "application/pdf");
			connection.setRequestProperty("Content-Type", "application/xml");
			connection.setRequestProperty("Content-Length", ""+ content.getBytes().length);
			connection.setRequestProperty("Cache-Control", "no-cache");
			connection.setUseCaches (false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
//			connection.setConnectTimeout(Integer.parseInt(Configuration.getInstance().getConfig("bus.http.timeout")));
			//Send request
			DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
			wr.writeBytes(content);
			wr.flush();
			wr.close();
			return getBytes(connection.getInputStream());
		} catch (MalformedURLException e) {
			logger.log(Level.SEVERE, "Error getting resource URL",e);
		} catch (ProtocolException e) {
			logger.log(Level.SEVERE, "Error processing protocol resource URL",e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error reading resource URL",e);
		} catch (Exception e){
			logger.log(Level.SEVERE, "Error posting resource URL",e);
		} finally {
			connection.disconnect();
		}
		return null;
	}

	/**
	 * Serializes an object to XML using SYSOne serialization API.
	 * @param src - Object to serialize
	 * @return - XML serialized object as String
	 * @throws Exception if any error
	 */
	protected String toXML(Object src) throws Exception{
		return null;
	}
	/**
	 * Gets an InputStream as a byte array, commonly used to get file contents
	 * @param is - InputStream to be read.
	 * @return static byte array.
	 * @throws IOException
	 */
	public static byte[] getBytes(InputStream is) throws IOException {
	    int len;
	    int size = is.available();
	    byte[] buf;
	    if (is instanceof ByteArrayInputStream) {
	      size = is.available();
	      buf = new byte[size];
	      len = is.read(buf, 0, size);
	    } else {
	      ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      buf = new byte[size];
	      while ((len = is.read(buf, 0, size)) != -1)
	        bos.write(buf, 0, len);
	      buf = bos.toByteArray();
	    }
	    return buf;
	  }
}
