package dominio;

import java.util.Date;

public class Produto extends EntidadeDominio {

    private String codigo;
    private String descricao;

    public Produto(){}
    public Produto(Integer id){
        super(id);
    }
    public Produto(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
    public Produto(Integer id, String codigo, String descricao, Date dtCadastro) {
        super(id, dtCadastro);
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return String.format(
            "PRODUTO - Código: %10s, Descrição: %30s",
            this.getCodigo(),
            this.getDescricao()
        );
    }
}
