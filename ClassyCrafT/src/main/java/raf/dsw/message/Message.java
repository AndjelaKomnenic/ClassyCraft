package raf.dsw.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Getter
@Setter
public class Message {

    private String text;
    private String type; // greska, upozorenje, obavestenje
    private Timestamp timestamp;
    private SimpleDateFormat sdf;

    public Message(String text, String type, Timestamp timestamp) {
        this.text = text;
        this.type = type;
        this.timestamp = timestamp;
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String toString() {
        return "[" + type + "][" + timestamp + "] " + text;
    }
}
