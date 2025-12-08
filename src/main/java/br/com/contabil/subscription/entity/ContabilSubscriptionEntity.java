package br.com.contabil.subscription.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "tab_user_subscription", schema = "contabil")
public class ContabilSubscriptionEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "clerk_id", nullable = false, unique = true)
	private String clerkId;
	
	@Column(name = "stripe_customer_id", nullable = false, unique = true)
	private String stripeCustomerId;
	
	@Column(name = "stripe_subscription_id", nullable = false, unique = true)
	private String stripeSubscriptionId;
	
	@Column(name = "stripe_price_id", nullable = false)
	private String stripePriceId;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "stripe_current_period_end", nullable = false)
	private Date stripeCurrentPeriodEnd;
	
	@Column(name = "plano", nullable = false)
	private String plano;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "dthr_create", nullable = false)
	private Date dthr_create;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "dthr_update", nullable = false)
	private Date dthr_update;
	
	@PrePersist
	public void prePersist() {
		Date date = new Date();
		this.dthr_create = date;
		this.dthr_update = date;
	}
	
	@PreUpdate
	public void preupdate() {
		this.dthr_update = new Date();
	}
}
