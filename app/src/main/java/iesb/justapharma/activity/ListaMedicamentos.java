package iesb.justapharma.activity;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import iesb.justapharma.R;
import iesb.justapharma.domain.Medicamento;
import iesb.justapharma.domain.SuspensaoMedicamentoEnum;

public class ListaMedicamentos extends ActionBarActivity {

    ListAdapter adapter;
    ListView lstMedicamentos;
    List<Medicamento> medicamentos = new ArrayList<Medicamento>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicamentos);

        lstMedicamentos = (ListView) findViewById(R.id.lstMedicamentos);
        //Apenas para teste
        Medicamento med1 = new Medicamento();
        med1.setNomeMedicamento("Med 1");
        med1.setPrincipioAtivo("Ativo 1");
        Medicamento med2 = new Medicamento();
        med2.setNomeMedicamento("Med 2");
        med2.setPrincipioAtivo("Ativo 2");
        Medicamento med3 = new Medicamento();
        med3.setNomeMedicamento("Med 3");
        med3.setPrincipioAtivo("Ativo 3");
        medicamentos.add(med1);
        medicamentos.add(med2);
        medicamentos.add(med3);

        montarListaMedicamento();
    }
    public void montarListaMedicamento(){
        adapter = new ListaMedicamentoAdapter();
        lstMedicamentos.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_medicamentos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private class ListaMedicamentoAdapter extends ArrayAdapter<Medicamento>{
        public ListaMedicamentoAdapter(){
            super (ListaMedicamentos.this, R.layout.lista_medicamentos_layout, medicamentos);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){

            if(view == null)
                view = getLayoutInflater().inflate(R.layout.lista_medicamentos_layout, parent, false);

            Medicamento currentMedicamento = medicamentos.get(position);

            TextView nomeMedicamento = (TextView) view.findViewById(R.id.txtNomeMed);
            TextView principioAtivo = (TextView) view.findViewById(R.id.txtPrincipioAtivo);

            nomeMedicamento.setText(currentMedicamento.getNomeMedicamento());
            principioAtivo.setText(currentMedicamento.getPrincipioAtivo());

            return view;
        }

    }


}
