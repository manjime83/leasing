package co.com.assist.leasing.objects;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "Impuesto")
@XmlType(propOrder = { "vigencia", "nroTransaccion", "fechaDePago", "fechaDeVencimiento", "valorPagar", "soporte",
		"entidad" })
public class Impuesto {

	private String vigencia;
	private String nroTransaccion;
	private Date fechaDePago;
	private Date fechaDeVencimiento;
	private BigDecimal valorPagar;
	private String soporte;
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

	@XmlElement
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(XmlDateAdapter.class)
	public Date getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}

	public void setFechaDeVencimiento(Date fechaDeVencimiento) {
		this.fechaDeVencimiento = fechaDeVencimiento;
	}

	public String getSoporte() {
		return soporte;
	}

	public void setSoporte(String soporte) {
		this.soporte = soporte;
	}

	public BigDecimal getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(BigDecimal valorPagar) {
		this.valorPagar = valorPagar;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

}
