package appconsole;

import java.util.List;
import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import modelo.Entrega;
import modelo.Entregador;

public class Alterar {
    private ObjectContainer manager;

    
    public Alterar() {
        manager = Util.conectarBanco();
        atualizar(); 
        Util.desconectar();
    }

    public void atualizar() {
        //remove a entrega de id6 a entregador lucas, e os objs tem que ta no banco 
        
        Query q1 = manager.query();
        q1.constrain(Entregador.class);
        q1.descend("nome").constrain("Lucas"); 
        List<Entregador> resultados = q1.execute();

        if (resultados.size() > 0) {
            Entregador entregador = resultados.get(0); 
            System.out.println("Entregador encontrado: " + entregador.getNome());

            //procurando a entrega id6 dentro do entregador
            Entrega entregaParaRemover = null;
            for (Entrega e : entregador.getListaEntregas()) {
                if (e.getId() == 6) { //tem q ter o getId na classe entrega
                    entregaParaRemover = e;
                    break;
                }
            }

            // removendo relacionamento se achado 
            if (entregaParaRemover != null) {
                System.out.println("Removendo a entrega " + entregaParaRemover.getId() + " do entregador.");

                
                entregador.remover(entregaParaRemover);
                
                //tirando o nome do entregador da entrega 
                entregaParaRemover.setEntregador(null);

                
                manager.store(entregador);          // salvando entregador sem aquela entref a espeecifica 
                manager.store(entregaParaRemover);  // salvando entrega sem entrefador

               
                manager.commit();
                System.out.println("Relacionamento removido.");

            } else {
                System.out.println("O entregador " + entregador.getNome() + " não possui a entrega de ID 6.");
            }

        } else {
            System.out.println("Entregador  não encontrado.");
        }
    }

    
	public static void main(String[] args) {
		new Alterar();
}
}