package application.model;

import java.io.Serializable;

public abstract class Rabat implements Serializable {
    public abstract double getRabat(double pris);
}
