package iesb.justapharma.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import iesb.justapharma.domain.Estabelecimento;
import iesb.justapharma.domain.Medicamento;

/**
 * Created by SAMSUNG on 11/08/2015.
 */
public class CadastrarrEstabelecimentoDAO {

 public void salvarDenuncia(Estabelecimento estabelecimento){

  estabelecimento.saveInBackground();

 }
}
