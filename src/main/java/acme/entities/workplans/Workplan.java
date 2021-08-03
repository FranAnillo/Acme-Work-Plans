package acme.entities.workplans;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Workplan extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------
	
	@NotNull
	protected String title;
	
	@Future
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date start;
	
	@Future
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date end;


	@NotNull                   
	protected Double workload;
	
	@NotNull
	protected Boolean publica;
	
	@NotNull
	protected Boolean finish;
	
	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Manager manager;
	
	@Valid
	@ManyToMany
	protected List<Task> task;
}
