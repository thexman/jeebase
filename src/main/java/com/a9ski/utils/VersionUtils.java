package com.a9ski.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VersionUtils {

	public static final Logger LOGGER = LoggerFactory.getLogger(VersionUtils.class);

	private static final Properties props = new Properties();

	public static final String DEFAULT_PROPS = "META-INF/buildinfo.properties";

	static {
		try {
			loadDefaultProps();
		} catch (final IOException ex) {
			// ignore
		}
	}

	protected VersionUtils() {
		super();
	}

	public static void loadProps(InputStream is) throws IOException {
		if (is != null) {
			props.load(new InputStreamReader(is, "UTF-8"));
		}
	}

	public static void loadDefaultProps() throws IOException {
		try (final InputStream is = FileUtils.getResourceAsStream(VersionUtils.class, DEFAULT_PROPS)) {
			loadProps(is);
		}
	}

	public static String getProperty(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}

	public static String getVersion() {
		return getProperty("version", "0.0");
	}

	public static String getCommitId() {
		return getProperty("commitId", "0000000000000000000000000000000000000000");
	}

	public static String getBuildTime() {
		return getProperty("buildTime", "1970-01-01T00:00:00Z");
	}

	public static String getBuildId() {
		return getProperty("buildId", "");
	}

}
