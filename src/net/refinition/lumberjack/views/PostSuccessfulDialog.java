package net.refinition.lumberjack.views;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;

public class PostSuccessfulDialog
{
  public void show(PostEditor postEditor) 
  {
      JFrame parent_frame = null;

      if (postEditor != null)
          parent_frame = postEditor.getFrame();

      JOptionPane.showMessageDialog(parent_frame, "Your post was successful!", "Lumberjack", JOptionPane.INFORMATION_MESSAGE);

  }
}
