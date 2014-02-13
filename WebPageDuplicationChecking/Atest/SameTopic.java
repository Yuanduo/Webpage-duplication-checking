package Atest;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import Shingling.Shingling;

public class SameTopic {
	public ArrayList<Resources> data = new ArrayList<Resources>();
	
	// 添加数据
	public boolean add(){
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar cal=Calendar.getInstance();
		Date dt = new Date();
		
		for(int i = 0; i < 2; i ++){
			Resources rs = new Resources();
			rs.setWords("hello world");
			rs.setUsername("abc");
			cal.clear();
			cal.set(1999, 1, 2, 3, 4, i);
			dt=cal.getTime();
			rs.setTime(date.format(dt));
			data.add(rs);
		}		
		
		for(int i = 0; i < 2; i ++){
			Resources rs = new Resources();
			rs.setWords("123456789");
			rs.setUsername("bca");
			cal.clear();
			cal.set(1999, 1, 2, 3, 5, i);
			dt=cal.getTime();
			rs.setTime(date.format(dt));
			data.add(rs);
		}		
		
		for(int i = 0; i < 2; i ++){
			Resources rs = new Resources();
			rs.setWords("hello world");
			rs.setUsername("abc");
			cal.clear();
			cal.set(1999, 1, 2, 3, 6, i);
			dt=cal.getTime();
			rs.setTime(date.format(dt));
			data.add(rs);
		}	
		
		Resources rs = new Resources();
		rs.setWords("hello world");
		rs.setUsername("abc");
		cal.clear();
		cal.set(1999, 1, 2, 3, 10, 1);
		dt=cal.getTime();
		rs.setTime(date.format(dt));
		data.add(rs);
		
		return true;
	}
	
	// 检验判断相似度
	public int[] getFlag(int[] flag, ArrayList<Resources> data){
		String tmp = null;
		
		Shingling s = null;
		double similarity = 0;
		
		for(int i = 0; i < data.size(); i ++){
			if(flag[i]==0){
				tmp = data.get(i).getWords();
				for(int j = i + 1; j < data.size() ; j ++){
					//if(tmp.equals(data.get(j).getWords())){
					s = new Shingling(tmp,data.get(j).getWords()); // 相似度
					try {
						similarity = s.compare();
						System.out.println("the similarity is " + similarity);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if( similarity > 0.98 ){
						flag[j] = i + 1;
					}// if
				}// for
			}// if
		}//for
		return flag;
	}
	
	// 按时间排序
	public ArrayList<Resources> sort(ArrayList<Resources> tmp){
		Comparator comp = new Mycomparator();
        Collections.sort(tmp,comp);        
		return tmp;
	}
	
	// 找出相似的倒数第二个文本所在位置
	public int lastWordPosition(int[] flag, int startPosition){
		int last1 = 0, last2 = 0;
		for(int i = startPosition; i < flag.length; i ++){
			if(flag[startPosition] == 0){
				if(flag[i] == startPosition + 1){
					last2 = last1;
					last1 = i;
				}
			}else{
				if(flag[i] == flag[startPosition]){
					last2 = last1;
					last1 = i;
				}
			}
		}
		return last2;
	}
	
	//暂定规则为5分钟以内内容相似的文本达3条及以上为垃圾信息
	public int[] parseTime(){ 
		ArrayList<Resources> tmp = new ArrayList<Resources>();	
		int mark = 0;
		int flag1 = 0;
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date dt1 = null;
		Date dt2 = null;
		long d1, d2;
		int[] nflag = new int[data.size()];
		int size = 0;
		int i , j ;
		
		tmp = sort(data);	
		
		nflag = getFlag(nflag, tmp);
		System.out.println("new flag");
		for(i = 0; i < data.size(); i ++)
			System.out.println(nflag[i]);
		System.out.println("=============================================");
		size = lastWordPosition(nflag, 0);
		
		i = 0;
		while( i < tmp.size() ){
			if( i < size){
				j = i + 1;
				try {
					dt1 = date.parse(tmp.get(i).getTime());
					dt2 = date.parse(tmp.get(j).getTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				d1 = dt1.getTime();
				d2 = dt2.getTime();

				while(d2 - d1 <= 5*60*1000 && j < tmp.size()){
					if( nflag[i] == 0 ){
						if(nflag[j] == i + 1){
							flag1 ++; 
							if(flag1 == 2) break;
							mark = j;
						}
					}else{
						if(nflag[j]==nflag[i]){
							flag1 ++;
							if(flag1 == 2) break;
							mark = j;
						}
					}
					if( j == tmp.size() - 1 )  break;
					j ++;
					
					try {
						dt2 = date.parse(tmp.get(j).getTime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					d2 = dt2.getTime();
				}// while time
				if(flag1 == 2){
					nflag[mark] = -1;
					nflag[i] = -1;
				}
				flag1 = 0;
			}
			i ++;
			size = lastWordPosition(nflag, i);
		}
		System.out.println("ok!");
		
		return nflag;
	}
	
	// 过滤
	public boolean checkSame(){
		int[] flag = new int[data.size()];
		
		// the value of the flag indicate the first position of the same words
		// and after this operation, the value equals to 0 is the position the same words first appear.
		flag = getFlag(flag,data);
		
		// according to specific time interval, to judge whether the words is a spam
		int[] nflag = new int[data.size()];
		nflag = parseTime();
		for(int i = 0; i < data.size(); i ++)
			System.out.println(nflag[i]);
		
		System.out.println("====================================");
		// display the useful information
		System.out.println("the useful information");
		for(int i = 0; i < data.size(); i ++){
			if(nflag[i]!=-1)
				System.out.println(data.get(i).getWords() + "  " + data.get(i).getUsername()
						+ "  " + data.get(i).getTime());
		}
		// display the spam information
		System.out.println("the spam information: ");
		for(int i = 0; i < data.size(); i ++){
			if(nflag[i]==-1)
				System.out.println(data.get(i).getWords() + "  " + data.get(i).getUsername()
						+ "  " + data.get(i).getTime());
		}
		
		return true;
	}
	
	public static void main(String args[]){
		SameTopic test = new SameTopic();
		test.add();
		System.out.println("original data:");
		for(int i = 0; i < test.data.size(); i ++){
			System.out.println("   " + test.data.get(i).getWords() 
					+ "   " + test.data.get(i).getUsername() + "   "
					+ test.data.get(i).getTime());
		}
		System.out.println("==========================================================");
		test.checkSame();
		
	}
}
