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
    static int aux = 0;

    List<Medicamento> results = new ArrayList<Medicamento>();

    EditText numCodBarras;
    EditText numPrecoAtual;
    ImageButton btConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        if(aux == 0) {
            ParseObject.registerSubclass(Medicamento.class);
            Parse.initialize(this, getString(R.string.param1), getString(R.string.param2));
        }

        aux++;

        ParseQuery<Medicamento> query = new ParseQuery<Medicamento>("medicamentos");
        query.selectKeys(Arrays.asList("EAN", "PRINCIPIO_ATIVO", "PRODUTO", "PMC_19"));
        query.whereEqualTo("EAN", "7896015516604");
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



        numCodBarras = (EditText) findViewById(R.id.numCodBarras);
        numPrecoAtual = (EditText) findViewById(R.id.numPrecoAtual);
        btConsultar = (ImageButton) findViewById(R.id.btConsultar);
        System.out.println("AUX: "+aux);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);

        return true;
    }

    public void consultarCompleta(View view){

        Intent intent = new Intent(this, ConsultaCompletaActivity.class);
        startActivity(intent);

    }

    public void consultarCodigoBarras(String codBarra) throws ParseException {
        Medicamento medicamento;
        //medicamento.setPreco(100.0);

       medicamento = consultarMedicamentoService.consultarMedicamentoPorCodBarras(codBarra);

        if(Double.parseDouble(numPrecoAtual.getText().toString()) > medicamento.getPreco()){
            medicamento.setPrecoMargem(PrecoMargemEnum.FORA_MEDIA);
        }else{
            medicamento.setPrecoMargem(PrecoMargemEnum.DENTRO_MEDIA);
        }


        Intent intent = new Intent(this, Detalhamento.class);
        intent.putExtra("COD_BARRAS",codBarra);
        intent.putExtra("PRECO_MARGEM",medicamento.getPrecoMargem());
        startActivity(intent);
    }

    public void escanearCodigoBarra(View v){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

     public void onActivityResult(int requestCode, int resultCode, Intent intent){
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if(scanResult != null){
            String barcode;
            barcode = scanResult.getContents();
            numCodBarras.setText(barcode);
            try {
                consultarCodigoBarras(barcode);
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
