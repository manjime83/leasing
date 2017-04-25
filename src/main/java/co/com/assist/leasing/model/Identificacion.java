package co.com.assist.leasing.model;

public class Identificacion {

	private String numero;

	private Integer tipo;

	public Identificacion() {
	}

	public Identificacion(String numero, Integer tipo) {
		this.numero = numero;
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

}
