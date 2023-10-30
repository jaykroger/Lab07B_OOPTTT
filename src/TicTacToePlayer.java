public class TicTacToePlayer
{
    private String symbol;

    public TicTacToePlayer(String symbol)
    {
        this.symbol = symbol;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public void changeTurn()
    {
        if (this.symbol.equalsIgnoreCase("X"))
        {
            this.setSymbol("O");
        }

        else if (this.symbol.equalsIgnoreCase("O"))
        {
            this.setSymbol("X");
        }
    }
}
