package sort;

/**
 * 归并排序
 * 1、将有序的子序列进行合并，得到完全有序的序列
 * 2.申请空间，使其大小为两个已经排序序列之和的空间用于存放合并后的序列
 * 3.设定两个指针，最初位置分别为两个已经排序序列的起始位置
 * 重复3直到某一指针到下一位置
 * 4.将另一序列剩下的所有元素直接复制合并序列列尾
 * 空间:O(n)
 * 时间:O(nlogn)
 * @author echo
 */
public class MergeSort {
	public void sort(int a[], int b[], int l, int r){
		if(l<r){
			int mid = (l+r)/2 + 1;
			sort(a, b, l, mid);
			sort(a, b, mid+1, r);
			merge(a, b, l, mid, r);
		}
	}
	//合并
	private void merge(int a[], int b[], int l, int m, int r){
		int i = l;
		int j = m+1;
		int k = l;
		while(i<=m && j<=r)
			if(a[i]<a[j])
				b[k++] = a[i++];
			else
				b[k++] = a[j++];
		while(l<=m)
			b[k++] = a[i++];
		while(j<=r)
			b[k++] = a[j++];
	}
}
