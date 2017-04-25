package co.com.assist.leasing.objects;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class XmlDateAdapter extends XmlAdapter<XMLGregorianCalendar, Date> {

	@Override
	public XMLGregorianCalendar marshal(Date date) throws Exception {
		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(date);
		XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		xmlDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
		return xmlDate;
	}

	@Override
	public Date unmarshal(XMLGregorianCalendar v) throws Exception {
		return v.toGregorianCalendar().getTime();
	}
}