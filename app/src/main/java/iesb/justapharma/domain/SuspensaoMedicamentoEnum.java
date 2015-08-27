package iesb.justapharma.domain;

/**
 * Created by SAMSUNG on 18/08/2015.
 */
public enum SuspensaoMedicamentoEnum {

    SUSPENSO(1,"Suspenso"),LIBERADO(2,"Liberado");

    private final int val;
    private final String desc;

    SuspensaoMedicamentoEnum(int valor, String descricao){
        this.desc = descricao;
        this.val = valor;
    }

    public int getVal() {
        return val;
    }

    public String getDesc() {
        return desc;
    }
}
