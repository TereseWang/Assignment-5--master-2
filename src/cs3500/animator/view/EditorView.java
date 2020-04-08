package cs3500.animator.view;

import cs3500.animation.model.Frame;
import cs3500.animation.model.KeyFrameAnimation;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Shape;
import cs3500.animator.shape.ShapeCreator;
import cs3500.animator.shape.ShapeType;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * represents the editable JFrame for visual view.
 */
public class EditorView extends JFrame implements View, ActionListener {

  KeyFrameAnimation animation;
  JButton start;
  JButton stop;
  JButton resume;
  JButton restart;
  JButton loop;
  JButton increase;
  JButton decrease;
  JButton insert;
  JButton delete;
  JTextField nameInsert;
  JLabel speed;
  JLabel errorMessage;
  JTextField timeInsert;
  JTextField insertX;
  JTextField insertY;
  JTextField insertR;
  JTextField insertG;
  JTextField insertB;
  JTextField insertW;
  JTextField insertH;
  JTextField timeDelete;
  EditorPanel panel;
  JPanel editorPanel;

  /**
   * constructs the editor view with given model and tickPerSec. Jbuttons for the editable view is
   * constructed here.
   *
   * @param model      keyframeAnimation model
   * @param tickPerSec number of tick per second
   */
  public EditorView(KeyFrameAnimation model, int tickPerSec) {
    super();
    this.animation = model;
    this.setTitle("Animation player");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    panel = new EditorPanel(animation, tickPerSec);
    this.add(panel);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    start = new JButton("Start");
    start.addActionListener(this);
    buttonPanel.add(start);

    stop = new JButton("Stop");
    stop.addActionListener(this);
    buttonPanel.add(stop);

    resume = new JButton("Resume");
    resume.addActionListener(this);
    buttonPanel.add(resume);

    restart = new JButton("Restart");
    restart.addActionListener(this);
    buttonPanel.add(restart);

    loop = new JButton("Disable");
    loop.addActionListener(this);
    buttonPanel.add(loop);

    JPanel speedPanel = new JPanel();
    this.add(speedPanel, BorderLayout.NORTH);

    speed = new JLabel("Speed: " + 0);
    speedPanel.add(speed);

    increase = new JButton("Increase Speed");
    increase.addActionListener(this);
    speedPanel.add(increase);

    decrease = new JButton("Decrease Speed");
    decrease.addActionListener(this);
    speedPanel.add(decrease);

    editorPanel = new JPanel();
    this.add(editorPanel, BorderLayout.EAST);

    editorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    editorPanel.setPreferredSize(new Dimension(150, 100));
    editorPanel.setMaximumSize(new Dimension(150, 100));

    JLabel shape = new JLabel("ShapeName: ");
    nameInsert = new JTextField(10);

    editorPanel.add(shape);
    editorPanel.add(nameInsert);

    JLabel edit = new JLabel("Insert KeyFrame");
    editorPanel.add(edit);

    JLabel time = new JLabel("StartTick: ");
    timeInsert = new JTextField(5);
    editorPanel.add(time);
    editorPanel.add(timeInsert);

    JLabel posnx = new JLabel("PositionX: ");
    editorPanel.add(posnx);
    insertX = new JTextField(5);
    editorPanel.add(insertX);

    JLabel posny = new JLabel("PositionY: ");
    editorPanel.add(posny);
    insertY = new JTextField(5);
    editorPanel.add(insertY);

    JLabel colorR = new JLabel("R: ");
    editorPanel.add(colorR);
    insertR = new JTextField(8);
    editorPanel.add(insertR);

    JLabel colorG = new JLabel("G: ");
    editorPanel.add(colorG);
    insertG = new JTextField(8);
    editorPanel.add(insertG);

    JLabel colorB = new JLabel("B: ");
    editorPanel.add(colorB);
    insertB = new JTextField(8);
    editorPanel.add(insertB);

    JLabel width = new JLabel("Width: ");
    editorPanel.add(width);
    insertW = new JTextField(5);
    editorPanel.add(insertW);

    JLabel height = new JLabel("Height: ");
    editorPanel.add(height);
    insertH = new JTextField(5);
    editorPanel.add(insertH);

    insert = new JButton("Insert");
    insert.addActionListener(this);
    editorPanel.add(insert);

    JLabel deleteK = new JLabel("Delete KeyFrame");
    editorPanel.add(deleteK);

    JLabel deleteTime = new JLabel("StartTick: ");
    timeDelete = new JTextField(5);
    editorPanel.add(deleteTime);
    editorPanel.add(timeDelete);

    delete = new JButton("Delete");
    delete.addActionListener(this);
    editorPanel.add(delete);

    JLabel tip = new JLabel("All Fields Need to");
    JLabel tip2 = new JLabel("be Integer except");
    JLabel tip3 = new JLabel("ShapeName Field");
    editorPanel.add(tip);
    editorPanel.add(tip2);
    editorPanel.add(tip3);

    errorMessage = new JLabel("");
    editorPanel.add(errorMessage);

    pack();
    this.setBounds(model.getBox().x, model.getBox().y, model.getBox().width + 150,
        model.getBox().height + 100);
  }


  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void display() throws IOException {
    this.setVisible(true);
  }

