package iesb.justapharma.domain;

import android.widget.Adapter;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by SAMSUNG on 11/08/2015.
 */
@ParseClassName("denuncia")
public class Estabelecimento extends ParseObject {

    public Estabelecimento() {
        super("denuncia");
    }

/*
    private String nomeFantasia;
    private String CNPJ;
    private String endereco;
    private String nome;
    private String CPF;
    private String enderecoDenunciante;

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getEnderecoDenunciante() {
        return enderecoDenunciante;
    }

    public void setEnderecoDenunciante(String enderecoDenunciante) {
        enderecoDenunciante = enderecoDenunciante;
    }
*/

    public String getNomeFantasia() {
        return getString("nome_fantasia");
    }

    public void setNomeFantasia(String nomeFantasia) {
        put("nome_fantasia", nomeFantasia);
    }

    public String getCNPJ() {
        return getString("CNPJ");
    }

    public void setCNPJ(String CNPJ) {
        put("CNPJ", CNPJ);
    }

    public String getEndereco() {
        return getString("endereco");
    }

    public void setEndereco(String endereco) {
        put("endereco", endereco);
    }

    public String getNomeMedicamento() {
        return getString("nome_medicamento");
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        put("nome_medicamento", nomeMedicamento);
    }
    public Date getDataDenuncia(){
       return getDate("createdAt");
    }

    public void setDataDenuncia(Date dataDenuncia){
        put("createdAt",dataDenuncia);
    }

    public String getDescricao() {
        return getString("descricao");
    }

    public void setDescricao(String descricao) {
        put("descricao", descricao);
    }

    public String getNome() {

        return getString("nome");
    }

    public void setNome(String nome) {

        put("nome", nome);
    }

    public String getCPF() {
        return getString("CPF");
    }

    public void setCPF(String CPF) {
        put("CPF", CPF);
    }

    public String getEnderecoDenunciante() {
        return getString("endereco_denunciante");
    }

    public void setEnderecoDenunciante(String enderecoDenunciante) {
        put("endereco_denunciante", enderecoDenunciante);
    }


}
