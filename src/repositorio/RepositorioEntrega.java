package repositorio;
import java.util.List;
import com.db4o.query.Query;
import modelo.Entrega;
import modelo.Pedido;
import util.Util;

public class RepositorioEntrega extends CRUDRepositorio<Entrega> {

	public  Entrega lerEntrega(Object chave) {
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
	
	
	public List<Entrega> ListarEntregas() {

    System.out.println("-------Lista de Entregas--------");
    Query q2 = Util.getManager().query(); 
    q2.constrain(Entrega.class); 
    List<Entrega> resultados2 = q2.execute();

    if (resultados2.isEmpty()) {
        System.out.println("Nenhuma entrega cadastrada.");
    } else {
        for (Entrega e : resultados2) {
            System.out.println(e); 
        }}
	return resultados2;
    }


	@Override
	public Entrega ler(Object chave) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
