import java.util.Scanner;


public class CodeChecker {

	private MyStack stack;
	private FileSelector fileS;
	private Scanner scanner;
	private char[] chars;
	
	public CodeChecker()
	{
		fileS = new FileSelector();
		init();
		
	}
	
	public void init()
	{
		fileS = new FileSelector();
		scanner = fileS.init();
		int i = 0;
		while (scanner.hasNext())
		{
			i++;
		}
		chars = new char[i];
		int k = 0;
		while (scanner.hasNext())
		{
			String input = scanner.next();
			for (int j = 0; j < input.length(); j++)
			{
				chars[(k*j)+j] = input.charAt(j);
			}
			k++;
		}
		MyStack stack = new MyStack(chars.length);
		
	}
	
	public void checkInput()
	{
		String stackObject = null;
		boolean space = false;
		boolean tagStart = false;
		boolean endTag = false;
		boolean error = false;
		boolean comment = false;
		for (char character : chars)
		{
			if (!error)
			{
				if (comment)
				{
					if (Character.toString(character).equals(">"))
					{
						comment = false;
					}
				}
				else if (space)
				{
					if (Character.toString(character).equals(">"))
					{
						stackObject = stackObject + "" + character;
						space = false;
					}
				}
				else if (tagStart == false)
				{
					if (Character.toString(character).equals("<"))
					{
						stackObject = stackObject + "" + character;
						tagStart = true;
					}
				}
				else if (endTag == true)
				{
					if (Character.toString(character).equals(">"))
					{
						stackObject = stackObject + "" + character;
						tagStart = false;
						endTag = false;
						error = stackChecker(stackObject);
						if (!error)
						{
							stackObject = null;
						}
					}
					else
					{
						stackObject = stackObject + "" + character;
					}
				}
				else
				{
					if (Character.toString(character).equals(">"))
					{
						stackObject = stackObject + "" + character;
						tagStart = false;
						stack.push(stackObject);
						stackObject = null;
					}
					else if (Character.toString(character).equals("/"))
					{
						endTag = true;
					}
					else if (Character.toString(character).equals("!"))
					{
						comment = true;
						stackObject = null;
					}
					else if (Character.toString(character).equals(" "))
					{
						space = true;
					}
					else
					{
						stackObject = stackObject + "" + character;
					}
				}
			}
		}
		if (stackObject != null)
		{
			if (Character.toString(stackObject.charAt(stackObject.length() -1)).equals(">"))
			{
				System.out.println("Error: end bracket " + stackObject + " did not match " + stack.peek());
			}
			else if (!stack.isEmpty())
			{
				System.out.println("Error: Error -- End of file reached before stack cleared");
			}
		}
		else
		{
			if (stack.isEmpty())
			{
				System.out.println("Tags Matched Correctly, file well formed");
			}
		}
	}
	
	public Boolean stackChecker(String stackObject)
	{
		if (stack.peek().equals(stackObject))
		{
			stack.pop();
			return false;
		}
		else
		{
			return true;
		}
	}
}
