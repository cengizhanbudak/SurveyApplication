package core.domain;

import com.uniyaz.core.domain.Anket;
import com.uniyaz.core.domain.BaseEntity;
import com.uniyaz.core.domain.Kullanici;
import com.uniyaz.core.domain.Soru;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Cevap")
public class Cevap extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "cevap", nullable = false, length = 100)
    @NotNull
    private String cevap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ANKET", foreignKey = @ForeignKey(name = "FK_CEVAP_CEVAP_ANKET"))
    private Anket anket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SORU", foreignKey = @ForeignKey(name = "FK_CEVAP_CEVAP_SORU"))
    private Soru soru;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KULLANICI", foreignKey = @ForeignKey(name = "FK_CEVAP_CEVAP_KULLANICI"))
    private Kullanici kullanici;

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

    public Anket getAnket() {
        return anket;
    }

    public void setAnket(Anket anket) {
        this.anket = anket;
    }

    public Soru getSoru() {
        return soru;
    }

    public void setSoru(Soru soru) {
        this.soru = soru;
    }

    public Kullanici getKullanici() {
        return kullanici;
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }
}


