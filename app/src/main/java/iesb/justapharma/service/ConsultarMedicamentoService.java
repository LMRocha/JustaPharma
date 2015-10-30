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

    private ConsultarMedicamentoDAO consultarMedicamentoDAO;
    private String filePath = "C:/Users/SAMSUNG/AndroidStudioProjects/JustaPharma/app/src/main/java/iesb/justapharmam/service/xls_conformidade_2015_07_20_refactored1.csv";
    public Medicamento consultarMedicamentoPorCodBarras(String codBarras, Context context) throws ParseException {
        consultarMedicamentoDAO = new ConsultarMedicamentoDAO(context);
        consultarMedicamentoDAO.importCSVfile(filePath);
        return consultarMedicamentoDAO.consultarMedicamentoPorCodBarras(codBarras, context) ;
    }

    public List<Medicamento> consultarMedicamentoPorFiltro(FiltroMedicamento filtro){

        List<Medicamento> medicamentos = new ArrayList<Medicamento>();

        return medicamentos;
    }

}
