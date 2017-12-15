/**
 * 
 */
package com.qwest.epwf.common.utils.logger;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import com.qwest.epwf.common.utils.logger.LoggerOptions.DEFAULT_LOGGER_FORMAT;
import com.qwest.epwf.common.utils.logger.LoggerOptions.EXCEPTION;
import com.qwest.epwf.common.utils.logger.LoggerOptions.EXCEPTION_FORMAT;
import com.qwest.epwf.common.utils.logger.LoggerOptions.FORMAT;
import com.qwest.epwf.common.utils.logger.LoggerOptions.MODE;
import com.qwest.epwf.common.utils.logger.LoggerOptions.THREAD_POOL_TYPE;

/**
 * @author AB40286
 * 
 */
public class LoggerConfig {

	private Properties properties = null;
	private static LoggerConfig instance = null;
	private static final String APP_TRANS_LOGGER_PROPERTIES_FILE_NAME = "TransactionsLogger.properties";

	private LoggerConfig() {
		InputStream inputStream = null;
		this.properties = new Properties();
		try {
			inputStream = this.getClass().getClassLoader().getResourceAsStream(
					APP_TRANS_LOGGER_PROPERTIES_FILE_NAME);
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file '"
						+ APP_TRANS_LOGGER_PROPERTIES_FILE_NAME
						+ "' not found in the classpath");
			}
		} catch (Exception ex) {
			throw new TransactionLoggerException("Problem in loading the "
					+ APP_TRANS_LOGGER_PROPERTIES_FILE_NAME + " file", ex);
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (Exception ex) {
					throw new TransactionLoggerException(
							"Problem in Closing the InputStream for loading the "
									+ APP_TRANS_LOGGER_PROPERTIES_FILE_NAME
									+ " file on start up of server", ex);
				}
		}
	}

	private synchronized static void createInstance() {
		if (instance == null) {
			instance = new LoggerConfig();
		}
	}

	public static LoggerConfig getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	/**
	 * @param propertyConstant
	 * @return
	 */
	public String getProperty(PropertyConstants propertyConstant) {
		String validatedLoggerConstantValue = null;
		try {
			switch (propertyConstant) {

			case LOGGER_ENABLE:
				validatedLoggerConstantValue = this.properties
						.getProperty(propertyConstant.getLoggerPropertyName());
				break;

			case LOGGER_FORMAT:
				validatedLoggerConstantValue = this.properties
						.getProperty(propertyConstant.getLoggerPropertyName());
				if (validatedLoggerConstantValue != null
						&& validatedLoggerConstantValue != ""
						&& !"".equalsIgnoreCase(validatedLoggerConstantValue)) {
					validatedLoggerConstantValue.trim().replaceAll(" ", "").replaceAll("\n", "")
							.trim();
					for (FORMAT format : FORMAT.values()) {
						if (format.name().equalsIgnoreCase(
								validatedLoggerConstantValue)) {
							validatedLoggerConstantValue = FORMAT.valueOf(
									validatedLoggerConstantValue).name();
							break;
						} else continue;
					}
				} else validatedLoggerConstantValue = null;
				break;

			case LOGGER_MODE:
				validatedLoggerConstantValue = this.properties
						.getProperty(propertyConstant.getLoggerPropertyName());
				if (validatedLoggerConstantValue != null
						&& validatedLoggerConstantValue != ""
						&& !"".equalsIgnoreCase(validatedLoggerConstantValue)) {
					validatedLoggerConstantValue.trim().replaceAll(" ", "").replaceAll("\n", "")
							.trim();
					for (MODE mode : MODE.values()) {
						if (mode.name().equalsIgnoreCase(
								validatedLoggerConstantValue)) {
							validatedLoggerConstantValue = MODE.valueOf(
									validatedLoggerConstantValue).name();
							break;
						} else continue;
					}
				} else validatedLoggerConstantValue = null;
				break;

			case LOGGER_EXCEPTION:
				validatedLoggerConstantValue = this.properties
						.getProperty(propertyConstant.getLoggerPropertyName());
				if (validatedLoggerConstantValue != null
						&& validatedLoggerConstantValue != ""
						&& !"".equalsIgnoreCase(validatedLoggerConstantValue)) {
					validatedLoggerConstantValue.trim().replaceAll(" ", "").replaceAll("\n", "")
							.trim();
					for (EXCEPTION exception : EXCEPTION.values()) {
						if (exception.name().equalsIgnoreCase(
								validatedLoggerConstantValue)) {
							validatedLoggerConstantValue = EXCEPTION.valueOf(
									validatedLoggerConstantValue).name();
							break;
						} else continue;
				}
			} else validatedLoggerConstantValue = null;
			break;

			case LOGGER_THREADPOOL_TYPE:
				validatedLoggerConstantValue = this.properties
						.getProperty(propertyConstant.getLoggerPropertyName());
				if (validatedLoggerConstantValue != null
						&& validatedLoggerConstantValue != ""
						&& !"".equalsIgnoreCase(validatedLoggerConstantValue)) {
					validatedLoggerConstantValue.trim().replaceAll(" ", "").replaceAll("\n", "")
							.trim();
					for (THREAD_POOL_TYPE threadPoolType : THREAD_POOL_TYPE
							.values()) {
						if (threadPoolType.name().equalsIgnoreCase(
								validatedLoggerConstantValue)) {
							validatedLoggerConstantValue = THREAD_POOL_TYPE
									.valueOf(validatedLoggerConstantValue)
									.name();
							break;
						} else continue;
					}
				} else validatedLoggerConstantValue = null;
				break;

			case LOGGER_THREADPOOL_SIZE:
				validatedLoggerConstantValue = this.properties
						.getProperty(propertyConstant.getLoggerPropertyName());
				break;

			case LOGGER_INPUT_FORMAT:
				validatedLoggerConstantValue = this.properties
						.getProperty(propertyConstant.getLoggerPropertyName());
				if (validatedLoggerConstantValue != null
						&& validatedLoggerConstantValue != ""
						&& !"".equalsIgnoreCase(validatedLoggerConstantValue)) {
					validatedLoggerConstantValue.trim().replaceAll(" ", "").replaceAll("\n", "")
							.trim();
					for (FORMAT inputFormat : FORMAT.values()) {
						if (inputFormat.name().equalsIgnoreCase(
								validatedLoggerConstantValue)) {
							validatedLoggerConstantValue = FORMAT.valueOf(
									validatedLoggerConstantValue).name();
							break;
						} else continue;
					}
				} else validatedLoggerConstantValue = null;
				break;

			case LOGGER_OUTPUT_FORMAT:
				validatedLoggerConstantValue = this.properties
						.getProperty(propertyConstant.getLoggerPropertyName());
				if (validatedLoggerConstantValue != null
						&& validatedLoggerConstantValue != ""
						&& !"".equalsIgnoreCase(validatedLoggerConstantValue)) {
					validatedLoggerConstantValue.trim().replaceAll(" ", "").replaceAll("\n", "")
							.trim();
					for (FORMAT outputFormat : FORMAT.values()) {
						if (outputFormat.name().equalsIgnoreCase(
								validatedLoggerConstantValue)) {
							validatedLoggerConstantValue = FORMAT.valueOf(
									validatedLoggerConstantValue).name();
							break;
						} else continue;
					}
				} else validatedLoggerConstantValue = null;
				break;

			case LOGGER_EXCEPTION_FORMAT:
				validatedLoggerConstantValue = this.properties
						.getProperty(propertyConstant.getLoggerPropertyName());
				if (validatedLoggerConstantValue != null
						&& validatedLoggerConstantValue != ""
						&& !"".equalsIgnoreCase(validatedLoggerConstantValue)) {
					validatedLoggerConstantValue.trim().replaceAll(" ", "").replaceAll("\n", "")
							.trim();
					for (EXCEPTION_FORMAT exceptionFormat : EXCEPTION_FORMAT
							.values()) {
						if (exceptionFormat.name().equalsIgnoreCase(
								validatedLoggerConstantValue)) {
							validatedLoggerConstantValue = EXCEPTION_FORMAT
									.valueOf(validatedLoggerConstantValue)
									.name();
							break;
						} else continue;
					}
				} else validatedLoggerConstantValue = null;
				break;

			case LOGGER_DEFAULT_FORMAT:
				validatedLoggerConstantValue = this.properties
						.getProperty(propertyConstant.getLoggerPropertyName());
				if (validatedLoggerConstantValue != null
						&& validatedLoggerConstantValue != ""
						&& !"".equalsIgnoreCase(validatedLoggerConstantValue)) {
					validatedLoggerConstantValue.trim().replaceAll(" ", "").replaceAll("\n", "")
							.trim();
					for (DEFAULT_LOGGER_FORMAT defaultLoggerFormat : DEFAULT_LOGGER_FORMAT
							.values()) {
						if (defaultLoggerFormat.name().equalsIgnoreCase(
								validatedLoggerConstantValue)) {
							validatedLoggerConstantValue = DEFAULT_LOGGER_FORMAT
									.valueOf(validatedLoggerConstantValue)
									.name();
							break;
						} else continue;
					}
				} else validatedLoggerConstantValue = null;
				break;

			default:
				throw new TransactionLoggerException(
						"Invalid PropertyConstants " + propertyConstant
								+ " with value "
								+ propertyConstant.getLoggerPropertyName());
			}
		} catch (Exception e) {}
		return validatedLoggerConstantValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public enum PropertyConstants {
		LOGGER_ENABLE("logger.enable"), LOGGER_FORMAT("logger.format"), LOGGER_MODE(
				"logger.mode"), LOGGER_EXCEPTION("logger.exception"), LOGGER_THREADPOOL_TYPE(
				"logger.threadpool.type"), LOGGER_THREADPOOL_SIZE(
				"logger.threadpool.size"), LOGGER_INPUT_FORMAT(
				"logger.params.inputFormat"), LOGGER_OUTPUT_FORMAT(
				"logger.params.outputFormat"), LOGGER_EXCEPTION_FORMAT(
				"logger.params.exceptionFormat"), LOGGER_DEFAULT_FORMAT(
				"logger.params.defaultFormat");
		private String loggerPropertyName;

		private PropertyConstants(String loggerPropertyName) {
			this.setLoggerPropertyName(loggerPropertyName);
		}

		public void setLoggerPropertyName(String loggerPropertyName) {
			this.loggerPropertyName = loggerPropertyName;
		}

		public String getLoggerPropertyName() {
			return loggerPropertyName;
		}
	}
}
