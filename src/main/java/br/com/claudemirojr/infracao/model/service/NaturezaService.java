package br.com.claudemirojr.infracao.model.service;

import org.springframework.data.domain.Page;

import br.com.claudemirojr.infracao.dto.NaturezaDto;
import br.com.claudemirojr.infracao.dto.NaturezaResponseDto;
import br.com.claudemirojr.infracao.model.ParamsRequestModel;

public interface NaturezaService {

	public NaturezaResponseDto criar(NaturezaDto naturezaCriarDto);

	public NaturezaResponseDto atualizar(Long id, NaturezaDto naturezaCriarDto);

	public void delete(Long id);

	public Page<NaturezaResponseDto> findAll(ParamsRequestModel prm);

	public Page<NaturezaResponseDto> findAllIdMaiorIgual(Long id, ParamsRequestModel prm);

	public Page<NaturezaResponseDto> findAllDescricaoContem(String descricao, ParamsRequestModel prm);

	public NaturezaResponseDto findById(Long id);

}
