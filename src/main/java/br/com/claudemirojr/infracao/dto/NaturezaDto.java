package br.com.claudemirojr.infracao.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NaturezaDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Length(min = 4, max = 100)
	private String descricao;

	@Range(min = 3, max = 7)
	private Integer ponto;

	@Range(min = 44, max = 1200)
	private Double valorCheio;

	@Range(min = 44, max = 1200)
	private Double valorDesconto;

	@Range(min = 44, max = 1200)
	private Double valorSNE;

	@NotNull
	private Boolean podeConverterEmAdvertencia;

}
