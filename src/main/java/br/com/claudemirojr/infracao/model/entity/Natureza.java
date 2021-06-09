package br.com.claudemirojr.infracao.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.claudemirojr.infracao.model.EntityBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "natureza")
@Audited
@AuditTable(value = "natureza_audit")
@AuditOverride(forClass = EntityBase.class)
@EntityListeners(AuditingEntityListener.class)

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class Natureza extends EntityBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length = 100)
	private String descricao;

	@Range(min = 1, max = 7)
	private Integer ponto;

	@Column(name = "valor_cheio")
	private Double valorCheio;

	@Column(name = "valor_desconto")
	private Double valorDesconto;

	@Column(name = "valor_sne")
	private Double valorSNE;

	@Column(name = "pode_converter_em_advertencia")
	private Boolean podeConverterEmAdvertencia;

	public void Atualizar(String descricao, Integer ponto, Double valorCheio, Double valorDesconto, Double valorSNE,
			Boolean podeConverterEmAdvertencia) {
		this.descricao = descricao;
		this.ponto = ponto;
		this.valorCheio = valorCheio;
		this.valorDesconto = valorDesconto;
		this.valorSNE = valorSNE;
		this.podeConverterEmAdvertencia = podeConverterEmAdvertencia;
	}

}
