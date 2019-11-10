package com.example.missionarioscanibais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
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

import Classes.Aresta;
import Classes.Estado;

public class MainActivity extends AppCompatActivity {

    List<Estado> estadosList;
    List<Aresta> arestasList;
    Aresta aresta1, aresta2, aresta3;
    Estado estado;
    int i;
    Button btnLevar;
    char ladoAtual;

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

        Toast.makeText(this,"Esquerda(C: "+quantCanibEsquerda+" M:"+quantMissEsquerda+")" +
                " barco (C: "+quantCAnibBarco+"M:"+quantMissBarco+") " +
                "direira(C: "+quantCanibDireita+"M:"+quantMissDireita, Toast.LENGTH_LONG).show();
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

        btnLevar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (levar_1c1m == 1){
                    try {
                        rodar(1, 1, ladoAtual);
                        if (ladoAtual == 'd') ladoAtual = 'e'; else ladoAtual = 'd';
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else if (levar_1c == 1){
                    try {
                        rodar(1, 0, ladoAtual);
                        if (ladoAtual == 'd') ladoAtual = 'e'; else ladoAtual = 'd';
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else if (levar_1m == 1){
                    try {
                        rodar(0, 1, ladoAtual);
                        if (ladoAtual == 'd') ladoAtual = 'e'; else ladoAtual = 'd';
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else if (levar_1c == 1){
                    try {
                        rodar(1, 0, ladoAtual);
                        if (ladoAtual == 'd') ladoAtual = 'e'; else ladoAtual = 'd';
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (levar_2c == 1){
                    try {
                        rodar(2, 0, ladoAtual);
                        if (ladoAtual == 'd') ladoAtual = 'e'; else ladoAtual = 'd';
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else if (levar_2m == 1){
                    try {
                        rodar(0, 2, ladoAtual);
                        if (ladoAtual == 'd') ladoAtual = 'e'; else ladoAtual = 'd';
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        for (i = 0; i < 15; i ++){

            if (i == 0){
                aresta1 = new Aresta(1,0, "1_0d");
                aresta2 = new Aresta(1,1, "1_1d");
                aresta3 = new Aresta(2,0, "2_0d");
                arestasList.add(aresta1);
                arestasList.add(aresta2);
                arestasList.add(aresta3);

                estado = new Estado("3_3e",3,3,'e', arestasList);
            }else if (i == 1){

                aresta1 = new Aresta(1,0, "3_3e");
                arestasList.add(aresta1);

                estado = new Estado("1_0d",1,0,'d', arestasList);
            }else if (i == 2){

                aresta1 = new Aresta(1,1, "3_3e");
                aresta2 = new Aresta(0,1, "2_3e");
                arestasList.add(aresta1);

                estado = new Estado("1_1d",1,1,'d', arestasList);
            }else if (i == 3){

                aresta1 = new Aresta(1,0);
                arestasList.add(aresta1);

                estado = new Estado(2,0,'d', arestasList);
            }else if (i == 4){

                aresta1 = new Aresta(2,0);
                arestasList.add(aresta1);

                estado = new Estado(2,3,'e', arestasList);
            }else if (i == 5){

                aresta1 = new Aresta(1,0);
                arestasList.add(aresta1);

                estado = new Estado(3,0,'d', arestasList);
            }else if (i == 6){

                aresta1 = new Aresta(0,2);
                arestasList.add(aresta1);

                estado = new Estado(1,3,'e', arestasList);
            }else if (i == 7){

                aresta1 = new Aresta(1,1);
                arestasList.add(aresta1);

                estado = new Estado(2,2,'d', arestasList);
            }else if (i == 8){

                aresta1 = new Aresta(0,2);
                arestasList.add(aresta1);

                estado = new Estado(2,2,'e', arestasList);
            }else if (i == 9){

                aresta1 = new Aresta(1,0);
                arestasList.add(aresta1);

                estado = new Estado(2,2,'d', arestasList);
            }else if (i == 10){

                aresta1 = new Aresta(2,0);
                arestasList.add(aresta1);

                estado = new Estado(3,0,'e', arestasList);
            }else if (i == 11){

                aresta1 = new Aresta(1,0);
                aresta2 = new Aresta(0,1);
                arestasList.add(aresta1);
                arestasList.add(aresta2);

                estado = new Estado(2,3,'d', arestasList);
            }else if (i == 12){

                aresta1 = new Aresta(2,0);
                arestasList.add(aresta1);

                estado = new Estado(2,0,'e', arestasList);
            }else if (i == 13){

                aresta1 = new Aresta(1,1);
                arestasList.add(aresta1);

                estado = new Estado(1,1,'e', arestasList);
            }else if (i == 13){

               estado = new Estado(3,3,'d', arestasList);
            }
        }


    }

    public void rodar(int quantCanibais, int quantMiss, char origem) throws InterruptedException {
        if (origem == 'd'){
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
}
