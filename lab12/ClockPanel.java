import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClockPanel extends JPanel {

    private JLabel lblTime;
    private JLabel lblDate;

    private static final Color COLOR_NORMAL  = new Color(237, 237, 240);
    private static final Color COLOR_RINGING = new Color(252, 129, 129);
    private static final Color COLOR_MUTED   = new Color(140, 140, 165);
    private static final Color BG            = new Color(18, 18, 28);

    private static final DateTimeFormatter TIME_FMT =
        DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter DATE_FMT =
        DateTimeFormatter.ofPattern("yyyy.MM.dd  EEEE",
            java.util.Locale.forLanguageTag("mn"));

    // ── Конструктор ─────────────────────────────────────────────
    public ClockPanel() {
        setBackground(BG);
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(0, 0, 24, 0));
        buildComponents();
    }

    private void buildComponents() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.fill  = GridBagConstraints.HORIZONTAL;

        lblDate = new JLabel("", SwingConstants.CENTER);
        lblDate.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblDate.setForeground(COLOR_MUTED);
        gc.gridy  = 0;
        gc.insets = new Insets(0, 0, 6, 0);
        add(lblDate, gc);

        lblTime = new JLabel("00:00:00", SwingConstants.CENTER);
        lblTime.setFont(new Font("Monospaced", Font.BOLD, 72));
        lblTime.setForeground(COLOR_NORMAL);
        gc.gridy  = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        add(lblTime, gc);
    }

    // ── Цагийг шинэчлэх (Timer-аас дуудагдана) ─────────────────
    public void updateTime() {
        LocalDateTime now = LocalDateTime.now();
        lblTime.setText(now.format(TIME_FMT));
        lblDate.setText(now.format(DATE_FMT));
    }

    // ── Сэрүүлэг дуугарахад улаан болгох ───────────────────────
    public void setRingingStyle(boolean ringing) {
        lblTime.setForeground(ringing ? COLOR_RINGING : COLOR_NORMAL);
    }
}
