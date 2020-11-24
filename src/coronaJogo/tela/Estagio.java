package coronaJogo.tela;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Estagio extends JPanel implements ActionListener {

	private Image background;
	private Jogador jogador;
	private Timer timer;
	private List<Vilao1> vilao1; //lista dos inimigos 
	private List<Luzes> luzes; 	 //lista das luzes
	private boolean ativo; //variavel que verifica se o jogo terminou ou esta rolando

	public Estagio() {
		setFocusable(true);
		setDoubleBuffered(true);

		// setando as imagens que estão compondo o jogo//
		ImageIcon ref = new ImageIcon("recursos\\ceu.png");
		background = ref.getImage();

		// carregando a imagem do jogador (o aviao)
		jogador = new Jogador();
		jogador.carregar();

		// instancia do teclado//
		addKeyListener(new TecladoAdapter());
		
		timer = new Timer(9, this);
		timer.start();

		trazerVirus(); //metodo q ta inicializando os inimigos
		trazerLuzes();//metodo q ta inicializando as luzes
		ativo = true; //definindo que o jogo ta rodando 
	}

	// metodo que inicializa os vilões/virus
	public void trazerVirus() {
		
		int coord[] = new int[5]; 			//vetor que indica o numero de inimigos que aparecerão
		vilao1 = new ArrayList<Vilao1>();	//arraylist dos inimigos

		// estrutura que gera inimigos aleatoriamente
		for (int i = 0; i < coord.length; i++) {
			int x = (int) (Math.random() * 6000 + 1000); //largura aleatoria de onde o inimigo vem
			int y = (int) (Math.random() * 700 + 30);	 //altura aleatoria de onde o inimigo vem
			vilao1.add(new Vilao1(x, y));
		}
	}
	//metodo de trazer as luzes na tela
	public void trazerLuzes() {
		
		int coord[] = new int[50];
		luzes = new ArrayList<Luzes>(); //lista de estrelas
		for (int i = 0; i < coord.length; i++) {
			int x = (int) (Math.random() * 1000 + 0);
			int y = (int) (Math.random() * 700 + 30);
			luzes.add(new Luzes(x, y));
		}
	}
	

	// pintando as imagens na tela//
	public void paint(Graphics g) {
		Graphics2D graf = (Graphics2D) g;
		//verifica se o jogo ta rolando, se sim, pinta as naves, inimigos e afins
		if (ativo == true) {

			graf.drawImage(background, 0, 0, null); 
			
			//carreando a lista de luzes na tela
			for (int p = 0; p < luzes.size(); p++) {
				Luzes q = luzes.get(p);
				q.carregar();
				graf.drawImage(q.getImg(), q.getX(), q.getY(), this);
			}
			
			graf.drawImage(jogador.getImg(), jogador.getX(), jogador.getY(), this);


			//testa a quantidade de misseis que tem na lista, e pinta na tela
			List<Disparo> disparos = jogador.getDisparos();
			//repete todos os elementos da lista e mostra na tela
			for (int i = 0; i < disparos.size(); i++) {
				Disparo m = disparos.get(i);
				m.carregar();
				graf.drawImage(m.getImg(), m.getX(), m.getY(), this); //pintura dos disparos na tela
			}
			
			// carregando a lista de viloes na tela 
			for (int k = 0; k < vilao1.size(); k++) {
				Vilao1 in = vilao1.get(k);
				in.carregar();
				graf.drawImage(in.getImg(), in.getX(), in.getY(), this); //pintura dos inimigos na tela
			}

		}
		
				// tela de game over
		else { //caso não esteja em jogo, aparece a tela de gameover
			
			ImageIcon jaEra = new ImageIcon("recursos\\gover.png");
			graf.drawImage(jaEra.getImage(), 0, 0, null);
					
		}
		
		//contador que exibe a quantidade de inimigos na tela
		graf.setColor(Color.white);
		graf.drawString("INIMIGOS: "+ vilao1.size(), 5, 15);
		graf.drawString("DETONE OS VIRUS DA COVID-19", 406, 16);

		g.dispose();
	}

	// metodo que atualiza a tela qnd o aviao se mover [timer]
	@Override
	public void actionPerformed(ActionEvent e) {
		jogador.update();
		
		//determina o fim do jogo
		if(vilao1.size() == 0 ) { //verifica se a lista de inimigos foi derrotada, caso seja, o jogo acaba 
			ativo =  false;
		}
		
		//trazendo a lista das luzes para o estagio
		for (int g = 0; g < luzes.size(); g++) {
			Luzes on = luzes.get(g);  
			if(on.isVisivel()) {	//verifica se as luzes são visiveis
				on.update();		//caso seja, é pintada	
			}else {
				luzes.remove(g); //caso não, é removida
			}		
		}

		// pegando a lista de tiros, e trazendo para a fase
		List<Disparo> disparos = jogador.getDisparos();
		for (int i = 0; i < disparos.size(); i++) {
			Disparo m = disparos.get(i); 
			if (m.isVisivel()) { //verifica se o disparo eh visivel na tela
				m.update();		//caso seja ele é pintado
			} else {
				disparos.remove(i); //caso ele não seja, ele é removido
			}
		}

		// estrutura que verifica se o vilao eh visivel na tela, caso não seja ele sera
		// removido
		for (int k = 0; k < vilao1.size(); k++) {
			Vilao1 in = vilao1.get(k);
			if (in.isVisivel()) { //verifica se o vilão é visivel na tela
				in.update();	 //caso seja, ele é pintado
			} else {
				vilao1.remove(k); //caso não seja, ele é removido
			}
		}
		
		testedeColisoes();
		repaint();
	}

	// testando a colisão com os inimigos/virus
	public void testedeColisoes() {
		Rectangle formAviao = jogador.getBounds(); //instanciei o getBounds porque o retangulo da nave é unico
		Rectangle formVilao1;
		Rectangle formDisparo;

		// colisao do jogador com o vilao
		for (int i = 0; i < vilao1.size(); i++) {
			Vilao1 tempVilao1 = vilao1.get(i);
			formVilao1 = tempVilao1.getBounds();
			if (formAviao.intersects(formVilao1)) { //teste de colisão 
				jogador.setVisivel(false); 			//caso colida o jogador morre
				tempVilao1.setVisivel(false); 		//o inimigo tbm morre pq o jogador perdeu
				ativo = false; 						//fim do jogo
			}			
		}
		
		//desenho do retangulo de colisão para o disparo
		List<Disparo> disparos = jogador.getDisparos();
		for (int m = 0; m < disparos.size(); m++) {
			Disparo tempDisparo = disparos.get(m);
			formDisparo = tempDisparo.getBounds();
			
			
			for (int n = 0; n < vilao1.size(); n++) { //2o for
				Vilao1 tempVilao1 = vilao1.get(n);
				formVilao1 = tempVilao1.getBounds();
				if(formDisparo.intersects(formVilao1)) { //se o retângulo do missel intereceptou o retângulo do inimigo, eles colidiram
					tempVilao1.setVisivel(false);		//se sim, inimigo morre
					tempDisparo.setVisivel(false);		// e o disparo some, pois o disparo só pode atingir um ÚNICO inimigo
				}
			}
			
		}
	}

	private class TecladoAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			
			//metodo que recomeça o jogo//
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				ativo = true;
				jogador = new Jogador();
				jogador.carregar();
				trazerVirus();
			}
			//fim metodo que recomeça o jogo//
			jogador.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			jogador.keyRelease(e);
		}
	}

}
