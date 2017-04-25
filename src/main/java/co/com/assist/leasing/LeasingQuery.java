package co.com.assist.leasing;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import co.com.assist.leasing.objects.Comparendo;
import co.com.assist.leasing.objects.Identificacion;
import co.com.assist.leasing.objects.Impuesto;
import co.com.assist.leasing.objects.Vehiculo;
import servicios.ClsDatosComparendos;
import servicios.ClsSalidaComparendos;
import servicios.EstadoCuenta;
import servicios.EstadoCuenta_Service;
import servicios.Exception_Exception;

@WebService
public class LeasingQuery {

	@WebMethod
	@WebResult(name = "vehiculo")
	public Vehiculo conultarVehiculoPorVIN(@WebParam(name = "vin") String vin) {
		return new Vehiculo();
	}

	@WebMethod
	@WebResult(name = "vehiculo")
	public Vehiculo conultarVehiculoPorPlaca(@WebParam(name = "identificacion") Identificacion id, @WebParam(name = "placa") String placa) {
		return new Vehiculo();
	}

	@WebMethod
	@WebResult(name = "impuesto")
	public Impuesto[] conultarObligacionesPendientes(@WebParam(name = "placa") String placa, @WebParam(name = "entidad") String entidad) {
		return new Impuesto[0];
	}

	@WebMethod
	@WebResult(name = "comparendo")
	public Comparendo[] conultarComparendosPorIdentificacion(@WebParam(name = "identificacion") Identificacion id) {
		try {
			EstadoCuenta estadoCuenta = new EstadoCuenta_Service(getClass().getResource("/WEB-INF/wsdl/EstadoCuenta.wsdl")).getEstadoCuentaPort();

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

			try {
				ClsSalidaComparendos salidaComparendos = estadoCuenta.comparendos(id.getNumero(), Integer.valueOf(id.getTipo()));

				List<ClsDatosComparendos> datosComparendos = salidaComparendos.getComparendos();
				List<Comparendo> comparendos = new ArrayList<Comparendo>(datosComparendos.size());

				for (ClsDatosComparendos datosComparendo : datosComparendos) {
					Comparendo comparendo = new Comparendo();
					comparendo.setNumeroComparendo(datosComparendo.getNumeroComparendo());
					comparendo.setFechaComparendo(dateFormat.parse(datosComparendo.getFechaComparendo()));
					comparendo.setDireccion(datosComparendo.getDireccionComparendo());
					comparendo.setElectronico(datosComparendo.getFotodeteccion().equals("Si"));

					comparendo.setSecretaria(datosComparendo.getSecretariaComparendo());
					comparendo.setEstado(datosComparendo.getEstadoComparendo());

					comparendo.setCodigoInfraccion(datosComparendo.getCodigoInfraccion());
					comparendo.setDescripcionInfraccion(datosComparendo.getDescripcionInfraccion());

					comparendo.setPlacasVehiculo(datosComparendo.getPlacaVehiculo());
					// comparendo.setServicioVehiculo(datosComparendo.getServicioVehiculo());
					// comparendo.setTipoVehiculo(datosComparendo.getTipoVehiculo());
					comparendo.setValorAPagor(BigDecimal.valueOf(datosComparendo.getTotal()));

					comparendos.add(comparendo);
				}

				return comparendos.toArray(new Comparendo[comparendos.size()]);
			} catch (Exception_Exception | ParseException e) {
				return new Comparendo[0];
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));

			Comparendo comparendo = new Comparendo();
			comparendo.setPlacasVehiculo(sw.toString());
			return new Comparendo[] { comparendo };
		}
	}

}
