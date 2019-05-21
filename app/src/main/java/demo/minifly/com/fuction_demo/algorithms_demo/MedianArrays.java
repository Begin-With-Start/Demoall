package demo.minifly.com.fuction_demo.algorithms_demo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 已经排序好的数组进行合并找中间的数据，进行取数据，同时要求时间复杂度为O(log(m+n))
 */
public class MedianArrays {
	public static void main(String arg[]){

		int [] array1 = new int[]{1,2};
		int [] array2 = new int[]{3,4};
		
		double result = findMedianSortedArrays(array1, array2);
		System.out.println("result 结果是:" + result);
		
	}
	
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		double result = 0d;
		Set<Integer> set = new HashSet();
		
		if(nums1==null || nums2==null){
			return result;
		}
		
        for(int i = 0 ; i < (nums1.length >= nums2.length?nums1.length:nums2.length) ; i ++ ){
        	if(i<nums1.length){
            	set.add(nums1[i]);
        	}
        	if(i<nums2.length){
        		set.add(nums2[i]);
        	}
        }

        boolean twoOrNot = false;
        int count = 0,needCount = 0,oneNum = 0 , twoNum = 0;
        if(set.size()%2==0){//偶数的合并集合
        	twoOrNot = true;
        	needCount = set.size()/2;
        }else{//基数的合并集 合
        	twoOrNot = false;
        	needCount = (set.size()+1)/2;
        }
        Iterator<Integer> ite = set.iterator();
        while(ite.hasNext()){
        	count ++;
        	if(twoOrNot){//偶数的处理
        		if(count == needCount){
        			oneNum = ite.next();
        		}else if(count==(needCount+1)){
        			twoNum = ite.next();
        		}else{
        			ite.next();
        		}
        	}else{//基数的处理
        		if(count == needCount){
        			oneNum = ite.next();
        			twoNum = oneNum;
        		}else{
        			ite.next();
        		}
        	}
        }
		
		return ((double)oneNum+(double)twoNum)/2;
    }
}
