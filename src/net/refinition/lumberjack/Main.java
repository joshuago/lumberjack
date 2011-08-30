package net.refinition.lumberjack;

import net.refinition.lumberjack.controllers.LoginController;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;

import com.google.gdata.client.*;
import com.google.gdata.data.*;
import com.google.gdata.data.blogger.*;
import com.google.gdata.util.*;

import java.io.IOException;
import java.net.URL;

import javax.swing.plaf.metal.*;


public class Main
{
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        LoginController lc = new LoginController();
        lc.showWindow();

        //LumberjackPostManager ljpm = new LumberjackPostManager();
        //ljpm.show();

        //LumberjackListener lumberjackListener = new LumberjackListener();
        //lumberjackListener.connectToBlogger();
        //lumberjackListener.createGUI();

        //LumberjackAboutDialog ljad = new LumberjackAboutDialog();
        //ljad.show();
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }

        });

    }
}
