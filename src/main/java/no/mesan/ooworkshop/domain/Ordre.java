package no.mesan.ooworkshop.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Ordre implements Iterable<Ordrelinje> {
    private Ordrestatus status;
    private Date bestillingsdato;
    private List<Ordrelinje> ordrelinjer;
    
    public Ordre() {
        status = Ordrestatus.NY;
        ordrelinjer = new ArrayList<Ordrelinje>();
    }
    
    public Ordrestatus getStatus() {
        return status;
    }
    public Date getBestillingsdato() {
        return bestillingsdato;
    }
    public double price() {
        double price = 0.0;
        for (Ordrelinje linje : ordrelinjer) {
            price += linje.price();
        }
        return price;
    }
    public void leggTilVare(Varenummer varenummer, int antall) {
        ordrelinjer.add(new Ordrelinje(varenummer, antall));
    }
    
    public void bestill() {
        if (status != Ordrestatus.NY) {
            throw new RuntimeException();
        }
        status = Ordrestatus.BESTILT;
        bestillingsdato = new Date();
    }

    @Override
    public Iterator<Ordrelinje> iterator() {
        return Collections.unmodifiableList(ordrelinjer).iterator();
    }
}

class Ordrelinje {
    private Varenummer varenummer;
    private int antall;
    public Ordrelinje(Varenummer varenummer, int antall) {
        this.varenummer = varenummer;
        this.antall = antall;
    }
    public Varenummer getVarenummer() {
        return varenummer;
    }
    public int getAntall() {
        return antall;
    }
    public double price() {
        return antall * varenummer.getPrice();
    }
}

class Varenummer {
    double getPrice() {
        return 1.0;
    }
}

enum Ordrestatus {
    NY,
    BESTILT,
    AVBESTILT,
    LEVERT,
    BETALT
}
