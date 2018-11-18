package com.my.rest.common;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
 
public class XmlExtraction {
   
  public static void getXmlData() throws Exception {
	  
     //1.문서를 읽기위한 공장을 만들어야 한다.
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         
	//2.빌더 생성
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         
    //3.생성된 빌더를 통해서 xml문서를 Document객체로 파싱해서 가져온다
	File file = new File("C:\\Dev\\test.xml");
	
    Document doc = dBuilder.parse(file);
    
    doc.getDocumentElement().normalize();//문서 구조 안정화
     
    Element root = doc.getDocumentElement();
     
    NodeList n_list = root.getElementsByTagName("person");
    Element el = null;
    NodeList sub_n_list = null; //sub_n_list
    Element sub_el = null; //sub_el         
    Node v_txt = null;
    
    
    
 
    String value="";
         
    String[] tagList = {"name", "age", "job"};
      
    for(int i=0; i<n_list.getLength(); i++) {
      el = (Element) n_list.item(i);
      for(int k=0; k< tagList.length; k++) {
        sub_n_list = el.getElementsByTagName(tagList[k]);
        for(int j=0; j<sub_n_list.getLength(); j++) {
          sub_el = (Element) sub_n_list.item(j);
          v_txt = sub_el.getFirstChild();
          value = v_txt.getNodeValue();
           
          System.out.println(sub_el.getNodeName() + "::::value="+value);
          if(sub_el.getAttributes().getNamedItem("id")!=null)
          System.out.println("id="+ sub_el.getAttributes().getNamedItem("id").getNodeValue() );
        }
      }

    }
  }
 
}
