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

		int i1 = 0; //文档1总的shingle个数
		int i2 = 0; //文档2总的shingle个数 
		int i1_Mod_M = 0; //整除M后的个数
		int i2_Mod_M = 0;
		int M = 0;
		double Similarity = 0;//相似度
		
		
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
		//第一个文档	
			for(Iterator<String> it = x1.iterator(); it.hasNext();){
				Object s = it.next();
				i1++;
				long hashValue = rabinHash.hash(s+"");
//				System.out.println(hashValue);
				if(hashValue%M==0){
					shingle1.add(hashValue/M+""); 
				}
					
			}
			//去重
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
			
		//第二个文档		
			for(Iterator<String> it = x2.iterator(); it.hasNext();){
				Object s = it.next();
				i2++;
				long hashValue = rabinHash.hash(s+"");
				if(hashValue%M==0){
					shingle2.add(hashValue/M+"");
				}
					
			}
			//去重
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
				System.out.print("数据量太少，无法使用Shingle算法。");
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
//			System.out.println("文档一共有shingle个数： "+i1);
//			System.out.println("文档二共有shingle个数： "+i2);
//			System.out.println("M取值： "+M);
//			System.out.println("文档一整除M后所余不重复的shingle个数： "+i1_Mod_M);
//			System.out.println("文档二整除M后所余不重复的shingle个数： "+i2_Mod_M);
//			System.out.println("缩小大约 "+(i1/i1_Mod_M+i2/i2_Mod_M)/2 +" 倍");
//			System.out.println("整除后文档一和二共同的shingle个数： "+same);
//			System.out.println("两个文档的相似值（0-1）: "+Similarity);
//			System.out.println("精确相似度为: "+(double)i2/(double)i1);
			
			//保留3为小数
			java.text.NumberFormat  formater  =  java.text.DecimalFormat.getInstance();  
			formater.setMaximumFractionDigits(3);  
			formater.setMinimumFractionDigits(3); 
			
			double xiangsi = Math.abs((same/(i1_Mod_M+i2_Mod_M-same)-(double)i2/(double)i1))
					/((double)i2/(double)i1);
			double wendang =(double)(i1-i2)/(double)i1;
//			System.out.println("相似度偏离: "+formater.format(xiangsi*100)+" %");
//			System.out.println("文档差别度: "+formater.format(Math.abs((wendang*100)))+" %");

			
			}
			return Similarity;
			
		}
}
