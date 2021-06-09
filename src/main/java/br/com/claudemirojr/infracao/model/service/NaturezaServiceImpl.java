package br.com.claudemirojr.infracao.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.claudemirojr.infracao.converter.DozerConverter;
import br.com.claudemirojr.infracao.dto.NaturezaDto;
import br.com.claudemirojr.infracao.dto.NaturezaResponseDto;
import br.com.claudemirojr.infracao.exception.ResourceServiceValidationException;
import br.com.claudemirojr.infracao.exception.ResourceNotFoundException;
import br.com.claudemirojr.infracao.model.ParamsRequestModel;
import br.com.claudemirojr.infracao.model.entity.Natureza;
import br.com.claudemirojr.infracao.model.repository.NaturezaRepository;

@Service
public class NaturezaServiceImpl implements NaturezaService {

	@Autowired
	private NaturezaRepository naturezaRepository;

	private NaturezaResponseDto convertToNaturezaResponseDto(Natureza entity) {
		return DozerConverter.parseObject(entity, NaturezaResponseDto.class);
	}

	private void validarValor(Natureza natureza) {
		if (natureza.getValorDesconto() >= natureza.getValorCheio()) {
			throw new ResourceServiceValidationException("ValorDesconto;Valor Desconto maior que Valor Cheio");
		}

		if (natureza.getValorSNE() >= natureza.getValorDesconto()) {
			throw new ResourceServiceValidationException("ValorSNE;Valor SNE maior que Valor Desconto");
		}
	}

	@Override
	@Transactional(readOnly = false)
	public NaturezaResponseDto criar(NaturezaDto naturezaCriarDto) {
		var entity = DozerConverter.parseObject(naturezaCriarDto, Natureza.class);

		this.validarValor(entity);

		var natureza = naturezaRepository.save(entity);

		return DozerConverter.parseObject(natureza, NaturezaResponseDto.class);
	}

	@Override
	@Transactional(readOnly = false)
	public NaturezaResponseDto atualizar(Long id, NaturezaDto naturezaCriarDto) {
		var entity = naturezaRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Natureza não encontrado para id %d", id)));

		this.validarValor(entity);

		entity.Atualizar(naturezaCriarDto.getDescricao(), naturezaCriarDto.getPonto(), naturezaCriarDto.getValorCheio(),
				naturezaCriarDto.getValorDesconto(), naturezaCriarDto.getValorSNE(),
				naturezaCriarDto.getPodeConverterEmAdvertencia());

		var natureza = naturezaRepository.save(entity);

		return DozerConverter.parseObject(natureza, NaturezaResponseDto.class);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Long id) {
		var entity = naturezaRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Natureza não encontrado para id %d", id)));

		naturezaRepository.delete(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<NaturezaResponseDto> findAll(ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		var page = naturezaRepository.findAll(pageable);

		return page.map(this::convertToNaturezaResponseDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<NaturezaResponseDto> findAllIdMaiorIgual(Long id, ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		var page = naturezaRepository.findByIdGreaterThanEqual(id, pageable);

		return page.map(this::convertToNaturezaResponseDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<NaturezaResponseDto> findAllDescricaoContem(String descricao, ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		var page = naturezaRepository.findByDescricaoIgnoreCaseContaining(descricao, pageable);

		return page.map(this::convertToNaturezaResponseDto);
	}

	@Override
	@Transactional(readOnly = true)
	public NaturezaResponseDto findById(Long id) {
		var entity = naturezaRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Natureza não encontrado para id %d", id)));

		return DozerConverter.parseObject(entity, NaturezaResponseDto.class);
	}

}
