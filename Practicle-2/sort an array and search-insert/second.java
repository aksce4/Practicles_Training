import java.util.*;

class second{
    
    static int Search(int arr[], int n, int key){
       for (int i = 0; i < n; i++)
            if (arr[i] == key)
                return i;
      
        return -1;
    }

    static int insert(int arr[], int n, int key, int cap){
        if (n >= cap)
           return n;
      
        arr[n] = key;
      
        return (n + 1);
    }

    public static void main(String args[]){
        int n = 7;
        Scanner in = new Scanner(System.in);

        int[] arr = new int[10];
        System.out.println("Enter the number of element you want to insert less than 11: ");
        int ch = in.nextInt();
        
        System.out.println("Enter the Element in Array:");
        for(int q=0; q < ch; q++){
            arr[q] = in.nextInt();
        }

        
        //Searching 
        System.out.println("Enter Search Value:");
        int e = in.nextInt();
        int s1 = Search(arr, ch, e);
        System.out.println("Found at: "+ s1);

        //Insertion
        int cap = arr.length;  
        System.out.println("Enter Insert Value:");
        int key = in.nextInt();
        n = insert(arr, ch, key, cap);    
    
        System.out.println("After Insertion: ");
        for(int i=0; i<n; i++){
            System.out.println(arr[i]);
        } 

        //Sorting 
        Arrays.sort(arr);

        System.out.println("After Sorting");
        for(int j=0;j<arr.length;j++){
            System.out.println(arr[j]);
        }
 
    } 
}