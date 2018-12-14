package cz.macgregor.maidai.util;

import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.text.JTextComponent;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GraphicUtils {

  /**
   * move the frame to the center of the screen.
   * 
   * @param comp
   *          component to move
   */
  public static void moveToCenter(Component comp) {
    Point stred = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
    stred.move(stred.x - (comp.getSize().width / 2), stred.y - (comp.getSize().height / 2));
    comp.setLocation(stred);
  }

  public static boolean validate(JTextComponent component, String regex, String defaultValue) {
    if (!component.getText().matches(regex)) {
      component.setText(defaultValue);
      return false;
    }
    return true;
  }

}
