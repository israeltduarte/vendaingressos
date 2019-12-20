package vendaingressos.controller;

import java.util.List;

import javax.jws.HandlerChain;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.operator.card.ws.CreditCard;
import com.operator.card.ws.CreditCardNotValid_Exception;
import com.operator.card.ws.CreditCardService;
import com.operator.card.ws.CreditCardServiceService;
import com.operator.card.ws.EmptyCreditCardData_Exception;
import com.operator.card.ws.InvalidCreditCardData_Exception;
import com.operator.card.ws.InvalidPriceException_Exception;
import com.operator.card.ws.InvalidTimesException_Exception;
import com.operator.card.ws.MissingDataException_Exception;

import vendaingressos.dao.EventoDAO;
import vendaingressos.dao.LugarDAO;
import vendaingressos.dao.PedidoDAO;
import vendaingressos.dao.Token;
import vendaingressos.dao.UsuarioDAO;
import vendaingressos.helper.Utils;
import vendaingressos.model.CompraDto;
import vendaingressos.model.ConsultaPedidoDto;
import vendaingressos.model.Evento;
import vendaingressos.model.Lugar;
import vendaingressos.model.Pedido;
import vendaingressos.model.Status;

@Path("/public")
@HandlerChain(file = "handlerchain.xml")
public class PublicController {

	@GET
	@Path("/evento")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventos() {
		List<Evento> listEvents = EventoDAO.getAll();
		if (listEvents == null) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}

		return Response.status(Response.Status.OK).entity(listEvents).build();
	}

	@GET
	@Path("/evento/filtro")
	@Produces(MediaType.APPLICATION_JSON)
	public Response filterEvento(@DefaultValue("-1") @QueryParam(value = "descricao") String description,
			@DefaultValue("-1") @QueryParam(value = "local") String place,
			@DefaultValue("-1") @QueryParam(value = "inicio") String begin,
			@DefaultValue("-1") @QueryParam(value = "final") String end) {

		String sql = "select * from evento";
		boolean addAnd = false;

		if ((!description.equals("-1") && description.trim().length() != 0)) {
			sql = PublicController.mountQueryNormalField(sql, description, "descricao", addAnd);
			addAnd = true;
		}
		if ((!place.equals("-1") && place.trim().length() != 0)) {
			sql = PublicController.mountQueryNormalField(sql, place, "local", addAnd);
			addAnd = true;

		}
		if ((!begin.equals("-1") && begin.trim().length() != 0)) {
			sql = PublicController.mountQueryDate(sql, begin, "iniciovenda", addAnd, ">=", "00:00:00");
			addAnd = true;

		}
		if ((!end.equals("-1") && end.trim().length() != 0)) {
			sql = PublicController.mountQueryDate(sql, end, "fimvenda", addAnd, "<=", "23:59:59");
			addAnd = true;

		}

		List<Evento> eventWithfilter = EventoDAO.getEventWithfilter(sql);

		return Response.status(Response.Status.OK).entity(eventWithfilter).build();
	}

	public static String mountQueryNormalField(String sql, String field, String columnName, boolean addAnd) {
		if ((!field.equals("-1") && field.trim().length() != 0)) {
			if (addAnd) {
				sql += " and " + columnName + " ilike '%" + field + "%'";
			} else {
				sql += " where " + columnName + " ilike '%" + field + "%'";
			}
			addAnd = true;
		}

		return sql;
	}

	public static String mountQueryDate(String sql, String field, String columnName, boolean addAnd, String signal,
			String hour) {
		if ((!field.equals("-1") && field.trim().length() != 0)) {
			if (addAnd) {
				sql += " and " + columnName + signal + "'" + field + " " + hour + "'";
			} else {
				sql += " where " + columnName + signal + "'" + field + " " + hour + "'";
			}
			addAnd = true;
		}

		return sql;
	}

	@GET
	@Path("/lugar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAvailableSpotsByEvent(@PathParam("id") int id) {
		if (!EventoDAO.checkById(id)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		List<Lugar> listLugares = LugarDAO.getAvailableSpotsByEvent(id);

		if (listLugares == null) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}

		return Response.status(Response.Status.OK).entity(listLugares).build();
	}

	@PUT
	@Path("/lugar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response buySpot(CompraDto dto) {

		CreditCard creditCard = Utils.from(dto.getCartao());

		Lugar spot = LugarDAO.getSpot(dto.getEvento(), dto.getLugar_numero());
		if (spot == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		if (!LugarDAO.checkVersion(dto.getLugar_versao(), dto.getEvento(), dto.getLugar_numero())) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		if (!EventoDAO.checkById(dto.getEvento())) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		CreditCardServiceService service = new CreditCardServiceService();

		CreditCardService port = service.getCreditCardServicePort();

		if (!LugarDAO.buySpot(dto.getEvento(), dto.getLugar_numero(), dto.getCpf())) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		System.out.println("lugar reservado para ser aprovada a compra");

		try {
			if (port.authorize(creditCard, spot.getPreco(), dto.getParcelas())) {
				spot.setStatus(Status.VENDIDO);
			} else {
				spot.setStatus(Status.DISPONIVEL);
				spot.setToken(null);
			}
		} catch (CreditCardNotValid_Exception | EmptyCreditCardData_Exception | InvalidCreditCardData_Exception
				| InvalidPriceException_Exception | InvalidTimesException_Exception
				| MissingDataException_Exception e) {
			e.printStackTrace();
		}

		Token token = Utils.generateToken(dto.getCpf(), spot.getId());
		if (token == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		spot.setToken(token);

		if (LugarDAO.updateSpot(spot) == null) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.status(Response.Status.OK).entity(token).build();
	}

	@POST
	@Path("/pedido")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrdersByUser(ConsultaPedidoDto dto) {
		if (!EventoDAO.checkById(dto.getEvento().getId())) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		if (!UsuarioDAO.checkByCPF(dto.getCpf())) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		List<Pedido> listPedidos = PedidoDAO.getPedidosByCPF(dto.getEvento().getId(), dto.getCpf(), dto.getToken());
		if (listPedidos == null) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}

		return Response.status(Response.Status.OK).entity(listPedidos).build();
	}

	@GET
	@Path("/pedido/{cpf}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrdersByCPF(@PathParam("cpf") String cpf) {
		if (!UsuarioDAO.checkByCPF(cpf)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		List<Pedido> listPedidos = PedidoDAO.getPedidosByCPF(cpf);
		if (listPedidos == null) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}

		return Response.status(Response.Status.OK).entity(listPedidos).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/lugar/{token}")
	public Response getOrderByToken(@PathParam("token") String token) {
		Lugar lugar = LugarDAO.getLugarByToken(token);
		
		if (lugar == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		return Response.status(Response.Status.OK).entity(lugar).build();
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(@HeaderParam("usuario") String username, @HeaderParam("senha") String password) {

		if (!UsuarioDAO.authenticate(username, password)) {
			System.out.println("rodou na autenticacao");
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}

		return Response.status(Response.Status.OK).build();
	}

}
