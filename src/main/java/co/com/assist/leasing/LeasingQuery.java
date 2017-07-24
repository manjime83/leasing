package co.com.assist.leasing;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

	public static void main(String[] args) throws Exception {

		LeasingQuery query = new LeasingQuery();

		Identificacion id = new Identificacion();
		id.setTipo("");
		id.setNumero("80777377");

		query.conultarVehiculoPorPlaca(id, "JYR29D");

	}

	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	private Client client = IgnoreSSLClient();

	public static Client IgnoreSSLClient() {
		// System.setProperty("http.proxyHost", "127.0.0.1");
		// System.setProperty("http.proxyPort", "8888");
		// System.setProperty("https.proxyHost", "127.0.0.1");
		// System.setProperty("https.proxyPort", "8888");
		//
		// try {
		// SSLContext sslcontext = SSLContext.getInstance("TLS");
		// sslcontext.init(null, new TrustManager[] { new X509TrustManager() {
		// public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		// }
		//
		// public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		// }
		//
		// public X509Certificate[] getAcceptedIssuers() {
		// return new X509Certificate[0];
		// }
		// } }, new java.security.SecureRandom());
		//
		// ClientConfig clientConfig = new ClientConfig();
		//
		// ClientBuilder builder = ClientBuilder.newBuilder().sslContext(sslcontext).withConfig(clientConfig);
		//
		// Map<String, Object> props = builder.getConfiguration().getProperties();
		// for (Entry<String, Object> prop : props.entrySet()) {
		// System.out.println(prop.getKey() + "," + prop.getValue());
		// }
		//
		// return builder.build();
		// } catch (Exception e) {
		// return null;
		// }
		return ClientBuilder.newClient();
	}

	public LeasingQuery() {

	}

	@WebMethod
	@WebResult(name = "vehiculo")
	public Vehiculo conultarVehiculoPorPlaca(@WebParam(name = "identificacion") Identificacion id,
			@WebParam(name = "placa") String placa) {
		URI runt = UriBuilder.fromUri("https://www.runt.com.co/consultaCiudadana").build();

		WebTarget target = client.target(runt).path("captcha");
		Response response = target.request("image/webp", "image/apng", "image/*", "*/*;q=0.8")
				.header("Referer", "https://www.runt.com.co/consultaCiudadana/").acceptEncoding("gzip", "deflate", "br")
				.acceptLanguage("en-US", "en;q=0.8", "es-US;q=0.6", "es;q=0.4").get();
		Map<String, NewCookie> cookies = response.getCookies();

		String captcha = "";
		try {
			InputStream stream = response.readEntity(InputStream.class);
			byte[] bytes = IOUtils.toByteArray(stream);
			File captchaFile = new File("captcha.jpg");
			FileUtils.writeByteArrayToFile(captchaFile, bytes);
			Desktop.getDesktop().open(captchaFile);
			System.out.print("captcha: ");
			Scanner sc = new Scanner(System.in);
			captcha = sc.nextLine();
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		target = client.target(runt).path("publico/automotores");
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.WILDCARD)
				.header("Referer", "https://www.runt.com.co/consultaCiudadana/").acceptEncoding("gzip", "deflate", "br")
				.acceptLanguage("en-US", "en;q=0.8", "es-US;q=0.6", "es;q=0.4");
		builder.header("X-XSRF-TOKEN", cookies.get("XSRF-TOKEN").getValue());
		builder.header("PLACA_CONSULTA", placa);
		builder.header("CEDULA_CONSULTA", id.getNumero());

		for (NewCookie cookie : cookies.values()) {
			builder.cookie(cookie);
		}

		JsonObject obj = new JsonObject();
		obj.addProperty("tipoDocumento", "C");
		obj.addProperty("procedencia", "NACIONAL");
		obj.addProperty("tipoConsulta", "1");
		obj.add("vin", null);
		obj.addProperty("noDocumento", "80777377");
		obj.addProperty("noPlaca", placa);
		obj.add("soat", null);
		obj.add("codigoSoat", null);
		obj.addProperty("captcha", captcha);

		String json = builder.post(Entity.entity(obj.toString(), MediaType.APPLICATION_JSON + ";charset=UTF-8"),
				String.class);
		System.out.println(json);

		target = client.target(runt).path("publico/automotores/soats");
		builder = target.request(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.WILDCARD)
				.header("Referer", "https://www.runt.com.co/consultaCiudadana/").acceptEncoding("gzip", "deflate", "br")
				.acceptLanguage("en-US", "en;q=0.8", "es-US;q=0.6", "es;q=0.4");
		builder.header("X-XSRF-TOKEN", cookies.get("XSRF-TOKEN").getValue());
		builder.header("PLACA_CONSULTA", placa);
		builder.header("CEDULA_CONSULTA", id.getNumero());
		for (NewCookie cookie : cookies.values()) {
			builder.cookie(cookie);
		}
		response = builder.get();

		System.out.println(response.readEntity(String.class));

		return new Vehiculo();
	}

	@WebMethod
	@WebResult(name = "impuesto")
	public Impuesto conultarValorVigencia(@WebParam(name = "placa") String placa,
			@WebParam(name = "vigencia") int vigencia) {
		String uri = "http://recursosweb.shd.gov.co/ServiciosSitII/resources/predial-ac-648/obtenerFormulario?vigencia="
				+ vigencia + "&periodo=0&impuesto=3&objeto=" + placa;

		WebTarget service = client.target(UriBuilder.fromUri(uri).build());
		String json = service.request(MediaType.APPLICATION_JSON).get(String.class);

		// System.out.println(json);

		JsonParser jsonParser = new JsonParser();
		JsonObject jo = (JsonObject) jsonParser.parse(json);

		JsonArray datos = jo.get("datos").getAsJsonArray();
		Iterator<JsonElement> datosIterator = datos.iterator();

		int idSoporte = 0;
		while (datosIterator.hasNext()) {
			JsonObject dato = datosIterator.next().getAsJsonObject();

			if (dato.get("cod").getAsString().equals("idSoporte")) {
				idSoporte = dato.get("desc").getAsInt();
				break;
			}
		}

		uri = "http://recursosweb.shd.gov.co/Tareaps/TaxPayment.jsp";
		service = client.target(UriBuilder.fromUri(uri).build());
		Form values = new Form();
		values.param("idSoporte", idSoporte + "");
		values.param("valor", "");

		String html = service.request(MediaType.APPLICATION_FORM_URLENCODED)
				.post(Entity.entity(values, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
		int start = html.indexOf("<input type=\"hidden\" id=\"refer\" value=\"") + 39;
		int end = html.indexOf("\"", start);

		long id = Long.parseLong(html.substring(start, end));

		uri = "http://recursosweb.shd.gov.co/Tareaps/resources/presentacion/" + id;
		service = client.target(UriBuilder.fromUri(uri).build());
		json = service.request(MediaType.APPLICATION_JSON).get(String.class);

		jo = (JsonObject) jsonParser.parse(json);

		Impuesto impuesto = new Impuesto();
		impuesto.setVigencia(vigencia + "");
		try {
			if (jo.get("fechaVcto") != null) {
				impuesto.setFechaDeVencimiento(dateFormat.parse(jo.get("fechaVcto").getAsString()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		impuesto.setSoporte(jo.get("rutaFormulario").getAsString() + idSoporte);
		impuesto.setValorPagar(jo.get("valorPagar").getAsBigDecimal());
		impuesto.setEntidad(jo.get("mensaje").getAsString());

		return impuesto;
	}

	@WebMethod
	@WebResult(name = "impuesto")
	public Impuesto[] conultarObligacionesPendientes(@WebParam(name = "placa") String placa) {
		String uri = "http://recursosweb.shd.gov.co/ConsultaPagos/resources/servicios/relacionPagos?impuesto=3&objeto="
				+ placa;

		WebTarget service = client.target(UriBuilder.fromUri(uri).build());
		String json = service.request(MediaType.APPLICATION_JSON).get(String.class);

		JsonParser jsonParser = new JsonParser();
		JsonObject jo = (JsonObject) jsonParser.parse(json);

		if (jo.size() == 0) {
			throw new RuntimeException("Vehiculo no encontrado en SHD. Solo soportamos vehiculos registrados en Bogotá.");
		}

		// System.out.println(json);

		Set<Integer> vigenciasPendientes = new LinkedHashSet<>();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int year = Math.max(jo.get("dato2").getAsInt(), 2011); year <= currentYear; year++) {
			vigenciasPendientes.add(year);
		}

		JsonArray detalles = jo.get("detalles").getAsJsonArray();
		Iterator<JsonElement> detallesIterator = detalles.iterator();

		while (detallesIterator.hasNext()) {
			JsonObject detalle = detallesIterator.next().getAsJsonObject();

			// System.out.println(detalle.get("vigencia").getAsInt());

			vigenciasPendientes.remove(detalle.get("vigencia").getAsInt());
		}

		int i = 0;
		Impuesto[] impuestos = new Impuesto[vigenciasPendientes.size()];
		for (Integer vigenciaPendiente : vigenciasPendientes) {
			Impuesto impuesto = new Impuesto();
			impuesto.setVigencia(vigenciaPendiente + "");
			impuestos[i++] = impuesto;
		}

		return impuestos;
	}

	@WebMethod
	@WebResult(name = "impuesto")
	public Impuesto[] consultarPagos(@WebParam(name = "placa") String placa) {
		String uri = "http://recursosweb.shd.gov.co/ConsultaPagos/resources/servicios/relacionPagos?impuesto=3&objeto="
				+ placa;

		WebTarget service = client.target(UriBuilder.fromUri(uri).build());
		String json = service.request(MediaType.APPLICATION_JSON).get(String.class);

		// System.out.println(json);

		JsonParser jsonParser = new JsonParser();
		JsonObject jo = (JsonObject) jsonParser.parse(json);

		// System.out.println(jo.get("dato1").getAsString());
		// System.out.println(jo.get("dato2").getAsString());

		JsonArray detalles = jo.get("detalles").getAsJsonArray();
		Iterator<JsonElement> detallesIterator = detalles.iterator();

		int i = 0;
		Impuesto[] impuestos = new Impuesto[detalles.size()];
		while (detallesIterator.hasNext()) {
			JsonObject detalle = detallesIterator.next().getAsJsonObject();

			Impuesto impuesto = new Impuesto();
			impuesto.setVigencia(detalle.get("vigencia").getAsString());
			impuesto.setNroTransaccion(detalle.get("nroReferencia").getAsString());
			try {
				impuesto.setFechaDePago(dateFormat.parse(detalle.get("fechaPresentacion").getAsString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			impuesto.setEntidad(detalle.get("nomBanco").getAsString());
			impuestos[i++] = impuesto;
		}

		return impuestos;
	}

	@WebMethod
	@WebResult(name = "comparendo")
	public Comparendo[] conultarComparendosPorIdentificacion(@WebParam(name = "identificacion") Identificacion id) {
		try {
			EstadoCuenta estadoCuenta = new EstadoCuenta_Service(
					getClass().getResource("/WEB-INF/wsdl/EstadoCuenta.wsdl")).getEstadoCuentaPort();

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

			try {
				ClsSalidaComparendos salidaComparendos = estadoCuenta.comparendos(id.getNumero(),
						Integer.valueOf(id.getTipo()));

				List<ClsDatosComparendos> datosComparendos = salidaComparendos.getComparendos();
				List<Comparendo> comparendos = new ArrayList<Comparendo>(datosComparendos.size());

				for (ClsDatosComparendos datosComparendo : datosComparendos) {
					String estado = datosComparendo.getEstadoComparendo();

					// if (estado.equals("Pendiente") || estado.equals("Pendiente Curso")) {
					// continue;
					// }

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
