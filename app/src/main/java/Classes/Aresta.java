package Classes;

public class Aresta {
    int nMissionarios, nCanibais;
    String pEstado;

    public Aresta() {
    }

    public Aresta(int nCanibais, int nMissionarios, String pEstado ) {
        this.nCanibais = nCanibais;
        this.nMissionarios = nMissionarios;
        this.pEstado = pEstado;
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
}
