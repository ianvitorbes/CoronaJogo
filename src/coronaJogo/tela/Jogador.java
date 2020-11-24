package coronaJogo.tela;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Jogador {
	
	private int x,y; //atributo da coordenada da nave
	private int dx, dy; //atributo da coordenada de movimenta��o
	private Image img; //atributo da imagem da nave
	private int altura, largura; 
	private List <Disparo> disparos;
	private boolean isVisivel; //visibilidade da nave

	
	//metodo que define a imagem do jogador, no caso o avi�o//
	public void carregar() {
		//chamando a imagem do jogador, que � o avi�o//
		ImageIcon ref = new ImageIcon ("recursos\\aviaozin.png");
		img = ref.getImage();
		
		this.x = 79; //coordenadas da nave, onde ela vai surgir
		this.y = 289; //coordenadas da nave, onde ela vai surgir
		isVisivel= true;
		disparos = new ArrayList<Disparo>(); //instancia de lista dos disparos
			
		
		//setando a altura e largura da imagem//
		altura = img.getHeight(null); 
		largura = img.getWidth(null);		
	}
	
	//metodo que define a movimenta��o do avi�o no eixo x ou no eixo y//
	public void update() {
		System.out.println(x+"," + y);
		x+=dx;
		y+=dy;		
		
		//colis�o do avi�o nas extremidades da tela
		if(this.x<1) {
			x =1;
		}
		
		if(this.x>871) {
			x =871;
		}
		
		if(this.y<1) {
			y =1;
		}
		
		if(this.y>587) {
			y =587;
		}
	}
	
	//metodo que adiciona um novo disparo a lista de disparos
	public void disparoNormal () {
		this.disparos.add(new Disparo(x+largura, y+(altura/2))); //utilizando como base as coordenadas da nave
	}															 //o y divide por 2 pq se n�o o disparo iria sair muito acima da nave
	
	//desenho do retangulo com base nas especifica��es do avi�o
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}
	
	
	//metodo de entrada do teclado//
	public void keyPressed(KeyEvent botao) {
		//ao pressionar uma tecla, uma a��o ser� feita (movimenta��o da nave, e afins)//
		int cod = botao.getKeyCode();
		
		//a��o que ser� feita//
		
		if(cod == KeyEvent.VK_SPACE) {
			disparoNormal();
		}
		
		if(cod == KeyEvent.VK_UP) {
			dy=-3; 
		}
		if(cod == KeyEvent.VK_DOWN) {
			dy= 3; 
		}
		if(cod == KeyEvent.VK_LEFT) {
			dx=-3; 
		}
		if(cod == KeyEvent.VK_RIGHT) {
			dx=3; 
		}
	}
	
	//este metodo serve para identificar quando a tecla parar de ser pressionada
	public void keyRelease(KeyEvent botao) {
		int cod = botao.getKeyCode();
		if(cod == KeyEvent.VK_UP) {
			dy=0; 
		}
		if(cod == KeyEvent.VK_DOWN) {
			dy=-0; 
		}
		if(cod == KeyEvent.VK_LEFT) {
			dx=-0; 
		}
		if(cod == KeyEvent.VK_RIGHT) {
			dx=0; 
		}
	}
	
	//gets e setters//	 
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImg() {
		return img;
	}

	public List<Disparo> getDisparos() {
		return disparos;
	}

	public void setVisivel(boolean b) {
			
	}
}
