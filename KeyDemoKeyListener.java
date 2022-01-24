import java.awt.event.*;

public class KeyDemoKeyListener implements KeyListener {

  @Override
  public void keyTyped(KeyEvent e) {
    System.out.println("Typed: " + e.getKeyChar());
  }

  @Override
  public void keyPressed(KeyEvent e) {
    System.out.println("Pressed: " + e.getKeyChar());
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      System.out.println("pressed SPACE");
    }
    else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      System.out.println("pressed RIGHT");
    }
    else if (e.getKeyCode() == KeyEvent.VK_Q) {
      System.exit(0);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    System.out.println("Released: " + e.getKeyChar());
  }
}