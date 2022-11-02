import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    private JLabel judul;
    private JTextArea inputTf;
    private JTextArea outputTf;
    private JButton pasteBtn;
    private JButton copyBtn;
    private JButton generateBtn;
    private JPanel panelMain;
    private JButton resetBtn;
    private JScrollPane scrollinput;
    private JScrollPane scrollOutput;
    private JScrollBar scrollBar1;
    private JScrollPane scroll;

    public Main() {

        scrollinput.createVerticalScrollBar();
        inputTf.setLineWrap(true);
        inputTf.setWrapStyleWord(true);

        scrollOutput.createVerticalScrollBar();
        outputTf.setLineWrap(true);
        outputTf.setWrapStyleWord(true);



        generateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text;
                text = inputTf.getText();

                text = text.replaceAll("\n", " ");
                outputTf.setText(text);

            }
        });
        copyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String myString = outputTf.getText();
                StringSelection stringSelection = new StringSelection(myString);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });

        pasteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
                try {
                    if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                        String text = (String) t.getTransferData(DataFlavor.stringFlavor);
                        inputTf.setText(text.trim());
                    }
                    else JOptionPane.showMessageDialog(panelMain, "Text Kosong");
                } catch (Exception ignored) {
                }
            }
        });
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputTf.setText("");
                outputTf.setText("");
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Formatting");
        frame.setContentPane(new Main().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}