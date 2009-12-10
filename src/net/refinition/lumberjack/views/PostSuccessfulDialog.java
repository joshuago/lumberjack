package net.refinition.lumberjack.views;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;

public class PostSuccessfulDialog
{
  public void show(PostEditor postEditor, Boolean isDraft) 
  {
      JFrame parent_frame = null;

      if (postEditor != null)
          parent_frame = postEditor.getFrame();

      if (isDraft == true)
        JOptionPane.showMessageDialog(parent_frame, "Your draft was successfully saved!", "Lumberjack", JOptionPane.INFORMATION_MESSAGE);
      else
        JOptionPane.showMessageDialog(parent_frame, "Your post was successfully published!", "Lumberjack", JOptionPane.INFORMATION_MESSAGE);

  }
}
