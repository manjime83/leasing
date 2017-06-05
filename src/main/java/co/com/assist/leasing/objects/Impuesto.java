package co.com.assist.leasing.objects;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "Impuesto")
@XmlType(propOrder = { "vigencia", "nroTransaccion", "fechaDePago", "entidad" })
public class Impuesto {

	private String vigencia;
	private String nroTransaccion;
	private Date fechaDePago;
	private String entidad;

	public String getVigencia() {
		return vigencia;
	}

	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}

	public String getNroTransaccion() {
		return nroTransaccion;
	}

	public void setNroTransaccion(String nroTransaccion) {
		this.nroTransaccion = nroTransaccion;
	}

	@XmlElement
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(XmlDateAdapter.class)
	public Date getFechaDePago() {
		return fechaDePago;
	}

	public void setFechaDePago(Date fechaDePago) {
		this.fechaDePago = fechaDePago;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

}
