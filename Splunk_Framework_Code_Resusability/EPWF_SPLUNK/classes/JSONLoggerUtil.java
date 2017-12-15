/**
 * 
 */
package com.qwest.epwf.common.utils.logger;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.qwest.epwf.common.utils.logger.LoggerOptions.FORMAT;
import com.qwest.epwf.common.utils.logger.LoggerOptions.IDENTIFIER_TYPE;
import com.qwest.epwf.common.utils.logger.LoggerOptions.MODE;
import com.qwest.epwf.common.utils.logger.LoggerOptions.STATUS;
import com.qwest.epwf.common.utils.logger.LoggerOptions.SYSTEM;
import com.qwest.epwf.common.utils.logger.LoggerOptions.TRANSACTION_API;
import com.qwest.epwf.common.utils.logger.LoggerOptions.TRANSACTION_TYPE;

/**
 * @author AB40286
 * 
 */
public class JSONLoggerUtil<T> extends LoggerUtil<T> {

	/**
	 * @param clazz
	 * @param mode
	 */
	public JSONLoggerUtil(Class<?> clazz, MODE mode) {
		super(clazz, mode,FORMAT.JSON);
	}

	@Override
	protected Object getTransVal(FORMAT inputFormat, FORMAT outputFormat, T transactionValue) {
		Object transVal=null;
		try {
			if(inputFormat==FORMAT.XML && outputFormat==FORMAT.XML) {
				transVal=LoggerConverter.XMLToXMLObject(transactionValue!=null?transactionValue.toString():"");
			} else if(inputFormat==FORMAT.XML && outputFormat==FORMAT.JSON) {
				transVal=LoggerConverter.XMLToJSONObject(transactionValue!=null?transactionValue.toString():"");
			} else if(inputFormat==FORMAT.JSON && outputFormat==FORMAT.JSON) {
				transVal=LoggerConverter.JSONToJSONObject(transactionValue!=null?transactionValue.toString():"");
			} else if(inputFormat==FORMAT.JSON && outputFormat==FORMAT.XML) {
				transVal=LoggerConverter.JSONToXMLObject(transactionValue!=null?transactionValue.toString():"");
			} else if(inputFormat==FORMAT.OBJECT && outputFormat==FORMAT.OBJECT) {
				transVal=transactionValue!=null?transactionValue:"";
			} else if(inputFormat==FORMAT.STRING && outputFormat==FORMAT.STRING) {
				transVal=transactionValue!=null?transactionValue.toString():"";
			} else if (inputFormat == null || outputFormat == null) {
				transVal="";
			} else if ((inputFormat != null && "".equalsIgnoreCase(inputFormat.name()))
					|| (outputFormat != null && "".equalsIgnoreCase(outputFormat.name()))) {
				transVal = "";
			}
		} catch ( Exception e) {
			transVal="";
			throw new TransactionLoggerException(
					"Exception in conversion from " + inputFormat + " to "
							+ outputFormat+" with exception details : "+e.getMessage(), transactionValue.toString(), e);
		}
		return transVal!=null?transVal:"";
	}

	@Override
	protected String format(String source, SYSTEM destination,
			IDENTIFIER_TYPE identifierType, String identifierValue,
			TRANSACTION_TYPE transactonType, TRANSACTION_API transactonApi,
			Object transactionValue, STATUS status, String statusMsg, String...messages) throws Exception {
		
		LogDTO logDTO = getLogDTO(source, destination, identifierType,
				identifierValue, transactonType, transactonApi,
				transactionValue, status, statusMsg, messages);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,
					false);
			return objectMapper.writeValueAsString(logDTO);
		} catch (Exception e) {
			throw new TransactionLoggerException("Exception in parsing the " + logDTO + " to JSONString with exception details: "+e.getMessage(), transactionValue.toString(), e);
		}

	}

	private LogDTO getLogDTO(String src, SYSTEM dest,
			IDENTIFIER_TYPE identifierType, String identifierValue,
			TRANSACTION_TYPE transactionType, TRANSACTION_API tranApi,
			Object transactionValue, STATUS status, String statusMsg, String...msgs) {
		LogDTO logDTO = new LogDTO();
		logDTO.setSource(src!=null?src:"");
		logDTO.setDestination(dest);
		logDTO.setIdentifierType(identifierType);
		logDTO.setIdentifierValue(identifierValue);
		logDTO.setTransactionType(transactionType);
		logDTO.setTransactionApi(tranApi);
		logDTO.setTransactionValue(transactionValue!=null?transactionValue:"");
		logDTO.setStatus(status);
		logDTO.setStatusMessage(statusMsg);
		logDTO.setMessages(msgs);
		return logDTO;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonAutoDetect(fieldVisibility = Visibility.ANY)
	private class LogDTO {
		private Long time;
		private String src = null;
		private SYSTEM dst = null;
		private IDENTIFIER_TYPE identifierType = null;
		private String identifierValue = null;
		private TRANSACTION_TYPE transactionType = null;
		private TRANSACTION_API transactionApi = null;
		private Object transactionValue = null;
		private STATUS status;
		private String statusMessage;
		private String[] messages = null;

		public LogDTO() {
			this.time=System.currentTimeMillis();
		}

		public void setMessages(String[] messages) {
			this.messages = messages;
		}

		public void setSource(String src) {
			this.src = src;
		}

		public void setDestination(LoggerOptions.SYSTEM dst) {
			this.dst = dst;
		}

		public void setIdentifierType(IDENTIFIER_TYPE identifierType) {
			this.identifierType = identifierType;
		}

		public void setIdentifierValue(String identifierValue) {
			this.identifierValue = identifierValue;
		}

		public void setTransactionType(TRANSACTION_TYPE transactionType) {
			this.transactionType = transactionType;
		}

		public void setTransactionApi(TRANSACTION_API transactionApi) {
			this.transactionApi = transactionApi;
		}

		public void setTransactionValue(Object transactionValue) {
			this.transactionValue = transactionValue;
		}

		public void setStatus(STATUS status) {
			this.status=status;
		}

		public void setStatusMessage(String statusMessage) {
			this.statusMessage=statusMessage;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "LogDTO [destination=" + dst + ", identifierType="
					+ identifierType + ", identifierValue=" + identifierValue
					+ ", messages=" + Arrays.toString(messages) + ", source="
					+ src + ", status=" + status + ", statusMessage="
					+ statusMessage + ", time=" + time + ", transactionValue="
					+ transactionValue + ", transactionApi=" + transactionApi
					+ ", transactonType=" + transactionType + "]";
		}
	}
}
