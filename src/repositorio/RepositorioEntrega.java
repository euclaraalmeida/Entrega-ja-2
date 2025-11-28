package repositorio;
import java.util.List;
import com.db4o.query.Query;
import modelo.Entrega;
import modelo.Pedido;
import util.Util;

public class RepositorioEntrega extends CRUDRepositorio<Entrega> {

	public  Entrega ler(Object chave) {
		Integer id = (Integer) chave;
		Query q = Util.getManager().query();
		q.constrain(Entrega.class);
		q.descend("id").constrain(id);
		List<Entrega> resultado = q.execute();
		if (resultado.size() > 0)
			return resultado.getFirst();
		else
			return null;
	}
	
	// quais entregas na data x
	
	public List<Entrega> EntregasNaData(Object chave) {
		String data = (String) chave;
		Query q = Util.getManager().query();
		q.constrain(Entrega.class);
		q.descend("data").constrain(data);
		List<Entrega> resultado = q.execute();
		if (resultado.size()>0) {
			return resultado ;
		}else {
			return null;
		}
	}
	
}
