//Lucas Brandt

public class Entrega {

	private int codigo;
	private double valor;
	private String descricao;
	private String email;
	private Cliente cliente;

	public Entrega(int codigo, double valor, String descricao, String email, Cliente cliente) {
		this.codigo = codigo;
		this.valor = valor;
		this.descricao = descricao;
		this.email = email;
		this.cliente = cliente;
	}


	public int getCodigo() {
		return codigo;
	}

	public double getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public String getEmail(){
		return email;
	}

	public Cliente getCliente(){
		return cliente;
	}

}
