package iesb.justapharma.dao;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import iesb.justapharma.activity.FiltroMedicamento;
import iesb.justapharma.domain.Medicamento;

/**
 * Created by SAMSUNG on 11/08/2015.
 */
public class ConsultarMedicamentoDAO {

    private Medicamento result;

    public Medicamento consultarMedicamentoPorCodBarras(String codBarras) throws ParseException {

        ParseObject.registerSubclass(Medicamento.class);
        result = new Medicamento();

        ParseQuery<Medicamento> query = ParseQuery.getQuery("medicamentos");
        query.selectKeys(Arrays.asList("EAN", "PRINCIPIO_ATIVO", "PRODUTO", "PMC_19"));
        query.whereEqualTo("EAN", codBarras);
        query.getFirstInBackground(new GetCallback<Medicamento>() {
            @Override
            public void done(Medicamento parseObject, ParseException e) {

                if (parseObject == null) {


                } else {

                    result = parseObject;

                }
            }
        });

        return result;

    }


    public List<Medicamento> consultarMedicamentoPorFiltro(FiltroMedicamento filtro){

        List<Medicamento> medicamentos = new ArrayList<Medicamento>();

        return medicamentos;
    }

}
