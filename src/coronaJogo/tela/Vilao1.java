package coronaJogo.tela;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Vilao1 {
	private Image img; //png do vil�o
	private int x,y;  //posi��o do vil�o
	private int largura, altura; //largura e altura do vil�o
	private boolean visivel;  //visibilidade do vil�o
	private static int VELO = 4; //velocidade de movimenta��o
	public static final int LARGURA_TELA = 1000;
	
	private static int contador = 0; //contador do numero de vil�es na tela
	
	//CONSTRUTOR//
	public Vilao1(int x, int y) {

		this.x = x;
		this.y = y;
		
		//contador de inimigos na tela
		if(contador++ %3 == 0) {
			ImageIcon ref = new ImageIcon("recursos\\vilao1.png");
		}
		
		visivel = true;
	}
	
		//carregando o vilao1 (virus)
	public void carregar() {
		//carregando a imagem do vil�o
		ImageIcon ref = new ImageIcon("recursos\\vilao1.png"); 
		img = ref.getImage();
		//sett de altura e largura do vil�o 
		this.largura = img.getWidth(null);
		this.altura = img.getHeight(null);
	}
	
	//movimenta��o do inimigo
	public void update () {			//"looping infinito"	
		if(this.x<0) { 				//movimento do vilao ate ele nao ser interceptado, 
			this.x = LARGURA_TELA;	//caso ele n�o seja, ele volta pro estado inicial
		}else {
			this.x -=VELO;          //velocidade negativa pois o inimigo desloca da direita pra esquerda
		}		
	}
	
	//retangulo de colis�o do vil�o
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura); //desenho do retangulo com base nas especifica��es do vil�o
	}
	
	//gets e setters
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
