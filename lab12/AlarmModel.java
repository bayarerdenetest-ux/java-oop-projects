import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AlarmModel {

    private LocalTime alarmTime  = null;
    private boolean   isSet      = false;
    private boolean   isRinging  = false;

    private static final DateTimeFormatter FMT =
        DateTimeFormatter.ofPattern("HH:mm:ss");

    // ── Сэрүүлэг тохируулах ────────────────────────────────────
    public void setAlarm(int hour, int minute, int second) {
        this.alarmTime = LocalTime.of(hour, minute, second);
        this.isSet     = true;
        this.isRinging = false;
    }

    // ── Сэрүүлэг арилгах ───────────────────────────────────────
    public void clearAlarm() {
        this.alarmTime = null;
        this.isSet     = false;
        this.isRinging = false;
    }

    // ── Дуугарахыг зогсоох ─────────────────────────────────────
    public void stopRinging() {
        this.isSet     = false;
        this.isRinging = false;
        this.alarmTime = null;
    }

    // ── Одоогийн цагтай харьцуулж шалгах ───────────────────────
    public boolean shouldTriggerNow() {
        if (!isSet || isRinging || alarmTime == null) return false;
        LocalTime now = LocalTime.now().withNano(0);
        return now.equals(alarmTime);
    }

    // ── Trigger хийх (байдал өөрчлөх) ──────────────────────────
    public void triggerAlarm() {
        this.isRinging = true;
        this.isSet     = false;
    }

    // ── Өнгөрсөн цаг эсэхийг шалгах ───────────────────────────
    public boolean isAlarmInPast() {
        if (alarmTime == null) return false;
        return alarmTime.isBefore(LocalTime.now());
    }

    // ── Getters ─────────────────────────────────────────────────
    public LocalTime getAlarmTime()  { return alarmTime;  }
    public boolean   isSet()         { return isSet;       }
    public boolean   isRinging()     { return isRinging;   }

    public String getAlarmTimeString() {
        return alarmTime != null ? alarmTime.format(FMT) : "--:--:--";
    }
}
