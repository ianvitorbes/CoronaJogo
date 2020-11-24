package coronaJogo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import coronaJogo.tela.Estagio;

public class Container extends JFrame {
	public static final int LARGURA_TELA = 1000;
	public static final int ALTURA_TELA = 700;

	// metodo construtor, aqui terá todas as configuraçoes da nossa tela do jogo
	// (tamanho, titulo, etc)//

	public Container() {
		
		// menu superior que aparece na janela do jogo //
		JMenuBar barraSuperior = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		
		JMenuItem sobre = new JMenuItem("Sobre");
		sobre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null,
						"Desenvolvido por Ian Vítor, para a matéria de Tópicos Avançados em TI", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menu.add(sobre);
		menu.add(new JSeparator()); //linha que separa as opções do menu
		menu.add(sair);
		barraSuperior.add(menu);
		setJMenuBar(barraSuperior); //setando a barra criada na janela (container)
		// ---fim menu---//

		add(new Estagio()); // chamada dos objetos da classe estagio para a janela//
 
		setTitle("Corona Jogo");
		setSize(LARGURA_TELA, ALTURA_TELA);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		// dispara a aplicação//
		new Container();
	}

}
