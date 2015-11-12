package iesb.justapharma.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;

import iesb.justapharma.R;
import iesb.justapharma.domain.Estabelecimento;
import iesb.justapharma.domain.Medicamento;
import iesb.justapharma.domain.PrecoMargemEnum;
import iesb.justapharma.service.ConsultarMedicamentoService;


public class Detalhamento extends ActionBarActivity {

    ConsultarMedicamentoService consultarMedicamentoService = new ConsultarMedicamentoService();
    Medicamento medicamento;
    int i = 0;

    String PRINCIPIO_ATIVO;
    String NOME_MEDICAMENTO;
    String STATUS_SUSPENSAO;
    ImageView imgDentroMargem;
    TextView txtPrincipioAtivo;
    TextView txtNomeMedicamento;
    TextView txtStatus;
    TextView txtCodBarras;
    TextView txtMensagemPreco;
    ImageButton btCadastrarEstabelecimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhamento);

        txtPrincipioAtivo = (TextView) findViewById(R.id.txtPrincipioAtivo);
        txtNomeMedicamento = (TextView) findViewById(R.id.txtNomeMed);
        txtCodBarras = (TextView) findViewById(R.id.txtCodBarras);
        txtMensagemPreco = (TextView) findViewById(R.id.txtMensagemPreco);
        imgDentroMargem = (ImageView) findViewById(R.id.imgDentroMargem);
        btCadastrarEstabelecimento = (ImageButton) findViewById(R.id.bt_cadastrarEstabelecimento);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        txtCodBarras.setText(extras.getString("COD_BARRAS"));
        txtPrincipioAtivo.setText(extras.getString("PRINCIPIO_ATIVO"));
        txtNomeMedicamento.setText(extras.getString("PRODUTO"));

        // Implementa��o do icone para o campo Pre�o dentro da Margem
        if(!Boolean.parseBoolean(extras.get("PRECO_MARGEM").toString())){
            imgDentroMargem.setImageResource(R.drawable.img_refuse);
            txtMensagemPreco.setText("Você está pagando mais caro, o valor está " + extras.getDouble("EXCEDENTE") +
                    " R$ acima do permitido pela legislação!!");

            medicamento = new Medicamento();
            medicamento.setProduto(extras.getString("PRODUTO"));
            medicamento.setPrincipioAtivo(extras.getString("PRINCIPIO_ATIVO"));
            medicamento.setValorExcedente(extras.getDouble("EXCEDENTE"));

        }else{
            imgDentroMargem.setImageResource(R.drawable.img_accept);
            btCadastrarEstabelecimento.setVisibility(View.INVISIBLE);
            txtMensagemPreco.setText("Tudo certo, o preço está dentro do estipulado!!");
        }
    }

    public void enviarDenuncia(View view){
        Estabelecimento estabelecimento = new Estabelecimento();
        //estabelecimento.setNome(txtNomeUsuario);
        Intent intent = new Intent(this,CadastrarEstabelecimento.class);
        intent.putExtra("PRODUTO",medicamento.getProduto());
        intent.putExtra("EXCEDENTE", medicamento.getValorExcedente());
        intent.putExtra("PRINCIPIO_ATIVO",medicamento.getPrincipioAtivo());
        intent.putExtra("ID","detalhamento");
        startActivity(intent);
    }

    public void rastrearEndereco(View v){
        Intent intent = new Intent(this,MapsActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalhamento, menu);
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
