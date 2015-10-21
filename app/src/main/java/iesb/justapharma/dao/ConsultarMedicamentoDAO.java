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

    //private Medicamento result;
    private List<Medicamento> results = new ArrayList<Medicamento>();
    public List<Medicamento> consultarMedicamentoPorCodBarras(String codBarras) throws ParseException {

        ParseQuery<Medicamento> query = new ParseQuery<Medicamento>("medicamentos");
        query.selectKeys(Arrays.asList("EAN", "PRINCIPIO_ATIVO", "PRODUTO", "PMC_19"));
        query.whereEqualTo("EAN", codBarras);
        query.findInBackground(new FindCallback<Medicamento>() {
            @Override
            public void done(List<Medicamento> list, ParseException e) {
                if(list.size() != 0 && list != null){

                    for (Medicamento med: list) {
                        Medicamento medicamento = new Medicamento();
                        medicamento.setCodigoBarras(med.getCodigoBarras());
                        medicamento.setPreco(med.getPreco());
                        medicamento.setNomeMedicamento(med.getNomeMedicamento());
                        medicamento.setPrincipioAtivo(med.getPrincipioAtivo());
                        results.add(medicamento);
                    }

                }else{
                    System.out.println("LISTA VAZIA!!");
                }
            }
        });

        return results;

    }


    public List<Medicamento> consultarMedicamentoPorFiltro(FiltroMedicamento filtro){

        List<Medicamento> medicamentos = new ArrayList<Medicamento>();

        return medicamentos;
    }

}
