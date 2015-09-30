package iesb.justapharma.service;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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
    //private Medicamento medicamento = new Medicamento();

    public Medicamento consultarMedicamentoPorCodBarras(String codBarras) throws ParseException {

        consultarMedicamentoDAO.consultarMedicamentoPorCodBarras(codBarras);

        return null;
    }

    public List<Medicamento> consultarMedicamentoPorFiltro(FiltroMedicamento filtro){

        List<Medicamento> medicamentos = new ArrayList<Medicamento>();

        return medicamentos;
    }

}
