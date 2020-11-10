package br.univali.compiladores.semantico;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.Utilities;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author biankatpas
 */
public class ScreenController {

    private langX p;
    private String filePath;
    private String fileName;
    private int error;

    public ScreenController() {
        error = 0;
    }

    public void setFile(String fp, String fn) {
        fileName = fn;
        filePath = fp;
    }

    public String newFile(JEditorPane jtaEdit, JTextArea jtaMessage, JFrame jf, String fileName, String filePath) {
        if (fileName.equals("sem nome.djt")) {
            if ("".equals(jtaEdit.getText())) {
                fileName = "sem nome.djt";
                jf.setTitle("Compilador - [sem nome.djt]");
                jtaMessage.setText("");
            } else {
                Object[] options
                        =
                        {
                                "Sim", "Não", "Cancelar"
                        };
                int op = JOptionPane.showOptionDialog(null, fileName + " foi alterado, salvar alterações?", "Salvar Alterações?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[2]);
                if (op == 1) //dont save file
                {
                    fileName = "sem nome.djt";
                    jtaEdit.setText("");
                    jtaMessage.setText("");
                    jf.setTitle("Compilador - [sem nome.djt]");
                } else if (op == 0) //save file
                {
                    save(fileName, filePath, jtaEdit, jf);
                    fileName = "sem nome.djt";
                    jtaEdit.setText("");
                    jtaMessage.setText("");
                    jf.setTitle("Compilador - [sem nome.djt]");
                }
            }

        } else {
//            System.out.println(fileName);
//            System.out.println(filePath);
            if (isEdited(jtaEdit, filePath) == true) {
                Object[] options
                        =
                        {
                                "Sim", "Não", "Cancelar"
                        };
                int op = JOptionPane.showOptionDialog(null, fileName + " foi alterado, salvar alterações?", "Salvar Alterações?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[2]);
                if (op == 0) {//save file
                    save(fileName, filePath, jtaEdit, jf);
                    fileName = "sem nome.djt";
                    filePath = ("");
                    jtaEdit.setText("");
                    jtaMessage.setText("");
                    jf.setTitle("Compilador - [sem nome.djt]");
                } else if (op == 1) //dont save file
                {
                    fileName = "sem nome.djt";
                    filePath = ("");
                    jtaEdit.setText("");
                    jtaMessage.setText("");
                    jf.setTitle("Compilador - [sem nome.djt]");
                }
            } else {
                fileName = "sem nome.djt";
                filePath = ("");
                jtaEdit.setText("");
                jtaMessage.setText("");
                jf.setTitle("Compilador - [sem nome.djt]");
            }
        }
        this.fileName = fileName;
        return fileName;
    }

    public String save(String fileName, String filePath, JEditorPane jta, JFrame jf) {
        String dir = getPath(filePath);

        try {
            if (fileName.equalsIgnoreCase("sem nome.djt")) {
                String res = saveAs(jta, fileName, filePath, jf);
                String array[] = new String[2];
                array = res.split(",");
                fileName = array[0];
                filePath = array[1];
            } else {
                BufferedWriter bw = new BufferedWriter(new FileWriter(dir));
                Scanner reader = new Scanner(jta.getText());
                while (reader.hasNextLine()) {
                    bw.write(reader.nextLine());
                    bw.newLine();
                }
                bw.close();
                jf.setTitle("Compilador - " + fileName);
            }
        } catch (IOException ex) {
            Logger.getLogger(ScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.fileName = fileName;
        this.filePath = filePath;
        return fileName + "," + filePath;
    }

    public String saveAs(JEditorPane jta, String fileName, String filePath, JFrame jf) {
        JFileChooser fileChooser = new JFileChooser();
        String originalPath = filePath;
        String originalName = fileName;
        FileNameExtensionFilter djt = new FileNameExtensionFilter("DJT (*.djt)", "djt");

        String dir = getPath(filePath);
        fileChooser.setSelectedFile(new File(dir));
        fileChooser.setFileFilter(djt);
        fileChooser.setSelectedFile(new File(filePath));

        try {
            if (fileChooser.showSaveDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                filePath = file.getPath();
                if (!filePath.contains(".djt")) {
                    filePath += ".djt";
                }

                File selectedFile = new File(filePath);
                fileName = selectedFile.getName();

                if (selectedFile.exists()) {
                    Object[] options
                            =
                            {
                                    "Sim", "Não", "Cancelar"
                            };
                    int op = JOptionPane.showOptionDialog(null, fileName + " já existe, Deseja substituí-lo?", "Confirmar Salvar Como", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

                    // save
                    if (op == 0) {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile));
                        Scanner reader = new Scanner(jta.getText());
                        while (reader.hasNextLine()) {
                            bw.write(reader.nextLine());
                            bw.newLine();
                        }
                        bw.close();
                        jf.setTitle("Compilador - " + fileName);
                    } else { // dont save
                        filePath = originalPath;
                        fileName = originalName;
                    }

                } else {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile));
                    Scanner reader = new Scanner(jta.getText());
                    while (reader.hasNextLine()) {
                        bw.write(reader.nextLine());
                        bw.newLine();
                    }
                    bw.close();
                    jf.setTitle("Compilador - " + fileName);
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.fileName = fileName;
        this.filePath = filePath;

        return fileName + "," + filePath;
//        return fileName;
    }

    public boolean isEdited(JEditorPane jta, String fileName) {
        try {

            FileReader arq = new FileReader(fileName);
            BufferedReader lerArq = new BufferedReader(arq);
            Scanner reader = new Scanner(jta.getText());
            ArrayList<String> jtaLines = new ArrayList<>();
            ArrayList<String> originalFileLines = new ArrayList<>();

            String linha = lerArq.readLine();
            originalFileLines.add(linha);
            while (linha != null) {
                linha = lerArq.readLine();
                if (linha != null) {
                    originalFileLines.add(linha);
                }
            }
            arq.close();
            while (reader.hasNextLine()) {
                jtaLines.add(reader.nextLine());
            }

            if (originalFileLines.size() != jtaLines.size()) {
                return true;
            } else {

                for (int i = 0; i < originalFileLines.size(); i++) {
                    if (!originalFileLines.get(i).equals(jtaLines.get(i))) {
                        return true;
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private String getPath(String filePath) {
        File exeFile = new File(".");
        String path = "";
        if (filePath.equals("")) {
            path = exeFile.getAbsolutePath();
        } else {
            exeFile = new File(filePath);
            path = exeFile.getAbsolutePath();
        }
        return path;
    }

    public String openFile(JEditorPane jtaEdit, JTextArea jtaMessage, String fileName, String filePath, JFrame jf) {
        String newFileName = fileName;
        String dir = "";
        String res = "";

        final JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter djt = new FileNameExtensionFilter("DJT (*.djt)", "djt");
        FileFilter defaultFilter = fc.getFileFilter();
        dir = getPath(filePath);
//        System.out.println(dir);
        fc.setSelectedFile(new File(dir));
        fc.setFileFilter(djt);

        try {
            if (fileName.equals("sem nome.djt")) {
                if ("".equals(jtaEdit.getText())) {
                    if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
                        if (fc.getSelectedFile() != null) {
                            dir = fc.getSelectedFile().getAbsolutePath();
                            filePath = fc.getSelectedFile().toString();
                            File selectedFile = new File(filePath);
                            newFileName = selectedFile.getName();
                            readFileText(selectedFile.toString(), jtaEdit);
                            jtaMessage.setText("");
                            jf.setTitle("Compilador - " + newFileName);
                        }
                    }
                } else {
                    Object[] options
                            =
                            {
                                    "Sim", "Não", "Cancelar"
                            };
                    int op = JOptionPane.showOptionDialog(null, fileName + " foi alterado, salvar alterações?", "Salvar Alterações?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[2]);

                    if (op == 1) //dont save file
                    {
                        if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
                            if (fc.getSelectedFile() != null) {

                                dir = fc.getSelectedFile().getAbsolutePath();
                                filePath = fc.getSelectedFile().toString();
                                File selectedFile = new File(filePath);
                                newFileName = selectedFile.getName();
                                readFileText(selectedFile.toString(), jtaEdit);
                                jtaMessage.setText("");
                                jf.setTitle("Compilador - " + newFileName);
                            }
                        }
                    } else if (op == 0) //save file
                    {
                        save(dir + fileName, filePath, jtaEdit, jf);
                        if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
                            if (fc.getSelectedFile() != null) {
                                dir = fc.getSelectedFile().getAbsolutePath();
                                filePath = fc.getSelectedFile().toString();
                                File selectedFile = new File(filePath);
                                newFileName = selectedFile.getName();
                                readFileText(selectedFile.toString(), jtaEdit);
                                jtaMessage.setText("");
                                jf.setTitle("Compilador - " + newFileName);
                            }
                        }
                    }
                }
            } else {
                if (isEdited(jtaEdit, dir) == true) {
                    Object[] options
                            =
                            {
                                    "Sim", "Não", "Cancelar"
                            };
                    int op = JOptionPane.showOptionDialog(null, fileName + " foi alterado, salvar alterações?", "Salvar Alterações?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[2]);
                    if (op == 0) //save file
                    {
                        save(dir, filePath, jtaEdit, jf);
                        if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
                            if (fc.getSelectedFile() != null) {
                                dir = fc.getSelectedFile().getAbsolutePath();
                                filePath = fc.getSelectedFile().toString();
                                File selectedFile = new File(filePath);
                                newFileName = selectedFile.getName();
                                readFileText(selectedFile.toString(), jtaEdit);
                                jtaMessage.setText("");
                                jf.setTitle("Compilador - " + newFileName);
                            }
                        }
                    } else if (op == 1) //dont save file
                    {
                        if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
                            if (fc.getSelectedFile() != null) {
                                dir = fc.getSelectedFile().getAbsolutePath();
                                filePath = fc.getSelectedFile().toString();
                                File selectedFile = new File(filePath);
                                newFileName = selectedFile.getName();
                                readFileText(selectedFile.toString(), jtaEdit);
                                jtaMessage.setText("");
                                jf.setTitle("Compilador - " + newFileName);
                            }
                        }
                    }
                } else {
                    if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
                        if (fc.getSelectedFile() != null) {
                            dir = fc.getSelectedFile().getAbsolutePath();
                            filePath = fc.getSelectedFile().toString();
                            File selectedFile = new File(filePath);
//                            System.out.println(selectedFile);
                            newFileName = selectedFile.getName();
                            readFileText(selectedFile.toString(), jtaEdit);
                            jtaMessage.setText("");
                            jf.setTitle("Compilador - " + newFileName);
                        }
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado.");
            newFileName = fileName;
        } catch (IOException ex) {
            Logger.getLogger(ScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.fileName = newFileName;
        this.filePath = dir;

        return newFileName + "," + dir;
    }

    private void readFileText(String fileName, JEditorPane jta) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            jta.setText(sb.toString());
        } finally {
            br.close();
        }

    }

    public void exit(JEditorPane jta, String fileName, String filePath, JFrame jf) {
        if (fileName.equals("sem nome.djt")) {
            if ("".equals(jta.getText())) {
                System.exit(0);
            } else {
                Object[] options
                        =
                        {
                                "Sim", "Não", "Cancelar"
                        };
                int op = JOptionPane.showOptionDialog(null, fileName + " foi alterado, salvar alterações?", "Salvar Alterações?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[2]);
                if (op == 1) //dont save file
                {
                    System.exit(0);
                } else if (op == 0) //save file
                {
                    saveAs(jta, fileName, filePath, jf);
                    System.exit(0);
                }
            }
        } else {
            if (isEdited(jta, fileName) == true) {
                Object[] options
                        =
                        {
                                "Sim", "Não", "Cancelar"
                        };
                int op = JOptionPane.showOptionDialog(null, fileName + " foi alterado, salvar alterações?", "Salvar Alterações?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[2]);
                if (op == 1) //dont save file
                {
                    System.exit(0);
                } else if (op == 0) //save file
                {
                    save(fileName, filePath, jta, jf);
                    System.exit(0);
                }
            } else {
                System.exit(0);
            }
        }
    }

    public void about() {
        JOptionPane.showMessageDialog(null, "Desenvolvido por:\nBianka Passos\nJuliana Sanguinetto");
    }

    public void cut(JEditorPane jta) {
        jta.cut();
    }

    public void copy(JEditorPane jta) {
        jta.copy();
    }

    public void paste(JEditorPane jta) {
        jta.paste();
    }

    public void getPosition(CaretEvent evt, JLabel lb) {
//        int row = jta.getDocument().getRootElements()[0].getElementIndex(jta.getCaretPosition());
//        int col = jta.getCaretPosition() - jta.getDocument().getRootElements()[0].getElement(row).getStartOffset();
        int x = getRow(evt.getDot(), (JTextComponent) evt.getSource());
        int y = getColumn(evt.getDot(), (JTextComponent) evt.getSource());
        lb.setText("Linha: " + x + ", Coluna:" + y);
    }

    private int getRow(int pos, JTextComponent editor) {
        int rn = (pos == 0) ? 1 : 0;
        try {
            int offs = pos;
            while (offs > 0) {
                offs = Utilities.getRowStart(editor, offs) - 1;
                rn++;
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return rn;
    }

    private int getColumn(int pos, JTextComponent editor) {
        try {
            return pos - Utilities.getRowStart(editor, pos) + 1;
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //    public void setFeedback(JLabel lb, JLabel lbErr, boolean status, int error, int errorLex, int errorSint, int errorSem) {
//        lbErr.setText("");
//        if (!status) {
//            lb.setText("Compilado com sucesso...");
//            lb.setForeground(Color.black);
//        } else {
//            lb.setText("Contem " + error + " erro(s)...");
//            lb.setForeground(Color.RED);
//            if (errorLex > 0){
//                lbErr.setText("Erros Lexicos: "+errorLex);
//            }
//            else if (errorSint > 0){
//                lbErr.setText("Erros Sintaticos: "+errorSint);
//            }
//            else if (errorSem > 0){
//                lbErr.setText("Erros Semanticos: "+errorSem);
//            }
//        }
//    }
    public List<Instruction> compile(JEditorPane jta, JFrame jf, JEditorPane jtaEdit, JTextArea jtaMessage, JLabel lbErr, JLabel lbErr2, List<Instruction> instructions) {
        ErrorManager.errosSintaticos.clear();
        ErrorManager.erroslexicos.clear();
        String result = "";
        try {
//            p._start("/home/biankatpas/Dropbox/Workspace/compilador/src/djt/teste.djt");
            System.out.println(fileName);

            if (filePath.equalsIgnoreCase("")) {
                saveAs(jtaEdit, fileName, filePath, jf);
            } else {
                save(fileName, filePath, jta, jf);
            }

            p._start(this.filePath);
            if (!ErrorManager.getErroslexicos().isEmpty()) {
                lbErr.setText("Erros Léxicos Encontrados");
                lbErr2.setForeground(Color.red);
                lbErr2.setText(ErrorManager.getErroslexicos().size() + " erro(s)");
                for (String str : ErrorManager.getErroslexicos()) {
                    result += str;
                }
            } else if (!ErrorManager.getErrosSintaticos().isEmpty()) {
                lbErr.setText("Erros Sintáticos Encontrados");
                lbErr2.setForeground(Color.red);
                lbErr2.setText(ErrorManager.getErrosSintaticos().size() + " erro(s)");
                for (String str : ErrorManager.getErrosSintaticos()) {
                    result += str;
                }
            } else {
                p._runSemantic(filePath);
                if (!p.semantic.erros.isEmpty()) {
                    error = p.semantic.erros.size();
                    lbErr.setText("Erros Semânticos Encontrados");
                    lbErr2.setForeground(Color.red);
                    lbErr2.setText(p.semantic.erros.size() + " erro(s)");
                    for (String str : p.semantic.erros) {
                        result += str + "\n";
                    }
                } else {
                    error = 0;
                    lbErr.setText("Compilado com sucesso");
                    lbErr2.setForeground(Color.BLACK);
                    lbErr2.setText("0 erro(s)");
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        jtaMessage.setText(result);
        return instructions;
    }

    //    public List<Instruction> compile(JEditorPane jtaEdit, JTextArea jtaMessage, JLabel lbErr, JLabel lbErr2, List<Instruction> instructions ) {
//        int sint_error_counter = 0, lex_error_counter = 0, sem_error_counter = 0;
//        if (!jtaEdit.getText().equals("")) {
//            String output = "";
//            jtaMessage.setText("");
//            List<Message> lexicalOutput = p.lexicalAnalizer(jtaEdit.getText());
//            lex_error_counter = lexicalOutput.size();
//            if (lex_error_counter > 0) {
//                for (int i = 0; i < lex_error_counter; i++) {
//                    if (lexicalOutput.get(i).isError()) {
//                        output += lexicalOutput.get(i).getMessage() + "\n";
//                    } else {
//                        output += lexicalOutput.get(i).getMessage() + "\n";
//                    }
//                }
//            } else {
//                List<Message> sintaticalOutput = p.sintaticalAnalizer(jtaEdit.getText());
//                sint_error_counter = sintaticalOutput.size();
//                if (sint_error_counter > 0) {
//                    for (int i = 0; i < sintaticalOutput.size(); i++) {
//                        if (sintaticalOutput.get(i).isError()) {
//                            output += sintaticalOutput.get(i).getMessage() + "\n";
//                        } else {
//                            output += sintaticalOutput.get(i).getMessage() + "\n";
//                        }
//                    }
//                }
//                 sem_error_counter = p.semantic.getNumeroDeErros();
//                 String semanticalOutput = p.semantic.getListaDeErrosAsString();
//                 output += semanticalOutput;
//            }
//            jtaMessage.setText(output);
//            setFeedback(lbErr, lbErr2, (sint_error_counter + lex_error_counter + sem_error_counter) > 0, (sint_error_counter + lex_error_counter + sem_error_counter), lex_error_counter, sint_error_counter, sem_error_counter);
//        } else {
//            JOptionPane.showMessageDialog(null, "Um arquivo vazio não pode ser compilado.");
//        }
//        
//        instructions = p.getSemanticTable().getInstructions();
//        TableObject tblo = new TableObject();
//        tblo.setInstructions(instructions);
//        tblo.updateTable();
//        tblo.setVisible(true);
//        return instructions;
//    }
//    public void run(List<Instruction> instructions,JTextArea jtaMessage ){
////        System.out.print("\033[H\033[2J");
////        System.out.flush();
////        System.out.println(p.semantic.toString());
//        Console console = new Console();
//        console.setConsoleOutput(p.semantic.toString());
//
//        VirtualMachine virtualmachine = new VirtualMachine(instructions);
//        List<String> listAux = virtualmachine.runVM();
////        String aux = "";
////        for (String string : listAux) {
////            aux += string;
////        }
////        jtaMessage.setText(aux);
////        JOptionPane.showMessageDialog(null, aux);
//        console.setVMOutput(listAux);
//        
//        console.setVisible(true);
//    }
    public void run(JTextArea jtaMessage, List<Instruction> instructions, TableObject tblo, Console console) {
//        compile(jtaEdit, jf, jtaEdit, jtaMessage, lbErr, lbErr2, instructions);
        if (!ErrorManager.getErroslexicos().isEmpty() || !ErrorManager.getErrosSintaticos().isEmpty() || error > 0) {
            JOptionPane.showMessageDialog(null, "Existem erros: sintáticos, léxicos ou semânticos!");
            return;
        } else {
            String result = "";

            try {
//            p._runSemantic("/home/biankatpas/Dropbox/Workspace/compilador/src/djt/teste.djt");
//            p._runSemantic(filePath);
//            if (!p.semantic.erros.isEmpty())
//            {
//                lbErr.setText("Erros Semânticos Encontrados");
//                lbErr2.setForeground(Color.red);
//                lbErr2.setText(p.semantic.erros.size() + " erro(s)");
//                for (String str : p.semantic.erros)
//                {
//                    result += str + "\n";
//                }
//            } else
//            {
//                TableObject tblo = new TableObject();
//                tblo.dispose();

                tblo.setInstructions(p.semantic.getAreaDeInstrucoes());
                tblo.updateTable();
                tblo.setVisible(true);

//                Console console = new Console();
                console.dispose();
                console.setConsoleOutput(p.semantic.toString());

                VirtualMachine virtualmachine = new VirtualMachine(instructions);
                List<String> listAux = virtualmachine.runVM();
                System.out.println(listAux);
                console.setVMOutput(listAux);
                console.setVisible(true);

//            }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(p.semantic.toString());
            jtaMessage.setText(result);
        }
    }
}
