package cz.macgregor.maidai.edit.grf.lstn.menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cz.macgregor.maidai.edit.core.EditorContext;
import cz.macgregor.maidai.edit.grf.MapEditor;
import cz.macgregor.maidai.edit.grf.lstn.ValidatingIistener;
import cz.macgregor.maidai.util.GraphicUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NewMapListener implements ActionListener {

  private final MapEditor editor;
  private final JFrame frame;

  private int outX = 0;
  private int outY = 0;

  private int fldOutX = 80;
  private int fldOutY = 60;

  @Override
  public void actionPerformed(ActionEvent e) {
    showDialog();
  }

  private void showDialog() {
    JDialog dialog = new JDialog(frame, true);
    dialog.setTitle("Nová mapa");

    JPanel sizeParams = new JPanel(new GridLayout(2, 2));
    JTextField sizeX = sizeField("Šířka", outX, sizeParams);
    JTextField sizeY = sizeField("Výška", outY, sizeParams);

    JPanel fldSizeParams = new JPanel(new GridLayout(2, 2));
    JTextField fldSizeX = sizeField("Šířka pole", fldOutX, fldSizeParams);
    JTextField fidSizeY = sizeField("Výška pole", fldOutY, fldSizeParams);

    JButton okButton = new JButton("OK");
    okButton.addActionListener(e -> {
      outX = Integer.parseInt(sizeX.getText());
      outY = Integer.parseInt(sizeY.getText());
      fldOutX = Integer.parseInt(fldSizeX.getText());
      fldOutY = Integer.parseInt(fidSizeY.getText());
      if (outX == 0 || outY == 0) {
        return;
      }

      editor.setCtx(new EditorContext(new Point(outX, outY), new Point(fldOutX, fldOutY)));
      dialog.dispose();
      frame.repaint();
    });

    JButton stornoButton = new JButton("storno");
    stornoButton.addActionListener(e -> dialog.dispose());

    JPanel centerpanel = new JPanel();
    centerpanel.add(sizeParams);
    centerpanel.add(fldSizeParams);
    dialog.add(centerpanel);

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.add(okButton);
    buttonsPanel.add(stornoButton);
    dialog.add(buttonsPanel, BorderLayout.SOUTH);

    dialog.pack();
    dialog.setResizable(false);

    GraphicUtils.moveToCenter(dialog);
    dialog.setVisible(true);
  }

  private JTextField sizeField(String tag, int defaultVal, JComponent parent) {
    JTextField labelField = new JTextField(tag);
    labelField.setEditable(false);

    JTextField sizeField = new JTextField(Integer.toString(defaultVal));
    sizeField.addKeyListener(new ValidatingIistener(sizeField, ValidatingIistener.POSITIVE_INTEGER));

    parent.add(labelField);
    parent.add(sizeField);

    return sizeField;
  }

}
