//Lucas Brandt

import java.util.ArrayList;

public class Clientela {

	private ArrayList<Cliente> clientes;

	public Clientela (){
		clientes = new ArrayList<Cliente>();
	}

	public boolean cadastraCliente(Cliente cliente) {
		Cliente aux = pesquisaCliente(cliente.getEmail());
		if (aux == null){
			somatorioClientes();
			return clientes.add(cliente);
		}
		else {
			return false;
		}
	}

	public Cliente pesquisaCliente(String email) {
		for (int i = 0; i < clientes.size(); i++){
			Cliente c = clientes.get(i);
			if (c.getEmail().equals(email)){
				return c;
			}
		}
		return null;
	}


	public int somatorioClientes(){
		int somatorio = clientes.size();
		return somatorio;
	}

	public ArrayList<Cliente> getClientes(){
		return clientes;
	}
}

