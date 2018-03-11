package com.a9ski.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TreeSet;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.a9ski.entities.UserFilter;
import com.a9ski.entities.filters.FilterStringField;
import com.a9ski.entities.filters.Matching;
import com.a9ski.utils.DateRange;
import com.a9ski.utils.Range;

public class JsonMessageBodyWriterTest {

	@Test
	public void testWriteTo() throws Exception {
		final JsonMessageBodyWriter w = new JsonMessageBodyWriter();
		final UserFilter userFilter = new UserFilter();
		userFilter.setLocale(Locale.US);
		userFilter.setLogin(new FilterStringField("User", Matching.ENDS_WITH));
		userFilter.setVersion(Range.newLongRange().addStart(0L).addEnd(42L));
		userFilter.setEdited(new DateRange(new Date(10000000000L), new Date()));
		userFilter.setIds(new TreeSet<>(Arrays.asList(1L, 2L, 42L)));
		userFilter.setDeleted(false);
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		w.writeTo(userFilter, UserFilter.class, UserFilter.class, null, MediaType.APPLICATION_JSON_TYPE, null, bos);
		String s = new String(bos.toByteArray());
		System.out.println(s);
		assertNotNull(s);

		@SuppressWarnings({ "unchecked", "rawtypes" })
		final UserFilter parsed = (UserFilter) w.readFrom((Class) UserFilter.class, (Type) UserFilter.class, null, MediaType.APPLICATION_JSON_TYPE, null, new ByteArrayInputStream(s.getBytes()));
		assertEquals(userFilter, parsed);
	}

}
