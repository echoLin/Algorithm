package analize;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;
/**
 * @author echoLin
 * */
/**
 * 字典序问题
 * 问题描述
 * 	在数据加密和数据压缩中常需要对特殊的字符串进行编码，
 * 	给定的字母表，给定的字母表A由26个小写英文字母组成，即A={a,b,...,z},
 *  该字母表产生的长序字符串时指定字符串中字母从左到右出现的次序与字母在字母表中出现的次序相同，
 *  且每个字符最多出现一次。例如a, b, ab, bc, xz等，字符串是升序字符串。
 *  现在对字母表A产生的所有长度不超过6的升序字符串按照字典排列编码如下：
 * 	1-a		2-b		…… 26-z		27-ab		28-ac	……
 * 	对于任意长度不超过6的升序字符串，迅速计算出它在上述字典表中的编码。
 * 算法设计
 * 	对于任意长度不超过6的升序字符串，计算出它在上述字典表中的编码。
 * 数据输入
 * 	输入数据由文件名为input.txt的文本文件提供，文件的第一行是一个正整数k, 表示接下来共有k行中，每行给出一个字符串。
 * 结果输出
 * 	将计算结果输出到文件output.txt。共输出k行，每行为对应input.txt中字符串在字典序中的值。
 * 
 * 设以i字符打头的长为k的升序字符串的个数为f(i,k),
 * 则长度为k的升序字符串的总个数为g(k)=累加f(i,k),1≤k≤26。
 * 因此我们可以递归计算长度为k的升序字符串i1i2...ik：
 * (1)小于i1的长度为k的升序字符串的值为 累加f(i,k),1≤i＜i1
 * (2)计算i2i3...ik长度为k-1的升序字符串
 * 所以i1i2...ik的值为（1）（2）相加。
 * */
public class DirectoryProblem {

	public static void main(String[] args) {
		int k = 0;
		String[] str = null;
		Scanner input;
		Formatter output;
		
		//read file
		try {
			input = new Scanner(Paths.get("input.txt"));
			k = input.nextInt();
			str = new String[k];
			for(int i = 0; i < k; i++){
				str[i] = input.next();
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//search the right values of strings
		int result[] = search(str, k);
		
		//write file
		try {
			output = new Formatter("output.txt");
			for(int num : result)
				output.format("%d%n", num);
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public static int[] search(String[] str, int k){
		
		int result[] = new int[k];
		for(int i = 0; i < k; i++){
			int size = str[i].length();
			//calculate combination num of string with size char
			int sum = 0;
			for(int j = 1; j < size; j++)
				sum += numForIndex(j);
			
			//calculate combination num of string with size char and j is less than str's first char
			for(int j = 1; j < change(str[i].charAt(0)); j++)
				sum += numForChar(j, size);
			
			//calculate combination num of str's following char
			for(int j = 1, temp = change(str[i].charAt(0)); j < size; j++){
				int t = change(str[i].charAt(j));
				int len = size - j;
				for(int x = temp + 1; x < t; x++)
					sum += numForChar(x, len);
				temp = t;
			}
			
			result[i] = sum+1;	
		}
		
		return result;
	}
	
	/**
	 * calculate combination num of str which first char is c and length is index
	 * */
	public static int numForChar(int c, int index){
		int num = 0;
		
		if(index == 1)
			return 1;
		
		for(int i = c + 1; i <= 26 ; i++)
			num += numForChar(i, index - 1);
		
		return num;
	}
	
	/**
	 * calculate combination num of str which length is index
	 * */
	public static int numForIndex(int index){
		int num = 0;
		
		for(int i = 1; i <= 26; i++)
			num += numForChar(i, index);
		
		return num;
	}
	
	/**
	 * change char to integer
	 * */
	public static int change(char c){
		return (c - 'a' + 1);
	}

}
