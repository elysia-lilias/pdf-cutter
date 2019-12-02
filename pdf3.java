package pdfs;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.script.ScriptException;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionGoTo;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDNamedDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode;
import org.apache.pdfbox.text.PDFTextStripper;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;



public class pdf3 extends JFrame implements ActionListener{
	private static pdf3 vo;
	private static ArrayList<String> bookmarks;
	private static ArrayList<Integer> level;
	private static ArrayList<ArrayList<Integer>> belonging;
	private static ArrayList<Integer> destin;
	private static Integer belongs; 
	private static JPanel jf2 =new JPanel();
	private static boolean isrun = false;
    private static JButton jb;
    private static JTextField jtf;
    private static JTextField jtf2;
    private static JTextField jtf3;
    private static ImageIcon ic1 = new ImageIcon("error1.png");
    private static ImageIcon ic2 = new ImageIcon("error2.png");
    private static JButton jb1;
    private static JButton jb2;
    private static CheckBoxTreeNode[] cb;
    private static JFrame jf1 = new JFrame("data");
    private static JTree jf = new JTree();
    private static String titleofpdf;
    private static ArrayList<Integer> selected;
    private static String outputDirectory;
    private static PdfDocument sourceDoc = new PdfDocument();
    private static int numofp;
    private CardLayout card=new CardLayout();
    private static JPanel cardPanel=new JPanel();
	private static JPanel controlpaPanel=new JPanel();
	private static JPanel inputpane = new JPanel(); 
	private static pdf3 meta;
	private static GridBagConstraints c = new GridBagConstraints();
	private static GridBagLayout gridbag = new GridBagLayout();
    private static JScrollPane jsp;
    private static int times;
    private static String args0;
    private static CheckBoxTreeNode covers;
    private static boolean getinput = false;
    private static boolean getoutput = false;
    
public pdf3() throws IOException
{   selected = new ArrayList<Integer>();
	jf2.setLayout(new BoxLayout(jf2, BoxLayout.Y_AXIS));
	jf.setLayout(new BoxLayout(jf, BoxLayout.Y_AXIS));
	belongs = 1;
	bookmarks = new ArrayList<String>();
	level = new ArrayList<Integer>();
	belonging = new ArrayList<ArrayList<Integer>>();
	destin = new ArrayList<Integer>();
	//jb = new JButton("Run");
	JLabel jl1 = new JLabel("Pdf Path（导入的pdf）:");
	JLabel jl2 = new JLabel("Output Path（导出地址）：");
	JLabel jl3 = new JLabel("Save Name（保存名，可以不带pdf）：");
	jtf = new JTextField("");
	jtf2 = new JTextField("");
	jtf3 = new JTextField("Output.pdf");
	//savechange = new JButton("Save");
	//jf.add(jb);
	jb = new JButton("Run");
	jb1 = new JButton("Next");
	jb2 = new JButton("Back");
	jb1.addActionListener(this);
    jb2.addActionListener(this);
    jb.addActionListener(this);
	controlpaPanel.setSize(400, 200);
    controlpaPanel.setLayout(new BorderLayout());
    controlpaPanel.add(jb1,BorderLayout.EAST);
   // controlpaPanel.add(jb2,BorderLayout.WEST);
    cardPanel.setLayout(card);
    GridBagConstraints ct = new GridBagConstraints();
    GridBagLayout gridbagt = new GridBagLayout();
    inputpane.setLayout(gridbagt);
    ct.fill = GridBagConstraints.HORIZONTAL;
	ct.weightx = 1.0; 
//	ct.gridheight = 1;
	ct.gridwidth = 1;
	ct.gridx = 0;
	ct.gridy = 0;
	gridbagt.setConstraints(jl1, ct);
	ct.gridy = 1;
	ct.gridwidth = 5;
	gridbagt.setConstraints(jtf, ct);
	ct.gridx = 0;
	ct.gridy = 2;
	ct.gridwidth = 1;
	gridbagt.setConstraints(jl2, ct);
	ct.gridy = 3;
	ct.gridwidth = 2;
	gridbagt.setConstraints(jtf2, ct);
	ct.gridx = 0;
	ct.gridy = 4;
	ct.gridwidth = 1;
	gridbagt.setConstraints(jl3, ct);
	ct.gridy = 5;
	ct.gridwidth = 2;
	gridbagt.setConstraints(jtf3, ct);
    inputpane.add(jtf);
    inputpane.add(jtf2);
    inputpane.add(jtf3);
    inputpane.add(jl1);
    inputpane.add(jl2);
    inputpane.add(jl3);
//    GridBagLayout gridbag = new GridBagLayout();
//	GridBagLayout gridbag1 = new GridBagLayout();
	jf1.setLayout(gridbag);
//	this.setLayout(gridbag1);
//	GridBagConstraints c = new GridBagConstraints();
//	GridBagConstraints c1 = new GridBagConstraints();
	int iy = 0;
	//c.fill = GridBagConstraints.HORIZONTAL;
	c.fill = GridBagConstraints.BOTH;
	c.weightx = 3.0; 
	c.gridheight = 1;
	c.gridx = 0;
	c.gridy = 0;
	c.gridwidth = 3;
	gridbag.setConstraints(inputpane, c);
	c.gridx = 0;
	c.gridy = 1;
	gridbag.setConstraints(controlpaPanel, c);
	jf1.add(inputpane);
	jf1.add(controlpaPanel);
    jf1.setSize(400, 1200);;
    jf1.setVisible(true); 
    jf1.setDefaultCloseOperation(2);
    //jf2.setPreferredSize(new Dimension(300,500));
	      //   jf.pack();
	 //		jf.setVisible(true);
}

@Override
public void actionPerformed(ActionEvent e) {
	 if (e.getSource()==jb1){
		 if(!isrun)
		 { isrun = true;
		 controlpaPanel.remove(jb1);
			try {
				main0();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
			} }}
		if (e.getSource()==jb2){
			 isrun = false;	 	    	
			 level.clear();
	 	     destin.clear();
	 	     bookmarks.clear();
	 	     belonging.clear();	
	 	     selected.clear();
	 	     controlpaPanel.remove(jb);
	 	     controlpaPanel.remove(jb2);
	 	     controlpaPanel.add(jb1,BorderLayout.EAST);
	 	     jf2.removeAll();
	 	     jf = new JTree();
	 	     getoutput = false;
	 	     getinput = false;
	 	    // jf2.add(jf);
	 	    // jsp.repaint();
	 	   // jsp=new JScrollPane(jf2);
	 	  //  c = new GridBagConstraints();
	 	   //gridbag = new GridBagLayout();
	 	  //   jsp.getViewport().remove(jf2);
	 	  //   jf1.removeAll();
	 	    jf1.remove(jsp);
	 	    jf1.setVisible(false); 
	         jf1.setVisible(true); 
		 }
		if(e.getSource()==jb) {
			getoutput = false;
			outputDirectory = jtf2.getText();
			// TODO Auto-generated method stub
			selected.clear();
			if(covers.isSelected)
				selected.add(-1);
			for(int sz=0;sz<cb.length;sz++)
				if(cb[sz].isSelected)
					selected.add(sz);
			//printarray(selected);
			if(!selected.isEmpty()) {
			OutputStream outputStream;
			while(!getoutput) {		
			try {
				PdfReader reader = new PdfReader(args0);
				if(!outputDirectory.isEmpty())
				if(outputDirectory.charAt(outputDirectory.length()-1)!='/')
					if(outputDirectory.charAt(outputDirectory.length()-1)!='\\')
				      outputDirectory = outputDirectory + "/";
				String savename = jtf3.getText();
				try{
				String lastn = savename.substring(savename.length()-3);
				if(lastn.equalsIgnoreCase("pdf"))
			    {if(savename.charAt(savename.length()-4)!='.')
				savename = savename.substring(0, savename.length()-3)+".pdf";
			    }else {
			    	savename = savename.replace(".", "")+".pdf";
			    }}catch(Exception e234) {savename = savename.replace(".", "")+".pdf";}
				//System.out.println(args0);
				Document newDoc_1 = new Document(reader.getPageSize(1));
		outputStream = new FileOutputStream(outputDirectory + savename);
		PdfWriter writer = PdfWriter.getInstance(newDoc_1, outputStream);
				newDoc_1.open();
				 PdfContentByte cb = writer.getDirectContent(); // Holds the PDF data
			        PdfImportedPage page;
			if(covers.isSelected)
			{
				 newDoc_1.newPage();
		            page = writer.getImportedPage(reader, 1);
		            cb.addTemplate(page, 0, 0);
		            selected.remove(0);
			}
			for(int sz=0;sz<selected.size();sz++)
		   {
			   int sz1 = destin.get(selected.get(sz))+1;
			   int sz2 = sz1;
			   if(selected.get(sz)==destin.size()-1)
			   {
				   while(sz1 <= reader.getNumberOfPages()) {
			            newDoc_1.newPage();
			            page = writer.getImportedPage(reader, sz1);
			            cb.addTemplate(page, 0, 0);
			            sz1++;
			        }
			   }
				   else {
				   sz2 = destin.get(selected.get(sz)+1);
				   while(sz1 <= sz2) {
			            newDoc_1.newPage();
			            page = writer.getImportedPage(reader, sz1);
			            cb.addTemplate(page, 0, 0);
			            sz1++;}
			        }
		   }
			 outputStream.flush();
			 newDoc_1.close();
		        outputStream.close();
		        getoutput = true;
		        JOptionPane.showMessageDialog(jf1, "successfully output to the directory", "success",JOptionPane.INFORMATION_MESSAGE);
			}catch (Exception e12) {
				//newDoc_1.close();
				outputDirectory = (String)JOptionPane.showInputDialog(null,"cannot find output file\nplease check the path","Output Error",JOptionPane.ERROR_MESSAGE,ic2,null,"在这输入");
			    try {
				if(outputDirectory.isEmpty()) {}
			    	}catch(Exception efag) {outputDirectory = "";}
			    
			}}
			
		}else
			JOptionPane.showMessageDialog(jf1, "please choose the index","empty" ,JOptionPane.WARNING_MESSAGE);
			}
	 }




