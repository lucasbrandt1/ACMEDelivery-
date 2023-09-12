//Lucas Brandt

import java.util.ArrayList;

public class Cliente {

	private String email;
	private String nome;
	private String endereco;
	private ArrayList<Entrega> entregas;

	public Cliente (String email, String nome, String endereco){
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		entregas = new ArrayList<Entrega>();
	}

	public boolean adicionaEntrega(Entrega entrega) {
		Entrega aux = pesquisaEntrega(entrega.getCodigo());
		if (aux == null){
			return entregas.add(entrega);
		}
		else {
			return false;
		}
	}


	public Entrega pesquisaEntrega (int codigo){
		for (int i = 0; i < entregas.size(); i++){
			Entrega e = entregas.get(i);
			if (e.getCodigo() == codigo){
				return e;
			}
		}
		return null;
	}


	public ArrayList<Entrega> pesquisaEntregas() {
		if (entregas.size() <= 0){
			return null;
		}
		else {
			return entregas;
		}
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	


}

