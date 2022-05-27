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
  <title>Cadastro de Fornecedor</title>

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
  <header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-lg-md-3 col-lg-lg-2 me-0 px-3" href="http://localhost:8080/pksoft/search.jsp">PKSoft</a>
  </header>

  <div class="container">
    <div class="py-5 text-center">
      <h2>Cadastro de Fornecedor</h2>
      <p class="lead">Lembre-se de conferir os dados antes de confirmar o cadastro.</p>
    </div>
    <div class="row">
      <div class="col">
        <form class="needs-validation" action="salvarFornecedor" method="post" novalidate>
          <div class="card m-3">
            <div class="card-header">
              Fornecedor
            </div>
            <div class="card-body wrapperTelefones">
              <div class="row mb-3 wrapperCnaes">
                <div class="col-lg-1">
	                <label for="tipoTelefone" class="form-label">Status</label>
	                  <select class="form-select" name="txtStatus">
	                  <option value="ATIVO" selected>Ativo</option>
	                  <option value="INATIVO">Inativo</option>
	               </select>
	            </div>
                <div class="col-lg-5">
                	<input type="hidden" name="txtAtivo" value="ATIVO">
                  <label for="nomeFantasia" class="form-label">Nome Fantasia</label>
                  <input type="text" class="form-control" name="txtNomeFantasia" placeholder="" value="">
                </div>

                <div class="col-lg-5">
                  <label for="razaoSocial" class="form-label">Razão Social</label>
                  <input type="text" class="form-control" name="txtRazaoSocial" placeholder="" value="">
                </div>

                <div class="col-lg-3">
                  <label for="cnpj" class="form-label">CNPJ</label>
                  <input type="text" class="form-control" name="txtCnpj" placeholder="" value="">
                </div>

                <div class="col-lg-3">
                  <label for="inscricaoEstadual" class="form-label">Inscrição Estadual</label>
                  <input type="text" class="form-control" name="txtInscricaoEstadual" placeholder="" value="">
                </div>

                <div class="col-lg-3">
                  <label for="inscricaoMunicipal" class="form-label">Inscrição Municipal</label>
                  <input type="text" class="form-control" name="txtInscricaoMunicipal" placeholder="" value="">
                </div>

                <div class="col-lg-6">
                  <label for="email" class="form-label">Email</label>
                  <input type="email" class="form-control" name="txtEmail" placeholder="">
                </div>

                <div class="col-lg-2 align-self-end d-grid">
                  <button class="addCnaeButton btn btn-secondary btn-md" type="button">Add Cnae</button>
                </div>

                <div class="col-lg-2">
                  <label for="cnae" class="form-label">CNAE</label>
                  <input type="text" class="form-control" name="listaCnaesString" placeholder="" value="">
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
                        <option value="Movel">Móvel</option>
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
                        <label for="telefoneEmpresaNumero" class="form-label">Número</label>
                        <input type="text" class="form-control" name="numeroTelefone" placeholder="" value="">
                      </div>
                    </div>
                  </div>
                </div>
              </div>
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
                            <option value="Movel">Móvel</option>
                          </select>
                        </div>
                        <div class="col-lg-2">
                          <div class="form-group">
                            <label for="telefoneEmpresaDDI" class="form-label">DDI</label>
                            <input type="text" class="form-control" name="ddiTelefoneContatos" placeholder="" value="">
  
                          </div>
                        </div>
                        <div class="col-lg-2" name="telefoneEmpresa">
                          <div class="form-group">
                            <label for="telefoneEmpresaDDD" class="form-label">DDD</label>
                            <input type="text" class="form-control" name="dddTelefoneContatos" placeholder="" value="">
                          </div>
                        </div>
                        <div class="col-lg-4">
                          <div class="form-group">
                            <label for="telefoneEmpresaNumero" class="form-label">Número</label>
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
                    <option value="">Choose...</option>
                    <option value="Edifício">Edifício</option>
                    <option value="Galpão">Galpão</option>
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
                  <label for="numeroEndereco" class="form-label">Número</label>
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
                    Serviços
                  </div>
                  <div class="card-body wrapperServicos">
                    <div class="row">
                      <div class="col-lg-3">
                      	<input type="hidden" name="listaServicosId" value="">
                        <label for="Descrição" class="form-label">Codigo Serviço</label>
                        <input type="text" class="form-control" name="codigoServico" placeholder="" value="">
                      </div>
                      <div class="col-lg-3">
                        <label for="Descrição" class="form-label">Descrição Serviço</label>
                        <input type="text" class="form-control" name="descricaoServico" placeholder="" value="">
                      </div>
                    </div>
                  </div>
                  <div class="mt-3 text-center">
                    <button class="addServicoButton w-50 btn btn-secondary btn-md" type="submit">Adicionar
                      Serviço</button>
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
                        <label for="Descrição" class="form-label">Codigo Produto</label>
                        <input type="text" class="form-control" name="codigoProduto" placeholder="" value="">
                      </div>
                      <div class="col-lg-3">
                        <label for="Descrição" class="form-label">Descrição Produto</label>
                        <input type="text" class="form-control" name="descricaoProduto" placeholder="" value="">
                      </div>
                    </div>
                  </div>
                  <div class="mt-3 text-center">
                    <button class="addProdutoButton w-50 btn btn-secondary btn-md" type="submit">Adicionar Produto</button>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="m-3">
            <button class="w-100 btn btn-primary btn-lg" type="submit" name="operacao" value="SALVAR">Cadastrar
              Fornecedor</button>
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
          $(wrapperCnaes).append('<div class="col-lg-2"> <label for="cnae" class="form-label">CNAE</label> <input type="text" class="form-control" name="listaCnaesString" placeholder="" value=""><input type="hidden" name="listaCnaesId" value=""> <div class="invalid-feedback"> CNAE é um campo obrigatório. </div> <a style="text-align:right" href="#" class="remove_field">Remover CNAE</a></div>');
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
          $(wrapperContatos).append('<div class="card"> <div class="card-header"> Contato </div> <div class="card-body"> <div class="row"> <div class="col-lg-8"><input type="hidden" name="listaContatosId" value=""><label for="contatoNome" class="form-label">Nome do Contato</label> <input type="text" class="form-control" name="nomeContatos" placeholder=""> <div class="invalid-feedback"> Nome do contato é um campo obrigatório. </div> </div> <div class="col-lg-4"> <label for="contatoDepartamento" class="form-label">Departamento</label> <input type="text" class="form-control" name="dptoContatos" placeholder=""> <div class="invalid-feedback"> Departamento do contato é um campo obrigatório. </div> </div> </div> <div class="row"> <div class="col-lg-5"> <label for="email" class="form-label">Email</label> <input type="email" class="form-control" name="emailContatos" placeholder=""> <div class="invalid-feedback"> Email é um campo obrigatório. </div> </div> <div class="col-lg-7"> <div class="row"> <div class="col-lg-4"> <label for="tipoTelefone" class="form-label">Tipo de Telefone</label> <select class="form-select" name="tipoTelefoneContatos"> <option value="">Choose...</option> <option>Fixo</option> <option>Móvel</option> </select> <div class="invalid-feedback"> Tipo de Telefone é um campo obrigatório. </div> </div> <div class="col-lg-2"> <div class="form-group"> <label for="telefoneEmpresaDDI" class="form-label">DDI</label> <input type="text" class="form-control" name="ddiTelefoneContatos" placeholder="" value=""> <div class="invalid-feedback"> DDI é um campo obrigatório. </div> </div> </div> <div class="col-lg-2" name="telefoneEmpresa"> <div class="form-group"> <label for="telefoneEmpresaDDD" class="form-label">DDD</label> <input type="text" class="form-control" name="dddTelefoneContatos" placeholder="" value=""> <div class="invalid-feedback"> DDD é um campo obrigatório. </div> </div> </div> <div class="col-lg-4"> <div class="form-group"> <label for="telefoneEmpresaNumero" class="form-label">Número</label> <input type="text" class="form-control" name="numeroTelefoneContatos" placeholder="" value=""> <div class="invalid-feedback"> Numero é um campo obrigatório. </div> </div> </div> </div> </div> </div> </div><a style="text-align:right" href="#" class="remove_field">Remover Contato</a></div>');
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
      $(document).ready(function () {
        //Select para mostrar e esconder divs
        $('#cardProdutos').hide();
        $('#cardServicos').hide();
        $('#tipoFornecimento').on('change', function () {

          var SelectValue = $(this).val();
          console.log(SelectValue);
          if(SelectValue == ""){
            $('#cardProdutos').hide();
            $('#cardServicos').hide();
          }
          if(SelectValue == "VENDA"){
            $('#cardProdutos').show();
            $('#cardServicos').hide();
          }
          if(SelectValue == "SERVICO"){
            $('#cardProdutos').hide();
            $('#cardServicos').show();
          }
          if(SelectValue == "DUPLO"){
            $('#cardProdutos').show();
            $('#cardServicos').show();
          }

          $(SelectValue).toggle();
        });
      });
    </script>

    <script>
      var btn = document.getElementById('btn-div');
      var container = document.querySelector('.container');
      btn.addEventListener('click', function () {

        if (container.style.display === 'block') {
          container.style.display = 'none';
        } else {
          container.style.display = 'block';
        }
      });
    </script>
</body>

</html>