package iesb.justapharma.domain;

/**
 * Created by SAMSUNG on 11/08/2015.
 */
public class Medicamento {
    private int codigoBarras;
    private double preco;
    private String principioAtivo;
    private String nomeMedicamento;
    private PrecoMargemEnum precoMargem;
    private SuspensaoMedicamentoEnum suspensao;

    public int getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(int codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
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

    public PrecoMargemEnum getPrecoMargem() {
        return precoMargem;
    }

    public void setPrecoMargem(PrecoMargemEnum precoMargem) {
        this.precoMargem = precoMargem;
    }

    public SuspensaoMedicamentoEnum getSuspensao() {
        return suspensao;
    }

    public void setSuspensao(SuspensaoMedicamentoEnum suspensao) {
        this.suspensao = suspensao;
    }
}
