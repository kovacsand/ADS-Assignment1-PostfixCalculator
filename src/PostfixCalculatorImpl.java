import java.util.ArrayList;

public class PostfixCalculatorImpl implements Calculator
{
    private StackADTADS<Operand> stack;

    //Only the following operators are handled based on the book's requirements: +, -, *, /
    @Override
    public int evaluateExpression(ArrayList<Token> tokenList) throws Exception
    {
        stack = new StackADS<>();

        if (tokenList == null || tokenList.isEmpty())
            throw new Exception("Invalid argument, expression doesn't contain tokens");

        //6523 + 8 ∗ + 3 + ∗
        for (Token token : tokenList)
        {
            if (token instanceof Operand)
                stack.push((Operand) token);
            else if (token instanceof Operator)
            {
                if (stack.isEmpty())
                    throw new Exception("Operator before having two operands");
                double b = stack.pop().getOperand();
                if (stack.isEmpty())
                    throw new Exception("Operator before having two operands");
                double a = stack.pop().getOperand();


                switch (((Operator) token).getOperator())
                {
                    case '+' -> stack.push(new Operand(a + b));
                    case '-' -> stack.push(new Operand(a - b));
                    case '*' -> stack.push(new Operand(a * b));
                    case '/' ->
                    {
                        if (b == 0)
                            throw new Exception("Math error, cannot divide by 0");
                        stack.push(new Operand(a / b));
                    }
                }
            }
        }

        Operand potentialResult = stack.pop();
        if (!stack.isEmpty())
            throw new Exception("For n operators more than n+1 operands were provided");


        stack.push(potentialResult);
        return (int) Math.round(getResult());
    }

    private double getResult()
    {
        return stack.pop().getOperand();
    }
}
