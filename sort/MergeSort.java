package sort;

/**
 * 归并排序
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
