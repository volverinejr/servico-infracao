package br.com.claudemirojr.infracao.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.claudemirojr.infracao.dto.NaturezaDto;
import br.com.claudemirojr.infracao.dto.NaturezaResponseDto;
import br.com.claudemirojr.infracao.model.ParamsRequestModel;
import br.com.claudemirojr.infracao.model.service.NaturezaService;

@RestController
@RequestMapping("/natureza/v1")
public class NaturezaController {

	@Autowired
	private NaturezaService naturezaService;

	@PostMapping
	public ResponseEntity<NaturezaResponseDto> create(@RequestBody @Valid NaturezaDto naturezaDto) {
		NaturezaResponseDto novo = naturezaService.criar(naturezaDto);

		return new ResponseEntity<>(novo, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<NaturezaResponseDto> update(
			@PathVariable("id") Long id,
			@RequestBody @Valid NaturezaDto naturezaDto) {
		NaturezaResponseDto existe = naturezaService.atualizar(id, naturezaDto);

		return new ResponseEntity<>(existe, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		naturezaService.delete(id);

		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<NaturezaResponseDto> naturezas = naturezaService.findAll(prm);

		return ResponseEntity.ok(naturezas);
	}

	@GetMapping("/findAllIdMaiorIgual/{id}")
	public ResponseEntity<?> findAllIdMaiorIgual(
			@PathVariable Long id, 
			@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<NaturezaResponseDto> naturezas = naturezaService.findAllIdMaiorIgual(id, prm);

		return ResponseEntity.ok(naturezas);
	}

	@GetMapping("/findAllDescricaoContem/{descricao}")
	public ResponseEntity<?> findAllDescricaoContem(
			@PathVariable String descricao,
			@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<NaturezaResponseDto> naturezas = naturezaService.findAllDescricaoContem(descricao, prm);

		return ResponseEntity.ok(naturezas);
	}

	@GetMapping("/{id}")
	public NaturezaResponseDto findById(@PathVariable("id") Long id) {
		return naturezaService.findById(id);
	}

}
