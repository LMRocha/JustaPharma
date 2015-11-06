package iesb.justapharma.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import iesb.justapharma.R;
import iesb.justapharma.dao.CadastrarrEstabelecimentoDAO;
import iesb.justapharma.domain.Estabelecimento;
import iesb.justapharma.domain.Medicamento;
import iesb.justapharma.service.CadastrarEstabelecimentoService;

public class CadastrarEstabelecimento extends ActionBarActivity {
    Medicamento medicamento;
    Estabelecimento estabelecimento;
    CadastrarEstabelecimentoService cadastrarEstabelecimentoService;
    EditText nomeFantasia;
    EditText cnpj;
    EditText endereco;
    EditText txtUsrNome;
    EditText txtUsrEndereco;
    EditText CPF;
    ImageView imgDentroMargem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_estabelecimento);

        nomeFantasia = (EditText) findViewById(R.id.txtNomeFantasia);
        cnpj = (EditText) findViewById(R.id.txtCnpj);
        endereco = (EditText) findViewById(R.id.txtEndereco);
        imgDentroMargem = (ImageView) findViewById(R.id.imgDentroMargem);
        txtUsrNome = (EditText) findViewById(R.id.txtUsrName);
        txtUsrEndereco = (EditText) findViewById(R.id.txtUsrEndereco);
        CPF = (EditText) findViewById(R.id.txtCpf);

        Intent intent = getIntent();
        if(intent != null && (intent.getExtras() != null)){

            Bundle extras = intent.getExtras();

            if((extras.getString("ID").equals("FROM_MAPS_ACTIVITY"))){
                medicamento = new Medicamento();
                medicamento.setPrincipioAtivo(extras.getString("MED_PRINCIPIO_ATIVO"));
                medicamento.setProduto(extras.getString("MED_PRODUTO"));
                medicamento.setValorExcedente(extras.getDouble("MED_EXCEDENTE"));
                this.endereco.setText(extras.getString("ENDERECO_RASTREADO"));


            }else if(extras.getString("ID").equals("detalhamento")){
                medicamento = new Medicamento();
                medicamento.setProduto(extras.getString("PRODUTO"));
                medicamento.setValorExcedente(extras.getDouble("EXCEDENTE"));
                medicamento.setPrincipioAtivo(extras.getString("PRINCIPIO_ATIVO"));
            }
        }

    }

    public void salvarEstabelecimento(View v){
        estabelecimento = new Estabelecimento();
        cadastrarEstabelecimentoService = new CadastrarEstabelecimentoService();

        estabelecimento.setNomeFantasia(nomeFantasia.getText().toString());
        estabelecimento.setCNPJ(cnpj.getText().toString());
        estabelecimento.setEndereco(endereco.getText().toString());
        estabelecimento.setNome(txtUsrNome.getText().toString());
        estabelecimento.setCPF(CPF.getText().toString());
        estabelecimento.setEnderecoDenunciante(txtUsrEndereco.getText().toString());

        try {
            cadastrarEstabelecimentoService.enviarDenuncia(estabelecimento, medicamento);
            Toast.makeText(getApplicationContext(), "Denúncia efetuada..", Toast.LENGTH_LONG).show();

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Ocorreu um erro ao enviar a denúncia", Toast.LENGTH_LONG).show();
        }

            }

    public void listarEstabelecimentos(View v){
        Intent intent = new Intent(this,ListaEstabelecimentos.class);
        startActivity(intent);

    }

    public void rastrearEndereco(View v){
        Intent intent = new Intent(this,MapsActivity.class);
        intent.putExtra("MED_PRODUTO",medicamento.getProduto() );
        intent.putExtra("MED_EXCEDENTE",medicamento.getValorExcedente());
        intent.putExtra("MED_PRINCIPIO_ATIVO",medicamento.getPrincipioAtivo());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastrar_estabelecimento, menu);
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
}
