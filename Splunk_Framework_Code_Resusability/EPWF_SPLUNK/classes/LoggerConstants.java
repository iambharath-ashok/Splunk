/**
 *
 */
package com.qwest.epwf.common.utils.logger;


import com.qwest.epwf.common.utils.logger.LoggerConfig.PropertyConstants;
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
public interface LoggerConstants {

	Boolean LOGGER_ENABLE = new Boolean(LoggerConfig.getInstance().getProperty(PropertyConstants.LOGGER_ENABLE));
	String LOGGER_FORMAT = LoggerConfig.getInstance().getProperty(PropertyConstants.LOGGER_FORMAT);
	String LOGGER_MODE = LoggerConfig.getInstance().getProperty(PropertyConstants.LOGGER_MODE);
	String LOGGER_EXCEPTION = LoggerConfig.getInstance().getProperty(PropertyConstants.LOGGER_EXCEPTION);
	String LOGGER_THREADPOOL_TYPE = LoggerConfig.getInstance().getProperty(PropertyConstants.LOGGER_THREADPOOL_TYPE);
	Integer LOGGER_THREADPOOL_SIZE = Integer.valueOf(LoggerConfig.getInstance().getProperty(PropertyConstants.LOGGER_THREADPOOL_SIZE));
	String LOGGER_INPUT_FORMAT = LoggerConfig.getInstance().getProperty(PropertyConstants.LOGGER_INPUT_FORMAT);
	String LOGGER_OUTPUT_FORMAT = LoggerConfig.getInstance().getProperty(PropertyConstants.LOGGER_OUTPUT_FORMAT);
	String LOGGER_EXCEPTION_FORMAT = LoggerConfig.getInstance().getProperty(PropertyConstants.LOGGER_EXCEPTION_FORMAT);
	String LOGGER_DEFAULT_FORMAT= LoggerConfig.getInstance().getProperty(PropertyConstants.LOGGER_DEFAULT_FORMAT);
}
