package iesb.justapharma.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.parse.ParseException;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import iesb.justapharma.R;
import iesb.justapharma.domain.Medicamento;

/**
 * Created by SAMSUNG on 11/08/2015.
 */
public class ConsultarMedicamentoDAO extends SQLiteOpenHelper {
    Context context;
    private static final int DATABASE_VERSION = 2;
    CSVRecord record;
    private static final String DATABASE_NAME = "JUSTAPHARMA",
            TABLE_MEDICAMENTOS = "medicamentos",
            KEY_ID = "id",
            KEY_PRINCIPIO_ATIVO = "principio_ativo",
            KEY_LABORATORIO = "laboratorio",
            KEY_EAN = "ean",
            KEY_PRODUTO = "produto",
            KEY_APRESENTACAO ="apresentacao",
            KEY_CLASSE_TERAPEUTICA = "classe_terapeutica",
            KEY_PF_0 = "pf_0",
            KEY_PF_12 = "pf_0",
            KEY_PF_17 = "pf_0",
            KEY_PF_18 = "pf_0",
            KEY_PF_19 = "pf_0",
            KEY_PF_17_ZONA = "pf_17_zona",
            KEY_PMC_0 = "pmc_0",
            KEY_PMC_12 = "pmc_12",
            KEY_PMC_17 = "pmc_17",
            KEY_PMC_18 = "pmc_18",
            KEY_PMC_19 = "pmc_19",
            KEY_PMC_17_ZONA = "pmc_17_zona";


    public ConsultarMedicamentoDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE "
                + TABLE_MEDICAMENTOS
                + "("
                + KEY_PRINCIPIO_ATIVO
                + " TEXT,"
                + KEY_LABORATORIO
                + " TEXT,"
                + KEY_EAN
                + " TEXT,"
                + KEY_APRESENTACAO
                + " TEXT,"
                + KEY_CLASSE_TERAPEUTICA
                + " TEXT,"
                + KEY_PMC_19
                + " REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_MEDICAMENTOS);
        onCreate(db);
    }


    public Medicamento consultarMedicamentoPorCodBarras(String codBarras, Context context){

        SQLiteDatabase db = this.getReadableDatabase();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            String t = this.getDatabaseName();
        }
        Medicamento medicamento = new Medicamento();
        //onCreate(db);

        Cursor cursor = db.query(TABLE_MEDICAMENTOS, new String[]{
                KEY_PRINCIPIO_ATIVO,
                KEY_EAN,
                KEY_APRESENTACAO,
                KEY_CLASSE_TERAPEUTICA,
                KEY_PMC_19 }, KEY_EAN+"=?", new String[]{codBarras},null,null,null,null);


        if(cursor != null){
            medicamento.setPrincipioAtivo(cursor.getString(0));
            medicamento.setCodigoBarras(cursor.getString(1));
            medicamento.setNomeMedicamento(cursor.getString(2));
            medicamento.setClasseTerapeutica(cursor.getString(3));
            medicamento.setPreco(Double.parseDouble(cursor.getString(4)));
        }

        return medicamento;
    }


    public void importCSVfile(String fileaName){
        String[] FILE_HEADER_MAPPING = {"principioAtivo","ean","apresentacao","classe_terapeutica","pmc19"};

        String PRINCIPIO_ATIVO = "principioAtivo";
        String EAN = "ean";
        String APRESENTACAO = "apresentacao";
        String CLASSE_TERAPEUTICA = "classe_terapeutica";
        String PMC19 = "pmc19";

        BufferedReader fileReader;
        CSVParser csvParser;
        InputStream iStream;
        //CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(',');
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);


        try {
            List<Medicamento> medicamentos = new ArrayList<Medicamento>();
            List<CSVRecord> records = new ArrayList<CSVRecord>();
            iStream = context.getResources().openRawResource(R.raw.xls_conformidade_2015_07_20_refactored1);
            fileReader = new BufferedReader(new InputStreamReader(iStream));
            csvParser = new CSVParser(fileReader, csvFormat);
            records.addAll(csvParser.getRecords());



            for (CSVRecord record: records) {
                this.record = record;
                Medicamento medicamento = new Medicamento(
                        Double.parseDouble(this.record.get(PMC19)),
                        String.valueOf(this.record.get(PRINCIPIO_ATIVO)),
                        String.valueOf(this.record.get(EAN)),
                        String.valueOf(this.record.get(APRESENTACAO)),
                        String.valueOf(this.record.get(CLASSE_TERAPEUTICA)));

                        medicamentos.add(medicamento);
            }


        }catch (Exception e){

        }

    }

}
