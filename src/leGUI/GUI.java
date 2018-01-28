package leGUI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GUI {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Button btnLaadData;
	private Button btnLaadData_1;
	private Button btnLaadData_2;
	private Text text_3;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Voor set vergelijking type: \"Ik ga akkoord met alles dat heer en meester Sebastiaan mij verteld.\".\nAnders type iets en dan mag je spelen met aminozuren. Als je er zo eentje bent.");
		if(s.nextLine().equals("Ik ga akkoord met alles dat heer en meester Sebastiaan mij verteld.")){
			try {
				GUI window = new GUI();
				window.open();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			
		}
		return;
	}
	
	
	

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(616, 592);
		shell.setText("SWT Application");
		
		text = new Text(shell, SWT.BORDER | SWT.V_SCROLL);
		text.setBounds(10, 10, 122, 279);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.V_SCROLL);
		text_1.setBounds(167, 10, 122, 279);
		
		text_2 = new Text(shell, SWT.BORDER | SWT.V_SCROLL);
		text_2.setBounds(328, 10, 122, 279);
		
		final Combo combo = new Combo(shell, SWT.NONE);
		combo.setItems(new String[] {"Alles", "1&2", "1&3", "2&3"});
		combo.setBounds(456, 60, 91, 29);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_3.setText(venningTFOutOfIt(combo.getSelectionIndex(), new String[]{text.getText(), text_1.getText(),text_2.getText()}));
			}
		});
		btnNewButton.setBounds(456, 25, 91, 29);
		btnNewButton.setText("Venn it");
		
		btnLaadData = new Button(shell, SWT.NONE);
		btnLaadData.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.setText(openFile(shell));
			}
		});
		btnLaadData.setText("laad data 1");
		btnLaadData.setBounds(10, 300, 91, 29);
		
		btnLaadData_1 = new Button(shell, SWT.NONE);
		btnLaadData_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_1.setText(openFile(shell));
			}
		});
		btnLaadData_1.setText("laad data 2");
		btnLaadData_1.setBounds(167, 295, 91, 29);
		
		btnLaadData_2 = new Button(shell, SWT.NONE);
		btnLaadData_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_2.setText(openFile(shell));
			}
		});
		btnLaadData_2.setText("laad data 3");
		btnLaadData_2.setBounds(328, 295, 91, 29);
		
		text_3 = new Text(shell, SWT.BORDER | SWT.V_SCROLL);
		text_3.setBounds(10, 335, 440, 129);

	}
	
	private static String openFile(Shell shell){
		String thaThing = "";
		final FileDialog fd = new FileDialog(shell,SWT.OPEN);
		fd.setText("Open");
		String fileName = fd.open();
		
		if(fileName == null ){
			System.out.println("Be more decisive.");
		}
		else{
			try {
	            FileReader fileReader = new FileReader(fileName);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            String line;
	            while((line = bufferedReader.readLine()) != null) {
	                thaThing += line;
	            }   
	            bufferedReader.close();         
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println("Unable to open file '" + fileName + "'");                
	        }
	        catch(IOException ex) {
	            System.out.println("Error reading file '" + fileName + "'");                   
	            ex.printStackTrace();
	        }
		}
		return thaThing;
	} 
	
	private String venningTFOutOfIt(int index, String[] texts){
		ArrayList<String> list = new ArrayList<String>();
		int skip = 3;
		if (index == -1){
			System.out.println("No set selection selected. So.. yeah...");
			return "No set selection selected. So.. yeah...";
		}
		else {
			for(int i = 0 ; i < texts.length; i++){
				if(i != skip - index){
					if(list.size() > 0){
						list.retainAll(Arrays.asList(texts[i].split("\\n")));
					}
					else{
						list.addAll(Arrays.asList(texts[i].split("\\n")));
					}
				}
			}
		}
		String text = "";
		for(String o : list){
			text = text + o + "\n";
		}
		return text;
	}
	
}
