package sort;

/**
 * 简单选择排序
 * 在要排序的数组中，选出最大或最小的一个数与第一个位置的数交换
 * 然后在剩下的数中选择最小或最大的一个数与第二个位置的数交换
 * 直至n-1个元素和第n个元素比较结束位置
 * 空间O(1)
 * 时间O(n^2)
 * 改进：二元选择排序，每趟循环确定两个元素，即最大最小元素的位置，这样只需要进行n/2次循环
 * @author echo
 * */
public class SimpleChooseSort {
	public void Sort(int arr[], int n){
		for(int i = 0; i<n; i++){
			int min = i;
			for(int j = i+1; j<n; j++){
				if(arr[j] < arr[min])
					min = j;
			}
			int x = arr[i];
			arr[i] = arr[min];
			arr[min] = x;
		}
	}
}
