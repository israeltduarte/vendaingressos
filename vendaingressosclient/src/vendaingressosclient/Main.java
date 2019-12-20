package vendaingressosclient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

import vendaingressosclient.model.Cartao;
import vendaingressosclient.model.CompraDto;
import vendaingressosclient.model.Evento;
import vendaingressosclient.model.Lugar;
import vendaingressosclient.model.Pedido;
import vendaingressosclient.model.SetPriceDto;
import vendaingressosclient.model.Token;

public class Main {

	public static Client client;

	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Inicio da Aplicacao\n");

		ClientConfig clientConfig = new ClientConfig();

		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder().nonPreemptive()
				.credentials("israel", "root").build();

		clientConfig.register(feature);

		clientConfig.register(JacksonFeature.class);

		client = ClientBuilder.newClient(clientConfig); // default pre-configurated client

		client = ClientBuilder.newBuilder().property("connection.timeout", 100).property("authentication.mode", "Basic")
				.property("username", "israel").property("password", "root").register(JacksonJsonProvider.class)
				.build();

		boolean flag = true;

		String username = null, password = null;

		boolean keeplogin = true;

		boolean isLogged = false;

		while (flag) {

			while (keeplogin) {
				System.out.println("Deseja realizar login?\n1 - Sim\n2 - Nao\n3 - Sair");
				int wantsLogin = Integer.parseInt(scan.nextLine());

				if (wantsLogin == 1) {
					System.out.println("Informe o nome de usuario: ");
					username = scan.nextLine();

					System.out.println("Informe a senha: ");
					password = scan.nextLine();

					Response response = client.target("http://localhost:8080/vendaingressos/main/public/login")
							.request().header("usuario", username).header("senha", password).post(null);

					if (response.getStatus() != Response.Status.OK.getStatusCode()) {
						System.out.println("erro ao realizar login");
					} else {
						System.out.println("login realizado com sucesso");
						keeplogin = false;
						isLogged = true;
					}
				} else if (wantsLogin == 2) {
					isLogged = false;
					keeplogin = false;
				} else {
					System.out.println("Fim da execucao");
					System.exit(0);
				}
			}

			if (isLogged) {
				System.out.println(
						"\n1 - Listar eventos\n2 - Listar lugares disponiveis\n3 - Comprar lugar\n4 - Consultar pedido de compra de lugar por token\n5 - Incluir evento\n6 - Atribuir preco a lugares\n0 - Voltar\n");

				System.out.println("Selecione a acao: ");
				Integer opcao = Integer.parseInt(scan.nextLine());

				switch (opcao) {

				case 1:
					listEvents();
					break;

				case 2:
					getAvailableSpotsByEvent();
					break;

				case 3:
					buySpot();
					break;

				case 4:
					getOrder();
					break;

				case 5:
					addEvento();
					break;

				case 6:
					setPriceToSpot();
					break;

				case 7:
					getFilteredEvent();
					break;

				case 0:
					keeplogin = true;
					break;

				default:
					break;
				}
			} else {
				System.out.println(
						"\n1 - Listar eventos\n2 - Listar lugares disponiveis\n3 - Comprar lugar\n4 - Consultar pedido de compra de lugar por token\n0 - Voltar\n");

				System.out.println("Selecione a acao:");
				String entrada = null;
				entrada = scan.nextLine();
				Integer opcao = Integer.parseInt(entrada);

				switch (opcao) {

				case 1:
					listEvents();
					break;

				case 2:
					getAvailableSpotsByEvent();
					break;

				case 3:
					buySpot();
					break;

				case 4:
					getOrder();
					break;

				case 0:
					keeplogin = true;
					break;

				default:
					break;
				}
			}
		}

