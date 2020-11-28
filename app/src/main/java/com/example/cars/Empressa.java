package com.example.cars;

import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Empressa {

    private String  nome_empressa;
    private String  endereco;
    private String  mail;
    private String  contato;
    private String  avalicao;
    private List<String> listar;


    public Empressa(){
        this.listar = new ArrayList<>();
    }


    public void addlista(String listar1){
        this.listar.add(listar1);
    }


    public List<String> getListar() {
        return listar;
    }

    public void setListar(List<String> lista) {
        this.listar = lista;
    }

    public String getNome_empressa() {
        return nome_empressa;
    }

    public void setNome_empressa(String nome_empressa) {
        this.nome_empressa = nome_empressa;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getAvalicao() {
        return avalicao;
    }

    public void setAvalicao(String avalicao) {
        this.avalicao = avalicao;
    }

    @Override
    public String toString(){return null;}
    public String Consultarempressa(){
        return "\n __________________________________________________________\n"+
                "\n Nome da empressa: "+ nome_empressa +
                "\n Endereço:" + endereco +
                "\n E-mail: " + mail +
                "\n Contato: " + contato +
                "\n Avalição: " + avalicao + "\n===============================================\n===============================================" +
                "\n"
                ;
    }
    public String toStringlista(){
        return ""+listar;
    }

    public String erro(){
        return "Desculpe não foi possivél realizar sua requisição";
    }


}
