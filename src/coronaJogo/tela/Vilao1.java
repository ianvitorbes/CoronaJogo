package coronaJogo.tela;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Vilao1 {
	private Image img; //png do vilão
	private int x,y;  //posição do vilão
	private int largura, altura; //largura e altura do vilão
	private boolean visivel;  //visibilidade do vilão
	private static int VELO = 4; //velocidade de movimentação
	public static final int LARGURA_TELA = 1000;
	
	private static int contador = 0; //contador do numero de vilões na tela
	
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
		//carregando a imagem do vilão
		ImageIcon ref = new ImageIcon("recursos\\vilao1.png"); 
		img = ref.getImage();
		//sett de altura e largura do vilão 
		this.largura = img.getWidth(null);
		this.altura = img.getHeight(null);
	}
	
	//movimentação do inimigo
	public void update () {			//"looping infinito"	
		if(this.x<0) { 				//movimento do vilao ate ele nao ser interceptado, 
			this.x = LARGURA_TELA;	//caso ele não seja, ele volta pro estado inicial
		}else {
			this.x -=VELO;          //velocidade negativa pois o inimigo desloca da direita pra esquerda
		}		
	}
	
	//retangulo de colisão do vilão
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura); //desenho do retangulo com base nas especificações do vilão
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
