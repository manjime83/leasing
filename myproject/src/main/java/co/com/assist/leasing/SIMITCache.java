package co.com.assist.leasing;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.xml.ws.BindingProvider;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;

import co.com.assist.leasing.model.Comparendo;
import co.com.assist.leasing.model.Identificacion;
import servicios.ClsDatosComparendos;
import servicios.ClsSalidaComparendos;
import servicios.EstadoCuenta;
import servicios.EstadoCuenta_Service;
import servicios.Exception_Exception;

@Singleton
public class SIMITCache {

	private static CloudantClient client = ClientBuilder.account("de1a6d0b-a585-48b5-8cc7-2b35e0bd60f5-bluemix")
			.username("de1a6d0b-a585-48b5-8cc7-2b35e0bd60f5-bluemix")
			.password("1aa78053046efc6b25e2789a03ccb945c2840c394be3a12b9a3a75ada41c2bd7").build();

	public static void main(String[] args) {
		Database db = client.database("identificaciones", true);

		List<Identificacion> identificaciones = new ArrayList<Identificacion>();
		identificaciones.add(new Identificacion("8909039388", 4));
		identificaciones.add(new Identificacion("890903938", 4));
		db.bulk(identificaciones);

		client.shutdown();
	}

	// @Schedule(hour = "*", minute = "*", second = "*/15")
	public void getComparendos() {
		Identificacion id = null;
		System.out.println("hola mundo");
		EstadoCuenta estadoCuenta = new EstadoCuenta_Service().getEstadoCuentaPort();

		try {
			((BindingProvider) estadoCuenta).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
					new URL("http://localhost:8080/ServiciosSimit/EstadoCuenta").toString());
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

		try {
			ClsSalidaComparendos salidaComparendos = estadoCuenta.comparendos(id.getNumero(), id.getTipo());
			List<ClsDatosComparendos> datosComparendos = salidaComparendos.getComparendos();
			List<Comparendo> comparendos = new ArrayList<Comparendo>(datosComparendos.size());

			for (ClsDatosComparendos datosComparendo : datosComparendos) {
				Comparendo comparendo = new Comparendo();
				comparendo.setCodigo(datosComparendo.getCodigoInfraccion());
				comparendo.setIdentificacion(id);
				comparendo.setDireccion(datosComparendo.getDireccionComparendo());
				comparendo.setEstado(datosComparendo.getEstadoComparendo());
				try {
					comparendo.setFecha(dateFormat.parse(datosComparendo.getFechaComparendo()));
				} catch (ParseException e) {
					System.err.println(e);
				}
				comparendo.setFotodeteccion(datosComparendo.getFotodeteccion() == "Si");
				comparendo.setNumero(datosComparendo.getNumeroComparendo());
				comparendo.setPlacaVehiculo(datosComparendo.getPlacaVehiculo());
				comparendo.setSecretaria(datosComparendo.getSecretariaComparendo());
				comparendo.setServicioVehiculo(datosComparendo.getServicioVehiculo());
				comparendo.setTipoVehiculo(datosComparendo.getTipoVehiculo());
				comparendo.setTotal(BigDecimal.valueOf(datosComparendo.getTotal()));

				comparendos.add(comparendo);

			}

			Database db = client.database("comparendos", true);
			List<Response> responses = db.bulk(comparendos);
			for (Response response : responses) {
				System.out.println(response.getId() + " " + response.getStatusCode());
			}

			// return comparendos;
		} catch (Exception_Exception e) {
			// return new ArrayList<Comparendo>(0);
		}

	}

}