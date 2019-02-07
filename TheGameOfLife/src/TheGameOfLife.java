import java.util.Random;

public class TheGameOfLife {

	public static void main(String[] args) {
		// declaring global variables which will be used to establish the area of the 2d
		// grid.
		int rows = 10;
		int cols = 10;
		int generations = 3;
		// use global variables as parameters to create a grid
		int[][] tempArray = createGrid(rows, cols);
		printGrid(tempArray);

		// loop through generations.
		for (int i = 0; i < generations; i++) {
			System.out.println("===== Gen: " + i);
			printGrid(lifeRules(tempArray));
		}

	}

	public static int[][] createGrid(int rows, int cols) {
		// create 2d array
		int[][] myArray = new int[rows][cols];
		// iterate through the 2d array and populate and randomly assign live and death
		// indexes 0-1
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {

				Random rand = new Random();
				int num = rand.nextInt(2);
				myArray[i][j] = num;
			}
		}

		return myArray;
	}

	// iterate throughout whole 2d array and print out every index
	public static void printGrid(int[][] array) {
		int[][] tempArray = array;
		for (int i = 0; i < tempArray.length; i++) {
			for (int j = 0; j < tempArray[i].length; j++) {
				System.out.print(tempArray[i][j]);

			}
			System.out.println("");
		}
	}

	// iterate through 2d array and accumulate any living cells, if the total
	// accumulation is 0, this will signify the end of the game.
	public static boolean checkGame(int[][] grid) {
		int[][] tempArray = grid;
		int tempNum = 0;
		for (int i = 0; i < tempArray.length; i++) {
			for (int j = 0; j < tempArray[i].length; j++) {
				tempNum = tempNum + tempArray[i][j];
			}
		}

		if (tempNum == 0) {
			System.out.println("GAME OVER");
			return true;
		}
		// if the total accumulation is not equal to 0, then the game is not over.
		System.out.println("GAME IS NOT OVER");
		return false;
	}

	public static int[][] lifeRules(int[][] grid) {
		int[][] tempArray = grid;
		// Due to the infinite grid field assumption, the corners will
		// not directly be iterated over, but will be counted as neighbours.
		for (int i = 1; i < tempArray.length - 1; i++) {
			for (int j = 1; j < tempArray[i].length - 1; j++) {
				System.out.print("index:" + tempArray[i][j]);
				// Initialise integer variable, to store the neighbours.
				int neighbours = 0;
				// due to there being 8 neighbours for each index, iterate through all 8
				// Corresponding neighbour indexes.
				for (int k = -1; k <= 1; k++) {
					for (int l = -1; l <= 1; l++) {
						// retrieve neighbours value and add it to neighbour variable.
						neighbours = neighbours + tempArray[i + k][j + l];

					}

				}
				neighbours = neighbours - tempArray[i][j];
				// if the cell is alive (=1) and neighbours are less than 2, then the cell dies
				if ((tempArray[i][j] == 1) && (neighbours < 2)) {
					tempArray[i][j] = 0;
					// if the cell is alive and has more than three neighbours, it dies.
				} else if ((tempArray[i][j] == 1) && (neighbours > 3)) {
					tempArray[i][j] = 0;
					// if the cell is dead and has exactly 3 neighbours, a cell is created in its
					// place
				} else if ((tempArray[i][j] == 0) && (neighbours == 3))
					tempArray[i][j] = 1;

			}

		}
		// pass 2d array into checkGame function to determine if there are any live
		// cells.
		if (checkGame(tempArray) == true) {

		}

		return grid;
	}

}
