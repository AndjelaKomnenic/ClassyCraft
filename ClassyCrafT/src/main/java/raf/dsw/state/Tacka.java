package raf.dsw.state;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Tacka
{
    public Tacka()
    {

    }

    public int x;
    public int y;

    public Tacka dodajVektor(int x1, int y1)
    {
        return new Tacka(x + x1, y + y1);
    }
}