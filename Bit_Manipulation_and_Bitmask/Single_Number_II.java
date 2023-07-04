/*
https://leetcode.com/problems/single-number-ii/description/

Logic Crux:
If an element appears first time, it will stored in seen_once.
If an element appears second time, it will stored in seen_twice.
If an element appears third time, it will be deleted from seen_twice.
*/  
class Single_Number_II {
    public int singleNumber(int[] nums) {
        int seen_once=0, seen_twice=0;

        for(int ele: nums) {
            seen_once = ~seen_twice & (seen_once ^ ele);
            seen_twice = ~seen_once & (seen_twice ^ ele);
        }
        return seen_once;
    }
}