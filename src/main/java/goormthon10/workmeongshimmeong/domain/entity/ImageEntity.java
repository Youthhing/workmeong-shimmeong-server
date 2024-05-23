package goormthon10.workmeongshimmeong.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "image_url", length = 500)
    private String url;

    @Column(name = "image_order")
    private Integer order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    private Program program;

    private ImageEntity(String url, Integer order, Program program) {
        this.url = url;
        this.order = order;
        this.program = program;
    }

    public static ImageEntity of(String url, Integer order, Program program) {
        return new ImageEntity(url, order, program);
    }
}
