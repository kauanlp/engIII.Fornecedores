package dominio;

import java.util.Date;

public class Telefone extends EntidadeDominio {
    
    private String tipoTelefone;
    private String ddi;
    private String ddd;
    private String numero;

    public Telefone(){}
    public Telefone(Integer id){
        super(id);
    }
    public Telefone(String tipoTelefone, String ddi, String ddd, String numero) {
        this.tipoTelefone = tipoTelefone;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
    }
    public Telefone(Integer id, String tipoTelefone, String ddi, String ddd, String numero, Date dtCadastro) {
        super(id, dtCadastro);
        this.tipoTelefone = tipoTelefone;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
    }
    

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return String.format(
            "TELEFONE - Tipo: %10s, DDI: %2s, DDD: %2s, Número: %12s",
            this.getTipoTelefone(),
            this.getDdi(),
            this.getDdd(),
            this.getNumero()
        );
    }
}
