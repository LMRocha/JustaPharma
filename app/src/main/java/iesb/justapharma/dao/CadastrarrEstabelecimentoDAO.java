package iesb.justapharma.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import iesb.justapharma.domain.Estabelecimento;
import iesb.justapharma.domain.Medicamento;

/**
 * Created by SAMSUNG on 11/08/2015.
 */
public class CadastrarrEstabelecimentoDAO {

 public void salvarDenuncia(Estabelecimento estabelecimento){

  estabelecimento.saveInBackground();

 }

 public List<Estabelecimento> listarEstabelecimentos() {
  List<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
  ParseQuery<Estabelecimento> query = ParseQuery.getQuery(Estabelecimento.class);
  try {
   estabelecimentos = query.find();
  } catch (ParseException e) {
   e.printStackTrace();
  }

 return estabelecimentos;
 }
}
