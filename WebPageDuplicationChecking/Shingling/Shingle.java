package Shingling;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.planetj.math.rabinhash.RabinHashFunction32;


public class Shingle {

	public double getSimilarity(String[] t1, String[] t2) throws IOException{

		int i1 = 0; //�ĵ�1�ܵ�shingle����
		int i2 = 0; //�ĵ�2�ܵ�shingle���� 
		int i1_Mod_M = 0; //����M��ĸ���
		int i2_Mod_M = 0;
		int M = 0;
		double Similarity = 0;//���ƶ�
		
		
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance(); 
	        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder(); 
	        Document doc = docBuilder.parse (new File("doc\\M.xml")); 
	        
	        NodeList MFormXml = doc.getElementsByTagName("VALUE"); 
	        for (int i = 0; i < MFormXml.getLength(); i++) {    
	            M =Integer.parseInt(doc.getElementsByTagName("M").item(i).getFirstChild().getNodeValue());
	           }     

		} catch (Exception e) {
		    e.printStackTrace();  
		}
		
        
		  
		  
		  List<String> x1 = new ArrayList<String>();
		  List<String> x2 = new ArrayList<String>();
		//  BufferedReader in1 = new BufferedReader(new FileReader("E:\\1.txt"));
		//  BufferedReader in2 = new BufferedReader(new FileReader("E:\\2.txt"));

		  for(int i=0;i<t1.length;i++)
			  x1.add(t1[i]);
		  
		  for(int i=0;i<t2.length;i++)
			  x2.add(t2[i]);
		  
//		  System.out.println(x);
		  
	
			
			List<String> shingle1 = new ArrayList<String>();
			List<String> shingle2 = new ArrayList<String>();
			List<String> shingleNoRepeat1 = new ArrayList<String>();
			List<String> shingleNoRepeat2 = new ArrayList<String>();
			RabinHashFunction32 rabinHash = new RabinHashFunction32(1);
		//��һ���ĵ�	
			for(Iterator<String> it = x1.iterator(); it.hasNext();){
				Object s = it.next();
				i1++;
				long hashValue = rabinHash.hash(s+"");
//				System.out.println(hashValue);
				if(hashValue%M==0){
					shingle1.add(hashValue/M+""); 
				}
					
			}
			//ȥ��
			if(shingle1!=null){
				Iterator<String> it = shingle1.iterator();
				while(it.hasNext()){
					Object o = it.next();
					if(!shingleNoRepeat1.contains(o)){
						shingleNoRepeat1.add(o+"");
//						System.out.println("1:"+o);
						i1_Mod_M++;
					}
				}
			}
			
		//�ڶ����ĵ�		
			for(Iterator<String> it = x2.iterator(); it.hasNext();){
				Object s = it.next();
				i2++;
				long hashValue = rabinHash.hash(s+"");
				if(hashValue%M==0){
					shingle2.add(hashValue/M+"");
				}
					
			}
			//ȥ��
			if(shingle2!=null){
				Iterator<String> it = shingle2.iterator();
				while(it.hasNext()){
					Object o = it.next();
					if(!shingleNoRepeat2.contains(o)){
						shingleNoRepeat2.add(o+"");
//						System.out.println("2:"+o); 
						i2_Mod_M++;
					}
				}
			}
			if(i1_Mod_M==0||i2_Mod_M==0)
				System.out.print("������̫�٣��޷�ʹ��Shingle�㷨��");
			else
			{
			Iterator<String> it1 = shingleNoRepeat1.iterator();
			Iterator<String> it2 = shingleNoRepeat2.iterator();
	

			double same = 0;
			
			while(it1.hasNext()){
				it1.next();
			}
			while(it2.hasNext()){
				Object o = it2.next();
				if(shingleNoRepeat1.contains(o)) same++;
			}
			
			Similarity = same/(i1_Mod_M+i2_Mod_M-same);
//			System.out.println("�ĵ�һ����shingle������ "+i1);
//			System.out.println("�ĵ�������shingle������ "+i2);
//			System.out.println("Mȡֵ�� "+M);
//			System.out.println("�ĵ�һ����M�����಻�ظ���shingle������ "+i1_Mod_M);
//			System.out.println("�ĵ�������M�����಻�ظ���shingle������ "+i2_Mod_M);
//			System.out.println("��С��Լ "+(i1/i1_Mod_M+i2/i2_Mod_M)/2 +" ��");
//			System.out.println("�������ĵ�һ�Ͷ���ͬ��shingle������ "+same);
//			System.out.println("�����ĵ�������ֵ��0-1��: "+Similarity);
//			System.out.println("��ȷ���ƶ�Ϊ: "+(double)i2/(double)i1);
			
			//����3ΪС��
			java.text.NumberFormat  formater  =  java.text.DecimalFormat.getInstance();  
			formater.setMaximumFractionDigits(3);  
			formater.setMinimumFractionDigits(3); 
			
			double xiangsi = Math.abs((same/(i1_Mod_M+i2_Mod_M-same)-(double)i2/(double)i1))
					/((double)i2/(double)i1);
			double wendang =(double)(i1-i2)/(double)i1;
//			System.out.println("���ƶ�ƫ��: "+formater.format(xiangsi*100)+" %");
//			System.out.println("�ĵ�����: "+formater.format(Math.abs((wendang*100)))+" %");

			
			}
			return Similarity;
			
		}
}
