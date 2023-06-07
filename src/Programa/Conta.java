package Programa;

import utilitarios.Utils;

public abstract class Conta {
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

	public abstract void depositar(Double valor);

	public abstract void sacar(Double valor);

	public abstract void transferir(Conta contaParaDeposito, Double valor);

	public String toString() {
		return "\nNumero da conta : " + this.getNumeroConta()
				+ "\nNome: " + this.pessoa.getNome()
				+ "\nCPF : " + this.pessoa.getcpf()
				+ "\nEmail: " + this.pessoa.getEmail()
				+ "\nSaldo : " + Utils.doubleToString(this.getSaldo());
	}


}
