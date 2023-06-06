package Programa;

public class ContaInvestimento extends Conta {

    public ContaInvestimento(Pessoa pessoa) {
        super(pessoa);
    }

    @Override
    public void depositar(Double valor) {
        if (valor > 0) {
            setSaldo(getSaldo() + valor);
            System.out.println("Seu depósito foi realizado com sucesso");
        } else {
            System.out.println("Não foi possível realizar o depósito! ");
        }
    }

    @Override
    public void sacar(Double valor) {
        if (valor > 0 && getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            System.out.println("Seu saque foi realizado com sucesso");
        } else {
            System.out.println("Não foi possível realizar o saque! ");
        }
    }

    @Override
    public void transferir(Conta contaParaDeposito, Double valor) {
        if (valor > 0 && getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            contaParaDeposito.setSaldo(contaParaDeposito.getSaldo() + valor);
            System.out.println("Transferência realizada com sucesso");
        } else {
            System.out.println("Não foi possível realizar a transferência ");
        }
    }

    public void rendimentoInvestimento() {
        double rendimento = getSaldo() * 0.10; // 10% de rendimento
        setSaldo(getSaldo() + rendimento);
        System.out.println("Rendimento do investimento aplicado: " + rendimento);
    }
}
