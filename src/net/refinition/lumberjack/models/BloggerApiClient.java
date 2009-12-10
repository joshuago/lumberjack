package net.refinition.lumberjack.models;

import com.google.gdata.client.*;
import com.google.gdata.data.*;
import com.google.gdata.data.blogger.*;
import com.google.gdata.util.*;

import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class BloggerApiClient
{
  private URL feedUrl;
  private BlogEntry[] blogArray; 
  private PostEntry[] postArray;

  private GoogleService bloggerService;

  private BlogEntry selectedBlog;
  private PostEntry selectedPost;

  public BloggerApiClient()
  {
    try {
      feedUrl = new URL("http://www.blogger.com/feeds/default/blogs");
    } 
    catch (MalformedURLException ex) {
      ex.printStackTrace();
    }
  }

  public GoogleService connectToBlogger(String username, String password) {
    GoogleService bs = new GoogleService("blogger", "Refinition-Lumberjack-1");

    try {
      System.out.println("Connecting to Blogger...");
      bs.setUserCredentials(username, password);
      System.out.println("    done.");
      this.bloggerService = bs;
      return bs;
    }
    catch (GoogleService.InvalidCredentialsException icex)
    {
      System.out.println("AUTHENTICATION FAILED");
      icex.printStackTrace();
      return null;
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }

    return null;
  }

  public void populateBlogs()
  {
      try {
        BlogFeed resultFeed = bloggerService.getFeed(feedUrl, BlogFeed.class);
        System.out.println(Feed.class);
        blogArray = new BlogEntry[resultFeed.getEntries().size()];
        for (int i = 0; i < resultFeed.getEntries().size(); i++)
        {
            blogArray[i] = resultFeed.getEntries().get(i); 
        }
      }
      catch (IOException ex) {
        ex.printStackTrace();
      }
      catch (ServiceException ex) {
        ex.printStackTrace();
      }
  }

  public void populatePosts()
  {
      try {
          String blog_link_href = selectedBlog.getEntryPostLink().getHref();
          URL blogUrl = new URL(blog_link_href);
          BlogPostFeed resultFeed = bloggerService.getFeed(blogUrl, BlogPostFeed.class);
          ArrayList<PostEntry> postEntryList = new ArrayList<PostEntry>( resultFeed.getEntries() );
          postArray = new PostEntry[postEntryList.size()];

          for (int i = 0; i < postEntryList.size(); i++)
              postArray[i] = postEntryList.get(i);
      }
      catch (IOException ex) {
          ex.printStackTrace();
      }
      catch (ServiceException ex) {
          ex.printStackTrace();
      }
  }

  public BlogEntry [] getBlogs()
  {
      return blogArray;
  }

  public PostEntry [] getPosts()
  {
      return postArray;
  }

  public String [] getBlogNames()
  {
      String [] blogNamesArray = new String[blogArray.length];
      for(int i = 0; i < blogArray.length; i++)
        blogNamesArray[i] = blogArray[i].getTitle().getPlainText();

      return blogNamesArray;
  }

  public String [][] getPostNamesAndStatuses()
  {
      String [][] postNamesAndStatusesArray = new String[postArray.length][3];

      for (int i = 0; i < postArray.length; i++)
      {
        postNamesAndStatusesArray[i][0] = postArray[i].getTitle().getPlainText();

        if ( postArray[i].isDraft() )
          postNamesAndStatusesArray[i][1] = "draft";
        else
          postNamesAndStatusesArray[i][1] = "published";

        postNamesAndStatusesArray[i][2] = postArray[i].getUpdated().toUiString();
      }

      return postNamesAndStatusesArray;
  }

  public BlogEntry getSelectedBlog()
  {
      return selectedBlog;
  }

  public void selectBlog(int selectedIndex)
  {
      selectedBlog = blogArray[selectedIndex];
      System.out.println("Newly selected blog title: " + selectedBlog.getTitle().getPlainText() );
      String entry_post_link = selectedBlog.getEntryPostLink().getHref();
      System.out.println("Newly selected blog resource URL: " + entry_post_link);

      populatePosts();
  }

  public void selectPost(int selectedIndex)
  {
      selectedPost = postArray[selectedIndex];
      System.out.println("Newly selected post title: " + selectedPost.getTitle().getPlainText() );
      String entry_edit_link = selectedPost.getEditLink().getHref();
      System.out.println("New selected post resource URL: " + entry_edit_link);
  }

  public void deselectPost()
  {
      selectedPost = null;
  }

  public PostEntry getSelectedPost()
  {
      return selectedPost;
  }

  public PostEntry createPost(String title, String content, Boolean isDraft)
         throws ServiceException, IOException
  {
      PostEntry myEntry = new PostEntry();
      myEntry.setTitle(new PlainTextConstruct(title));
      myEntry.setContent(new PlainTextConstruct(content));
      myEntry.setDraft(isDraft);

      String blog_link_href = selectedBlog.getEntryPostLink().getHref();

      URL postUrl = new URL(blog_link_href);
      return bloggerService.insert(postUrl, myEntry);
  }

  public PostEntry updatePost(String title, String content, Boolean isDraft)
          throws ServiceException, IOException
  {
      selectedPost.setTitle(new PlainTextConstruct(title));
      selectedPost.setContent(new PlainTextConstruct(content));
      selectedPost.setDraft(isDraft);

      URL editUrl = new URL(selectedPost.getEditLink().getHref());
      return bloggerService.update(editUrl, selectedPost);
  }
}
