package iesb.justapharma.domain;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by SAMSUNG on 11/08/2015.
 */
@ParseClassName("medicamentos")
public class Medicamento extends ParseObject{
    private String codigoBarras;
    private double preco;
    private String principioAtivo;
    private String nomeMedicamento;
    private PrecoMargemEnum precoMargem;
    private SuspensaoMedicamentoEnum suspensao;

    public String getCodigoBarras() {
        return getString("EAN");
    }

    public void setCodigoBarras(String codigoBarras) {
        put("EAN",codigoBarras);
    }

    public double getPreco() {
        return getDouble("PMC_19");
    }

    public void setPreco(double preco) {
        put("PMC_19",preco);
    }

    public String getPrincipioAtivo() {
        return getString("PRINCIPIO_ATIVO");
    }

    public void setPrincipioAtivo(String principioAtivo) {
        put("PRINCIPIO_ATIVO",principioAtivo);
    }

    public String getNomeMedicamento() {
        return getString("PRODUTO");
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        put("PRODUTO",nomeMedicamento);
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
