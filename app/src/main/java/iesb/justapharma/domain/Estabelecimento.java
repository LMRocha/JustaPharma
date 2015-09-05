package iesb.justapharma.domain;

import android.widget.Adapter;

/**
 * Created by SAMSUNG on 11/08/2015.
 */
public class Estabelecimento{
    private String nomeFantasia;
    private String CNPJ;
    private String Endereco;

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

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }
}
