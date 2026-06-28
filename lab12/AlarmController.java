import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlarmController implements ActionListener {

    private final AlarmModel  model;
    private final ClockPanel  clockPanel;
    private final AlarmPanel  alarmPanel;
    private final JFrame      parentFrame;
    private ButtonPanel       buttonPanel;   // setter-ээр авна

    private javax.swing.Timer clockTimer;
    private javax.swing.Timer beepTimer;

    public AlarmController(AlarmModel model, ClockPanel clockPanel,
                           AlarmPanel alarmPanel, JFrame parentFrame) {
        this.model       = model;
        this.clockPanel  = clockPanel;
        this.alarmPanel  = alarmPanel;
        this.parentFrame = parentFrame;
    }

    public void setButtonPanel(ButtonPanel bp) { this.buttonPanel = bp; }

    public void startClock() {
        clockTimer = new javax.swing.Timer(500, e -> onClockTick());
        clockTimer.start();
        onClockTick();
    }

    private void onClockTick() {
        clockPanel.updateTime();
        if (model.shouldTriggerNow()) {
            model.triggerAlarm();
            onAlarmTriggered();
        }
    }

    private void onAlarmTriggered() {
        alarmPanel .setStateRinging();
        buttonPanel.setStateRinging();
        clockPanel .setRingingStyle(true);

        beepTimer = new javax.swing.Timer(600,
            e -> Toolkit.getDefaultToolkit().beep());
        beepTimer.start();

        JOptionPane.showMessageDialog(parentFrame,
            "Сэрүүлэг дуугарлаа!\n" + model.getAlarmTimeString(),
            "🔔  Сэрүүлэг", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ButtonPanel.CMD_SET   -> handleSet();
            case ButtonPanel.CMD_STOP  -> handleStop();
            case ButtonPanel.CMD_CLEAR -> handleClear();
        }
    }

    private void handleSet() {
        model.setAlarm(alarmPanel.getSelectedHour(),
                       alarmPanel.getSelectedMinute(),
                       alarmPanel.getSelectedSecond());

        if (model.isAlarmInPast()) {
            int choice = JOptionPane.showConfirmDialog(parentFrame,
                "Сонгосон цаг өнгөрсөн байна.\nМаргааш "
                + model.getAlarmTimeString() + "-д тохируулах уу?",
                "Анхааруулга", JOptionPane.YES_NO_OPTION);
            if (choice != JOptionPane.YES_OPTION) { model.clearAlarm(); return; }
        }

        alarmPanel .setStateSet(model.getAlarmTimeString());
        buttonPanel.setStateSet();
    }

    private void handleStop() {
        stopBeep(); model.stopRinging();
        alarmPanel.setStateIdle(); buttonPanel.setStateIdle();
        clockPanel.setRingingStyle(false);
    }

    private void handleClear() {
        stopBeep(); model.clearAlarm();
        alarmPanel.setStateCleared(); buttonPanel.setStateIdle();
        clockPanel.setRingingStyle(false);
    }

    private void stopBeep() {
        if (beepTimer != null && beepTimer.isRunning()) beepTimer.stop();
    }
}
