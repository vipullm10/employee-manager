import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AddFrame extends JFrame
{
Container c;
JLabel lblEid,lblEname;
JTextField txtEid,txtEname;
JButton btnSave,btnBack;

AddFrame()
{
c = getContentPane();
c.setLayout(new FlowLayout());
lblEid = new JLabel("enter eid");
lblEname = new JLabel("enter ename");
txtEid = new JTextField(10);
txtEname = new JTextField(10);
btnSave = new JButton("Save");
btnBack = new JButton("Back");
c.add(lblEid);
c.add(txtEid);
c.add(lblEname);
c.add(txtEname);
c.add(btnSave);
c.add(btnBack);

ActionListener a1 = (ae) -> {
MainFrame mf = new MainFrame();
dispose();
};
btnBack.addActionListener(a1);

ActionListener a2 = (ae) -> {
try{
int eid = Integer.parseInt(txtEid.getText());
String ename = txtEname.getText();
Emp e = new Emp();
e.setEid(eid);
e.setEname(ename);
DbHandler db = new DbHandler();
db.addEmployee(e);
}
catch(NumberFormatException ne){
System.out.println("Only integers");
JOptionPane.showMessageDialog(new JDialog(),"Only integers");
}
};
btnSave.addActionListener(a2);

setTitle("Add S.");
setSize(400,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
} 
}
