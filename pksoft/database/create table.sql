CREATE TABLE tb_fornecedores
(
  for_id serial NOT NULL,
  for_cnpj character varying(14) NOT NULL,
  for_rz_social character varying(50) NOT NULL,
  for_nm_fantasia character varying(50) NOT NULL,
  for_insc_municipal character varying(11) NOT NULL,
  for_insc_estadual character varying(11) NOT NULL,
  for_email character varying(100) NOT NULL,
  for_end_tipo character varying(55) NOT NULL,
  for_end_cep character varying(8) NOT NULL,
  for_end_tipo_logradouro character varying(20) NOT NULL,
  for_end_logradouro character varying(255) NOT NULL,
  for_end_numero character varying(5) NOT NULL,
  for_end_bairro character varying(150) NOT NULL,
  for_end_complemento  character varying(150),
  for_end_cidade character varying(55) NOT NULL,
  for_end_estado character varying(55) NOT NULL,
  for_end_pais character varying(55) NOT NULL,
  for_tp_fornecimento character varying(10) NOT NULL,
  for_dt_cadastro timestamp without time zone NOT NULL,
  for_status character varying(10) NOT NULL,
  CONSTRAINT tb_fornecedores_pk PRIMARY KEY (for_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_fornecedores OWNER TO postgres;

----------------------------------------------------

CREATE TABLE tb_cnaes
(
  cnae_id serial NOT NULL,
  cnae_codigo character varying(10) NOT NULL,
  cnae_dt_cadastro timestamp without time zone NOT NULL,
  CONSTRAINT tb_cnaes_pk PRIMARY KEY (cnae_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_cnaes OWNER TO postgres;

----------------------------------------------------

CREATE TABLE tb_fornecedor_cnae
(
  forcnae_id serial NOT NULL,
  forcnae_for_id integer NOT NULL,
  forcnae_cnae_id integer NOT NULL,
  CONSTRAINT tb_fornecedor_cnae_pk PRIMARY KEY (forcnae_id),
  CONSTRAINT forcnae_for_id_fk FOREIGN KEY (forcnae_for_id)
      REFERENCES tb_fornecedores (for_id) MATCH SIMPLE ON DELETE CASCADE,
  CONSTRAINT forcnae_cnae_id_fk FOREIGN KEY (forcnae_cnae_id)
      REFERENCES tb_cnaes (cnae_id) MATCH SIMPLE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_fornecedor_cnae OWNER TO postgres;

-------------------------------------------------

CREATE TABLE tb_telefones
(
  tel_id serial NOT NULL,
  tel_tipo character varying(10) NOT NULL,
  tel_ddi character varying(2) NOT NULL,
  tel_ddd character varying(2) NOT NULL,
  tel_numero character varying(12) NOT NULL,
  tel_dt_cadastro timestamp without time zone NOT NULL,
  CONSTRAINT tb_telefones_pk PRIMARY KEY (tel_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_telefones OWNER TO postgres;

-------------------------------------------------
CREATE TABLE tb_contatos
(
  ctt_id serial NOT NULL,
  ctt_nome character varying(100) NOT NULL,
  ctt_dpto character varying(100) NOT NULL,
  ctt_email character varying(100) NOT NULL,
  ctt_telefone_id int NOT NULL,
  ctt_dt_cadastro timestamp without time zone NOT NULL,
  CONSTRAINT tb_contatos_pk PRIMARY KEY (ctt_id),
  CONSTRAINT ctt_telefone_id_fk FOREIGN KEY (ctt_telefone_id)
      REFERENCES tb_telefones (tel_id) MATCH SIMPLE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_contatos OWNER TO postgres;

-------------------------------------------------

CREATE TABLE tb_fornecedor_contato
(
  forctt_id serial NOT NULL,
  forctt_for_id integer NOT NULL,
  forctt_ctt_id integer NOT NULL,
  CONSTRAINT tb_fornecedor_contato_pk PRIMARY KEY (forctt_id),
  CONSTRAINT forctt_for_id_fk FOREIGN KEY (forctt_for_id)
      REFERENCES tb_fornecedores (for_id) MATCH SIMPLE ON DELETE CASCADE,
  CONSTRAINT forctt_ctt_id_fk FOREIGN KEY (forctt_ctt_id)
      REFERENCES tb_contatos (ctt_id) MATCH SIMPLE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_fornecedor_contato OWNER TO postgres;

-------------------------------------------------
CREATE TABLE tb_fornecedor_telefone
(
  fortel_id serial NOT NULL,
  fortel_for_id integer NOT NULL,
  fortel_tel_id integer NOT NULL,
  CONSTRAINT tb_fornecedor_telefone_pk PRIMARY KEY (fortel_id),
  CONSTRAINT fortel_for_id_fk FOREIGN KEY (fortel_for_id)
      REFERENCES tb_fornecedores (for_id) MATCH SIMPLE ON DELETE CASCADE,
  CONSTRAINT fortel_tel_id_fk FOREIGN KEY (fortel_tel_id)
      REFERENCES tb_telefones (tel_id) MATCH SIMPLE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_fornecedor_telefone OWNER TO postgres;

-------------------------------------------------

CREATE TABLE tb_servicos
(
  svc_id serial NOT NULL,
  svc_codigo character varying(10) NOT NULL,
  svc_descricao character varying(100) NOT NULL,
  svc_dt_cadastro timestamp without time zone NOT NULL,
  CONSTRAINT tb_servicos_pk PRIMARY KEY (svc_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_servicos OWNER TO postgres;

-------------------------------------------------

CREATE TABLE tb_fornecedor_servico
(
  forsvc_id serial NOT NULL,
  forsvc_for_id integer NOT NULL,
  forsvc_svc_id integer NOT NULL,
  CONSTRAINT tb_fornecedor_servico_pk PRIMARY KEY (forsvc_id),
  CONSTRAINT forsvc_for_id_fk FOREIGN KEY (forsvc_for_id)
      REFERENCES tb_fornecedores (for_id) MATCH SIMPLE ON DELETE CASCADE,
  CONSTRAINT forsvc_svc_id_fk FOREIGN KEY (forsvc_svc_id)
      REFERENCES tb_servicos (svc_id) MATCH SIMPLE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_fornecedor_servico OWNER TO postgres;

----------------------------------

CREATE TABLE tb_produtos
(
  pdt_id serial NOT NULL,
  pdt_codigo character varying(10) NOT NULL,
  pdt_descricao character varying(100) NOT NULL,
  pdt_dt_cadastro timestamp without time zone NOT NULL,
  CONSTRAINT tb_produtos_pk PRIMARY KEY (pdt_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_produtos OWNER TO postgres;

--------------------------------------

CREATE TABLE tb_fornecedor_produto
(
  forpdt_id serial NOT NULL,
  forpdt_for_id integer NOT NULL,
  forpdt_pdt_id integer NOT NULL,
  CONSTRAINT tb_fornecedor_produto_pk PRIMARY KEY (forpdt_id),
  CONSTRAINT forpdt_for_id_fk FOREIGN KEY (forpdt_for_id)
      REFERENCES tb_fornecedores (for_id) MATCH SIMPLE ON DELETE CASCADE,
  CONSTRAINT forpdt_pdt_id_fk FOREIGN KEY (forpdt_pdt_id)
      REFERENCES tb_produtos (pdt_id) MATCH SIMPLE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_fornecedor_produto OWNER TO postgres;