
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.*;

public class ConnectDB2Table extends JPanel {
private ArrayList<String> cols=new ArrayList<>();
	private ArrayList<ArrayList<String>> data=new ArrayList<>();
	 private JButton btFilter = new JButton(" Search for... ");
	 private JTextField jtfFilter = new JTextField(30);
	private JTable saleTable,purchaseTable;
	private TableRowSorter<TableModel> sorter=new TableRowSorter<TableModel>();
	private JLabel editLabel;
	private JLabel saveLabel;
	private JPopupMenu editMenu;private int x,y;JPanel TablePane,btmPane;
	private JTable myTable;


	public void connectDB(String url) throws ClassNotFoundException, SQLException  {

			Class.forName("com.mysql.jdbc.Driver");

		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/student","root","root");
		Statement s=connection.createStatement();



		ResultSet rs=s.executeQuery(url);

		ResultSetMetaData rsMeta=rs.getMetaData();
		for (int i = 1; i <= rsMeta.getColumnCount(); i++)
			cols.add(rs.getMetaData().getColumnName(i));


			while(rs.next()){

				ArrayList<String> row=new ArrayList<String>();
			for(int i=1;i <= rsMeta.getColumnCount(); i++)
			row.add(rs.getString(i));
		data.add(row);


			 }

			Vector ColVec=new Vector();
			Vector dataVec=new Vector();

		for(int i=0;i<data.size();i++){
				ArrayList subArray=(ArrayList)data.get(i);
				Vector subVector=new Vector();
				for(int j=0;j<subArray.size();j++)
				subVector.add(subArray.get(j));
				dataVec.add(subVector);
			}

			for(int i=0;i<cols.size();i++) ColVec.add(cols.get(i));



			//Creating myTable
			 //DefaultTableModel table=new DefaultTableModel();
			   createTable(dataVec,ColVec);
			 design();

			connection.close();

}

	///////////
public void createTable(Vector data,Vector col){
	JTable myTable=new JTable(data,col);
sorter.setModel(myTable.getModel());

			  myTable.setRowSorter(sorter);
			  myTable.setFont(new Font("Times New Roman",Font.PLAIN,18));
			  myTable.setForeground( Color.blue);
			  myTable.setRowMargin(0);
			  myTable.setRowHeight(22);
			//  myTable.setShowGrid(false);
			  myTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);


			  //Aligning Center
			  DefaultTableCellRenderer centerAlign=new DefaultTableCellRenderer();
			  centerAlign.setHorizontalAlignment(JLabel.CENTER);
			for(int x=0;x<myTable.getColumnCount();x++)
				 myTable.getColumnModel().getColumn(x).setCellRenderer(centerAlign);
			myTable.setEnabled(false);



}

		public void design(){

                                                           editLabel=new JLabel("  Edit  ");
			  editLabel.setFont(new Font("Times New Roman",Font.BOLD,18));
			  editLabel.setForeground(Color.BLUE);
			  editLabel.addMouseListener(new MouseAdapter(){
				  public void mouseClicked(MouseEvent e){ myTable.setEnabled(true);}
			  });
			  saveLabel=new JLabel("  Save Changes  ");
			  saveLabel.setFont(new Font("Times New Roman",Font.BOLD,18));
			  saveLabel.setForeground(Color.BLUE);
			  saveLabel.addMouseListener(new MouseAdapter(){
				  public void mouseClicked(MouseEvent e){myTable.setEnabled(false);   jtfFilter.setText("Saving");}
			  });
			  //////////// JPopupMenu
			  editMenu=new JPopupMenu();
			  editMenu.add(new JMenuItem("Add row"));
			  editMenu.add(new JMenuItem("Edit row"));
			  editMenu.add(new JMenuItem("Delete row"));
			  editMenu.addSeparator();
			  editMenu.add(new JMenuItem("Edit cell"));
			  editMenu.add(new JMenuItem("Delete cell"));

			  btFilter.addActionListener(new java.awt.event.ActionListener() {
				  public void actionPerformed(java.awt.event.ActionEvent e) {
				  String text = jtfFilter.getText();
				  if (text.trim().length() == 0)
				  sorter.setRowFilter(null);
				  else
				 	 sorter.setRowFilter(RowFilter.regexFilter(text));
				  }
				  });

			editLabel.addMouseListener(new MouseAdapter(){
				public void mouseReleased(MouseEvent e){
					if(e.isPopupTrigger()) editMenu.show(e.getComponent(),getX(),getY());
				}
			});

}

	public ConnectDB2Table() throws ClassNotFoundException, SQLException  {
		TablePane=new JPanel(new BorderLayout());
		 btmPane=new JPanel();


		// pane=new JTabbedPane();
		// connectDB("select * from student");


			 btmPane.add(editLabel);
			 btmPane.add(saveLabel);
			 btmPane.add(jtfFilter);
			  btmPane.add(btFilter);

			  //pane.addTab("Student",new JScrollPane(),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));

			//TablePane.add(pane,BorderLayout.CENTER);
			//add(TablePane,BorderLayout.CENTER);
	//	add(btmPane,BorderLayout.SOUTH);

	}

}