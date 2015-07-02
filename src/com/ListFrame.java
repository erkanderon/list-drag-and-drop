package com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import javax.swing.border.EmptyBorder;

public class ListFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListFrame frame = new ListFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(70, 70, 275, 200);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		String[] data = {"list_item1", "list_item2", "list_item3", "list_item4", "list_item5", "list_item6", "list_item7", "list_item8"};
		String[] data2 = {"list_item9", "list_item10", "list_item11", "list_item12", "list_item13", "list_item14", "list_item15", "list_item16"};
		
		//Button Implementation
		
		ImageIcon img1 = new ImageIcon("images/indir.png");
		ImageIcon img2 = new ImageIcon("images/indir2.png");
		ImageIcon img3 = new ImageIcon("images/details_delete.png");
		JButton right = new JButton(img1);
		JButton left = new JButton(img2);
		JButton remove = new JButton(img3);
		right.setBackground(Color.WHITE);
		left.setBackground(Color.WHITE);
		remove.setBackground(Color.WHITE);
		
		JPanel buttons = new JPanel(new GridLayout(3,1));
		buttons.add(left);
		buttons.add(right);
		buttons.add(remove);
		buttons.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
		
		
		
		
		
		DefaultListModel model = new DefaultListModel();
		DefaultListModel model2 = new DefaultListModel();
		
		//Adding datas to list object
		
		for(int i=0;i<data.length;i++){
			model.addElement(data[i]);
		}
		for(int i=0;i<data2.length;i++){
			model2.addElement(data2[i]);
		}
		
		//List Implementation
		
		JList list = new JList(model);
		JList list2 = new JList(model2);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		list.setDragEnabled(true);
		list2.setDragEnabled(true);
		
		
		add(list, BorderLayout.WEST);
		add(list2, BorderLayout.EAST);
		add(buttons,BorderLayout.CENTER);
		
		
		
		
		list.setTransferHandler(new TransferHandler(){
			@Override
	        public boolean canImport(TransferHandler.TransferSupport support) {
	            // data of type string?
	            return support.isDataFlavorSupported(DataFlavor.stringFlavor);
	        }

	        @Override
	        public boolean importData(TransferHandler.TransferSupport support) {
	            try {
	                // convert data to string
	                String s = (String)support.getTransferable().getTransferData(DataFlavor.stringFlavor);
	                JList.DropLocation dl = (JList.DropLocation)support.getDropLocation();
	                model.add(dl.getIndex(),s);
	                model2.remove(list2.getSelectedIndex());
	                
	                return true;
	            } 
	            catch (UnsupportedFlavorException e) {} 
	            catch (IOException e) {}

	            return false;
	        }
	    
		}); 
		left.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
				model2.addElement(list.getSelectedValue());
				model.removeElementAt(list.getSelectedIndex());
				}catch(ArrayIndexOutOfBoundsException k){
					System.out.println("Not a good choice");
				}
				
				
				
			}
		});
right.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
				model.addElement(list2.getSelectedValue());
				model2.removeElementAt(list2.getSelectedIndex());
				}catch(ArrayIndexOutOfBoundsException k){
					System.out.println("Not a good choice");
				}
				
				
				
			}
		});
remove.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try{
		model.removeElementAt(list.getSelectedIndex());
		
		}catch(ArrayIndexOutOfBoundsException k){
			
		}
		try{
			model2.removeElementAt(list2.getSelectedIndex());
			
			}catch(ArrayIndexOutOfBoundsException k){
				
			}
		
		
		
	}
});
		
		
	}
	
	

}
