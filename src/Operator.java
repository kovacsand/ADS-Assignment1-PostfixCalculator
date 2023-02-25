public class Operator implements Token
{
    private final char operator;

    public Operator(char operator)
    {
        this.operator = operator;
    }

    public char getOperator()
    {
        return operator;
    }
}
