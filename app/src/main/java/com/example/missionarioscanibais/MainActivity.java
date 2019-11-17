package com.example.missionarioscanibais;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Classes.Aresta;
import Classes.BuscaEmProfundidade;
import Classes.Estado;

public class MainActivity extends AppCompatActivity {

    List<Estado>  estadosList;
    List<Aresta> arestasList;
    Aresta aresta1, aresta2, aresta3, arestaDeEntrada;
    Estado estado, estadoAtual;
    int i, contadorPassos = 0;
    Button btnLevar;
    char ladoAtual;
    BuscaEmProfundidade buscaEmProfundidade;

    ImageView imgCanoa, imgDireita, imgEsquerda;
    private RadioGroup radioGroup;
    RadioButton check_txt_1m1c, check_txt_1m, check_txt_1c, check_txt_2m, check_txt_2c;
    int flag, check_1m, check_1c, levar_1c1m, levar_1c, levar_1m, levar_2c, levar_2m;
    int quantMissEsquerda=0, quantMissDireita=3, quantCanibEsquerda=0, quantCanibDireita=3
            ,quantMissBarco=0 ,quantCAnibBarco=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        estado = new Estado();

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        estadosList = new ArrayList<>();    flag = 0;   ladoAtual = 'd';
        arestasList = new ArrayList<>();    check_1c = 0; check_1m =0;


        imgCanoa = (ImageView) findViewById(R.id.imgCanoa);
        imgDireita = (ImageView) findViewById(R.id.imgDireita);
        imgEsquerda = (ImageView)findViewById(R.id.imgEsq);
        btnLevar = (Button)findViewById(R.id.btnLevar);

        check_txt_1m1c = (RadioButton)findViewById(R.id.txt_1m1c);
        check_txt_1m   = (RadioButton)findViewById(R.id.txt_1m);
        check_txt_1c   = (RadioButton)findViewById(R.id.txt_1c);
        check_txt_2m   = (RadioButton)findViewById(R.id.txt_2m);
        check_txt_2c   = (RadioButton)findViewById(R.id.txt_2c);

