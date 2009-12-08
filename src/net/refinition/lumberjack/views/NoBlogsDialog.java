package net.refinition.lumberjack.views;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;

public class NoBlogsDialog
{
  public void show() 
  {
      JOptionPane.showMessageDialog(null, "Your login was successful, but we did not find\nany blogs associated with your account.\n\nLumberjack does not support creating new blogs\nyet.\n\nPlease go to http://www.blogger.com to sign\nup for a new blog.", "Lumberjack - Error: No Blogs Detected", JOptionPane.INFORMATION_MESSAGE);

  }
}
