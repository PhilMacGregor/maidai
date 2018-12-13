package cz.macgregor.maidai.edit.grf.lstn.menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cz.macgregor.maidai.edit.core.EditorContext;
import cz.macgregor.maidai.edit.grf.MapEditor;
import cz.macgregor.maidai.util.GraphicUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NewMapListener implements ActionListener {

  private final MapEditor editor;
  private final JFrame frame;

  private int outX = 0;
  private int outY = 0;

  private JDialog dialog;

  @Override
  public void actionPerformed(ActionEvent e) {
    this.dialog = new NewMapDialog();
  }

  private class NewMapDialog extends JDialog {

    private NewMapDialog() {
      super(frame, true);
      this.setTitle("Nová mapa");

      JPanel params = new JPanel(new GridLayout(2, 2));

      JTextField sizeXLabel = new JTextField("Výška");
      sizeXLabel.setEditable(false);
      JTextField sizeX = new JTextField(Integer.toString(outX));

      JTextField sizeYLabel = new JTextField("Šířka");
      sizeYLabel.setEditable(false);
      JTextField sizeY = new JTextField(Integer.toString(outY));

      params.add(sizeXLabel);
      params.add(sizeX);
      params.add(sizeYLabel);
      params.add(sizeY);

      JDialog theDialog = this;

      JButton okButton = new JButton("OK");
      okButton.addActionListener(e -> {
        outX = Integer.parseInt(GraphicUtils.validate(sizeX, "[0-9]{1,9}", "0"));
        outY = Integer.parseInt(GraphicUtils.validate(sizeY, "[0-9]{1,9}", "0"));
        if (outX == 0 || outY == 0) {
          return;
        }

        editor.setCtx(new EditorContext(outX, outY));
        theDialog.dispose();
      });

      JButton stornoButton = new JButton("storno");
      stornoButton.addActionListener(e -> theDialog.dispose());

      this.add(params);

      JPanel buttonsPanel = new JPanel();
      buttonsPanel.add(okButton);
      buttonsPanel.add(stornoButton);
      this.add(buttonsPanel, BorderLayout.SOUTH);

      this.pack();
      this.setResizable(false);

      GraphicUtils.moveToCenter(this);
      this.setVisible(true);
    }

  }

}
