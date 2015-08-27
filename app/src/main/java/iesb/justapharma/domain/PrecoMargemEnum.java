package iesb.justapharma.domain;

/**
 * Created by SAMSUNG on 18/08/2015.
 */
public enum PrecoMargemEnum {

    DENTRO_MEDIA(1,"Dentro"),FORA_MEDIA(2,"Fora");

    private final String desc;
    private final int val;

    PrecoMargemEnum(int valor, String descricao){
        this.val = valor;
        this.desc = descricao;
    }

    public String getDesc() {
        return desc;
    }

    public int getVal() {
        return val;
    }
}
