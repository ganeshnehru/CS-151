
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;


public class MainMenu {
	JPanel frame;
	Help help;
		
	public void setVisible(boolean shouldShow) {
		frame.setVisible(shouldShow);
	}
	
	
	/**
	 * Create the panel.
	 */
	public MainMenu(ChessBoard chessBoard, Help help) {
		frame = new JPanel();
		frame.setLayout(null);
		frame.setBounds(new Rectangle(0, 0, 600, 600));
		
		JButton playButton = new JButton("");
		playButton.setBorder(null);
		playButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				playButton.setBorder(new MatteBorder(7, 7, 7, 7, (Color) Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				playButton.setBorder(null);
			}
			@Override
			public void mousePressed(MouseEvent e){
				chessBoard.setVisible(true);
				frame.setVisible(false);
			}
		});
		playButton.setIcon(new ImageIcon("assets/main/PlayButton.png"));
		playButton.setBounds(188, 354, 223, 74);
		frame.add(playButton);
		
		JButton helpButton = new JButton("");
		helpButton.setBorder(null);
		helpButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				helpButton.setBorder(new MatteBorder(7, 7, 7, 7, (Color) Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				helpButton.setBorder(null);
			}
			@Override
			public void mousePressed(MouseEvent e){
				help.setVisible(true);
				frame.setVisible(false);
			}
		});
		helpButton.setIcon(new ImageIcon("assets/main/HelpButton.png"));
		helpButton.setBounds(188, 434, 223, 74);
		frame.add(helpButton);
		
		JButton exitButton = new JButton("");
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setBorder(new MatteBorder(7, 7, 7, 7, (Color) Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setBorder(null);
			}
			@Override
			public void mousePressed(MouseEvent e){
				System.exit(0);
			}
		});
		exitButton.setIcon(new ImageIcon("assets/main/ExitButton.png"));
		exitButton.setBounds(188, 514, 223, 74);
		frame.add(exitButton);
		
		JLabel MainMenuBackground = new JLabel("");
		MainMenuBackground.setIcon(new ImageIcon("assets/main/MainMenuBackground.png"));
		MainMenuBackground.setBounds(0, 0, 600, 600);
		frame.add(MainMenuBackground);
	}
}
