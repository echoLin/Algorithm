package sort;


/**
 * 基数排序、桶排序
 * 最高位优先MSD：先按K1排序分组，同一组记录中关键码k1相等，
 * 再对各组按k2排序分成子组，之后对后面的关键码继续这样的排序分组，
 * 直到按最次为关键码kd对各子组排序后，在将各组连接起来，便得到一个有序序列
 * 最低位优先LSD：先从kd开始排序，再对Kd-1进行排序，
 * 依次重复，直到对k2跑徐后便得到一个有序序列
 * @author echo
 * */
public class LSDSort {
	public void LSD(int arr[], int n){
		int maxNum = findMaxNum(arr, n);
		int loopTime = getLoopTime(maxNum);
		for(int i = 1; i<loopTime; i++)
			LSDsort(arr, n, loopTime);
	}
	
	public void LSDsort(int arr[], int n, int loop){
		int buckets[][] = new int[10][n];
		int tempNum = (int) Math.pow(10, loop-1);
		for(int i = 0; i<n; i++){
			int row_index = arr[i] / tempNum;
			for(int j = 0; j<n; j++)
				if(buckets[row_index][j] == 0){
					buckets[row_index][j] = arr[i];
					break;
				}
		}
		//将桶中的数倒回到原有数组中
		int k = 0;
		for(int i = 0; i<10; i++)
			for(int j=0; j<n; j++)
				if(buckets[i][j] != 0){
					arr[k] = buckets[i][j];
					buckets[i][j] = 0;
					k++;
				}
	}
	
	private int findMaxNum(int arr[], int n){
		int max = arr[0];
		for(int i = 1; i<n; i++)
			if(arr[i] > max)
				max = arr[i];
		return max;
	}
	
	private int getLoopTime(int num){
		int count = 1;
		int temp = num/10;
		while(temp != 0){
			count++;
			temp = temp/10;
		}
		return count;
	}
}
