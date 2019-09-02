// Java Program to create a text editor using java 

import parser.ParseException;
import parser.TokenMgrError;
import parser.linguagem2019;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class editor extends JFrame implements ActionListener {
    private JLabel footnoteLabelText;
    private JTextArea t2;
    // Text component
    private JTextArea t;

    // Frame 
    private JFrame f;
    private JLabel footnoteLabelFile;

    // Constructor 
    private editor() {
        // Create a frame 
        f = new JFrame("Compilador");

        try {
            // Set metl look and feel 
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            // Set theme to ocean 
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {
            System.out.println(e);
        }

        // Text component 
        t = new JTextArea();
        t.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent evt) {
                JTextArea textPane1 = (JTextArea) evt.getSource();
                int row = getRow(evt.getDot(), textPane1); //row += 1;
                int col = getColumn(evt.getDot(), textPane1);
                footnoteLabelText.setText("Line: " + row + " Column: " + col);
            }
        });
        t.setLineWrap(true);
        t.setPreferredSize(new Dimension(400, 400));
        t.setMinimumSize(new Dimension(400, 400));
        t.setBounds(0, 0, 400, 400);
        t2 = new JTextArea();
        t2.setLineWrap(true);
        t2.setPreferredSize(new Dimension(400, 400));
        t2.setMinimumSize(new Dimension(400, 400));
        t2.setBounds(0, 0, 400, 400);
        t2.setEditable(false);

        // Footnote
        footnoteLabelFile = new JLabel();
        footnoteLabelText = new JLabel();

        // Create a menubar 
        JMenuBar mb = new JMenuBar();

        // Create amenu for menu 
        JMenu m1 = new JMenu("File");

        // Create menu items 
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi2 = new JMenuItem("Open");
        JMenuItem mi3 = new JMenuItem("Save");
        JMenuItem mi9 = new JMenuItem("Print");

        // Add action listener 
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);

        // Create amenu for menu 
        JMenu m2 = new JMenu("Edit");

        // Create menu items 
        JMenuItem mi4 = new JMenuItem("Cut");
        JMenuItem mi5 = new JMenuItem("Copy");
        JMenuItem mi6 = new JMenuItem("Paste");

        // Add action listener 
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);

        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);

        JMenu m3 = new JMenu("Compiler");

        JMenuItem mi7 = new JMenuItem("Compile");
        JMenuItem mi8 = new JMenuItem("Execute");
        mi7.addActionListener(this);

        m3.add(mi7);
        m3.add(mi8);

        mb.add(m1);
        mb.add(m2);
        mb.add(m3);

        f.setJMenuBar(mb);
        JPanel jp = new JPanel(new GridLayout(0, 2, 2, 2));
        JPanel jp1 = new JPanel();
        jp1.add(t);
        JPanel jp2 = new JPanel();
        jp2.add(t2);
        jp.add(new JScrollPane(jp1));
        jp.add(new JScrollPane(jp2));
        f.add(createMenuButton(), BorderLayout.NORTH);
        JPanel footnote = new JPanel(new GridLayout(0, 3, 2, 2));
        footnote.add(footnoteLabelFile);
        footnote.add(footnoteLabelText);
        f.add(footnote, BorderLayout.SOUTH);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.add(jp);
        f.setSize(800, 600);
        f.show();
    }

    // Main class
    public static void main(String[] args) {
        editor e = new editor();
    }

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    // If a button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("Cut")) {
            t.cut();
        } else if (s.equals("Copy")) {
            t.copy();
        } else if (s.equals("Paste")) {
            t.paste();
        } else if (s.equals("Save")) {
            // Create an object of JFileChooser class
            JFileChooser j = new JFileChooser("f:");

            // Invoke the showsSaveDialog function to show the save dialog
            int r = j.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {

                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // Create a file writer
                    FileWriter wr = new FileWriter(fi, false);

                    // Create buffered writer to write
                    BufferedWriter w = new BufferedWriter(wr);

                    // Write
                    w.write(t.getText());

                    w.flush();
                    w.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation");
        } else if (s.equals("Print")) {
            try {
                // print the file
                t.print();
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
        } else if (s.equals("Open")) {
            // Create an object of JFileChooser class
            JFileChooser j = new JFileChooser("f:");

            // Invoke the showsOpenDialog function to show the save dialog
            int r = j.showOpenDialog(null);

            // If the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                footnoteLabelFile.setText(fi.getName());
                try {
                    // String
                    String s1 = "", sl = "";

                    // File reader
                    FileReader fr = new FileReader(fi);

                    // Buffered reader
                    BufferedReader br = new BufferedReader(fr);

                    // Initilize sl
                    sl = br.readLine();

                    // Take the input from the file
                    while ((s1 = br.readLine()) != null) {
                        sl = sl + "\n" + s1;
                    }

                    // Set the text
                    t.setText(sl);
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation");
        } else if (s.equals("New")) {
            t.setText("");
        } else if (s.equals("close")) {
            f.setVisible(false);
        } else if (s.equals("Compile")) {
            if (!t.getText().isEmpty()) {
                linguagem2019 compiler = new linguagem2019(new StringBufferInputStream(t.getText()));
                ByteArrayOutputStream baos = getSystemPrint(compiler);
                t2.setText(baos.toString());
            }
        }
    }

    private ByteArrayOutputStream getSystemPrint(linguagem2019 compiler) {
        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        // Print some output: goes to your special stream
        try {
            compiler.Start();
        } catch (ParseException e) {
            System.out.println("ExampleParser: There was an error during the parse.");
            System.out.println(e.getMessage());
        } catch (TokenMgrError e) {
            System.out.println("ExampleParser: There was an error.");
            System.out.println(e.getMessage());
        }
        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened
        return baos;
    }

    private JPanel createMenuButton() {
        JPanel menuButton = new JPanel();
        JButton compile = new JButton("Compile");
        ImageIcon compileIcon = new ImageIcon(getClass().getResource("hammer.png"));
        compile.setIcon(resizeIcon(compileIcon, 25, 25));
        compile.setSize(25, 25);
        compile.setBackground(Color.white);
        compile.setMargin(new Insets(0, 0, 0, 0));
        compile.addActionListener(this);
        compile.setBorder(null);
        menuButton.add(compile);
        return menuButton;
    }

    private int getRow(int pos, JTextArea textPane1) {
        int rn = (pos == 0) ? 1 : 0;
        try {
            int offs = pos;
            while (offs > 0) {
                offs = Utilities.getRowStart(textPane1, offs) - 1;
                rn++;
            }
        } catch (BadLocationException evt) {
            evt.printStackTrace();
        }

        return rn;
    }

    private int getColumn(int pos, JTextArea textPane1) {
        try {
            return pos - Utilities.getRowStart(textPane1, pos) + 1;
        } catch (BadLocationException evt) {
            evt.printStackTrace();
        }

        return -1;
    }
}