       public static void main( String[] arg ) throws IOException
	   { 
    	   meta = new pdf3();
	   }
       
	 public static void main0() throws IOException	 
	 {
	 String jft = jtf.getText();
	 args0 = jft;	
while(!getinput) {
	 READPDF(args0);
	 if(!getinput) {
	args0 = (String)JOptionPane.showInputDialog(null,"cannot find input file\nplease check the path","Input Error",JOptionPane.ERROR_MESSAGE,ic1,null,"在这输入");
	jtf.setText(args0);
	 }
}
	 if(getinput) {
	 if(args0.isEmpty())
	        {
	            usage();
	        }
	        else
	        {
	            try (PDDocument document = PDDocument.load(new File(args0)))
	            {
	                PDDocumentOutline outline =  document.getDocumentCatalog().getDocumentOutline();
	                if( outline != null )
	                {
	                    meta.printBookmark(document, outline, "",1);
	                    meta.findbelong();
	                    meta.printarray();
	                }
	                else
	                {
	                    System.out.println( "This document does not contain any bookmarks" );
	                }
	            }
	        }
	 
	         
	         ArrayList<String> st = new ArrayList<String>();
	         for(int st1=0;st1<destin.size();st1++)
	         {
	        	 String tempp = "";
	        	 tempp+=bookmarks.get(st1);
	        	 tempp = String.format("%-20s", tempp).replace(' ', '-');
	        	 tempp+="\t"+destin.get(st1);
	        	 st.add(tempp);
	         }
	         
	         cb = new CheckBoxTreeNode [st.size()];
	         for(int cb1=0;cb1<st.size();cb1++)
	         { cb[cb1] = new CheckBoxTreeNode (st.get(cb1));
	         //jf.add(cb[cb1]);
	         }
	         
	         CheckBoxTreeNode rootNode = new CheckBoxTreeNode(titleofpdf);
	         covers = new CheckBoxTreeNode("cover");
	         if(destin.get(0)>0)
	         rootNode.add(covers);
	         for(int st1=0;st1<cb.length;st1++)
	         {
	        	 if(level.get(st1)==1)
	        		 rootNode.add(cb[st1]);
	        	 else if(level.get(st1)==level.get(st1-1)+1)
	        	 {
	        		 cb[st1-1].add(cb[st1]);
	        	 }
	        	 else if (level.get(st1)==level.get(st1-1))
	        		 {
	        		 CheckBoxTreeNode cttemp = (CheckBoxTreeNode)cb[st1-1].getParent();
	        		 cttemp.add(cb[st1]);
	        		 }
	        	 else if (level.get(st1)<level.get(st1-1))
	        		 for(int st1a=2;st1a<=st1;st1a++)
	        		 {
	        			 if(level.get(st1)==level.get(st1-st1a))
	        			 {
	        				 CheckBoxTreeNode cttemp = (CheckBoxTreeNode)cb[st1-st1a].getParent();
	    	        		 cttemp.add(cb[st1]);
	    	        		 st1a = st1+1;
	        			 }
	        		 }
	         }
	         DefaultTreeModel model = new DefaultTreeModel(rootNode); 
	         model.reload();
	         jf.addMouseListener(new CheckBoxTreeNodeSelectionListener()); 
	         jf.setModel(model); 
	         jf.setCellRenderer(new CheckBoxTreeCellRenderer()); 
	         //jf2.setLayout(new Box);
	        // times = 1;
	       //  controlpaPanel.add(jb2,BorderLayout.CENTER);
	         controlpaPanel.add(jb,BorderLayout.WEST);
	         controlpaPanel.add(jb2,BorderLayout.EAST);
	       //  jf2.add(jf);
	         jsp = new JScrollPane(jf);
	        // JScrollPane jsp2 = new JScrollPane(jf2);
	         jsp.setBounds(0, 0, 300, 320); 
	         jsp.getHorizontalScrollBar().setAutoscrolls(false);
	         jsp.getVerticalScrollBar().setAutoscrolls(true);
	        // cardPanel.add(jsp);
	       //  jsp2.getVerticalScrollBar().setAutoscrolls(true);
	         c.gridx = 0;
	     	c.gridy = 2;
	     	c.weighty = 3.0; 
	     	//c.weighty = 0.0; 
	     	c.weighty = 20.0; 
	     	gridbag.setConstraints(jsp, c);
	     	jf1.add(jsp);
	        // jf1.repaint();
	        // jf1.add(controlpaPanel);
	         jf1.setVisible(false); 
	         jf1.setVisible(true); 
	        // jf1.pack();
	        
	 }    
	 }
	 
	
	 
