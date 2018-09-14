package com.yunkouan.util;

import java.io.File;
import java.io.StringReader;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;


/**
 * xml解析工具
 * <P>XmlUtil.java</P>
 * <P>Description: </P>
 * <P>Copyright: Copyright(c) 2016</P>
 * <P>Company: yunkouan.com</P>
 * <P>Date: 2016年12月15日 下午2:31:00</P>
 * @author andy
 */
public class XmlUtil {
	
	/**
	 * 读取xml
	 * @param fileName - 文件名
	 * @return 返回xml根节点
	 * @throws DocumentException
	 * <P>@author andy</P>
	 * <P>Date 2016年12月15日 下午2:31:18</P>
	 */
	public static Element readXML( String fileName ) throws DocumentException {
        String path = XmlUtil.class.getClassLoader().getResource(fileName).getPath();
        return XmlUtil.readXML(new File(path));
	}
	
	/**
	 * 读取xml
	 * @param fileName - 文件名
	 * @return 返回xml根节点
	 * @throws DocumentException
	 * <P>@author andy</P>
	 * <P>Date 2016年12月15日 下午2:31:18</P>
	 */
	public static Element readXML( File file ) throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(file);
        // 获取根元素
        Element root = document.getRootElement();
        return root;
	}

	public static Document createDocument() {
		Document doc = DocumentHelper.createDocument();
		return doc;
	}

	public static Element createElement(Document doc, String name, String text) {
		Element e = doc.addElement(name);
		if(text != null) e.setText(text);
		return e;
	}

	public static Element createElement(Element parent, String name, String text) {
		Element e = parent.addElement(name);
		if(text != null) e.setText(text);
		return e;
	}

	public static Element createElement(Document doc, String name) {
		Element e = doc.addElement(name);
		return e;
	}

	public static Element createElement(Element parent, String name) {
		Element e = parent.addElement(name);
		return e;
	}

	public static String toXML(Document doc) {
		return doc.getRootElement().asXML();
	}

	public static XPath createElementXPath(Document doc, String xpath) {
		return doc.createXPath(xpath);
	}

	  public static Document parseDoc(String strXml) throws DocumentException {
	    SAXReader reader = new SAXReader();
	    Document doc = reader.read(new StringReader(strXml.trim()));
	    return doc;
	  }

	  public static String getText(Document d, String xpath) {
	    Element e = getElemet(d, xpath);
	    return getText(e);
	  }

	  public static String getText(Element e, String xpath) {
	    if (e == null) return "";
	    Element e1 = (Element)e.selectSingleNode(xpath);
	    if (e1 == null) return "";
	    return StringUtils.trimToEmpty(e1.getText());
	  }
	  public static String getTextWithBlank(Element e, String xpath) {
	    if (e == null) return "";
	    Element e1 = (Element)e.selectSingleNode(xpath);
	    if (e1 == null) return "";
	    return e1.getText();
	  }

	  public static String getText(Element e) {
	    if (e == null) return "";
	    return StringUtils.trimToEmpty(e.getText());
	  }

	  public static String getText(Attribute e) {
	    if (e == null) return "";
	    return StringUtils.trimToEmpty(e.getText());
	  }

	  public static Element getElemet(Document d, String xpath) {
	    return (Element)d.selectSingleNode(xpath);
	  }

	  public static List<Element> getElemets(Document d, String xpath) {
	    return d.selectNodes(xpath);
	  }

	public static void main(String[] args) throws DocumentException {
		Document d = createDocument();
		Element root = createElement(d, "ITF_WHKJ_TRANSMIT");
		Element child1 = createElement(root, "ITF_WHKJ_STORE_AMOUNT");
		Element child2 = createElement(root, "ITF_WHKJ_STORE_AMOUNT");
		createElement(child1, "MESSAGE_ID", "1");
		createElement(child2, "MESSAGE_ID", "2");
		String xml = toXML(d);
		System.out.println(xml);
		xml = "<root><head><returncode>1000</returncode></head><body></body></root>";
		String returncode = getText(parseDoc(xml), "/root/head/returncode");
		System.out.println(returncode);
	}
}