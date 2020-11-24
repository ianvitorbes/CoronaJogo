package coronaJogo.tela;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Luzes {
	private Image img;
	private int x, y;
	private int largura, altura;
	private boolean visivel;

	// private static final int LARGURA = 1000;
	private static int VELO = 1;

	// CONSTRUTOR//
	public Luzes(int x, int y) {
		
		this.x = x;
		this.y = y;
		visivel = true;
	}

	// carregando as luzes
	public void carregar() {
		ImageIcon ref = new ImageIcon("recursos\\luzes.png");
		img = ref.getImage();

		// set da altura e largura da luzes
		this.largura = img.getWidth(null);
		this.altura = img.getHeight(null);
	}

	// configurando as luzes que aparecem no fundo do cenario
	// ao tocar na coordenada 0, ela vai voltar pra largura
	public void update() {
		if(this.x <0) { 
			this.x = largura;
			Random a = new Random(); //variavel randomica 
			int p = a.nextInt(500); 
			this.x = p + 1024;		//largura
			
			Random q = new Random(); //variavel randomica
			int k = q.nextInt(768);  //altura
			this.y = k;
		}else {
			this.x -= VELO;
		}
	}

	// gets e setters//
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
