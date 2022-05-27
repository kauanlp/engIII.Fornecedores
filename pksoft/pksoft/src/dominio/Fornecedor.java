package dominio;

import java.util.Date;
import java.util.List;

public class Fornecedor extends EntidadeDominio {

    private String cnpj;
    private String rzSocial;
    private String nmFantasia;
    private String inscricaoMunicipal;
    private String inscricaoEstadual;
    private List<Cnae> cnaes;
    private String email;
    private List<Telefone> telefones;
    private List<Contato> contatos;
    private Endereco endereco;
    private String tipoFornecimento;
    private List<Produto> produtosOfertados;
    private List<Servico> servicosOfertados;
    private String status;

    public Fornecedor() {}
    public Fornecedor(Integer id){
        super(id);
    }
    public Fornecedor(String cnpj, String rzSocial, String nmFantasia, String inscricaoMunicipal,
            String inscricaoEstadual, List<Cnae> cnaes, String email, List<Telefone> telefones, List<Contato> contatos,
            Endereco endereco, String tipoFornecimento, List<Produto> produtosOfertados,
            List<Servico> servicosOfertados, String status) {
        this.cnpj = cnpj;
        this.rzSocial = rzSocial;
        this.nmFantasia = nmFantasia;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.inscricaoEstadual = inscricaoEstadual;
        this.cnaes = cnaes;
        this.email = email;
        this.telefones = telefones;
        this.contatos = contatos;
        this.endereco = endereco;
        this.tipoFornecimento = tipoFornecimento;
        this.produtosOfertados = produtosOfertados;
        this.servicosOfertados = servicosOfertados;
        this.status = status;
    }
    
    public Fornecedor(Integer id, String cnpj, String rzSocial, String nmFantasia, String inscricaoMunicipal,
            String inscricaoEstadual, List<Cnae> cnaes, String email, List<Telefone> telefones, List<Contato> contatos,
            Endereco endereco, String tipoFornecimento, List<Produto> produtosOfertados,
            List<Servico> servicosOfertados, String status, Date dtCadastro) {
        super(id, dtCadastro);
        this.cnpj = cnpj;
        this.rzSocial = rzSocial;
        this.nmFantasia = nmFantasia;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.inscricaoEstadual = inscricaoEstadual;
        this.cnaes = cnaes;
        this.email = email;
        this.telefones = telefones;
        this.contatos = contatos;
        this.endereco = endereco;
        this.tipoFornecimento = tipoFornecimento;
        this.produtosOfertados = produtosOfertados;
        this.servicosOfertados = servicosOfertados;
        this.status = status;
    }
    
    public String getNmFantasia() {
        return nmFantasia;
    }

    public void setNmFantasia(String nmFantasia) {
        this.nmFantasia = nmFantasia;
    }

    public String getRzSocial() {
        return rzSocial;
    }

    public void setRzSocial(String rzSocial) {
        this.rzSocial = rzSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Cnae> getCnaes() {
        return cnaes;
    }

    public void setCnaes(List<Cnae> cnaes) {
        this.cnaes = cnaes;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }
    
    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Servico> getServicosOfertados() {
        return servicosOfertados;
    }

    public void setServicosOfertados(List<Servico> servicosOfertados) {
        this.servicosOfertados = servicosOfertados;
    }

    public List<Produto> getProdutosOfertados() {
        return produtosOfertados;
    }

    public void setProdutosOfertados(List<Produto> produtosOfertados) {
        this.produtosOfertados = produtosOfertados;
    }

    public String getTipoFornecimento() {
        return tipoFornecimento;
    }

    public void setTipoFornecimento(String tipoFornecimento) {
        this.tipoFornecimento = tipoFornecimento;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format(
            "FORNECEDOR - CNPJ: %14s, Nome Fantasia: %30s, email: %30s, Status: %10s",
            this.getCnpj(),
            this.getNmFantasia(),
            this.getEmail(),
            this.getStatus()
        );
    }
}
