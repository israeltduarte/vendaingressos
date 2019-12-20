
package com.operator.card.ws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "InvalidTimesException", targetNamespace = "http://ws.card.operator.com/")
public class InvalidTimesException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private InvalidTimesException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public InvalidTimesException_Exception(String message, InvalidTimesException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public InvalidTimesException_Exception(String message, InvalidTimesException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.operator.card.ws.InvalidTimesException
     */
    public InvalidTimesException getFaultInfo() {
        return faultInfo;
    }

}
