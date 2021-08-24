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

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.datatypes.Workload;
import acme.entities.roles.Manag;
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
	
	
	protected Boolean publica;
	
	@Valid
	@NotNull
	protected Workload workload;
	
	// Derived attributes -----------------------------------------------------

	public Integer getTime(){
		return this.workload.getTime();
	}
	public boolean isPublishable() {
		boolean result;
		Date now;

		now = new Date();
		result = !this.publica && now.before(this.end);

		return result;
	}
	public boolean isFinished() {
		Date now;

		now = new Date();
		return this.end.before(now);
	}
		// Relationships ----------------------------------------------------------
	
		@NotNull
		@Valid
		@ManyToOne(optional = false)
		protected Manag manag;

}
