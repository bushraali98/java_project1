import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String [][] board = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };

        String position;
        String x = "x";
        String o = "o";

        Random rand = new Random();

        do {

            // looping to display the board for the user
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j] + " | ");
                }
                System.out.println();
            }

            // Get the user input
            System.out.println("Please inter a number from (1-9) to play or 0 to quit");
            position = input.nextLine();

            // Quiting the game
            if (position.equals("0")) break;

            boolean validMove = false;

            // playing the user move
            while (!validMove) {
                boolean found = false;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j].equals(position)) {
                            board[i][j] = x; // play user move
                            found = true;
                            validMove = true;
                            break;
                        }
                    }
                    if (found) break;;
                }

                // asking the user to enter a new position, If the position is occupied
                if (!validMove) {
                    System.out.println("Position is already taken! enter another number:");
                    position = input.nextLine();
                }
            }

            // checking if there is winner
            String winner = checkWinner(board);
            if (winner != null) {
                System.out.println("We have a winner! The winner is: " + winner);
                break;
            }


            // Check if the board is full
            if (isBoardFull(board)) {
                System.out.println("It's a draw! The board is full.");
                break;
            }

            // Generating a random position
            String randPosition;
            do {
                int random = rand.nextInt(9) + 1;
                randPosition = Integer.toString(random);
            } while (!isPositionEmpty(board, randPosition));

           // playing 'o' in the generated random position
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].equals(randPosition)){
                        board[i][j] = o;
                        break;
                    }
                }
            }

            // checking if there is a winner
            winner = checkWinner(board);
            if (winner != null) {
                System.out.println("We have a winner! The winner is: " + winner);
                break;
            }

            // checking if the board is full
            if (isBoardFull(board)) {
                System.out.println("It is a draw! The board is full.");
            }

        } while (true
        );


        // Final display of the board
        System.out.println("Final board:");
        for (String[] row : board) {
            for (String cell : row) {
                System.out.print(cell + " | ");
            }
            System.out.println();
        }


    }

    public static boolean isPositionEmpty(String [][] board, String position) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals(position)) {
                    return true;
               }
            }
        }
        return false;
    }

    public static String checkWinner(String [][] board) {
        // checking for row match
        for (int i = 0; i < 3; i++){
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                return board[i][0];
            }
        }

        // checking for column match
        for (int i = 0; i < 3; i++) {
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                return board[0][i];
            }
        }

        // checking the diagonal match
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            return board[0][0];
        }

        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            return board[0][2];
        }

        return null; // no winner
    }

    public static boolean isBoardFull(String[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // checking if the cell contains 'x' or 'o'
                if (!board[i][j].equals("x") && !board[i][j].equals("o")) {
                    return false;
                }
            }
        }
        // If the board has no numbers, the board is full
        return true;
    }

}