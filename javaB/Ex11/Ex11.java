/**
 * This class represents a Mamn11
 * @author David Weisman
 * @version 29-04-2023
 */
public class Ex11
{
    /**
    * Finds the shortest path from the start point to the end point with one possibility of crossing to the other road
    * Time all complexity is O(n) because in every function (shortestRoad, BuildingArrToEnd, BuildingArrWithAcross, MinOfArr) the complexity is O(n) so all complexity is O(n).
    * Space all complexity is O(1) because in evert function (shortestRoad, BuildingArrToEnd, BuildingArrWithAcross, MinOfArr) the complexity is O(1) so all complexity is O(1). 
    * The time complexity is O(1) because there is one for loop that runs 3 times. 
    * The space complexity is O(n) because there is 4 arrays (arrRoad1ToEnd, arrRoad2ToEnd, arrRoad1WithAcross, arrRoad2WithAcross) with the same size as the roads array, meaning the space complexity O(4n) meaning O(n). There is another array with 4 cells and one variable, so all space complexity is O(n). 
    * @param raod1 the first raod
    * @param raod2 the second raod
    * @return the amount of time of the shortest raod
    */
    public static int shortestRoad (int [] road1, int [] road2)
    {
        
        // An arrays of the amount of time from each point on each road to its end without crossing the second road.
        int[] arrRoad1ToEnd = new int[road1.length];
        int[] arrRoad2ToEnd = new int[road1.length];
        
        // An arrays of the amount of time from the beginning of one of the road and across to the second road from any point on the first road to the end of the second road.
        int[] arrRoad1WithAcross = new int[road1.length];
        int[] arrRoad2WithAcross = new int[road1.length];
        
        arrRoad1ToEnd = BuildingArrToEnd(road1);
        arrRoad2ToEnd = BuildingArrToEnd(road2);
        
        arrRoad1WithAcross = BuildingArrWithAcross(road1, arrRoad2ToEnd);
        arrRoad2WithAcross = BuildingArrWithAcross(road2, arrRoad1ToEnd);
        
        // An arr of the shortest time to move from one side to the other for each road.
        int[] minOfAllRoads = new int[]{arrRoad1ToEnd[0], arrRoad2ToEnd[0], MinOfArr(arrRoad1WithAcross), MinOfArr(arrRoad2WithAcross)};
        
        // Varible of the shortest time.
        int min = minOfAllRoads[0];
        
        // Loop that finds the shortest time.
        for(int i = 1; i < minOfAllRoads.length; i++)
        {
            if(minOfAllRoads[i] < min)
            {
                min = minOfAllRoads[i];
            }
        }
        
        // The output of the shortest time to move from one side to the other.
        return min;
    }
    
    /**
    * Function for shortestRoad that builds the arrays of the amount of time from each point on each road to its end without crossing the second road.
    * The time complexity is O(n) because there is one for loop that runs until the end of the array. 
    * The space complexity is O(n) because there is tow variable (lenOfArrys, sum) and one array (arr) with the size of the roads so all space complexity is O(n). 
    * @param raod arry represent the road describe in the exercises 
    * @return the arry of the same size of the road, in which each call represent the time it will take to complete the journey without crossing the road    
    */
    public static int[] BuildingArrToEnd(int[] road)
    {
        int lenOfArrys = road.length; 
        int [] arr = new int[lenOfArrys];
        int sum = 0;
        for(int i = 0; i< lenOfArrys; i++)
        {
            sum += road[lenOfArrys - i - 1];
            arr[lenOfArrys - 1 - i] = sum;
        }
        return arr;
    }
    
    /**
     * Function for shortestRoad that builds the arrays of the amount of time from the beginning of one of the road and across to the second road from each point on the first road to the end of the second road.
     * The time complexity is O(n - 1) because the for loop is running on top of the original array, so the time complexity is O(n). 
     * The space complexity is O(n) because there are two variables (lenOfArrys, sum) and one array with the same size as the roads, so the space complexity is O(n). 
     * @param raod1 the firs road 
     * @param raod2 the second road
     * @return the arry fo the seme size as the two roads, in which each call represent the amount of time it will from the beginning of one of the road and across to the second road from each point on the first road to the end of the second road.
     */
    public static int[] BuildingArrWithAcross(int[] road1, int[] road2)
    {
        int lenOfArrys = road1.length;
        int [] arr = new int[lenOfArrys];
        int sum = 0;
        for(int i = 0; i< lenOfArrys - 1; i++)
        {
            sum += road1[i]; 
            arr[i] = road2[i + 1] + sum;
        }
        return arr;
    }
    
    /**
     * Function for shortestRoad that finds the shortest time from arr
     * The time complexity is O(n – 1 ) because the for loop is running on top of the original array, so the time complexity is O(n). 
     * The space complexity is O(1) because there are 2 variables (lenOfArrys, sum), so the space complexity is O(1). 
     * @param arr of shortest times to across from the start of the roads to the finish of the roads 
     * @return the shortest amount of time in the arr 
     */
    public static int MinOfArr(int[] arr)
    {
        int lenOfArrys = arr.length;
        int min = arr[0];
        for(int i = 1; i < lenOfArrys - 1; i++)
        {
            if(arr[i] < min)
            {
                min = arr[i];
            }
        }
        return min;
    }
    
    /**
     * Finds the missing number in an invoice series
     * The time complexity is O(log n) because we have only one while loop, that is getting smaller every run by 2. 
     * The space complexity is O(1) because there are 4 variables (diff, min, max, temp), meaning O(4) so the space complexity is O(1). 
     * @param arr the invoice series
     * @return the missing number
     */
    public static int missingValue (int [] arr)
    {
        //Finding the missing number in an array of 2
        if(arr.length == 2)
        {
            return ((arr[0] + arr[1]) / 2);
        }
        
        //Finding the difference of the invoice series
        int diff;
        if((arr[1] - arr[0]) < (arr[2] - arr[1]))
        {
            diff = arr[1] - arr[0];
        }
        else
        {
            diff = arr[2] - arr[1];
        }
        
        //Finding the missing number using the Binary Search method
        int min = 0;
        int max = arr.length - 1;
        int temp = (max + min) / 2;
        while(max != min + 1)
        {
            temp = (max + min) / 2;
            if((max - temp) * diff == arr[max] - arr[temp])
            {
                max = temp;
            }
            else
            {
                min = temp;
            }
        }
        return ((arr[min] + arr[max]) / 2);
    }
}
