package br.com.contabil.subscription.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContabilSubscriptionDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	
	@NotBlank(message = "insira um valor válido.")
	private String clerkId;
	
	@NotBlank(message = "insira um valor válido.")
	private String stripeCustomerId;
	
	@NotBlank(message = "insira um valor válido.")
	private String stripeSubscriptionId;
	
	@NotBlank(message = "insira um valor válido.")
	private String stripePriceId;
	
	@NotNull
	private Date stripeCurrentPeriodEnd;
	
	@NotBlank(message = "insira um valor válido.")
	private String plano;
}
