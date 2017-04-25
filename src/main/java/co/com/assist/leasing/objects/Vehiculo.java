package co.com.assist.leasing.objects;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Vehiculo")
@XmlType(propOrder = { "placa", "numeroLicenciaTransito", "estado", "tipoServicio", "claseVehiculo", "marca", "modelo", "linea", "color", "numeroSerie",
		"numeroMotor", "numeroChasis", "vin", "fechaMatricula", "autoridadTransito", "soatNumero", "soatFechaExpedicion", "soatVigencia", "soatEntidad",
		"soatEstado", "rtmExpedicion", "rtmVigencia", "rtmEntidad", "rtmEstado" })
public class Vehiculo {

	private String placa;
	private String numeroLicenciaTransito;
	private String estado;
	private String tipoServicio;
	private String claseVehiculo;

	private String marca;
	private String modelo;
	private String linea;
	private String color;
	private String numeroSerie;
	private String numeroMotor;
	private String numeroChasis;
	private String vin;

	private Date fechaMatricula;
	private String autoridadTransito;

	private String soatNumero;
	private Date soatFechaExpedicion;
	private Date soatVigencia;
	private String soatEntidad;
	private String soatEstado;

	private String rtmExpedicion;
	private String rtmVigencia;
	private String rtmEntidad;
	private String rtmEstado;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getNumeroLicenciaTransito() {
		return numeroLicenciaTransito;
	}

	public void setNumeroLicenciaTransito(String numeroLicenciaTransito) {
		this.numeroLicenciaTransito = numeroLicenciaTransito;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public String getClaseVehiculo() {
		return claseVehiculo;
	}

	public void setClaseVehiculo(String claseVehiculo) {
		this.claseVehiculo = claseVehiculo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getNumeroMotor() {
		return numeroMotor;
	}

	public void setNumeroMotor(String numeroMotor) {
		this.numeroMotor = numeroMotor;
	}

	public String getNumeroChasis() {
		return numeroChasis;
	}

	public void setNumeroChasis(String numeroChasis) {
		this.numeroChasis = numeroChasis;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	@XmlElement
	@XmlSchemaType(name = "date")
	public Date getFechaMatricula() {
		return fechaMatricula;
	}

	public void setFechaMatricula(Date fechaMatricula) {
		this.fechaMatricula = fechaMatricula;
	}

	public String getAutoridadTransito() {
		return autoridadTransito;
	}

	public void setAutoridadTransito(String autoridadTransito) {
		this.autoridadTransito = autoridadTransito;
	}

	public String getSoatNumero() {
		return soatNumero;
	}

	public void setSoatNumero(String soatNumero) {
		this.soatNumero = soatNumero;
	}

	 @XmlElement
	 @XmlSchemaType(name = "date")
	public Date getSoatFechaExpedicion() {
		return soatFechaExpedicion;
	}

	public void setSoatFechaExpedicion(Date soatFechaExpedicion) {
		this.soatFechaExpedicion = soatFechaExpedicion;
	}

	 @XmlElement
	 @XmlSchemaType(name = "date")
	public Date getSoatVigencia() {
		return soatVigencia;
	}

	public void setSoatVigencia(Date soatVigencia) {
		this.soatVigencia = soatVigencia;
	}

	public String getSoatEntidad() {
		return soatEntidad;
	}

	public void setSoatEntidad(String soatEntidad) {
		this.soatEntidad = soatEntidad;
	}

	public String getSoatEstado() {
		return soatEstado;
	}

	public void setSoatEstado(String soatEstado) {
		this.soatEstado = soatEstado;
	}

	public String getRtmExpedicion() {
		return rtmExpedicion;
	}

	public void setRtmExpedicion(String rtmExpedicion) {
		this.rtmExpedicion = rtmExpedicion;
	}

	public String getRtmVigencia() {
		return rtmVigencia;
	}

	public void setRtmVigencia(String rtmVigencia) {
		this.rtmVigencia = rtmVigencia;
	}

	public String getRtmEntidad() {
		return rtmEntidad;
	}

	public void setRtmEntidad(String rtmEntidad) {
		this.rtmEntidad = rtmEntidad;
	}

	public String getRtmEstado() {
		return rtmEstado;
	}

	public void setRtmEstado(String rtmEstado) {
		this.rtmEstado = rtmEstado;
	}

}
