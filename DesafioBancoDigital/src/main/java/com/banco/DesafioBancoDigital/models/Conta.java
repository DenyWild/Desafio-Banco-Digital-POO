package com.banco.DesafioBancoDigital.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class Conta implements Iconta {

	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	private int agencia;
	private int numero;
	private double saldo;
	private Cliente cliente;

	public Conta(Cliente cliente) {

		this.agencia = AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;

	}

	@Override
	public void sacar(double valor) {
		if (valor > this.saldo) {
			throw new RuntimeException(
					"Não é possível sacar um valor maior que o saldo" + "/n" + "Saldo : " + this.saldo);
		} else if (valor < this.saldo) {
			this.saldo = saldo - valor;
		}

	}

	@Override
	public void depositar(double valor) {
		this.saldo = saldo + valor;

	}

	@Override
	public void transferir(Conta contaDestino, double valor) {

		if (valor > saldo) {
			throw new RuntimeException(
					"Não é possível transferir um valor maior que o seu saldo" + "/n" + "Saldo : " + this.saldo);
		} else if (valor < saldo) {
			this.sacar(valor);
			contaDestino.depositar(valor);
		}

	}

	protected void imprimirInfoComuns() {

		System.out.println("Títular: " + this.cliente.getNome());
		System.out.println("Agência: " + this.getAgencia());
		System.out.println("Numero: " + this.getNumero());
		System.out.printf("Saldo: %.2f ", this.getSaldo());

	}

}
