
package com.operator.card.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "CreditCardServiceService", targetNamespace = "http://ws.card.operator.com/", wsdlLocation = "http://localhost:8080/CardSystem/CreditCardServiceService?wsdl")
public class CreditCardServiceService
    extends Service
{

    private final static URL CREDITCARDSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException CREDITCARDSERVICESERVICE_EXCEPTION;
    private final static QName CREDITCARDSERVICESERVICE_QNAME = new QName("http://ws.card.operator.com/", "CreditCardServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/CardSystem/CreditCardServiceService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CREDITCARDSERVICESERVICE_WSDL_LOCATION = url;
        CREDITCARDSERVICESERVICE_EXCEPTION = e;
    }

    public CreditCardServiceService() {
        super(__getWsdlLocation(), CREDITCARDSERVICESERVICE_QNAME);
    }

    public CreditCardServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CREDITCARDSERVICESERVICE_QNAME, features);
    }

    public CreditCardServiceService(URL wsdlLocation) {
        super(wsdlLocation, CREDITCARDSERVICESERVICE_QNAME);
    }

    public CreditCardServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CREDITCARDSERVICESERVICE_QNAME, features);
    }

    public CreditCardServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CreditCardServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns CreditCardService
     */
    @WebEndpoint(name = "CreditCardServicePort")
    public CreditCardService getCreditCardServicePort() {
        return super.getPort(new QName("http://ws.card.operator.com/", "CreditCardServicePort"), CreditCardService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CreditCardService
     */
    @WebEndpoint(name = "CreditCardServicePort")
    public CreditCardService getCreditCardServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.card.operator.com/", "CreditCardServicePort"), CreditCardService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CREDITCARDSERVICESERVICE_EXCEPTION!= null) {
            throw CREDITCARDSERVICESERVICE_EXCEPTION;
        }
        return CREDITCARDSERVICESERVICE_WSDL_LOCATION;
    }

}
