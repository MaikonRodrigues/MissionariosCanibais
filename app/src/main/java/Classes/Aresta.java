package Classes;

public class Aresta {
    int nMissionarios, nCanibais;
    String pEstado;
    int pLista;

    public Aresta() {
    }

    public Aresta(int nCanibais, int nMissionarios, String pEstado, int pLista ) {
        this.nCanibais = nCanibais;
        this.nMissionarios = nMissionarios;
        this.pEstado = pEstado;
        this.pLista = pLista;
    }

    public int getnMissionarios() {
        return nMissionarios;
    }

    public void setnMissionarios(int nMissionarios) {
        this.nMissionarios = nMissionarios;
    }

    public int getnCanibais() {
        return nCanibais;
    }

    public void setnCanibais(int nCanibais) {
        this.nCanibais = nCanibais;
    }

    public String getpEstado() {
        return pEstado;
    }

    public int getpLista() {
        return pLista;
    }
}
