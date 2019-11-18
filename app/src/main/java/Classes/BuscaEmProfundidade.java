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
    //exibe o Resultado
    public void exibirTextoResultado() {
        if (this.textoResposta != null) {
            //imprime o resultado do caminho
            System.out.println("O caminho percorrido será: " + this.textoResposta);
        } else {
            //caso nao for encontradoo melhorcaminho
            System.out.println("O valor " + this.valorBusca + " não foi encontrado.");
        }
    }


    public void busca(Estado estado){
        this.pilhaEstados.push(estado);
        if (isResultado(estado)){
            //  Exibir camiho
            for (int i = 0; i < pilhaEstados.size(); i++){
                Toast.makeText(context, "sequencia de estados: "+pilhaEstados.get(i).getId(), Toast.LENGTH_LONG).show();
            }
        }else {
            //  Expandir os Próximos nos
            for (int i = 0; i < estado.getArestas().size(); i++){
                if (estadoList.get(estado.getArestas().get(i).getpLista()).getArestas().isEmpty()){
                    this.pilhaEstados.pop();
                    return;
                }else{
                    busca(estadoList.get(estado.getArestas().get(i).getpLista()));
                }

            }
        }
        this.pilhaEstados.pop();
    }
}
