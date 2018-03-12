package com.a9ski.ws;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.criteria.Path;

import com.a9ski.entities.Role;
import com.a9ski.entities.RoleFilter;
import com.a9ski.entities.Role_;
import com.a9ski.jpa.CriteriaApiObjects;
import com.a9ski.jpa.CriteriaBuilderHelper;
import com.a9ski.jpa.QueryConfig;
import com.a9ski.security.AccessChecker;
import com.a9ski.security.Permission;

@javax.ws.rs.Path("/role")
@Stateless
@LocalBean
@Named
public class RoleService extends AbstractCrudService<Role, RoleFilter> {

	public RoleService() {
		super(Role.class, RoleFilter.class, AccessChecker.permit(Permission.LIST_ROLES), AccessChecker.permit(Permission.EDIT_ROLE));
	}

	protected QueryConfig createPredicates(final CriteriaApiObjects<Role> cao, final RoleFilter filter) {
		final Path<Role> role = cao.getPath();
		final CriteriaBuilderHelper cbh = jpa.addAuditableEntityPredicates(cao.getCriteriaBuilder(), role, filter);

		if (filter != null) {
			cbh.add(role.get(Role_.name), filter.getName());
		}

		return new QueryConfig(cbh.getPredicates(), null, null, true);
	}

}
