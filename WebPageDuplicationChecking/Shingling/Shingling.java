package Shingling;

import java.io.IOException;

public class Shingling {
	public String sh1;
	public String sh2;

	public Shingling(String s1, String s2){
		sh1 = s1;
		sh2 = s2;
	}
	
	public double compare() throws IOException{
		Compare c1=new Compare(sh1);
		Compare c2=new Compare(sh2);
	//	long startTime=System.currentTimeMillis();
	//	Shingle s = new Shingle();
	//	double similarity = s.getSimilarity(c1.testICTCLAS_ParagraphProcess(),c2.testICTCLAS_ParagraphProcess());
	 //   long endTime=System.currentTimeMillis();
	//	System.out.println("运行时间："+ (endTime-startTime)+"ms");
		double similarity=0.0;
		String[] r1=c1.testICTCLAS_ParagraphProcess();
		String[] r2=c2.testICTCLAS_ParagraphProcess();
		int son=0;
		int father=0;
		for(int i=0;i<r1.length;i++)
			for(int j=0;j<r2.length;j++)
				if(r1[i].equals(r2[j]))
				{
					son++;
					break;
				}
		father=r1.length+r2.length-son;	
		similarity=(double)son/father;
		return similarity;
	}
	
}
