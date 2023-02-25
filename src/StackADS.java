import java.util.LinkedList;

public class StackADS<T> implements StackADTADS<T>
{
    private final LinkedList<T> stack = new LinkedList<>();

    @Override
    public boolean isEmpty()
    {
        return stack.size() == 0;
    }

    @Override
    public T pop()
    {
        return stack.removeLast();
    }

    @Override
    public void push(T element)
    {
        stack.add(element);
    }
}
