package com.a9ski.ws;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.a9ski.entities.AuditableEntity;
import com.a9ski.entities.IdentifiableEntity;
import com.a9ski.entities.filters.PageableFilter;
import com.a9ski.exceptions.ObjectAlreadyModifiedException;
import com.a9ski.jpa.CriteriaApiObjects;
import com.a9ski.jpa.JpaUtils;
import com.a9ski.jpa.QueryConfig;

public abstract class AbstractCrudService<E extends IdentifiableEntity, F extends PageableFilter> extends AbstractService {

	@PersistenceContext(unitName = "jeeBase")
	protected EntityManager em;

	protected final JpaUtils jpa = new JpaUtils(() -> em);
	protected final Class<E> entityClass;
	protected final Class<F> filterClass;

	protected abstract QueryConfig createPredicates(final CriteriaApiObjects<E> cao, final F filter);

	protected AbstractCrudService(final Class<E> entityClass, final Class<F> filterClass) {
		super();
		this.entityClass = entityClass;
		this.filterClass = filterClass;
	}

	protected F getFilter(final F filter) {
		return getFilter(filter, filterClass);
	}

	@javax.ws.rs.Path("count")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public long count(@QueryParam("filter") final F filter) {
		return jpa.countEntities(getFilter(filter), this::createPredicates, entityClass);
	}

	@javax.ws.rs.Path("createFilter")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public F createFilter() {
		return getFilter(null);
	}

	@javax.ws.rs.Path("/list")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public List<E> list(@QueryParam("filter") final F filter) {
		return jpa.listEntities(getFilter(filter), this::createPredicates, entityClass);
	}

	@javax.ws.rs.Path("/save")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public E save(E entity) throws ObjectAlreadyModifiedException {
		if (entity instanceof AuditableEntity) {
			touch((AuditableEntity) entity);
		}
		return jpa.save(entity, true);
	}

	@javax.ws.rs.Path("/save")
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public E delete(E entity) throws ObjectAlreadyModifiedException {
		if (entity instanceof AuditableEntity) {
			final AuditableEntity auditableEntity = (AuditableEntity) entity;
			auditableEntity.setDeleted(true);
			touch(auditableEntity);
			return jpa.save(entity, true);
		} else {
			throw new UnsupportedOperationException();
		}

	}

}
