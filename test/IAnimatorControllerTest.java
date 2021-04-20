import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JScrollPane;

import cs5004.animator.controller.AnimatorControllerImpl;
import cs5004.animator.controller.IAnimatorController;
import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.view.EditorView;
import cs5004.animator.view.Frame;
import cs5004.animator.view.ShapesPanel;

import static org.junit.Assert.assertEquals;

public class IAnimatorControllerTest {
  private IAnimatorModel model;
  private MockView view;
  private IAnimatorController controller;
  private int speed;
  private ByteArrayOutputStream outContent;
  private PrintStream originalOut;

  @Before
  public void setUp() {
    model = new AnimatorModelImpl();
    view = new MockView();
    controller = new AnimatorControllerImpl(model, view, speed);
    speed = 1;

    outContent = new ByteArrayOutputStream();
    originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    controller.go();
  }

  @After
  public void restoreStreams() {
    System.setOut(originalOut);
  }

  @Test
  public void testKeyboard() {
    // play: key enter
    KeyEvent enter = new KeyEvent(view.getFrame(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0,  KeyEvent.VK_ENTER, '\n');
    // pause: key spacebar
    KeyEvent space = new KeyEvent(view.getFrame(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0,  KeyEvent.VK_SPACE, '\b');
    // resume: key 's'
    KeyEvent keyS = new KeyEvent(view.getFrame(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0,  KeyEvent.VK_S, 's');
    // restart: key 'r'
    KeyEvent keyR = new KeyEvent(view.getFrame(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0,  KeyEvent.VK_R, 'r');
    // loop: key 'l'
    KeyEvent keyL = new KeyEvent(view.getFrame(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0,  KeyEvent.VK_L, 'l');
    // increase speed: key '.'
    KeyEvent period = new KeyEvent(view.getFrame(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0,  KeyEvent.VK_PERIOD, '.');
    // decrease speed: key ','
    KeyEvent comma = new KeyEvent(view.getFrame(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0,  KeyEvent.VK_COMMA, ',');

    // press keys
    view.getFrame().getKeyListeners()[0].keyPressed(enter);
    view.getFrame().getKeyListeners()[0].keyPressed(space);
    view.getFrame().getKeyListeners()[0].keyPressed(keyS);
    view.getFrame().getKeyListeners()[0].keyPressed(keyR);
    view.getFrame().getKeyListeners()[0].keyPressed(keyL);
    view.getFrame().getKeyListeners()[0].keyPressed(period);
    view.getFrame().getKeyListeners()[0].keyPressed(comma);

    assertEquals("play\npause\nresume\nrestart\nloop\nincreaseSpeed\ndecreaseSpeed\n",
            outContent.toString());
  }

  private class MockView extends EditorView {
    private Frame frame;
    private ShapesPanel animation;
    private JScrollPane scrollPane;

    private MockView() {
      this.animation = new ShapesPanel(model.getShapesAtTicks(0));
      this.scrollPane = new JScrollPane(animation);
      this.frame = new Frame(1, 1, animation, scrollPane);
    }

    @Override
    public void create(IAnimatorModel model, int speed) {
    }

    @Override
    public void play() {
      System.out.println("play");
    }

    @Override
    public void pause() {
      System.out.println("pause");
    }

    @Override
    public void restart() {
      System.out.println("restart");
    }

    @Override
    public void resume() {
      System.out.println("resume");
    }

    @Override
    public void loop() {
      System.out.println("loop");
    }

    @Override
    public void increaseSpeed() {
      System.out.println("increaseSpeed");
    }

    @Override
    public void decreaseSpeed() {
      System.out.println("decreaseSpeed");
    }

    @Override
    public void makeVisible() {
    }

    @Override
    public void setCommandButtonListener(MouseListener mouseEvent, KeyListener keyEvent) {
      frame.addKeyListener(keyEvent);
    }

    @Override
    public void setFocus() {
      frame.requestFocus();
    }

    public Frame getFrame() {
      return this.frame;
    }
  }

}

