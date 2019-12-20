package vendaingressos.helper;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPException;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class AESHandler implements SOAPHandler<SOAPMessageContext> {

	public boolean handleMessage(SOAPMessageContext context) {

		if (!(boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)) {

			System.out.println("Intercepter do cliente funfando");
			AES aes = new AES("MiZygpJusCpRrf0r".getBytes(StandardCharsets.UTF_8)); 
			Node node = null;
			try {
				node = context.getMessage().getSOAPBody().getElementsByTagName("cartao").item(0);
			} catch (SOAPException e) {
				e.printStackTrace();
			}
			try {
				node.getParentNode().replaceChild(context.getMessage().getSOAPBody().getOwnerDocument()
						.importNode(DocumentBuilderFactory.newInstance().newDocumentBuilder()
								.parse(new ByteArrayInputStream(aes.decrypt(Base64.getDecoder()
										.decode(((Element) node).getTextContent().getBytes(StandardCharsets.UTF_8)))))
								.getElementsByTagName("cartao").item(0), true),
						node);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return (true);
	}

	@Override
	public void close(MessageContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean handleFault(SOAPMessageContext arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}
}
