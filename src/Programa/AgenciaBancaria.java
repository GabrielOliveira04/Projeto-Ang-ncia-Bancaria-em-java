package Programa;

import utilitarios.Validacao;

import java.util.ArrayList;
import java.util.Scanner;


public class AgenciaBancaria {
	
	static Scanner input = new Scanner(System.in);
	static ArrayList<Conta> contasBancarias;
	
	public static void main(String[] args) {
		contasBancarias = new ArrayList<Conta>();
		operacoes();
	}
	
	public static void operacoes() {
		System.out.println("-------------------------------------------------------------------------- ");
		System.out.println("-----------------Bem vindos ao BanKGO--------------------------------------");
		System.out.println("--------------------------------------------------------------------------- ");
		System.out.println(" 		***** Selecione uma operação que deseja realizar ******");
		System.out.println("--------------------------------------------------------------------------- ");
		System.out.println("|      Opção 1 - Criar conta       |");
		System.out.println("|      Opção 2 - Depositar         |");
		System.out.println("|      Opção 3 - Sacar         |");
		System.out.println("|      Opção 4 - Tranferir         |");
		System.out.println("|      Opção 5 - Listar         |");
		System.out.println("|      Opção 6 - Sair         |");
	
		int operacao =  input.nextInt();
		switch(operacao) {
		case 1:
			criarConta();
			break;
		case 2:
			depositar();
			break;
		case 3:
			sacar();
			break;
		case 4:
			tranferir();
			break;
		case 5:
			listarContas();
			break;
		case 6:
			System.out.println("Obrigado por usar a nossa agência");
			System.exit(0);
			
		default:
			System.out.println("Opção inválida ! ");
			operacoes();
			
		
		}
	}



	public static void criarConta() {
		try {
			System.out.println("\nNome: ");
			String nome = input.next();
			input.nextLine();
			if (!Validacao.validarNome(nome)) {
				throw new Exception("Nome inválido.");
			}

			System.out.println("\nCPF: ");
			String cpf = input.nextLine();
			if (!Validacao.validarCPF(cpf)) {
				throw new Exception("CPF inválido.");
			}

			System.out.println("\nEmail: ");
			String email = input.nextLine();
			if (!Validacao.validarEmail(email)) {
				throw new Exception("Email inválido.");
			}

			Pessoa pessoa = new Pessoa(nome, cpf, email);
			Conta conta = new Conta(pessoa);
			contasBancarias.add(conta);

			System.out.println("Sua conta foi criada com sucesso!");
			operacoes();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao criar a conta: " + e.getMessage());
			operacoes();
		}
	}


	public static Conta encontrarConta( int numeroConta) {
		Conta conta = null;
		if(contasBancarias.size() >0){
			for(Conta c :contasBancarias) {
				if(c.getNumeroConta() == numeroConta);{
				conta = c;
			}
		}
	}
		return conta;
	}
	
	public static void depositar() {
		System.out.println("Numero da conta");
		int numeroConta = input.nextInt();
		
		Conta conta = encontrarConta(numeroConta);
		
		if(conta != null) {
			System.out.println("Qual o valor deseja depositar? ");
			Double valorDeposito = input.nextDouble();
			conta.depositar(valorDeposito);
			System.out.println("Valor depositado com sucesso ");
		}else {
			System.out.println("A conta não foi encontrada! ");
		}
		operacoes();
	} 	
	
	public static void sacar() {
		System.out.println(" Número da conta: ");
		int numeroConta = input.nextInt();
		
		Conta conta = encontrarConta(numeroConta);
		if( conta !=null) {
			System.out.println(" Qual valor deseja sacar? ");
			Double valorSaque = input.nextDouble();
			conta.sacar(valorSaque);
			//System.out.println(" Valor sacado com sucesso ");
		}else {
			System.out.println(" Você não tem dinheiro disponivel ");
		}
		operacoes();
	}
	
	public static void tranferir() {
		System.out.println("Número da conta do remetente: ");
		int numeroContaRemetente = input.nextInt();
		Conta contaRemetente = encontrarConta(numeroContaRemetente);
		
		if(contaRemetente != null) {
			System.out.println("Número da conta do destinatário: ");
			int numeroContaDestinatario = input.nextInt();
			
			Conta contaDestinatario = encontrarConta(numeroContaDestinatario);
			
			if(contaDestinatario != null) {
				System.out.println(" Qual valor deseja transferir");
				Double valor = input.nextDouble();
				
				contaRemetente.tranferir(contaDestinatario, valor);
			}else {
				System.out.println("A conta para deposito não foi encontrada");
			}
		}else {
			System.out.println("Conta para transferÊncia não encontrada");
		}
			operacoes();
		
	}
	
	public static void listarContas() {
	if(contasBancarias.size()>0) {
		for(Conta conta:contasBancarias) {
			System.out.println(conta);
		}
	}else {
		System.out.println("Não há contas cadastradas! ");
	}
	
	operacoes();
	}
	
	
}		
	

