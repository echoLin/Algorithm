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
 * 统计数字
 * 问题描述
 *        一本书的页码从自然数1开始顺序编码直到自然数n。
 *        书的页码按照通常的习惯编排，每个页码都不含多余的前导数字0。
 *        例如，第6页用数字6表示，而不是06或006等。
 *        数字计数问题要求对给定书的总页码n，计算出书的全部页码中分别用到多少次数字0,1,2，...，9。
 * 算法设计
 *        给定表示书的总页码的10进制整数n（1≤n≤109）。
 *        编程计算数的全部页码中分别用到多少次数字0,1,2，...，9。
 * 数据输入
 *        输入数据由文件名为input.txt的文本文件提供，每个文件只有1行，
 *        给出表示书的总页码的整数n。
 * 结果输出
 *        将计算结果输出到文件output.txt。
 *        输出文件共有10行，在第k行输出页码中用到数字k-1的次数，k=1,2,3,…,10。
 * 
 * 数字长度为N，当前为从高位到低位的第i位，数字为a,则
 * a在当前位置出现的次数为10^(N-i-1)*(第0~（i+1）位上的数字 + 1)
 * 小于a的数字在当前位置出现的次数为10^(N-i-1)*(第0~（i-1）位上的数字 + 1)
 * 大于a的数组在当前位置出现的次数为10^(N-i-1)*（第0~（i-1）位上的数字）
 * 所以采用递归的方法可以求得各个数字出现的次数。
 * PS：以上在计算数字出现的时候把前导0也统计了进去，故，需要在最后用循环将前导0的统计次数减去
 * */
public class CountNumbers {

	public static void main(String[] args) {
		int page = 0;
		Scanner input;
		Formatter output;
		
		//read file
		try {
			input = new Scanner(Paths.get("input.txt"));
			if(input.hasNext()){
				page = input.nextInt();
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//count numbers
		int count[] = count(page);
		
		//write file
		try {
			output = new Formatter("output.txt");
			for(int num : count)
				output.format("%d%n", num);
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public static int[] count(int n){
		int count[] = new int[10];//used for storage the appear times of 0-9
		int i, j, k;//used for circle count
		int L;//used for storage the length of the n number
		int len;//used for storage the length of the m number
		int m;//m is the n number which abandon the top number
		
		m = n;
		L = (int)Math.ceil(Math.log10(n+1));
		
		//initialize the count array
		for(i = 0; i < 10; i++)
			count[i] = 0;
		
		for(j = 0; j < L; j++){
			len = (int)Math.ceil(Math.log10(m+1));//calculate the length of m
			k = (int)(m/Math.pow(10.0, len - 1));//the first number of m
			for(i = 0; i < 10; i++)
				count[i] += k * (len - 1) * Math.pow(10.0, len-2);
			for(i = 0; i < k; i++)
				count[i] += Math.pow(10.0, len - 1);
			count[k] += m - k * Math.pow(10.0, len-1) + 1;
			m = m - (int)(k * Math.pow(10.0, len - 1));//next m
		}
		
		for(i = 0; i < L; i++)//subtract the bellwether zero number
			count[0] -= Math.pow(10.0, i);
		
		return count;
	}

}
