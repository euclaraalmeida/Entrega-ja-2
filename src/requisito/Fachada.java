package requisito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.db4o.query.Query;

import modelo.Entrega;
import modelo.Entregador;
import modelo.Pedido;
import repositorio.RepositorioEntrega;
import repositorio.RepositorioEntregador;
import repositorio.RepositorioPedido;
import repositorio.CRUDRepositorio;
import util.Util;

public class Fachada {
/*/Criar ok / Listar Pedido ok
	Criar ok / Listar Entrega ok
	Criar ok / Listar Entregador ok
	Apagar Pedido - faltando
	Adicionar um pedido (já
	existente) na entrega ok */
	
	private static  RepositorioPedido PedidoRepositorio = new RepositorioPedido();
	private static  RepositorioEntrega EntregaRepositorio = new RepositorioEntrega();
	private static  RepositorioEntregador EntregadorRepositorio = new RepositorioEntregador();

	
	public static void CriarPedido(double valor, String descricao, String localizacao) {
		
		// antes de criar conferir se ja existe o pedido usando o 
		// metodo lerPedido da classe Repositorio Pedido criar uma execeçao caso tenha
	    PedidoRepositorio.conectar();
	    PedidoRepositorio.begin();

	    Pedido p = new Pedido(valor,descricao,localizacao);
	    p.setValor(valor);
	    p.setDescricao(descricao);
	    p.setLocalizacao(localizacao);

	    PedidoRepositorio.criar(p);

	    PedidoRepositorio.commit();
	    PedidoRepositorio.desconectar();
	}
	
	

	public static void CriarEntrega(String data, Entregador entregador, List<Pedido> pedido, Object id) throws Exception {
		EntregaRepositorio.conectar();
		EntregaRepositorio.begin();
		try {
			LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (DateTimeParseException e) {
			EntregaRepositorio.rollback();
			throw new Exception("formato data invalido:" + data);
		}
		Entrega e = EntregaRepositorio.lerEntrega(id);
		if (e != null) {
			EntregaRepositorio.rollback();
			throw new Exception("Entrega existente" + id);
		}
		 e = new Entrega(data);
		e.setData(data);
		e.setEntregador(entregador);

		for (Pedido p : pedido) {
		    e.adicionar(p);
		}
		EntregaRepositorio.criar(e);
		
		EntregaRepositorio.commit();
		EntregaRepositorio.desconectar();
	}
	
	
	
	public static void CriarEntregador(String nome,List<Entrega> entregas) {
		
		// antes de criar conferir se ja existe o entregar usando o 
		// metodo lerEntregador da classe Repositorio Entrega criar uma execeçao caso tenha 
		EntregadorRepositorio.conectar();
		EntregadorRepositorio.begin();

	    Entregador e1 = new Entregador(nome);
	    e1.setNome(nome);
	  
	    for (Entrega e: entregas) {
	    	e1.adicionar(e);
	    }

	    EntregadorRepositorio.criar(e1);

	    EntregadorRepositorio.commit();
	    EntregadorRepositorio.desconectar();
	}
	

	
	public static List<Entregador> listarEntregador(){
		EntregadorRepositorio.conectar();
		List<Entregador> resultado = EntregadorRepositorio.ListarEntregador();
		EntregadorRepositorio.desconectar();
		return resultado;
	}
	
	
	public static List<Entrega> listarEntregas(){
		EntregaRepositorio.conectar();
		List<Entrega> resultado = EntregaRepositorio.ListarEntregas();
		EntregaRepositorio.desconectar();
		return resultado;
	}
	
	public static List<Pedido> listarPedidos(){
		EntregaRepositorio.conectar();
		List<Pedido> resultado = PedidoRepositorio.ListarPedidos();
		EntregaRepositorio.desconectar();
		return resultado;
	}
	/* FALTA APAGAR PEDIDO
	public static void apagarPedido(Pedido pedido) throws Exception {
		PedidoRepositorio.conectar();
		PedidoRepositorio.begin();
		Pedido p = PedidoRepositorio.ler(id);
		if (p == null) {
			PedidoRepositorio.rollback();
			throw new Exception(" Pedido inexistente" + id);
		}
		Entrega e ;
		e.remover(p);
		p.setPedido(null);
		PedidoRepositorio.atualizar(e);
		PedidoRepositorio.apagar(p);
		PedidoRepositorio.commit();
		PedidoRepositorio.desconectar();
	}*/
	
	public static void AddPedidoNaEntrega(Pedido pedido,Integer id) throws Exception {
		PedidoRepositorio.conectar();
		PedidoRepositorio.begin();
		Pedido p = PedidoRepositorio.ler(pedido.getId());
		if (p == null) {
			PedidoRepositorio.rollback();
			throw new Exception(" Esse Pedido não existe" + pedido.getId());
		}
		Entrega e = new Entrega(id);

		e.adicionar(pedido);
	}
	
}

