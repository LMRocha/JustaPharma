package iesb.justapharma.domain;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by SAMSUNG on 11/08/2015.
 */
@ParseClassName("medicamentos")
public class Medicamento{
    private double preco;
    private String codigoBarras;
    private String principioAtivo;
    private String nomeMedicamento;
    private String classeTerapeutica;
    private PrecoMargemEnum precoMargem;

    public Medicamento(){

    }

    public Medicamento(double preco,
                       String principioAtivo,
                       String codigoBarras,
                       String nomeMedicamento,
                       String classeTerapeutica,
                       PrecoMargemEnum precoMargem) {
        this.preco = preco;
        this.principioAtivo = principioAtivo;
        this.codigoBarras = codigoBarras;
        this.nomeMedicamento = nomeMedicamento;
        this.classeTerapeutica = classeTerapeutica;
        this.precoMargem = precoMargem;
    }

    public Medicamento(double preco,
                       String principioAtivo,
                       String codigoBarras,
                       String nomeMedicamento,
                       String classeTerapeutica) {
        this.preco = preco;
        this.principioAtivo = principioAtivo;
        this.codigoBarras = codigoBarras;
        this.nomeMedicamento = nomeMedicamento;
        this.classeTerapeutica = classeTerapeutica;
        this.precoMargem = precoMargem;
    }



    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }


    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public void setPrincipioAtivo(String principioAtivo) {
        this.principioAtivo = principioAtivo;
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }

    public String getClasseTerapeutica() {
        return classeTerapeutica;
    }

    public void setClasseTerapeutica(String classeTerapeutica) {
        this.classeTerapeutica = classeTerapeutica;
    }

    public PrecoMargemEnum getPrecoMargem() {
        return precoMargem;
    }

    public void setPrecoMargem(PrecoMargemEnum precoMargem) {
        this.precoMargem = precoMargem;
    }
}
