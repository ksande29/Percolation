package percolation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;

public class Gui extends JFrame 
{

	private JPanel contentPane;
	
	private int width = 20;
	private int height = 20;
	private int totalN = width * height;
	private ArrayList<JButton> buttons;

	private Percolation percolation;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Gui frame = new Gui();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() 
	{
		makePercolation();
		
		setUpMainDisplay();	
		JPanel panelCenter = makeCenterPanel();	
		makeLeftPanel();	
		makeRightpanel();	
		JPanel panelTop = makeTopPanel();
		JLabel topMessage = makeTopMessage(panelTop);	
		JPanel panelBottom = makeBottomPanel();				
		makeRunButton(panelCenter, panelBottom, topMessage);	
		makeResetButton(panelCenter, panelBottom, topMessage);
	}

	private void makePercolation()
	{
		percolation = new Percolation(width, height);
	}
	
	private void setUpMainDisplay() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}	

	private JPanel makeCenterPanel() 
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(height, 0, 0, 0));		
		drawButtons(panel);	
		return panel;
	}
	
	private void makeLeftPanel() 
	{
		JPanel panelLeft = new JPanel();
		panelLeft.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelLeft.setBackground(Color.BLACK);
		contentPane.add(panelLeft, BorderLayout.WEST);
	}
	
	private void makeRightpanel() 
	{
		JPanel panelRight = new JPanel();
		panelRight.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelRight.setBackground(Color.BLACK);
		contentPane.add(panelRight, BorderLayout.EAST);
	}

	private JPanel makeTopPanel() 
	{
		JPanel panelTop = new JPanel();
		panelTop.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelTop.setBackground(Color.BLACK);
		contentPane.add(panelTop, BorderLayout.NORTH);	
		return panelTop;
	}
	
	private JLabel makeTopMessage(JPanel panelTop)
	{
		JLabel topMessage = new JLabel("Will it Percolate?");
		topMessage.setBorder(new EmptyBorder(10, 10, 10, 10));
		topMessage.setFont(new Font("Verdana", Font.BOLD, 30));
		topMessage.setForeground(Color.WHITE);
		panelTop.add(topMessage);
		return topMessage;
	}
	
	private JPanel makeBottomPanel() 
	{
		JPanel panelBottom = new JPanel();
		panelBottom.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelBottom.setBackground(Color.BLACK);
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		return panelBottom;
	}
	
	private void makeRunButton(JPanel panel, JPanel panelBottom, JLabel topMessage) 
	{
		JButton runBtn = new JButton("Test Percolation");
		runBtn.setFocusable(false);
		runBtn.setBorder(new EmptyBorder(10, 10, 10, 10));
		runBtn.setFont(new Font("Verdana", Font.BOLD, 20));
		runBtn.setBackground(Color.WHITE);
		panelBottom.add(runBtn);
		runBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//redraw board
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				drawWater(panel);
				
				//test percolation
				if (percolation.percolates())
					topMessage.setText("It Percolates");
				else
					topMessage.setText("It does not Percolate");
			}
		});
	}
	
	private void makeResetButton(JPanel panel, JPanel panelBottom, JLabel messageTop) 
	{
		JButton runBtn = new JButton("New Board");
		runBtn.setFocusable(false);
		runBtn.setBorder(new EmptyBorder(10, 10, 10, 10));
		runBtn.setFont(new Font("Verdana", Font.BOLD, 20));
		runBtn.setBackground(Color.WHITE);
		panelBottom.add(runBtn);
		runBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				
				makePercolation();
				drawButtons(panel);
				
				messageTop.setText("Will it Percolate?");
			}
		});
	}

	private void drawButtons(JPanel panel) 
	{
		buttons = new ArrayList<JButton>();
		for (int i = 0; i < totalN; i++)
		{
			JButton button = new JButton("");
			button.setEnabled(false);
			if (percolation.getIsOpen()[i] == true)
				button.setBackground(Color.WHITE);
			else
				button.setBackground(Color.BLACK);
			panel.add(button);
			buttons.add(button);
		}
	}
	
	private void drawWater(JPanel panel) 
	{
		for (int i = 0; i < totalN; i++)
		{
			JButton button = new JButton("");
			button.setEnabled(false);
			if (percolation.getIsOpen()[i] == true)
				if ( percolation.isConnected(percolation.getId()[i], percolation.getTop()) )
					button.setBackground(Color.BLUE);
				else
					button.setBackground(Color.WHITE);
			else
				button.setBackground(Color.BLACK);
			panel.add(button);			
		}
	}
	
}
