package coronaJogo.tela;

public class Carro extends Automovel {
	
	private String nome;
	
	public void Automovel(String nome) {
		this.nome =  nome;
		
	}
	
	public void acelerar() {
		System.out.println(nome+"Acelerando");
	}

}
