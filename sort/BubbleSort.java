package sort;

/**
 * 1.比较相邻的元素，如果第一个比第二个大，则健换位置
 * 2.每一对相邻的元素做同样的工作，从开始第一对到结尾的最后一对
 * 3.针对所有元素重复以上步骤
 * 4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要交换位置
 * 空间O(1)
 * 时间O(n^2)
 * @author echo
 * */
public class BubbleSort {
	public void Sort(int arr[], int n){
		for(int i = 1; i<n; i++)
			for(int j = 0; j<n-i; j++)
				if(arr[j+1] < arr[j]){
					int x = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = x;
				}
	}
}
