package Programa;

import utilitarios.Utils;

public class Conta {

	private static int contadorDeContas = 1;

	private int numeroConta;
	private Pessoa pessoa;
	private Double saldo = 0.0;

	public Conta(Pessoa pessoa) {
		this.numeroConta = contadorDeContas;
		this.pessoa = pessoa;
		contadorDeContas++;

	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String toString() {
		return "\nNumero da conta : " + this.getNumeroConta() +
				"\nNome: " + this.pessoa.getNome() +
				"\nCPF : " + this.pessoa.getcpf() +
				"\nEmail: " + this.pessoa.getEmail() +
				"\nSaldo : " + Utils.doubleToString(this.getSaldo());
	}

	
	
	public void depositar(Double valor) {
		if(valor > 0) {
			setSaldo(getSaldo() + valor);
			System.out.println("Seu depósito foi realizado com sucesso");
		}else {
			System.out.println("Não foi possivel realizar o deposito! ");
		}
	}
	
	public  void sacar(double valor) {
		if(valor > 0 && this.getSaldo() >= valor) {
			setSaldo(getSaldo()- valor);
			System.out.println("Seu Saque foi realizado com sucesso");

		}else {
			System.out.println("Não foi possivel realizar o Saque ! ");

		}
	}
	
	public void tranferir (Conta contaParaDeposito, double valor) {
		if(valor > 0 && this.getSaldo() >= valor) {
			setSaldo(getSaldo() - valor);
			contaParaDeposito.saldo = contaParaDeposito.getSaldo() + valor;
			System.out.println("Transferencia realizada com sucesso");
		}else {
			System.out.println("Não foi possivel realizar a transferência ");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}