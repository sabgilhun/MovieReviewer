package org.sabgil.moviereviewer.model;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class NaverApiParser {
    private final static String TAG = NaverApiParser.class.getSimpleName();

    DocumentBuilderFactory objDocumentBuilderFactory;
    DocumentBuilder objDocumentBuilder;
    Document doc;
    XPath xPath;
    String path;
    ArrayList<String> selectList;

    public NaverApiParser(String path, ArrayList<String> selectList) {
        objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
        xPath = XPathFactory.newInstance().newXPath();
        this.path = path;
        this.selectList = selectList;
    }

    public ArrayList<HashMap<String, String>> parse(InputStream inStream) {
        NodeList descNodes;
        ArrayList<HashMap<String, String>> itemsList = new ArrayList<>();

        try {
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
            doc = objDocumentBuilder.parse(inStream);

            descNodes = (NodeList) xPath.evaluate(path, doc, XPathConstants.NODESET);

            for (int idx = 0; idx < descNodes.getLength(); idx++) {
                HashMap<String, String> items = new HashMap<>();
                for (Node node = descNodes.item(idx).getFirstChild(); node != null;
                     node = node.getNextSibling()) {

                    String nodeName = node.getNodeName();
                    if (selectList.contains(nodeName)) {
                        items.put(nodeName, node.getTextContent());
                    }
                }
                itemsList.add(items);
            }
            Log.i(TAG, itemsList.toString());
            return itemsList;

        } catch (Exception e) {
            Log.e(TAG, "parse error", e);
            return null;
        }
    }
}
