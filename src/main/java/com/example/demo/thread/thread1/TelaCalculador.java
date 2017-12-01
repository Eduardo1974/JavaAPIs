package com.example.demo.thread.thread1;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class TelaCalculador {
	
	public static void main(String[] args) {
		
		JFrame janela = new JFrame("Multiplicação Demorada");

		JTextField primeiro = new JTextField(10);
		JTextField segundo = new JTextField(10);
		JButton botao = new JButton(" = ");
		JLabel resultado = new JLabel("           ?          ");
		
		//quando clica no botão será executado a classe Multiplicador
		botao.addActionListener(new AcaoBotao(primeiro, segundo, resultado));
		
		JPanel painel = new JPanel();
		painel.add(primeiro);
		painel.add(new JLabel("x"));
		painel.add(segundo);
		painel.add(botao);
		painel.add(resultado);
		
		janela.add(painel);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.pack();
		janela.setVisible(true);
	}
}

 class AcaoBotao implements ActionListener {

	 private JTextField primeiro;
	 private JTextField segundo;
	 private JLabel resultado;

	public AcaoBotao(JTextField primeiro, JTextField segundo, JLabel resultado) {
		this.primeiro = primeiro;
		this.segundo = segundo;
		this.resultado = resultado;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//DESSA FORMA A INTERFACE NÃO IRÁ TRAVAR CASO O CALCULO SEJA MUITO DEMORADO, POIS ELE SERÁ EXECUTADO
		// EM UMA THREAD PARALELA
		TarefaMultiplicacao tarefaMultiplicacao = new TarefaMultiplicacao(primeiro, segundo, resultado);
		Thread calculoThread = new Thread(tarefaMultiplicacao, "Thread calculadora");
		calculoThread.start();
	}

}

class TarefaMultiplicacao implements Runnable {

	private JTextField primeiro;
	private JTextField segundo;
	private JLabel resultado;

	public TarefaMultiplicacao(JTextField primeiro, JTextField segundo, JLabel resultado) {
		this.primeiro = primeiro;
		this.segundo = segundo;
		this.resultado = resultado;
	}

	@Override
	public void run() {
		long valor1 = Long.parseLong(primeiro.getText());
		long valor2 = Long.parseLong(segundo.getText());
		BigInteger calculo = new BigInteger("0");

		for (int i = 0; i < valor1; i++) {
			for (int j = 0; j < valor2; j++) {
				calculo = calculo.add(new BigInteger("1"));
			}
		}
		resultado.setText(calculo.toString());
	}
}

