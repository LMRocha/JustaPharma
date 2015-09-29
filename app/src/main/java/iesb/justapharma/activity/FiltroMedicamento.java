package iesb.justapharma.activity;

/**
 * Created by SAMSUNG on 26/09/2015.
 */
public class FiltroMedicamento {
    private String principioAtivo;
    private String nomeDoMedicamento;
    private String laboratorio;
    private String classeTerapeutica;

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public void setPrincipioAtivo(String principioAtivo) {
        this.principioAtivo = principioAtivo;
    }

    public String getNomeDoMedicamento() {
        return nomeDoMedicamento;
    }

    public void setNomeDoMedicamento(String nomeDoMedicamento) {
        this.nomeDoMedicamento = nomeDoMedicamento;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getClasseTerapeutica() {
        return classeTerapeutica;
    }

    public void setClasseTerapeutica(String classeTerapeutica) {
        this.classeTerapeutica = classeTerapeutica;
    }
}
