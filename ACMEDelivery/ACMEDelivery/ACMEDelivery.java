//Lucas Brandt

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;

public class ACMEDelivery {

	private Scanner entrada = null;
	private PrintStream saidaPadrao = System.out;

	private CadastroEntregas cadastroEntregas;
	private Clientela clientela;

	public ACMEDelivery(){
		try {
			BufferedReader streamEntrada = new BufferedReader(new FileReader("arquivoentrada.txt"));
			entrada = new Scanner(streamEntrada);  
			PrintStream streamSaida = new PrintStream(new File("arquivosaida.txt"), Charset.forName("UTF-8"));
			System.setOut(streamSaida);            
		} catch (Exception e) {
			System.out.println(e);
		}
		Locale.setDefault(Locale.ENGLISH);  
		entrada.useLocale(Locale.ENGLISH);

		clientela = new Clientela();
		cadastroEntregas = new CadastroEntregas(clientela);
	}

	private void restauraES() {
        System.setOut(saidaPadrao);
        entrada = new Scanner(System.in);
    }


	public void executa() {
		
		//1
		cadastrarClientes();
		//2
		cadastrarEntregas();
		//3
		quantClientesCadastrados();
		//4
		quantEntregasCadastradas();
		//5
		mostraDadosCliente();
		//6
		mostraDadosEntrega();
		//7
		mostraDadosEntregaCliente();
		//8
		mostraDadosEntregaMaiorValor();
		//9
		mostraEnderecoDeEntrega();
		//10
		mostraSomatorioDeEntregasDeCliente();

		
		restauraES();
				
		int opcao = 0;
		do {
			menu();
			System.out.print("Digite a opcao desejada: ");
			opcao = entrada.nextInt();
			entrada.nextLine();
			switch(opcao) {
				case 0:
					break;
				case 1:
					cadastrarNovoClienteEEntrega();
					break;
				case 2:
					mostrarTodosClientesESuasEntregas();
					break;
				default:
					System.out.println("Opcao invalida! Redigite!");
			}
		} while(opcao != 0);
		
			
	}
	

	public void menu(){
		System.out.println("=====================================");
        System.out.println("Menu de opcoes: ");
        System.out.println("[0] Sair do sistema");
        System.out.println("[1] Cadastrar um novo cliente e uma entrega correspondente.");
        System.out.println("[2] Mostrar todos os clientes cadastrados e suas entregas correspondentes.");
        System.out.println("=====================================");
	}

	//1
	public void cadastrarClientes(){
		String email;
		String nome;
		String endereco;
		email = entrada.nextLine();
		while (!email.equals("-1")) {
			nome = entrada.nextLine();
			endereco = entrada.nextLine();
			Cliente cliente = new Cliente(email, nome, endereco);
			clientela.cadastraCliente(cliente);
			System.out.println ("1;" + cliente.getEmail() + ";" + cliente.getNome() + ";" + cliente.getEndereco());
			email = entrada.nextLine();
		}
	}

	//2
	public void cadastrarEntregas(){
		int codigo;
		double valor;
		String descricao;
		String email;
		codigo = entrada.nextInt();
		while (codigo != -1){
			valor = entrada.nextDouble();
			entrada.nextLine();
			descricao = entrada.nextLine();
			email = entrada.nextLine();
			Cliente cliente = clientela.pesquisaCliente(email);
			Entrega entrega = new Entrega(codigo, valor, descricao, email, cliente);{
			cadastroEntregas.cadastraEntrega(entrega);
			cliente.adicionaEntrega(entrega);
			System.out.println ("2;" + codigo + ";" + valor +";" + descricao + ";" + email);
			codigo = entrada.nextInt();
			}
		}
	}
	
	//3
	public void quantClientesCadastrados(){
		System.out.println ("3;" + clientela.somatorioClientes());
	}
	
	//4
	public void quantEntregasCadastradas(){
		System.out.println ("4;" + cadastroEntregas.somatorioEntregas());
	}
	
	//5
	public void mostraDadosCliente (){
		entrada.nextLine();
		String email = entrada.nextLine();
		Cliente cliente = clientela.pesquisaCliente(email);
		if (cliente == null){
			System.out.println("5;Cliente inexistente");
		}
		else {
			System.out.println("5;" + cliente.getEmail() + ";" + cliente.getNome() +";"+ cliente.getEndereco());
		}
	}
	
	//6
	public void mostraDadosEntrega(){
		int codigo = entrada.nextInt();
		Entrega entrega =  cadastroEntregas.pesquisaEntrega(codigo);
		Cliente c = clientela.pesquisaCliente(entrega.getEmail());
		if (entrega == null){
			System.out.println("6;Entrega inexistente");
		}
		else {
			System.out.println("6;" + entrega.getCodigo() + ";" + entrega.getValor() +";"+ entrega.getDescricao() + ";" + c.getEmail() + ";" + c.getNome() +";" + c.getEndereco());
		}
	}
	
