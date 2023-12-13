package MEating.domain;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Enumerated(EnumType.STRING)
    private Status status;

    private String regUser;
    private LocalDateTime regDtm;
    private String updUser;
    private LocalDateTime updDtm;
}
