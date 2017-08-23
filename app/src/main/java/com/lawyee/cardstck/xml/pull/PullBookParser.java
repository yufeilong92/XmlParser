package com.lawyee.cardstck.xml.pull;

import android.util.Xml;

import com.lawyee.cardstck.xml.Book;
import com.lawyee.cardstck.xml.BookParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: CardStck
 * @Package com.lawyee.cardstck.xml.pull
 * @Description: $todo$
 * @author: YFL
 * @date: 2017/8/23 16:32
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class PullBookParser implements BookParser {
    @Override
    public List<Book> parse(InputStream is) throws Exception {
        List<Book> books = null;
        Book book = null;
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    books = new ArrayList<Book>();
                    break;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("book")) {
                        book = new Book();
                    } else if (parser.getName().equals("id")) {
                        eventType = parser.next();
                        book.setId(Integer.parseInt(parser.getText()));
                    } else if (parser.getName().equals("name")) {
                        eventType = parser.next();
                        book.setName(String.valueOf(parser.getText()));
                    } else if (parser.getName().equals("price")) {
                        eventType = parser.next();
                        book.setPrice(Float.valueOf(parser.getText()));
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("book")) {
                        books.add(book);
                        book = null;
                    }
                    break;
                default:
                    break;
            }
            eventType = parser.next();
        }

        return books;
    }

    @Override
    public String serizalize(List<Book> Books) throws Exception {
        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        serializer.setOutput(writer);
        serializer.startDocument("UTF-8",true);
        serializer.startTag("","books");
        for (Book book:Books) {
            serializer.startTag("","book");
            serializer.attribute("","id",book.getId()+"");

            serializer.startTag("","name");
            serializer.text(book.getName());
            serializer.endTag("","name");

            serializer.startTag("","price");
            serializer.text(book.getPrice()+"");
            serializer.endTag("","price");

            serializer.endTag("","book");

        }
        serializer.endTag("","books");
        serializer.endDocument();

        return writer.toString();
    }
}
