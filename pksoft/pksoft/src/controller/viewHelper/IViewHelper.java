package controller.viewHelper;

import java.io.IOException;

import aplicacao.Resultado;
import dominio.EntidadeDominio;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IViewHelper {

    public EntidadeDominio getEntidade(HttpServletRequest request);

    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
