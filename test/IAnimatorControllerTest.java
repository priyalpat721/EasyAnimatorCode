import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.PrintStream;

import cs5004.animator.controller.AnimatorControllerImpl;
import cs5004.animator.controller.IAnimatorController;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;
import cs5004.animator.view.Frame;
import cs5004.animator.view.IAnimatorView;
import cs5004.animator.view.PlayBack;

import static cs5004.animator.utils.AnimationReader.parseFile;
import static java.lang.System.lineSeparator;
import static org.junit.Assert.assertEquals;

/**
 * A JUnit test for the IAnimatorController.
 */
public class IAnimatorControllerTest {
  private MockView view;
  private PrintStream originalOut;
  private OutputStream newOut;

  @Before
  public void setUp() throws FileNotFoundException {
    AnimationBuilder<IAnimatorModel> builder = new Builder();
    Readable in = new FileReader("test/smalldemo.txt");
    IAnimatorModel model = parseFile(in, builder);
    view = new MockView();
    String[] args = {"-in", "in", "-view", "playback"};

    originalOut = System.out;
    newOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(newOut));

    IAnimatorController controller = new MockController(args, model, view);
    controller.start();
  }

  @After
  public void resetStream() {
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
    KeyEvent keyK = new KeyEvent(view.getFrame(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0,  KeyEvent.VK_K, 'k');
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
    // help: hey 'h'
    KeyEvent keyH = new KeyEvent(view.getFrame(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0,  KeyEvent.VK_H, 'h');
    // add shape: key 'a'
    KeyEvent keyA = new KeyEvent(view.getFrame(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0,  KeyEvent.VK_A, 'a');
    // add motion: key 'm'
    KeyEvent keyM = new KeyEvent(view.getFrame(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0,  KeyEvent.VK_M, 'm');
    // save: key 's'
    KeyEvent keyS = new KeyEvent(view.getFrame(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0,  KeyEvent.VK_S, 's');
    // remove shape: key 'delete'
    KeyEvent keyDelete = new KeyEvent(view.getFrame(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0,  KeyEvent.VK_DELETE, 'z');

    // press keys
    view.getFrame().getKeyListeners()[0].keyPressed(enter);
    view.getFrame().getKeyListeners()[0].keyPressed(space);
    view.getFrame().getKeyListeners()[0].keyPressed(keyK);
    view.getFrame().getKeyListeners()[0].keyPressed(keyR);
    view.getFrame().getKeyListeners()[0].keyPressed(keyL);
    view.getFrame().getKeyListeners()[0].keyPressed(period);
    view.getFrame().getKeyListeners()[0].keyPressed(comma);
    view.getFrame().getKeyListeners()[0].keyPressed(keyH);
    view.getFrame().getKeyListeners()[0].keyPressed(keyA);
    view.getFrame().getKeyListeners()[0].keyPressed(keyM);
    view.getFrame().getKeyListeners()[0].keyPressed(keyS);
    view.getFrame().getKeyListeners()[0].keyPressed(keyDelete);

    assertEquals("play" + lineSeparator()
                      + "pause" + lineSeparator()
                      + "resume" + lineSeparator()
                      + "restart" + lineSeparator()
                      + "loop" + lineSeparator()
                      + "increaseSpeed" + lineSeparator()
                      + "decreaseSpeed" + lineSeparator()
                      + "help" + lineSeparator()
                      + "addShape" + lineSeparator()
                      + "addMotion" + lineSeparator()
                      + "save" + lineSeparator()
                      + "removeShape", newOut.toString().trim());
  }

  /**
   * A private mock controller class to test the animator controller.
   * It extends AnimatorControllerImpl.
   */
  private class MockController extends AnimatorControllerImpl {
    private final MockView view;

    /**
     * Constructs a mock controller object.
     * @param args command-line arguments.
     * @param model the model.
     * @param view the view.
     */
    private MockController(String[] args, IAnimatorModel model, IAnimatorView view) {
      super(args, model, view);
      this.view = (MockView) view;
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
    public void resume() {
      System.out.println("resume");
    }

    @Override
    public void restart() {
      System.out.println("restart");
    }

    @Override
    public void loop() {
      System.out.println("loop");
    }

    @Override
    public void decreaseSpeed() {
      System.out.println("decreaseSpeed");
    }

    @Override
    public void increaseSpeed() {
      System.out.println("increaseSpeed");
    }

    @Override
    public void help() {
      System.out.println("help");
    }

    @Override
    public void addShape() {
      System.out.println("addShape");
    }

    @Override
    public void addMotion() {
      System.out.println("addMotion");
    }

    @Override
    public void removeShape() {
      System.out.println("removeShape");
    }

    @Override
    public void save() {
      System.out.println("save");
    }
  }


  /**
   * A private mock view class to test the animator controller.
   * It extends PlayBack.
   */
  private class MockView extends PlayBack {
    private final Frame frame;

    /**
     * Constructs a mock view object.
     */
    private MockView() {
      this.frame = new Frame(1, 1, null);
    }

    @Override
    public void makeVisible() {
      this.frame.setVisible(false);
    }

    @Override
    public void setCommandButtonListener(MouseListener mouseEvent, KeyListener keyEvent) {
      frame.addKeyListener(keyEvent);
    }

    /**
     * Gets the frame.
     * @return the frame.
     */
    public Frame getFrame() {
      return this.frame;
    }
  }

}

