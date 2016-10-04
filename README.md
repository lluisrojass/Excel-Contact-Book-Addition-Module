# Microsoft Excel Email/Records Book Insertion Module

A Java Swing GUI Module which handles the addition of an entry into a Microsoft Excel based Email Records book. In order to access and manipulate Microsoft OLE 2 Compound Document formatted and Open Office XML standard files this module implements Apache Software Foundation's [POI API Library](https://poi.apache.org/). Also Implements Apache Maven's [Javatuples](http://www.javatuples.org/) for simple tuple integration in java. 

~/GUI/src/File_Handler.java handles all direct interactions with the POI file. Most methods return a Triplet<Boolean,String,String> report tuple which contains information on the method's performance. If during execution an exception is to caught, the Boolean field will return True and the second String field will contain error details (otherwise null). Last String field is reserved for a description of the method's intended job.  

~/GUI/src/Event_Controller.java handles all the interactions between the Swing GUI and the file system. This is accomplished through anonymous event handlers. The event handler also uses returned performance reports to update the GUI warnings JLabel in order to notify the user. 


~/GUI/src/Module_Window.java handles all graphical components. The class extends javax.swing.JFrame instead of creating a JFrame object. 










