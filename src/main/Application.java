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

import car.CarListActionListener;
import car.CarStore;
import car.NewCarActionListener;
import client.ClientListActionListener;
import client.ClientStore;
import client.NewClientActionListener;
import rent.NewRentActionListener;
import rent.RentListActionListener;


public class Application extends JFrame {
	
	JPanel mainPanel;
	
    private void initComponents() {
        this.setLayout(new BorderLayout());
        mainPanel = new JPanel();  
        this.add(mainPanel, BorderLayout.CENTER);
        
        // client menu
        JMenuItem newClientMenuItem = new JMenuItem("Add client");
        JMenuItem lientListMenuItem = new JMenuItem("Clients list");
        lientListMenuItem.addActionListener(new ClientListActionListener(mainPanel));
        newClientMenuItem.addActionListener(new NewClientActionListener(mainPanel));
        JMenu clientsMenu = new JMenu("Clients");
        clientsMenu.add(newClientMenuItem);
        clientsMenu.add(lientListMenuItem);
        
        // car menu
        JMenuItem newCarMenuItem = new JMenuItem("Add car");
        JMenuItem carListMenuItem = new JMenuItem("Cars lists");
        carListMenuItem.addActionListener(new CarListActionListener(mainPanel));
        newCarMenuItem.addActionListener(new NewCarActionListener(mainPanel));
        JMenu carsMenu = new JMenu("Cars");
        carsMenu.add(newCarMenuItem);
        carsMenu.add(carListMenuItem);
        
        // rent menu
        JMenuItem newRentMenuItem = new JMenuItem("New rent");
        JMenuItem rentListMenuItem = new JMenuItem("Rent lists");
        rentListMenuItem.addActionListener(new RentListActionListener(mainPanel));
        newRentMenuItem.addActionListener(new NewRentActionListener(mainPanel));
        JMenu rentsMenu = new JMenu("Rent");
        rentsMenu.add(newRentMenuItem);
        rentsMenu.add(rentListMenuItem);
        
        // create menu
        JMenuBar bar = new JMenuBar();
        bar.add(clientsMenu);
        bar.add(carsMenu);
        bar.add(rentsMenu);
        this.setJMenuBar(bar);
    }
    
    
    @SuppressWarnings("unchecked")
    public Application() {
        super("Basic of  programming 3 - Homework - Lupák Gábor - YBMX5Q");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 500));
        initComponents();
    };
        
        
	public static void main(String[] args) {
		System.out.println("Hello Homework!");
		Application sf = new Application();
        sf.setVisible(true);
	}
}


