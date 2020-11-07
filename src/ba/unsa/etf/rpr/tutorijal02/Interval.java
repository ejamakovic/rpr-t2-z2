package ba.unsa.etf.rpr.tutorijal02;

import java.util.Objects;

public class Interval {
    private double pocetnaTacka,krajnjaTacka;
    private boolean pocetnaPripada,krajnjaPripada;

    public Interval(double prvaTacka,double drugaTacka,boolean prvaPripada,boolean drugaPripada){
        if(prvaTacka>drugaTacka)
            throw new IllegalArgumentException("Pocetna tacka je veca od krajnje!");
        this.pocetnaTacka=prvaTacka;
        this.krajnjaTacka=drugaTacka;
        this.pocetnaPripada=prvaPripada;
        this.krajnjaPripada=drugaPripada;
    }

    public Interval(){}

    public boolean isNull(){
        return pocetnaTacka==0 && krajnjaTacka==0 && !pocetnaPripada && !krajnjaPripada;
    }

    public boolean isIn(double a){
        if(this.isNull()) return false;
        if(a==pocetnaTacka && pocetnaPripada) return true;
        if(a==krajnjaTacka && krajnjaPripada) return true;
        return a>pocetnaTacka && a<krajnjaTacka;
    }

    public Interval intersect(Interval interval){
        Interval presjek = new Interval();
        if(this.isNull() || interval.isNull()) return presjek;
        Interval manji = this, veci = interval;

        if(Math.abs(interval.krajnjaTacka)-Math.abs(interval.pocetnaTacka) < Math.abs(this.krajnjaTacka)-Math.abs(this.pocetnaTacka)) {
            veci = this;
            manji = interval;
        }

        if(veci.isIn(manji.pocetnaTacka)){
            presjek.pocetnaTacka=manji.pocetnaTacka;
            if(manji.pocetnaPripada) presjek.pocetnaPripada=true;
        }
        else if(manji.isIn(veci.pocetnaTacka)){
            presjek.pocetnaTacka=veci.pocetnaTacka;
            if(veci.pocetnaPripada) presjek.pocetnaPripada=true;
        }
        else return presjek;

        if(veci.isIn(manji.krajnjaTacka)){
            presjek.krajnjaTacka=manji.krajnjaTacka;
            if(manji.krajnjaPripada) presjek.krajnjaPripada=true;
        }
        else if(manji.isIn(veci.krajnjaTacka)){
            presjek.krajnjaTacka=veci.krajnjaTacka;
            if(veci.krajnjaPripada) presjek.krajnjaPripada=true;
        }
        else return new Interval();

        return presjek;
    }

    public static Interval intersect(Interval i1,Interval i2){
        return i1.intersect(i2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return Double.compare(interval.pocetnaTacka, pocetnaTacka) == 0 &&
                Double.compare(interval.krajnjaTacka, krajnjaTacka) == 0 &&
                pocetnaPripada == interval.pocetnaPripada &&
                krajnjaPripada == interval.krajnjaPripada;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pocetnaTacka, krajnjaTacka, pocetnaPripada, krajnjaPripada);
    }

    @Override
    public String toString() {
        if(this.isNull()) return "()";
        String ispis ="";
        if(pocetnaPripada) ispis+="[";
        else ispis+="(";
        ispis+=pocetnaTacka + "," + krajnjaTacka;
        if(krajnjaPripada) ispis+="]";
        else ispis+=")";
        return ispis;
    }
}
