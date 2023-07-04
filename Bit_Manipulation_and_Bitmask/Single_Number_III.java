/*
https://leetcode.com/problems/single-number-iii/description/

Logic Crux:
xor &= -xor;   
This sets the right most bit which will be used to classify the elements of array into two groups.
Further, we can xor all elements of each group to find the two elements.

*/

class Single_Number_III {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for(int ele: nums) {
            xor = xor ^ ele;
        }
        xor &= -xor;   
        int[] answer = new int[2];
        for(int ele: nums) {
            if((xor & ele)!=0) {
                answer[0] = answer[0] ^ ele;
            } else {
                answer[1] = answer[1] ^ ele; 
            }
        }
        return answer;
    }
}