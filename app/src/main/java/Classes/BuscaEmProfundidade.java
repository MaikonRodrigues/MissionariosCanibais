package Classes;


import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BuscaEmProfundidade {

    String textoResposta;
    List<Estado> estadoList;
    private Estado estadoAtual;
    private int valorBusca;
    Stack<Estado> pilhaEstados;
    Context context;

    public BuscaEmProfundidade(int valorBusca, List<Estado> estList, Context context) {
        this.valorBusca = valorBusca;
        this.pilhaEstados = new Stack<>();
        this.estadoList = estList;
        this.context = context;
    }

    public boolean isResultado(Estado estado){
        return estado.getpLista() == valorBusca;
    }

    protected void obterResultadoPaternal(Estado estado) {

        String retorno = "";
        Estado noValor = estado;
        retorno += noValor.getId();
        while (noValor.getpLista() != 0) {
            noValor = estadoList.get(noValor.getEstPai().getpLista());
            retorno = noValor.getId() + " - " + retorno;
        }
        this.textoResposta = retorno;
    }

    public void exibirTextoResultado() {
        if (this.textoResposta != null) {
            //imprime o resultado do caminho
            System.out.println("O caminho percorrido será: " + this.textoResposta);
        } else {
            //caso nao for encontradoo melhorcaminho
            System.out.println("O valor " + this.valorBusca + " não foi encontrado.");
        }
    }

    public Stack<Estado> getPilhaEstados() {
        return pilhaEstados;
    }

    public void setPilhaEstados(Stack<Estado> pilhaEstados) {
        this.pilhaEstados = pilhaEstados;
    }

    public boolean busca(Estado estado){
        if (isResultado(estado)){
            //  Exibir camiho
            this.pilhaEstados.push(estado);
            return true;
        }else {
            //  Expandir os Próximos nos
            int tamanho = estado.getArestas().size();
            if (tamanho == 0)
                return false;
            else {
                int i = 0;
                while (!busca(estadoList.get(estado.getArestas().get(i).getpLista()))) {
                    i++;
                    if (i >= tamanho && pilhaEstados.isEmpty())
                        return false;
                }
                this.pilhaEstados.push(estado);
                return true;
            }
        }
    }
}
