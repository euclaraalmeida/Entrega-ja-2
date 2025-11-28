package repositorio;
import java.util.List;
import com.db4o.query.Query;
import modelo.Pedido;
import util.Util;

public class RepositorioPedido {

	
	public Pedido lerPedido(Object chave) {
		Integer id = (Integer) chave;
		Query q = Util.getManager().query();
		q.constrain(Pedido.class);
		q.descend("id").constrain(id);
		List<Pedido> resultado = q.execute();
		if (resultado.size() > 0){
			return resultado.getFirst();
		}else {
			return null;
		}
				
	}
}
