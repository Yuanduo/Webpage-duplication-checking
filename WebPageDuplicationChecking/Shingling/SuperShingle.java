package Shingling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.planetj.math.rabinhash.RabinHashFunction32;

public class SuperShingle {

	
	public static void main(String[] args) throws IOException {
		  List<String> x1 = new ArrayList<String>();
		  List<String> x2 = new ArrayList<String>();
		  BufferedReader in1 = new BufferedReader(new FileReader("1.txt"));
		  BufferedReader in2 = new BufferedReader(new FileReader("2.txt"));
		  String str = null;
		  while ((str = in1.readLine()) != null) {
			  x1.add(str);
		  }
		  while ((str = in2.readLine()) != null) {
			  x2.add(str);
		  }
		  
//		  System.out.println(x);
		  
		  in1.close();
		  in2.close();
			
			List<String> shingle1 = new ArrayList<String>();
			List<String> shingle2 = new ArrayList<String>();
			List<String> shingleNoRepeat1 = new ArrayList<String>();
			List<String> shingleNoRepeat2 = new ArrayList<String>();
			RabinHashFunction32 rabinHash = new RabinHashFunction32(1);
		//第一个文档	
			for(Iterator<String> it = x1.iterator(); it.hasNext();){
				Object s = it.next();
				long hashValue = rabinHash.hash(s+"");
				if(hashValue%25==0){
					 long hashSuperValue = rabinHash.hash(hashValue/25);
					 if(hashSuperValue%25==0){
							shingle1.add(hashValue+""); 
						 }
					
				}
					
			}
			//去重
			if(shingle1!=null){
				Iterator<String> it = shingle1.iterator();
				while(it.hasNext()){
					Object o = it.next();
					if(!shingleNoRepeat1.contains(o)){
						shingleNoRepeat1.add(o+"");
						System.out.println(o);
					}
				}
			}
			
		//第二个文档		
			for(Iterator<String> it = x2.iterator(); it.hasNext();){
				Object s = it.next();
				long hashValue = rabinHash.hash(s+"");
				if(hashValue%25==0){
					 long hashSuperValue = rabinHash.hash(hashValue/25);
					 if(hashSuperValue%25==0){
							shingle2.add(hashValue+""); 
						 }
					
				}
					
			}
			System.out.println("**************************");
			//去重
			if(shingle2!=null){
				Iterator<String> it = shingle2.iterator();
				while(it.hasNext()){
					Object o = it.next();
					if(!shingleNoRepeat2.contains(o)){
						shingleNoRepeat2.add(o+"");
						System.out.println(o);
					}
				}
			}
			
			Iterator<String> it2 = shingleNoRepeat2.iterator();
			double same = 0;
			while(it2.hasNext()){
				Object o = it2.next();
				if(shingleNoRepeat1.contains(o)) same++;
			}
			System.out.print(same);
			if(same>0){
				System.out.print("两个文档相似");
			}else{
				System.out.print("两个文档不相似");
			}
		
		}
}
