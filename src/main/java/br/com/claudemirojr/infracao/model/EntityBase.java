package br.com.claudemirojr.infracao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@MappedSuperclass
public class EntityBase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// -------AUDITORIA
	@Column(name = "created_by", nullable = true, updatable = false)
	@CreatedBy
	@JsonIgnore
	private String createdBy;

	@Column(name = "created_date", nullable = true, updatable = false)
	@CreatedDate
	@JsonIgnore
	private Date createdDate;

	@Column(name = "last_modified_by", nullable = true)
	@LastModifiedBy
	@JsonIgnore
	private String lastModifiedBy;

	@Column(name = "last_modified_date", nullable = true)
	@LastModifiedDate
	@JsonIgnore
	private Date lastModifiedDate;
	// -------AUDITORIA

}
