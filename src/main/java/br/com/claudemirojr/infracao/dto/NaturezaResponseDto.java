package br.com.claudemirojr.infracao.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NaturezaResponseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String descricao;

	private Integer ponto;

	private Double valorCheio;

	private Double valorDesconto;

	private Double valorSNE;

	private Boolean podeConverterEmAdvertencia;

}