	 public static void printarray(ArrayList<Integer> s)
	 {
		 System.out.println();
		 for(int sz=0;sz<s.size();sz++)
	//		 System.out.println(bookmarks.get(s.get(sz))+"  ");
		 System.out.println();
	 }
	 	
	 	    /**
	 	     * 
	 70	     * This will print the usage for this document.
	 71	     */
	 public static void READPDF(String inputFile){
	        //创建文档对象
		   // String in0 = inputFile.replaceAll("\\", "/");
		 String titleofpdf1 = "";
		 try {
		    titleofpdf = inputFile.substring(inputFile.lastIndexOf("/"),inputFile.length());
		 }catch(StringIndexOutOfBoundsException ee) {}
			 try {
				   titleofpdf1 = inputFile.substring(inputFile.lastIndexOf("\\")+1,inputFile.length());
			 }catch(StringIndexOutOfBoundsException e1e) {
				 
			 }
			 try { 
		 if(titleofpdf.length()>titleofpdf1.length())
			 if(titleofpdf1.length() >=1)
			 titleofpdf = titleofpdf1;
		 if(titleofpdf1.length()==0&&titleofpdf.length()==0)
			 titleofpdf = inputFile;
			 }catch(Exception ee) {titleofpdf = args0;}
		    PDDocument doc =null;
	        String content="";
	        int startPage = 1;
	        // 结束提取页数
	        int endPage = Integer.MAX_VALUE;   
	        try {
	            //加载一个pdf对象
	            doc =PDDocument.load(new File(inputFile));
	            //获取一个PDFTextStripper文本剥离对象  
	            PDFTextStripper textStripper =new PDFTextStripper();
	            textStripper.setSortByPosition(true);
	            endPage = doc.getNumberOfPages();
	            textStripper.setStartPage(4);
	            textStripper.setEndPage(5);
	            content=textStripper.getText(doc);
	           // vo.setContent(content);
	          //  System.out.println("内容:"+content);
	            numofp = doc.getNumberOfPages();
	            System.out.println("全部页数"+doc.getNumberOfPages());  
	            //关闭文档
	            getinput = true;
	            doc.close();
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	    }
	 
	 	    private static void usage()
	 	    {
	 	        System.err.println( "Usage: java " + pdf3.class.getName() + " <input-pdf>" );
	        }
	 	
	 	    /**
	 78	     * This will print the documents bookmarks to System.out.
	 79	     *
	 80	     * @param document The document.
	 81	     * @param bookmark The bookmark to print out.
	 82	     * @param indentation A pretty printing parameter
	 83	     *
	 84	     * @throws IOException If there is an error getting the page count.
	 85	     */
	 	    public void printBookmark(PDDocument document, PDOutlineNode bookmark, String indentation,int level1) throws IOException
	 	    {

	 	        PDOutlineItem current = bookmark.getFirstChild();
	 	      //  belonging.add(belongs);
		        while( current != null )
	 	        {
	 	            // one could also use current.findDestinationPage(document) to get the page number,
	 	            // but this example does it the hard way to explain the different types
	 	            // Note that bookmarks can also do completely different things, e.g. link to a website,
	 	            // or to an external file. This example focuses on internal pages.
	 	            level.add(level1);
	 	            if (current.getDestination() instanceof PDPageDestination)
	 	            {
	 	                PDPageDestination pd = (PDPageDestination) current.getDestination();
	 	                System.out.println(indentation + "Destination page: " + (pd.retrievePageNumber() + 1));
	 	              //  System.out.println(level1);
	 	              //  System.out.println(belongs);
	 	                destin.add(pd.retrievePageNumber());
	 	            }
	 	            else if (current.getDestination() instanceof PDNamedDestination)
	 	            {
	 	                PDPageDestination pd = document.getDocumentCatalog().findNamedDestinationPage((PDNamedDestination) current.getDestination());
	 	                if (pd != null)
	 	                {
	 	                    System.out.println(indentation + "Destination page: " + (pd.retrievePageNumber() + 1));
	 	                   destin.add(pd.retrievePageNumber());
	 	                }
	 	            }
	 	            else if (current.getDestination() != null)
	 	            {
	 	                System.out.println(indentation + "Destination class: " + current.getDestination().getClass().getSimpleName());
		            }
	 	
	 	            if (current.getAction() instanceof PDActionGoTo)
	 	            {
	 	                PDActionGoTo gta = (PDActionGoTo) current.getAction();
	 	                if (gta.getDestination() instanceof PDPageDestination)
	 	                {
	 	                    PDPageDestination pd = (PDPageDestination) gta.getDestination();
	 	                    System.out.println(indentation + "Destination page: " + (pd.retrievePageNumber() + 1));
	 	                   destin.add(pd.retrievePageNumber());
	 	                }
	 	                else if (gta.getDestination() instanceof PDNamedDestination)
	 	                {
	 	                    PDPageDestination pd = document.getDocumentCatalog().findNamedDestinationPage((PDNamedDestination) gta.getDestination());
	 	                    if (pd != null)
	 	                    {
	 	                        System.out.println(indentation + "Destination page: " + (pd.retrievePageNumber() + 1));
	 	                       destin.add(pd.retrievePageNumber());
	 	                    }
	 	                }
	 	                else
	 	                {
	 	                    System.out.println(indentation + "Destination class: " + gta.getDestination().getClass().getSimpleName());
	 	                }
	 	            }
	 	            else if (current.getAction() != null)
	 	            {
	 	                System.out.println(indentation + "Action class: " + current.getAction().getClass().getSimpleName());
	 	            }
	 	            System.out.println( indentation + current.getTitle() );
	 	            bookmarks.add(current.getTitle());
	 	            printBookmark( document, current, indentation + "   " ,level1+1);
	 	            current = current.getNextSibling(); 
	 	        }
	 	    }
	 	    
	 	    public void findbelong() {
	 	    	int beforelevel = level.get(0);
	 	    	ArrayList<Integer> temp = new ArrayList<Integer>();
	 	    	temp.add(1);
	 	    	ArrayList<Integer> temp00 = new ArrayList<Integer>();
	 	    	temp00.add(1);
	 	    	belonging.add(temp00);
	 	    	ArrayList<ArrayList<Integer>> bm = new ArrayList<ArrayList<Integer>>();
	 	    	int c = 1;
	 	    	for(int i=1;i<level.size();i++)
	 	    	{	
	 	    		if(level.get(i)>beforelevel)
	 	    			{beforelevel = level.get(i);
	 	    			temp.add(1);}
	 	    		else if(level.get(i) == beforelevel)
	 	    			{c=temp.get(level.get(i)-1)+1;
	 	    			temp.remove(temp.size()-1);
	 	    			temp.add(c);}
	 	    		else
	 	    		{   for(int cz=beforelevel-1;cz>=level.get(i);cz--)
	 	    			//c=temp.get(level.get(i)-1);
	 	    			{temp.remove(temp.size()-1);
	 	    			c=temp.get(temp.size()-1)+1;
	 	    			temp.remove(temp.size()-1);
	 	    		    temp.add(c);}
	 	    		}
	 	    		ArrayList<Integer> temp2 = new ArrayList<Integer>(temp);
	 	    		//temp
	 	    		belonging.add(temp2);
	 	    		beforelevel = level.get(i);
	 	    		bm = belonging;
	 	    	}
	 	    	
	 	    }
	 	    public void printarray()
	 	    {
	 	    	for(int i=0;i<belonging.size();i++)
	 	    	{
	 	    		String temp0 = bookmarks.get(i);
	 	    		for(int j=1;j<=level.get(i);j++)
	 	    		{	System.out.print("   ");}
	 	    		System.out.print(temp0);
	 	    		System.out.println();
	 	    		ArrayList<Integer> temp = new ArrayList<Integer>();
	 	    		temp = belonging.get(i);
	 	    		System.out.print("[");
	 	    		for(int j=0;j<temp.size();j++)
	 	    			System.out.print(temp.get(j)+"  ");
	 	    		System.out.print("]\n");
	 	    	}
	 	    }
}

