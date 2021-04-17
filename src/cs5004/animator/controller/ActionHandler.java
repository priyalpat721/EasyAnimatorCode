package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs5004.animator.view.EditorView;

public class ActionHandler implements ActionListener {
  private EditorView view;
  private Timer timer;

  public ActionHandler(EditorView view) {
    this.view = view;
    this.timer = new Timer(1000, this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getActionCommand().equals("play")) {
      timer.start();
      view.play();
      timer.stop();
    }
  }

}
