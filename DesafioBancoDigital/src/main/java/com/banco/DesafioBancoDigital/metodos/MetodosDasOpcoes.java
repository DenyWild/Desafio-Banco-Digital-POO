package com.banco.DesafioBancoDigital.metodos;

import java.util.Scanner;

public class MetodosDasOpcoes {

	Scanner input = new Scanner(System.in);

	public void checarMetodoValido(int opcao) {
		if (opcao > 6 || opcao < 0) {

			System.out.println("Opção não valida \nInsira um opção que esteja no menu!\n");

		} else {
			System.out.println();
		}

	}

	public boolean cpfChecado(String cpf) {

		boolean resposta = false;

		if (cpf.length() < 11 || cpf.length() > 11) {
			cpf = null;
			System.out.println("Um CPF possui 11 caracteres, favor tentar novamente.");
		} else if (cpf.length() == 11) {
			resposta = true;
			System.out.println("CPF EXISTENTE");
		}

		return resposta;
	}

}
