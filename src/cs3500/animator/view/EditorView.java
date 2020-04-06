package cs3500.animator.view;

import cs3500.animation.model.Animation;
import cs3500.animation.model.Motion;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class EditorView extends JFrame implements View, ActionListener {

  JButton start;
  JButton stop;
  JButton resume;
  JButton restart;
  JButton loop;

  JPanel buttonPanel;
  VisualPanel panel;
  Animation<List<Motion>> model;
  int tickPerSec;

  public EditorView(Animation<List<Motion>> model, int tickPerSec) {
    super();
    this.model = model;
    this.tickPerSec = tickPerSec;

    this.setTitle("Animation player");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    panel = new VisualPanel(model, tickPerSec);
    this.add(panel);

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    start = new JButton("Start");
    start.addActionListener(this);
    buttonPanel.add(start);

    stop = new JButton("Stop");
    stop.addActionListener(this);
    buttonPanel.add(stop, BorderLayout.SOUTH);

    resume = new JButton("Resume");
    resume.addActionListener(this);
    buttonPanel.add(resume, BorderLayout.EAST);

    restart = new JButton("Restart");
    restart.addActionListener(this);
    buttonPanel.add(restart, BorderLayout.NORTH);

    loop = new JButton("Disable");
    loop.addActionListener(this);
    buttonPanel.add(loop, BorderLayout.NORTH);
    pack();
    this.setBounds(model.getBox());
  }


  @Override
  public void refresh() {
  }

  @Override
  public void display() throws IOException {
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == start) {
      panel.startTimer();
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
    }
  }
}
