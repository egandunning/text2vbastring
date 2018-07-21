package com.egandunning.vba;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MainWindow {

	private JFrame frame;
	private JTextArea inputTextArea;
	private JTextArea outputTextArea;
	private JCheckBox liveUpdate;
	private JTextField varName;
	
	public MainWindow() {
		init();
	}
	
	private void init() {
		frame = new JFrame();
		
		frame.setTitle("Convert text to a VBA-friendly string");
		frame.setSize(800, 600);
		frame.setResizable(true);
		
		inputTextArea = new JTextArea(1, 1);
		outputTextArea = new JTextArea(1, 1);
		
		int fontSize = inputTextArea.getFont().getSize();
		
		inputTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontSize));
		outputTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontSize));
		
		inputTextArea.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {				
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
		
		liveUpdate = new JCheckBox("Live update?");
		liveUpdate.setSelected(true);
		
		varName = new JTextField("myVar");
		varName.setMinimumSize(new Dimension(100, 20));
		varName.setMaximumSize(new Dimension(1000, 20));
		
		JLabel inputLabel = new JLabel("Add text here");
		inputLabel.setMaximumSize(new Dimension(100, 20));
		
		JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
		inputScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
		outputScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		GridLayout layout = new GridLayout(1, 2);

		JPanel inputPane = new JPanel();
		inputPane.setLayout(new BoxLayout(inputPane, BoxLayout.Y_AXIS));
		
		JPanel inputControls = new JPanel();
		inputControls.setLayout(new BoxLayout(inputControls, BoxLayout.X_AXIS));
		inputControls.add(Box.createRigidArea(new Dimension(0,24)));
		inputControls.add(inputLabel);
		
		
		inputPane.add(inputControls);
		inputPane.add(inputScrollPane);
		
		JPanel outputPane = new JPanel();
		outputPane.setLayout(new BoxLayout(outputPane, BoxLayout.Y_AXIS));
		
		JPanel outputControls = new JPanel();
		outputControls.setLayout(new BoxLayout(outputControls, BoxLayout.X_AXIS));
		outputControls.add(liveUpdate);
		outputControls.add(Box.createRigidArea(new Dimension(5,0)));
		outputControls.add(varName);
		
		outputPane.add(outputControls);
		outputPane.add(outputScrollPane);
		
		frame.getContentPane().setLayout(layout);
		frame.getContentPane().add(inputPane);
		frame.getContentPane().add(outputPane);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
	
	private void updateText() {
		if(liveUpdate.isSelected()) {
			outputTextArea.setText(ConvertText.toVbaString(inputTextArea.getText(), varName.getText()));
		}
	}
	
}
