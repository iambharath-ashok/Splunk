/**
 * 
 */
package com.qwest.epwf.common.utils.logger;

import com.qwest.epwf.common.utils.logger.LoggerOptions.FORMAT;
import com.qwest.epwf.common.utils.logger.LoggerOptions.IDENTIFIER_TYPE;
import com.qwest.epwf.common.utils.logger.LoggerOptions.LEVEL;
import com.qwest.epwf.common.utils.logger.LoggerOptions.STATUS;
import com.qwest.epwf.common.utils.logger.LoggerOptions.TRANSACTION_API;
import com.qwest.epwf.common.utils.logger.LoggerOptions.TRANSACTION_TYPE;

/**
 * @author AB40286
 * 
 */
public interface Logger<T> {


	/**
	 * @param level
	 * @param inputFormat
	 * @param outputFormat
	 * @param src
	 * @param dest
	 * @param identifierType
	 * @param identifierValue
	 * @param transactonType
	 * @param tranApi
	 * @param transactionValue
	 * @param status
	 * @param statusMsg
	 * @param msgs
	 * @return
	 */
	Object log(LEVEL level, FORMAT inputFormat, FORMAT outputFormat, String src, LoggerOptions.SYSTEM dest,
			IDENTIFIER_TYPE identifierType, String identifierValue,
			TRANSACTION_TYPE transactonType, TRANSACTION_API tranApi,
			T transactionValue, STATUS status, String statusMsg, String...msgs);
	
}
