package com.example.ruokaohjelma.ruokaohjelma;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AteriaParameterBuilder {
    private boolean aamiainenAny;
    private boolean paivallinenAny;
    private boolean lounasAny;
    private boolean illallinenAny;
}
