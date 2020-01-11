import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Combine extends JPanel {

	private JPanel buttonPanel = new JPanel();
	private JPanel p=new JPanel(new FlowLayout(FlowLayout.LEFT));
	private StandardButton addPurchaseButton = new StandardButton("Add New PurchaseProduct");
	private Purchase pur = new Purchase();
	private JLabel sear = new JLabel("Enter Date");
	private JSearchTextField jsear = new JSearchTextField("search","YYYY-MM-DD","Enter Procuct name or ID".length()-8);

	public Combine() throws Exception {

		addPurchaseButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.print("add purchase work");
				try {
					new AddNewPurchase(pur.getCustomerClass());

				}
				catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		jsear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					new viewDate(jsear.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		setLayout(new BorderLayout());
		buttonPanel.add(addPurchaseButton);
		p.add(sear);
		p.add(jsear);

		add(p,BorderLayout.NORTH);
		add(pur, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	public static void main(String[] args) throws Exception {

		JPanel panel= new Combine();
		JFrame frame=new JFrame();
		frame.add(panel);

		frame.setTitle("Purchase");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}

