package net.refinition.lumberjack.views;

import net.refinition.lumberjack.controllers.LoginController;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.plaf.metal.*;

public class LoginWindow
{
  private JFrame frame;
  private JPanel loginPanel;
  private JLabel usernameLabel;
  private JTextField usernameField;
  private JLabel passwordLabel;
  private JPasswordField passwordField;
  private JLabel failedAuthMessage;
  private JButton loginButton;

  public void show(LoginController lj_lc)
  {
      frame = new JFrame("Lumberjack - Log In");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setPreferredSize(new Dimension(325, 200));

      loginPanel = new JPanel();

      usernameLabel = new JLabel("Username:");
      loginPanel.add(usernameLabel);
      usernameField = new JTextField(20);
      loginPanel.add(usernameField);

      passwordLabel = new JLabel("Password:");
      loginPanel.add(passwordLabel);
      passwordField = new JPasswordField(20);
      loginPanel.add(passwordField);

      failedAuthMessage = new JLabel("Login failed!");
      loginPanel.add(failedAuthMessage);
      failedAuthMessage.setVisible(false);

      loginButton = new JButton("Log In");
      loginButton.setActionCommand("log_in_button_clicked");
      loginButton.addActionListener(lj_lc);
      loginPanel.add(loginButton);

      frame.getContentPane().add(loginPanel);

      frame.pack();
      frame.setVisible(true);
  }

  public void displayFailedAuthMessage()
  {
      failedAuthMessage.setVisible(true);
  }
  
  public void dispose()
  {
      frame.dispose();
  }

  public String getUsername()
  {
    if (usernameField != null)
      return usernameField.getText();
    else
      return null;
  }

  public String getPassword()
  {
    if (passwordField != null)
      return new String( passwordField.getPassword() );
    else
      return null;
  }
}
