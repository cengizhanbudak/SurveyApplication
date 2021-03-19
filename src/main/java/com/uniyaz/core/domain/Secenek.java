package com.uniyaz.core.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Secenek")
public class Secenek extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ad", nullable = false, length = 100)
    @NotNull
    private String cevap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SORU", foreignKey = @ForeignKey(name = "FK_SECENEK_SORU"))
    private Soru soru;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ANKET", foreignKey = @ForeignKey(name = "FK_ANKET_SORU_ANKET"))
    private Anket anket;

    public Anket getAnket() {
        return anket;
    }

    public void setAnket(Anket anket) {
        this.anket = anket;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCevap() {
        return cevap;
    }

    public void setCevap(String cevap) {
        this.cevap = cevap;
    }

    public Soru getSoru() {
        return soru;
    }

    public void setSoru(Soru sou) {
        this.soru = sou;
    }
}
