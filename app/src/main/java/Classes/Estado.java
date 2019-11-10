package Classes;

import java.util.List;

public class Estado {
    int nCanibais, nMissionarios ;
    char pCanoa;
    String id;
    List<Aresta> arestas;

    public Estado() {
    }

    public Estado(String id, int nCanibais,int nMissionarios , char pCanoa, List<Aresta> arestas) {
        this.id = id;
        this.nCanibais = nCanibais;
        this.nMissionarios = nMissionarios;
        this.pCanoa = pCanoa;
        this.arestas = arestas;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
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

    public char getpCanoa() {
        return pCanoa;
    }

    public char setpCanoa(char pCanoa) {
        this.pCanoa = pCanoa;
        return pCanoa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

