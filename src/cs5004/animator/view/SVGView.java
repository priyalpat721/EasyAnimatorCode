package cs5004.animator.view;

import java.util.List;
import java.util.Map;

import cs5004.animator.action.Action;
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

    result.append(String.format("<svg width='%d' height='%d' "
                    + "version='1.1' xmlns='http://www.w3.org/2000/svg'>\n\n", model.getBox()[2],
                                                                               model.getBox()[3]));


    for (Map.Entry<String, IShape> entry : model.getLogOfShapes().entrySet()) {
      if (entry.getValue().getType() == Shape.RECTANGLE) {
        IShape shape = entry.getValue();
        result.append(String.format("id='%s' x='%d' y='%d' width='%d' height='%d' "
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
                result.append(String.format("<animate attributeType='xml' begin='%dms' dur='%dms' "
                        + "attributeName='x' from='%d' to='%d' fill='freeze' />\n",
                        action.getTime().getStartTime() * 1000,
                        (action.getTime().getEndTime() - action.getTime().getStartTime()) * 1000,
                        (int) action.getCurrentShape().getPosition().getX(),
                        (int) action.getNewPosition().getX()));

                result.append(String.format("<animate attributeType='xml' begin='%dms' dur='%dms' "
                                + "attributeName='y' from='%d' to='%d' fill='freeze' />\n",
                        action.getTime().getStartTime() * 1000,
                        (action.getTime().getEndTime() - action.getTime().getStartTime()) * 1000,
                        (int) action.getCurrentShape().getPosition().getY(),
                        (int) action.getNewPosition().getY()));
                break;

              case SCALE:
                result.append(String.format("<animate attributeType='xml' begin='%dms' dur='%dms' "
                        + "attributeName='y' from='%d' to='%d' fill='freeze' />\n"))

            }





          }

        }

      }

    }




  }

}
