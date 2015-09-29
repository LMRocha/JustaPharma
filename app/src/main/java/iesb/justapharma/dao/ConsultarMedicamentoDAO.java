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

    public ParseObject consultarMedicamentoPorCodBarras(String codBarras) throws ParseException {
        final ParseObject result = new ParseObject("medicamentos");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("medicamentos");
        query.selectKeys(Arrays.asList("EAN", "PRINCIPIO_ATIVO", "PRODUTO", "PMC_19"));
        query.whereEqualTo("EAN", codBarras);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {

                if (parseObject == null) {

                    Log.d("EAN", "Retrieving failed");
                    Log.d("PRINCIPIO_ATIVO", "Retrieving failed");
                    Log.d("PRODUTO", "Retrieving failed");
                    Log.d("PMC_19", "Retrieving failed");

                } else {

                    Log.d("EAN", "Retrieved");
                    Log.d("PRINCIPIO_ATIVO", "Retrieved");
                    Log.d("PRODUTO", "Retrieved");
                    Log.d("PMC_19", "Retrieved");

                }
            }
        });

        return (ParseObject) query.getFirst();
    }


    public List<Medicamento> consultarMedicamentoPorFiltro(FiltroMedicamento filtro){

        List<Medicamento> medicamentos = new ArrayList<Medicamento>();

        return medicamentos;
    }

}
