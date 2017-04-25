package co.com.assist.leasing.objects;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Comparendo {

	private String numeroComparendo;
	private Date fechaHoraComparendo;
	private String direccion;
	private Boolean electronico;
	private String fuente;
	private String secretaria;
	private String agente;

	private String numeroResolucion;
	private Date fechaResolucion;
	private String comparendo;
	private Date fechaComparendo;
	private String nombreSecretaria;
	private String estado;
	private String codigoInfraccion;
	private String descripcionInfraccion;
	private BigDecimal valor;
	private BigDecimal interesMora;
	private BigDecimal valorAdicional;
	private BigDecimal valorAPagor;

	private String placasVehiculo;

	private String municipio;

	public String getNumeroComparendo() {
		return numeroComparendo;
	}

	public void setNumeroComparendo(String numeroComparendo) {
		this.numeroComparendo = numeroComparendo;
	}

	@XmlElement
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(XmlDateAdapter.class)
	public Date getFechaHoraComparendo() {
		return fechaHoraComparendo;
	}

	public void setFechaHoraComparendo(Date fechaHoraComparendo) {
		this.fechaHoraComparendo = fechaHoraComparendo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Boolean getElectronico() {
		return electronico;
	}

	public void setElectronico(Boolean electronico) {
		this.electronico = electronico;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(String secretaria) {
		this.secretaria = secretaria;
	}

	public String getAgente() {
		return agente;
	}

	public void setAgente(String agente) {
		this.agente = agente;
	}

	public String getNumeroResolucion() {
		return numeroResolucion;
	}

	public void setNumeroResolucion(String numeroResolucion) {
		this.numeroResolucion = numeroResolucion;
	}

	@XmlElement
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(XmlDateAdapter.class)
	public Date getFechaResolucion() {
		return fechaResolucion;
	}

	public void setFechaResolucion(Date fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}

	public String getComparendo() {
		return comparendo;
	}

	public void setComparendo(String comparendo) {
		this.comparendo = comparendo;
	}

	@XmlElement
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(XmlDateAdapter.class)
	public Date getFechaComparendo() {
		return fechaComparendo;
	}

	public void setFechaComparendo(Date fechaComparendo) {
		this.fechaComparendo = fechaComparendo;
	}

	public String getNombreSecretaria() {
		return nombreSecretaria;
	}

	public void setNombreSecretaria(String nombreSecretaria) {
		this.nombreSecretaria = nombreSecretaria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigoInfraccion() {
		return codigoInfraccion;
	}

	public void setCodigoInfraccion(String codigoInfraccion) {
		this.codigoInfraccion = codigoInfraccion;
	}

	public String getDescripcionInfraccion() {
		return descripcionInfraccion;
	}

	public void setDescripcionInfraccion(String descripcionInfraccion) {
		this.descripcionInfraccion = descripcionInfraccion;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getInteresMora() {
		return interesMora;
	}

	public void setInteresMora(BigDecimal interesMora) {
		this.interesMora = interesMora;
	}

	public BigDecimal getValorAdicional() {
		return valorAdicional;
	}

	public void setValorAdicional(BigDecimal valorAdicional) {
		this.valorAdicional = valorAdicional;
	}

	public BigDecimal getValorAPagor() {
		return valorAPagor;
	}

	public void setValorAPagor(BigDecimal valorAPagor) {
		this.valorAPagor = valorAPagor;
	}

	public String getPlacasVehiculo() {
		return placasVehiculo;
	}

	public void setPlacasVehiculo(String placasVehiculo) {
		this.placasVehiculo = placasVehiculo;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

}
