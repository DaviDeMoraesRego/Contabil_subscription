package br.com.contabil.subscription.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.contabil.subscription.dto.ContabilSubscriptionDto;
import br.com.contabil.subscription.dto.ResponseDto;
import br.com.contabil.subscription.service.ContabilSubscriptionService;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/contabil-subscription/v1")
public class ContabilSubscriptionController {
	
	@Autowired
	ContabilSubscriptionService service;

	@PostMapping
	public ResponseEntity<ResponseDto<Integer>> create(@RequestBody @Valid ContabilSubscriptionDto dto) throws Exception {
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(new ResponseDto<Integer>(service.create(dto), null));
	}

	@GetMapping
	public ResponseEntity<ResponseDto<List<ContabilSubscriptionDto>>> findAll() throws Exception {
			return ResponseEntity
			.ok(new ResponseDto<List<ContabilSubscriptionDto>>(service.findAll(), null));
	}
	
	@GetMapping("/{clerkId}/")
	public ResponseEntity<ResponseDto<ContabilSubscriptionDto>> findByClerkId (@PathVariable("clerkId") String clerkId) throws Exception {
		return ResponseEntity
			.ok(new ResponseDto<ContabilSubscriptionDto>(service.findByClerkId(clerkId), null));
	}
	
	@PutMapping("/{id}/")
	public ResponseEntity<ResponseDto<String>> update(@RequestBody @Valid ContabilSubscriptionDto dto, @PathVariable("id") String id) throws Exception {		
		return ResponseEntity
			.ok(new ResponseDto<String>(service.update(dto, id), null));
	}
	
	@DeleteMapping("/{id}/")
	public ResponseEntity<ResponseDto<String>> delete(@PathVariable("id") Integer id) throws Exception {
		return ResponseEntity
			.ok(new ResponseDto<String>(service.delete(id), null));
	}
}
