package cs3500.animator.view;

import cs3500.animation.model.Animation;
import cs3500.animation.model.Motion;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class EditorView extends JFrame implements View, ActionListener {

  JButton start;
  JButton stop;
  JButton resume;
  JButton restart;
  JButton loop;
  JButton increase;
  JButton decrease;
  JLabel speed;
  EditorPanel panel;

  public EditorView(Animation<List<Motion>> model, int tickPerSec) {
    super();

    this.setTitle("Animation player");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    panel = new EditorPanel(model, tickPerSec);
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
    speedPanel.add(speed, BorderLayout.NORTH);

    increase = new JButton("Increase Speed");
    increase.addActionListener(this);
    speedPanel.add(increase);

    decrease = new JButton("Decrease Speed");
    decrease.addActionListener(this);
    speedPanel.add(decrease);

    pack();
    this.setBounds(model.getBox());
  }


  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void display() throws IOException {
    this.setVisible(true);
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
    }
  }
}
