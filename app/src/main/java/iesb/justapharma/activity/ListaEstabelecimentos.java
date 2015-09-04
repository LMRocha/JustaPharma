package iesb.justapharma.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import iesb.justapharma.R;
import iesb.justapharma.domain.Estabelecimento;
import iesb.justapharma.domain.Medicamento;

public class ListaEstabelecimentos extends ActionBarActivity {

    List<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
    ListView listVEstabelecimento;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_estabelecimentos);

        listVEstabelecimento = (ListView) findViewById(R.id.listVEstabelecimento);

        Estabelecimento estabelecimento1 = new Estabelecimento();
        estabelecimento1.setNomeFantasia("FARMA 1");
        Estabelecimento estabelecimento2 = new Estabelecimento();
        estabelecimento2.setNomeFantasia("FARMA 2");
        Estabelecimento estabelecimento3 = new Estabelecimento();
        estabelecimento3.setNomeFantasia("FARMA 3");

        estabelecimentos.add(estabelecimento1);
        estabelecimentos.add(estabelecimento2);
        estabelecimentos.add(estabelecimento3);

        montarListaEstabelecimentos();
    }

    public void montarListaEstabelecimentos(){
        adapter = new ListaEstabelecimentoAdapter();
        listVEstabelecimento.setAdapter(adapter);
    }

    public void editarEstabelecimento(View v){
        Toast.makeText(getApplicationContext(), "Long click is ok !!!", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_estabelecimentos, menu);
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


    private class ListaEstabelecimentoAdapter extends ArrayAdapter<Estabelecimento> {
        public ListaEstabelecimentoAdapter(){
            super (ListaEstabelecimentos.this, R.layout.layout_lista_estabelecimentos, estabelecimentos);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){

            if(view == null)
                view = getLayoutInflater().inflate(R.layout.layout_lista_estabelecimentos, parent, false);

            Estabelecimento currentEstabelecimento = estabelecimentos.get(position);

            TextView nomeEstabelecimento = (TextView) view.findViewById(R.id.txtNomeEstabelecimento);

            nomeEstabelecimento.setText(currentEstabelecimento.getNomeFantasia());

            return view;
        }

    }


}
