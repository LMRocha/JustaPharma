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
public class CadastrarEstabelecimentoService extends Activity {

    CadastrarrEstabelecimentoDAO cadastrarrEstabelecimentoDAO = new CadastrarrEstabelecimentoDAO();

    public void enviarDenuncia(Estabelecimento estabelecimento, Medicamento medicamento){

        String descricao = "O medicamento "
                +medicamento.getProduto()
                +" está "+medicamento.getValorExcedente()
                +" R$ acima do permitido, segundo a regra do PMC (Preço máximo do consumidor)";

        estabelecimento.setNomeMedicamento(medicamento.getProduto());
        estabelecimento.setDescricao(descricao);
        enviarEmailAuditoria(estabelecimento);

        //cadastrarrEstabelecimentoDAO.salvarDenuncia(estabelecimento);
    }

    private void enviarEmailAuditoria(Estabelecimento estabelecimento){

        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","lauro.mrocha@gmail.com",null));
        //intent.setData(Uri.parse("mailto:"));
        //String[] to = {"lauro.mrocha@gmail.com","lauro.mrocha@gmail.com"};
        //intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Denúncia");
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
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Send Email..."));
    }
}
