
public class MyStack {
	
	private String[] stack;
	private int N;
	public MyStack(int max)
	{
		stack = new String[max];
	}
	
	public void push(String item)
	{
		stack[N++] = item;
	}
	
	public boolean isEmpty()
	{
		return (N == 0);
	}
	
	public String pop()
	{
		if (null != stack && 0 < stack.length)
			return stack[--N];
		else
			return null;
	}
	
	public String peek()
	{
		if (null != stack && 0 < stack.length)
    		return stack[stack.length - 1];
    	else
    		return null;
	}
	

}
