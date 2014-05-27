package com.sappe.ontrack.test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DiasHabilesTset {

	private static final int SUNDAY = 0;
	private static final int SATURDAY = 6;

	@Test
	public void test() throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		Date from = format.parse("20131002"); //el día debería ser el jueves 17/10
		//		Date to = format.parse("20131101");

		int cantHabiles = 10;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(from);
		cal.add(Calendar.DATE, cantHabiles*2);
		Date to = cal.getTime();

		Date proximoDiaHabil = diaHabilLimite(from, to,cantHabiles);

		int dias = getDias(from,proximoDiaHabil);
		System.out.println(dias);
		
		System.out.println(format.format(proximoDiaHabil));
	}

	public int getDias(Date fInicial, Date fFinal)
	{
		Calendar ci = Calendar.getInstance();
		ci.setTime(fInicial);

		Calendar cf = Calendar.getInstance();
		cf.setTime(fFinal);

		long ntime = cf.getTimeInMillis() - ci.getTimeInMillis();

		return (int)Math.ceil((double)ntime / 1000 / 3600 / 24);
	}

	private List<Date> feriados() throws ParseException{
		List<Date> feriados = new ArrayList<Date>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			File file = new File("feriados.xml");
			Document doc = builder.parse(file);
			NodeList nList = doc.getElementsByTagName("row");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String fecha = eElement.getElementsByTagName("fecha").item(0).getTextContent();
					SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
					Date date = format.parse(fecha);
					feriados.add(date);
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return feriados;
	}

	@SuppressWarnings({ "deprecation" })
	public Date diaHabilLimite(Date desde, Date hasta,int limit) throws Exception{
		List<Date> habiles = new ArrayList<Date>();
		Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("America/Argentina"));
		int diasHabiles = 0;
		try {
			List<Date> feriados = feriados();
			if(feriados != null && !feriados.isEmpty()){
				List<Date> dates = getDatesBetween(desde, hasta);
				for (Date date : dates) {
					if(!feriados.contains(date) && !(date.getDay() == SUNDAY || date.getDay() == SATURDAY)){
						diasHabiles++;
						habiles.add(date);
						if(diasHabiles == limit){
							calendar.setTime(date);
							System.out.println("El último día hábil "+date);
						}
					}
				}
			}
		} catch (ParseException e) {
			throw new Exception(e);
		}
		
		
		return calendar.getTime();
	}
	
	
	@SuppressWarnings({ "deprecation" })
	public List<Date> getDatesBetween(final Date date1, final Date date2) {
		List<Date> dates = new ArrayList<Date>();

		Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("America/Argentina"));
		calendar.set(Calendar.YEAR, date1.getYear());
		calendar.set(Calendar.MONTH, date1.getMonth());
		calendar.set(Calendar.DATE, date1.getDate());

		boolean fromEqualsTo = isFromEqualsTo(calendar,date2);
		while (!fromEqualsTo) {
			calendar.add(Calendar.DATE, 1);
			Date current = new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
			dates.add(current);
			fromEqualsTo = isFromEqualsTo(calendar,date2);
		}

		return dates;
	}

	@SuppressWarnings("deprecation")
	private boolean isFromEqualsTo(Calendar calendar, Date date2){
		boolean year = calendar.get(Calendar.YEAR) == date2.getYear();
		boolean month = calendar.get(Calendar.MONTH) == date2.getMonth();
		boolean day = calendar.get(Calendar.DATE) == date2.getDate();    

		
		boolean fromEqualsTo = year && month && day;
		return fromEqualsTo;
	}


}
