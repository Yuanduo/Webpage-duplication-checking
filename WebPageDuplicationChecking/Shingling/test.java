package Shingling;

import java.io.IOException;

public class test {
	public static void main(String[] args) throws IOException{
		//String txt1 = "һ�ۻ� ��˹��˰�i1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_M��������������һ�ۻ� ��˹��˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰�����������������������������������i1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mһ�ۻ� ��˹��˰�����������������������������������������������������������������������������������һ�ۻ� ��˹��˰�����������������������������������������������������������������������������������һ�ۻ� ��˹��˰�����������������������������������������������������������������������������������һ�ۻ� ��˹��˰�����������������������������������������������������������������������������������";
		//String txt2 = "һ�ۻ� ��˹���1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_M��������������һ�ۻ� ��˹��˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰������������������������������������˰�����������������������������������i1_Mod_Mi1_Mod_Mi1_Mod_Mi1_Mod_Mһ�ۻ� ��˹��˰�����������������������������������������������������������������������������������һ�ۻ� ��˹��˰�����������������������������������������������������������������������������������һ�ۻ� ��˹��˰�����������������������������������������������������������������������������������һ�ۻ� ��˹��˰�����������������������������������������������������������������������������������";

		String txt1 = "hello world";
		String txt2 = "world";
		Shingling s = new Shingling(txt1,txt2); //���ƶ�
		double similarity = s.compare();
		System.out.println("���ƶ�"+similarity);
	}
}