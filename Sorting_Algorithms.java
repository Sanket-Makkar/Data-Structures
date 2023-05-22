public class Sort{
    //insertion sort
    public static void insertionSort(int[] arr){
        for (int i = 1; i < arr.length; i++){
            int tempI = i;
            while (tempI - 1 >= 0){
                if (arr[tempI] > arr[tempI - 1] && tempI - 1 >= 0){
                    int tempVal = arr[tempI];
                    arr[tempI] = arr[tempI - 1];
                    arr[tempI - 1] = tempVal;
                }
                tempI--;
            }
        }
    }


    public static void bubbleSort(int[] arr){
        int swapCount = -1;
        while (swapCount != 0){
            swapCount = 0;
            for (int i = 0; i < arr.length - 1; i++){
                if (arr[i] < arr[i + 1]){
                    int tempNum = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tempNum;
                    swapCount++;
                }
            }
        }
    }


    public static void shellSort(int[] arr){
        int increment = 1;
        while (2 * increment <= arr.length) 
            increment *= 2;  // starting incr = floor(log arr.length)
        increment -= 1;
        while (increment >= 1) {
            int j = 0;
            for (int i = increment; i < arr.length; i++) {
            int toInsert = arr[i];
            for(j = i; j > increment - 1 && toInsert > arr[j - increment]; j = j - increment) 
                arr[j] = arr[j - increment];
            arr[j] = toInsert;
            }
            increment = increment/2;
        }
    }
    


    public static void quickSort(int[] arr){
        quickSortHelper(arr, 0, arr.length - 1);
    }
    private static void quickSortHelper(int[] arr, int first, int last){
        if (first < last){
            int jPos = partition(arr, first, last);
            
            quickSortHelper(arr,first, jPos - 1);
            quickSortHelper(arr, jPos, last);
        }
        else{
            return;
        }
    }
    private static int partition(int[] arr, int first, int last){
        int pivot = 0;
        
        int num1 = (int)Math.floor(Math.random()*(last-first+1)+first);
        
        int num2 = (int)Math.floor(Math.random()*(last-first+1)+first);
        
        int num3 = (int)Math.floor(Math.random()*(last-first+1)+first);
        
        pivot = arr[num3];

        if (arr[num2] <= arr[num3] || arr[num2] <= arr[num1])
            if (arr[num2] >= arr[num3] || arr[num2] >= arr[num1])
                pivot = arr[num2];
        
        if (arr[num1] <= arr[num2] || arr[num1] <= arr[num3])
            if (arr[num1] >= arr[num2] || arr[num1] >= arr[num3])
                pivot = arr[num1];
        
        int i = first;
        int j = last;
        
        boolean iTrue = false;
        boolean jTrue = false;
        while (i <= j){
            if (arr[i] > pivot)
                i++;
            else
                iTrue = true;
            
            if (arr[j] < pivot)
                j--;
            else
                jTrue = true;
            
            if (iTrue == true && jTrue == true){
                int tempVal = arr[i];
                arr[i] = arr[j];
                arr[j] = tempVal;
                
                i++;
                j--;
                
                jTrue = false;
                iTrue = false;
            }
        }
        return j+1;
    }


    public static void mergeSort(int[] arr){
        if (arr.length <= 1)
            return;
        
        int[] leftArr = new int[((arr.length + (arr.length%2))/2)];
        int[] rightArr = new int[((arr.length - (arr.length%2))/2)];
        
        splitter(arr, leftArr, rightArr);

        mergeSort(leftArr);
        mergeSort(rightArr);

        merger(arr, leftArr, rightArr);
    }
    private static void merger(int[] arr, int[] leftArr, int[] rightArr){
        int[] totalArr = new int[leftArr.length + rightArr.length];

        int i = 0;
        int j = 0;
        int pos = 0;
        
        while (i < leftArr.length && j < rightArr.length){
            if(leftArr[i] >= rightArr[j]){
                totalArr[pos] = leftArr[i];
                i++;
            }
            else{
                totalArr[pos] = rightArr[j];
                j++;
            }
            pos++;
        }
        
        while(i < leftArr.length){
            totalArr[pos] = leftArr[i];
            i++;
            pos++;
        }
        
        while(j < rightArr.length){
            totalArr[pos] = rightArr[j];
            j++;
            pos++;
        }

        for (int totalPos = 0; totalPos < pos; totalPos++){
            arr[totalPos] = totalArr[totalPos];
        }
    }
    private static void splitter(int[] arr, int[] leftSplitArr, int[] rightSplitArr){
        int i = 0;
        for (i = i; i < leftSplitArr.length; i++){
            leftSplitArr[i] = arr[i];
        }
        
        for (int j = 0; j < rightSplitArr.length; j++){
            rightSplitArr[j] = arr[j + i];
        }
    }


    public static void upgradedQuickSort(int[] input, int d, int k){
        helperQuickSortUpgraded(input, 0, input.length - 1, d);
        helperMergeSortUpgraded(input, k);
    }
    private static void helperQuickSortUpgraded(int[] arr, int first, int last, int d){
        d--;
        if (d > 0 && first < last){
            int jPos = partition(arr, first, last);
            
            helperQuickSortUpgraded(arr,first, jPos - 1, d);
            helperQuickSortUpgraded(arr, jPos, last, d);
        }
        else{
            return;
        }
    }
    private static void helperMergeSortUpgraded(int[] arr, int k){
        if (arr.length <= k){
            insertionSort(arr);
            return;
        }
        else{
            int[] leftArr = new int[((arr.length)/2)];
            int[] rightArr = new int[((arr.length)/2)];
            
            splitter(arr, leftArr, rightArr);

            mergeSort(leftArr);
            mergeSort(rightArr);

            merger(arr, leftArr, rightArr);
        }
    }


    public static int[] generateRandomArray(int n){
        int[] randomArr = new int[n];

        for (int i = 0; i < n; i++){
            randomArr[i] = (int)Math.floor(Math.random()*(100000 + 1));
        }
        
        return randomArr;
    }

    public static void readCommands(String filepath){
        String[] words = filepath.split("\\s+");
        if (words[0].compareTo("upgradedQuickSort:") == 0){
            int d = Integer.parseInt(words[1]);
            int k = Integer.parseInt(words[3]);

            words[4] = words[4].substring(1, words[4].length() - 1);
            words[4] = words[4].replace(",", " ");
            String[] stringOfNumbers = words[4].split("\\s+");
            int[] arrayOfNums = new int[stringOfNumbers.length];
            for (int i = 0; i < arrayOfNums.length; i++){
                arrayOfNums[i] = Integer.parseInt(stringOfNumbers[i]);
            }

            upgradedQuickSort(arrayOfNums, d, k);
            System.out.print("upgradedQuickSort: [");
            for(int i = 0; i < arrayOfNums.length - 1; i++){
                System.out.print(arrayOfNums[i]);
                System.out.print(",");
            }
            System.out.print(arrayOfNums[arrayOfNums.length - 1]);
            System.out.println("] ");
            return;
        }
        
        words[1] = words[1].substring(1, words[1].length() - 1);
        words[1] = words[1].replace(",", " ");
        String[] stringOfNumbers = words[1].split("\\s+");
        int[] arrayOfNums = new int[stringOfNumbers.length];
        for (int i = 0; i < arrayOfNums.length; i++){
            arrayOfNums[i] = Integer.parseInt(stringOfNumbers[i]);
        }

        
        if (words[0].compareTo("insertionSort:") == 0){
            insertionSort(arrayOfNums);
            System.out.print("insertionSort: [");
        }
        else if (words[0].compareTo("bubbleSort:") == 0){
            bubbleSort(arrayOfNums);
            System.out.print("bubbleSort: [");
        }
        else if (words[0].compareTo("shellSort:") == 0){
            shellSort(arrayOfNums);
            System.out.print("shellSort: [");
        }
        else if (words[0].compareTo("quickSort:") == 0){
            quickSort(arrayOfNums);
            System.out.print("quickSort: [");
        }
        else if (words[0].compareTo("mergeSort:") == 0){
            mergeSort(arrayOfNums);
            System.out.print("mergeSort: [");
        }
        else {
            System.out.println("not valid input");
            return;
        }
        
        for(int i = 0; i < arrayOfNums.length - 1; i++){
            System.out.print(arrayOfNums[i]);
            System.out.print(",");
        }
        System.out.print(arrayOfNums[arrayOfNums.length - 1]);
        System.out.println("] ");
    }
}