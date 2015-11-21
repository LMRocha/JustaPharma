package iesb.justapharma.activity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    Button btLocalizar;

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
        btLocalizar = (Button) findViewById(R.id.btGetEndereco);

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
            }else if(extras.getString("ID").equals("Lista_Estabelecimento")){
                nomeFantasia.setText(extras.getString("NOME_ESTABELECIMENTO"));
                cnpj.setText(extras.getString("CNPJ"));
                endereco.setText(extras.getString("ENDERECO"));
                txtUsrNome.setText(extras.getString("NOME_DENUNCIANTE"));
                CPF.setText(extras.getString("CPF"));
                txtUsrEndereco.setText(extras.getString("ENDERECO_DENUNCIANTE"));

                nomeFantasia.setEnabled(false);
                cnpj.setEnabled(false);
                endereco.setEnabled(false);
                txtUsrNome.setEnabled(false);
                CPF.setEnabled(false);
                txtUsrEndereco.setEnabled(false);
                btLocalizar.setEnabled(false);
            }
        }

    }

    public void salvarEstabelecimento(View v){
        if(nomeFantasia.getText().toString().isEmpty()){
            nomeFantasia.setError("Campo de preenchimento obrigatório");
        }else if(cnpj.getText().toString().isEmpty()){
            cnpj.setError("Campo de preenchimento obrigatório");
        }else if (endereco.getText().toString().isEmpty()){
            endereco.setError("Campo de preenchimento obrigatório");
        }else if(txtUsrNome.getText().toString().isEmpty()){
            txtUsrNome.setError("Campo de preenchimento obrigatório");
        }else if(CPF.getText().toString().isEmpty()){
            CPF.setError("Campo de preenchimento obrigatório");
        }else if(txtUsrEndereco.getText().toString().isEmpty()){
            txtUsrEndereco.setError("Campo de preenchimento obrigatório");
        }else {

            estabelecimento = new Estabelecimento();
            cadastrarEstabelecimentoService = new CadastrarEstabelecimentoService();

            String descricao = "O medicamento "
                    + medicamento.getProduto()
                    + " está " + medicamento.getValorExcedente()
                    + " R$ acima do permitido, segundo a regra do PMC (Preço máximo do consumidor)";


            estabelecimento.setNomeFantasia(nomeFantasia.getText().toString());
            estabelecimento.setCNPJ(cnpj.getText().toString());
            estabelecimento.setEndereco(endereco.getText().toString());
            estabelecimento.setNome(txtUsrNome.getText().toString());
            estabelecimento.setCPF(CPF.getText().toString());
            estabelecimento.setEnderecoDenunciante(txtUsrEndereco.getText().toString());
            estabelecimento.setDescricao(descricao);

            try {
                cadastrarEstabelecimentoService.enviarDenuncia(estabelecimento, medicamento);
                enviarEmailAuditoria(estabelecimento);
                Toast.makeText(getApplicationContext(), "Denúncia efetuada..", Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Ocorreu um erro ao enviar a denúncia", Toast.LENGTH_LONG).show();
            }
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

    private void enviarEmailAuditoria(Estabelecimento estabelecimento){
        String mail = "lauro.mrocha@gmail.com";
        String subject = "Denuncia";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {mail});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, "Estabelecimento: " + estabelecimento.getNomeFantasia() + "\n "
                + "CNPJ: " + estabelecimento.getCNPJ()
                + " \n"
                + "Endereço: " + estabelecimento.getEndereco()
                + "\n"
                + "\n"
                + estabelecimento.getDescricao()
                + "\n"
                + "\n"
                + "\n"
                + "Dados do denunciante: "
                + " \n"
                + "Nome: " + estabelecimento.getNome()
                + "\n"
                + "CPF: " + estabelecimento.getCPF()
                + "\n"
                + "Endereço: " + estabelecimento.getEnderecoDenunciante()
                + "\n"
                + "\n"
                + "--------------------//-------------------------");
        startActivity(Intent.createChooser(intent, "Send Mail..."));
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
