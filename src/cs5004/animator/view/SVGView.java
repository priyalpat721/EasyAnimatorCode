package cs5004.animator.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cs5004.animator.action.IAction;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.shape.IShape;
import cs5004.animator.shape.Shape;

/**
 * This class represents a SVG view.
 * This class generates a file.svg format.
 * It contains the code to render a SVG view of the animation.
 * The class implements the interface IAnimatorView.
 */
public class SVGView implements IAnimatorView {
  private StringBuilder result;

  /**
   * Constructs an SVG view object.
   */
  public SVGView() {
    this.result = new StringBuilder();
  }

  @Override
  public void create(IAnimatorModel model, int speed) {
    Objects.requireNonNull(model, "Must have non-null model");
    if (speed <= 0) {
      throw new IllegalArgumentException("Speed must be greater than zero");
    }

    List<IShape> shapes = model.getLogOfShapes();
    HashMap<String, List<IAction>> dict = model.getLogOfActions();

    int count = 0;

    result.append(String.format("<svg width=\"%d\" height=\"%d\" "
                    + "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n",
            model.getBox()[2], model.getBox()[3]));

    for (IShape shape : shapes) {
      String tag = "";
      String[] attributes = new String[4];

      switch (shape.getType()) {
        case RECTANGLE -> {
          tag = "rect";
          attributes[0] = "x";
          attributes[1] = "y";
          attributes[2] = "width";
          attributes[3] = "height";
        }
        case ELLIPSE -> {
          tag = "ellipse";
          attributes[0] = "cx";
          attributes[1] = "cy";
          attributes[2] = "rx";
          attributes[3] = "ry";
        }
        case CIRCLE -> {
          tag = "circle";
          attributes[0] = "cx";
          attributes[1] = "cy";
          attributes[2] = "r";
          attributes[3] = "";
        }
      }

      if (shape.getType() == Shape.RECTANGLE || shape.getType() == Shape.ELLIPSE) {
        result.append(String.format("<%s id=\"%s\" %s=\"%d\" %s=\"%d\" %s=\"%d\" %s=\"%d\" "
                        + "fill=\"rgb(%d,%d,%d)\" visibility=\"hidden\" >\n", tag,
                shape.getName(),
                attributes[0],
                (int) shape.getPosition().getX(),
                attributes[1],
                (int) shape.getPosition().getY(),
                attributes[2],
                (int) shape.getWidth(),
                attributes[3],
                (int) shape.getHeight(),
                (int) shape.getColor().getRed(),
                (int) shape.getColor().getGreen(),
                (int) shape.getColor().getBlue()));
      }

      if (shape.getType() == Shape.CIRCLE) {
        result.append(String.format("<%s id=\"%s\" %s=\"%d\" %s=\"%d\" %s=\"%d\" "
                        + "fill=\"rgb(%d,%d,%d)\" visibility=\"hidden\" >\n", tag,
                shape.getName(),
                attributes[0],
                (int) shape.getPosition().getX(),
                attributes[1],
                (int) shape.getPosition().getY(),
                attributes[2],
                (int) shape.getRadius(),
                (int) shape.getColor().getRed(),
                (int) shape.getColor().getGreen(),
                (int) shape.getColor().getBlue()));
      }

      for (Map.Entry<String, List<IAction>> entry : dict.entrySet()) {
        if (entry.getKey().equals(shape.getName())) {
          List<IAction> actions = entry.getValue();
          for (IAction action : actions) {
            switch (action.getType()) {
              case MOVE -> {
                result.append(String.format("\t<animate attributeType=\"xml\" begin=\"%s\" "
                                + "dur=\"%s\" attributeName=\"%s\" from=\"%d\" to=\"%d\" "
                                + "fill=\"freeze\" />\n",
                        (action.getTime().getStartTime()) / speed * 100 + "ms",
                        ((action.getTime().getEndTime() - action.getTime().getStartTime()) * 100)
                                / speed + "ms",
                        attributes[0],
                        (int) action.getOldPosition().getX(),
                        (int) action.getNewPosition().getX()));
                result.append(String.format("\t<animate attributeType=\"xml\" begin=\"%s\" "
                                + "dur=\"%s\" attributeName=\"%s\" from=\"%d\" to=\"%d\" "
                                + "fill=\"freeze\" />\n",
                        (action.getTime().getStartTime() * 100) / speed + "ms",
                        ((action.getTime().getEndTime() - action.getTime().getStartTime()) * 100)
                                / speed + "ms",
                        attributes[1],
                        (int) action.getOldPosition().getY(),
                        (int) action.getNewPosition().getY()));
              }

              case SCALE -> {
                result.append(String.format("\t<animate attributeType=\"xml\" begin=\"%s\" "
                                + "dur=\"%s\" attributeName=\"%s\" from=\"%d\" to=\"%d\" "
                                + "fill=\"freeze\" />\n",
                        (action.getTime().getStartTime() * 100) / speed + "ms",
                        ((action.getTime().getEndTime() - action.getTime().getStartTime()) * 100)
                                / speed + "ms",
                        attributes[2],
                        (int) action.getOldWidth(),
                        (int) action.getNewWidth()));

                if (shape.getType() != Shape.CIRCLE) {
                  result.append(String.format("\t<animate attributeType=\"xml\" begin=\"%s\" "
                                  + "dur=\"%s\" attributeName=\"%s\" from=\"%d\" to=\"%d\" "
                                  + "fill=\"freeze\" />\n",
                          (action.getTime().getStartTime() * 100) / speed + "ms",
                          ((action.getTime().getEndTime() - action.getTime().getStartTime()) * 100)
                                  / speed + "ms",
                          attributes[3],
                          (int) action.getOldHeight(),
                          (int) action.getNewHeight()));
                }
              }

              case CHANGECOLOR -> {
                result.append(String.format("\t<animate attributeType=\"xml\" "
                                + "begin=\"%s\" dur=\"%s\" attributeName=\"fill\" "
                                + "from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\" "
                                + "fill=\"freeze\" />\n",
                        (action.getTime().getStartTime() * 100) / speed + "ms",
                        ((action.getTime().getEndTime() - action.getTime().getStartTime()) * 100)
                                / speed + "ms",
                        (int) action.getOldColor().getRed(),
                        (int) action.getOldColor().getGreen(),
                        (int) action.getOldColor().getBlue(),
                        (int) action.getNewColor().getRed(),
                        (int) action.getNewColor().getGreen(),
                        (int) action.getNewColor().getBlue()));
              }

              case STAY -> {
                if (count != 0) {
                  result.append(String.format("\t<animate attributeType=\"xml\" "
                                  + "begin=\"%s\" dur=\"%s\" fill=\"freeze\" />\n",
                          (action.getTime().getStartTime() * 100) / speed + "ms",
                          ((action.getTime().getEndTime() - action.getTime().getStartTime()) * 100)
                                  / speed + "ms"));
                } else {
                  result.append(String.format("\t<set attributeName=\"visibility\" "
                                  + "attributeType=\"CSS\" to=\"visible\" begin=\"%s\" "
                                  + "dur=\"%s\" fill=\"freeze\" />\n",
                          (action.getTime().getStartTime() * 100) / speed + "ms",
                          ((action.getTime().getEndTime() - action.getTime().getStartTime()) * 100)
                                  / speed + "ms"));
                }
              }
            }
            count += 1;
          }
        }
      }
      result.append(String.format("</%s>\n\n", tag));
      count = 0;
    }
    result.append("</svg>");
  }

  /**
   * Generates a String representation of the SVG view.
   * @return a String representation of the SVG view.
   */
  public String generate() {
    return this.result.toString();
  }

}
