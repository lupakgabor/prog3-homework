package main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.*;

import client.clientListActionListener;
import client.newClientActionListener;


public class Application extends JFrame {
	
	JPanel mainPanel;
	
    private void initComponents() {
        this.setLayout(new BorderLayout());
        mainPanel = new JPanel();  
        this.add(mainPanel, BorderLayout.CENTER);
        JMenuItem mi1 = new JMenuItem("Add new client");
        JMenuItem mi2 = new JMenuItem("List clients");
        mi2.addActionListener(new clientListActionListener(mainPanel));
        mi1.addActionListener(new newClientActionListener(mainPanel));
        JMenu m1 = new JMenu("Clients");
        m1.add(mi1);
        m1.add(mi2);
        JMenuBar bar = new JMenuBar();
        bar.add(m1);
        this.setJMenuBar(bar);
    }
    
    
    @SuppressWarnings("unchecked")
    public Application() {
        super("Basic of  programming 3 - Homework - Lupák Gábor - YBMX5Q");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 300));
        initComponents();
    };
        
        
	public static void main(String[] args) {
		System.out.println("Hello Homework!");
		Application sf = new Application();
        sf.setVisible(true);
	}
}


