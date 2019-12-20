
package com.operator.card.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for authorize complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="authorize">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cartao" type="{http://ws.card.operator.com/}creditCard"/>
 *         &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="parcelas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authorize", propOrder = {
    "cartao",
    "valor",
    "parcelas"
})
public class Authorize {

    @XmlElement(required = true)
    protected CreditCard cartao;
    protected double valor;
    protected int parcelas;

    /**
     * Gets the value of the cartao property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCard }
     *     
     */
    public CreditCard getCartao() {
        return cartao;
    }

    /**
     * Sets the value of the cartao property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCard }
     *     
     */
    public void setCartao(CreditCard value) {
        this.cartao = value;
    }

    /**
     * Gets the value of the valor property.
     * 
     */
    public double getValor() {
        return valor;
    }

    /**
     * Sets the value of the valor property.
     * 
     */
    public void setValor(double value) {
        this.valor = value;
    }

    /**
     * Gets the value of the parcelas property.
     * 
     */
    public int getParcelas() {
        return parcelas;
    }

    /**
     * Sets the value of the parcelas property.
     * 
     */
    public void setParcelas(int value) {
        this.parcelas = value;
    }

}
