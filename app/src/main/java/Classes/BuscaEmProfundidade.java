package Classes;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BuscaEmProfundidade {

    List<Estado> estadoList;
    private Estado estadoAtual;
    private int valorBusca;
    Stack<Estado> pilhaEstados;

    public BuscaEmProfundidade(int valorBusca, List<Estado> estList) {
        this.valorBusca = valorBusca;
        this.pilhaEstados = new Stack<>();
        this.estadoList = estList;
    }

    public boolean isResultado(Estado estado){
        return estado.getpLista() == valorBusca;
    }

    public void busca(Estado estado){
        this.pilhaEstados.add(estado);
        if (isResultado(estado)){
            //  Exibir camiho
        }else {
            //  Expandir os Próximos nos
            for (int i = 0; i < estado.getArestas().size(); i++){
                busca(estadoList.get(estado.getArestas().get(i).getpLista()));
            }
        }
        this.pilhaEstados.pop();
    }
}
