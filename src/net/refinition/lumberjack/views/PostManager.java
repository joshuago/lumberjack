package net.refinition.lumberjack.views;

import net.refinition.lumberjack.controllers.PostsController;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

public class PostManager {
  private JFrame frame;
  private JPanel postManagerPanel;
  private JPanel tableContainer;

  private JScrollPane postTableScrollPane;
  private JTable postTable;

  private JButton loadButton;

  private PostsController postsController;

  public PostManager(PostsController lj_pc, String [][] postNamesAndStatusesArray)
  {
      postsController = lj_pc;

      GridBagLayout gridbag = new GridBagLayout();
      GridBagConstraints c = new GridBagConstraints();

      frame = new JFrame("Lumberjack - Manage Posts");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      postManagerPanel = new JPanel();

      postManagerPanel.setLayout(gridbag);

      c.gridwidth = GridBagConstraints.REMAINDER;
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 0;

      tableContainer = new JPanel( new GridLayout(1, 1) );
      tableContainer.setBorder(BorderFactory.createTitledBorder("Manage Posts"));
      tableContainer.setMinimumSize( new Dimension(400, 250) );

      String [] columnNames = {"Title", "Status", "Last Updated"};
      postTable = new JTable(postNamesAndStatusesArray, columnNames);
      postTableScrollPane = new JScrollPane(postTable);
      tableContainer.add(postTableScrollPane);
      postManagerPanel.add(tableContainer, c);

      c.gridx = 0;
      c.gridy = 1;
      loadButton = new JButton("Load Post");
      loadButton.setActionCommand("load_post");
      loadButton.addActionListener(postsController);
      postManagerPanel.add(loadButton, c);

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

  public JTable getPostTable() {
      return postTable;
  }
}
