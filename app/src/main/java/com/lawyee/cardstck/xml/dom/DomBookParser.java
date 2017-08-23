package com.lawyee.cardstck.xml.dom;

import com.lawyee.cardstck.xml.Book;
import com.lawyee.cardstck.xml.BookParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: CardStck
 * @Package com.lawyee.cardstck.xml.dom
 * @Description: $todo$
 * @author: YFL
 * @date: 2017/8/23 15:28
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class DomBookParser implements BookParser {
    @Override
    public List<Book> parse(InputStream is) throws Exception {
        ArrayList<Book> books = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(is);
        Element rootElement = doc.getDocumentElement();
        NodeList items = rootElement.getElementsByTagName("book");

        for (int i = 0; i < items.getLength(); i++) {
            Book book = new Book();
            Node item = items.item(i);
            NodeList properties = item.getChildNodes();
            for (int j = 0; j < properties.getLength(); j++) {
                Node property = properties.item(j);
                String nodeName = property.getNodeName();
                if (nodeName.equals("id")) {
                    book.setId(Integer.parseInt(property.getFirstChild().getNodeValue()));
                } else if (nodeName.equals("name")) {
                    book.setName(property.getFirstChild().getNodeValue());
                } else if (nodeName.equals("price")) {
                    book.setPrice(Float.valueOf(property.getFirstChild().getNodeValue()));
                }
            }
            books.add(book);
        }


        return books;
    }

    @Override
    public String serizalize(List<Book> Books) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element rootEement = doc.createElement("books");
        for (Book book : Books) {
            Element bookElement = doc.createElement("book");
            bookElement.setAttribute("id", book.getId() + "");

            Element nameElement = doc.createElement("name");
            nameElement.setTextContent(book.getName());
            bookElement.appendChild(nameElement);

            Element priceElement = doc.createElement("price");
            priceElement.setTextContent(book.getPrice() + "");
            bookElement.appendChild(priceElement);

            rootEement.appendChild(bookElement);
        }
        doc.appendChild(rootEement);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"no");

        StringWriter stringWriter = new StringWriter();

        Source source = new DOMSource(doc);
        StreamResult result = new StreamResult(stringWriter);
        transformer.transform(source,result);

        return stringWriter.toString();
    }
}
