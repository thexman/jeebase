package com.a9ski.ws;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Path;

import com.a9ski.entities.User;
import com.a9ski.entities.UserFilter;
import com.a9ski.entities.User_;
import com.a9ski.jpa.CriteriaApiObjects;
import com.a9ski.jpa.CriteriaBuilderHelper;
import com.a9ski.jpa.JpaUtils;
import com.a9ski.jpa.QueryConfig;

@javax.ws.rs.Path("/user")
@Stateless
@LocalBean
@Named
public class UserService extends AbstractService {

	@PersistenceContext(unitName = "jeeBase")
	private EntityManager em;

	private final JpaUtils jpa = new JpaUtils(this::getEntityManager);

	private EntityManager getEntityManager() {
		return em;
	}

	public long countUsers(final UserFilter filter) {
		return jpa.countEntities(filter, this::createUserPredicates, User.class);
	}

	public List<User> listUsers(final UserFilter filter) {
		return jpa.listEntities(filter, this::createUserPredicates, User.class);
	}

	private QueryConfig createUserPredicates(final CriteriaApiObjects<User> cao, final UserFilter filter) {
		final Path<User> user = cao.getPath();
		final CriteriaBuilderHelper cbh = jpa.addAuditableEntityPredicates(cao.getCriteriaBuilder(), user, filter);
		cbh.add(user.get(User_.login), filter.getLogin());
		cbh.add(user.get(User_.firstName), filter.getFirstName());
		cbh.add(user.get(User_.lastName), filter.getLastName());
		cbh.add(user.get(User_.email), filter.getEmail());
		return new QueryConfig(cbh.getPredicates(), null, null, true);
	}

}
