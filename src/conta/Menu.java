package conta;

import conta.controller.ContaController;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import conta.util.Cores;

public class Menu {
	public static void main(String[] args) {

		ContaController contas = new ContaController();

		Scanner leia = new Scanner(System.in);

		int opcao, numero, agencia, tipo, aniversario;
		String titular;
		float saldo, limite;

		System.out.println("\nCriar Contas\n");

		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João Da Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria Da Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);
		ContaCorrente cp1 = new ContaCorrente(contas.gerarNumero(), 125, 2, "Mariana Dos Santos", 2000f, 12);
		contas.cadastrar(cp1);
		ContaCorrente cp2 = new ContaCorrente(contas.gerarNumero(), 126, 2, "Juliana Ramos", 8000f, 15);
		contas.cadastrar(cp2);

		while (true) {

			System.out.println(Cores.TEXT_PURPLE + Cores.ANSI_BLACK_BACKGROUND
					+ " ***************************************************** ");
			System.out.println("                                                       ");
			System.out.println("                 BANCO DO BRAZIL COM Z                 ");
			System.out.println("                                                       ");
			System.out.println(" ***************************************************** ");
			System.out.println("                                                       ");
			System.out.println("             1 - Criar Conta                           ");
			System.out.println("             2 - Listar todas as Contas                ");
			System.out.println("             3 - Buscar Conta por Numero               ");
			System.out.println("             4 - Atualizar Dados da Conta              ");
			System.out.println("             5 - Apagar Conta                          ");
			System.out.println("             6 - Sacar                                 ");
			System.out.println("             7 - Depositar                             ");
			System.out.println("             8 - Transferir valores entre Contas       ");
			System.out.println("             9 - Sair                                  ");
			System.out.println("                                                       ");
			System.out.println(" ***************************************************** ");
			System.out.println(" Entre com a opção desejada:                           ");
			System.out.println("                                                       " + Cores.TEXT_RESET);

			try {
				opcao = leia.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				opcao = 0;
			}

			if (opcao == 9) {
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Brazil com Z - O seu Futuro começa aqui!");
				sobre();
				leia.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE_BOLD + "\n Criar Conta");
				System.out.println("Digite o número da agência: ");
				agencia = leia.nextInt();
				System.out.println("Digite o nome do titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();
				System.out.println("Digite o saldo da conta (R$): ");
				saldo = leia.nextFloat();

				do {
					System.out.println("Digite o tipo de conta: (1 - CC ou 2 - CP):");
					tipo = leia.nextInt();
				} while (tipo < 1 && tipo > 2);

				System.out.println("Digite o limite de crédito (R$): ");
				limite = leia.nextFloat();

				switch (tipo) {
				case 1 -> {
					System.out.println("Digite o limite de crédito R$: ");
					limite = leia.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					System.out.println("Digite o dia do aniversário da conta: ");
					aniversario = leia.nextInt();
					contas.cadastrar(
							new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}
				}

				keyPress();
				break;
			case 2:
				System.out.println(Cores.TEXT_WHITE_BOLD + "\n Listar todas as Contas");
				contas.listarTodas();
				keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE_BOLD + "\n Buscar Conta por número");

				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();

				contas.procurarPorNumero(numero);
				keyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE_BOLD + "\n Atualizar dados da Conta");

				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();

				var buscaConta = contas.buscarNaCollection(numero);

				if (buscaConta != null) {
					tipo = buscaConta.getTipo();

					System.out.println("Digite o número da agência: ");
					agencia = leia.nextInt();
					System.out.println("Digite o nome do titular: ");
					leia.skip("\\R?");
					titular = leia.nextLine();
					System.out.println("Digite o saldo da conta (R$): ");
					saldo = leia.nextFloat();

					do {
						System.out.println("Digite o tipo de conta: (1 - CC ou 2 - CP):");
						tipo = leia.nextInt();
					} while (tipo < 1 && tipo > 2);

					System.out.println("Digite o limite de crédito (R$): ");
					limite = leia.nextFloat();

					switch (tipo) {
					case 1 -> {
						System.out.println("Digite o limite de crédito R$: ");
						limite = leia.nextFloat();
						contas.atualizar(
								new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
					}
					case 2 -> {
						System.out.println("Digite o dia do aniversário da conta: ");
						aniversario = leia.nextInt();
						contas.atualizar(
								new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
					}
					default -> {
						System.out.println("Tipo de conta inválido");
					}
					}
					
				} else 
					System.out.println("A conta não foi encontrada");

				keyPress();
				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE_BOLD + "\n Apagar Conta");
				
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				
				contas.deletar(numero);

				keyPress();
				break;
			case 6:
				System.out.println(Cores.TEXT_WHITE_BOLD + "\n Sacar");

				keyPress();
				break;
			case 7:
				System.out.println(Cores.TEXT_WHITE_BOLD + "\n Depositar");

				keyPress();
				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE_BOLD + "\n Transferir");

				keyPress();
				break;
			default:
				System.out.println(Cores.TEXT_RED + "\nOpção Inválida" + Cores.TEXT_RESET);

				keyPress();
				break;
			}
		}
	}

	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: ");
		System.out.println("Mariana Carmo - marianapimentel.mpc@gmail.com");
		System.out.println("github.com/MariPimentelCarmo");
		System.out.println("*********************************************************");
	}

	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
	}

}
