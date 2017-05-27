import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

public class Login extends JFrame implements ActionListener
{
	JLabel L1,L2;
	JTextField T1;
	JPasswordField T2;
	JButton B,B1;
	
	
	Login()
	{
			setLayout(null);
			setTitle("Enter your details");
			T1=new JTextField();
			add(T1);
			T1.addActionListener(this);
			T1.setBounds(290,250,100,30);
			T2=new JPasswordField();
			add(T2);
			T2.addActionListener(this);
			T2.setBounds(290,300,100,30);
			L1=new JLabel("UserId");
			add(L1);
			
			L1.setBounds(210,250,100,30);
			L2=new JLabel("Password");
			
			add(L2);
			L2.setBounds(210,300,100,30);
			B=new JButton("Log In");
			add(B);
			B.setBounds(200,350,100,30);
			B.addActionListener(this);
			B1=new JButton("Reset");
			add(B1);
			B1.setBounds(320,350,100,30);
			B1.addActionListener(this);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
public	void actionPerformed(ActionEvent ae)
	{
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
    
		
        Scanner sc=new Scanner(System.in);
			try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver connected");
				}
			catch(ClassNotFoundException e)
			{
			System.out.println(e);
			}
	try{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			System.out.println("db connected");
			if(ae.getSource()==B)
		{
			char ch[];
			ch=T2.getPassword();
			String S= new String(ch);
			String K=T1.getText();
			String sql="select * from logindb";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			boolean flag=false;
			while(rs.next())
			{
			if(K.equals(rs.getString(1)) && S.equals(rs.getString(2)))
				{
				flag=true;
				break;
				}
			}
			if(flag)
			{
				Test F=new Test("Test of Java");
				F.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Invalid UserId or Password","Incorrect Details",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else if (ae.getSource()==B1)
		{
			T1.setText(null);
			T2.setText(null);
		}

    }
    catch(SQLException e)
    {
    System.out.println(e);
    }
		
		
			
	}
	
public static void main (String []args)
{
MyFrame F=new MyFrame();
F.setSize(650,650);
F.setVisible(true);
}
}