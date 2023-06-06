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
		System.out.println("         ***** Selecione uma operação que deseja realizar ******");
		System.out.println("--------------------------------------------------------------------------- ");
		System.out.println("|      Opção 1 - Criar conta     					  |");
		System.out.println("|      Opção 2 - Depositar     					      |");
		System.out.println("|      Opção 3 - Sacar        				          |");
		System.out.println("|      Opção 4 - Transferir       				      |");
		System.out.println("|      Opção 5 - Listar           					  |");
		System.out.println("|      Opção 6 - Rendimento Poupança             	  |");
		System.out.println("|      Opção 7 - Rendimento Investimento              |");
		System.out.println("|      Opção 8 - Sair             					  |");

		int operacao = input.nextInt();
		switch (operacao) {
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
				transferir();
				break;
			case 5:
				listarContas();
				break;

			case 6:
				rendimentoPoupanca();
				break;

			case 7:
				rendimentoInvestimento();
				break;

				case 8:
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

			System.out.println("Qual tipo de conta deseja criar? (1 - Conta Corrente, 2 - Conta Poupança, 3 - Conta Investimento)");
			int tipoConta = input.nextInt();
			Conta conta;

			switch (tipoConta) {
				case 1:
					conta = new ContaCorrente(pessoa);
					break;
				case 2:
					conta = new ContaPoupanca(pessoa);
					break;
				case 3:
					conta = new ContaInvestimento(pessoa);
					break;
				default:
					throw new Exception("Tipo de conta inválido.");
			}

			// Gerar número aleatório para a conta
			int numeroConta = gerarNumeroContaAleatorio();
			conta.setNumeroConta(numeroConta);

			contasBancarias.add(conta);

			System.out.println("Sua conta foi criada com sucesso!");
			operacoes();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao criar a conta: " + e.getMessage());
			operacoes();
		}
	}

	public static int gerarNumeroContaAleatorio() {
		// Gerar um número de conta aleatório
		int numeroConta;
		boolean numeroExistente;

		do {
			numeroConta = (int) (Math.random() * 10000); // Gera um número aleatório de 0 a 9999

			// Verificar se o número já existe nas contas cadastradas
			int finalNumeroConta = numeroConta;
			numeroExistente = contasBancarias.stream().anyMatch(conta -> conta.getNumeroConta() == finalNumeroConta);
		} while (numeroExistente);

		return numeroConta;
	}

	public static Conta encontrarConta(int numeroConta) {
		for (Conta conta : contasBancarias) {
			if (conta.getNumeroConta() == numeroConta) {
				return conta;
			}
		}
		return null;
	}

	public static void depositar() {
		System.out.println("Número da conta: ");
		int numeroConta = input.nextInt();

		Conta conta = encontrarConta(numeroConta);

		if (conta != null) {
			System.out.println("Qual o valor deseja depositar? ");
			Double valorDeposito = input.nextDouble();
			conta.depositar(valorDeposito);
			System.out.println("Valor depositado com sucesso ");
		} else {
			System.out.println("A conta não foi encontrada! ");
		}
		operacoes();
	}

	public static void sacar() {
		System.out.println("Número da conta: ");
		int numeroConta = input.nextInt();

		Conta conta = encontrarConta(numeroConta);
		if (conta != null) {
			System.out.println("Qual valor deseja sacar? ");
			Double valorSaque = input.nextDouble();

			// Verificar o tipo de conta e aplicar desconto de tarifa (se necessário)
			if (conta instanceof ContaCorrente || conta instanceof ContaPoupanca || conta instanceof ContaInvestimento) {
				System.out.println("De qual conta deseja sacar? (1 - Conta Corrente, 2 - Conta Poupança, 3 - Conta Investimento)");
				int tipoSaque = input.nextInt();

				if (tipoSaque == 1 && conta instanceof ContaCorrente) {
					((ContaCorrente) conta).sacar(valorSaque); // Realizar o saque na conta corrente
				} else if (tipoSaque == 2 && conta instanceof ContaPoupanca) {
					((ContaPoupanca) conta).sacar(valorSaque); // Realizar o saque na conta poupança
				}else if (tipoSaque == 3 && conta instanceof ContaInvestimento) {
						((ContaInvestimento) conta).sacar(valorSaque);
				} else {
					System.out.println("Tipo de conta inválido para saque.");
				}
			} else {
				conta.sacar(valorSaque); // Realizar o saque normalmente para outros tipos de conta
			}
		} else {
			System.out.println("A conta não foi encontrada! ");
		}
		operacoes();
	}


	public static void transferir() {
		System.out.println("Número da conta do remetente: ");
		int numeroContaRemetente = input.nextInt();
		Conta contaRemetente = encontrarConta(numeroContaRemetente);

		if (contaRemetente != null) {
			System.out.println("Número da conta do destinatário: ");
			int numeroContaDestinatario = input.nextInt();

			Conta contaDestinatario = encontrarConta(numeroContaDestinatario);

			if (contaDestinatario != null) {
				System.out.println("Qual valor deseja transferir? ");
				Double valor = input.nextDouble();

				contaRemetente.transferir(contaDestinatario, valor);
			} else {
				System.out.println("A conta de destino não foi encontrada.");
			}
		} else {
			System.out.println("A conta remetente não foi encontrada.");
		}
		operacoes();
	}
	public static void rendimentoPoupanca() {
		System.out.println("Número da conta poupança: ");
		int numeroConta = input.nextInt();

		Conta conta = encontrarConta(numeroConta);
		if (conta != null && conta instanceof ContaPoupanca) {
			((ContaPoupanca) conta).rendimentoPoupanca();
		} else {
			System.out.println("A conta poupança não foi encontrada! ");
		}
		operacoes();
	}

	public static void rendimentoInvestimento() {
		System.out.println("Número da conta investimento: ");
		int numeroConta = input.nextInt();

		Conta conta = encontrarConta(numeroConta);
		if (conta != null && conta instanceof ContaInvestimento) {
			((ContaInvestimento) conta).rendimentoInvestimento();
		} else {
			System.out.println("A conta investimento não foi encontrada! ");
		}
		operacoes();
	}

	public static void listarContas() {
		if (contasBancarias.size() > 0) {
			for (Conta conta : contasBancarias) {
				System.out.println(conta);
			}
		} else {
			System.out.println("Não há contas cadastradas! ");
		}
		operacoes();
	}
}
