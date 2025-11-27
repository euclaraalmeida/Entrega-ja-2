package appconsole;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;


import modelo.Entregador;
import modelo.Entrega;
import modelo.Pedido;

public class Listar {
    
    private ObjectContainer manager;

    
    public Listar() {
        
        manager = Util.conectarBanco();
        
        listar(); 
        
        
        Util.desconectar();
    }

    
    public void listar() {
        
    	
        System.out.println("------Lista de Entregadores---");
        Query q1 = manager.query(); 
        q1.constrain(Entregador.class);
        List<Entregador> resultados1 = q1.execute(); 

        if (resultados1.isEmpty()) {
            System.out.println("Nenhum entregador cadastrado.");
        } else {
            for (Entregador e : resultados1) {
                System.out.println(e); 
            }
        }

        
        System.out.println("-------Lista de Entregas--------");
        Query q2 = manager.query(); 
        q2.constrain(Entrega.class); 
        List<Entrega> resultados2 = q2.execute();

        if (resultados2.isEmpty()) {
            System.out.println("Nenhuma entrega cadastrada.");
        } else {
            for (Entrega e : resultados2) {
                System.out.println(e); 
            }
        }

     
        System.out.println("-------Lista de Pedidos--------");
        Query q3 = manager.query(); 
        q3.constrain(Pedido.class); 
        List<Pedido> resultados3 = q3.execute(); 

        if (resultados3.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
        } else {
            for (Pedido p : resultados3) {
                System.out.println(p); 
            }
        }
    }

    	public static void main(String[] args) {
    		new Listar();
	}
}
