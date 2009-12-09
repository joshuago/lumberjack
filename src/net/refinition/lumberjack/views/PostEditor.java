package net.refinition.lumberjack.views;

import net.refinition.lumberjack.controllers.PostsController;


import javax.swing.*;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class PostEditor {
  private JFrame frame;
  private JPanel postPanel;

  private JComboBox blogsComboBox;
  private JLabel currentBlogLabel;
  private JButton postManagerButton;
  private JButton aboutButton;

  private JTextField postTitleTextField;
  private JTextArea postTextArea;
  private JScrollPane postContentScrollPane;

  private JCheckBox isDraftCheckBox;

  private JButton submitButton;

  private PostsController postsController;

  private String [] blogNamesArray; // When we're creating a new post.
  private String blogName;          // When we're editing an existing post.

  public PostEditor(PostsController lj_pc, String [] blog_names_array)
  {
      postsController = lj_pc;
      blogNamesArray = blog_names_array;

      initializeVisuals();
  }

  public PostEditor(PostsController lj_pc, String blog_name, String title, String content, Boolean isDraft)
  {
      postsController = lj_pc;
      blogName = blog_name;

      initializeVisuals();

      setTitleText(title);
      setContentText(content);
      setDraft(isDraft);
  }

  private void initializeVisuals()
  {
      frame = new JFrame("Lumberjack - Post Editor");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setPreferredSize(new Dimension(647, 400));
      postPanel = new JPanel();

      GridBagLayout gridbag = new GridBagLayout();
      GridBagConstraints c = new GridBagConstraints();

      postPanel.setLayout(gridbag);

      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 0;
      if (blogNamesArray != null)
      {
          blogsComboBox = new JComboBox(blogNamesArray);
          blogsComboBox.addActionListener(postsController);
          gridbag.setConstraints(blogsComboBox, c);
          postPanel.add(blogsComboBox);
      }
      else if (blogName != null)
      {
          currentBlogLabel = new JLabel(blogName);
          gridbag.setConstraints(currentBlogLabel, c);
          postPanel.add(currentBlogLabel);
      }

      postManagerButton = new JButton("Manage Posts");
      postManagerButton.setActionCommand("manage_posts");
      postManagerButton.addActionListener(postsController);
      c.gridx = 1;
      c.gridy = 0;
      gridbag.setConstraints(postManagerButton, c);
      postPanel.add(postManagerButton);
     
      aboutButton = new JButton("About Lumberjack");
      aboutButton.setActionCommand("about_lumberjack");
      aboutButton.addActionListener(postsController);
      c.gridx = 2;
      c.gridy = 0;
      gridbag.setConstraints(aboutButton, c);
      postPanel.add(aboutButton);

      postTitleTextField = new JTextField(80);
      c.gridx = 0;
      c.gridy = 1; 
      c.gridwidth = 4;
      postTitleTextField.setFont(new Font("Courier", Font.PLAIN, 12));
      gridbag.setConstraints(postTitleTextField, c);
      postPanel.add(postTitleTextField);

      postTextArea = new JTextArea("Test post content", 15, 80);
      postTextArea.setLineWrap(true);
      postTextArea.setWrapStyleWord(true);
      postTextArea.setFont(new Font("Courier", Font.PLAIN, 12));
      postContentScrollPane = new JScrollPane(postTextArea);
      c.gridx = 0;
      c.gridy = 2; 
      gridbag.setConstraints(postContentScrollPane, c);
      postPanel.add(postContentScrollPane);
    
      c.gridx = 2; 
      c.gridy = 3; 
      isDraftCheckBox = new JCheckBox("save as draft");
      gridbag.setConstraints(isDraftCheckBox, c);
      postPanel.add(isDraftCheckBox);
    
      submitButton = new JButton("Submit");
      submitButton.setActionCommand("submit_blog_post");
      submitButton.addActionListener(postsController);
      c.gridx = 2; 
      c.gridy = 4; 
      c.gridwidth = 2;
      gridbag.setConstraints(submitButton, c);
      postPanel.add(submitButton);
    
      frame.getContentPane().add(postPanel);
      frame.pack();
  }

  // TODO: Put this stuff in a constructor instead.
  public void show()
  {
      frame.setVisible(true);
  }

  public void dispose()
  {
      frame.dispose();
  }

  public JFrame getFrame()
  {
      return frame;
  }

  public boolean isDraft() {
      if (isDraftCheckBox != null)
          return isDraftCheckBox.isSelected();
      else
          return false;
  }

  public void setDraft(Boolean is_draft)
  {
      if (isDraftCheckBox != null)
          isDraftCheckBox.setSelected(is_draft);
  }

  public String getTitleText()
  {
      return postTitleTextField.getText();
  }

  public void setTitleText(String s)
  {
      postTitleTextField.setText(s);
  }

  public String getContentText()
  {
      return postTextArea.getText();
  }

  public void setContentText(String s)
  {
      postTextArea.setText(s);
  }


}
