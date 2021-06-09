create table natureza (
	id bigint not null auto_increment,

	descricao varchar(100),
	ponto integer,
	valor_cheio double precision,
	valor_desconto double precision,
	valor_sne double precision,
	pode_converter_em_advertencia bit,

	created_by varchar(255),
	created_date datetime(6),
	last_modified_by varchar(255),
	last_modified_date datetime(6),
	
	primary key (id)
)
