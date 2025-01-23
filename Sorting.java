import java.util.Arrays;

public class Sorting {
    // Merge Sort algorithm
    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return;
        }
        
        // Find the middle of the array
        int mid = array.length / 2;
        
        // Divide the array into two halves
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        
        // Recursively sort both halves
        mergeSort(left);
        mergeSort(right);
        
        // Merge the sorted halves
        merge(array, left, right);
    }
    
    // Merges two sorted subarrays into the original array
    public static void merge(int[] array, int[] left, int[] right) {
        int leftIndex = 0, rightIndex = 0, arrayIndex = 0;
        
        // Merge the left and right arrays into the original array in sorted order
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                array[arrayIndex++] = left[leftIndex++];
            } else {
                array[arrayIndex++] = right[rightIndex++];
            }
        }
        
        // Copy remaining elements of the left subarray (if any)
        while (leftIndex < left.length) {
            array[arrayIndex++] = left[leftIndex++];
        }
        
        // Copy remaining elements of the right subarray (if any)
        while (rightIndex < right.length) {
            array[arrayIndex++] = right[rightIndex++];
        }
    }

    public static  void quickSort(int[]arr,int left,int right){
        if(left<right){
            int pivotIndex=partion(arr,left,right);
            quickSort(arr, left, pivotIndex-1);
            quickSort(arr, pivotIndex+1, right);
        }
    }
    public static int partion(int[] arr,int low,int high){
        int pivot=arr[high];
        int i=low-1;

        for(int j=low;j<high;j++){
            if(arr[j]<pivot){
                i++;
                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
        }
        int temp=arr[i+1];
        arr[i+1]=arr[high];
         arr[high]=temp;
         return i+1;
    }
    public static void main(String[] args) {
      /*  System.out.println("This class is for the Sorting techinqe Use!....");

        int arr[]={13,4,484,36,934,83,334};
        System.out.println("Array Befor Sorting :"+ Arrays.toString(arr));

        mergeSort(arr);

        System.out.println("Array After Sorting:"+Arrays.toString(arr));
*/
         int arr[]={13,4,484,36,934,83,334};
        System.out.println("After Sorting in Quick Sort1!..."+Arrays.toString(arr));

        quickSort(arr, 0, arr.length-1);
     
        System.out.println("Array After Sorting:"+Arrays.toString(arr));
        
    }
}
