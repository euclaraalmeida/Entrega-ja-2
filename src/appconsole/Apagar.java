package appconsole;

import java.util.List;
import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import modelo.Entrega;
import modelo.Entregador;


public class Apagar {
    private ObjectContainer manager;

    
    public Apagar() {
        manager = Util.conectarBanco();
        apagar(); 
        Util.desconectar();
    }

    public void apagar() {
        
        Query q = manager.query();
        q.constrain(Entregador.class);
        q.descend("nome").constrain("Davi");
        List<Entregador> resultados = q.execute();
        
        if (resultados.size() > 0) {
            Entregador entregadorParaApagar = resultados.get(0);
            System.out.println("Entregador encontrado: " + entregadorParaApagar.getNome());
            
            
            List<Entrega> listaDeEntregas = entregadorParaApagar.getListaEntregas();
            
            if (listaDeEntregas.isEmpty()) {
                System.out.println("Entregador não tem entregas, pode ser apagado diretamente.");
            } else {
                System.out.println("Tratando " + listaDeEntregas.size() + " entrega(s) órfã(s)...");
                for(Entrega e : listaDeEntregas) {
                    
                    e.setEntregador(null); 
                    
                    manager.store(e); 
                    System.out.println("...Entrega " + e.getId() + " agora está sem entregador (órfã).");
                }
            }
            
            //Na próxima vez que o banco for salvo, este objeto não deve mais existir."
            manager.delete(entregadorParaApagar);
            
            
            manager.commit();
            System.out.println("Entregador 'Davi' foi apagado.");
        }
        else {
            System.out.println("Entregador 'Davi' nao encontrado.");
        }
    }

    
    public static void main(String[] args) {
		new Apagar();
}
}
