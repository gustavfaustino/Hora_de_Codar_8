import java.util.ArrayList;

public class ContaBancaria {
    private String titular;
    private double saldo;
    private String numeroConta;
    private ArrayList<String> extrato;

    public ContaBancaria(String titular, double saldo, String numeroConta) {
        this.titular = titular;
        this.saldo = saldo;
        this.numeroConta = numeroConta;
        this.extrato = new ArrayList<>();
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String novoNumero) {
        this.numeroConta = novoNumero;
    }

    public boolean sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            extrato.add("Saque de R$ " + valor);
            return true;
        }
        return false;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            extrato.add("Depósito de R$ " + valor);
        }
    }

    public void visualizarExtrato() {
        System.out.println("Extrato da conta de " + titular + ":");
        if (extrato.isEmpty()) {
            System.out.println("Nenhuma transação realizada.");
        } else {
            for (String transacao : extrato) {
                System.out.println(transacao);
            }
        }
    }
}
