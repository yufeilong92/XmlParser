package com.lawyee.cardstck.xml;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lawyee.cardstck.R;
import com.lawyee.cardstck.xml.pull.PullBookParser;
import com.lawyee.cardstck.xml.sax.SaxBookParser;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnReadXml;
    private Button mBtnWriterXml;
    private SaxBookParser parser;
    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();


    }

    private void initView() {
        mBtnReadXml = (Button) findViewById(R.id.btn_readXml);
        mBtnWriterXml = (Button) findViewById(R.id.btn_writerXml);

        mBtnReadXml.setOnClickListener(this);
        mBtnWriterXml.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_readXml:
                try {
                    InputStream inputStream = getAssets().open("book.xml");
//                    parser = new SaxBookParser();
//                    DomBookParser parser = new DomBookParser();
                    PullBookParser parser = new PullBookParser();
                    books = parser.parse(inputStream);
                    for (Book book : books) {
                        Log.e("解析", "onClick: "+book.toString() );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.btn_writerXml:
                try {
                    String serizalize = parser.serizalize(books);
                    FileOutputStream fos = openFileOutput("book.xml", Context.MODE_PRIVATE);
                    fos.write(serizalize.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }


                break;
        }
    }
}