  /**
   * check if the text is empty when using deletion. If it is, send an error message to stop
   * deletion.
   *
   * @param name name of the action
   * @param time time of the action
   */
  public void checkEmptyDelte(String name, String time) {
    if (name.trim().length() == 0 && time.trim().length() != 0) {
      errorMessage.setText("Shape Name Empty!");
      timeDelete.setText("");
    } else if (name.trim().length() != 0 && time.trim().length() == 0) {
      errorMessage.setText("Star Tick Empty!");
      nameInsert.setText("");
    } else if (name.trim().length() == 0 && time.trim().length() == 0) {
      errorMessage.setText("Shape & Time Empty!");
    } else if (errorMessage.getText().trim().length() != 0 && name.trim().length() != 0
        && time.trim().length() != 0) {
      errorMessage.setText("");
      try {
        Integer.parseInt(time);
      } catch (NumberFormatException e) {
        errorMessage.setText("Time Must Be Integer");
        nameInsert.setText("");
        timeDelete.setText("");
      }
    } else if (errorMessage.getText().trim().length() == 0 && name.trim().length() != 0
        && time.trim().length() != 0) {
      try {
        Integer.parseInt(time);
      } catch (NumberFormatException e) {
        errorMessage.setText("Time Must Be Integer");
        nameInsert.setText("");
        timeDelete.setText("");
      }
    }
  }

  /**
   * set the text when text box is empty.
   */
  public void setInsertToEmpty() {
    nameInsert.setText("");
    timeInsert.setText("");
    insertX.setText("");
    insertY.setText("");
    insertR.setText("");
    insertG.setText("");
    insertB.setText("");
    insertW.setText("");
    insertH.setText("");
  }

