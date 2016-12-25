# Microsoft Excel Email/Records Book Insertion Module

A Java Swing GUI Module which handles the addition of an entry into a Microsoft Excel based Email Records book. In order to access and manipulate Microsoft OLE 2 Compound Documents and Open Office XML standard files this module implements Apache Software Foundation's [POI API Library](https://poi.apache.org/). Also Implements Apache Maven's [Javatuples](http://www.javatuples.org/) for simple tuple integration in java. 

~/GUI/src/File_Handler.java handles all direct interactions with the POI file.

~/GUI/src/Event_Controller.java handles all the interactions between the Swing GUI and the file system. 

~/GUI/src/Module_Window.java handles all graphical components. The class extends javax.swing.JFrame.








