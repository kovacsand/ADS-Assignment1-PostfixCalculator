public class Operand implements Token
{
    private final double operand;

    public Operand(double operand)
    {
        this.operand = operand;
    }

    public double getOperand()
    {
        return operand;
    }
}
