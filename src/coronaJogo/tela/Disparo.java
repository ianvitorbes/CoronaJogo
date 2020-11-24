package coronaJogo.tela;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Disparo {
	private Image img; //png do tiro
	private int x,y; //posição do tiro
	private int largura, altura; //largura e altura do tiro
	private boolean visivel; //visibilidade do tiro, qnd o tiro atingir o inimigo, ou o limite da tela, ele sumir
	
	private static final int LARGURA = 950; //largura maxima onde o tiro vai sumir
	private static int VELO = 2; //velocidade do disparo do tiro 
	
	//CONSTRUTOR//
	public Disparo(int x, int y) {
		//coordenadas x e y são as mesmas do jogador,
		this.x = x;
		this.y = y;
		visivel = true;
	}
	//carregando a imagem do disparo
	public void carregar() {
		ImageIcon ref = new ImageIcon("recursos\\disparoNormal.png"); 
		img = ref.getImage();
		
		//set da altura e largura do disparo
		this.largura = img.getWidth(null);
		this.altura = img.getHeight(null);
	}
	//configurando a largura limite do tiro, caso ele ultrapasse a largura, ele ira desaparecer
	public void update () {
		this.x  += VELO; //velocidade positiva pois desloca da esquerda pra direita
		//teste para verificar se o missel ultrapassou a tela
			if(this.x > LARGURA) {
				visivel = false;
			}	
	}
	
	//retangulo de colisão do disparo
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}
	
	//gets e setters//	
	public boolean isVisivel() {
		return visivel;
	}
	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}
	public static int getVELO() {
		return VELO;
	}
	public static void setVELO(int vELO) {
		VELO = vELO;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Image getImg() {
		return img;
	}
	
	
		
}
