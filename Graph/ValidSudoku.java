package graphs.intuition.problems;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {

	public static void main(String[] args) {
		char[][] sudoku = {
			    {'.','.','.','.','5','.','.','1','.'},
			    {'.','4','.','3','.','.','.','.','.'},
			    {'.','.','.','.','.','3','.','.','1'},
			    {'8','.','.','.','.','.','.','2','.'},
			    {'.','.','2','.','7','.','.','.','.'},
			    {'.','1','5','.','.','.','.','.','.'},
			    {'.','.','.','.','.','2','.','.','.'},
			    {'.','2','.','9','.','.','.','.','.'},
			    {'.','.','4','.','.','.','.','.','.'}
			};
		System.out.println("Is valid? = " + isValidSudoku(sudoku));
	} 
	
	private static boolean isValidSudoku(char[][] board) {
        boolean row = true;
        boolean column = true;

        for(int i=0;i<9;i++){
            if(row && column){
                row = rowCheck(board, i);
                column = columnCheck(board, i);
            } else {
                break;
            } 
        }
        
        return row && column ? 
            threeXThreeCheck(board,0,3,0,3) && threeXThreeCheck(board,0,3,3,6) &&
            threeXThreeCheck(board,0,3,6,9) && threeXThreeCheck(board,3,6,0,3) &&
            threeXThreeCheck(board,3,6,3,6) && threeXThreeCheck(board,3,6,6,9) &&
            threeXThreeCheck(board,6,9,0,3) && threeXThreeCheck(board,6,9,3,6) &&
            threeXThreeCheck(board,6,9,6,9) : false;
        
    }
    
    private static boolean rowCheck(char[][] board, int rowNumber){
        Set<Character> set = new HashSet<>();
        for(int i=0;i<9;i++){
            if(board[rowNumber][i]!='.' && !set.add(board[rowNumber][i])) 
                return false;
        }
        return true;
    }
    
    private static boolean columnCheck(char[][] board, int columnNumber){
        Set<Character> set = new HashSet<>();
        for(int i=0;i<9;i++){
            if(board[i][columnNumber]!='.' && !set.add(board[i][columnNumber])) 
                return false;
        }
        return true;
    }
    
    private static boolean threeXThreeCheck(char[][] board, int iStart, int iEnd, int jStart, int jEnd){
        Set<Character> set = new HashSet<>();
        int temp = jStart;
        while(iStart < iEnd){
        	jStart = temp;
            while(jStart<jEnd){
            	char ch = board[iStart][jStart];
                if(ch!='.' && !set.add(ch))
                    return false;
                jStart++;
                
            }
            iStart++;
        }
        
        System.out.println("IStart: " + iStart + "; JStart: " + jStart + "=> true");
        return true;
    }
}
