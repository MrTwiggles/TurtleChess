import java.awt.*;
import java.awt.event.*;

/** SimpleWindow är ett ritfönster med enkla operationer */
public class SimpleWindow {
  private static int nbrOpenFrames = 0;     // number of open frames
  private Frame frame;                      // the frame
  private SWCanvas canvas;                  // the drawing area
  private SWEventBuffer eventQueue;         // queue for events
  private AWTEvent lastEvent;               // the last event
  private int eventType;                    // type of last event
  private int mouseX,mouseY,mouseButton;    // mouse event parameters
  private char key;                         // key event parameter
  private MouseEventHandler mouseHandler;   // mouse event handler
  private KeyEventHandler keyHandler;       // key event handler

  /*-------- fönsteroperationer --------*/

  /** skapa ett fönster med storleken width*height och namnet title */
  public SimpleWindow(int width, int height, String title) {
    frame = new Frame(title);
    
    MenuBar menuBar = new MenuBar();
    Menu fileMenu = new Menu("File");
    MenuItem fileQuit = new MenuItem("Quit",new MenuShortcut(KeyEvent.VK_Q));
    fileMenu.add(fileQuit);
    menuBar.add(fileMenu);
    frame.setMenuBar(menuBar);
    fileQuit.addActionListener(new MenuEventHandler());

    frame.addWindowListener(new WindowEventHandler());
    
    frame.addNotify();
    Insets insets = frame.getInsets();
    frame.setSize(width+insets.left+insets.right, 
		  height+insets.top+insets.bottom);

    canvas = new SWCanvas(width,height);
    frame.add(canvas,BorderLayout.CENTER);
    canvas.addNotify();
    canvas.createOffScreenImage();

    open();
    
    mouseHandler = new MouseEventHandler();
    keyHandler = new KeyEventHandler();
    eventQueue = new SWEventBuffer();
  }

  /** tag reda på fönstrets storlek i x-led */
  public int getWidth() { 
    return canvas.getSize().width;
  }

  /** tag reda på fönstrets storlek i y-led */
  public int getHeight() { 
    return canvas.getSize().height; 
  }

  /** radera innehållet i fönstret */
  public void clear() {
    canvas.clear();
  }

  /** stäng fönstret tillfälligt */ 
  public void close() {
    frame.setVisible(false);
    nbrOpenFrames--;
  }

  /** öppna ett stängt fönster */
  public void open() {
    frame.setVisible(true);
    nbrOpenFrames++;
  }

  /*-------- ritoperationer --------*/

  /** flytta "pennan" till punkten x,y utan att rita */
  public void moveTo(int x, int y) {
    canvas.moveTo(x,y);
  }

  /** flytta pennan från dess aktuella läge till punkten x,y
      och rita samtidigt en rät linje */
  public void lineTo(int x, int y) {
    canvas.lineTo(x,y);
  }

  /** tag reda på x-koordinaten för pennans läge */
  public int getX() { 
    return canvas.getSWX(); 
  }
    
  /** tag reda på y-koordinaten för pennans läge */
  public int getY() { 
    return canvas.getSWY(); 
  }
    
  /** skriv texten txt med början i pennans aktuella position.
      Pennans läge påverkas inte */
  public void writeText(String txt) {
    canvas.writeText(txt);
  }

  /** sätt linjebredden till thickness pixlar */
  public void setLineWidth(int thickness) {
    canvas.setLineWidth(thickness);
  }
    
  /** sätt linjefärgen till color. Färgerna anges med java.awt.Color.namn.
      Möjliga namn är black (standard), white (kan utnyttjas för radering),
      blue, cyan, darkGray, gray, green, lightGray, magenta, orange, 
      pink, red, yellow */
  public void setLineColor(Color col) {
    canvas.setLineColor(col);
  }

  /*-------- musrelaterade operationer --------*/

  /** vänta tills användaren har klickat på en musknapp */
  public void waitForMouseClick() {
    do {
      waitForEvent();
    } while (getEventType() != 2);
  }

  /** Tag reda på x-koordinaten för musens position vid senaste musklick */
  public int getMouseX() {
    return mouseX;
  }
    
  /** Tag reda på y-koordinaten för musens position vid senaste musklick */
  public int getMouseY() {
    return mouseY;
  }
  
  /** Tag reda på vilken musknapp som trycktes ned vid senaste musklick
      (1 = vänster, 2 = mitten, 3 = höger) */
  public int getMouseButton() {
    return mouseButton;
  }

  /*-------- händelsehantering --------*/
    
  /** vänta tills användaren antingen trycker ned en tangent på
      tangentbordet eller klickar med musen */
  public void waitForEvent() {
    frame.getToolkit().sync();
    canvas.addMouseListener(mouseHandler);
    canvas.addKeyListener(keyHandler);

    lastEvent = eventQueue.getNextEvent();
    if (lastEvent.getID()==KeyEvent.KEY_TYPED) {
      eventType = 1;
      key = ((KeyEvent) lastEvent).getKeyChar();
    }
    else if (lastEvent.getID()==MouseEvent.MOUSE_PRESSED) {
      eventType = 2;
      mouseX = ((MouseEvent) lastEvent).getX();
      mouseY = ((MouseEvent) lastEvent).getY();
      if (((MouseEvent) lastEvent).isMetaDown())
        mouseButton = 3;
      else if (((MouseEvent) lastEvent).isAltDown())
        mouseButton = 2;
      else
        mouseButton = 1;
    } 

    canvas.removeMouseListener(mouseHandler);
    canvas.removeKeyListener(keyHandler);
  }
    
