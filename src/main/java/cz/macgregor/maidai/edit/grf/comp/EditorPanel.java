package cz.macgregor.maidai.edit.grf.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cz.macgregor.maidai.edit.core.EditorContext;
import cz.macgregor.maidai.edit.grf.MapEditor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class EditorPanel extends JPanel {

  @Getter
  private final MapEditor editor;

  @Setter
  private JScrollPane parentScrollpane;

  public void update() {
    EditorContext ctx = editor.getCtx();

    this.setSize(new Dimension(ctx.getSize().x + ctx.getFieldSize().x, ctx.getSize().y + ctx.getFieldSize().y));
    parentScrollpane.revalidate();
    parentScrollpane.repaint();

    revalidate();
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponents(g);

    Image img = new ImageIcon(this.getClass().getClassLoader().getResource("img/grass.png")).getImage();

    EditorContext ctx = editor.getCtx();

    g.setColor(Color.yellow);

    for (int i = 0; i < ctx.getSize().x; i++) {
      for (int j = 0; j < ctx.getSize().y; j++) {
        g.drawImage(img, i * ctx.getFieldSize().x, j * ctx.getFieldSize().y, null, null);
        g.drawString(i + ";" + j, i * ctx.getFieldSize().x, (j * ctx.getFieldSize().y) + 10);
      }
    }

  }

}
