package customerInformationGui;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class GUIWindow 
{
	public static void main(String[] args)
	{
        try {
        	ArrayList<String> custId, fname,lname,strtaddress,city,state,zip,phnumb, CrCN, balance;
        	custId = new ArrayList<String>();
        	fname = new ArrayList<String>();
        	lname = new ArrayList<String>();
        	strtaddress = new ArrayList<String>();
        	city = new ArrayList<String>();
        	state = new ArrayList<String>();
        	zip = new ArrayList<String>();
        	phnumb = new ArrayList<String>();
        	CrCN = new ArrayList<String>();
        	balance = new ArrayList<String>();
        	
        	String client_address;
    	    int ccid = 0;
    		dBConnection(custId, fname, lname, strtaddress, city, state, zip, phnumb, CrCN, balance);
    		
		//create GUI window, 
		JFrame customerGUI = new JFrame();
		customerGUI.setSize(1000,1000);
		customerGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//main parent jpanel box which will contain 2 side-by-side child jpanels
		JPanel parent = new JPanel();
		BoxLayout twoHorizBox = new BoxLayout(parent,BoxLayout.X_AXIS);
		parent.setLayout(twoHorizBox);
		
		
		//main window(not used for this sprint)
		JPanel mainWindowPanel = new JPanel();
		mainWindowPanel.setMaximumSize(new Dimension(750,1000));
		mainWindowPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel mainWindowText = new JLabel("Main Working Window");

		mainWindowPanel.add(mainWindowText);
		
		//customer info child window. Set up with 3 top to bottom boxes
		JPanel customerInfoPanel = new JPanel();
		customerInfoPanel.setMaximumSize(new Dimension(250,1000));
		BoxLayout threeVertBox = new BoxLayout(customerInfoPanel, BoxLayout.Y_AXIS);
		customerInfoPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		
		//1st box of customer info child window. contains customer info
		JPanel customerInfo = new JPanel();
		customerInfo.setMaximumSize(new Dimension(225,333)); //FIX expand box to to fill top third of right column
		BoxLayout sixCustomerVertBox = new BoxLayout(customerInfo, BoxLayout.Y_AXIS);
		customerInfo.setLayout(sixCustomerVertBox);
		customerInfo.setBorder(BorderFactory.createLineBorder(Color.black));
		
			//customer ID child of customer info window
			JPanel ID = new JPanel();
			JLabel customerID = new JLabel("ID: ");
			ID.add(customerID);
			//custId = clientInfo.getString("ccid");	
			JTextField DBCustomerID = new JTextField(custId.get(ccid)); //TO-DO add DB call here inside JTextField
			DBCustomerID.setColumns(12);;
			ID.add(DBCustomerID);
			customerInfo.add(ID);
			
			//First Name child of customer info window
			JPanel firstName = new JPanel();
			JLabel firstNameLabel = new JLabel("First Name: ");
			firstName.add(firstNameLabel);
			//fname = clientInfo.getString("first_name");
			JTextField firstNameText = new JTextField(fname.get(ccid)); //TO-DO add DB call here
			firstNameText.setColumns(12);;
			firstName.add(firstNameText);
			customerInfo.add(firstName);
			
			//Last Name child of customer info window
			JPanel lastName = new JPanel();
			JLabel lastNameLabel = new JLabel("Last Name: ");
			lastName.add(lastNameLabel);
			//lname = clientInfo.getString("last_name");
			JTextField lastNameText = new JTextField(lname.get(ccid)); //TO-DO add DB call here
			lastNameText.setColumns(12);;
			lastName.add(lastNameText);
			customerInfo.add(lastName);
			
			//Address child of customer info window
			JPanel address = new JPanel();
			JLabel addressLabel = new JLabel("Address: ");
			address.add(addressLabel);
			client_address = getAddress(strtaddress, city, state, zip, ccid);
			JTextField addressText = new JTextField(client_address); //TO-DO add DB call here
			addressText.setColumns(12);;
			address.add(addressText);
			customerInfo.add(address);
			
			//Phone Number child of customer info window
			JPanel phoneNum = new JPanel();
			JLabel phoneNumLabel = new JLabel("Phone #: ");
			phoneNum.add(phoneNumLabel);
			//phnumb = clientInfo.getString("primary_phone");
			JTextField phoneNumText = new JTextField(phnumb.get(ccid)); //TO-DO add DB call here
			phoneNumText.setColumns(12);;
			phoneNum.add(phoneNumText);
			customerInfo.add(phoneNum);
			//Credit Card Number child of customer info window
			JPanel creditNum = new JPanel();
			JLabel creditNumLabel = new JLabel("Credit Card #: ");
			creditNum.add(creditNumLabel);
			//CrCN = clientInfo.getString("chase_account_no");
			JTextField creditNumText = new JTextField(CrCN.get(ccid)); //TO-DO add DB call here
			creditNumText.setColumns(12);;
			creditNum.add(creditNumText);
			customerInfo.add(creditNum);

			//Current Credit Card Balance child of customer info window
			JPanel creditBal = new JPanel();
			JLabel creditBalLabel = new JLabel("Credit Balance: ");
			creditBal.add(creditBalLabel);
			//balance =clientInfo.getString("agg_cc_balance");
			JTextField creditBalText = new JTextField(balance.get(ccid)); //TO-DOadd DB call here
			creditBalText.setColumns(12);;
			creditBal.add(creditBalText);
			customerInfo.add(creditBal);
		
				
		//2st box of customer info child window. contains call history
		JPanel callHistory = new JPanel();
		callHistory.setMaximumSize(new Dimension(225,333)); //FIX expand box to to fill middle third of right column
		callHistory.setLayout(new BorderLayout());
		JLabel callHistoryText = new JLabel("Call History Window");
		callHistory.setBorder(BorderFactory.createLineBorder(Color.black));
		callHistory.add(callHistoryText);
		
		//3rd box of customer info child window. contains payment history
		JPanel payHistory = new JPanel();
		payHistory.setMaximumSize(new Dimension(225,333)); //FIX expand box to to fill bottom third of right column
		payHistory.setLayout(new BorderLayout());
		JLabel payHistoryText = new JLabel("Payment History Window");
		payHistory.setBorder(BorderFactory.createLineBorder(Color.black));
		payHistory.add(payHistoryText);
		
		customerInfoPanel.add(customerInfo);
		customerInfoPanel.add(callHistory);
		customerInfoPanel.add(payHistory);
		
		parent.add(mainWindowPanel);
		parent.add(customerInfoPanel);
		
		customerGUI.setVisible(true);
		customerGUI.getContentPane().add(parent);
		customerGUI.setVisible(true);
		DBCustomerID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ccid = Integer.parseInt(DBCustomerID.getText());
				ccid -= 1;
				if(ccid >= custId.size() || ccid <= -1) 
				{
					firstNameText.setText("");
					lastNameText.setText("");
					addressText.setText("");
					phoneNumText.setText("");
					creditNumText.setText("");
					creditBalText.setText("");
				}
				else 
				{
					firstNameText.setText(fname.get(ccid));
					lastNameText.setText(lname.get(ccid));
					String client_address = getAddress(strtaddress, city, state, zip, ccid);
					addressText.setText(client_address);
					phoneNumText.setText(phnumb.get(ccid));
					creditNumText.setText(CrCN.get(ccid));
					creditBalText.setText(balance.get(ccid));
				}
			}
		});		
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
	}

	private static String getAddress(ArrayList<String> strtaddress, ArrayList<String> city, ArrayList<String> state,
			ArrayList<String> zip, int ccid) {
		String client_address;
		String strtaddressstr = strtaddress.get(ccid);
		String citystr = city.get(ccid);
		String statestr = state.get(ccid);
		String zipstr = zip.get(ccid);
		client_address = strtaddressstr + " " + citystr + " " + statestr + " " + zipstr;
		return client_address;
	}

	private static void dBConnection(ArrayList<String> custId, ArrayList<String> fname, ArrayList<String> lname,
			ArrayList<String> strtaddress, ArrayList<String> city, ArrayList<String> state, ArrayList<String> zip,
			ArrayList<String> phnumb, ArrayList<String> CrCN, ArrayList<String> balance) throws SQLException 
	{
		String myUrl = "jdbc:mysql://localhost:3306/callcenter";
		Connection conn = DriverManager.getConnection(myUrl, "root", "Password");
		Statement stmt = conn.createStatement();
		ResultSet clientInfo = stmt.executeQuery("SELECT * FROM customers");
		while (clientInfo.next())
		{
			addName(fname, lname, clientInfo);
			phnumb.add(clientInfo.getString("primary_phone"));
			addAddress(custId, strtaddress, city, state, zip, clientInfo);
			addCreditinfo(CrCN, balance, clientInfo);
		}
	}

	private static void addCreditinfo(ArrayList<String> CrCN, ArrayList<String> balance, ResultSet clientInfo)
			throws SQLException 
	{
		CrCN.add(clientInfo.getString("chase_account_no"));
		balance.add(clientInfo.getString("agg_cc_balance"));
	}

	private static void addName(ArrayList<String> fname, ArrayList<String> lname, ResultSet clientInfo)
			throws SQLException 
	{
		fname.add(clientInfo.getString("first_name"));
		lname.add(clientInfo.getString("last_name"));
	}

	private static void addAddress(ArrayList<String> custId, ArrayList<String> strtaddress, ArrayList<String> city,
			ArrayList<String> state, ArrayList<String> zip, ResultSet clientInfo) throws SQLException 
	{
		custId.add(clientInfo.getString("ccid"));
		strtaddress.add(clientInfo.getString("billing_address_1"));
		city.add(clientInfo.getString("billing_city"));
		state.add(clientInfo.getString("billing_state"));
		zip.add(clientInfo.getString("billing_zip"));
	}
}
