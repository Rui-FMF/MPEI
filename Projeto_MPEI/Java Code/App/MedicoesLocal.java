package Projeto;

public class MedicoesLocal {
	private String local;
	private String data;
	private int[] temperaturas;
	
	public MedicoesLocal(String local, String data, int[] temperaturas) {
		super();
		this.local = local;
		this.data = data;
		this.temperaturas = temperaturas;
	}

	public String getLocal() {
		return local;
	}

	public String getData() {
		return data;
	}

	public int[] getTemperaturas() {
		return temperaturas;
	}
	
}
