package net.refinition.lumberjack.views;

import net.refinition.lumberjack.controllers.PostsController;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;

public class PostManager {
  private JFrame frame;
  private JPanel postManagerPanel;

  private JScrollPane postListScrollPane;
  private JList postList;

  private JButton loadButton;

  private PostsController postsController;

  public PostManager(PostsController lj_pc, String [] postNamesArray)
  {
      postsController = lj_pc;
    
      frame = new JFrame("Lumberjack - Manage Posts");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setPreferredSize(new Dimension(647, 400));
      postManagerPanel = new JPanel();

      postList = new JList(postNamesArray);
      postListScrollPane = new JScrollPane(postList);
      postManagerPanel.add(postListScrollPane);

      loadButton = new JButton("Load Post");
      loadButton.setActionCommand("load_post");
      loadButton.addActionListener(postsController);
      postManagerPanel.add(loadButton);

      frame.getContentPane().add(postManagerPanel);
      frame.pack();
  }

  public void show()
  {
      frame.setVisible(true);
  }

  public void dispose()
  {
      frame.dispose();
  }

  public JList getPostList() {
      return postList;
  }
}