 class CheckBoxTreeNode extends DefaultMutableTreeNode 
{ 
 protected boolean isSelected; 
  
 public CheckBoxTreeNode() 
 { 
  this(null); 
 } 
  
 public CheckBoxTreeNode(Object userObject) 
 { 
  this(userObject, true, false); 
 } 
  
 public CheckBoxTreeNode(Object userObject, boolean allowsChildren, boolean isSelected) 
 { 
  super(userObject, allowsChildren); 
  this.isSelected = isSelected; 
 } 
 
 public boolean isSelected() 
 { 
  return isSelected; 
 } 
  
 public void setSelected(boolean _isSelected) 
 { 
  this.isSelected = _isSelected; 
   
  if(_isSelected) 
  { 
 
   if(children != null) 
   { 
    for(Object obj : children) 
    { 
     CheckBoxTreeNode node = (CheckBoxTreeNode)obj; 
     if(_isSelected != node.isSelected()) 
      node.setSelected(_isSelected); 
    } 
   } 

   CheckBoxTreeNode pNode = (CheckBoxTreeNode)parent; 

   if(pNode != null) 
   { 
    int index = 0; 
    for(; index < pNode.children.size(); ++ index) 
    { 
     CheckBoxTreeNode pChildNode = (CheckBoxTreeNode)pNode.children.get(index); 
     if(!pChildNode.isSelected()) 
      break; 
    } 

    if(index == pNode.children.size()) 
    { 
     if(pNode.isSelected() != _isSelected) 
      pNode.setSelected(_isSelected); 
    } 
   } 
  } 
  else 
  { 

   if(children != null) 
   { 
    int index = 0; 
    for(; index < children.size(); ++ index) 
    { 
     CheckBoxTreeNode childNode = (CheckBoxTreeNode)children.get(index); 
     if(!childNode.isSelected()) 
      break; 
    } 

    if(index == children.size()) 
    { 
     for(int i = 0; i < children.size(); ++ i) 
     { 
      CheckBoxTreeNode node = (CheckBoxTreeNode)children.get(i); 
      if(node.isSelected() != _isSelected) 
       node.setSelected(_isSelected); 
     } 
    } 
   } 
    

   CheckBoxTreeNode pNode = (CheckBoxTreeNode)parent; 
   if(pNode != null && pNode.isSelected() != _isSelected) 
    pNode.setSelected(_isSelected); 
  } 
 } 
} 
 
 
 
 
 class CheckBoxTreeCellRenderer extends JPanel implements TreeCellRenderer 
{ 
 protected JCheckBox check; 
 protected CheckBoxTreeLabel label; 
  
