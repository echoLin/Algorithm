package sort;

/**
 * 快速排序
 * 通过一趟排序将要排序的数据分隔成独立的两部分，其中一部分的所有数据都比另一部分的所有数据都小
 * 然后再批次按方法对这两部分数据分别进行快速排序
 * 整个排序过程递归进行，以此达到有序
 * 空间O(1)
 * 时间O(nlogn)
 * @author echo
 * */
public class QuickSort {
	
	private int Partition(int left, int right, int arr[]){
		int privote = (left+right)/2 - 1;
		while(left <= right){
			while(left<=right && arr[right] >= privote) right--;
			arr[left] = arr[right];
			while(left<=right && arr[left] <= privote) left++;
			arr[right] = arr[left];
		}
		arr[left] = privote;
		return left;
	}
	
	public void Sort(int left, int right, int arr[]){
		if(left<=right){
			int mid = Partition(left, right, arr);
			Sort(left, mid-1, arr);
			Sort(mid+1, right, arr);
		}
	}
}
