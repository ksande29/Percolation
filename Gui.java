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
import java.util.Random;
import java.awt.Font;

public class Gui extends JFrame 
{

	private JPanel contentPane;
	
	private int width = 20;
	private int height = 20;
	private int totalN = width * height;
	private ArrayList<JButton> buttons;

	private Percolation percolation = new Percolation(width, height);


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
		setUpMainDisplay();	
		JPanel panelCenter = makeCenterPanel();	
		makeLeftPanel();	
		makeRightpanel();	
		makeTopPanel();
		JPanel panelBottom = makeBottomPanel();				
		makeRunButton(panelCenter, panelBottom);	
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

	private void makeTopPanel() 
	{
		JPanel panelTop = new JPanel();
		panelTop.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelTop.setBackground(Color.BLACK);
		contentPane.add(panelTop, BorderLayout.NORTH);
	}
	
	private JPanel makeBottomPanel() 
	{
		JPanel panelBottom = new JPanel();
		panelBottom.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelBottom.setBackground(Color.BLACK);
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		return panelBottom;
	}
	
	private void makeRunButton(JPanel panel, JPanel panelBottom) 
	{
		JButton runBtn = new JButton("Run");
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
				drawWater(panel);
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