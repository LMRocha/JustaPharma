package iesb.justapharma.service;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import iesb.justapharma.dao.CadastrarrEstabelecimentoDAO;
import iesb.justapharma.domain.Estabelecimento;
import iesb.justapharma.domain.Medicamento;

/**
 * Created by SAMSUNG on 11/08/2015.
 */
public class CadastrarEstabelecimentoService {

    CadastrarrEstabelecimentoDAO cadastrarrEstabelecimentoDAO = new CadastrarrEstabelecimentoDAO();

    public void enviarDenuncia(Estabelecimento estabelecimento, Medicamento medicamento){


        estabelecimento.setNomeMedicamento(medicamento.getProduto())/*String descricao = "O medicamento "
                +medicamento.getProduto()
                +" está "+medicamento.getValorExcedente()
                +" R$ acima do permitido, segundo a regra do PMC (Preço máximo do consumidor)";
*/;
        estabelecimento.setDescricao(estabelecimento.getDescricao());
        //enviarEmailAuditoria(estabelecimento);

        cadastrarrEstabelecimentoDAO.salvarDenuncia(estabelecimento);
    }

/*
    private void enviarEmailAuditoria(Estabelecimento estabelecimento){
        String mail = "lauro.mrocha@gmail.com";
        String subject = "Denuncia";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {mail});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, "Estabelecimento: " + estabelecimento.getNome() + "\n "
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
*/
}
