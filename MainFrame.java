import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class MainFrame extends JFrame
{
Container c;
JButton btnAdd,btnView;

MainFrame()
{
c=getContentPane();
c.setLayout(new FlowLayout());
btnAdd = new JButton("Add");
btnView = new JButton("View");
c.add(btnAdd);
c.add(btnView);

ActionListener a1 = (ae) -> {
AddFrame a = new AddFrame();
dispose();
};
btnAdd.addActionListener(a1);

ActionListener a2 =(ae) -> {
ViewFrame a = new ViewFrame();
dispose();
};
btnView.addActionListener(a2);

setTitle("E. M. S.");
setSize(400,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

public static void main(String args[])
{
MainFrame m = new MainFrame();
}
}


class DbHandler
{
public void addEmployee(Emp e)
{
Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory factory = cfg.buildSessionFactory();

Session session = null;
Transaction t = null;
try{
session = factory.openSession();
System.out.println("Session opened");
t = session.beginTransaction();
session.save(e);
t.commit();
System.out.println("Record inserted");
JOptionPane.showMessageDialog(new JDialog(),"Record inserted");
}


catch(Exception ex){
System.out.println("Issue : " + ex);
t.rollback();
JOptionPane.showMessageDialog(new JDialog(),"Issue : " + e);
}
finally{
session.close();
System.out.println("Session close");
}
}

public String viewEmployee()
{
StringBuffer sb = new StringBuffer();
Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory factory = cfg.buildSessionFactory();

Session session = null;
try{
session = factory.openSession();
System.out.println("Session open");
java.util.List<Emp> e = new java.util.ArrayList<>();
e = session.createQuery("from Emp").list();
for(Emp m : e){
System.out.println(m.getEid() + " " + m.getEname() + "\n");
sb.append(m.getEid() + " " + m.getEname() + "\n");
}
}
catch(Exception e){
System.out.println("issue : " + e);
}
finally{
session.close();
System.out.println("Session closed");
} 
return sb.toString();
}
}