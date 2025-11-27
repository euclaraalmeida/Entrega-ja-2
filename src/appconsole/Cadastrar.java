package appconsole;

import com.db4o.ObjectContainer;
import modelo.Pedido;
import modelo.Entregador;
import modelo.Entrega;

public class Cadastrar {
    private ObjectContainer manager;

    public Cadastrar() {
        try {
            manager = Util.conectarBanco();

            // --- Criar Entregadores ---
            Entregador e1 = new Entregador("João");
            Entregador e2 = new Entregador("Maria");
            Entregador e3 = new Entregador("Lucas");
            Entregador e4 = new Entregador("Matheus");
            Entregador e5 = new Entregador("Fausto");
            Entregador e6 = new Entregador("Mariana");
            Entregador e7 = new Entregador("Davi");
            Entregador e8 = new Entregador("Arthur");
            Entregador e9 = new Entregador("Clara");
            Entregador e10 = new Entregador("Laura");

            // --- Criar Entregas ---
            Entrega E1 = new Entrega("22/10/2025");
            Entrega E2 = new Entrega("23/10/2025");
            Entrega E3 = new Entrega("24/10/2025");
            Entrega E4 = new Entrega("25/10/2025");
            Entrega E5 = new Entrega("26/10/2025");
            Entrega E6 = new Entrega("27/10/2025");
            Entrega E7 = new Entrega("28/10/2025");
            Entrega E8 = new Entrega("29/10/2025");
            Entrega E9 = new Entrega("30/10/2025");
            Entrega E10 = new Entrega("31/10/2025");
            Entrega E11 = new Entrega("01/11/2025");
            Entrega E12 = new Entrega("02/11/2025");
            Entrega E13 = new Entrega("03/11/2025");
            Entrega E14 = new Entrega("04/11/2025");
            Entrega E15 = new Entrega("05/11/2025");

            // --- Criar Pedidos ---
            Pedido P1 = new Pedido("22/10/2025", 150.0, "Eletrônicos", "Porto Velho");
            Pedido P2 = new Pedido("23/10/2025", 89.9, "Roupas", "Ji-Paraná");
            Pedido P3 = new Pedido("24/10/2025", 230.5, "Livros", "Ariquemes");
            Pedido P4 = new Pedido("25/10/2025", 45.0, "Acessórios", "Cacoal");
            Pedido P5 = new Pedido("26/10/2025", 120.75, "Brinquedos", "Guajará-Mirim");
            Pedido P6 = new Pedido("27/10/2025", 310.4, "Eletrodomésticos", "Vilhena");
            Pedido P7 = new Pedido("28/10/2025", 67.8, "Cosméticos", "Jaru");
            Pedido P8 = new Pedido("29/10/2025", 540.0, "Móveis", "Rolim de Moura");
            Pedido P9 = new Pedido("30/10/2025", 22.5, "Alimentos", "Buritis");
            Pedido P10 = new Pedido("31/10/2025", 98.3, "Calçados", "Ouro Preto do Oeste");
            Pedido P11 = new Pedido("01/11/2025", 250.0, "Ferramentas", "Nova Mamoré");
            Pedido P12 = new Pedido("02/11/2025", 199.9, "Utilidades domésticas", "Alto Paraíso");

            // --- Relacionamentos completos ---

            // João faz 2 entregas
            e1.adicionar(E1);
            E1.setEntregador(e1);
            E1.adicionar(P1);
            P1.setEntrega(E1);

            e1.adicionar(E2);
            E2.setEntregador(e1);
            E2.adicionar(P2);
            E2.adicionar(P3);
            P2.setEntrega(E2);
            P3.setEntrega(E2);

            // Maria faz 3 entregas
            e2.adicionar(E3);
            E3.setEntregador(e2);
            E3.adicionar(P4);
            P4.setEntrega(E3);

            e2.adicionar(E4);
            E4.setEntregador(e2);
            E4.adicionar(P5);
            P5.setEntrega(E4);

            e2.adicionar(E5);
            E5.setEntregador(e2);
            E5.adicionar(P6);
            P6.setEntrega(E5);

            // Lucas faz 1 entrega
            e3.adicionar(E6);
            E6.setEntregador(e3);
            E6.adicionar(P7);
            P7.setEntrega(E6);

            // Matheus faz 2 entregas
            e4.adicionar(E7);
            E7.setEntregador(e4);
            E7.adicionar(P8);
            P8.setEntrega(E7);

            e4.adicionar(E8);
            E8.setEntregador(e4);
            E8.adicionar(P9);
            P9.setEntrega(E8);

            // Fausto faz 1 entrega
            e5.adicionar(E9);
            E9.setEntregador(e5);
            E9.adicionar(P10);
            P10.setEntrega(E9);

            // Mariana faz 1 entrega
            e6.adicionar(E10);
            E10.setEntregador(e6);
            E10.adicionar(P11);
            P11.setEntrega(E10);

            // Davi faz 2 entregas
            e7.adicionar(E11);
            E11.setEntregador(e7);
            E11.adicionar(P12);
            P12.setEntrega(E11);

            e7.adicionar(E12);
            E12.setEntregador(e7);

            e8.adicionar(E13);
            E13.setEntregador(e8);

            e9.adicionar(E14);
            E14.setEntregador(e9);

            e10.adicionar(E15);
            E15.setEntregador(e10);

            // --- Persistência ---
            manager.store(e1);
            manager.store(e2);
            manager.store(e3);
            manager.store(e4);
            manager.store(e5);
            manager.store(e6);
            manager.store(e7);
            manager.store(e8);
            manager.store(e9);
            manager.store(e10);

            manager.store(E1);
            manager.store(E2);
            manager.store(E3);
            manager.store(E4);
            manager.store(E5);
            manager.store(E6);
            manager.store(E7);
            manager.store(E8);
            manager.store(E9);
            manager.store(E10);
            manager.store(E11);
            manager.store(E12);
            manager.store(E13);
            manager.store(E14);
            manager.store(E15);

            manager.store(P1);
            manager.store(P2);
            manager.store(P3);
            manager.store(P4);
            manager.store(P5);
            manager.store(P6);
            manager.store(P7);
            manager.store(P8);
            manager.store(P9);
            manager.store(P10);
            manager.store(P11);
            manager.store(P12);

            manager.commit();
            System.out.println("Banco populado com dados completos e relacionamentos criados!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            manager.rollback();
        } finally {
            Util.desconectar();
        }
    }

    //=================================================
    public static void main(String[] args) {
        new Cadastrar();
    }
}
