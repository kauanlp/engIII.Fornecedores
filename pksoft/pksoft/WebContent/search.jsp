<%@page import="dominio.Fornecedor" %>
<%@page import="aplicacao.Resultado" %>
<%@page import="dominio.EntidadeDominio" %>
  <%@page import="java.util.List" %>

    <!doctype html>
    <html lang="en">

    <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="description" content="">
      <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
      <meta name="generator" content="Hugo 0.84.0">
      <title>Consulta de Fornecedores</title>

      <!-- Bootstrap core CSS -->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

      <style>
        .bd-placeholder-img {
          font-size: 1.125rem;
          text-anchor: middle;
          -webkit-user-select: none;
          -moz-user-select: none;
          user-select: none;
        }

        @media (min-width: 768px) {
          .bd-placeholder-img-lg {
            font-size: 3.5rem;
          }
        }
      </style>

      <!-- Custom styles for this template -->
      <link href="style.css" rel="stylesheet">
      <link href="form-validation.css" rel="stylesheet">

      <script type="text/javascript" language="javascript" src="javascript/jquery.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    </head>

    <body >
    
      <header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
        <a class="navbar-brand col-lg-md-3 col-lg-lg-2 me-0 px-3" href="http://localhost:8080/pksoft/search.jsp">PKSoft</a>
      </header>

      <div class="m-3">
        <button class="w-100 btn btn-primary" id="btnForm">Definir Filtro</button>
      </div>

      <div class="container">
        <form class="needs-validation" action="consultarFornecedores" method="post" novalidate>
          <div class="row">
            <div class="col">
              <div id="cardFormulario">
                <div class="card m-3">
                  <div class="card-header">
                    Fornecedor
                  </div>
                  <div class="card-body wrapperTelefones">
                    <div class="row mb-3 wrapperCnaes">
                     <div class="col-lg-1">
	                   <label for="tipoTelefone" class="form-label">Status</label>
		               <select class="form-select" name="txtStatus">
		                   <option value="">Choose...</option>
		                   <option value="ATIVO">Ativo</option>
		                   <option value="INATIVO">Inativo</option>
		               </select>
	           		  </div>
  
                      <div class="col-lg-6">
                        <label for="nomeFantasia" class="form-label">Nome Fantasia</label>
                        <input type="text" class="form-control" name="txtNomeFantasia" placeholder="" value="">
                      </div>

                      <div class="col-lg-6">
                        <label for="razaoSocial" class="form-label">Razao Social</label>
                        <input type="text" class="form-control" name="txtRazaoSocial" placeholder="" value="">
                      </div>

                      <div class="col-lg-3">
                        <label for="cnpj" class="form-label">CNPJ</label>
                        <input type="text" class="form-control" name="txtCnpj" placeholder="" value="">
                      </div>

                      <div class="col-lg-3">
                        <label for="inscricaoEstadual" class="form-label">Inscricao Estadual</label>
                        <input type="text" class="form-control" name="txtInscricaoEstadual" placeholder="" value="">
                      </div>

                      <div class="col-lg-3">
                        <label for="inscricaoMunicipal" class="form-label">Inscricao Municipal</label>
                        <input type="text" class="form-control" name="txtInscricaoMunicipal" placeholder="" value="">
                      </div>

                      <div class="col-lg-6">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" name="txtEmail" placeholder="">
                      </div>

                      <div class="col-lg-2">
                        <label for="cnae" class="form-label">CNAE</label>
                        <input type="text" class="form-control" name="listaCnaesString" value="">
                        <input type="hidden" name="listaCnaesId" value="">
                      </div>

                    </div>
                    <div class="card">
                      <div class="card-header">
                        Telefone 1
                      </div>
                      <div class="card-body">
                        <div class="row">
                          <div class="col-lg-2">
                            <input type="hidden" name="listaTelefonesId" value="">
                            <label for="tipoTelefone" class="form-label">Tipo de Telefone</label>
                            <select class="form-select" name="tipoTelefone">
                              <option value="">Choose...</option>
                              <option value="Fixo">Fixo</option>
                              <option value="Movel">Movel</option>
                            </select>
                          </div>

                          <div class="col-lg-2">
                            <div class="form-group">
                              <label for="telefoneEmpresaDDI" class="form-label">DDI</label>
                              <input type="text" class="form-control" name="ddiTelefone" placeholder="" value="">
                            </div>

                          </div>
                          <div class="col-lg-2">
                            <div class="form-group">
                              <label for="telefoneEmpresaDDD" class="form-label">DDD</label>
                              <input type="text" class="form-control" name="dddTelefone" placeholder="" value="">
                            </div>
                          </div>

                          <div class="col-lg-6">
                            <div class="form-group">
                              <label for="telefoneEmpresaNumero" class="form-label">Numero</label>
                              <input type="text" class="form-control" name="numeroTelefone" placeholder="" value="">
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                  </div>
                </div>

                <div class="card m-3">
                  <div class="card-header">
                    Contatos
                  </div>
                  <div class="card-body wrapperContatos">
                    <div class="card">
                      <div class="card-header">
                        Contato 1
                      </div>
                      <div class="card-body">
                        <div class="row">
                          <div class="col-lg-8">
                            <input type="hidden" name="listaContatosId" value="">
                            <label for="contatoNome" class="form-label">Nome do Contato</label>
                            <input type="text" class="form-control" name="nomeContatos" placeholder="">
                          </div>
                          <div class="col-lg-4">
                            <label for="contatoDepartamento" class="form-label">Departamento</label>
                            <input type="text" class="form-control" name="dptoContatos" placeholder="">
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-lg-5">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" name="emailContatos" placeholder="">
                          </div>
                          <div class="col-lg-7">
                            <div class="row">
                              <div class="col-lg-4">
                                <label for="tipoTelefone" class="form-label">Tipo de Telefone</label>
                                <select class="form-select" name="tipoTelefoneContatos">
                                  <option value="">Choose...</option>
                                  <option value="Fixo">Fixo</option>
                                  <option value="Movel">Movel</option>
                                </select>
                              </div>
                              <div class="col-lg-2">
                                <div class="form-group">
                                  <label for="telefoneEmpresaDDI" class="form-label">DDI</label>
                                  <input type="text" class="form-control" name="ddiTelefoneContatos" placeholder=""
                                    value="">
                                </div>
                              </div>
                              <div class="col-lg-2" name="telefoneEmpresa">
                                <div class="form-group">
                                  <label for="telefoneEmpresaDDD" class="form-label">DDD</label>
                                  <input type="text" class="form-control" name="dddTelefoneContatos" placeholder=""
                                    value="">
                                </div>
                              </div>
                              <div class="col-lg-4">
                                <div class="form-group">
                                  <label for="telefoneEmpresaNumero" class="form-label">Numero</label>
                                  <input type="text" class="form-control" name="numeroTelefoneContatos" placeholder=""
                                    value="">
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="card m-3">
                  <div class="card-header">
                    Endereco
                  </div>
                  <div class="card-body">
                    <div class="row">
                      <div class="col-lg-9">
                        <label for="tipoEndereco" class="form-label">Tipo de Endereco</label>
                        <select class="form-select" name="txtTipoEndereco">
                          <option value="">Choose...</option>
                          <option value="Edificio">Edificio</option>
                          <option value="Galpao">Galpao</option>
                          <option value="Sala Comercial">Sala Comercial</option>
                        </select>
                      </div>

                      <div class="col-lg-3">
                        <label for="cep" class="form-label">CEP</label>
                        <input type="text" class="form-control" name="txtCep" placeholder="" value="">
                      </div>

                      <div class="col-lg-2">
                        <label for="tipoLogradouro" class="form-label">Tipo de Logradouro</label>
                        <select class="form-select" name="txtTipoLogradouro">
                          <option value="">Choose...</option>
                          <option value="Avenida">Avenida</option>
                          <option value="Estrada">Estrada</option>
                          <option value="Rua">Rua</option>
                        </select>
                      </div>

                      <div class="col-lg-5">
                        <label for="logradouro" class="form-label">Logradouro</label>
                        <input type="text" class="form-control" name="txtLogradouro" placeholder="" value="">
                      </div>

                      <div class="col-lg-2">
                        <label for="numeroEndereco" class="form-label">Numero</label>
                        <input type="text" class="form-control" name="txtNumero" placeholder="" value="">
                      </div>

                      <div class="col-lg-3">
                        <label for="complemento" class="form-label">Complemento <span></span></label>
                        <input type="text" class="form-control" name="txtComplemento" placeholder="" value="">
                      </div>

                      <div class="col-lg-3">
                        <label for="bairro" class="form-label">Bairro</label>
                        <input type="text" class="form-control" name="txtBairro" placeholder="" value="">
                      </div>

                      <div class="col-lg-3">
                        <label for="cidade" class="form-label">Cidade</label>
                        <input type="text" class="form-control" name="txtCidade" placeholder="" value="">
                      </div>

                      <div class="col-lg-3">
                        <label for="estado" class="form-label">Estado</label>
                        <input type="text" class="form-control" name="txtEstado" placeholder="" value="">
                      </div>

                      <div class="col-lg-3">
                        <label for="pais" class="form-label">Pais</label>
                        <input type="text" class="form-control" name="txtPais" placeholder="" value="">
                      </div>
                    </div>
                  </div>
                </div>

                <div class="card m-3">
                  <div class="card-header">
                    Fornecimento
                  </div>
                  <div class="card-body">
                    <div class="row">
                      <div class="col">
                        <div class="col-lg-2">
                          <label for="tipoFornecimento" class="form-label">Tipo de Fornecimento</label>
                          <select class="form-select" id="tipoFornecimento" name="txtTipoFornecimento">
                            <option value="">Choose...</option>
                            <option value="SERVICO">Servico</option>
                            <option value="VENDA">Venda</option>
                            <option value="DUPLO">Duplo</option>
                          </select>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="DivPai">
                    <div id="cardServicos">
                      <div class="card m-3">
                        <div class="card-header">
                          Servicos
                        </div>
                        <div class="card-body wrapperServicos">
                          <div class="row">
                            <div class="col-lg-3">
                              <input type="hidden" name="listaServicosId" value="">
                              <label for="Descricao" class="form-label">Codigo Servico</label>
                              <input type="text" class="form-control" name="codigoServico" placeholder="" value="">
                            </div>
                            <div class="col-lg-3">
                              <label for="Descricao" class="form-label">Descricao Servico</label>
                              <input type="text" class="form-control" name="descricaoServico" placeholder="" value="">
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>

                    <div id="cardProdutos">
                      <div class="card m-3">
                        <div class="card-header">
                          Produtos
                        </div>
                        <div class="card-body wrapperProdutos">
                          <div class="row">
                            <div class="col-lg-3">
                              <input type="hidden" name="listaProdutosId" value="">
                              <label for="Descricao" class="form-label">Codigo Produto</label>
                              <input type="text" class="form-control" name="codigoProduto" placeholder="" value="">
                            </div>
                            <div class="col-lg-3">
                              <label for="Descricao" class="form-label">Descricao Produto</label>
                              <input type="text" class="form-control" name="descricaoProduto" placeholder="" value="">
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
          
            <div class="row">
              <main class="col">
                <div
                  class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                  <h1 class="h2">Fornecedores</h1>
                  <div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group me-2">
                      <button
                          class="btn btn-sm btn-outline-secondary" type="submit" name="operacao" value="SALVAR">Adicionar</button>
                    </div>
                  </div>
                </div>
                <div class="table-responsive">
                  <table class="table table-striped table-hover table-sm">
                  	<%
            			Resultado resultadoConsulta = (Resultado) session.getAttribute("fornecedorSessao");
       	 			%> 	 
                    <thead>
                      <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Nome Fantasia</th>
                        <th scope="col">CNPJ</th>
                        <th scope="col">Tipo de Fornecimento</th>
                        <th scope="col">Status</th>
                      </tr>
                    </thead>
                    <tbody>
                      <% 
                      if (resultadoConsulta != null) {
                          List<EntidadeDominio> fornecedores = resultadoConsulta.getEntidades();
                          if (fornecedores != null) {
                              for (int i = 0; i < fornecedores.size(); i++) {
                                  Fornecedor fornecedor = (Fornecedor) fornecedores.get(i);
                      %>
                      <tr>
                        <td><%out.print(fornecedor.getId());%> </th>
                        <td><%out.print(fornecedor.getNmFantasia());%></th>
                        <td><%out.print(fornecedor.getCnpj());%></th>
                        <td><%out.print(fornecedor.getTipoFornecimento());%> </th>
                        <td><%out.print(fornecedor.getStatus());%> </th> 
                        <td>
                            <div style='text-align:right'>
                            <a href="http://localhost:8080/pksoft/visualizarFornecedor?txtId=<%out.print(fornecedor.getId());%>&operacao=VISUALIZAR" >
                            	<button type="button" class="btn btn-sm btn-outline-secondary">Consultar</button>
                            </a>
                                
                            </div>
                        </th>
                      </tr>
                      <%              
                              }
                          }
                      }
                      %>
                    </tbody>
                  </table>
                </div>
                
                <div class="m-3">
                  <button class="w-100 btn btn-primary btn-lg" type="submit" name="operacao"
                    value="CONSULTAR">Buscar</button>
                </div>
                <div class="row">
                  <footer class="my-5 pt-5 text-muted text-center text-small">
                      <p class="mb-1">&copy; 2021 PKSoft</p>
                  </footer>
                </div>
              </main>
            

            </div>
          </div>
        </div>
       </form>
      </div>

  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script type="text/javascript">
    $(document).ready(function (e) {
      $("#cardFormulario").hide();

      $("#btnForm").click(function (e) {
        $("#cardFormulario").toggle();
      });
    });
  </script>

</body>

</html>