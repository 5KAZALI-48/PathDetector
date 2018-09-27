import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;


public class UserInterface extends JFrame 
{	
	Object[] row = new Object[3];
	DefaultTableModel model = new DefaultTableModel();
	protected JPanel contentPane;
	protected JTable table;
	protected JTextField txtActivityName;
	protected JTextField txtPredecessor;
	private JTextField txtDuration;
	protected JLabel lblActivityName;
	protected JScrollPane scrollPane;
	protected JLabel lblPredecessor;
	protected JLabel lblDuration;
	protected JButton btnAdd;
	protected JButton btnDelete;
	protected JButton btnSave;
	protected JButton btnDisplay;
	protected JMenuItem mntmAbout;
	protected JMenuItem mntmHelp;
	protected String activity_name;
	protected String predecessor;
	protected String duration;
	/********************************************************
	* 		Main:Lounch the application			           *
    ********************************************************/
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try 
				{
					UserInterface frame = new UserInterface();
					frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
			}
			
		});
	}
	

   /*******************************************************
	* 					Constructor						  *
	*******************************************************/
	public UserInterface()
	{
		initComponents();
		addButton();
		deleteButton();
		firstActivity();
		menuItemAbout();
		menuItemHelp();
		inputControl();
	}
	
	private void addButton()
	{
		btnAdd.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(txtActivityName.getText().isEmpty() || 
				   txtPredecessor.getText().isEmpty()  ||
				   txtDuration.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "All Fields must be filled");
				}
				else
				{
					row[0] = txtActivityName.getText();
					row[1] = txtPredecessor.getText();
					row[2] = txtDuration.getText();
					model.addRow(row);
				}
				
				
			}
		});
	}
	
	private void deleteButton()
	{
		btnDelete.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(table.getRowCount() > 0)
				{
					for(int i = table.getRowCount()-1; i >- 1; i--)
					{
						table.remove(i);
					}
				}
				
				
			}
		});
	}
	
	private void firstActivity()
	{
		txtPredecessor.addKeyListener(new KeyAdapter() 
		{
			String no_dependency = "NULL";
			@Override
			public void keyTyped(KeyEvent e)
			{
				if (!txtActivityName.getText().isEmpty() && model.getRowCount() <= 0) 
				{
					e.consume();
					txtPredecessor.setText(no_dependency);
						JOptionPane.showMessageDialog(null,
								"First Activity can not have dependency. This Field will be Null for this time!");

				}
			}
		});
	 }
	
	private void menuItemAbout() 
	{
		mntmAbout.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
					String msg_about = "<html><body><div width='600px' align='center'>Program Name: Path Detector <br/> Description:The Path Detector is a software program to analyze a network diagram and determine all paths in the network <br/><br/> ===Contributers=== <br/>Faruk Karagoz<br/> Alison Mayer<br/> Divesh Dinoli <br/> Yash <br/><br/> ARIZONA STATE UNIVERSITY <br/> Computer Science</body></html>";
					JOptionPane.showMessageDialog(null, msg_about);
			}
		});
	}
	
	
	private void menuItemHelp() 
	{
		mntmHelp.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "
							+ "C:\\Users\\faruk\\Desktop\\CodeWar\\JavaProject\\PathDetector\\src\\pathDetector\\resources\\Help.pdf");
				} 
				catch (Exception evt) 
				{
					JOptionPane.showMessageDialog(null, "Error occured while retrieving the file!");
				}
			}
		});
	}
	
	private void inputControl()
	{
		txtDuration.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyTyped(KeyEvent e) 
			{
				char input = e.getKeyChar();
				if ((input < '0' || input > '9') && input != '\b') 
				{
					e.consume();
					JOptionPane.showMessageDialog(null, "An Integer Value is required!");
				}
			}
		});
	}

	/*********************************************************
	 * Initializing the Components						     *
	 *********************************************************/
	private void initComponents() 
	{
		/*********************************************************
		 * 					ContentPane						     *
	     *********************************************************/
		setTitle("Path Detector");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(UserInterface.class.getResource("/pathdetector/resources/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
	   /*********************************************************
		* 					Menu&MenuItems						*
		*********************************************************/
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuMenu = new JMenu("Menu");
		menuBar.add(menuMenu);
		
		mntmAbout = new JMenuItem("About");
		menuMenu.add(mntmAbout);
		
		mntmHelp = new JMenuItem("Help");
		menuMenu.add(mntmHelp);
		
	   /*********************************************************
	  	* 					JButtons    					    *
 		*********************************************************/
		btnAdd = new JButton("Add");
		btnDelete = new JButton("Delete");
		btnDisplay = new JButton("Display");
		btnSave = new JButton("Save");

		/*********************************************************
	  	* 					JLabels    					         *
 		**********************************************************/
		JLabel lblInstruction = new JLabel("Please Enter Following Fields");
		lblInstruction.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel lblNewLabel = new JLabel("Activity Name");
		JLabel lblPredecessor = new JLabel("Predecessor");
		JLabel lblDuration = new JLabel("Duration");
		
	   /*********************************************************
	  	* 					JTextFields    					     *
 		**********************************************************/
		txtActivityName = new JTextField();
		txtActivityName.setColumns(10);

		txtPredecessor = new JTextField();
		txtPredecessor.setColumns(10);

		txtDuration = new JTextField();
		txtDuration.setColumns(10);
		
	   /***********************************************************
	  	* 					JTScrollPane    					  *
 		***********************************************************/
		scrollPane = new JScrollPane();
		
	   /***********************************************************
		* 		GroupLayout:HorizontalLayoutGroup   			  *
	    ***********************************************************/
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblInstruction, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblPredecessor)
								.addComponent(lblDuration))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtDuration, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
								.addComponent(txtPredecessor, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
								.addComponent(txtActivityName, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDisplay, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
								.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		/***********************************************************
		* 		GroupLayout:VerticalLayoutGroup   	  		       *
	    ************************************************************/
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblInstruction)
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblNewLabel)
									.addComponent(txtActivityName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblPredecessor)
									.addComponent(txtPredecessor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblDuration)
									.addComponent(txtDuration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnDelete))
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnDisplay)
									.addComponent(btnSave))))
						.addContainerGap())
			);
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		Object[] columns = { "Activity Name", "Predecessor", "Duration" };
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		Font font = new Font("", 1, 22);
		table.setFont(font);
		table.setRowHeight(30);

	}


}
