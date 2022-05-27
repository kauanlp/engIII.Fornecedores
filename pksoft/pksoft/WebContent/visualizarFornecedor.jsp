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
  <title>Visualizar Fornecedor</title>

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

<body>
   
  		<%
           Resultado resultado = (Resultado) request.getAttribute("resultado");
           Fornecedor fornecedor = (Fornecedor) session.getAttribute("visualizarFornecedor");
        %>
        
  <header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-lg-md-3 col-lg-lg-2 me-0 px-3" href="http://localhost:8080/pksoft/search.jsp">PKSoft</a>
  </header>

  <div class="container">
    <div class="py-5 text-center">
      <h2>Dados do Fornecedor</h2>
    </div>
    <div class="row">
      <div class="col">
        <form class="needs-validation" action="alterarFornecedor" id="formulario" method="post" novalidate>
          <div class="card m-3">
            <div class="card-header">
              Fornecedor
            </div>

            <div class="card-body wrapperTelefones">
              <div class="row mb-3 wrapperCnaes">
                <div class="col-lg-1">
                  <label for="nomeFantasia" class="form-label">ID</label>
                  <input type="text" class="form-control" name="txtId" placeholder="" value="<%out.print(fornecedor.getId());%>" readonly>
                </div>
                <div class="col-lg-1">
	                 <label for="tipoTelefone" class="form-label">Status</label>
	                   <select class="form-select" name="txtStatus">
	                   <option value="<%out.print(fornecedor.getStatus());%>"><%out.print(fornecedor.getStatus());%></option>
	                   <option value="ATIVO">Ativo</option>
	                   <option value="INATIVO">Inativo</option>
	                </select>
	            </div>
                <div class="col-lg-5">
                  <label for="nomeFantasia" class="form-label">Nome Fantasia</label>
                  <input type="text" class="form-control" name="txtNomeFantasia" placeholder="" value="<%out.print(fornecedor.getNmFantasia());%>">
                </div>

                <div class="col-lg-5">
                  <label for="razaoSocial" class="form-label">Razão Social</label>
                  <input type="text" class="form-control" name="txtRazaoSocial" placeholder="" value="<%out.print(fornecedor.getCnpj());%>">
                </div>

                <div class="col-lg-3">
                  <label for="cnpj" class="form-label">CNPJ</label>
                  <input type="text" class="form-control" name="txtCnpj" placeholder="" value="<%out.print(fornecedor.getCnpj());%>">
                </div>

                <div class="col-lg-3">
                  <label for="inscricaoEstadual" class="form-label">Inscrição Estadual</label>
                  <input type="text" class="form-control" name="txtInscricaoEstadual" placeholder="" value="<%out.print(fornecedor.getInscricaoEstadual());%>">
                </div>

                <div class="col-lg-3">
                  <label for="inscricaoMunicipal" class="form-label">Inscrição Municipal</label>
                  <input type="text" class="form-control" name="txtInscricaoMunicipal" placeholder="" value="<%out.print(fornecedor.getInscricaoMunicipal());%>">
                </div>

                <div class="col-lg-6">
                  <label for="email" class="form-label">Email</label>
                  <input type="email" class="form-control" name="txtEmail" placeholder="email@dominio.com" value="<%out.print(fornecedor.getEmail());%>">
                </div>
                
                 <div class="col-lg-2 align-self-end d-grid">
                  <button class="addCnaeButton btn btn-secondary btn-md" type="button">Add Cnae</button>
                </div>
                  <% 	
                        for (int i = 0; i<fornecedor.getCnaes().size(); i++) {
                  %>
                  <div class="col-lg-2">
                    <label for="cnae" class="form-label">CNAE</label>
                    <input type="text" class="form-control" name="listaCnaesString" placeholder="" value="<%out.print(fornecedor.getCnaes().get(i).getCodigo());%>">
                    <input type="hidden" name="listaCnaesId" value="<%out.print(fornecedor.getCnaes().get(i).getId());%>">
                  </div>
                  <%
                        }
                  %>
                </div>
                  <% 	
                    for (int i=0; i<fornecedor.getTelefones().size(); i++) {
                  %>
              <div class="card">
                <div class="card-header">
                  Telefone
                </div>
                <div class="card-body">
                  <div class="row">
                    <div class="col-lg-2">
                    <input type="hidden" name="listaTelefonesId" value="<%out.print(fornecedor.getTelefones().get(i).getId());%>">
                      <label for="tipoTelefone" class="form-label">Tipo de Telefone</label>
                      <select class="form-select" name="tipoTelefone">
                        <option value="<%out.print(fornecedor.getTelefones().get(i).getTipoTelefone());%>"><%out.print(fornecedor.getTelefones().get(i).getTipoTelefone());%></option>
                        <option value="Fixo">Fixo</option>
                        <option value="Movel">Móvel</option>
                      </select>
                    </div>

                    <div class="col-lg-2">
                      <div class="form-group">
                        <label for="telefoneEmpresaDDI" class="form-label">DDI</label>
                        <input type="text" class="form-control" name="ddiTelefone" placeholder="" value="<%out.print(fornecedor.getTelefones().get(i).getDdi());%>">
                      </div>

                    </div>
                    <div class="col-lg-2">
                      <div class="form-group">
                        <label for="telefoneEmpresaDDD" class="form-label">DDD</label>
                        <input type="text" class="form-control" name="dddTelefone" placeholder="" value="<%out.print(fornecedor.getTelefones().get(i).getDdd());%>">
                      </div>
                    </div>

                    <div class="col-lg-6">
                      <div class="form-group">
                        <label for="telefoneEmpresaNumero" class="form-label">Número</label>
                        <input type="text" class="form-control" name="numeroTelefone" placeholder="" value="<%out.print(fornecedor.getTelefones().get(i).getNumero());%>">
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <%
                }
              %>
            </div>
            <div class="row">
              <div class="mt-3 text-center">
                <button class="addTelefoneButton w-50 btn btn-secondary btn-md" type="button">Adicionar Telefone</button>
              </div>
            </div>
          </div>

          <div class="card m-3">
            <div class="card-header">
              Contatos
            </div>
            <div class="card-body wrapperContatos">
              <% 	
                for (int i=0; i<fornecedor.getContatos().size(); i++) {
              %>
              <div class="card">
                <div class="card-header">
                  Contato 1
                </div>
                <div class="card-body">
                  <div class="row">
                    <input type="hidden" name="listaContatosId" value="<%out.print(fornecedor.getContatos().get(i).getId());%>">
                    <div class="col-lg-8">
                      <label for="contatoNome" class="form-label">Nome do Contato</label>
                      <input type="text" class="form-control" name="nomeContatos" placeholder="" value="<%out.print(fornecedor.getContatos().get(i).getNome());%>">
                    </div>
                    <div class="col-lg-4">
                      <label for="contatoDepartamento" class="form-label">Departamento</label>
                      <input type="text" class="form-control" name="dptoContatos" placeholder="" value="<%out.print(fornecedor.getContatos().get(i).getDepartamento());%>">
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-5">
                      <label for="email" class="form-label">Email</label>
                      <input type="email" class="form-control" name="emailContatos" placeholder="" value="<%out.print(fornecedor.getContatos().get(i).getEmail());%>">
                    </div>
                    <div class="col-lg-7">
                      <div class="row">
                        <div class="col-lg-4">
                          <input type="hidden" name="listaTelefonesContatosId" value="<%out.print(fornecedor.getContatos().get(i).getTelefone().getId());%>">
                          <label for="tipoTelefone" class="form-label">Tipo de Telefone</label>
                          <select class="form-select" name="tipoTelefoneContatos">
                            <option value="<%out.print(fornecedor.getContatos().get(i).getTelefone().getTipoTelefone());%>"><%out.print(fornecedor.getContatos().get(i).getTelefone().getTipoTelefone());%></option>
                            <option value="Fixo">Fixo</option>
                            <option value="Movel">Móvel</option>
                          </select>
                        </div>
                        <div class="col-lg-2">
                          <div class="form-group">
                            <label for="telefoneEmpresaDDI" class="form-label">DDI</label>
                            <input type="text" class="form-control" name="ddiTelefoneContatos" placeholder="" value="<%out.print(fornecedor.getContatos().get(i).getTelefone().getDdi());%>">
                          </div>
                        </div>
                        <div class="col-lg-2" name="telefoneEmpresa">
                          <div class="form-group">
                            <label for="telefoneEmpresaDDD" class="form-label">DDD</label>
                            <input type="text" class="form-control" name="dddTelefoneContatos" placeholder="" value="<%out.print(fornecedor.getContatos().get(i).getTelefone().getDdd());%>">
                          </div>
                        </div>
                        <div class="col-lg-4">
                          <div class="form-group">
                            <label for="telefoneEmpresaNumero" class="form-label">Número</label>
                            <input type="text" class="form-control" name="numeroTelefoneContatos" placeholder="" value="<%out.print(fornecedor.getContatos().get(i).getTelefone().getNumero());%>">
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <%
                }
              %>
            </div>
            <div class="mt-3 text-center">
              <button class="addContatoButton w-50 btn btn-secondary btn-md" type="button">Adicionar Contato</button>
            </div>
          </div>

          <div class="card m-3">
            <div class="card-header">
              Endereço
            </div>
            <div class="card-body">
              <div class="row">
                <div class="col-lg-9">
                  <label for="tipoEndereco" class="form-label">Tipo de Endereço</label>
                  <select class="form-select" name="txtTipoEndereco">
                    <option value="<%out.print(fornecedor.getEndereco().getTipoEndereco());%>"><%out.print(fornecedor.getEndereco().getTipoEndereco());%></option>
                    <option value="Edifício">Edifício</option>
                    <option value="Galpão">Galpão</option>
                    <option value="Sala Comercial">Sala Comercial</option>
                  </select>
                </div>

                <div class="col-lg-3">
                  <label for="cep" class="form-label">CEP</label>
                  <input type="text" class="form-control" name="txtCep" placeholder="" value="<%out.print(fornecedor.getEndereco().getCep());%>">
                </div>

                <div class="col-lg-2">
                  <label for="tipoLogradouro" class="form-label">Tipo de Logradouro</label>
                  <select class="form-select" name="txtTipoLogradouro">
                    <option value="<%out.print(fornecedor.getEndereco().getTipoLogradouro());%>"><%out.print(fornecedor.getEndereco().getTipoLogradouro());%></option>
                    <option value="Avenida">Avenida</option>
                    <option value="Estrada">Estrada</option>
                    <option value="Rua">Rua</option>
                  </select>
                </div>

                <div class="col-lg-5">
                  <label for="logradouro" class="form-label">Logradouro</label>
                  <input type="text" class="form-control" name="txtLogradouro" placeholder="" value="<%out.print(fornecedor.getEndereco().getLogradouro());%>">
                </div>

                <div class="col-lg-2">
                  <label for="numeroEndereco" class="form-label">Número</label>
                  <input type="text" class="form-control" name="txtNumero" placeholder="" value="<%out.print(fornecedor.getEndereco().getNumero());%>">
                </div>

                <div class="col-lg-3">
                  <label for="complemento" class="form-label">Complemento <span></span></label>
                  <input type="text" class="form-control" name="txtComplemento" placeholder="" value="<%out.print(fornecedor.getEndereco().getComplemento());%>">
                </div>

                <div class="col-lg-3">
                  <label for="bairro" class="form-label">Bairro</label>
                  <input type="text" class="form-control" name="txtBairro" placeholder="" value="<%out.print(fornecedor.getEndereco().getBairro());%>">
                </div>

                <div class="col-lg-3">
                  <label for="cidade" class="form-label">Cidade</label>
                  <input type="text" class="form-control" name="txtCidade" placeholder="" value="<%out.print(fornecedor.getEndereco().getCidade());%>">
                </div>

                <div class="col-lg-3">
                  <label for="estado" class="form-label">Estado</label>
                  <input type="text" class="form-control" name="txtEstado" placeholder="" value="<%out.print(fornecedor.getEndereco().getEstado());%>">
                </div>

                <div class="col-lg-3">
                  <label for="pais" class="form-label">Pais</label>
                  <input type="text" class="form-control" name="txtPais" placeholder="" value="<%out.print(fornecedor.getEndereco().getPais());%>">
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
                      <option value="<%out.print(fornecedor.getTipoFornecimento());%>"><%out.print(fornecedor.getTipoFornecimento());%></option>
                      <option value="SERVICO">Servico</option>
                      <option value="VENDA">Venda</option>
                      <option value="DUPLO">Duplo</option>
                    </select>
                  </div>
                </div>
              </div>
            </div>
            <div class="DivPai">
              <%if(fornecedor.getTipoFornecimento().equals("SERVICO") || fornecedor.getTipoFornecimento().equals("DUPLO")){ %>
              <div id="cardServicos">
                <div class="card m-3">
                  <div class="card-header">
                    Serviços
                  </div>
                  <div class="card-body wrapperServicos">
                  <% 	
                    for (int i=0; i<fornecedor.getServicosOfertados().size(); i++) {
                  %>
                    <div class="row">
                      <input type="hidden" name="listaServicosId" value="<%out.print(fornecedor.getServicosOfertados().get(i).getId());%>">
                      <div class="col-lg-3">
                        <label for="Descrição" class="form-label">Codigo Serviço</label>
                        <input type="text" class="form-control" name="codigoServico" placeholder="" value="<%out.print(fornecedor.getServicosOfertados().get(i).getCodigo());%>">
                      </div>
                      <div class="col-lg-3">
                        <label for="Descrição" class="form-label">Descrição Serviço</label>
                        <input type="text" class="form-control" name="descricaoServico" placeholder="" value="<%out.print(fornecedor.getServicosOfertados().get(i).getDescricao());%>">
                      </div>
                    </div>
                  <%
                    }
                  %>
                  </div>
                  <div class="mt-3 text-center">
                    <button class="addServicoButton w-50 btn btn-secondary btn-md" type="submit">Adicionar Serviço</button>
                  </div>
                </div>
              </div>
	          <% 
	             } 
	          %>

			<%if(fornecedor.getTipoFornecimento().equals("VENDA") || fornecedor.getTipoFornecimento().equals("DUPLO")){ %>
              <div id="cardProdutos">
                <div class="card m-3">
                  <div class="card-header">
                    Produtos
                  </div>
                  <div class="card-body wrapperProdutos">
                    <% 	
                      for (int i=0; i<fornecedor.getProdutosOfertados().size(); i++) {
                    %>
                    <div class="row">
                      <input type="hidden" name="listaProdutosId" value="<%out.print(fornecedor.getProdutosOfertados().get(i).getId());%>">
                      <div class="col-lg-3">
                        <label for="Descrição" class="form-label">Codigo Produto</label>
                        <input type="text" class="form-control" name="codigoProduto" placeholder="" value="<%out.print(fornecedor.getProdutosOfertados().get(i).getCodigo());%>">
                      </div>
                      <div class="col-lg-3">
                        <label for="Descrição" class="form-label">Descrição Produto</label>
                        <input type="text" class="form-control" name="descricaoProduto" placeholder="" value="<%out.print(fornecedor.getProdutosOfertados().get(i).getDescricao());%>">
                      </div>
                    </div>
                    <%
                      }
                    %>
                  </div>
                  <div class="mt-3 text-center">
                    <button class="addProdutoButton w-50 btn btn-secondary btn-md" type="submit">Adicionar Produto</button>
                  </div>
                </div>
              </div>
             <% 
             	} 
             %>
            </div>
          </div>

          <div class="m-3">
            <div class="row justify-content-md-center">
              <div class="col-lg-3">
              <button class="w-100 btn btn-primary btn-lg" type="submit" name="operacao" value="ALTERAR">Alterar</button>
              </div>
              
              <div class="col-lg-3">
               <a href="http://localhost:8080/pksoft/consultarFornecedores?txtId=<%out.print(fornecedor.getId());%>&operacao=EXCLUIR" >
				  <button type="button" class="w-100 btn btn-primary btn-lg">Excluir</button>
               </a>
              </div>
            </div>
          </div>
                    <div style="text-align:center">
          		<% 
		            Resultado resultadoCadastro = (Resultado) request.getAttribute("resultado");
                    if(resultadoCadastro != null) {
                    	out.print(resultadoCadastro.getMsg());
                    }
          		%>
          </div>
        </form>
      </div>

      <footer class="my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">&copy; 2021 PKSoft</p>
      </footer>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"></script>
    <script src="form-validation.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script>
      $(document).ready(function () {
        var wrapperCnaes = $(".wrapperCnaes"); //Fields wrapper
        var addCnaeButton = $(".addCnaeButton"); //Add button ID

        $(addCnaeButton).click(function (e) { //on add input button click
          e.preventDefault();
          $(wrapperCnaes).append('<div class="col-lg-2"> <label for="cnae" class="form-label">CNAE</label> <input type="text" class="form-control" name="listaCnaesString" placeholder="" value=""><input type="hidden" name="listaCnaesId" value=""><div class="invalid-feedback"> CNAE é um campo obrigatório. </div> <a style="text-align:right" href="#" class="remove_field">Remover CNAE</a></div>');
        });

        $(wrapperCnaes).on("click", ".remove_field", function (e) { //user click on remove text
          e.preventDefault(); $(this).parent('div').remove(); x--;
        })

      });

      $(document).ready(function () {
        var wrapperTelefones = $(".wrapperTelefones"); //Fields wrapper
        var addTelefoneButton = $(".addTelefoneButton"); //Add button ID

        $(addTelefoneButton).click(function (e) { //on add input button click
          e.preventDefault();
          $(wrapperTelefones).append('<div class="card"><div class="card-header">Telefone</div><div class="card-body"><div class="row"><div class="col-lg-2"><input type="hidden" name="listaTelefonesId" value=""><label for="tipoTelefone" class="form-label">Tipo de Telefone</label><select class="form-select" name="tipoTelefone"><option value="">Choose...</option> <option>Fixo</option><option>Móvel</option></select><div class="invalid-feedback">Tipo de Telefone é um campo obrigatório.</div></div><div class="col-lg-2"><div class="form-group"><label for="telefoneEmpresaDDI" class="form-label">DDI</label><input type="text" class="form-control" name="ddiTelefone" placeholder="" value=""><div class="invalid-feedback">DDI é um campo obrigatório.</div></div></div> <div class="col-lg-2"> <div class="form-group"><label for="telefoneEmpresaDDD" class="form-label">DDD</label><input type="text" class="form-control" name="dddTelefone" placeholder="" value=""><div class="invalid-feedback">DDD é um campo obrigatório.</div></div></div><div class="col-lg-6"> <div class="form-group"><label for="telefoneEmpresaNumero" class="form-label">Número</label><input type="text" class="form-control" name="numeroTelefone" placeholder="" value=""><div class="invalid-feedback">Numero é um campo obrigatório.</div></div></div></div></div><a style="text-align:right" href="#" class="remove_field">Remover Telefone</a>');
        });

        $(wrapperTelefones).on("click", ".remove_field", function (e) { //user click on remove text
          e.preventDefault(); $(this).parent('div').remove(); x--;
        })

      });

      $(document).ready(function () {
        var wrapperContatos = $(".wrapperContatos"); //Fields wrapper
        var addContatoButton = $(".addContatoButton"); //Add button ID

        $(addContatoButton).click(function (e) { //on add input button click
          e.preventDefault();
          $(wrapperContatos).append('<div class="card"> <div class="card-header"> Contato </div> <div class="card-body"> <div class="row"> <div class="col-lg-8"><input type="hidden" name="listaContatosId" value=""><label for="contatoNome" class="form-label">Nome do Contato</label> <input type="text" class="form-control" name="nomeContatos" placeholder=""> <div class="invalid-feedback"> Nome do contato é um campo obrigatório. </div> </div> <div class="col-lg-4"> <label for="contatoDepartamento" class="form-label">Departamento</label> <input type="text" class="form-control" name="dptoContatos" placeholder=""> <div class="invalid-feedback"> Departamento do contato é um campo obrigatório. </div> </div> </div> <div class="row"> <div class="col-lg-5"> <label for="email" class="form-label">Email</label> <input type="email" class="form-control" name="emailContatos" placeholder=""> <div class="invalid-feedback"> Email é um campo obrigatório. </div> </div> <div class="col-lg-7"> <div class="row"> <div class="col-lg-4"> <input type="hidden" name="listaTelefonesContatosId" value=""><label for="tipoTelefone" class="form-label">Tipo de Telefone</label> <select class="form-select" name="tipoTelefoneContatos"> <option value="">Choose...</option> <option>Fixo</option> <option>Móvel</option> </select> <div class="invalid-feedback"> Tipo de Telefone é um campo obrigatório. </div> </div> <div class="col-lg-2"> <div class="form-group"> <label for="telefoneEmpresaDDI" class="form-label">DDI</label> <input type="text" class="form-control" name="ddiTelefoneContatos" placeholder="" value=""> <div class="invalid-feedback"> DDI é um campo obrigatório. </div> </div> </div> <div class="col-lg-2" name="telefoneEmpresa"> <div class="form-group"> <label for="telefoneEmpresaDDD" class="form-label">DDD</label> <input type="text" class="form-control" name="dddTelefoneContatos" placeholder="" value=""> <div class="invalid-feedback"> DDD é um campo obrigatório. </div> </div> </div> <div class="col-lg-4"> <div class="form-group"> <label for="telefoneEmpresaNumero" class="form-label">Número</label> <input type="text" class="form-control" name="numeroTelefoneContatos" placeholder="" value=""> <div class="invalid-feedback"> Numero é um campo obrigatório. </div> </div> </div> </div> </div> </div> </div><a style="text-align:right" href="#" class="remove_field">Remover Contato</a></div>');
        });
        $(wrapperContatos).on("click", ".remove_field", function (e) { //user click on remove text
          e.preventDefault(); $(this).parent('div').remove(); x--;
        })

      });

      $(document).ready(function () {
        var wrapperServicos = $(".wrapperServicos"); //Fields wrapper
        var addServicoButton = $(".addServicoButton"); //Add button ID

        $(addServicoButton).click(function (e) { //on add input button click
          e.preventDefault();
          $(wrapperServicos).append('<div class="row"> <div class="col-lg-3"><input type="hidden" name="listaServicosId" value=""><label for="Descrição" class="form-label">Codigo Serviço</label> <input type="text" class="form-control" name="codigoServico" placeholder="" value=""> </div> <div class="col-lg-3"> <label for="Descrição" class="form-label">Descrição Serviço</label> <input type="text" class="form-control" name="descricaoServico" placeholder="" value=""> </div> <a style="text-align:right" href="#" class="remove_field">Remover Serviço</a></div>');

        });
        $(wrapperServicos).on("click", ".remove_field", function (e) { //user click on remove text
          e.preventDefault(); $(this).parent('div').remove(); x--;
        })

      });

      $(document).ready(function () {
        var wrapperProdutos = $(".wrapperProdutos"); //Fields wrapper
        var addProdutoButton = $(".addProdutoButton"); //Add button ID

        $(addProdutoButton).click(function (e) { //on add input button click
          e.preventDefault();
          $(wrapperProdutos).append('          <div class="row"> <div class="col-lg-3"><input type="hidden" name="listaProdutosId" value=""><label for="Descrição" class="form-label">Codigo Produto</label> <input type="text" class="form-control" name="codigoProduto" placeholder="" value=""> </div> <div class="col-lg-3"> <label for="Descrição" class="form-label">Descrição Produto</label> <input type="text" class="form-control" name="descricaoProduto" placeholder="" value=""> </div><a style="text-align:right" href="#" class="remove_field">Remover Produto</a></div>');

        });
        $(wrapperProdutos).on("click", ".remove_field", function (e) { //user click on remove text
          e.preventDefault(); $(this).parent('div').remove(); x--;
        })

      });
    </script>
    
    <script>
    <%
		String operacao = request.getParameter("operacao");
    	if(operacao.equals("VISUALIZAR")){
    %>
    
     <%
    	}
     %>
    </script>
 
</body>

</html>