  /** tag reda på vilken händelse som inträffat
      (1=tangentnedtryckning,  2=musklick) */
  public int getEventType() {
    return eventType;
  }
    
  /** tag reda på vilken tangent som trycktes ned */
  public char getKey() {
    return key;
  }

  /*-------- fördröjning --------*/

  /** Vänta mSec millisekunder. Kan utnyttjas i program där man
      vill se hur ritandet fortskrider */
  public static void delay(int mSec) {
    if (mSec>0) 
      try {
	Thread.sleep(mSec);
      }
      catch (InterruptedException e) {};
  }

  /*-------- händelsehanterare --------*/

  class MouseEventHandler extends MouseAdapter {
    public void mousePressed(MouseEvent e) {
      eventQueue.postEvent(e);
    }
  }
  
  class KeyEventHandler extends KeyAdapter {
    public void keyTyped(KeyEvent e) {
      eventQueue.postEvent(e);
    }
  }

  class MenuEventHandler implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      System.exit(0); 
    }
  }

  class WindowEventHandler extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
      nbrOpenFrames--;
      if (nbrOpenFrames>0) {
	frame.setVisible(false);
	frame.dispose();
      }
      else
	System.exit(0);
    }
  }
}

/* an event buffer */
class SWEventBuffer {
  private AWTEvent bufferedEvent;  // suffices to buffer one event

  public synchronized void postEvent(AWTEvent e) {
    while (bufferedEvent!=null) {
      try {
        wait();
      } catch (InterruptedException ex) {}   
    }
    bufferedEvent = e;
    notifyAll();
  }

  public synchronized AWTEvent getNextEvent() {
    while (bufferedEvent==null) {
      try {
        wait();
      } catch (InterruptedException ex) {}  
    }
    AWTEvent e = bufferedEvent;
    bufferedEvent = null;
    notifyAll();
    return e;
  }
}

/* the drawing area */
class SWCanvas extends Canvas {
  private int x,y;                 // pen attributes
  private int lineWidth;           //
  private Color lineColor;         //
  private Image offImage;          // offscreen image where all drawing
                                   // takes place
  private Graphics offGraphics;    // graphics for offscreen image
  private Dimension offDimension;  // & size

  SWCanvas(int width, int height) {
    super();
    setSize(width,height);
    setBackground(Color.white);

    x = 0;
    y = 0; 
    lineWidth = 1;
    lineColor = Color.black;
  }

  int getSWX() {
    return x;
  }

  int getSWY() {
    return y;
  }

  void clear() {
    offGraphics.clearRect(0,0,offDimension.width,offDimension.height);
    offGraphics.setColor(lineColor);  // nödvändigt; varför?
    repaint();
  }

  void moveTo(int x, int y) {
    this.x = x;
    this.y = y;
  }

  void lineTo(int x, int y) {
    int tx = this.x;
    int ty = this.y;
    if (lineWidth==1)
      offGraphics.drawLine(tx,ty,x,y);
    else if (x!=tx || y!=ty) {
      double d = Math.sqrt((x-tx)*(x-tx) + (y-ty)*(y-ty));
      double w = (double) lineWidth/2;
      double dx = w*(y-ty)/d;
      double dy = w*(x-tx)/d;
      int[] px = new int[4];
      int[] py = new int[4];

      px[0] = (int) (tx - dx);
      py[0] = (int) (ty + dy);
      px[1] = (int) (tx + dx);
      py[1] = (int) (ty - dy);
      px[2] = (int) (x + dx);
      py[2] = (int) (y - dy);
      px[3] = (int) (x - dx);
      py[3] = (int) (y + dy);
      offGraphics.fillPolygon(px,py,4);
    }
    moveTo(x,y);
    repaint();
  }

  void writeText(String txt) {
    offGraphics.drawString(txt,x,y);
    repaint();
  }

  void setLineWidth(int width) { 
    lineWidth = width; 
  }

  void setLineColor(Color col) { 
    lineColor = col; 
    offGraphics.setColor(lineColor);
  }

  void createOffScreenImage() {
    offDimension = getSize();
    offImage = createImage(offDimension.width,offDimension.height);
    offGraphics = offImage.getGraphics();
  }

  public void paint(Graphics g) {
    update(g);
  }

  public void update(Graphics g) {
    Dimension d = getSize();
    if (d.width!=offDimension.width || d.height!=offDimension.height) {
      Image temp = offImage;
      offDimension = d;
      offImage = createImage(d.width,d.height);
      offGraphics = offImage.getGraphics();
      offGraphics.drawImage(temp,0,0,this);
      offGraphics.setColor(lineColor);
    }
    g.drawImage(offImage,0,0,this);
  }
}