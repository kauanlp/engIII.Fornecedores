package dominio;

import java.util.Date;

public abstract class EntidadeDominio{
    
    private Integer id;
    private Date dtCadastro;

    public EntidadeDominio() {
        this.dtCadastro = new Date();
    }
    public EntidadeDominio(Integer id) {
        this.id = id;
    }
    public EntidadeDominio(Integer id, Date dtCadastro) {
        this.id = id;
        this.dtCadastro = dtCadastro;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

}
