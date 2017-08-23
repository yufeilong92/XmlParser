package com.lawyee.cardstck.xml.sax;

import android.util.Log;

import com.lawyee.cardstck.xml.Book;
import com.lawyee.cardstck.xml.BookParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: CardStck
 * @Package com.lawyee.cardstck.xml
 * @Description: $todo$
 * @author: YFL
 * @date: 2017/8/23 14:01
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class SaxBookParser implements BookParser {
    @Override
    public List<Book> parse(InputStream is) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        SaxHandler saxHandler = new SaxHandler();
        saxParser.parse(is, saxHandler);
        return saxHandler.getBooks();
    }

    @Override
    public String serizalize(List<Book> Books) throws Exception {
        SAXTransformerFactory factory = (SAXTransformerFactory) TransformerFactory.newInstance();
        factory.newTransformerHandler();
        TransformerHandler handler = factory.newTransformerHandler();
        Transformer transformer = handler.getTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

        StringWriter stringWriter = new StringWriter();
        StreamResult streamResult = new StreamResult(stringWriter);
        handler.setResult(streamResult);

        String uri = ""; //代表命名空间的URI 当URI无值时 须置为空字符串
        String loaclName = "";//命名空间的本地名称(不包含前缀) 当没有进行命名空间处理时 须置为空字符串
        handler.startDocument();
        handler.startElement(uri, loaclName, "books", null);
        AttributesImpl attributes = new AttributesImpl();
        char[] ch = null;
        for (Book book : Books) {
            attributes.clear();
            attributes.addAttribute(uri, loaclName, "id", "string", String.valueOf(book.getId()));
            handler.startElement(uri, loaclName, "book", attributes);
            handler.startElement(uri, loaclName, "name", null);
            ch = String.valueOf(book.getName()).toCharArray();
            handler.characters(ch, 0, ch.length);
            handler.endElement(uri, loaclName, "name");

            handler.startElement(uri, loaclName, "price", null);
            ch = String.valueOf(book.getPrice()).toCharArray();
            handler.characters(ch, 0, ch.length);
            handler.endElement(uri, loaclName, "price");
            handler.endElement(uri, loaclName, "book");

        }
        handler.endElement(uri, loaclName, "books");
        handler.endDocument();

        return stringWriter.toString();
    }

    private class SaxHandler extends DefaultHandler {
        private List<Book> books;
        private Book book;
        private StringBuilder builder;

        public List<Book> getBooks() {
            return books;
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            books = new ArrayList<>();
            builder = new StringBuilder();

        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            Log.e("及诶些", "解析完成");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if (localName.equals("book")) {
                book = new Book();
            }
            builder.setLength(0);//将字符长度设置为0 以便重新开始读取元素内的字符节点

        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            Log.e("解析数据", "endElement: " + localName);
            if (localName.equals("id")) {
                book.setId(Integer.parseInt(builder.toString()));
            } else if (localName.equals("name")) {
                book.setName(String.valueOf(builder.toString()));
            } else if (localName.equals("price")) {
                book.setPrice(Float.valueOf(builder.toString()));
            } else if (localName.equals("book")) {
                books.add(book);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            builder.append(ch, start, length); //将读取的字符数组追加到builder中
        }
    }
}
