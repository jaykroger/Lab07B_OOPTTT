import javax.swing.*;

public class TicTacToeRunner
{
    public static void main(String[] args)
    {
        TicTacToeBoard ticTacToe = new TicTacToeBoard();

        ticTacToe.setTitle("Rock Paper Scissors");
        ticTacToe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ticTacToe.setSize(1080, 560);
        ticTacToe.setLocation(100, 80);
        ticTacToe.setVisible(true);
    }
}