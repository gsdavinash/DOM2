package com.student;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Student1 {

	public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerConfigurationException {
		// TODO Auto-generated method stub

		try
		{
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		dbf.setValidating(true);
		//dbf.setNamespaceAware(true);
		//SchemaFactory sf=SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		//Schema s=sf.newSchema();
		//Validator v=s.newValidator();
		
		DocumentBuilder db=dbf.newDocumentBuilder();
		db.setErrorHandler(new ErrorHandler1());
		Document d=db.parse(new File("resources/Student.xml"));
		//v.validate(new DOMSource(d));
		Element rootElement=d.getDocumentElement();
		//System.out.println(rootElement.getTagName());
		System.out.println("<"+rootElement.getNodeName()+">");
		NodeList nl=rootElement.getChildNodes();
		for(int j=0;j<nl.getLength();j++)
		{
			Node k=nl.item(j);
			if(k.getNodeType()==Node.ELEMENT_NODE){
			System.out.println("<"+k.getNodeName()+">");}
			NodeList nl2=k.getChildNodes();
			for(int h=0;h<nl2.getLength();h++)
			{
				Node f=nl2.item(h);
				if(f.getNodeType()==Node.ELEMENT_NODE)
				{
				System.out.print("<"+f.getNodeName());
				if(f.hasAttributes())
				{
					NamedNodeMap nm=f.getAttributes();
					for(int g=0;g<nm.getLength();g++)
					{
						Node o=nm.item(g);
						System.out.print(" "+o.getNodeName()+" = "+o.getNodeValue()+">");
					}
				}
				else
				{
					System.out.print(">");
					
				}
				System.out.print(f.getTextContent());
				System.out.println("</"+f.getNodeName()+">");
				}
				
			}
		}
		System.out.println("=============================");
		//reading sequentially finished
		NodeList nl3=d.getElementsByTagName("sname");
		System.out.println(nl3.item(0).getTextContent());
		NamedNodeMap nm2=nl3.item(0).getAttributes();
		System.out.println(nm2.item(0).getNodeName()+" = "+nm2.item(0).getNodeValue());
		nm2.item(0).setNodeValue("avinashgsd1");
		TransformerFactory tf=TransformerFactory.newInstance();
		Transformer t=tf.newTransformer();
		StreamResult r=new StreamResult(new File("resources/Student.xml"));
		t.transform(new DOMSource(d),r);
		System.out.println("done");
		
		
		}
		catch(Exception se)
		{
			se.printStackTrace();
		}
	}

}
