package com.example.networktest;


import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by john on 2018/1/9.
 */

public class Myhandler extends DefaultHandler {

    private static final String TAG = "Myhandler";
    String nodeName;
    StringBuilder id;
    StringBuilder name;
    StringBuilder version;

    @Override
    public void startDocument() throws SAXException {
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        nodeName = localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if ("id".equals(nodeName)){
            id.append(ch, start, length);
        }else if ("name".equals(nodeName)){
            name.append(ch, start, length);
        }else if ("version".equals(nodeName)){
            version.append(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("app".equals(localName)){
            Log.d(TAG, "id -----------" + id.toString().trim());
            Log.d(TAG, "name ----------------------" + name.toString().trim());
            Log.d(TAG, "version ---------------------------------------------" + version.toString().trim());
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