 public CheckBoxTreeCellRenderer() 
 { 
  setLayout(null); 
  add(check = new JCheckBox()); 
  add(label = new CheckBoxTreeLabel()); 
  check.setBackground(UIManager.getColor("Tree.textBackground")); 
  label.setForeground(UIManager.getColor("Tree.textForeground")); 
 } 
  

 @Override 
 public Component getTreeCellRendererComponent(JTree tree, Object value, 
   boolean selected, boolean expanded, boolean leaf, int row, 
   boolean hasFocus) 
 { 
  String stringValue = tree.convertValueToText(value, selected, expanded, leaf, row, hasFocus); 
  setEnabled(tree.isEnabled()); 
  check.setSelected(((CheckBoxTreeNode)value).isSelected()); 
  label.setFont(tree.getFont()); 
  label.setText(stringValue); 
  label.setSelected(selected); 
  label.setFocus(hasFocus); 
  if(leaf) 
   label.setIcon(UIManager.getIcon("Tree.leafIcon")); 
  else if(expanded) 
   label.setIcon(UIManager.getIcon("Tree.openIcon")); 
  else 
   label.setIcon(UIManager.getIcon("Tree.closedIcon")); 
    
  return this; 
 } 
 
 @Override 
 public Dimension getPreferredSize() 
 { 
  Dimension dCheck = check.getPreferredSize(); 
  Dimension dLabel = label.getPreferredSize(); 
  return new Dimension(dCheck.width + dLabel.width, dCheck.height < dLabel.height ? dLabel.height: dCheck.height); 
 } 
  
