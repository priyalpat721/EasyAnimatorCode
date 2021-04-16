package cs5004.animator.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;


import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;
import cs5004.animator.view.TestView;
import cs5004.animator.view.VisualView;

import static cs5004.animator.tools.Helpers.generateView;
import static cs5004.animator.tools.Helpers.parseCommands;
import static cs5004.animator.tools.Helpers.showMessage;
import static cs5004.animator.utils.AnimationReader.parseFile;

public class AnimatorControllerImpl extends MouseAdapter implements IAnimatorController {
  private IAnimatorModel model;
  private TestView view;
//  private VisualView view;

  public AnimatorControllerImpl(IAnimatorModel model, TestView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void go() {
    view.setCommandButtonListener(this);
    view.makeVisible();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    switch (e.getComponent().getName()) {
      case "press":
        view.setText("button pressed");
        break;
      case "quit":
        System.out.println("quit");
        System.exit(0);
    }

  }

//  public static void main(String[] args) {
//    TestView view = new TestView();
//    IAnimatorModel model = new AnimatorModelImpl();
//    AnimatorControllerImpl controller = new AnimatorControllerImpl(model, view);
//    controller.go();
//  }

  public static void main(String[] args) {
    String[] commands = parseCommands(args);
    String inputFile = commands[0];
    String viewType = commands[1];
    String[] outputFile = commands[2].split("\\.");

    int speed = 1;
    if (!commands[3].equals("")) {
      speed = Integer.parseInt(commands[3]);
    }

    AnimationBuilder<IAnimatorModel> builder = new Builder();
    Readable in = null;

    try {
      in = new FileReader(inputFile);
    } catch (FileNotFoundException e) {
      showMessage("Input file not found", 2);
      System.exit(0);
    }

    IAnimatorModel animation = parseFile(in, builder);
    generateView(animation, viewType, outputFile, speed);
  }

}
