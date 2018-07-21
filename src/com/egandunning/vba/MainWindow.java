package com.egandunning.vba;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MainWindow {

	private JFrame frame;
	private JTextArea inputTextArea;
	private JTextArea outputTextArea;
	
	public MainWindow() {
		
		init();
	}
	
	private void init() {
		frame = new JFrame();
		
		frame.setTitle("Convert text to a VBA-friendly string");
		frame.setSize(800, 600);
		frame.setResizable(true);
		
		inputTextArea = new JTextArea(30, 50);
		outputTextArea = new JTextArea(30, 50);
		
		inputTextArea.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
//				int beginIndex = e.getOffset();
//				int getEndIndex = beginIndex + e.getLength();
				
				updateText();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateText();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateText();
			}
			
		});
				
		JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
		inputScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
		outputScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		int fontSize = inputTextArea.getFont().getSize();
		
		inputTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontSize));
		outputTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontSize));
				
		GridLayout layout = new GridLayout(1, 2);

		JPanel inputPane = new JPanel();
		inputPane.setLayout(new BoxLayout(inputPane, BoxLayout.Y_AXIS));
		inputPane.add(new JLabel("Add text here"));
		inputPane.add(inputScrollPane);
		
		JPanel outputPane = new JPanel();
		outputPane.setLayout(new BoxLayout(outputPane, BoxLayout.Y_AXIS));
		outputPane.add(new JLabel(" "));
		outputPane.add(outputScrollPane);
		
		frame.getContentPane().setLayout(layout);
		frame.getContentPane().add(inputPane);
		frame.getContentPane().add(outputPane);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		frame.setVisible(true);
	}
	
	private void updateText() {
		outputTextArea.setText(ConvertText.toVbaString(inputTextArea.getText()));
	}
	
}
