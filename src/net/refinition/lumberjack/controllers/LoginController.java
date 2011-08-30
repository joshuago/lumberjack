package net.refinition.lumberjack.controllers;

import net.refinition.lumberjack.views.NoBlogsDialog;
import net.refinition.lumberjack.views.LoginWindow;
import net.refinition.lumberjack.models.BloggerApiClient;
import net.refinition.lumberjack.controllers.PostsController;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.google.gdata.client.*;
import com.google.gdata.data.*;
import com.google.gdata.data.blogger.*;
import com.google.gdata.util.*;
import java.io.IOException;
import java.net.URL;
import javax.swing.SwingUtilities;

public class LoginController implements ActionListener
{
  private LoginWindow loginWindow; 

  public void showWindow()
  {
      loginWindow = new LoginWindow();
      loginWindow.show(this);
  }

  public void actionPerformed(ActionEvent e) {
    if ( "log_in_button_clicked".equals(e.getActionCommand() )) {
      System.out.println("Log In button clicked!");
      String username = loginWindow.getUsername();
      String password = loginWindow.getPassword();
      loginWindow.dispose();

      BloggerApiClient  bac = new BloggerApiClient();
      GoogleService bs = bac.connectToBlogger(username, password);
      if (bs != null)
      {
        bac.populateBlogs();

        String [] blog_names_array = bac.getBlogNames();
        if (blog_names_array.length > 0)
        {
          PostsController pc = new PostsController(bac);
          pc.showWindow();
        }
        else
        {
          NoBlogsDialog nbd = new NoBlogsDialog();
          nbd.show();
        }
      }
      else
      {
        loginWindow = new LoginWindow();
        loginWindow.show(this);
        loginWindow.setUsername(username);
        loginWindow.displayFailedAuthMessage();
      }
    }
    else {
      System.out.println("Unrecognized event " + e.getActionCommand() );
    }
  }
}
