/**
 *
 */
package com.qwest.epwf.common.utils.logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qwest.mdw.common.utilities.startup.StartupClass;

/**
 * @author AB40286
 *
 */
public class EPWFTransactionLoggerRegistration implements StartupClass {

	private static final long serialVersionUID = -837745369781363681L;
	private static Log logger = LogFactory.getLog(EPWFTransactionLoggerRegistration.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qwest.mdw.common.utilities.startup.StartupClass#onShutdown()
	 */
	@Override
	public void onShutdown() {
		logger.info("(+)EPWFTransactionLoggerRegistration onShutdown()");
		logger.info("(-)EPWFTransactionLoggerRegistration onShutdown()");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qwest.mdw.common.utilities.startup.StartupClass#onStartup()
	 */
	@Override
	public void onStartup() {
		logger.info("(+)EPWFTransactionLoggerRegistration onStartup()");
		try {
			LoggerConfig.getInstance();
			Class.forName("com.qwest.epwf.common.utils.logger.LoggerConstants",true,
			Class.forName("com.qwest.epwf.common.utils.logger.LoggerConstants").getClassLoader());
			if(LoggerConstants.LOGGER_THREADPOOL_TYPE!=null || LoggerConstants.LOGGER_THREADPOOL_SIZE!=null) {
				LoggerUtil.init(LoggerConstants.LOGGER_THREADPOOL_TYPE, LoggerConstants.LOGGER_THREADPOOL_SIZE);
				logger.info("LOGGER_THREAD_POOL_TYPE : "+LoggerConstants.LOGGER_THREADPOOL_TYPE);
				logger.info("LOGGER_THREAD_POOL_SIZE : "+LoggerConstants.LOGGER_THREADPOOL_SIZE);
			}
			logger.info("(-)EPWFTransactionLoggerRegistration onStartup()");
		} catch (TransactionLoggerException e) {
			LoggerExceptionUtil.log(e);
		} catch (ClassNotFoundException e) {
			LoggerExceptionUtil.log(e);
		} catch (Exception e) {
			LoggerExceptionUtil.log(e);
		}
	}
}