  /**
   * check if the insertion is empty, and if it is, send an error message.
   *
   * @param name name of the action
   * @param time time of the action
   * @param x    x coordinate of the position
   * @param y    y coordinate of the position
   * @param r    color component of the redness
   * @param g    color component of the greenness
   * @param b    color component of the blueness
   * @param w    width of the action
   * @param h    height of the action
   */
  public void checkInsert(String name, String time, String x, String y, String r, String g,
      String b, String w, String h) {
    List<String> list = new ArrayList<>(Arrays.asList(name, time, x, y, r, g, b, w, h));
    boolean temp = true;
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).trim().length() == 0) {
        temp = false;
        errorMessage.setText("Has Empty Field/Fields");
        setInsertToEmpty();
        break;
      }
    }
    if (errorMessage.getText().trim().length() != 0 && temp == true) {
      errorMessage.setText("");
      try {
        Integer.parseInt(time);
        Integer.parseInt(x);
        Integer.parseInt(y);
        Integer.parseInt(r);
        Integer.parseInt(g);
        Integer.parseInt(b);
        Integer.parseInt(w);
        Integer.parseInt(h);
      } catch (NumberFormatException a) {
        setInsertToEmpty();
        errorMessage.setText("Need Integer here");
      }
    }
    if (errorMessage.getText().trim().length() == 0 && temp == true) {
      errorMessage.setText("");
      try {
        Integer.parseInt(time);
        Integer.parseInt(x);
        Integer.parseInt(y);
        Integer.parseInt(r);
        Integer.parseInt(g);
        Integer.parseInt(b);
        Integer.parseInt(w);
        Integer.parseInt(h);
      } catch (NumberFormatException a) {
        setInsertToEmpty();
        errorMessage.setText("Need Integer Here");
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == start) {
      panel.startTimer();
      speed.setText("Speed:" + panel.getSpeed());
    } else if (e.getSource() == stop) {
      panel.stopTimer();
    } else if (e.getSource() == resume) {
      panel.resumeTimer();
    } else if (e.getSource() == restart) {
      panel.restartTimer();
    } else if (e.getSource() == loop) {
      panel.loopEnableDisable();
      if (loop.getText() == "Disable") {
        loop.setText("Enable");
      } else {
        loop.setText("Disable");
      }
    } else if (e.getSource() == increase) {
      int currentSpeed = panel.getSpeed();
      if (currentSpeed <= 990) {
        panel.changeSpeed(currentSpeed + 10);
        speed.setText("Speed:" + panel.getSpeed());
      }
      if (currentSpeed == 1) {
        panel.changeSpeed(10);
        speed.setText("Speed: 10");
      }

    } else if (e.getSource() == decrease) {
      int currentSpeed = panel.getSpeed();
      if (currentSpeed >= 11) {
        panel.changeSpeed(currentSpeed - 10);
        speed.setText("Speed:" + panel.getSpeed());
      }
      if (currentSpeed == 10) {
        panel.changeSpeed(1);
        speed.setText("Speed: 1");
      }
    } else if (e.getSource() == insert) {
      String name = nameInsert.getText();
      String time = timeInsert.getText();
      String x = insertX.getText();
      String y = insertY.getText();
      String r = insertR.getText();
      String g = insertG.getText();
      String b = insertB.getText();
      String w = insertW.getText();
      String h = insertH.getText();
      checkInsert(name, time, x, y, r, g, b, w, h);
      if (errorMessage.getText().trim().length() == 0 && name.trim().length() != 0
          && time.trim().length() != 0 && x.trim().length() != 0 && y.trim().length() != 0
          && r.trim().length() != 0 && g.trim().length() != 0 && b.trim().length() != 0
          && w.trim().length() != 0 && h.trim().length() != 0) {
        try {
          ShapeType type = animation.getShapeType(name);
          ShapeCreator creator = new ShapeCreator();
          Shape s = creator.create(type, new Posn(Integer.parseInt(x), Integer.parseInt(y)),
              new Color(Integer.parseInt(g), Integer.parseInt(g),
                  Integer.parseInt(b)),
              Integer.parseInt(w), Integer.parseInt(h));
          Frame f = new Frame(s, Integer.parseInt(time));
          panel.addKeyFrame(name, f);
        } catch (IllegalArgumentException erro) {
          errorMessage.setText("Cannot found the shape");
        }
      }
    } else if (e.getSource() == delete) {
      String name = nameInsert.getText();
      String time = timeDelete.getText();
      checkEmptyDelte(name, time);
      if (name.trim().length() != 0 && time.trim().length() != 0
          && errorMessage.getText().trim().length() == 0) {
        try {
          panel.deleteKeyFrame(name, Integer.parseInt(time));
          nameInsert.setText("");
          timeDelete.setText("");
        } catch (IllegalArgumentException a) {
          errorMessage.setText("Frame Not Found");
        }
      }
    }
  }
}
