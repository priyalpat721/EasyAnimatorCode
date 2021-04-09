package cs5004.animator.view;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs5004.animator.action.IAction;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.shape.IShape;
import cs5004.animator.shape.Shape;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;

import static cs5004.animator.utils.AnimationReader.parseFile;

public class SVGView {
  private StringBuilder result;

  public SVGView() {
    this.result = new StringBuilder();
  }

  public void create(IAnimatorModel model, int speed) {

    HashMap<String, IShape> shapes = model.getLogOfShapes();
    List<IAction> actions = model.getChronological();

    result.append(String.format("<svg width=\"%d\" height=\"%d\" "
                    + "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n",
            model.getBox()[2], model.getBox()[3]));


    for (Map.Entry<String, IShape> entry : shapes.entrySet()) {
      IShape shape = entry.getValue();
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
              + "fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n", tag,
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
                        + "fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n", tag,
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

      for (IAction action : actions) {
        if (action.getName().equals(shape.getName())) {
          switch (action.getType()) {
            case MOVE -> {
              result.append(String.format("\t<animate attributeType=\"xml\" begin=\"%s\" "
                              + "dur=\"%s\" attributeName=\"%s\" from=\"%d\" to=\"%d\" "
                              + "fill=\"freeze\" />\n",
                      action.getTime().getStartTime() * 100 + "ms",
                      ((action.getTime().getEndTime() - action.getTime().getStartTime()) * 100)
                              / speed + "ms",
                      attributes[0],
                      (int) action.getOldPosition().getX(),
                      (int) action.getNewPosition().getX()));
              result.append(String.format("\t<animate attributeType=\"xml\" begin=\"%s\" "
                              + "dur=\"%s\" attributeName=\"%s\" from=\"%d\" to=\"%d\" "
                              + "fill=\"freeze\" />\n",
                      action.getTime().getStartTime() * 100 + "ms",
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
                      action.getTime().getStartTime() * 100 + "ms",
                      ((action.getTime().getEndTime() - action.getTime().getStartTime()) * 100)
                              / speed + "ms",
                      attributes[2],
                      (int) action.getOldWidth(),
                      (int) action.getNewWidth()));
              if (shape.getType() != Shape.CIRCLE) {
                result.append(String.format("\t<animate attributeType=\"xml\" begin=\"%s\" "
                                + "dur=\"%s\" attributeName=\"%s\" from=\"%d\" to=\"%d\" "
                                + "fill=\"freeze\" />\n",
                        action.getTime().getStartTime() * 100 + "ms",
                        ((action.getTime().getEndTime() - action.getTime().getStartTime()) * 100)
                                / speed + "ms",
                        attributes[3],
                        (int) action.getOldHeight(),
                        (int) action.getNewHeight()));
              }
            }

            case CHANGECOLOR -> result.append(String.format("\t<animate attributeType=\"xml\" "
                            + "begin=\"%s\" dur=\"%s\" attributeName=\"fill\" "
                            + "from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\" fill=\"freeze\" />\n",
                    action.getTime().getStartTime() * 100 + "ms",
                    ((action.getTime().getEndTime() - action.getTime().getStartTime()) * 100)
                            / speed + "ms",
                    (int) action.getOldColor().getRed(),
                    (int) action.getOldColor().getGreen(),
                    (int) action.getOldColor().getBlue(),
                    (int) action.getNewColor().getRed(),
                    (int) action.getNewColor().getGreen(),
                    (int) action.getNewColor().getBlue()));
          }
        }
      }
      result.append(String.format("</%s>\n\n", tag));
    }

    result.append("</svg>");
  }

  public String build() {
    return this.result.toString();
  }


  public static void main(String[] args) throws IOException {
    AnimationBuilder<IAnimatorModel> builder = new Builder();
    var fileName = "src/cs5004/animator/demo.txt";
    Readable in = new FileReader(fileName);
    IAnimatorModel animation = parseFile(in, builder);

    SVGView svg = new SVGView();
    svg.create(animation, 1);
    System.out.println(svg.build());

    //createFile("test", "svg", svg.toString());
  }

}
