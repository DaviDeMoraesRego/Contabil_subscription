package br.com.contabil.subscription.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import br.com.contabil.subscription.adapter.ContabilSubscriptionAdapter;
import br.com.contabil.subscription.dto.ContabilSubscriptionDto;
import br.com.contabil.subscription.entity.ContabilSubscriptionEntity;
import br.com.contabil.subscription.exception.BadRequestException;
import br.com.contabil.subscription.exception.InternalServerError;
import br.com.contabil.subscription.exception.NotFoundException;
import br.com.contabil.subscription.repository.ContabilSubscriptionRepository;

@Service
public class ContabilSubscriptionService {

	@Autowired
	ContabilSubscriptionRepository repository;

	@Autowired
	ContabilSubscriptionAdapter adapter;

	public int create(ContabilSubscriptionDto dto) throws Exception {
		int id = 0;
		try {
			ContabilSubscriptionEntity entity = adapter.adapterDtoToEntity(dto, id);
			id = repository.save(entity).getId();
		} catch (IllegalArgumentException | OptimisticLockingFailureException e) {
			throw new BadRequestException(e.getMessage());
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
		return id;
	}

	public List<ContabilSubscriptionDto> findAll() throws Exception {
		List<ContabilSubscriptionDto> dtos = new ArrayList<>();
		try {
			List<ContabilSubscriptionEntity> entities = repository.findAll();
			notFoundChecker(entities.size());
			entities.forEach(entity -> dtos.add(adapter.adapterEntityToDto(entity)));
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
		return dtos;
	}

	public ContabilSubscriptionDto findById(int id) throws Exception {
		ContabilSubscriptionDto dto;
		try {
			ContabilSubscriptionEntity entity = repository.findById(id)
					.orElseThrow(() -> new NotFoundException("Nenhum registro encontrado."));
			dto = adapter.adapterEntityToDto(entity);
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
		return dto;
	}

	public String update(ContabilSubscriptionDto dto, String id) throws Exception {
		try {

			ContabilSubscriptionEntity entity = repository.findByClerkId(id);
			entity.setStripePriceId(dto.getStripePriceId());
			entity.setStripeCurrentPeriodEnd(dto.getStripeCurrentPeriodEnd());

			repository.save(entity);
		} catch (IllegalArgumentException | OptimisticLockingFailureException e) {
			throw new BadRequestException(e.getMessage());
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}

		return "Alterações realizadas com êxito.";
	}

	public String delete(int id) throws Exception {
		try {
			repository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new BadRequestException(e.getMessage());
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
		return "Dados excluídos com êxito.";
	}
	
	public ContabilSubscriptionDto findByClerkId(String clerkId) throws Exception {
		ContabilSubscriptionDto dto;
		int hasEntity = 0;
		try {
			ContabilSubscriptionEntity entity = repository.findByClerkId(clerkId);
			if(entity != null ) {
				hasEntity = 1;
			}
			notFoundChecker(hasEntity);
			dto = adapter.adapterEntityToDto(entity);
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
		return dto;
	}

	private static void notFoundChecker(int paramForCheck) throws NotFoundException {
		if (paramForCheck == 0) {
			throw new NotFoundException("Nenhum registro encontrado.");
		}
	}
}