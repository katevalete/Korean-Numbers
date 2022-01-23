import java.awt.event.*;

public class KeyDemoKeyListener implements KeyListener {

  @Override
  public void keyTyped(KeyEvent e) {
    System.out.println("Typed: " + e.getKeyChar());
  }

  @Override
  public void keyPressed(KeyEvent e) {
    System.out.println("Pressed: " + e.getKeyChar());
  }

  @Override
  public void keyReleased(KeyEvent e) {
    System.out.println("Released: " + e.getKeyChar());
  }
}