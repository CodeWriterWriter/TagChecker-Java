import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class FileSelector {
	
	final JFileChooser fs = new JFileChooser();
	
	
	public FileSelector ()
	{
	}
	
	public Scanner init()
	{
		while (true)
		{
			FileNameExtensionFilter filter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
			fs.setFileFilter(filter);
			int returnVal = fs.showOpenDialog(null);
			try
			{
				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
					return new Scanner(fs.getSelectedFile());
				}
				else
				{
					return null;
				}
			}
			catch (FileNotFoundException e)
			{
				JOptionPane.showMessageDialog(null, "Invalid File", "error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}

}
