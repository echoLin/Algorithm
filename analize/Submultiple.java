package analize;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;

/**
 * 最多约数问题
 * 正整数x的约数是能整除x的正整数。
 * 正整数x的约数个数记为div(x)。
 * 例如：1,2,5,10都是正整数10的约数，
 * 且div(10) = 4. 设a和b是2个正整数， a≤b, 找出a和b之间约数个数最多的数x。
 * 
 * 有一个求约数个数的公式：
 * 如果一个数N = A1^N1 * A2^N2 * A3^N3 * …… * Am^Nm
 * 那么这个数的约数个数为（N1+1)(N2+1)...(Nm+1).
 * 如果采用枚举，则可能会重复计算某个数的约数。比如枚举到n 和 m*n 则我们会重复计算n的约数。
 * 因此可以采用以质因子为对象的深度搜索。
 * 初始时，令number=1，然后从最小的质数2开始枚举，枚举包含一个2，两个2，...，n个2的情况，
 * 直到number*(2^n)大于区间的上限。
 * 对于每一种2^k的情况，令number = number * (2^k)，再枚举3的情况，再枚举5，7,...,的情况，
 * 整个过程是一个深度搜索的过程。
 * 当number大于等于区间下限的时，我们就找到了一个区间内的数。再根据上门的介绍的公式，可以计算出约数个数。
 * PS:上面的过程中 number可能是一个无用的数，所以我们需要进行剪枝。
 * 如果(min – 1)/number < max/number，
 * 则区间内存在可以被number整除的数。
 * 因为，如果区间[min, max]内存在可以被number整除的数，
 * 也即是从min到max中至少有一个数能被number整除，
 * 那么区间[min – 1, max]内的数被number除得的商肯定不止一种，
 * 所以(min – 1)/number必然小于max/number。
 * 因此我们可以假设[min, max]内存在可以被number整除的数，
 * 如果（min-1)/number = max/number, 则min/number = max/number
 * (1)min=max, min和max都可以被number整除
 * (2)max>min, min可以被number整除
 * 
 * @author echo
 * */
public class Submultiple {
	private static final int MAXP = 100000;
	private static int prim[] = new int[MAXP];
	private static int max, numb, PCOUNT;

	public static void main(String[] args) {
		Scanner input;
		Formatter output;
		int a = 0, b = 0;
		
		//read file
		try {
			input = new Scanner(Paths.get("input.txt"));
			a = input.nextInt();
			b = input.nextInt();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//calculate
		primes();
		if((a == 1) && (b == 1)){
			max = 1;
			numb = 1;
		}else{
			max = 2;
			numb = a;
			search(1, 1, 1, a, b);
		}
		
		//write file
		try {
			output = new Formatter("output.txt");
			output.format("%d%n", max);
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		
	}
	
	//筛选产生质数存在prim[]中
	public static void primes(){
		boolean get[] = new boolean[MAXP+1];
		int i;
		for(i = 2; i <= MAXP; i++)
			get[i] = true;
		for(i = 2; i <= MAXP; i++)
			if(get[i]){
				int j = i + i;
				while(j <= MAXP){
					get[j] = false;
					j += i;
				}
			}
		
		int ii, j;
		for(ii = 2, j = 0; ii <= MAXP; ii++)
			if(get[ii])
				prim[++j] = ii;
		PCOUNT = j;
	}
	
	//在区间[low,up]中，tot为当前约数最多个数，num为当前约数最多的数，from表示现在是第几个质数
	public static void search(int from, int tot, int num, int low, int up){
		if(num >= 1)
			if(tot > max || ( tot == max && num < numb)){
				max = tot;
				numb = num;
			}
		if(low == up && low > num)
			search(from, tot*2, num*low, 1, 1);
		for(int i = from; i <= PCOUNT; i++){
			if(prim[i] > up)
				return ;
			else{
				int j = prim[i];
				int x = low - 1;
				int y = up;
				int n = num;
				int t = tot;
				int m = 1;
				while(true){
					m++;
					t += tot;
					x /= j;
					y /= j;
					if(x == y)
						break;
					n *= j;
					search(i+1, t, n, x+1, y);
				}
				m = 1 << m;
				if(tot < max / m)
					return ;
				
			}
		}
	}

}
