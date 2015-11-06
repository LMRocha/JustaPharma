package iesb.justapharma.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by SAMSUNG on 11/08/2015.
 */
@ParseClassName("medicamentos")
public class Medicamento extends ParseObject implements  Parcelable{
/*
    private double preco;
    private String codigoBarras;
    private String principioAtivo;
    private String nomeMedicamento;
    private String classeTerapeutica;*/
    private Boolean precoMargem;
    private Double valorExcedente;

    public Medicamento(){
        super("medicamentos");
    }

/*
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
*/

    public Boolean getPrecoMargem() {
        return precoMargem;
    }

    public void setPrecoMargem(Boolean precoMargem) {
        this.precoMargem = precoMargem;
    }

    public Double getValorExcedente() {
        return valorExcedente;
    }

    public void setValorExcedente(Double valorExcedente) {
        this.valorExcedente = valorExcedente;
    }


    // Colunas banco de dados Parse

    public String getPrincipioAtivo(){

        return getString("PRINCIPIO_ATIVO");
    }

    public void setPrincipioAtivo(String principioAtivo){

        put("PRINCIPIO_ATIVO",principioAtivo);
    }

    public String getEan(){

        return getString("EAN");
    }

    public void setEan(String ean){

        put("EAN",ean);
    }

    public String getProduto(){

        return getString("PRODUTO");
    }

    public void setProduto(String produto){

        put("PRODUTO",produto);
    }

    public String getClasse(){

        return getString("CLASSE_TERAPEUTICA");
    }

    public void setClasse(String classe){

        put("CLASSE_TERAPEUTICA",classe);
    }

    public Double getPMC(){

        return getDouble("PMC_19");
    }

    public void setPMC(Double pmc){

        put("PMC_19",pmc);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
