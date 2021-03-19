package core.domain;


import com.uniyaz.core.domain.Anket;
import com.uniyaz.core.domain.BaseEntity;
import com.uniyaz.core.domain.EnumSecenekTuru;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SORU")
public class Soru extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SORUYAZ", nullable = false,length = 255)
    @NotNull
    private String soruyaz;

    @Column(length = 100)
    @Enumerated(EnumType.STRING)
    private EnumSecenekTuru secenek;

    @Column(name ="CEVAP", length = 100)
    private String cevap;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ANKET", foreignKey = @ForeignKey(name = "FK_ANKET_SORU_ANKET"))
    private Anket anket;



    @Override
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public EnumSecenekTuru getSecenek() {
        return secenek;
    }

    public void setSecenek(EnumSecenekTuru secenek) {
        this.secenek = secenek;
    }

    public String getCevap() { return cevap; }

    public void setCevap(String cevap) { this.cevap = cevap; }

    public String getSoruyaz() { return soruyaz; }

    public void setSoruyaz(String soruyaz) { this.soruyaz = soruyaz; }

    public Anket getAnket() { return anket; }

    public void setAnket(Anket anket) { this.anket = anket; }
}