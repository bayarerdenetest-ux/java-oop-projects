import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class AlarmPanel extends JPanel {

    private JSpinner spinHour;
    private JSpinner spinMinute;
    private JSpinner spinSecond;
    private JLabel   lblStatus;
    private JPanel   pnlLight;     // байдлын дугуй

    // Өнгүүд
    static final Color BG_CARD      = new Color(28, 28, 45);
    static final Color BG_DARK      = new Color(18, 18, 28);
    static final Color TEXT_PRIMARY = new Color(237, 237, 240);
    static final Color TEXT_MUTED   = new Color(140, 140, 165);
    static final Color COLOR_GREEN  = new Color(72, 199, 142);
    static final Color COLOR_RED    = new Color(252, 129, 129);
    static final Color COLOR_IDLE   = new Color(70, 70, 100);

    // ── Конструктор ─────────────────────────────────────────────
    public AlarmPanel() {
        setBackground(BG_DARK);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JPanel card = buildCard();
        add(card, BorderLayout.CENTER);
    }

    private JPanel buildCard() {
        JPanel card = new JPanel(new BorderLayout(0, 16));
        card.setBackground(BG_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(55, 55, 80), 1, true),
            BorderFactory.createEmptyBorder(20, 24, 20, 24)
        ));

        card.add(buildHeader(),  BorderLayout.NORTH);
        card.add(buildSpinRow(), BorderLayout.CENTER);
        card.add(buildStatus(),  BorderLayout.SOUTH);
        return card;
    }

    // ── Гарчиг + байдлын гэрэл ──────────────────────────────────
    private JPanel buildHeader() {
        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setBackground(BG_CARD);

        JLabel title = new JLabel("Сэрүүлэг тохируулах");
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        title.setForeground(TEXT_PRIMARY);

        pnlLight = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getLightColor());
                g2.fillOval(0, 0, getWidth(), getHeight());
            }
        };
        pnlLight.setPreferredSize(new Dimension(18, 18));
        pnlLight.setBackground(BG_CARD);
        pnlLight.putClientProperty("state", "idle");

        pnl.add(title,    BorderLayout.WEST);
        pnl.add(pnlLight, BorderLayout.EAST);
        return pnl;
    }

    // ── Spinner мөр ─────────────────────────────────────────────
    private JPanel buildSpinRow() {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        row.setBackground(BG_CARD);

        spinHour   = makeSpinner(0, 23, java.time.LocalTime.now().getHour());
        spinMinute = makeSpinner(0, 59, java.time.LocalTime.now().getMinute());
        spinSecond = makeSpinner(0, 59, 0);

        row.add(wrapSpinner(spinHour,   "цаг"));
        row.add(colonLabel());
        row.add(wrapSpinner(spinMinute, "мин"));
        row.add(colonLabel());
        row.add(wrapSpinner(spinSecond, "сек"));
        return row;
    }

    // ── Статус текст ─────────────────────────────────────────────
    private JLabel buildStatus() {
        lblStatus = new JLabel("Сэрүүлэг тохируулаагүй байна", SwingConstants.CENTER);
        lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblStatus.setForeground(TEXT_MUTED);
        return lblStatus;
    }

    // ── Байдлыг шинэчлэх (Controller дуудана) ───────────────────
    public void setStateIdle() {
        lblStatus.setText("Сэрүүлэг тохируулаагүй байна");
        lblStatus.setForeground(TEXT_MUTED);
        pnlLight.putClientProperty("state", "idle");
        pnlLight.repaint();
    }

    public void setStateSet(String timeStr) {
        lblStatus.setText("✓  " + timeStr + " -д тохируулагдлаа");
        lblStatus.setForeground(COLOR_GREEN);
        pnlLight.putClientProperty("state", "set");
        pnlLight.repaint();
    }

    public void setStateRinging() {
        lblStatus.setText("🔔  СЭРҮҮЛЭГ ДУУГАРЧ БАЙНА!");
        lblStatus.setForeground(COLOR_RED);
        pnlLight.putClientProperty("state", "ringing");
        pnlLight.repaint();
    }

    public void setStateCleared() {
        lblStatus.setText("Сэрүүлэг арилгагдлаа");
        lblStatus.setForeground(TEXT_MUTED);
        pnlLight.putClientProperty("state", "idle");
        pnlLight.repaint();
    }

    // ── Оролтыг унших ───────────────────────────────────────────
    public int getSelectedHour()   { return (int) spinHour.getValue();   }
    public int getSelectedMinute() { return (int) spinMinute.getValue(); }
    public int getSelectedSecond() { return (int) spinSecond.getValue(); }

    // ── Допомогч методууд ────────────────────────────────────────
    private Color getLightColor() {
        String state = (String) pnlLight.getClientProperty("state");
        if ("ringing".equals(state)) return COLOR_RED;
        if ("set"    .equals(state)) return COLOR_GREEN;
        return COLOR_IDLE;
    }

    private JSpinner makeSpinner(int min, int max, int val) {
        JSpinner sp = new JSpinner(new SpinnerNumberModel(val, min, max, 1));
        JSpinner.NumberEditor ed = new JSpinner.NumberEditor(sp, "00");
        sp.setEditor(ed);
        sp.setPreferredSize(new Dimension(72, 48));
        sp.setFont(new Font("Monospaced", Font.BOLD, 24));
        sp.setBorder(new LineBorder(new Color(60, 60, 90), 1, true));

        JFormattedTextField tf = ed.getTextField();
        tf.setBackground(BG_DARK);
        tf.setForeground(TEXT_PRIMARY);
        tf.setCaretColor(new Color(99, 179, 237));
        tf.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 4));
        tf.setHorizontalAlignment(SwingConstants.CENTER);
        return sp;
    }

    private JPanel wrapSpinner(JSpinner sp, String label) {
        JPanel p = new JPanel(new BorderLayout(0, 4));
        p.setBackground(BG_CARD);
        JLabel lbl = new JLabel(label, SwingConstants.CENTER);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lbl.setForeground(TEXT_MUTED);
        p.add(sp,  BorderLayout.CENTER);
        p.add(lbl, BorderLayout.SOUTH);
        return p;
    }

    private JLabel colonLabel() {
        JLabel lbl = new JLabel(":", SwingConstants.CENTER);
        lbl.setFont(new Font("Monospaced", Font.BOLD, 32));
        lbl.setForeground(TEXT_MUTED);
        lbl.setBorder(BorderFactory.createEmptyBorder(0, 2, 14, 2));
        return lbl;
    }
}
