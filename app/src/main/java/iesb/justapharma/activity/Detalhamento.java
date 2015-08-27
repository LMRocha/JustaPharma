package iesb.justapharma.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import iesb.justapharma.R;
import iesb.justapharma.domain.PrecoMargemEnum;


public class Detalhamento extends ActionBarActivity {

    String PRINCIPIO_ATIVO;
    String NOME_MEDICAMENTO;
    String STATUS_SUSPENSAO;
    ImageView imgDentroMargem;
    TextView txtPrincipioAtivo;
    TextView txtNomeMedicamento;
    TextView txtStatus;
    TextView txtCodBarras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhamento);

        txtPrincipioAtivo = (TextView) findViewById(R.id.txtPrincipioAtivo);
        txtNomeMedicamento = (TextView) findViewById(R.id.txtNomeMed);
        txtCodBarras = (TextView) findViewById(R.id.txtCodBarras);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        imgDentroMargem = (ImageView) findViewById(R.id.imgDentroMargem);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        txtCodBarras.setText(extras.getString("COD_BARRAS"));


        // Implementa��o do icone para o campo Pre�o dentro da Margem
        if(extras.get("PRECO_MARGEM").equals(PrecoMargemEnum.FORA_MEDIA)){
            imgDentroMargem.setImageResource(R.drawable.img_refuse);
        }else{
            imgDentroMargem.setImageResource(R.drawable.img_accept);
        }
    }

    public void cadastrarEstabelecimento(View view){

        Intent intent = new Intent(this,CadastrarEstabelecimento.class);
        startActivity(intent);

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