		client.close();
	}

	private static void addEvento() {
		Evento evento = new Evento();

		System.out.println("Informe a descricao do evento:");
		evento.setDescricao(entradaDeTexto("^.{1,100}$"));

		System.out.println("Informe a data e hora do evento:");
		evento.setDataHora(LocalDateTime.parse(entradaDeTexto("[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

		System.out.println("Informe o local do evento:");
		evento.setLocal(entradaDeTexto("^.{1,100}$"));

		System.out.println("Informe a quantidade de lugares disponiveis do evento:");
		evento.setquantidadelugares(entradaDeTexto("[\\d]{1,4}"));

		System.out.println("Informe a data e hora do inicio da venda de lugares do evento:");
		evento.setInicioVenda(LocalDateTime.parse(entradaDeTexto("[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

		System.out.println("Informe a data e hora do fim da venda de lugares do evento:");
		evento.setFimVenda(LocalDateTime.parse(entradaDeTexto("[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

		Response response = client.target("http://localhost:8080/vendaingressos/main/private/evento").request()
				.post(Entity.json(evento));

		if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
			System.out.println("falha ao criar evento");
		}
	}

	private static void getOrder() {
		System.out.println("Informe o token do pedido a ser consultado: ");
		String entrada = scan.nextLine();

		Response response = client.target("http://localhost:8080/vendaingressos/main/public/lugar/{token}")
				.resolveTemplate("token", entrada).request().accept(MediaType.APPLICATION_JSON).post(null);

		if (response.getStatus() == Response.Status.OK.getStatusCode()) {
			Lugar lugar = response.readEntity(Lugar.class);
			System.out.println(lugar);
		} else {
			System.out.println("pedido nao encontrado");
		}

		response.close();
	}

	private static void getAvailableSpotsByEvent() {
		System.out.println("Informe o id do evento a ter seus lugares consultados: ");
		String entrada = scan.nextLine();

		Response response = client.target("http://localhost:8080/vendaingressos/main/public/lugar/{id}")
				.resolveTemplate("id", entrada).request().accept(MediaType.APPLICATION_JSON).get();

		if (response.getStatus() == Response.Status.OK.getStatusCode()) {
			response.readEntity(new GenericType<ArrayList<Lugar>>() {
			}).forEach(Lugar::toStringLugarDisponivel);
		} else {
			System.out.println("erros ao carregar lugares");
		}

		response.close();
	}

	private static void listEvents() {
		Response response = client.target("http://localhost:8080/vendaingressos/main/public/evento").request()
				.accept(MediaType.APPLICATION_JSON).get();

		ArrayList<Evento> listevents = response.readEntity(ArrayList.class);
		if (response.getStatus() == Response.Status.OK.getStatusCode()) {
			for (int i = 0; i < listevents.size(); i++) {
				System.out.println(listevents.get(i));
			}
		} else {
			System.out.println("erros ao carregar eventos");
		}

		response.close();
	}

	private static void buySpot() {
		CompraDto dto = new CompraDto();

		System.out.println("Informe o CPF do comprador: ");
		dto.setCpf(entradaDeTexto("^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$"));

		System.out.println("Informe o id do evento: ");
		dto.setEvento(Integer.parseInt(scan.nextLine()));

		System.out.println("Informe o numero do lugar a ser comprado: ");
		dto.setLugar_numero(Integer.parseInt(scan.nextLine()));

		System.out.println("Informe a quantidade de parcelas: ");
		dto.setParcelas(Integer.parseInt(scan.nextLine()));

		System.out.println("Informe a versao: ");
		dto.setLugar_versao(Integer.parseInt(scan.nextLine()));

		Cartao card = new Cartao();

		System.out.println("Informe a bandeira do cartao: (Visa/Master) ");
		card.setBandeira(entradaDeTexto(""));

		System.out.println("Informe o numero do cartao: (DDD) ");
		card.setNumero(entradaDeTexto(""));

		System.out.println("Informe o titular do cartao: ");
		card.setTitular(entradaDeTexto(""));

		System.out.println("Informe a validade do cartao: (MM/AAAA) ");
		card.setValidade(entradaDeTexto(""));

		System.out.println("Informe a codigo de seguranca do cartao: (DDD) ");
		card.setCodeSeguranca(entradaDeTexto(""));

		dto.setCartao(card);

		Response response = client.target("http://localhost:8080/vendaingressos/main/public/lugar").request()
				.accept(MediaType.APPLICATION_JSON).put(Entity.json(dto));

		if (response.getStatus() != Response.Status.OK.getStatusCode()) {
			System.out.println("erro ao definir comprar lugar");
		} else {
			Token token = response.readEntity(Token.class);
			System.out.println("lugar comprado\n");
			System.out.println("token da compra " + token.getToken());
		}

		response.close();
	}

	private static void setPriceToSpot() {
		SetPriceDto dto = new SetPriceDto();

		System.out.println("Informe o id do evento para definir o preco dos lugares: ");
		String eventId = scan.nextLine();

		System.out.println("Informe o inicio dos lugares a terem seu valor definido:");
		dto.setBegin(Integer.parseInt(entradaDeTexto("[\\d]{1,4}")));

		System.out.println("Informe o fim dos lugares a terem seu valor definido:");
		dto.setEnd(Integer.parseInt(entradaDeTexto("[\\d]{1,4}")));

		System.out.println("Informe o preco dos lugares:");
		dto.setPrice(Double.parseDouble(entradaDeTexto("^(\\d{1,1000})(\\.\\d{1,})*$")));

		Response response = client.target("http://localhost:8080/vendaingressos/main/private/evento/{id}/lugar")
				.resolveTemplate("id", eventId).request().put(Entity.json(dto));

		if (response.getStatus() != Response.Status.OK.getStatusCode()) {
			System.out.println("erro ao definir preco dos lugares");
		} else {
			System.out.println("preco definido");
		}

		response.close();
	}

	private static String entradaDeTexto(String regex) {
		String entrada = null;

		while (true) {
			entrada = scan.nextLine();
			if (!entrada.matches(regex)) {
				System.out.println("Dados invalidos! Informe novamente.");
			} else {
				return entrada;
			}
		}
	}

	private static void getFilteredEvent() {
		String url = "http://localhost:8080/vendaingressos/main/public/evento/filtro";

		boolean flag = false;

		String[] fields = { "descricao", "lugar", "inicio", "fim" };

		System.out.println("Informe a descrição: ");
		String entrada = scan.nextLine();
		if (!entrada.isEmpty()) {
			url = addFilter("descricao", entrada, flag, url);
			flag = true;
		}

		System.out.println("Informe o local: ");
		entrada = scan.nextLine();

		if (!entrada.isEmpty()) {
			url = addFilter("local", entrada, flag, url);
			flag = true;
		}

		System.out.println("Informe o início da venda: ");
		entrada = scan.nextLine();
		if (!entrada.isEmpty()) {
			url = addFilter("inicio", entrada, flag, url);
			flag = true;
		}

		System.out.println("Informe o final da venda: ");
		entrada = scan.nextLine();
		if (!entrada.isEmpty()) {
			url = addFilter("final", entrada, flag, url);
			flag = true;
		}

		Response response = client.target(url).request().accept(MediaType.APPLICATION_JSON).get();

		if (response.getStatus() == Response.Status.OK.getStatusCode()) {
			Pedido pedido = response.readEntity(Pedido.class);
			System.out.println(pedido);
		} else {
			System.out.println("eventos nao encontrados");
		}

		response.close();

	}

	private static String addFilter(String campo, String entrada, boolean b, String url) {

		if (b) {
			url += "&" + campo + "=" + entrada;
		} else {
			url += "?" + campo + "=" + entrada;
		}
		return url;

	}

}
