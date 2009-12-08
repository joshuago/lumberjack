package net.refinition.lumberjack.views;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;

public class AboutDialog
{
  public void show(PostEditor postEditor) 
  {
      JFrame parent_frame = null;

      if (postEditor != null)
          parent_frame = postEditor.getFrame();

      JOptionPane.showMessageDialog(parent_frame, "Lumberjack is a client for Google Blogger.\n\nAuthor: Joshua Go <joshuago@gmail.com>\n\nCopyright 2009 Refinition, Inc.", "Lumberjack - About", JOptionPane.INFORMATION_MESSAGE);

  }
}