	//7
	public void mostraDadosEntregaCliente (){
		entrada.nextLine();
		String email = entrada.nextLine();
		ArrayList<Entrega> listaEntregasCliente = cadastroEntregas.pesquisaEntrega(email);
		if (listaEntregasCliente == null){
			System.out.println("7;Cliente inexistente");
		}
		else {
			for (int i = 0; i < listaEntregasCliente.size(); i++){
				System.out.println("7;" + email + ";" + listaEntregasCliente.get(i).getCodigo() + ";" + listaEntregasCliente.get(i).getValor() + ";" + listaEntregasCliente.get(i).getDescricao());
			}
		}
	}
	
	//8
	public void mostraDadosEntregaMaiorValor(){
		Entrega entrega = cadastroEntregas.maiorValor();
		if (entrega == null ){
			System.out.println ("8;Entrega inexistente"); 
		}
		else {
			System.out.println ("8;" + entrega.getCodigo() + ";" + entrega.getValor() +";"+ entrega.getDescricao());
		}
	}
	
	//9
	public void mostraEnderecoDeEntrega(){
		int codigo = entrada.nextInt();
		Entrega entrega = cadastroEntregas.pesquisaEntrega(codigo);
		Cliente cliente = entrega.getCliente();
		if (entrega ==null){
			System.out.println ("9;Entrega inexistente"); 
		}
		else {
			System.out.println ("9;" + entrega.getCodigo() + ";" + entrega.getValor() +";"+ entrega.getDescricao() + ";" + cliente.getEndereco());
		}

	}

	//10
	public void mostraSomatorioDeEntregasDeCliente(){
		entrada.nextLine();
		String email = entrada.nextLine();
		Cliente c = clientela.pesquisaCliente(email);
		double somatorio = 0;
		if (c == null){
			System.out.println("“10;Cliente inexistente");
		}
		ArrayList<Entrega> entregas = c.pesquisaEntregas();
		if (entregas == null){
			System.out.println("10;Entrega inexistente");
		}
		else {
			for (int i = 0; i < entregas.size(); i++){
				somatorio +=entregas.get(i).getValor();
			}
			System.out.printf ("10;%s;%s;%.2f\n", email, c.getNome(), somatorio);
		}
	}

	

	public void cadastrarNovoClienteEEntrega(){
		//novo cliente
		System.out.println("Cadastrando novo cliente...");
		System.out.println("Digite o email: ");
		String email = entrada.nextLine();
		System.out.println("Digite o nome: ");
		String nome = entrada.nextLine();
		System.out.println("Digite o endereco: ");
		String endereco = entrada.nextLine();
		Cliente cliente = new Cliente (email, nome, endereco);
		clientela.cadastraCliente(cliente);

		//nova entrega correspondente ao cliente
		System.out.println("Cadastrando nova entrega... ");
		System.out.println("Digite o codigo da entrega: ");
		int codigo = entrada.nextInt();
		System.out.println("Digite o valor da entrega: ");
		double valor = entrada.nextDouble();
		entrada.nextLine();
		System.out.println("Digite a descricao da entrega: ");
		String descricao = entrada.nextLine();
		System.out.println("Por favor, digite o seu email novamente para concluir a operação: ");
		String email2 = entrada.nextLine();
		Entrega entrega = new Entrega(codigo, valor, descricao, email2, cliente);
		cadastroEntregas.cadastraEntrega(entrega);

		cliente.adicionaEntrega(entrega);
		System.out.println ("Feito!");
	}

	public void mostrarTodosClientesESuasEntregas(){
		System.out.println("Todos os clientes e suas entregas: ");
		ArrayList<Cliente> clientes = clientela.getClientes();
		for (int i = 0; i < clientes.size(); i++){
			Cliente cliente = clientes.get(i); 
			System.out.println("Cliente " + (i+1) + ": " + cliente.getNome() + " - " + cliente.getEmail() + " - " + cliente.getEndereco());
			ArrayList<Entrega> entregasCliente = cadastroEntregas.pesquisaEntrega(cliente.getEmail());
			for (int j = 0; j < entregasCliente.size(); j++){
				Entrega entrega = entregasCliente.get(j);
				System.out.println("		Entrega " + (j+1) + ": " + "Codigo da entrega: #" + entrega.getCodigo() + " - $" + entrega.getValor() + " - " + entrega.getDescricao() + " - " + entrega.getEmail());
			}
		}
	}
}
