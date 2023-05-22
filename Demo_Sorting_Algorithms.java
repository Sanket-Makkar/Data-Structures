import java.util.Arrays;
public class Demo
{
    public static void main(String[] args){
        Sort toTest = new Sort();
        
        int[] sizesArr = {10, 20, 50, 100, 200, 500, 1000, 2000, 5000};
        
        long sortedInit = 0, sortedFinal = 0, unsortedInit = 0, unsortedFinal = 0, reverseSortedInit = 0, reverseSortedFinal = 0;
        int[][] unsortedArrays = new int[sizesArr.length][];
        for (int i = 0; i < sizesArr.length; i++){
            unsortedArrays[i] = toTest.generateRandomArray(sizesArr[i]);
        }
        
        int[][] sortedArrays = unsortedArrays;
        for (int i = 0; i < sortedArrays.length; i++){
            toTest.insertionSort(sortedArrays[i]);
        }
        
        int[][] reverseSortedArrays = sortedArrays;
        for (int i = 0; i < sortedArrays.length; i++){
            int[] curr = new int[sortedArrays[i].length];
            int j = sortedArrays[i].length;
            for (int w = 0; w < sortedArrays[i].length; w++) {
                curr[j - 1] = reverseSortedArrays[i][w];
                j = j - 1;
            }
            
            reverseSortedArrays[i] = curr;
        }
        
        
        System.out.println("UnsortedArrays...");
        for (int j = 0; j < unsortedArrays.length; j++){
            int[] curr1 = unsortedArrays[j];
            
            unsortedInit = System.nanoTime();
            toTest.insertionSort(curr1);
            unsortedFinal = System.nanoTime();
            System.out.printf("insertionSort of size %d: %d | ", unsortedArrays[j].length, unsortedFinal - unsortedInit);
            
            int[] curr2 = unsortedArrays[j];
            unsortedInit = System.nanoTime();
            toTest.bubbleSort(curr2);
            unsortedFinal = System.nanoTime();
            System.out.printf("bubbleSort of size %d: %d | ", unsortedArrays[j].length, unsortedFinal - unsortedInit);
            
            int[] curr4 = unsortedArrays[j];
            unsortedInit = System.nanoTime();
            toTest.shellSort(curr4);
            unsortedFinal = System.nanoTime();
            System.out.printf("shellSort of size %d: %d | ", unsortedArrays[j].length, unsortedFinal - unsortedInit);
            
            int[] curr5 = unsortedArrays[j];
            unsortedInit = System.nanoTime();
            toTest.quickSort(curr5);
            unsortedFinal = System.nanoTime();
            System.out.printf("quickSort of size %d: %d | ", unsortedArrays[j].length, unsortedFinal - unsortedInit);
            
            int[] curr6 = unsortedArrays[j];
            unsortedInit = System.nanoTime();
            toTest.mergeSort(curr6);
            unsortedFinal = System.nanoTime();
            System.out.printf("mergeSort of size %d: %d | ", unsortedArrays[j].length, unsortedFinal - unsortedInit);
            
            int[] curr7 = unsortedArrays[j];
            unsortedInit = System.nanoTime();
            toTest.upgradedQuickSort(curr7, 2, 4);
            unsortedFinal = System.nanoTime();
            System.out.printf("upgradedQuickSort of size %d: %d | ", unsortedArrays[j].length, unsortedFinal - unsortedInit);
            
            int[] curr8 = unsortedArrays[j];
            unsortedInit = System.nanoTime();
            Arrays.sort(curr8);
            unsortedFinal = System.nanoTime();
            System.out.printf("Java sort of size %d: %d | ", unsortedArrays[j].length, unsortedFinal - unsortedInit);
            
            System.out.println();
        }
        
        System.out.println();
        System.out.println("sortedArrays...");
        for (int j = 0; j < sortedArrays.length; j++){
            int[] curr1 = sortedArrays[j];
            
            sortedInit = System.nanoTime();
            toTest.insertionSort(curr1);
            sortedFinal = System.nanoTime();
            System.out.printf("insertionSort of size %d: %d | ", sortedArrays[j].length, sortedFinal - sortedInit);
            
            int[] curr2 = sortedArrays[j];
            sortedInit = System.nanoTime();
            toTest.bubbleSort(curr2);
            sortedFinal = System.nanoTime();
            System.out.printf("bubbleSort of size %d: %d | ", sortedArrays[j].length, sortedFinal - sortedInit);
            
            int[] curr4 = sortedArrays[j];
            sortedInit = System.nanoTime();
            toTest.shellSort(curr4);
            sortedFinal = System.nanoTime();
            System.out.printf("shellSort of size %d: %d | ", sortedArrays[j].length, sortedFinal - sortedInit);
            
            int[] curr5 = sortedArrays[j];
            sortedInit = System.nanoTime();
            toTest.quickSort(curr5);
            sortedFinal = System.nanoTime();
            System.out.printf("quickSort of size %d: %d | ", sortedArrays[j].length, sortedFinal - sortedInit);
            
            int[] curr6 = sortedArrays[j];
            sortedInit = System.nanoTime();
            toTest.mergeSort(curr6);
            sortedFinal = System.nanoTime();
            System.out.printf("mergeSort of size %d: %d | ", sortedArrays[j].length, sortedFinal - sortedInit);
            
            int[] curr7 = sortedArrays[j];
            sortedInit = System.nanoTime();
            toTest.upgradedQuickSort(curr7, 2, 4);
            sortedFinal = System.nanoTime();
            System.out.printf("upgradedQuickSort of size %d: %d | ", sortedArrays[j].length, sortedFinal - sortedInit);
            
            int[] curr8 = sortedArrays[j];
            sortedInit = System.nanoTime();
            Arrays.sort(curr8);
            sortedFinal = System.nanoTime();
            System.out.printf("Java sort of size %d: %d | ", sortedArrays[j].length, sortedFinal - sortedInit);
            
            System.out.println();
        }
        
        System.out.println();
        System.out.println("reverseSortedArrays...");
        for (int j = 0; j < reverseSortedArrays.length; j++){
            int[] curr1 = reverseSortedArrays[j];
            
            reverseSortedInit = System.nanoTime();
            toTest.insertionSort(curr1);
            reverseSortedFinal = System.nanoTime();
            System.out.printf("insertionSort of size %d: %d | ", reverseSortedArrays[j].length, reverseSortedFinal - reverseSortedInit);
            
            int[] curr2 = reverseSortedArrays[j];
            reverseSortedInit = System.nanoTime();
            toTest.bubbleSort(curr2);
            reverseSortedFinal = System.nanoTime();
            System.out.printf("bubbleSort of size %d: %d | ", reverseSortedArrays[j].length, reverseSortedFinal - reverseSortedInit);
            
            int[] curr4 = reverseSortedArrays[j];
            reverseSortedInit = System.nanoTime();
            toTest.shellSort(curr4);
            reverseSortedFinal = System.nanoTime();
            System.out.printf("shellSort of size %d: %d | ", reverseSortedArrays[j].length, reverseSortedFinal - reverseSortedInit);
            
            int[] curr5 = reverseSortedArrays[j];
            reverseSortedInit = System.nanoTime();
            toTest.quickSort(curr5);
            reverseSortedFinal = System.nanoTime();
            System.out.printf("quickSort of size %d: %d | ", reverseSortedArrays[j].length, reverseSortedFinal - reverseSortedInit);
            
            int[] curr6 = reverseSortedArrays[j];
            reverseSortedInit = System.nanoTime();
            toTest.mergeSort(curr6);
            reverseSortedFinal = System.nanoTime();
            System.out.printf("mergeSort of size %d: %d | ", reverseSortedArrays[j].length, reverseSortedFinal - reverseSortedInit);
            
            int[] curr7 = reverseSortedArrays[j];
            reverseSortedInit = System.nanoTime();
            toTest.upgradedQuickSort(curr7, 2, 4);
            reverseSortedFinal = System.nanoTime();
            System.out.printf("upgradedQuickSort of size %d: %d | ", reverseSortedArrays[j].length, reverseSortedFinal - reverseSortedInit);
            
            int[] curr8 = reverseSortedArrays[j];
            reverseSortedInit = System.nanoTime();
            Arrays.sort(curr8);
            reverseSortedFinal = System.nanoTime();
            System.out.printf("Java sort of size %d: %d | ", reverseSortedArrays[j].length, reverseSortedFinal - reverseSortedInit);
            
            System.out.println();
        }
    }
}
