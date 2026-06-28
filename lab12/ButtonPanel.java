import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class ButtonPanel extends JPanel {

    public static final String CMD_SET   = "SET";
    public static final String CMD_STOP  = "STOP";
    public static final String CMD_CLEAR = "CLEAR";

    private JButton btnSet;
    private JButton btnStop;
    private JButton btnClear;

    private static final Color BG          = new Color(18, 18, 28);
    private static final Color COLOR_BLUE  = new Color(99, 179, 237);
    private static final Color COLOR_AMBER = new Color(251, 191,  36);
    private static final Color COLOR_RED   = new Color(252, 129, 129);

    // ── Конструктор ─────────────────────────────────────────────
    public ButtonPanel(ActionListener listener) {
        setBackground(BG);
        setLayout(new GridLayout(1, 3, 12, 0));

        btnSet   = createButton("Тохируулах", COLOR_BLUE,  CMD_SET,   listener);
        btnStop  = createButton("Зогсоох",    COLOR_AMBER, CMD_STOP,  listener);
        btnClear = createButton("Арилгах",    COLOR_RED,   CMD_CLEAR, listener);

        add(btnSet);
        add(btnStop);
        add(btnClear);

        // Эхлэх байдал
        btnStop .setEnabled(false);
        btnClear.setEnabled(false);
    }

    // ── Байдлыг шинэчлэх ────────────────────────────────────────
    public void setStateIdle() {
        btnSet  .setEnabled(true);
        btnStop .setEnabled(false);
        btnClear.setEnabled(false);
    }

    public void setStateSet() {
        btnSet  .setEnabled(true);
        btnStop .setEnabled(false);
        btnClear.setEnabled(true);
    }

    public void setStateRinging() {
        btnSet  .setEnabled(false);
        btnStop .setEnabled(true);
        btnClear.setEnabled(false);
    }

    // ── Товч үүсгэх ─────────────────────────────────────────────
    private JButton createButton(String text, Color accent,
                                 String cmd, ActionListener listener) {
        JButton btn = new JButton(text);
        btn.setActionCommand(cmd);
        btn.addActionListener(listener);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setForeground(accent);
        btn.setBackground(dimColor(accent, 6));
        btn.setBorder(new LineBorder(accent, 1, true));
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(130, 42));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (btn.isEnabled()) btn.setBackground(dimColor(accent, 3));
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(dimColor(accent, 6));
            }
        });
        return btn;
    }

    private Color dimColor(Color c, int divisor) {
        return new Color(c.getRed()   / divisor,
                         c.getGreen() / divisor,
                         c.getBlue()  / divisor);
    }
}
