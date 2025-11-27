package modelo;

public class Pedido {
    private int id;
    private String data;
    private double valor;
    private String descricao;
    private Entrega entrega;
    private String localizacao;


    public Pedido(String data, double valor, String descricao, String localizacao) {
        this.data = data;
        this.valor = valor;
        this.descricao = descricao;
        this.localizacao = localizacao;	}
    
    
    public double getValor() {
        return valor;
    }

    
	public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }
    
    public int getId() {
        return id;
    }
    
    public void SetId(int idNovo) {
        this.id = idNovo;
   }
    
    public String getDescricao() {
        return descricao;
    }
    

  //tostring
  		@Override
  		public String toString() {
  			return "id: " + this.id + ", "+ " Pedido: " + this.descricao + ", "+ "Localização "+":"+ this.localizacao + " Valor: " + this.valor + ", "+ " Entrega: " + (this.entrega != null ? this.entrega.getId() : "Sem entrega");
  		}	
  		
  	
		
  	}

