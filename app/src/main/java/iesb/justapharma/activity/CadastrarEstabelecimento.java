package iesb.justapharma.activity;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import iesb.justapharma.R;

public class CadastrarEstabelecimento extends ActionBarActivity {

    TextView nomeFantasia;
    TextView cnpj;
    TextView endereco;
    ImageView imgDentroMargem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_estabelecimento);

        nomeFantasia = (TextView) findViewById(R.id.txtNomeFantasia);
        cnpj = (TextView) findViewById(R.id.txtCnpj);
        endereco = (TextView) findViewById(R.id.txtEndereco);
        imgDentroMargem = (ImageView) findViewById(R.id.imgDentroMargem);

        Intent intent = getIntent();
        if(intent != null && (intent.getExtras() != null)){

            Bundle extras = intent.getExtras();

            if((extras.getString("INTENT_MAPS").equals("FROM_MAPS_ACTIVITY"))){

                this.endereco.setText(extras.getString("ENDERECO_RASTREADO"));
            }
        }

    }

    public void salvarEstabelecimento(View v){
        Toast.makeText(getApplicationContext(), "Estabelecimento salvo com sucesso", Toast.LENGTH_LONG).show();
    }

    public void listarEstabelecimentos(View v){
        Intent intent = new Intent(this,ListaEstabelecimentos.class);
        startActivity(intent);

    }

    public void rastrearEndereco(View v){
        Intent intent = new Intent(this,MapsActivity.class);
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