 @Override 
 public void doLayout() 
 { 
  Dimension dCheck = check.getPreferredSize(); 
  Dimension dLabel = label.getPreferredSize(); 
  int yCheck = 0; 
  int yLabel = 0; 
  if(dCheck.height < dLabel.height) 
   yCheck = (dLabel.height - dCheck.height) / 2; 
  else 
   yLabel = (dCheck.height - dLabel.height) / 2; 
  check.setLocation(0, yCheck); 
  check.setBounds(0, yCheck, dCheck.width, dCheck.height); 
  label.setLocation(dCheck.width, yLabel); 
  label.setBounds(dCheck.width, yLabel, dLabel.width, dLabel.height); 
 } 
  
 @Override 
 public void setBackground(Color color) 
 { 
  if(color instanceof ColorUIResource) 
   color = null; 
  super.setBackground(color); 
 }

} 
 
 class CheckBoxTreeLabel extends JLabel 
 { 
  private boolean isSelected; 
  private boolean hasFocus; 
   
  public CheckBoxTreeLabel() 
  { 
  } 
   
  @Override 
  public void setBackground(Color color) 
  { 
   if(color instanceof ColorUIResource) 
    color = null; 
   super.setBackground(color); 
  } 
   
  @Override 
  public void paint(Graphics g) 
  { 
   String str; 
   if((str = getText()) != null) 
   { 
    if(0 < str.length()) 
    { 
     if(isSelected) 
      g.setColor(UIManager.getColor("Tree.selectionBackground")); 
     else 
      g.setColor(UIManager.getColor("Tree.textBackground")); 
     Dimension d = getPreferredSize(); 
     int imageOffset = 0; 
     Icon currentIcon = getIcon(); 
     if(currentIcon != null) 
      imageOffset = currentIcon.getIconWidth() + Math.max(0, getIconTextGap() - 1); 
     g.fillRect(imageOffset, 0, d.width - 1 - imageOffset, d.height); 
     if(hasFocus) 
     { 
      g.setColor(UIManager.getColor("Tree.selectionBorderColor")); 
      g.drawRect(imageOffset, 0, d.width - 1 - imageOffset, d.height - 1); 
     } 
    } 
   } 
   super.paint(g); 
  } 
   
  @Override 
  public Dimension getPreferredSize() 
  { 
   Dimension retDimension = super.getPreferredSize(); 
   if(retDimension != null) 
    retDimension = new Dimension(retDimension.width + 3, retDimension.height); 
   return retDimension; 
  } 
   
  public void setSelected(boolean isSelected) 
  { 
   this.isSelected = isSelected; 
  } 
   
  public void setFocus(boolean hasFocus) 
  { 
   this.hasFocus = hasFocus; 
  } 
 } 
 
 class CheckBoxTreeNodeSelectionListener extends MouseAdapter 
{ 
 @Override 
 public void mouseClicked(MouseEvent event) 
 { 
  JTree tree = (JTree)event.getSource(); 
  int x = event.getX(); 
  int y = event.getY(); 
  int row = tree.getRowForLocation(x, y); 
  TreePath path = tree.getPathForRow(row); 
  if(path != null) 
  { 
   CheckBoxTreeNode node = (CheckBoxTreeNode)path.getLastPathComponent(); 
   if(node != null) 
   { 
    boolean isSelected = !node.isSelected(); 
    node.setSelected(isSelected); 
    ((DefaultTreeModel)tree.getModel()).nodeStructureChanged(node); 
   } 
  } 
 } 
} 
 

 
