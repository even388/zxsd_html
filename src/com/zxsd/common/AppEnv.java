package com.zxsd.common;

import java.util.Properties;

public class AppEnv {

	private static AppEnv instance = null;

	private Properties props = null;

	protected AppEnv() {
		try {
			props = PropertyLoader.loadProperties("message.properties");
		} catch (Exception e) {
		}

		if (props == null) {
			try {
				props = PropertyLoader
						.loadProperties("./message.properties");
			} catch (Exception e) {
			}
		}
	}

	public static AppEnv getInstance() {
		if (instance == null) {
			instance = new AppEnv();
		}
		return instance;
	}

	public Object getProp(final String key) {
		if (props != null) {
			return props.get(key);
		} else {
			return null;
		}
	}

	public void setProp(final String key, final Object value) {
		if (props != null) {
			props.put(key, value);
		}
	}

	public String getStringProp(final String key, final String defaultValue) {
		String value = (String) getProp(key);

		if (value == null) {
			return defaultValue;
		} else {
			return value;
		}
	}

	public int getIntProp(final String key, final int defaultValue) {
		String value = (String) getProp(key);
		int intValue = defaultValue;

		if (value != null) {
			try {
				intValue = Integer.parseInt(value);
			} catch (Exception e) {
				intValue = defaultValue;
			}
		}

		return intValue;
	}

	public boolean getBoolProp(final String key, final boolean defaultValue) {
		String value = (String) getProp(key);
		boolean intValue = defaultValue;
		if (value != null) {
			try {
				intValue = Boolean.parseBoolean(value);
			} catch (Exception e) {
				intValue = defaultValue;
			}
		}

		return intValue;
	}

	public double getDoubleProp(final String key, final double defaultValue) {
		String value = (String) getProp(key);
		double doubleval = defaultValue;
		if (value != null) {
			try {
				doubleval = Double.parseDouble(value);
			} catch (Exception e) {
				doubleval = defaultValue;
			}
		}

		return doubleval;
	}

}
