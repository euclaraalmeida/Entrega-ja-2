package appconsole;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Entrega;
import modelo.Entregador;
import modelo.Pedido;

public class Consultar {
	private ObjectContainer manager;

	public Consultar() {
		try {
			manager = Util.conectarBanco();
			consultar();
		} catch (Exception e) {
			System.out.println("Erro durante a consulta: " + e.getMessage());
		} finally {
			Util.desconectar();
		}
		System.out.println("\n\nConsulta finalizada.");
	}

	public void consultar() {
		System.out.println("------ Iniciando Consultas ------");

		// 1. quais as entregas na data 23/10/2025
		System.out.println("\n--- 1. Quais as entregas na data 23/10/2025 ---");
		Query q1 = manager.query();
		q1.constrain(Entrega.class);
		q1.descend("data").constrain("23/10/2025");
		List<Entrega> resultados1 = q1.execute();

		for (Entrega e : resultados1) {
			System.out.println(e); 

		}

		// 2. quais os pedidos entregues pelo entregador de nome "Maria"
		System.out.println("\n--- 2. Quais os pedidos entregues pelo entregador 'Maria' ---");
		Query q2 = manager.query();
		q2.constrain(Pedido.class);
		q2.descend("entrega")
		  .descend("entregador")
		  .descend("nome")
		  .constrain("Maria");
		
		List<Pedido> resultados2 = q2.execute();

			System.out.println("Pedidos encontrados:");
			for (Pedido p : resultados2) {
				System.out.println(p); 
				
			
			}
	
 
	     System.out.println("\n---listar quais os entregadores que tem mais N entregas");
        class Filtro implements Evaluation {
            private int n;

            public Filtro(int n) {
                this.n = n;
            }

           
            public void evaluate(Candidate candidate) {
                Entregador e = (Entregador) candidate.getObject();
                if (e.getListaEntregas().size() > n)
                    candidate.include(true);
                else
                    candidate.include(false);
            }


		
        }
        
        int N = 2;
        Query queryEntregaFiltro = manager.query();
        queryEntregaFiltro.constrain(Entregador.class);
        queryEntregaFiltro.constrain(new Filtro(N)); // aplica o filtro

        List<Entregador> EntregadorComNEntregas = queryEntregaFiltro.execute();

        if (EntregadorComNEntregas.isEmpty()) {
            System.out.println("Nenhum entregador com mais de " + N + " entregas");
        } else {
            System.out.println("Entregadore(s) com mais de " + N + " entregas:");
            for (Entregador e : EntregadorComNEntregas) {
                System.out.println(e);
            }
        }
	}

	  
    public static void main(String[] args) {
		new Consultar();
}
}

