package acme.datatypes;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.Range;

import acme.framework.datatypes.DomainDatatype;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Workload extends DomainDatatype {
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------

		@Range(min=0,max=99)
		protected Integer			hours;
		
		@Range(min=0,max=59)
		protected Integer			minutes;

		// Object interface -------------------------------------------------------
		@Override
		public String toString() {
			StringBuilder result;
			
			result = new StringBuilder();
			result.append("<<");
			result.append(this.hours);
			if (this.minutes!=null) {
			result.append(".");
			if (this.minutes<10) result.append("0");
			result.append(this.minutes);
			}
			result.append(">>");
			return result.toString();
		}
		
		public Integer getTime() {
			return this.hours*60+this.minutes;
		}
}
