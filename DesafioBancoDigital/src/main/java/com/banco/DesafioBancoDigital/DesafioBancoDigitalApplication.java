package com.banco.DesafioBancoDigital;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.banco.DesafioBancoDigital.metodos.MetodosDasOpcoes;
import com.banco.DesafioBancoDigital.models.Cliente;
import com.banco.DesafioBancoDigital.models.Conta;
import com.banco.DesafioBancoDigital.models.ContaCorrente;
import com.banco.DesafioBancoDigital.models.ContaPoupanca;

@SpringBootApplication
public class DesafioBancoDigitalApplication {

	private static int numero;

	private static int numeroTransf;

	public static void main(String[] args) {
		SpringApplication.run(DesafioBancoDigitalApplication.class, args);

		Scanner input = new Scanner(System.in);

		MetodosDasOpcoes mtd = new MetodosDasOpcoes();

		List<Conta> contas = new ArrayList<Conta>();

		int opcao;

		double valor = 0;

		boolean checar = false;

		do {
			do {

				System.out.println("X-------BANCO DIGITAL FELLAS--------X");
				System.out.println("1 - Criar conta");
				System.out.println("2 - Sacar ");
				System.out.println("3 - Depositar");
				System.out.println("4 - Transferir ");
				System.out.println("5 - Listar Contas");
				System.out.println("6 - Imprimir Extrato por CPF");
				System.out.println("0 - para SAIR");
				System.out.println("X-----------------------------------X");
				System.out.println("Insira a opção desejada:");

				opcao = input.nextInt();
				mtd.checarMetodoValido(opcao);

			} while (opcao > 6 || opcao < 0);
			switch (opcao) {

			case 0:
				System.out.println("*------------------------------------------------------------*");
				System.out.println("|ENCERRADANDO APLICAÇÃO...                                   |");
				System.out.println("|OBRIGADO!                                                   |");
				System.out.println("*------------------------------------------------------------*");
				break;

			case 1:
				System.out.println("---CRIAR CONTA---");

				Cliente cliente = new Cliente();
				System.out.println("Informe seus dados primeiramente.");
				System.out.println("Digite seu nome: ");
				input.nextLine();
				cliente.setNome(input.nextLine());

				do {
					System.out.println("Digite seu cpf: ");
					cliente.setCpf(input.nextLine());

					checar = mtd.cpfChecado(cliente.getCpf());

				} while (checar == false);

				Conta cc;

				int tipo_conta;

				System.out.println("1 - Conta corrente  | 2 - Conta Poupança ");
				tipo_conta = input.nextInt();

				if (tipo_conta == 1) {
					cc = new ContaCorrente(cliente);

				} else if (tipo_conta == 2) {
					cc = new ContaPoupanca(cliente);

				} else {
					System.out.println("É preciso escolher um tipo de conta existente. Tente Novamente");

					return;
				}

				contas.add(cc);

				break;

			case 2:
				System.out.println("---SAQUE---");

				Conta contaSaque = null;

				System.out.println("Digite o numero da sua conta: ");
				numero = input.nextInt();

				for (int i = 0; i < contas.size(); i++) {

					if (contas.get(i).getNumero() == numero) {
						contaSaque = contas.get(i);
						System.out.println("Digite o valor que você deseja sacar");
						valor = input.nextDouble();
					}

				}

				try {
					contaSaque.sacar(valor);
				} catch (RuntimeException e) {
					e.getMessage();
				}

				break;

			case 3:
				System.out.println("---DEPOSITAR---");

				Conta contaDep = null;

				System.out.println("Digite o numero da conta: ");
				int numero = input.nextInt();

				for (int i = 0; i < contas.size(); i++) {

					if (contas.get(i).getNumero() == numero) {
						contaDep = contas.get(i);
						System.out.println("Digite o valor que você deseja depositar");
						valor = input.nextDouble();
					}
				}

				contaDep.depositar(valor);

				break;

			case 4:
				System.out.println("---TRANSFERIR---");

				Conta contaTransf = null;

				System.out.println("Digite o numero da sua conta: ");
				numero = input.nextInt();

				for (int i = 0; i < contas.size(); i++) {

					if (contas.get(i).getNumero() == numero) {
						contaSaque = contas.get(i);
						System.out.println("Digite o numero da conta destino que você deseja transferir");
						numeroTransf = input.nextInt();

						for (int j = 0; j < contas.size(); j++) {

							if (contas.get(j).getNumero() == numeroTransf) {
								contaTransf = contas.get(j);

								System.out.println("Digite o valor que você deseja transferir: ");
								valor = input.nextDouble();
								try {
									contaSaque.sacar(valor);
								} catch (RuntimeException e) {
									e.getMessage();
								}
								contaTransf.depositar(valor);
							}

						}
					}

				}

				break;

			case 5:
				System.out.println("---LISTAR CONTAS---");

				contas.stream().forEach(lista -> System.out.println(lista));

				System.out.println("-------------------");

				break;

			case 6:
				System.out.println("---EXTRATO---");

				System.out.println("Digite o CPF para imprimir o extrato da sua conta: ");
				input.nextLine();
				String cpf = input.nextLine();

				for (int i = 0; i < contas.size(); i++) {

					if (contas.get(i).getCliente().getCpf().equals(cpf)) {

						Conta contaExtrato = contas.get(i);

						contaExtrato.imprimirExtrato();
					}
				}

				break;

			}

		} while (opcao != 0);

		input.close();

	}

}
