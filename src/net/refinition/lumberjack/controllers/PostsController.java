package net.refinition.lumberjack.controllers;

import net.refinition.lumberjack.models.BloggerApiClient;
import net.refinition.lumberjack.views.PostEditor;
import net.refinition.lumberjack.views.PostManager;
import net.refinition.lumberjack.views.AboutDialog;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import com.google.gdata.util.ServiceException;
import com.google.gdata.data.HtmlTextConstruct;

public class PostsController implements ActionListener
{
  private BloggerApiClient bloggerApiClient;
  private PostEditor postEditor;
  private PostManager postManager;
  private AboutDialog aboutDialog;

  public PostsController(BloggerApiClient bac) {
      bloggerApiClient = bac;

      String [] blog_names_array = bloggerApiClient.getBlogNames();
      if (blog_names_array.length > 0)
          bloggerApiClient.selectBlog(0);

      postEditor = new PostEditor(this, blog_names_array); 
  }

  public void showWindow() {
      postEditor.show();
  }

  public void actionPerformed(ActionEvent e)
  {
    if ("comboBoxChanged".equals(e.getActionCommand()))
    {
        JComboBox cb = (JComboBox)e.getSource();
        int selectedIndex = cb.getSelectedIndex();
        System.out.println("Selected index: " + selectedIndex);
        bloggerApiClient.selectBlog(selectedIndex);
    }
    else if ( "manage_posts".equals(e.getActionCommand()) )
    {
        postManager = new PostManager(this, bloggerApiClient.getPostNames() ); 
        postManager.show();
    }
    else if ( "about_lumberjack".equals(e.getActionCommand()) )
    {
        aboutDialog = new AboutDialog();
        aboutDialog.show(postEditor);
    }
    else if ( "submit_blog_post".equals(e.getActionCommand()) )
    {
        String post_title = postEditor.getTitleText();
        String post_content = postEditor.getContentText();
        Boolean isDraft = postEditor.isDraft();
        System.out.println("submit event!" + e.paramString());
        System.out.print(post_content);

        try {
            if (bloggerApiClient.getSelectedPost() == null)
                bloggerApiClient.createPost(post_title, post_content, isDraft);
            else
                bloggerApiClient.updatePost(post_title, post_content, isDraft);

            //System.out.println("Post saved! Clearing fields.");
            //postEditor.setTitleText("");
            //postEditor.setContentText("");

            postEditor.dispose();
            postEditor = null;

            bloggerApiClient.deselectPost();
            postEditor = new PostEditor(this, bloggerApiClient.getBlogNames() ); 
            showWindow();
        }
        catch (ServiceException ex)
        {
            ex.printStackTrace();
            return;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return;
        }

    }
    else if ( "load_post".equals(e.getActionCommand()) )
    {
        JList postList = postManager.getPostList();
        int selectedIndex = postList.getSelectedIndex();
        System.out.println("Selected index: " + selectedIndex);

        bloggerApiClient.selectPost(selectedIndex);

        postEditor.getFrame().dispose();


        HtmlTextConstruct postContentHtml = (HtmlTextConstruct) bloggerApiClient.getSelectedPost().getTextContent().getContent();
        String postContentText = postContentHtml.getHtml().replaceAll("<br />", "\n");

        postEditor = new PostEditor(this, 
                                    bloggerApiClient.getSelectedBlog().getTitle().getPlainText(), 
                                    bloggerApiClient.getSelectedPost().getTitle().getPlainText() ,
                                    postContentText,
                                    bloggerApiClient.getSelectedPost().isDraft() );

        postManager.dispose();
        postEditor.show();
    }
    else
        System.out.println(e.getActionCommand());
  }

}
