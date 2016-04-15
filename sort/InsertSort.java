package sort;

/**
 * 直接插入排序（稳定）
 * 将一个记录插入到已排序好的有序表中，从而等到一个新的有序表
 * 即序列的第一个记录看成是一个有序的子序列，然后从第二个记录逐个进行插入，直至整个序列有序为止
 * 要点：
 * 	设立哨兵，作为临时存储和判断数组边界
 * 空间O(1)
 * 时间O(n^2)
 * @author echo
 * */
public class InsertSort {
	public void Sort(int arr[], int n){
		for(int i = 1; i< n; i++)
			if(arr[i] < arr[i-1]){
				int j = i-1;
				int x = arr[i];
				while(x < arr[j]){
					arr[j+1] = arr[j];
					j--;
				}
				arr[j+1] = x;
			}
	}
}
