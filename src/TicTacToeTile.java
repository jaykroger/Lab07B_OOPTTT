import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToeTile extends JButton {
    private final int row;
    private final int col;
    static TicTacToePlayer player;
    static TicTacToeBoard board;

    public TicTacToeTile(int row, int col, TicTacToePlayer player, TicTacToeBoard board)
    {
        super();
        this.row = row;
        this.col = col;
        TicTacToeTile.player = player;
        TicTacToeTile.board = board;

        addActionListener((ActionEvent ae) -> markSpace(this));

        setFont(new Font("Montserrat", Font.BOLD, 40));
    }

    public static void markSpace(TicTacToeTile tile)
    {
        if (tile.getText().isEmpty())
        {
            tile.setText(player.getSymbol());
            board.increaseTurnCounter();
            board.checkWinOrTie();
            player.changeTurn();
        }

        else
        {
            JOptionPane.showMessageDialog(null, "Illegal Move.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public int getRow()
    {
        return row;
    }
    public int getCol()
    {
        return col;
    }
}
