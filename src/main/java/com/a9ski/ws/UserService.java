package com.a9ski.ws;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.criteria.Path;

import com.a9ski.entities.User;
import com.a9ski.entities.UserFilter;
import com.a9ski.entities.User_;
import com.a9ski.jpa.CriteriaApiObjects;
import com.a9ski.jpa.CriteriaBuilderHelper;
import com.a9ski.jpa.QueryConfig;
import com.a9ski.security.AccessChecker;
import com.a9ski.security.Permission;

@javax.ws.rs.Path("/user")
@Stateless
@LocalBean
@Named
public class UserService extends AbstractCrudService<User, UserFilter> {

	public UserService() {
		super(User.class, UserFilter.class, AccessChecker.permit(Permission.LIST_USERS), AccessChecker.permit(Permission.EDIT_USER));
	}

	@Override
	protected QueryConfig createPredicates(final CriteriaApiObjects<User> cao, final UserFilter filter) {
		final Path<User> user = cao.getPath();
		final CriteriaBuilderHelper cbh = jpa.addAuditableEntityPredicates(cao.getCriteriaBuilder(), user, filter);

		if (filter != null) {
			cbh.add(user.get(User_.login), filter.getLogin());
			cbh.add(user.get(User_.firstName), filter.getFirstName());
			cbh.add(user.get(User_.lastName), filter.getLastName());
			cbh.add(user.get(User_.email), filter.getEmail());
		}

		return new QueryConfig(cbh.getPredicates(), null, null, true);
	}
}
