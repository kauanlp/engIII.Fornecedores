package dominio;

import java.util.Date;

public class Contato extends EntidadeDominio {

    private String nome;
    private String departamento;
    private String email;
    private Telefone telefone;
    
    public Contato() {}
    public Contato(Integer id){
        super(id);
    }
    public Contato(String nome, String departamento, String email, Telefone telefone) {
        this.nome = nome;
        this.departamento = departamento;
        this.email = email;
        this.telefone = telefone;
    }
    public Contato(Integer id, String nome, String departamento, String email, Telefone telefone, Date dtCadastro) {
        super(id, dtCadastro);
        this.nome = nome;
        this.departamento = departamento;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return String.format(
            "CONTATO - Nome: %10s, Dpto: %2s, email: %2s\n\t%s",
            this.getNome(),
            this.getDepartamento(),
            this.getEmail(),
            this.getTelefone()
        );
    }
}
