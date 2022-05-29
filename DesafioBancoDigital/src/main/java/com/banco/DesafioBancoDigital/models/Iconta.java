package com.banco.DesafioBancoDigital.models;

public interface Iconta {

	void sacar(double valor);

	void depositar(double valor);

	void transferir(Conta contaDestino, double valor);

	void imprimirExtrato();

}
