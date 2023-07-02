package graphs.intuition.problems;

public class ClosedIslands {

	public static void main(String[] args) {
		int[][] grid = { { 0, 0, 1, 1, 0, 1, 0, 0, 1, 0 }, { 1, 1, 0, 1, 1, 0, 1, 1, 1, 0 },
				{ 1, 0, 1, 1, 1, 0, 0, 1, 1, 0 }, { 0, 1, 1, 0, 0, 0, 0, 1, 0, 1 }, { 0, 0, 0, 0, 0, 0, 1, 1, 1, 0 },
				{ 0, 1, 0, 1, 0, 1, 0, 1, 1, 1 }, { 1, 0, 1, 0, 1, 1, 0, 0, 0, 1 }, { 1, 1, 1, 1, 1, 1, 0, 0, 0, 0 },
				{ 1, 1, 1, 0, 0, 1, 0, 1, 0, 1 }, { 1, 1, 1, 0, 1, 1, 0, 1, 1, 0 } };
		System.out.println("Number of Islands = " + closedIsland(grid));
	}

	private static int closedIsland(int[][] grid) {
		int l = grid.length;
		int b = grid[0].length;

		int count = 0;
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < b; j++) {
				if (grid[i][j] == 0 && isClosedIsland(grid, i, j, l, b)) {
					System.out.println("Closed Island found at ( i ="  + i + ", j = "  + j + ")");
					count++;
				}
			}
		}
		return count;
	}

	private static boolean isClosedIsland(int[][] grid, int i, int j, int l, int b) {
		if (i < 0 || i >= l || j < 0 || j >= b) {
			return false;
		}
		if (grid[i][j] == 1 || grid[i][j] == -1)
			return true;
		grid[i][j] = -1;
		boolean value1 = isClosedIsland(grid, i - 1, j, l, b);
		boolean value2 = isClosedIsland(grid, i, j - 1, l, b);
		boolean value3 = isClosedIsland(grid, i, j + 1, l, b);
		boolean value4 = isClosedIsland(grid, i + 1, j, l, b);
		return value1 && value2 && value3 && value4;
	}
}
