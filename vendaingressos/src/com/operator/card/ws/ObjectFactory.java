
package com.operator.card.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.operator.card.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EmptyCreditCardData_QNAME = new QName("http://ws.card.operator.com/", "EmptyCreditCardData");
    private final static QName _InvalidPriceException_QNAME = new QName("http://ws.card.operator.com/", "InvalidPriceException");
    private final static QName _CreditCardNotValid_QNAME = new QName("http://ws.card.operator.com/", "CreditCardNotValid");
    private final static QName _InvalidTimesException_QNAME = new QName("http://ws.card.operator.com/", "InvalidTimesException");
    private final static QName _MissingDataException_QNAME = new QName("http://ws.card.operator.com/", "MissingDataException");
    private final static QName _Authorize_QNAME = new QName("http://ws.card.operator.com/", "authorize");
    private final static QName _AuthorizeResponse_QNAME = new QName("http://ws.card.operator.com/", "authorizeResponse");
    private final static QName _Creditcard_QNAME = new QName("http://ws.card.operator.com/", "creditcard");
    private final static QName _InvalidCreditCardData_QNAME = new QName("http://ws.card.operator.com/", "InvalidCreditCardData");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.operator.card.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EmptyCreditCardData }
     * 
     */
    public EmptyCreditCardData createEmptyCreditCardData() {
        return new EmptyCreditCardData();
    }

    /**
     * Create an instance of {@link InvalidPriceException }
     * 
     */
    public InvalidPriceException createInvalidPriceException() {
        return new InvalidPriceException();
    }

    /**
     * Create an instance of {@link InvalidCreditCardData }
     * 
     */
    public InvalidCreditCardData createInvalidCreditCardData() {
        return new InvalidCreditCardData();
    }

    /**
     * Create an instance of {@link InvalidTimesException }
     * 
     */
    public InvalidTimesException createInvalidTimesException() {
        return new InvalidTimesException();
    }

    /**
     * Create an instance of {@link MissingDataException }
     * 
     */
    public MissingDataException createMissingDataException() {
        return new MissingDataException();
    }

    /**
     * Create an instance of {@link Authorize }
     * 
     */
    public Authorize createAuthorize() {
        return new Authorize();
    }

    /**
     * Create an instance of {@link AuthorizeResponse }
     * 
     */
    public AuthorizeResponse createAuthorizeResponse() {
        return new AuthorizeResponse();
    }

    /**
     * Create an instance of {@link CreditCard }
     * 
     */
    public CreditCard createCreditCard() {
        return new CreditCard();
    }

    /**
     * Create an instance of {@link CreditCardNotValid }
     * 
     */
    public CreditCardNotValid createCreditCardNotValid() {
        return new CreditCardNotValid();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyCreditCardData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.card.operator.com/", name = "EmptyCreditCardData")
    public JAXBElement<EmptyCreditCardData> createEmptyCreditCardData(EmptyCreditCardData value) {
        return new JAXBElement<EmptyCreditCardData>(_EmptyCreditCardData_QNAME, EmptyCreditCardData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidPriceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.card.operator.com/", name = "InvalidPriceException")
    public JAXBElement<InvalidPriceException> createInvalidPriceException(InvalidPriceException value) {
        return new JAXBElement<InvalidPriceException>(_InvalidPriceException_QNAME, InvalidPriceException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditCardNotValid }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.card.operator.com/", name = "CreditCardNotValid")
    public JAXBElement<CreditCardNotValid> createCreditCardNotValid(CreditCardNotValid value) {
        return new JAXBElement<CreditCardNotValid>(_CreditCardNotValid_QNAME, CreditCardNotValid.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidTimesException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.card.operator.com/", name = "InvalidTimesException")
    public JAXBElement<InvalidTimesException> createInvalidTimesException(InvalidTimesException value) {
        return new JAXBElement<InvalidTimesException>(_InvalidTimesException_QNAME, InvalidTimesException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MissingDataException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.card.operator.com/", name = "MissingDataException")
    public JAXBElement<MissingDataException> createMissingDataException(MissingDataException value) {
        return new JAXBElement<MissingDataException>(_MissingDataException_QNAME, MissingDataException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Authorize }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.card.operator.com/", name = "authorize")
    public JAXBElement<Authorize> createAuthorize(Authorize value) {
        return new JAXBElement<Authorize>(_Authorize_QNAME, Authorize.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthorizeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.card.operator.com/", name = "authorizeResponse")
    public JAXBElement<AuthorizeResponse> createAuthorizeResponse(AuthorizeResponse value) {
        return new JAXBElement<AuthorizeResponse>(_AuthorizeResponse_QNAME, AuthorizeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditCard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.card.operator.com/", name = "creditcard")
    public JAXBElement<CreditCard> createCreditcard(CreditCard value) {
        return new JAXBElement<CreditCard>(_Creditcard_QNAME, CreditCard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidCreditCardData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.card.operator.com/", name = "InvalidCreditCardData")
    public JAXBElement<InvalidCreditCardData> createInvalidCreditCardData(InvalidCreditCardData value) {
        return new JAXBElement<InvalidCreditCardData>(_InvalidCreditCardData_QNAME, InvalidCreditCardData.class, null, value);
    }

}
