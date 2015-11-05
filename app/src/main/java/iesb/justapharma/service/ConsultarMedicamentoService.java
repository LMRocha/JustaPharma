package iesb.justapharma.service;

import android.content.Context;

import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

import iesb.justapharma.activity.FiltroMedicamento;
import iesb.justapharma.dao.ConsultarMedicamentoDAO;
import iesb.justapharma.domain.Medicamento;

/**
 * Created by SAMSUNG on 11/08/2015.
 */
public class ConsultarMedicamentoService {

    private ConsultarMedicamentoDAO consultarMedicamentoDAO = new ConsultarMedicamentoDAO();
    public Medicamento consultarMedicamentoPorCodBarras(String codBarras, Double preco) throws ParseException {

        Medicamento medicamento = consultarMedicamentoDAO.consultarMedicamento(codBarras);
        medicamento.setPrecoMargem(isPrecoAceitavel(medicamento.getPMC(), preco));

        if(!medicamento.getPrecoMargem()){
            medicamento.setValorExcedente(calculaExcedente(medicamento.getPMC(),preco));
        }

        return medicamento ;
    }


    private Boolean isPrecoAceitavel(Double pmc, Double preco) {
        Boolean flag;

        if (preco > pmc) {
            flag = false;
        }else{
            flag = true;
        }

        return flag;
    }


    private Double calculaExcedente(Double pmc, Double preco){

        return preco - pmc;
    }
}

