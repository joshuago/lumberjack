package net.refinition.lumberjack.views;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;

public class NoPermissionToEditDialog
{
  public void show(PostEditor postEditor) 
  {
      JFrame parent_frame = null;

      if (postEditor != null)
          parent_frame = postEditor.getFrame();

      JOptionPane.showMessageDialog(parent_frame, "You don't have permission to edit this post!", "Lumberjack - No Permission Error", JOptionPane.INFORMATION_MESSAGE);

  }
}
