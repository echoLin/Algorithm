package sort;

/**
 * 堆排序
 * 是一种树形选择排序，是对直接选择排序的有效改进
 * 堆的定义：具有n个元素的序列（k1,k2,...,kn)，当且仅当满足ki<=k2i&&ki<=k2i+1或者ki>=k2i&&ki>=k2+1，i=1,2,...[n/2]时称之为堆
 * 由堆的定义可知，堆顶元素即第一个元素为最小项（小顶堆）
 * 若以一维数组存储一个堆，则堆对应一颗完全二叉树，且所有非叶节点的值均不大于或不小于其子女的值
 * 1、建堆
 * 	建堆是不断调整堆的过程，从len/2处开始调整，直到第一个节点，此处len是堆中的元素个数
 * 2.调整堆
 * 	利用的思想是比较节点i和他的孩子节点left(i)和right(i)，选出三者最大或者最下者，如果醉倒或最小值不是节点i而是他的一个孩子节点，那么交换节点i和该节点，然后再调用调整堆过程，是一个递归的过程
 * 3.堆排序
 * 	首先根据元素构建堆，然后将堆的根节点取出（一般与最后一个节点进行交换），将前面len-1个节点继续进行堆调整的过程，然后再将根结点取出，这样一直到所有结点都取出。
 * 时间:O(nlogn)
 * @author echo
 * */
public class HeapSort {
	public void sort(int arr[], int n){
		//调整序列的前半部分元素，调整完一直第一个元素是序列的最大的元素
		//n/2-1是最后一个非叶结点
		for(int i = n/2-1; i>=0; i++)
			heapAdjust(arr, i, n);
		//从最后一个元素开始对序列进行调整，不断缩小调整的范围直到第一个元素
		for(int i = n-1; i>0; i--){
			//把第一个元素和当前最后一个元素交换
			//保证当前的最后一个位置的元素都是在现在这个序列之中最大的
			arr[i] = arr[0]^arr[i];
			arr[0] = arr[0]^arr[i];
			arr[i] = arr[0]^arr[i];
			//不断缩小调整堆的范围，每一次调整完毕保证第一个元素是当前序列的最大值
			heapAdjust(arr,0,i)
;		}
	}
	
	private void heapAdjust(int arr[], int i, int n){
		int nChild;
		int nTemp;
		for(; 2*i+1 < n; i=nChild){
			//子结点位置=2*父结点位置+1
			nChild = 2*i+1;
			//得到子结点中比较大的结点
			if(nChild<n-1 &&arr[nChild+1]>arr[nChild])
				nChild++;
			//如果较大的结点大于父结点，那么把较大的子结点往上移动，替换父结点
			if(arr[i] < arr[nChild]){
				nTemp = arr[i];
				arr[i] = arr[nChild];
				arr[nChild] = nTemp;
			}else
				break;
		}
	}
}
