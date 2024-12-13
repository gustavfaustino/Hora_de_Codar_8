import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<ContaBancaria> contas = new ArrayList<>();
        Scanner entrada = new Scanner(System.in);


        System.out.println("Digite seu nome: ");
        String nomeUsuario = entrada.nextLine();
        System.out.println("Olá " + nomeUsuario + ", é um prazer ter você por aqui!");

        boolean continuar = true;

        while (continuar) {
            try {

                System.out.println("Escolha uma opção: \n1. Criar Conta \n2. Saldo \n3. Extrato \n4. Saque \n5. Depósito \n6. Transferência \n7. Sair");
                int opcao = entrada.nextInt();
                entrada.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("Digite o nome do titular: ");
                        String nomeTitular = entrada.nextLine();

                        System.out.println("Digite o saldo inicial: ");
                        double saldoInicial = entrada.nextDouble();
                        entrada.nextLine(); // Limpa o buffer

                        System.out.println("Digite o número da conta: ");
                        String numeroConta = entrada.nextLine();

                        contas.add(new ContaBancaria(nomeTitular, saldoInicial, numeroConta));
                        System.out.println("Conta criada com sucesso!");
                        break;

                    case 2:
                        if (verificarSenha(entrada)) {
                            System.out.println("Digite o número da conta: ");
                            String numeroContaSaldo = entrada.nextLine();
                            boolean contaSaldoEncontrada = false;

                            for (ContaBancaria conta : contas) {
                                if (conta.getNumeroConta().equals(numeroContaSaldo)) {
                                    System.out.println("O saldo da conta de " + conta.getTitular() + " é " + conta.getSaldo());
                                    contaSaldoEncontrada = true;
                                    break;
                                }
                            }
                            if (!contaSaldoEncontrada) {
                                System.out.println("Conta não encontrada.");
                            }
                        }
                        break;

                    case 3:
                        if (verificarSenha(entrada)) {
                            System.out.println("Digite o número da conta para visualizar o extrato: ");
                            String numeroContaExtrato = entrada.nextLine();
                            boolean contaExtratoEncontrada = false;

                            for (ContaBancaria conta : contas) {
                                if (conta.getNumeroConta().equals(numeroContaExtrato)) {
                                    conta.visualizarExtrato();
                                    contaExtratoEncontrada = true;
                                    break;
                                }
                            }
                            if (!contaExtratoEncontrada) {
                                System.out.println("Conta não encontrada.");
                            }
                        }
                        break;

                    case 4:
                        if (verificarSenha(entrada)) {
                            System.out.println("Digite o número da conta: ");
                            String numeroContaSaque = entrada.nextLine();

                            System.out.println("Digite o valor do saque: ");
                            double valorSaque = entrada.nextDouble();
                            entrada.nextLine();

                            boolean contaSaqueEncontrada = false;
                            for (ContaBancaria conta : contas) {
                                if (conta.getNumeroConta().equals(numeroContaSaque)) {
                                    if (valorSaque <= 0) {
                                        System.out.println("Operação não autorizada. O valor do saque deve ser maior que zero.");
                                    } else if (conta.sacar(valorSaque)) {
                                        System.out.println("Saque realizado com sucesso!");
                                    } else {
                                        System.out.println("Operação não autorizada.");
                                    }
                                    contaSaqueEncontrada = true;
                                    break;
                                }
                            }
                            if (!contaSaqueEncontrada) {
                                System.out.println("Conta não encontrada.");
                            }
                        }
                        break;

                    case 5:
                        if (verificarSenha(entrada)) {
                            System.out.println("Digite o número da conta: ");
                            String numeroContaDeposito = entrada.nextLine();

                            System.out.println("Digite o valor do depósito: ");
                            double valorDeposito = entrada.nextDouble();
                            entrada.nextLine();

                            if (valorDeposito <= 0) {
                                System.out.println("Operação não autorizada. O valor do depósito deve ser maior que zero.");
                            } else {
                                boolean contaDepositoEncontrada = false;
                                for (ContaBancaria conta : contas) {
                                    if (conta.getNumeroConta().equals(numeroContaDeposito)) {
                                        conta.depositar(valorDeposito);
                                        System.out.println("Depósito realizado com sucesso!");
                                        contaDepositoEncontrada = true;
                                        break;
                                    }
                                }
                                if (!contaDepositoEncontrada) {
                                    System.out.println("Conta não encontrada.");
                                }
                            }
                        }
                        break;

                    case 6:
                        if (verificarSenha(entrada)) {
                            System.out.println("Digite o número da conta de origem: ");
                            String numeroContaOrigem = entrada.nextLine();

                            System.out.println("Digite o número da conta de destino: ");
                            String numeroContaDestino = entrada.nextLine();

                            System.out.println("Digite o valor da transferência: ");
                            double valorTransferencia = entrada.nextDouble();
                            entrada.nextLine();

                            if (valorTransferencia <= 0) {
                                System.out.println("Operação não autorizada. O valor da transferência deve ser maior que zero.");
                            } else {
                                boolean contaOrigemEncontrada = false;
                                boolean contaDestinoEncontrada = false;
                                for (ContaBancaria conta : contas) {
                                    if (conta.getNumeroConta().equals(numeroContaOrigem)) {
                                        if (conta.sacar(valorTransferencia)) {
                                            contaOrigemEncontrada = true;
                                        }
                                        break;
                                    }
                                }
                                for (ContaBancaria conta : contas) {
                                    if (conta.getNumeroConta().equals(numeroContaDestino)) {
                                        conta.depositar(valorTransferencia);
                                        contaDestinoEncontrada = true;
                                        break;
                                    }
                                }
                                if (contaOrigemEncontrada && contaDestinoEncontrada) {
                                    System.out.println("Transferência realizada com sucesso!");
                                } else {
                                    System.out.println("Operação não autorizada. Verifique se a conta de origem tem saldo suficiente ou se as contas existem.");
                                }
                            }
                        }
                        break;

                    case 7:
                        System.out.println(nomeUsuario + ", foi um prazer ter você por aqui!");
                        Thread.sleep(1000);
                        continuar = false;
                        System.out.println("Sistema encerrado.");
                        break;

                    default:
                        erro();
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                entrada.nextLine();
            }
        }
        entrada.close();
    }

    public static void erro() {
        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
    }


    public static boolean verificarSenha(Scanner entrada) {
        while (true) {
            System.out.println("Digite a senha para continuar: ");
            int senha = entrada.nextInt();
            entrada.nextLine();
            if (senha == 3589) {
                return true;
            } else {
                System.out.println("Senha incorreta. Acesso negado. Tente novamente.");
            }
        }
    }
}
