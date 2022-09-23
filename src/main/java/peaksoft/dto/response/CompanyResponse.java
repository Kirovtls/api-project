package peaksoft.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CompanyResponse {
    private Long companyId;
    private String companyName;
    private String locatedCountry;
    private LocalDate createAt;
    private boolean isActive;
}
