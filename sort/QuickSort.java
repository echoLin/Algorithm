package sort;

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
