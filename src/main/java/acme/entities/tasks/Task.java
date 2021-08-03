package acme.entities.tasks;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Manager;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max=80)
	protected String title;
	
	@NotBlank
	@Length(max=500)
	protected String description;
	
	@Future
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date start;
	
	@Future
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date end;
	
	@URL
	protected String link;
	
	@NotNull
	protected Boolean publica;
	
	@NotNull
	protected Boolean finish;
	
	@Positive
	@NotNull
	protected Double workload;


		// Relationships ----------------------------------------------------------
	
		@NotNull
		@Valid
		@ManyToOne(optional = false)
		protected Manager manager;

}
