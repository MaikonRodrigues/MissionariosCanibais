package Classes;

import java.util.List;

public class Estado {
    int nCanibais, nMissionarios ;
    char pCanoa; Estado estPai;
    String id;
    List<Aresta> arestas;
    int pLista;     // Posicao na lista

    public Estado() {
    }

    public Estado(String id, int nCanibais,int nMissionarios , char pCanoa, List<Aresta> arestas, int pLista, Estado estPai) {
        this.id = id;
        this.nCanibais = nCanibais;
        this.nMissionarios = nMissionarios;
        this.pCanoa = pCanoa;
        this.arestas = arestas;
        this.pLista = pLista;
        this.estPai = estPai;
    }

    public Estado getEstPai() {
        return estPai;
    }

    public void setEstPai(Estado estPai) {
        this.estPai = estPai;
    }

    public int getpLista() {
        return pLista;
    }

    public void setpLista(int pLista) {
        this.pLista = pLista;
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

