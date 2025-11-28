package repositorio;
import java.util.List;
import com.db4o.query.Query;
import modelo.Entregador;
import modelo.Pedido;
import util.Util;

public class RepositorioEntregador  extends CRUDRepositorio<Entregador>{

	
	public Entregador ler(Object chave) {
		Integer id = (Integer) chave;
		Query q = Util.getManager().query();
		q.constrain(Entregador.class);
		q.descend("id").constrain(id);
		List<Entregador> resultado = q.execute();
		if(resultado.size()>0) {
			return resultado.getFirst();
		}else {
			return null;
		}
	}
	
	

	// pedidos entre por X entregador
	public List<Pedido> PedidosPorEntregador(Object chave){
		String nome = (String) chave;
		Query q2 = Util.getManager().query();
		q2.constrain(Pedido.class);
		q2.descend("entrega")
		  .descend("entregador")
		  .descend("nome")
		  .constrain("chave");
		
		List<Pedido> resultados2 = q2.execute();
		
		if (resultados2.size()>0) {
			System.out.println("Pedidos do entregador " + chave + ":");
			return resultados2;
		}else {
		return null;
		}
	}
}

