import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class Help {	
	private JTextField txtHowToPlay;
	JPanel frame;
	MainMenu menu;
	public void setVisible(boolean shouldShow) {
		frame.setVisible(shouldShow);
	}

	/**
	 * Create the panel.
	 */
	public Help() {
		frame = new JPanel();
		frame.setBounds(new Rectangle(0, 0, 600, 600));
		frame.setLayout(null);
		
		txtHowToPlay = new JTextField();
		txtHowToPlay.setBounds(68, 104, 467, 37);
		frame.add(txtHowToPlay);
		txtHowToPlay.setBorder(null);
		txtHowToPlay.setEditable(false);
		txtHowToPlay.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtHowToPlay.setHorizontalAlignment(SwingConstants.CENTER);
		txtHowToPlay.setText("How to Play!");
		txtHowToPlay.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(68, 144, 467, 354);
		frame.add(scrollPane);
		
		JTextPane txtHelpArea = new JTextPane();
		txtHelpArea.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtHelpArea.setText("Chess Moves: \n"
								+"King can move exactly one square horizontally, vertically, or diagonally. At most once in every game, each king is allowed to make a special move, known as castling.\r\n" + 
								"Queen can move any number of vacant squares diagonally, horizontally, or vertically.\r\n" + 
								"Rook can move any number of vacant squares vertically or horizontally. It also is moved while castling.\r\n" + 
								"Bishop can move any number of vacant squares in any diagonal direction.\r\n" + 
								"Knight can move one square along any rank or file and then at an angle. The knight's movement can also be viewed as an 'L' or '7' laid out at any horizontal or vertical angle.\r\n" + 
								"Pawns can move forward one square, if that square is unoccupied. If it has not yet moved, the pawn has the option of moving two squares forward provided both squares in front of the pawn are unoccupied. A pawn cannot move backward. Pawns are the only pieces that capture differently from how they move. They can capture an enemy piece on either of the two spaces adjacent to the space in front of them (i.e., the two squares diagonally in front of them) but cannot move to these spaces if they are vacant. The pawn is also involved in the two special moves en passant and promotion.");
		scrollPane.setViewportView(txtHelpArea);
		
		JButton backButton = new JButton("");
		backButton.setBorder(null);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setBorder(new MatteBorder(5, 5, 5, 5, (Color) Color.BLACK));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setBorder(null);
			}
			@Override
			public void mousePressed(MouseEvent e){
				frame.setVisible(false);
				menu.setVisible(true);
			}
		});
		backButton.setIcon(new ImageIcon("assets/main/BackButton.png"));
		backButton.setBounds(213, 517, 184, 58);
		frame.add(backButton);
	
		
		JLabel MainMenuBackground = new JLabel("");
		MainMenuBackground.setBounds(0, 0, 600, 600);
		MainMenuBackground.setIcon(new ImageIcon("assets/main/HelpBackground.png"));
		frame.add(MainMenuBackground);
	}

	public void setMenu(MainMenu menu){
		this.menu = menu;
	}
}
	

