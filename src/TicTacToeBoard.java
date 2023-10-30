import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToeBoard extends JFrame
{
    // Panels
    JPanel mainPanel;
    JPanel titlePanel;
    JPanel gamePanel;
    JPanel buttonPanel;


    // Panel Assets

    // Title Panel
    JLabel title;

    // Game Panel
    static TicTacToeTile topLeftTile;
    static TicTacToeTile topMiddleTile;
    static TicTacToeTile topRightTile;

    static TicTacToeTile centerLeftTile;
    static TicTacToeTile centerMiddleTile;
    static TicTacToeTile centerRightTile;

    static TicTacToeTile bottomLeftTile;
    static TicTacToeTile bottomMiddleTile;
    static TicTacToeTile bottomRightTile;

    // Button Panel
    JButton quitButton;

    private static final int ROW = 3;
    private static final int COL = 3;
    private static TicTacToeTile[][] board = new TicTacToeTile[ROW][COL];
    public static TicTacToePlayer player = new TicTacToePlayer("X");
    private int turnCounter;

    public TicTacToeBoard()
    {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createTitlePanel();
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        createGamePanel();
        mainPanel.add(gamePanel, BorderLayout.CENTER);

        createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Create Panels
    private void createTitlePanel()
    {
        titlePanel = new JPanel();
        title = new JLabel("Tic Tac Toe");
        title.setFont(new Font("Montserrat", Font.PLAIN, 48));
        titlePanel.add(title);
    }

    private void createGamePanel()
    {
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));

        topLeftTile = new TicTacToeTile(0, 0, player, this);
        topMiddleTile = new TicTacToeTile(0, 1, player, this);
        topRightTile = new TicTacToeTile(0, 2, player, this);
        board[topLeftTile.getRow()][topLeftTile.getCol()] = topLeftTile;
        board[topMiddleTile.getRow()][topMiddleTile.getCol()] = topMiddleTile;
        board[topRightTile.getRow()][topRightTile.getCol()] = topRightTile;

        centerLeftTile = new TicTacToeTile(1, 0, player, this);
        centerMiddleTile = new TicTacToeTile(1, 1, player, this);
        centerRightTile = new TicTacToeTile(1, 2, player, this);
        board[centerLeftTile.getRow()][centerLeftTile.getCol()] = centerLeftTile;
        board[centerMiddleTile.getRow()][centerMiddleTile.getCol()] = centerMiddleTile;
        board[centerRightTile.getRow()][centerRightTile.getCol()] = centerRightTile;

        bottomLeftTile = new TicTacToeTile(2, 0, player, this);
        bottomMiddleTile = new TicTacToeTile(2, 1, player,this);
        bottomRightTile = new TicTacToeTile(2, 2, player,this);
        board[bottomLeftTile.getRow()][bottomLeftTile.getCol()] = bottomLeftTile;
        board[bottomMiddleTile.getRow()][bottomMiddleTile.getCol()] = bottomMiddleTile;
        board[bottomRightTile.getRow()][bottomRightTile.getCol()] = bottomRightTile;


        gamePanel.add(topLeftTile);
        gamePanel.add(topMiddleTile);
        gamePanel.add(topRightTile);

        gamePanel.add(centerLeftTile);
        gamePanel.add(centerMiddleTile);
        gamePanel.add(centerRightTile);

        gamePanel.add(bottomLeftTile);
        gamePanel.add(bottomMiddleTile);
        gamePanel.add(bottomRightTile);

    }

    private void createButtonPanel()
    {
        buttonPanel = new JPanel();
        quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        quitButton.addActionListener((ActionEvent ae) -> getQuitConfirm());
        buttonPanel.add(quitButton);
    }

    private static boolean isWin(String player)
    {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }
    private static boolean isColWin(String playerSymbol)
    {
        // checks for a col win for specified player
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].getText().equals(playerSymbol) &&
                    board[1][col].getText().equals(playerSymbol) &&
                    board[2][col].getText().equals(playerSymbol))
            {
                return true;
            }
        }
        return false; // no col win
    }
    private static boolean isRowWin(String playerSymbol)
    {
        // checks for a row win for the specified player
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].getText().equals(playerSymbol) &&
                    board[row][1].getText().equals(playerSymbol) &&
                    board[row][2].getText().equals(playerSymbol))
            {
                return true;
            }
        }
        return false; // no row win
    }
    private static boolean isDiagonalWin(String playerSymbol)
    {
        // checks for a diagonal win for the specified player

        if(board[0][0].getText().equals(playerSymbol) &&
                board[1][1].getText().equals(playerSymbol) &&
                board[2][2].getText().equals(playerSymbol))
        {
            return true;
        }
        return board[0][2].getText().equals(playerSymbol) &&
                board[1][1].getText().equals(playerSymbol) &&
                board[2][0].getText().equals(playerSymbol);
    }

    // checks for a tie before board is filled.
    // check for the win first to be efficient
    private static boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].getText().equals("X") ||
                    board[row][1].getText().equals("X") ||
                    board[row][2].getText().equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(board[row][0].getText().equals("O") ||
                    board[row][1].getText().equals("O") ||
                    board[row][2].getText().equals("O"))
            {
                oFlag = true; // there is an O in this row
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }

            xFlag = oFlag = false;

        }
        // Now scan the columns
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].getText().equals("X") ||
                    board[1][col].getText().equals("X") ||
                    board[2][col].getText().equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(board[0][col].getText().equals("O") ||
                    board[1][col].getText().equals("O") ||
                    board[2][col].getText().equals("O"))
            {
                oFlag = true; // there is an O in this col
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }
        // Now check for the diagonals
        xFlag = oFlag = false;

        if(board[0][0].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][2].getText().equals("X") )
        {
            xFlag = true;
        }
        if(board[0][0].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][2].getText().equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diagonal win
        }
        xFlag = oFlag = false;

        if(board[0][2].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][0].getText().equals("X") )
        {
            xFlag =  true;
        }
        if(board[0][2].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][0].getText().equals("O") )
        {
            oFlag =  true;
        }
        return xFlag && oFlag; // No tie can still have a diagonal win

        // Checked every vector so I know I have a tie
    }

    private static void resetBoard()
    {
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                board[row][col].setText("");
            }
        }
    }

    public void increaseTurnCounter()
    {
        turnCounter++;
        showBoard();
    }

    private void getQuitConfirm()
    {
        int quitConfirm;
        quitConfirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);

        if (quitConfirm == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }

    public void checkWinOrTie()
    {
        if (this.turnCounter >= 5)
        {
            if (isWin(player.getSymbol()))
            {
                int playAgain;
                playAgain = JOptionPane.showConfirmDialog(null, player.getSymbol() +  " wins.\nPlay Again?", "Play Again?", JOptionPane.YES_NO_OPTION);
                if (playAgain == JOptionPane.YES_OPTION)
                {
                    resetBoard();
                }
                else
                {
                    System.exit(0);
                }
            }
            else if (turnCounter >= 7)
            {
                if (isTie())
                {
                    int playAgain;
                    playAgain = JOptionPane.showConfirmDialog(null, "Tie.\nPlay Again?", "Play Again?", JOptionPane.YES_NO_OPTION);
                    if (playAgain == JOptionPane.YES_OPTION)
                    {
                        resetBoard();
                    }
                    else
                    {
                        System.exit(0);
                    }
                }
            }
        }
    }

    // Console output for testing and validation
    private void showBoard()
    {
        System.out.println();
        System.out.print(board[0][0].getText() + " | " + board[0][1].getText() + " | " + board[0][2].getText() + "\n");
        System.out.print(board[1][0].getText() + " | " + board[1][1].getText() + " | " + board[1][2].getText() + "\n");
        System.out.print(board[2][0].getText() + " | " + board[2][1].getText() + " | " + board[2][2].getText() + "\n");
        System.out.println("Current turn: " + player.getSymbol());
        System.out.println(turnCounter);
        System.out.println();
    }
}
