package MEating.domain;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
public class BaseEntity {

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime regDtm;
    private LocalDateTime updDtm;
}
