package gameEOTIH;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;
import javax.swing.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Game extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private int point;
    private int question_num = 0;
    

    // Swing components
    private Container container;
    private JPanel questionPane, answerPane, nextChallengePane;
    private static JPanel FilePane;
    private JTextField answer;
    private JLabel checker, question, score;
    private BufferedReader reader_Ans;
    private BufferedReader reader_Ques;
    private File questionFile, answerFile;
    private JMenuBar menuBar;
    final JFileChooser fileDialog = new JFileChooser();
    static ArrayList<String> ques = new ArrayList<String>();
    static ArrayList<String> ans = new ArrayList<String>();
    // Queue and Stack
    private Queue<File> queue = new LinkedList<> ();
    private Stack<File> stack = new Stack<File> ();
    private int isStack;
  
    eotihMusic correctMusic = new eotihMusic("G:/JAVA/background/right.wav");
	eotihMusic wrongMusic = new eotihMusic("G:/JAVA/background/wrong.wav");
	eotihMusic bgMusic = new eotihMusic("G:/JAVA/background/gunny.wav");
	private JLabel question_1;
    public Game() throws IOException {
    	
        super("EOTIHvn.com Gameshow"); // use this instead of setTitle for JFrame
        getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 15));
        init();
        setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Logo EOTIH\\logo.png"));
        
    }

    private void init() throws IOException {
        this.point = 0;
        container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        renderFileChooser();
        renderQuestionPane();
        renderAnswerPane();
        renderNextChallengePane();
        container.add(questionPane);
        
        question_1 = new JLabel("Quesiton: ");
        question_1.setForeground(new Color(255, 255, 255));
        question_1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        question_1.setBounds(10, 10, 288, 49);
        questionPane.add(question_1);
        container.add(answerPane);
        container.add(nextChallengePane);
        menuBar = new Menu(FilePane);
        this.setJMenuBar(menuBar);
        renderWindow();
    }

    /**
     * Add this funciton to fix bug
     * 
     * @throws IOException
     */
    private void readFile() throws IOException {
        String question_name = questionFile.getName();
        
        answerFile = new File(questionFile.getParentFile().getAbsolutePath() + "\\" + question_name.substring(0, question_name.lastIndexOf("_")) + "_answer.txt");
        System.out.println(questionFile.getParentFile().getAbsolutePath() + "\\" + question_name.substring(0, question_name.lastIndexOf("_")) + "_answer.txt");
        InputStream inputStream_Ques = new FileInputStream(questionFile);
        
        InputStream inputStream_Ans = new FileInputStream(answerFile);
        InputStreamReader inputStreamReader_Ques = new InputStreamReader(inputStream_Ques);
        InputStreamReader inputStreamReader_Ans = new InputStreamReader(inputStream_Ans);
        
        reader_Ques = new BufferedReader(inputStreamReader_Ques);
        reader_Ans = new BufferedReader(inputStreamReader_Ans);

        // clear list
        ques.clear();
        ans.clear();
        String line_Ques = "";
        String line_Ans = "";
        while ((line_Ques = reader_Ques.readLine()) != null && (line_Ans = reader_Ans.readLine()) != null) {
            ques.add(line_Ques);
            ans.add(line_Ans);
        }

        // close stream
        inputStream_Ques.close();
        inputStreamReader_Ans.close();
    }

    private void renderWindow() {
        setSize(450, 650);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    private void renderFileChooser() {
        FilePane = new JPanel();
        FilePane.setPreferredSize(new Dimension(400, 100));

        // question
        JPanel questionFilePane = new JPanel(new FlowLayout(1, 10, 0));
        JButton ques_choose_file = new JButton("Choose File");
        JLabel ques_file_choosed = new JLabel("");

        // JFileChooser config
        fileDialog.setDialogTitle("File");
        fileDialog.setCurrentDirectory(new java.io.File(".")); // get current directory
        fileDialog.setMultiSelectionEnabled(true);

        ques_choose_file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int res = fileDialog.showOpenDialog(null);
                if (res == JFileChooser.APPROVE_OPTION) {
                	String[] options = {"Queue", "Stack"};
                	 isStack = JOptionPane.showOptionDialog(null, "Choose play type",
                             "Select play type",
                             JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
             		File[] files = fileDialog.getSelectedFiles();
                	for (File file : files) { 
                		if (isStack == 0) { // Means choose queue
                			queue.add(file);
                		} else if (isStack == 1) { // Means choose stack
                			stack.add(file);
                		}	
                	}}
            }

        });

        questionFilePane.add(new JLabel("File: "));
        questionFilePane.add(ques_choose_file);
        questionFilePane.add(ques_file_choosed);

        // Confirm button
        JButton confirmBtn = new JButton("Let's go");
        confirmBtn.setPreferredSize(new Dimension(160, 30));
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // read file
                    if (isStack == 0) { // Means choose Queue	
                    	handleQueue();
                    } else if (isStack == 1) { // Means choose Stack
                    	handleStack();
                    }
                    makeNewGame();
                } catch (Exception ecep) {
                    JOptionPane.showMessageDialog(container, "Cannot Open file", "Error", JOptionPane.CANCEL_OPTION);
                }
            }
        });

        FilePane.add(questionFilePane);
        
        FilePane.add(confirmBtn);
    }
    
    private void handleQueue() throws IOException {
    	questionFile = queue.remove();
        readFile();
    }
    private void handleStack() throws IOException {
    	questionFile = stack.pop();
        readFile();
    }
    private void makeNewGame() {
        // config new game
        question_num = 0;
        point = 0;
        question.setText("Câu Hỏi : "+ques.get(question_num));
        score.setText("Total: " + point);
        answer.setEnabled(true);
    }

    private void renderQuestionPane() {
        questionPane = new JPanel();
        questionPane.setLayout(null);
        questionPane.setBackground(new Color(65, 105, 225));
        question = new JLabel("");
        question.setForeground(new Color(255, 255, 255));
        question.setHorizontalAlignment(SwingConstants.CENTER);
        question.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        question.setBounds(10, 54, 426, 49);

        questionPane.add(question);
    }

    private void renderAnswerPane() {
        answerPane = new JPanel();
        
        answer = new JTextField(20);
        answer.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        answer.setBounds(89, 23, 257, 40);
        answer.addActionListener(this); // add event listener
        checker = new JLabel("");
        checker.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        checker.setBounds(353, 14, 0, 0);
        score = new JLabel("Total: " + this.point);
        score.setForeground(new Color(255, 0, 0));
        score.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        score.setBounds(356, 22, 202, 40);

        answer.setEnabled(false);
        answerPane.setLayout(null);

        JLabel lblAnswer = new JLabel("Answer :");
        lblAnswer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblAnswer.setBounds(10, 22, 86, 40);
        answerPane.add(lblAnswer);
        answerPane.add(answer);
        answerPane.add(score);
        answerPane.add(checker);
    }
    
    private void renderNextChallengePane() {
    	nextChallengePane = new JPanel();
    	JButton next = new JButton("NEXT");
    	next.setFont(new Font("Times New Roman", Font.PLAIN, 15));
    	next.setBounds(123, 10, 188, 42);
    	next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // read file
                    if (isStack == 0) { // Means choose Queue	
                    	handleQueue();
                    } else if (isStack == 1) { // Means choose Stack
                    	handleStack();
                    }
                    makeNewGame();
                } catch (Exception ecep) {
                    JOptionPane.showMessageDialog(container, "Cannot Open file", "Error", JOptionPane.CANCEL_OPTION);
                }
            }
        });
    	nextChallengePane.setLayout(null);
    	
    	nextChallengePane.add(next);
    }

    public static void main(String[] args) throws IOException {
        Game g = new Game();
    }

    // Catch event
 
	
	@Override
	public void actionPerformed(ActionEvent e) {
        String res = answer.getText();
        if (res.equalsIgnoreCase(ans.get(question_num))) {
        	try
        	{
				WinMusic win = new WinMusic();
				win.start();
			}
        	catch (LineUnavailableException e1) {}
        	catch (IOException e1) {} 
        	catch (UnsupportedAudioFileException e1) {} 
            question_num++;
            point++;
            checker.setText("");
            answer.setText("");
            score.setText("Score: " + point);
            try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				java.sql.Connection connn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eotih","root","letronghieu");
				Statement stmt2 = connn.createStatement();
				int rsaa = stmt2.executeUpdate("update taikhoan set point ='"+point+"' where tendn ='"+eotihLogin.tendn+"'");
				if(rsaa == 1) {
					JOptionPane.showMessageDialog(null, "Success!");
				}else {
					JOptionPane.showMessageDialog(null, "Faill!");
				}
				connn.close();
				}catch(Exception e1) {
					System.out.println(e1);
				}
            // try and catch error when out of quesion ==> end game
            try {
                question.setText(ques.get(question_num));
            } 
            catch (IndexOutOfBoundsException exception) {
                question.setText("You win!!!");              
                answer.setEnabled(false); // disable answer when game end               
            }
        } else {
        	try
        	{
				LoseMusic lose = new LoseMusic();
				lose.start();
			}
        	catch (LineUnavailableException e1) {}
        	catch (IOException e1) {} 
        	catch (UnsupportedAudioFileException e1) {} 
            checker.setText("Wrong aswer!!!");
            
        }
    }

}