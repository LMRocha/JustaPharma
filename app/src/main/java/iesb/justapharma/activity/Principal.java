package iesb.justapharma.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.PushService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import iesb.justapharma.R;
import iesb.justapharma.domain.Medicamento;
import iesb.justapharma.domain.PrecoMargemEnum;
import iesb.justapharma.service.ConsultarMedicamentoService;
import iesb.justapharma.utils.IntentIntegrator;
import iesb.justapharma.utils.IntentResult;


public class Principal extends Activity {

   ConsultarMedicamentoService consultarMedicamentoService = new ConsultarMedicamentoService();

    EditText numCodBarras;
    EditText numPrecoAtual;
    ImageButton btConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        numCodBarras = (EditText) findViewById(R.id.numCodBarras);
        numPrecoAtual = (EditText) findViewById(R.id.numPrecoAtual);
        btConsultar = (ImageButton) findViewById(R.id.btConsultar);

        //consultarMedicamentoService.openDataBase(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);

        return true;
    }

    /* Funcionalidade será implementada no futuro

    public void consultarCompleta(View view){

        Intent intent = new Intent(this, ConsultaCompletaActivity.class);
        startActivity(intent);

    }*/

    public void consultarCodigoBarras(String codBarra, Double preco) throws ParseException {
        Medicamento medicamento = consultarMedicamentoService.consultarMedicamentoPorCodBarras(codBarra, preco);


        Intent intent = new Intent(this, Detalhamento.class);
        intent.putExtra("COD_BARRAS",medicamento.getEan());
        intent.putExtra("PRECO_MARGEM",medicamento.getPrecoMargem());
        intent.putExtra("PRINCIPIO_ATIVO",medicamento.getPrincipioAtivo());
        intent.putExtra("PRODUTO",medicamento.getProduto());
        intent.putExtra("EXCEDENTE",medicamento.getValorExcedente());
        intent.putExtra("ID","principal");
        startActivity(intent);
    }

    public void escanearCodigoBarra(View v){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

     public void onActivityResult(int requestCode, int resultCode, Intent intent){
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        String barcode = "7896422516754";
        //numCodBarras.setText(barcode);

         /*try {
             consultarCodigoBarras(barcode,Double.parseDouble(numPrecoAtual.getText().toString()));
         } catch (ParseException e) {
             e.printStackTrace();
         }*/

         if(scanResult != null){
           // String barcode;
            //barcode = scanResult.getContents();
            //numCodBarras.setText(barcode);
            try {
                consultarCodigoBarras(barcode,Double.parseDouble(numPrecoAtual.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
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
