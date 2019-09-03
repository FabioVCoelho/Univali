// Java Program to create a text editor using java 

import parser.ParseException;
import parser.TokenMgrError;
import parser.linguagem2019;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// TODO: Criar os botãozinho.
// TODO: Melhorar JTextArea para resize.
// TODO: Melhorar mensagem de token.
// TODO: Melhorar mensagem de erro e analisar o de comentário.

class Compilador extends JFrame implements ActionListener {
    private String filePath = "";
    private JLabel footnoteLabelText;
    private JTextArea t2;
    // Text component
    private JTextArea t;

    // Frame 
    private JFrame f;
    private JLabel footnoteLabelFile;

    // Constructor 
    private Compilador() {
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
        t.addCaretListener(evt -> {
            JTextArea textPane1 = (JTextArea) evt.getSource();
            int row = getRow(evt.getDot(), textPane1); //row += 1;
            int col = getColumn(evt.getDot(), textPane1);
            footnoteLabelText.setText("Line: " + row + " Column: " + col);
        });
        t.setLineWrap(true);
        t.setPreferredSize(new Dimension(800, 800));
        t.setMinimumSize(new Dimension(800, 800));
        t.setBounds(0, 0, 800, 800);
        t2 = new JTextArea();
        t2.setLineWrap(true);
        t2.setPreferredSize(new Dimension(800, 800));
        t2.setMinimumSize(new Dimension(800, 800));
        t2.setBounds(0, 0, 800, 800);
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
        JMenuItem mi10 = new JMenuItem("Save As");
        JMenuItem mi9 = new JMenuItem("Print");
        JMenuItem m11 = new JMenuItem("Quit");

        // Add action listener 
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi10.addActionListener(this);
        mi9.addActionListener(this);
        m11.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi10);
        m1.add(mi9);
        m1.add(m11);

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

        JMenuItem m4 = new JMenuItem("About");

        m4.addActionListener(this);

        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(m4);

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
        new Compilador();
    }

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    // If a button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        switch (s) {
            case "Cut":
                t.cut();
                break;
            case "Copy":
                t.copy();
                break;
            case "Paste":
                t.paste();
                break;
            case "Save": {
                if (filePath.equals("")) {
                    JFileChooser j = new JFileChooser();
                    int r = j.showSaveDialog(null);
                    if (r == JFileChooser.APPROVE_OPTION) {
                        String absolutePath = j.getSelectedFile().getAbsolutePath();
                        saveFile(absolutePath);
                    } else
                        showMessageDialog("the user cancelled the operation");
                    break;
                } else {
                    saveFile(filePath);
                    break;
                }
            }
            case "Save As": {
                JFileChooser j = new JFileChooser();
                int r = j.showSaveDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
                    String absolutePath = j.getSelectedFile().getAbsolutePath();
                    if (j.getSelectedFile().exists()) {
                        int n = JOptionPane.showConfirmDialog(
                                this,
                                "Do You Want to Overwrite File?",
                                "Confirm Overwrite",
                                JOptionPane.YES_NO_OPTION);

                        if (n == JOptionPane.YES_OPTION)
                            saveFile(absolutePath);
                    } else {
                        saveFile(absolutePath);
                    }
                } else
                    showMessageDialog("the user cancelled the operation");
                break;
            }
            case "Print":
                try {
                    t.print();
                } catch (Exception evt) {
                    showMessageDialog(evt.getMessage());
                }
                break;
            case "Open": {
                exitingFile();
                JFileChooser j = new JFileChooser("f:");
                int r = j.showOpenDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
                    File fi = new File(j.getSelectedFile().getAbsolutePath());
                    filePath = fi.getAbsolutePath();
                    footnoteLabelFile.setText(fi.getName());

                    try {
                        String sl = getStringFromFile(fi);
                        t.setText(sl);
                    } catch (Exception evt) {
                        showMessageDialog(evt.getMessage());
                    }
                }
                // If the user cancelled the operation
                else
                    showMessageDialog("the user cancelled the operation");
            }
            break;
            case "New":
                exitingFile();
                t.setText("");
                footnoteLabelFile.setText("");
                filePath = "";
                break;
            case "Quit":
                wantToSaveFile();
                System.exit(0);
                break;
            case "Compile":
                if (!t.getText().isEmpty()) {
                    linguagem2019 compiler = new linguagem2019(new StringBufferInputStream(t.getText()));
                    ByteArrayOutputStream baos = getSystemPrint(compiler);
                    t2.setText(baos.toString());
                }
                break;
            case "About": {
                showMessageDialog("Created by: Fábio Volkmann Coelho");
                break;
            }
        }
    }

    private void showMessageDialog(String s) {
        JOptionPane.showMessageDialog(f, s);
    }

    private void exitingFile() {
        if (fileIsEdited() && !t.getText().equals("")) {
            int n = wantToSaveFile();
            if (n == JOptionPane.YES_OPTION && filePath.equals("")) {
                JFileChooser j = new JFileChooser("f:");
                int r = j.showSaveDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
                    saveFile(j.getSelectedFile().getAbsolutePath());
                }
            } else if (n == JOptionPane.YES_OPTION) {
                saveFile(filePath);
            }
        }
    }

    private void saveFile(String absolutePath) {
        File fi = new File(absolutePath);
        filePath = absolutePath;
        footnoteLabelFile.setText(fi.getName());
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
            showMessageDialog(evt.getMessage());
        }
    }

    private int wantToSaveFile() {
        int n = JOptionPane.showConfirmDialog(
                this,
                "Do You Want to Save File?",
                "Save File",
                JOptionPane.YES_NO_OPTION);
        return n;
    }

    private String getStringFromFile(File fi) throws IOException {
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
        return sl;
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
        } catch (ParseException | TokenMgrError e) {
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

    private boolean fileIsEdited() {
        try {
            return !t.getText().equals(getStringFromFile(new File(filePath)));
        } catch (IOException ignored) {

        }
        return true;
    }
}