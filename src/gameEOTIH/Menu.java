package gameEOTIH;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Menu extends JMenuBar {
    private JPanel FilePane;
    JMenu qa, acc;
    JMenuItem miNew, miOpen,miOpen2;

    Menu(JPanel FilePane) { // access in package only
        this.FilePane = FilePane;
        init();
    }

    private void init() {
        // Build the first menu.
        renderQAManager();
        // Build second menu in the menu bar.
        renderAccountManager();

        this.add(qa);
        this.add(acc);
    }

    private void renderQAManager() {
        qa = new JMenu("QA manager");
        // menu.setMnemonic(KeyEvent.VK_A);
        qa.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
        this.add(qa);

        // a group of JMenuItems
        miNew = new JMenuItem("New", new ImageIcon("images/middle.gif"));
        miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        // menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really
        // do anything");
        miNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame fr = new JFrame();

                // JFrame config
                fr.setSize(500, 400);
                fr.setResizable(false);

                // Instruction Pane
                JPanel instructionPane = new JPanel(new FlowLayout());
                instructionPane.add(new JLabel("Format: <Question>?<Answer>"));
                instructionPane.add(new JLabel("Example: What is capital of VN?ha noi"));

                JButton btnSave = new JButton("Save");
                JButton btnCancel = new JButton("Cancel");

                JPanel listPane = new JPanel();
                listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

                JTextArea ta = new JTextArea(300, 300);

                // Lay out the label and scroll pane from top to bottom.
                /*
                 * JScrollPane listScroller = new JScrollPane(listPane);
                 * listScroller.setPreferredSize(new Dimension(250, 80));
                 * listScroller.setAlignmentX(LEFT_ALIGNMENT);
                 */

                listPane.add(ta);
                listPane.add(Box.createRigidArea(new Dimension(0, 5)));
                // listPane.add(listScroller);
                listPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                // Lay out the buttons from left to right.
                JPanel buttonPane = new JPanel();
                buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
                buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
                buttonPane.add(Box.createHorizontalGlue());
                buttonPane.add(btnCancel);
                buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
                buttonPane.add(btnSave);

                // Put everything together, using the content pane's BorderLayout.

                fr.add(instructionPane, BorderLayout.PAGE_START);
                fr.getContentPane().add(listPane, BorderLayout.CENTER);
                fr.getContentPane().add(buttonPane, BorderLayout.PAGE_END);
                fr.setVisible(true);

                btnSave.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser fc = new JFileChooser();

                        // FileChoose configuation
                        //fc.setMultiSelectionEnabled(true);
                        fc.setDialogTitle("Choose Directory to save files");
                        fc.setCurrentDirectory(new java.io.File(".")); // get current directory
                        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        fc.setAcceptAllFileFilterUsed(false);

                        // Function
                        int res = fc.showOpenDialog(null);
                        if (res == JFileChooser.APPROVE_OPTION) {
                            String file_name = JOptionPane.showInputDialog(fr, "File name: ");

                            String path = fc.getSelectedFile().getAbsolutePath() + "\\" + file_name;
                            System.out.println(path);

                            String[] lines = ta.getText().split("\n");

                            FileWriter ques_writer = null;
                            FileWriter ans_writer = null;
                            try {
                                ques_writer = new FileWriter(new File(path + "_question.txt"));
                                ans_writer = new FileWriter(new File(path + "_answer.txt"));
                                for (String line : lines) {
                                    String[] questions = line.split("\\?");
                                    System.out.println(questions[0]);
                                    System.out.println(questions[1]);
                                    ques_writer.append(questions[0] + "?\n");
                                    ans_writer.append(questions[1] + "\n");
                                }
                                ta.setText("");
                            } catch (IOException exeption) {
                                exeption.printStackTrace();
                                JOptionPane.showMessageDialog(fr, "Cannot save file", "Error", JOptionPane.ERROR_MESSAGE);
                            } finally {
                                if (ques_writer != null) {
                                    try {
                                        ques_writer.close();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                                if (ans_writer != null) {
                                    try {
                                        ans_writer.close();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                });
            }
        });
        qa.add(miNew);

        miOpen = new JMenuItem("Open", new ImageIcon("images/middle.gif"));
        miOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        // menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really
        // do anything");
        miOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame fr = new JFrame("Choose File to Open");
                // Config JFrame
                fr.setSize(400, 150);
                fr.setVisible(true);
                fr.setLocation(100, 100);
                fr.setResizable(false);

                // render File Chooser
                fr.add(FilePane);
            }

        });
        qa.add(miOpen);


        miOpen2 = new JMenuItem("Open File From Database", new ImageIcon("images/middle.gif"));
        miOpen2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        // menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really
        // do anything");
        miOpen2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame fr = new JFrame("Choose File to Open");
                // Config JFrame
                fr.setSize(400, 150);
                fr.setVisible(true);
                fr.setLocation(100, 100);
                fr.setResizable(false);

                // render File Chooser
                fr.add(FilePane);
            }

        });
        qa.add(miOpen2);
    }

    private void renderAccountManager() {
        acc = new JMenu("Account Manager");
        acc.setMnemonic(KeyEvent.VK_N);
        acc.getAccessibleContext().setAccessibleDescription("This menu does nothing");
    }
}