import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.sql.*;
import java.text.*;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

class App extends JFrame {
    Date date;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    ResultSet rss;
    JDateChooser Bdate = new JDateChooser();
    JDateChooser Jdate = new JDateChooser();
    String[] columnNames = {"Emp_Id","Name","Company","Skill","Age","Date of Birth","Date of Joining","Salary"};
    String[][] data= new String[0][columnNames.length];
    String sname, scompany, sskill, sage, sdob, sdoj, ssalary, sempid;
    Connection conn = null;
    Statement statement = null;
    DefaultTableModel model = new DefaultTableModel(data, columnNames);
    DateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy hh:mm a");
    JTextArea ta;
    JLabel title, name, company, age, skill, dob, doj, salary, ialary, lbackup;
    JTextField ntext, ctext, atext, ttext, stext,itext;
    JButton submit, edit, delect, backup, search, cancel;
    JTable table;
    JPanel nJPanel, cJPanel, aJPanel, sJPanel, bJPanel, jPanel, sPanel, button_bar, iPanel;
    
    App() {

        
        
        

        title = new JLabel("Employee Management System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(0, 10, 670, 50);

        cJPanel = new JPanel();
        cJPanel.setLayout(null);
        cJPanel.setBounds(0, 80, 670, 50);

        company = new JLabel("Name Of Company: ");
        company.setFont(new Font("Arial", Font.PLAIN, 15));
        company.setBounds(10, -5, 200, 40);
        cJPanel.add(company);

        ctext = new JTextField();
        ctext.setFont(new Font("Arial", Font.BOLD, 15));
        ctext.setBounds(360, 0, 300, 30);
        cJPanel.add(ctext);


        nJPanel = new JPanel();
        nJPanel.setLayout(null);
        nJPanel.setBounds(0, 140, 670, 50);

        name = new JLabel("Name Of Employee: ");
        name.setFont(new Font("Arial", Font.PLAIN, 15));
        name.setBounds(10, 0, 200, 40);
        nJPanel.add(name);

        ntext = new JTextField();
        ntext.setFont(new Font("Arial", Font.BOLD, 15));
        ntext.setBounds(360, 0, 300, 30);
        nJPanel.add(ntext);
        ntext.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_SPACE))) {
                    e.consume(); 
                    JOptionPane.showMessageDialog(null, "Enter Alphabet only");
                }
            }
         });

        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBounds(0, 200, 670, 50);

        doj = new JLabel("Date Of Joining: ");
        doj.setFont(new Font("Arial", Font.PLAIN, 15));
        doj.setBounds(10, 0, 200, 40);
        jPanel.add(doj);

       
        Jdate.setBounds(360, 0, 300, 30);
        Jdate.setFont(new Font("Arial", Font.BOLD, 15));
        jPanel.add(Jdate);

        

        aJPanel = new JPanel();
        aJPanel.setLayout(null);
        aJPanel.setBounds(0, 260, 670, 50);

        age = new JLabel("Age: ");
        age.setFont(new Font("Arial", Font.PLAIN, 15));
        age.setBounds(10, 0, 200, 40);
        aJPanel.add(age);

        atext = new JTextField();
        atext.setBounds(360, 0, 300, 30);
        atext.setFont(new Font("Arial", Font.BOLD, 15));
        aJPanel.add(atext);

        atext.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();
                     JOptionPane.showMessageDialog(null, "Enter Numbers only");
                }
            }
         });

        sJPanel = new JPanel();
        sJPanel.setLayout(null);
        sJPanel.setBounds(0, 320, 670, 50);

        skill = new JLabel("Skill: ");
        skill.setFont(new Font("Arial", Font.PLAIN, 15));
        skill.setBounds(10, 0, 200, 40);
        sJPanel.add(skill);

        ttext = new JTextField();
        ttext.setBounds(360, 0, 300, 30);
        ttext.setFont(new Font("Arial", Font.BOLD, 15));
        sJPanel.add(ttext);

        ttext.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_SPACE)) {
                    e.consume(); 
                    JOptionPane.showMessageDialog(null, "Enter Alphabet only");
                }
            }
         });

        sPanel = new JPanel();
        sPanel.setLayout(null);
        sPanel.setBounds(0, 380, 670, 50);

        salary = new JLabel("Salary: ");
        salary.setFont(new Font("Arial", Font.PLAIN, 15));
        salary.setBounds(10, 0, 200, 40);
        sPanel.add(salary);

        stext = new JTextField();
        stext.setBounds(360, 0, 300, 30);
        stext.setFont(new Font("Arial", Font.BOLD, 15));
        sPanel.add(stext);

        stext.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();  
                     JOptionPane.showMessageDialog(null, "Enter Numbers only");
                }
            }
         });

        bJPanel = new JPanel();
        bJPanel.setLayout(null);
        bJPanel.setBounds(0, 440, 670, 50);

        dob = new JLabel("Date Of Birth: ");
        dob.setFont(new Font("Arial", Font.PLAIN, 15));
        dob.setBounds(10, 0, 200, 40);
        bJPanel.add(dob);

        Bdate.setBounds(360, 0, 300, 30);
        Bdate.setFont(new Font("Arial", Font.BOLD, 15));
        bJPanel.add(Bdate);

        iPanel = new JPanel();
        iPanel.setLayout(null);
        iPanel.setBounds(0, 500, 670, 50);

        ialary = new JLabel("Emp_ID: ");
        ialary.setFont(new Font("Arial", Font.PLAIN, 15));
        ialary.setBounds(10, 0, 200, 40);
        iPanel.add(ialary);

        itext = new JTextField();
        itext.setBounds(360, 0, 300, 30);
        itext.setFont(new Font("Arial", Font.BOLD, 15));
        iPanel.add(itext);

        itext.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();  // if it's not a number, ignore the event
                }
            }
         });

        button_bar = new JPanel();
        button_bar.setLayout(null);
        button_bar.setBounds(0, 580, 1200, 50);

        submit = new JButton();
        submit.setText("Submit");
        submit.setBounds(285, 0, 100, 30);
        button_bar.add(submit);

        edit = new JButton();
        edit.setText("Edit");
        edit.setBounds(10, 0, 100, 30);
        button_bar.add(edit);

        delect = new JButton();
        delect.setText("Delect");
        delect.setBounds(565, 0, 100, 30);
        button_bar.add(delect);

        backup = new JButton();
        backup.setText("Backup");
        backup.setBounds(788, 0, 100, 30);
        button_bar.add(backup);

        cancel = new JButton();
        cancel.setText("Cancel");
        cancel.setBounds(912, 0, 100, 30);
        button_bar.add(cancel);

        search = new JButton();
        search.setText("Search");
        search.setBounds(1030, 0, 100, 30);
        button_bar.add(search);

        lbackup = new JLabel("bACKUP");
        lbackup.setFont(new Font("Arial", Font.BOLD, 12));
        lbackup.setBounds(928, 640, 300, 30);
        
        

        table = new JTable(model);
        model.setColumnIdentifiers(columnNames);
        

        JScrollPane js=new JScrollPane(table);
        js.setBounds(680, 80, 560, 250);
        js.setVisible(true);
        add(js,BorderLayout.CENTER);

        ta = new JTextArea();
        ta.setBounds(680, 350, 560,210);
        ta.setFont(new Font("Arial", Font.BOLD, 12));
        ta.setEditable(false);
        ta.setText("\nSearched EMPLOYEE Details:");

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test?" + "user=username&password=password");
            statement = (Statement) conn.createStatement();
            System.out.println("Connected");
            String sql;
            sql = "select *from Test.Employee";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                sname = rs.getString(2);
                scompany= rs.getString(1);
                sskill = rs.getString(3);
                sage = rs.getString(4);
                sdob = rs.getString(5);
                sdoj = rs.getString(6);
                ssalary = rs.getString(7);
                sempid = rs.getString(8);
                String[] row = {sempid,sname,scompany,sskill,sage,sdob,sdoj,ssalary};
                model.addRow(row);
                
            }
            
            

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        try {
            File myfile = new File("emp_backup.txt");
            if (myfile.createNewFile()) {
              System.out.println("File created: " + myfile.getName());
              lbackup.setText("No Backup Done yet.");
            } else {
            long time = myfile.lastModified();
              System.out.println("File already exists.");
              lbackup.setText("Last Backup on "+sdf.format(time));
            }
          } catch (IOException E) {
            System.out.println("An error occurred.");
            E.printStackTrace();
          }


         cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                  itext.setEditable(true);
                  itext.setText("");
                  ntext.setText("");
                  ctext.setText("");
                  ttext.setText("");
                  atext.setText("");
                  Bdate.setCalendar(null);
                  Jdate.setCalendar(null);
                  stext.setText("");
                
            
            }
        });


        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(ntext.getText().isEmpty() || ctext.getText().isEmpty() || ttext.getText().isEmpty() || atext.getText().isEmpty() || simpleDateFormat.format(Bdate.getDate()).isEmpty() || simpleDateFormat.format(Jdate.getDate()).isEmpty() || stext.getText().isEmpty() || itext.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Enter value for all data");
                }
                else{


                    sname = ntext.getText().toString();
                    scompany = ctext.getText().toString();
                    sskill = ttext.getText().toString();
                    sage = atext.getText().toString();
                    sdob = simpleDateFormat.format(Bdate.getDate());
                    sdoj = simpleDateFormat.format(Jdate.getDate());
                    ssalary = stext.getText().toString();
                    sempid = itext.getText().toString();

                    String selectQuery = "select * from Employee where e_id ='"+sempid+"'";
                    System.out.println(selectQuery);
                    
                    try {
                        rss = statement.executeQuery(selectQuery);
                    } catch (SQLException e2) {
    
                        e2.printStackTrace();
                    }
                    

                    try {
                        if(rss.next())
                        {
                            JOptionPane.showMessageDialog(null, "Data Already present");
                            
                        }
                        else
                        {
                            String sql = "INSERT INTO Employee VALUES ('"+scompany+"', '"+sname+"', '"+sskill+"', "+sage+",'"+sdob+"','"+sdoj+"',"+ssalary+","+sempid+")";
                            try {
                                statement.executeUpdate(sql);
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            while(model.getRowCount() > 0){
                                model.removeRow(0);
                            }
   
            
                            sql = "select *from Test.Employee";
                            try (ResultSet rs = statement.executeQuery(sql)) {
                                while(rs.next()){
                                    sname = rs.getString(2);
                                    scompany= rs.getString(1);
                                    sskill = rs.getString(3);
                                    sage = rs.getString(4);
                                    sdob = rs.getString(5);
                                    sdoj = rs.getString(6);
                                    ssalary = rs.getString(7);
                                    sempid = rs.getString(8);
                                    String[] row = {sempid,sname,scompany,sskill,sage,sdob,sdoj,ssalary};
                                    model.addRow(row);
                                    
                                }
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(null, "Data Added");
                        }
                    } catch (HeadlessException e1) {
                        
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        
                        e1.printStackTrace();
                    }

                   
                        }

                  itext.setEditable(true);
                  itext.setText("");
                  ntext.setText("");
                  ctext.setText("");
                  ttext.setText("");
                  atext.setText("");
                  Bdate.setCalendar(null);
                  Jdate.setCalendar(null);
                  stext.setText("");
                
            
            }
        });

        backup.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            
            try {
                    FileWriter myWriter = new FileWriter("emp_backup.txt");
                    String sql;
            sql = "select *from Test.Employee";
            try (ResultSet rs = statement.executeQuery(sql)) {
                while(rs.next()){
                    sname = rs.getString(2);
                    scompany= rs.getString(1);
                    sskill = rs.getString(3);
                    sage = rs.getString(4);
                    sdob = rs.getString(5);
                    sdoj = rs.getString(6);
                    ssalary = rs.getString(7);
                    sempid = rs.getString(8);
                    myWriter.write("{\n{\nEmp_id:"+sempid+"\nEmp_name:"+sname+"\nCompany:"+scompany+"\nSkill:"+sskill+"\nSalary:"+ssalary+"\nAge:"+sage+"\nDOB:"+sdob+"\nDOJ:"+sdoj+"\n}\n}\n\n");
                    
                    
                }
                myWriter.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
                    System.out.println("Successfully wrote to the file.");
                  } catch (IOException exp) {
                    System.out.println("An error occurred.");
                    exp.printStackTrace();
                  }
                  try {
                    File myfile = new File("emp_backup.txt");
                    if (myfile.createNewFile()) {
                      System.out.println("File created: " + myfile.getName());
                      lbackup.setText("No Backup Done yet.");
                    } else {
                    long time = myfile.lastModified();
                      System.out.println("File already exists.");
                      lbackup.setText("Last Backup on "+sdf.format(time));
                    }
                  } catch (IOException E) {
                    System.out.println("An error occurred.");
                    E.printStackTrace();
                  }
        
                
            JOptionPane.showMessageDialog(null, "Data Backup Done");
            }
        });

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String result = (String)JOptionPane.showInputDialog(
                  table,
                  "Search by Employee Id", 
                  "Search",            
                  JOptionPane.PLAIN_MESSAGE,
                  null,            
                  null, 
                  ""
               );
               if(result != null && result.length() > 0){
                String sql = "SELECT * FROM Employee WHERE e_id="+result+"";
                try (ResultSet rs = statement.executeQuery(sql)) {
                    while(rs.next()){
                        sname = rs.getString(2);
                        scompany= rs.getString(1);
                        sskill = rs.getString(3);
                        sage = rs.getString(4);
                        sdob = rs.getString(5);
                        sdoj = rs.getString(6);
                        ssalary = rs.getString(7);
                        sempid = rs.getString(8);
                        ta.setText("\nSearched EMPLOYEE Details:\n\nEmployeeId: " + sempid +"\nEmployeeName: " + sname+"\nSkills: " + sskill+"\nSalary: " + ssalary+"\nAge: " + sage+"\nDate of Joining: " + sdoj+"\nDate of Birth: " + sdob);
                        itext.setText(sempid);
                        ntext.setText(sname);
                        ctext.setText(scompany);
                        ttext.setText(sskill);
                        atext.setText(sage);
                        stext.setText(ssalary);
                        try {
                            date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(sdob);
                        } catch (ParseException e2) {
                            e2.printStackTrace();
                        }
                            Bdate.setDate(date);
                            try {
                                date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(sdoj);
                            } catch (ParseException e2) {
                                e2.printStackTrace();
                            }
                                Jdate.setDate(date);
                        
                        
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
               }else {
                  System.out.println("Not Selected");
               }
               
            }
         });

        delect.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int rowi = table.getSelectedRow();
                String key = (String) table.getModel().getValueAt(rowi, 0);

              String sql = "DELETE FROM Employee WHERE e_id='"+key+"'";
              try {
                statement.executeUpdate(sql);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            while(model.getRowCount() > 0){
                model.removeRow(0);
            }

            
            sql = "select *from Test.Employee";
            try (ResultSet rs = statement.executeQuery(sql)) {
                while(rs.next()){
                    sname = rs.getString(2);
                    scompany= rs.getString(1);
                    sskill = rs.getString(3);
                    sage = rs.getString(4);
                    sdob = rs.getString(5);
                    sdoj = rs.getString(6);
                    ssalary = rs.getString(7);
                    sempid = rs.getString(8);
                    String[] row = {sempid,sname,scompany,sskill,sage,sdob,sdoj,ssalary};
                    model.addRow(row);
                    
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Data Removed");


            itext.setEditable(true);
                  itext.setText("");
                  ntext.setText("");
                  ctext.setText("");
                  ttext.setText("");
                  atext.setText("");
                  Bdate.setCalendar(null);
                  Jdate.setCalendar(null);
                  stext.setText("");
            }
        });

        edit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            
           
            String key = itext.getText().toString();
                 

            String sql;
            
            sql = "UPDATE Employee SET e_name='"+ntext.getText().toString()+"' WHERE e_id='"+key+"'";
              try {
                statement.executeUpdate(sql);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            sql = "UPDATE Employee SET comapany='"+ctext.getText().toString()+"' WHERE e_id='"+key+"'";
              try {
                statement.executeUpdate(sql);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            sql = "UPDATE Employee SET skill='"+ttext.getText().toString()+"' WHERE e_id='"+key+"'";
              try {
                statement.executeUpdate(sql);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            sql = "UPDATE Employee SET dob='"+simpleDateFormat.format(Bdate.getDate())+"' WHERE e_id='"+key+"'";
              try {
                statement.executeUpdate(sql);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            sql = "UPDATE Employee SET doj='"+simpleDateFormat.format(Jdate.getDate())+"' WHERE e_id='"+key+"'";
              try {
                statement.executeUpdate(sql);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            sql = "UPDATE Employee SET age='"+atext.getText().toString()+"' WHERE e_id='"+key+"'";
              try {
                statement.executeUpdate(sql);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            sql = "UPDATE Employee SET salary='"+stext.getText().toString()+"' WHERE e_id='"+key+"'";
              try {
                statement.executeUpdate(sql);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            while(model.getRowCount() > 0){
                model.removeRow(0);
            }

            
            sql = "select *from Test.Employee";
            try (ResultSet rs = statement.executeQuery(sql)) {
                while(rs.next()){
                    sname = rs.getString(2);
                    scompany= rs.getString(1);
                    sskill = rs.getString(3);
                    sage = rs.getString(4);
                    sdob = rs.getString(5);
                    sdoj = rs.getString(6);
                    ssalary = rs.getString(7);
                    sempid = rs.getString(8);
                    String[] row = {sempid,sname,scompany,sskill,sage,sdob,sdoj,ssalary};
                    model.addRow(row);
                    
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            itext.setEditable(true);

                  itext.setText("");
                  ntext.setText("");
                  ctext.setText("");
                  ttext.setText("");
                  atext.setText("");
                  Bdate.setCalendar(null);
                  Jdate.setCalendar(null);
                  stext.setText("");
            }
        });


        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
               if (me.getClickCount() == 1) {     // to detect doble click events
                  JTable target = (JTable)me.getSource();
                  
                  int row = target.getSelectedRow(); // select a row
                  itext.setText(table.getValueAt(row, 0).toString());
                  ntext.setText(table.getValueAt(row, 1).toString());
                  ctext.setText(table.getValueAt(row, 2).toString());
                  ttext.setText(table.getValueAt(row, 3).toString());
                  atext.setText(table.getValueAt(row, 4).toString());
                  stext.setText(table.getValueAt(row, 7).toString());
                  
                try {
                    date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(table.getValueAt(row, 5).toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                    Bdate.setDate(date);
                    try {
                        date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(table.getValueAt(row, 6).toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                       Jdate.setDate(date);
                  
                  itext.setEditable(false);
               }
            }
         });

        table.setModel(model);

        

       
        
        

        this.setTitle("Employee Management System");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1275, 720);
        this.setVisible(true);
        this.setResizable(false);
        this.add(title);
        this.add(cJPanel);
        this.add(nJPanel);
        this.add(jPanel);
        this.add(aJPanel);
        this.add(sJPanel);
        this.add(sPanel);
        this.add(bJPanel);
        this.add(iPanel);
        this.add(button_bar);
        this.add(lbackup);
        this.add(ta);
        
    }

    
    public static void main(String[] args) {
        new App();
    }
}