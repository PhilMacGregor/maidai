/**
 * 
 */
package cz.macgregor.maidai.edit.grf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import cz.macgregor.maidai.edit.core.EditorContext;
import cz.macgregor.maidai.edit.grf.comp.EditorPanel;
import cz.macgregor.maidai.edit.grf.lstn.menu.NewMapListener;
import cz.macgregor.maidai.util.GraphicUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MacGregor
 *
 */
public class MapEditor {

  @Setter
  @Getter
  private EditorContext ctx;

  @Getter
  private boolean dirty;

  @Getter
  private EditorPanel editorLabel;

  /**
   * @param args
   */
  public static void main(String[] args) {
    MapEditor mapEdit = new MapEditor();
    mapEdit.setCtx(new EditorContext(new Point(10, 10), new Point(80, 60)));

    JFrame frame = mapEdit.createFrame();

    frame.setVisible(true);

    mapEdit.getEditorLabel().requestFocus();

    Timer updateTimer = new Timer();
    updateTimer.schedule(new TimerTask() {

      @Override
      public void run() {
        mapEdit.getEditorLabel().update();
        frame.repaint();
      }

    }, 0, 10);

  }

  private JFrame createFrame() {
    JFrame frame = new JFrame("Maidai map editor");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setMinimumSize(new Dimension(800, 600));

    EditorPanel editorPanel = createEditorPanel();
    JScrollPane editorScroll = new JScrollPane(editorPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    editorScroll.getVerticalScrollBar().setUnitIncrement(4);
    frame.add(editorScroll, BorderLayout.CENTER);

    editorPanel.setParentScrollpane(editorScroll);

    JScrollPane bottomScroll = new JScrollPane(createBottomPanel(), JScrollPane.VERTICAL_SCROLLBAR_NEVER,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    frame.add(bottomScroll, BorderLayout.SOUTH);

    JScrollPane sideScroll = new JScrollPane(createSidePanel(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    editorScroll.getVerticalScrollBar().setUnitIncrement(4);
    frame.add(sideScroll, BorderLayout.EAST);

    createMenuBar(frame);

    frame.pack();
    GraphicUtils.moveToCenter(frame);

    return frame;
  }

  private EditorPanel createEditorPanel() {
    EditorPanel editorPanel = new EditorPanel(this);
    editorPanel.setOpaque(true);
    editorPanel.setBackground(Color.black);
    editorPanel.setPreferredSize(new Dimension(800, 480));
    editorPanel.setMinimumSize(new Dimension(640, 480));

    editorPanel.setFocusable(true);

    this.editorLabel = editorPanel;

    return editorPanel;
  }

  private JPanel createBottomPanel() {
    JPanel bottomPanel = new JPanel();
    bottomPanel.setOpaque(true);
    bottomPanel.setBackground(Color.BLUE);
    bottomPanel.setPreferredSize(new Dimension(800, 120));
    bottomPanel.setMinimumSize(new Dimension(640, 120));
    return bottomPanel;
  }

  private JPanel createSidePanel() {
    JPanel sidePanel = new JPanel();
    sidePanel.setOpaque(true);
    sidePanel.setBackground(Color.RED);
    sidePanel.setPreferredSize(new Dimension(240, 600));
    sidePanel.setMinimumSize(new Dimension(240, 600));
    return sidePanel;
  }

  private void createMenuBar(JFrame frame) {
    JMenuBar menu = new JMenuBar();
    frame.setJMenuBar(menu);

    JMenu souborMenu = new JMenu("Soubor");
    souborMenu.setMnemonic(KeyEvent.VK_S);

    JMenuItem newGameItem = new JMenuItem("Nová mapa", KeyEvent.VK_N);
    newGameItem.addActionListener(new NewMapListener(this, frame));
    souborMenu.add(newGameItem);

    souborMenu.add(new JMenuItem("Otevřít mapu", KeyEvent.VK_O));
    souborMenu.add(new JMenuItem("Uložit", KeyEvent.VK_U));
    souborMenu.add(new JMenuItem("Uložit jako", KeyEvent.VK_J));
    souborMenu.add(new JMenuItem("Finalizovat", KeyEvent.VK_F));
    souborMenu.add(new JMenuItem("Možnosti mapy", KeyEvent.VK_M));
    souborMenu.add(new JMenuItem("Konec"));

    menu.add(souborMenu);

    menu.add(new JSeparator());

    JMenu MapMenu = new JMenu("Mapa");
    MapMenu.setMnemonic(KeyEvent.VK_M);

    MapMenu.add(new JMenuItem("Testovat", KeyEvent.VK_T));

    menu.add(MapMenu);

    menu.add(new JSeparator());

    JMenu optionsMenu = new JMenu("Nastavení");
    optionsMenu.setMnemonic(KeyEvent.VK_N);
    menu.add(optionsMenu);

    menu.add(new JSeparator());

    JMenu helpMenu = new JMenu("Nápověda");
    helpMenu.setMnemonic(KeyEvent.VK_F1);
    menu.add(helpMenu);

    menu.add(new JSeparator());

  }

}
