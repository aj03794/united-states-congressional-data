package congressional_data.domain;

import congressional_data.domain.Party;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Item {
    private String bioguideId;
    private String fullName;
    private String firstName;
    private String middleName;
    private String lastName;
    private Party party;
    private String state;
    private String identifiers;
    private String district;
    private String byRequestType;
    private String sponsorshipDate;
    private Boolean isOriginalCosponsor;
    private String sponsorshipWithdrawnDate;
}
