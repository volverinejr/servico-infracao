package br.com.claudemirojr.infracao.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.claudemirojr.infracao.model.entity.Natureza;

public interface NaturezaRepository extends JpaRepository<Natureza, Long> {

	Page<Natureza> findByIdGreaterThanEqual(Long id, Pageable pageable);

	Page<Natureza> findByDescricaoIgnoreCaseContaining(String descricao, Pageable pageable);

}
