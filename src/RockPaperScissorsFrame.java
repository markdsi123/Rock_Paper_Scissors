import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;
import static java.awt.BorderLayout.NORTH;

public class RockPaperScissorsFrame extends JFrame
{
    JPanel mainPanel, titlePanel, buttonPanel, statsPanel, displayPanel;
    JButton quitButton, rockButton, paperButton, scissorsButton;
    JLabel titleLbl, computerWinLabel, playerWinLabel, tieLabel;
    JTextArea result;
    JTextField computerWinTxt, playerWinTxt, tieTxt;
    JScrollPane scroller;
    ImageIcon icon, rock, paper, scissors;

    int playerWin = 0;
    int computerWin = 0;
    int tie = 0;
    int max = 3;
    int min = 1;

    Random rnd = new Random();

    public RockPaperScissorsFrame()
    {
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.RED);
        add(mainPanel);

        createTitlePanel();
        createDisplayPanel();
        createButtonPanel();
        createStatsPanel();
        createCommandPanel();

        setVisible(true);
    }

    public void createTitlePanel()
    {
        titlePanel = new JPanel();
        icon = new ImageIcon("src\\rockpaperscissors.jpg", "ROCK PAPER and SCISSORS");
        this.setIconImage(icon.getImage());
        titleLbl = new JLabel("Rock Paper Scissors Game", JLabel.CENTER);
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titleLbl.setFont(new Font("Time New Roman", Font.BOLD, 30));
        mainPanel.add(titleLbl, NORTH);
    }
    public void createStatsPanel()
    {
        statsPanel = new JPanel();

        playerWinLabel = new JLabel("Player Wins: ");
        playerWinTxt = new JTextField(" " + playerWin + " ", 3);
        playerWinLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        playerWinTxt.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        computerWinLabel = new JLabel("Computer Wins: ");
        computerWinTxt = new JTextField(" " + computerWin + " ", 3);
        computerWinLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        computerWinTxt.setFont(new Font("Times New Roman", Font.PLAIN, 20));


        tieLabel = new JLabel("Ties: ");
        tieTxt = new JTextField(" " + tie + " ", 3);
        tieLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tieTxt.setFont(new Font("Times new Roman", Font.PLAIN, 20));

        statsPanel.add(computerWinLabel);
        statsPanel.add(computerWinTxt);
        statsPanel.add(playerWinLabel);
        statsPanel.add(playerWinTxt);
        statsPanel.add(tieLabel);
        statsPanel.add(tieTxt);

        mainPanel.add(statsPanel, BorderLayout.NORTH);
    }

    private void createDisplayPanel()
    {
        displayPanel = new JPanel();
        result = new JTextArea(5, 20);
        scroller = new JScrollPane(result);
        result.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        displayPanel.add(scroller);
        mainPanel.add(displayPanel, BorderLayout.EAST);
    }

    private void createButtonPanel()
    {
        paper = new ImageIcon(new ImageIcon("src/paper.jpg").getImage().getScaledInstance(160, 160, Image.SCALE_DEFAULT));
        rock = new ImageIcon(new ImageIcon("src/rock.jpg").getImage().getScaledInstance(160, 160, Image.SCALE_DEFAULT));
        scissors = new ImageIcon(new ImageIcon("src/scissors.jpg").getImage().getScaledInstance(160, 160, Image.SCALE_DEFAULT));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        paperButton = new JButton(paper);
        rockButton = new JButton(rock);
        scissorsButton = new JButton(scissors);

        paperButton.addActionListener
                (
                        (ActionEvent ae) ->
                        {
                            result.append(run(1) + "\n");
                        }
                );

        rockButton.addActionListener
                (
                        (ActionEvent ae) ->
                        {
                            result.append(run(0) + "\n");
                        }
                );

        scissorsButton.addActionListener
                (
                        (ActionEvent ae) ->
                        {
                            result.append(run(2) + "\n");
                        }
                );

        buttonPanel.add(paperButton);
        buttonPanel.add(rockButton);
        buttonPanel.add(scissorsButton);

        mainPanel.add(BorderLayout.CENTER, buttonPanel);
    }

    private String run(int playerChoice)
    {
        Random rnd = new Random();
        int computerChoice = rnd.nextInt(3);
        String gameResult = "";

        if (computerChoice == playerChoice)
        {
            gameResult = "Tie Game!";
            tie++;
            tieTxt.setText(tie + "");
        }
        else
        {
            //So, the number 0,1,2 menas rock = 0, paper = 1, scissors = 2
            switch(playerChoice)
            {
                case 0 ->
                {
                    if (computerChoice == 1)
                    {
                        gameResult = "Paper beats Rock!  You lose!";
                        computerWin++;
                        computerWinTxt.setText(computerWin + "");
                    }
                    else
                    {
                        gameResult = "Rock beats Scissors!  You win!";
                        playerWin++;
                        playerWinTxt.setText(playerWin + "");
                    }
                }
                case 1 ->
                {
                    if (computerChoice == 2)
                    {
                        gameResult = "Scissors beats paper!  You lose!";
                        computerWin++;
                        computerWinTxt.setText(computerWin + "");
                    }
                    else
                    {
                        gameResult = "Paper beats rock!  You Win!";
                        playerWin++;
                        playerWinTxt.setText(playerWin + "");
                    }
                }
                case 2 ->
                {
                    if (computerChoice == 1)
                    {
                        gameResult = "Scissors beats paper!  You win!";
                        playerWin++;
                        playerWinTxt.setText(playerWin + "");
                    }
                    else
                    {
                        gameResult = "Rock beats scissors!  You lose!";
                        computerWin++;
                        computerWinTxt.setText(computerWin + "");
                    }
                }
            }
        }
        return gameResult;
    }
    public void createCommandPanel()
    {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        quitButton = new JButton("Quit");
        quitButton.setBackground(Color.RED);

        buttonPanel.add(quitButton);
        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
}