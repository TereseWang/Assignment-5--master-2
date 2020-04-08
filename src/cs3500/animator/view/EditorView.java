package cs3500.animator.view;

import cs3500.animation.model.KeyFrameAnimation;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

  public void checkInsert(String name, String time, String x, String y, String r, String g,
      String b) {
    if (name.trim().length() == 0 && time.trim().length() != 0 && x.trim().length() != 0
        && y.trim().length() != 0 && r.trim().length() != 0 && g.trim().length() != 0) {
      errorMessage.setText("");
    }
    if (name.trim().length() == 0 && time.trim().length() != 0 && x.trim().length() != 0
            && y.trim().length() != 0 && r.trim().length() != 0 && g.trim().length() != 0) {
          errorMessage.setText("");
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
