package br.com.contabil.subscription.adapter;

import org.springframework.stereotype.Component;

import br.com.contabil.subscription.dto.ContabilSubscriptionDto;
import br.com.contabil.subscription.entity.ContabilSubscriptionEntity;

@Component
public class ContabilSubscriptionAdapter {

	public ContabilSubscriptionEntity adapterDtoToEntity (ContabilSubscriptionDto dto, int id) {
		ContabilSubscriptionEntity entity = new ContabilSubscriptionEntity();
		
		entity.setId(id);
		entity.setClerkId(dto.getClerkId());
		entity.setStripeCustomerId(dto.getStripeCustomerId());
		entity.setStripeSubscriptionId(dto.getStripeSubscriptionId());
		entity.setStripePriceId(dto.getStripePriceId());
		entity.setStripeCurrentPeriodEnd(dto.getStripeCurrentPeriodEnd());
		entity.setPlano(dto.getPlano());
		
		return entity;
	}
	
	public ContabilSubscriptionDto adapterEntityToDto (ContabilSubscriptionEntity entity) {
		ContabilSubscriptionDto dto = new ContabilSubscriptionDto();
		
		dto.setId(entity.getId());
		dto.setClerkId(entity.getClerkId());
		dto.setStripeCustomerId(entity.getStripeCustomerId());
		dto.setStripeSubscriptionId(entity.getStripeSubscriptionId());
		dto.setStripePriceId(entity.getStripePriceId());
		dto.setStripeCurrentPeriodEnd(entity.getStripeCurrentPeriodEnd());
		dto.setPlano(entity.getPlano());
		
		return dto;
	}
}
