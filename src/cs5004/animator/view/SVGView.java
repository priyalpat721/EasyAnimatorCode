package cs5004.animator.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs5004.animator.action.IAction;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.shape.IShape;
import cs5004.animator.shape.Shape;

public class SVGView {
  private StringBuilder result;

  public SVGView() {
    this.result = new StringBuilder();
  }

  public void buildSVG(IAnimatorModel model) {
    List<IAction> actions = model.getChronological();
    HashMap<String, IShape> shapes = model.getLogOfShapes();

    result.append(String.format("<svg width='%d' height='%d' "
                    + "version='1.1' xmlns='http://www.w3.org/2000/svg'>\n\n", model.getBox()[2],
                                                                               model.getBox()[3]));


    for (Map.Entry<String, IShape> entry : shapes.entrySet()) {
      if (entry.getValue().getType() == Shape.RECTANGLE) {
        IShape shape = entry.getValue();
        result.append(String.format("<rect id='%s' x='%d' y='%d' width='%d' height='%d' "
                + "fill=rgb('%d','%d','%d') visibility='visible' >\n", shape.getName(),
                                                               (int) shape.getPosition().getX(),
                                                               (int) shape.getPosition().getY(),
                                                               (int) shape.getWidth(),
                                                               (int) shape.getHeight(),
                                                               (int) shape.getColor().getRed(),
                                                               (int) shape.getColor().getGreen(),
                                                               (int) shape.getColor().getBlue()));
        for (IAction action : model.getChronological()) {
          if (action.getName().equals(shape.getName())) {
            switch (action.getType()) {
              case MOVE:
                result.append(String.format("\t<animate attributeType='xml' begin='%s' dur='%s' "
                        + "attributeName='x' from='%d' to='%d' fill='freeze' />\n",
                        action.getTime().getStartTime() * 1000 + "ms",
                        (action.getTime().getEndTime() - action.getTime().getStartTime()) * 1000 + "ms",
                        (int) action.getCurrentShape().getPosition().getX(),
                        (int) action.getNewPosition().getX()));

                result.append(String.format("\t<animate attributeType='xml' begin='%s' dur='%s' "
                                + "attributeName='y' from='%d' to='%d' fill='freeze' />\n",
                        action.getTime().getStartTime() * 1000 + "ms",
                        (action.getTime().getEndTime() - action.getTime().getStartTime()) * 1000 + "ms",
                        (int) action.getCurrentShape().getPosition().getY(),
                        (int) action.getNewPosition().getY()));

                break;

              case SCALE:
                result.append(String.format("\t<animate attributeType='xml' begin='%s' dur='%s' "
                        + "attributeName='width' from='%d' to='%d' fill='freeze' />\n",
                        action.getTime().getStartTime() * 1000 + "ms",
                        (action.getTime().getEndTime() - action.getTime().getStartTime()) * 1000 + "ms",
                        (int) action.getCurrentShape().getWidth(),
                        (int) action.getNewWidth()));

                result.append(String.format("\t<animate attributeType='xml' begin='%s' dur='%s' "
                                + "attributeName='height' from='%d' to='%d' fill='freeze' />\n",
                        action.getTime().getStartTime() * 1000 + "ms",
                        (action.getTime().getEndTime() - action.getTime().getStartTime()) * 1000 + "ms",
                        (int) action.getCurrentShape().getHeight(),
                        (int) action.getNewHeight()));

                break;

              case CHANGECOLOR:
                result.append(String.format("\t<animate attributeType='xml' begin='%s' dur='%s' "
                                + "attributeName='r' from='%d' to='%d' fill='freeze' />\n",
                        action.getTime().getStartTime() * 1000 + "ms",
                        (action.getTime().getEndTime() - action.getTime().getStartTime()) * 1000 + "ms",
                        (int) action.getCurrentShape().getColor().getRed(),
                        (int) action.getNewColor().getRed()));

                result.append(String.format("\t<animate attributeType='xml' begin='%s' dur='%s' "
                                + "attributeName='g' from='%d' to='%d' fill='freeze' />\n",
                        action.getTime().getStartTime() * 1000 + "ms",
                        (action.getTime().getEndTime() - action.getTime().getStartTime()) * 1000 + "ms",
                        (int) action.getCurrentShape().getColor().getGreen(),
                        (int) action.getNewColor().getGreen()));

                result.append(String.format("\t<animate attributeType='xml' begin='%s' dur='%s' "
                                + "attributeName='b' from='%d' to='%d' fill='freeze' />\n",
                        action.getTime().getStartTime() * 1000 + "ms",
                        (action.getTime().getEndTime() - action.getTime().getStartTime()) * 1000 + "ms",
                        (int) action.getCurrentShape().getColor().getBlue(),
                        (int) action.getNewColor().getBlue()));

                break;
            }
          }
        }
        result.append("</rect>\n\n");
      }
    }

    result.append("</svg");
  }



}
