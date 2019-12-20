package vendaingressos.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import vendaingressos.dao.EventoDAO;
import vendaingressos.dao.LugarDAO;
import vendaingressos.dao.PedidoDAO;
import vendaingressos.dao.UsuarioDAO;
import vendaingressos.model.Evento;
import vendaingressos.model.Pedido;
import vendaingressos.model.SetPriceDto;

@Path("/private")
public class MainController {

	private UriBuilder uriBuilder = UriBuilder.fromPath("vendaingressos/main/public").scheme("http").host("localhost")
			.port(8080).path(this.getClass()).path(this.getClass());

	@GET
	@Path("/pedido")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrders() {
		List<Pedido> listPedidos = PedidoDAO.getAll();

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
	@Path("/evento")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEvent(Evento event) {
		if (Integer.parseInt(event.getquantidadelugares()) < 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		Evento novoEvento = EventoDAO.addEvento(event);
		if (novoEvento == null) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.created(uriBuilder.build(novoEvento.getId())).entity(novoEvento).build();
	}

	@PUT
	@Path("/evento/{id}/lugar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setPriceToSpot(@PathParam("id") int id, SetPriceDto priceDto) {
		if (!EventoDAO.checkById(id)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		if ((priceDto.getEnd() > EventoDAO.getQuantidadeLugares(id)) || (priceDto.getEnd() < priceDto.getBegin())
				|| (priceDto.getPrice() < 0) || (priceDto.getBegin() <= 0))
			return Response.status(Response.Status.BAD_REQUEST).build();

		if (!LugarDAO.setPrice(id, priceDto)) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.status(Response.Status.OK).build();
	}

}