        for (i = 0; i < 15; i ++){

            if (i == 0){    // Estado inicial
                aresta1 = new Aresta(1,0, "1_0d",1);  // Tres arestas possiveis sem retorno pos se trata
                aresta2 = new Aresta(1,1, "1_1d", 2);  // do estado q0
                aresta3 = new Aresta(2,0, "2_0d", 3);
                arestasList.add(aresta1);
                arestasList.add(aresta2);
                arestasList.add(aresta3);


                estado = new Estado("3_3e",3,3,'e', arestasList, 0);

                estadosList.add(estado);

            }else if (i == 1){
                // estado e acessado porem sem saida, logo so tem a aresta de volta para o estado inicial
                aresta1 = new Aresta(1,0, "3_3e", 0);
                arestasList.add(aresta1);

                estado = new Estado("1_0d",1,0,'d', arestasList,1);
                estadosList.add(estado);

            }else if (i == 2){

                aresta1 = new Aresta(1,1, "3_3e", 0);  // aresta de ida para estado anterior
                aresta2 = new Aresta(0,1, "2_3e", 4);  // aresta para o proximo
                arestasList.add(aresta1);
                arestasList.add(aresta2);

                estado = new Estado("1_1d",1,1,'d', arestasList,2);
                estadosList.add(estado);

            }else if (i == 3){

                aresta1 = new Aresta(1,0, "2_3e", 4 ); // aresta de ida
                aresta2 = new Aresta(2,0, "3_3e", 0 ); // aresta de volta
                arestasList.add(aresta1);
                arestasList.add(aresta2);

                estado = new Estado("2_0d",2,0,'d', arestasList,3);
                estadosList.add(estado);

            }else if (i == 4){

                aresta1 = new Aresta(2,0, "3_0d", 5);  // aresta de ida
                aresta2 = new Aresta(0,1, "1_1d", 2);  // aresta de volta
                aresta3 = new Aresta(1,0, "2_0d", 3);  // aresta de volta
                arestasList.add(aresta1);
                arestasList.add(aresta2);
                arestasList.add(aresta3);

                estado = new Estado("2_3e",2,3,'e', arestasList,4);
                estadosList.add(estado);

            }else if (i == 5){

                aresta1 = new Aresta(1,0, "1_3e",6);  //aresta de ida
                aresta2 = new Aresta(2,0, "2_3e", 4);  //aresta de volta
                arestasList.add(aresta1);
                arestasList.add(aresta2);

                estado = new Estado("3_0d",3,0,'d', arestasList,5);
                estadosList.add(estado);

            }else if (i == 6){

                aresta1 = new Aresta(0,2, "2_2d", 7);  //aresta de ida
                aresta2 = new Aresta(1,0, "3_0d",5);  //aresta de volta
                arestasList.add(aresta1);
                arestasList.add(aresta2);

                estado = new Estado("1_3e",1,3,'e', arestasList,6);
                estadosList.add(estado);

            }else if (i == 7){

                aresta1 = new Aresta(1,1, "2_2e", 8);  //aresta de ida
                aresta2 = new Aresta(0,2, "1_3e", 6);  //aresta de ida
                arestasList.add(aresta1);
                arestasList.add(aresta2);

                estado = new Estado("2_2d",2,2,'d', arestasList,7);
                estadosList.add(estado);

            }else if (i == 8){

                aresta1 = new Aresta(0,2, "1_3d", 9);  // aresta de ida
                aresta2 = new Aresta(1,1, "2_2d", 7);  // aresta de volta
                arestasList.add(aresta1);
                arestasList.add(aresta2);

                estado = new Estado("2_2e",2,2,'e', arestasList,8);
                estadosList.add(estado);

            }else if (i == 9){

                aresta1 = new Aresta(1,0, "3_0e", 10);  //aresta de ida
                aresta2 = new Aresta(1,0, "0_2e", 8);  //aresta de volta
                arestasList.add(aresta1);
                arestasList.add(aresta2);

                estado = new Estado("1_3d",2,2,'d', arestasList,9);
                estadosList.add(estado);

            }else if (i == 10){

                aresta1 = new Aresta(2,0, "2_3d",11);  //aresta de ida
                aresta2 = new Aresta(1,0, "1_3d",9);  //aresta de volta
                arestasList.add(aresta1);
                arestasList.add(aresta2);

                estado = new Estado("3_0e",3,0,'e', arestasList,10);
                estadosList.add(estado);

            }else if (i == 11){

                aresta1 = new Aresta(1,0, "2_0e", 12);     //aresta de ida
                aresta2 = new Aresta(0,1, "1_1e", 13);      // aresta de ida
                aresta3 = new Aresta(2,0, "3_3e",10);      // aresta de volta
                arestasList.add(aresta1);
                arestasList.add(aresta2);
                arestasList.add(aresta3);

                estado = new Estado("2_3d",2,3,'d', arestasList,11);
                estadosList.add(estado);

            }else if (i == 12){

                aresta1 = new Aresta(2,0, "3_3d",14);  // aresta de ida pro estado final
                aresta2 = new Aresta(1,0, "2_3d",11);  // aresta de volta
                arestasList.add(aresta1);
                arestasList.add(aresta2);

                estado = new Estado("2_0e",2,0,'e', arestasList,12);
                estadosList.add(estado);

            }else if (i == 13){

                aresta1 = new Aresta(1,1, "3_3d",14);  // aresta de ida para o estado final
                aresta2 = new Aresta(0,1, "2_3d",11);  // aresta de volta
                arestasList.add(aresta1);
                arestasList.add(aresta2);

                estado = new Estado("1_1e",1,1,'e', arestasList,13);
                estadosList.add(estado);

            }else if (i == 14){

                aresta1 = new Aresta(1,1, "1_1e", 13);  // aresta de ida para o estado final
                aresta2 = new Aresta(2,0, "2_0e", 12);  // aresta de volta
                arestasList.add(aresta1);
                arestasList.add(aresta2);

                estado = new Estado("3_3d",3,3,'d', arestasList,14);
                estadosList.add(estado);

            }
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                levar_1c = 0;
                levar_1m = 0;
                levar_1c1m = 0;
                levar_2m = 0;
                levar_2c = 0;
                if(i == R.id.txt_1c) {
                    levar_1c = 1;
                }
                else if(i == R.id.txt_1m) {
                    levar_1m = 1;
                }
                else if(i == R.id.txt_1m1c) {
                    levar_1c1m = 1;
                }
                else if(i == R.id.txt_2m) {
                    levar_2m = 1;
                }
                else if(i == R.id.txt_2c) {
                    levar_2c = 1;
                }
            }
        });

        buscaEmProfundidade = new BuscaEmProfundidade(14,estadosList,MainActivity.this);

        btnLevar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (levar_1c1m == 1){
                    try {
                        rodar(1, 1, ladoAtual, estadosList);
                        //rodar1(1, 1, ladoAtual);
                        if (ladoAtual == 'd') ladoAtual = 'e'; else ladoAtual = 'd';
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else if (levar_1c == 1){
                    try {
                        rodar(1, 0, ladoAtual, estadosList);
                        //rodar1(1, 0, ladoAtual);
                        if (ladoAtual == 'd') ladoAtual = 'e'; else ladoAtual = 'd';
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else if (levar_1m == 1){
                    try {
                        rodar(0, 1, ladoAtual, estadosList);
                       // rodar1(0, 1, ladoAtual);
                        if (ladoAtual == 'd') ladoAtual = 'e'; else ladoAtual = 'd';
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else if (levar_1c == 1){
                    try {
                        rodar(1, 0, ladoAtual, estadosList);
                       // rodar1(1, 0, ladoAtual);
                        if (ladoAtual == 'd') ladoAtual = 'e'; else ladoAtual = 'd';
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (levar_2c == 1){
                    try {
                        rodar(2, 0, ladoAtual, estadosList);
                      //  rodar1(2, 0, ladoAtual);
                        if (ladoAtual == 'd') ladoAtual = 'e'; else ladoAtual = 'd';
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else if (levar_2m == 1){
                    try {
                        rodar(0, 2, ladoAtual, estadosList);
                       // rodar1(0, 2, ladoAtual);
                        if (ladoAtual == 'd') ladoAtual = 'e'; else ladoAtual = 'd';
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buscaEmProfundidade.busca(estadosList.get(0));
            }
        });




    }

    public void rodar(int quantCanibais, int quantMiss, char origem, List<Estado> L_Estados) throws InterruptedException {

        if (origem == 'd'){
            rodar1(quantCanibais, quantMiss, L_Estados);
            quantMissDireita -= quantMiss;
            quantCanibDireita -= quantCanibais;
            setImageDireita();

            quantMissBarco = quantMiss;
            quantCAnibBarco = quantCanibais;

            //setImageBarco();

            // new Thread().sleep(3000);
            movimentoCanoa('e');
            setImageBarco();
            // new Thread().sleep(3000);

            quantCanibEsquerda += quantCanibais;
            quantMissEsquerda += quantMiss;
            setImageEsquerda();
            quantCAnibBarco = 0;
            quantMissBarco = 0;
            setImageBarco();



        }else{
            rodar1(quantCanibais, quantMiss, L_Estados);
            arestaDeEntrada = new Aresta(quantCanibais, quantMiss,"", 0);

            quantCanibEsquerda -= quantCanibais;
            quantMissEsquerda -= quantMiss;
            setImageEsquerda();

            quantMissBarco = quantMiss;
            quantCAnibBarco = quantCanibais;
            setImageBarco();

            //new Thread().sleep(3000);
            movimentoCanoa('d');
            // Toast.makeText(this,"Barco(M: "+quantMissBarco+" C:"+quantCAnibBarco, Toast.LENGTH_LONG).show();
            // setImageBarco();
            new Thread().sleep(3000);
            quantMissDireita += quantMiss;
            quantCanibDireita += quantCanibais;
            setImageDireita();
            quantCAnibBarco = 0;
            quantMissBarco = 0;
            setImageBarco();
        }
        if(quantMissEsquerda == 0 && quantCanibEsquerda == 0){
            imgEsquerda.setVisibility(View.INVISIBLE);
        }
        else{
            imgEsquerda.setVisibility(View.VISIBLE);
        }

    }




    public void rodar1(int quantCanibais, int quantMiss, List<Estado> L_Estados){

       /* int j;
        estadoAtual = new Estado();                             // Crio um novo estado
        estadoAtual.setId(L_Estados.get(0).getId());            // atribuo o valor de entrada para criar a aresta do estado
        estadoAtual.setArestas(L_Estados.get(0).getArestas());
        contadorPassos += 1;    // contando a quantidade de passos

        Aresta arestaProxEstado, arestaAux;

        arestaAux = new Aresta();
        arestaDeEntrada = new Aresta(quantCanibais, quantMiss,"", 0);
        arestaProxEstado = verificaArestaEstadoAtual(arestaDeEntrada, estadoAtual);

        if (arestaProxEstado == null){
            gameOver();
        }else{

            for( i = 0; i < 14 ; i++){
                //Toast.makeText(this, "tamanho :"+L_Estados.size(), Toast.LENGTH_LONG).show();
                for( j = 0; j < L_Estados.get(i).getArestas().size(); j++){
                    arestaAux = L_Estados.get(i).getArestas().get(j);
                    if (arestaAux.getpEstado() == arestaProxEstado.getpEstado()){
                        estadoAtual = L_Estados.get(i);
                        Toast.makeText(this, "Estado Atual :"+estadoAtual.getId(), Toast.LENGTH_LONG).show();
                        return;
                    }else{      // talvez nao precisa
                        Toast.makeText(this, "Nao tem essa aresta no estado :"+estadoAtual.getId(), Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            }
        }*/
       // buscaProfundidade(estadosList.get(0));
    }

    public String buscaProfundidade(Estado estado){
        String estVisitados = "";
        if (estado.getId().equals("3_3d")){
            Toast.makeText(this, "Adicionou: "+estado.getId(), Toast.LENGTH_LONG).show();
            estVisitados = ""+estadoAtual.getId();
            return estVisitados;
        }else {
            int tamanho = estado.getArestas().size();
            if (tamanho == 0)
                return null;
            else{
                int aresta = 0;
                while (estVisitados.isEmpty()){
                    estVisitados = buscaProfundidade(estadosList.get(estado.getArestas().get(aresta).getpLista()));
                    aresta ++;
                    if (aresta >= tamanho && estVisitados.isEmpty())
                        return null;
                }
                estVisitados = estado.getId() + " " + estVisitados;
                Toast.makeText(this, "Adicionou: "+estado.getId(), Toast.LENGTH_LONG).show();
                return estVisitados;
            }
        }
    }
    /*
    public List<Estado> bbuscaProfundidade(Estado estado){
        List<Estado> estVisitados;
        estVisitados = new ArrayList<>();
        if (estado.getId().equals("3_3d")){
            Toast.makeText(this, "Adicionou: "+estado.getId(), Toast.LENGTH_LONG).show();
            estVisitados.add(estadoAtual);
            return estVisitados;
        }else {
            int tamanho = estado.getArestas().size();
            if (tamanho == 0)
                return null;
            else{
                int aresta = 0;
                while (estVisitados.isEmpty()){
                    estVisitados.addAll(buscaProfundidade(estadosList.get(estado.getArestas().get(aresta).getpLista())));
                    aresta ++;
                    if (aresta >= tamanho && estVisitados.isEmpty())
                        return null;
                }
                estVisitados.add(estado);
                Toast.makeText(this, "Adicionou: "+estado.getId(), Toast.LENGTH_LONG).show();
                return estVisitados;
            }
        }
    }*/

    public Aresta verificaArestaEstadoAtual(Aresta arestaDEntrada, Estado estadAtual){

        for (int i = 0; i < estadoAtual.getArestas().size(); i++){
            if (arestaDEntrada.getnCanibais() == estadAtual.getArestas().get(i).getnCanibais()
                    && arestaDEntrada.getnMissionarios() == estadAtual.getArestas().get(i).getnMissionarios() ){
                Aresta arestaParaProxEstado = new Aresta(estadAtual.getArestas().get(i).getnCanibais(),
                        estadAtual.getArestas().get(i).getnMissionarios(),
                        estadAtual.getArestas().get(i).getpEstado(), 0);
                return arestaParaProxEstado;
            }
        }
        return null;
    }

    public void setImageBarco(){
        if (quantCAnibBarco == 1 && quantMissBarco == 1){
            imgCanoa.setImageResource(R.mipmap.cm_c);
        }else if (quantCAnibBarco == 2 && quantMissBarco == 0){
            imgCanoa.setImageResource(R.mipmap.cc_c);
        }else  if(quantCAnibBarco == 0 && quantMissBarco == 2){
            imgCanoa.setImageResource(R.mipmap.mm_c);
        }else if (quantCAnibBarco == 1 && quantMissBarco == 0){
            imgCanoa.setImageResource(R.mipmap.c_c);
        }else if (quantCAnibBarco == 0 && quantMissBarco == 1){
            imgCanoa.setImageResource(R.mipmap.m_c);
        }else{
            imgCanoa.setImageResource(R.drawable.canoa);
        }
    }

    public void setImageDireita(){
        if (quantMissDireita == 3 && quantCanibDireita == 3){
            imgDireita.setImageResource(R.mipmap.cccmmm);
        }else if (quantMissDireita == 3 && quantCanibDireita == 2){
            imgDireita.setImageResource(R.mipmap.ccmmm);
        }else if (quantMissDireita == 3 && quantCanibDireita == 1){
            imgDireita.setImageResource(R.mipmap.cmmm);
        }else if (quantMissDireita == 3 && quantCanibDireita == 0){
            imgDireita.setImageResource(R.mipmap.mmm);
        }else if (quantMissDireita == 2 && quantCanibDireita == 2){
            imgDireita.setImageResource(R.mipmap.ccmm);
        }else if (quantMissDireita == 2 && quantCanibDireita == 1){
            imgDireita.setImageResource(R.drawable.cmm);
        }else if (quantMissDireita == 1 && quantCanibDireita == 1){
        imgDireita.setImageResource(R.drawable.cm);
        }else if (quantMissDireita == 0 && quantCanibDireita == 3){
            imgDireita.setImageResource(R.drawable.ccc);
        }else if (quantMissDireita == 0 && quantCanibDireita == 2){
            imgDireita.setImageResource(R.drawable.cc);
        }else if (quantMissDireita == 0 && quantCanibDireita == 1){
            imgDireita.setImageResource(R.drawable.c);
        }else {
            Toast.makeText(this, "Game Over", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }/*else if (quantMissDireita == 1 && quantCanibDireita == 3){ //GAME
            imgDireita.setImageResource(R.mipmap.cccm);
        }else if (quantMissDireita == 2 && quantCanibDireita == 3){
            imgDireita.setImageResource(R.mipmap.cccmm);
        }*/
    }

    public void setImageEsquerda(){
        if (quantMissEsquerda == 3 && quantCanibEsquerda == 3){
            imgEsquerda.setImageResource(R.mipmap.cccmmm);
        }else if (quantMissEsquerda == 3 && quantCanibEsquerda == 2){
            imgEsquerda.setImageResource(R.mipmap.ccmmm);
        }else if (quantMissEsquerda == 3 && quantCanibEsquerda == 1){
            imgEsquerda.setImageResource(R.mipmap.cmmm);
        }else if (quantMissEsquerda == 3 && quantCanibEsquerda == 0){
            imgEsquerda.setImageResource(R.mipmap.mmm);
        }else if (quantMissEsquerda == 2 && quantCanibEsquerda == 2){
            imgEsquerda.setImageResource(R.mipmap.ccmm);
        }else if (quantMissEsquerda == 2 && quantCanibEsquerda == 1){
            imgEsquerda.setImageResource(R.drawable.cmm);
        }else if (quantMissEsquerda == 1 && quantCanibEsquerda == 1) {
            imgEsquerda.setImageResource(R.drawable.cm);
        }else if (quantMissEsquerda == 0 && quantCanibEsquerda == 1) {
            imgEsquerda.setImageResource(R.drawable.c);
        }else if (quantMissEsquerda == 0 && quantCanibEsquerda == 2) {
            imgEsquerda.setImageResource(R.drawable.cc);
        }else if (quantMissEsquerda == 0 && quantCanibEsquerda == 3) {
            imgEsquerda.setImageResource(R.drawable.ccc);
        }else{

        }
    }

    public void movimentoCanoa(char lado) throws InterruptedException {
        if (lado == 'd'){
            imgCanoa.setX(450);
           // new Thread().sleep(1000);
        }else{
            imgCanoa.setX(130);
           // new Thread().sleep(1000);
        }
    }

    public void gameOver(){
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Game Over");
        //define a mensagem
        builder.setMessage("Você perdeu o jogo Tente novamente !");
        //define um botão como positivo
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //cria o AlertDialog
        AlertDialog alerta = builder.create();
        //Exibe
        alerta.show();
    }
}
