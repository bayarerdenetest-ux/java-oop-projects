import javax.swing.*;
import java.awt.*;

public class AlarmApp {

    public static void main(String[] args) {
        // Swing-ийн EDT (Event Dispatch Thread) дээр эхлүүлэх
        SwingUtilities.invokeLater(AlarmApp::launch);
    }

    private static void launch() {
        // ── Look & Feel ─────────────────────────────────────────
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // ── MVC угсрах ──────────────────────────────────────────
        JFrame frame = buildFrame();

        // 1. Model
        AlarmModel model = new AlarmModel();

        // 2. View-үүд
        ClockPanel  clockPanel  = new ClockPanel();
        AlarmPanel  alarmPanel  = new AlarmPanel();

        // 3. Controller (ActionListener-г ButtonPanel-д дамжуулна)
        AlarmController controller = new AlarmController(
            model, clockPanel, alarmPanel, frame
        );

        ButtonPanel buttonPanel = new ButtonPanel(controller);

        // Controller-д buttonPanel-г холбох (дугуйлаас зайлсхийхийн тулд setter)
        controller.setButtonPanel(buttonPanel);

        // ── Layout ──────────────────────────────────────────────
        JPanel root = new JPanel(new BorderLayout(0, 0));
        root.setBackground(new Color(18, 18, 28));
        root.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        root.add(clockPanel,  BorderLayout.NORTH);
        root.add(alarmPanel,  BorderLayout.CENTER);
        root.add(buttonPanel, BorderLayout.SOUTH);

        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // ── Цагийн Timer эхлүүлэх ───────────────────────────────
        controller.startClock();
    }

    private static JFrame buildFrame() {
        JFrame frame = new JFrame("🔔  Сэрүүлэг — Java Swing MVC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setBackground(new Color(18, 18, 28));
        return frame;
    }
}
