package iesb.justapharma.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
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
import iesb.justapharma.service.CadastrarEstabelecimentoService;

public class ListaEstabelecimentos extends Activity{
    CadastrarEstabelecimentoService cadastrarEstabelecimentoService;
    List<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
    ListView listVEstabelecimento;
    ListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_estabelecimentos);
        cadastrarEstabelecimentoService = new CadastrarEstabelecimentoService();
        listVEstabelecimento = (ListView) findViewById(R.id.listVEstabelecimento);
        registerForContextMenu(listVEstabelecimento);

        final Intent intent = new Intent(this, CadastrarEstabelecimento.class);

        listVEstabelecimento.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String message = "Você selecionou o estabelecimento " +
                        estabelecimentos.get(position).getNomeFantasia();
                String.valueOf((Estabelecimento) parent.getItemAtPosition(position));

                Toast.makeText(ListaEstabelecimentos.this, message, Toast.LENGTH_LONG).show();

                intent.putExtra("ID", "Lista_Estabelecimento");
                intent.putExtra("NOME_ESTABELECIMENTO",estabelecimentos.get(position).getNomeFantasia());
                intent.putExtra("CNPJ",estabelecimentos.get(position).getCNPJ());
                intent.putExtra("ENDERECO",estabelecimentos.get(position).getEndereco());
                intent.putExtra("NOME_DENUNCIANTE",estabelecimentos.get(position).getNome());
                intent.putExtra("CPF",estabelecimentos.get(position).getCPF());
                intent.putExtra("ENDERECO_DENUNCIANTE",estabelecimentos.get(position).getEnderecoDenunciante());
                startActivity(intent);

            }
        });

/*
        */
/*Estabelecimento estabelecimento1 = new Estabelecimento();
        estabelecimento1.setNomeFantasia("FARMA 1");
        Estabelecimento estabelecimento2 = new Estabelecimento();
        estabelecimento2.setNomeFantasia("FARMA 2");
        Estabelecimento estabelecimento3 = new Estabelecimento();
        estabelecimento3.setNomeFantasia("FARMA 3");*//*


        estabelecimentos.add(estabelecimento1);
        estabelecimentos.add(estabelecimento2);
        estabelecimentos.add(estabelecimento3);
*/

        estabelecimentos.addAll(cadastrarEstabelecimentoService.listarEstabelecimentos());
        montarListaEstabelecimentos();
    }

    public void montarListaEstabelecimentos(){
        adapter = new ListaEstabelecimentoAdapter();
        listVEstabelecimento.setAdapter(adapter);
    }

    public void editarEstabelecimento(View v){

        Toast.makeText(getApplicationContext(), "Edit item is ok !!!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actions_lista_estabelecimento,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){

            case R.id.itemDelete:
                Toast.makeText(ListaEstabelecimentos.this,"Deletar Item",Toast.LENGTH_SHORT).show();
            case R.id.itemEditar:
                Toast.makeText(ListaEstabelecimentos.this,"Editar Item",Toast.LENGTH_SHORT).show();
        }


        return super.onContextItemSelected(item);


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
            TextView dataDenuncia = (TextView) view.findViewById(R.id.txtDataDenuncia);
            TextView nomeMedicamento = (TextView) view.findViewById(R.id.txtMedicamento);

            nomeEstabelecimento.setText(currentEstabelecimento.getNomeFantasia());
            dataDenuncia.setText(currentEstabelecimento.getCreatedAt().toString());
            nomeMedicamento.setText(currentEstabelecimento.getNomeMedicamento());

            return view;
        }

    }


}
