package cz.macgregor.maidai.edit.grf.lstn;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.text.JTextComponent;

import cz.macgregor.maidai.util.GraphicUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidatingIistener implements KeyListener {

  public static final String POSITIVE_INTEGER = "[0-9]{1,9}";

  private final JTextComponent comp;

  private final String regex;

  private String oldValue = "";

  @Override
  public void keyTyped(KeyEvent e) {
    // do nothing
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (GraphicUtils.validate(comp, regex, oldValue)) {
      oldValue = comp.getText();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    GraphicUtils.validate(comp, regex, oldValue);
  }

}
