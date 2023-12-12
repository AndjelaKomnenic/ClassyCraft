package raf.dsw.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Tacka
{
    int x;
    int y;

    public Tacka dodajVektor(int x1, int y1)
    {
        return new Tacka(x + x1, y + y1);
    }
}