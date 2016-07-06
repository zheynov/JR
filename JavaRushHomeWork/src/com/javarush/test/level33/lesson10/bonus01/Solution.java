package com.javarush.test.level33.lesson10.bonus01;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/* Комментарий внутри xml
Реализовать метод toXmlWithComment, который должен возвращать строку - xml представление объекта obj.
В строке перед каждым тэгом tagName должен быть вставлен комментарий comment.
Сериализация obj в xml может содержать CDATA с искомым тегом. Перед ним вставлять комментарий не нужно.

Пример вызова:  toXmlWithComment(firstSecondObject, "second", "it's a comment")
Пример результата:
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<first>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second><![CDATA[need CDATA because of < and >]]></second>
    <!--it's a comment-->
    <second/>
</first>
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {

        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            marshaller.marshal(obj, doc);

            NodeList nodeList;
            if (doc.hasChildNodes()) {

                nodeList = doc.getElementsByTagName("*");

                if (nodeList.getLength() > 0) {
                    for (int i = 0; i < nodeList.getLength(); i++) {

                        Node node = nodeList.item(i);
                        if (node.getNodeName().equals(tagName)) {
                            Comment com = doc.createComment(comment);
                            node.getParentNode().insertBefore(com, node);
                        }
                        cdataAddinf(node, doc);
                    }
                }
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            StringWriter sw = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();

        } catch (JAXBException | ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void cdataAddinf(Node node, Document doc) {

        String context = node.getTextContent();

        if (context.contains("&") || context.contains("\"") || context.contains(">") || context.contains("<") || context.contains("‘")) {
            {
                if (node.hasChildNodes()) {
                    for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                        cdataAddinf(node.getChildNodes().item(i), doc);
                    }
                } else {
                    node.getParentNode().replaceChild(doc.createCDATASection(node.getNodeValue()), node);
                }
            }
        }
    }
}
