package congressional_data.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@Getter
@Setter
@ToString
@XmlRootElement(name = "billStatus")
public class CongressionalBill {

    public Bill bill;
    public String dublinCore;

    public boolean isBipartisan() {
        return getAllUniqueParties().size() > 1;
    }

    public boolean isRepublican() {
        return isPartisan(getAllUniqueParties(), Party.R);
    }

    public boolean isDemocrat() {
        return isPartisan(getAllUniqueParties(), Party.D);
    }

    public boolean isIndependent() {
        return isPartisan(getAllUniqueParties(), Party.I);
    }

    private Set<Party> getAllUniqueParties() {
        Set<Party> uniqueParties = new HashSet<>();
        for (Item item: this.bill.getCosponsors()) {
            uniqueParties.add(item.getParty());
        }
        for (Item item: this.bill.getSponsors()) {
            uniqueParties.add(item.getParty());
        }
        return uniqueParties;
    }

    private boolean isPartisan(Set<Party> parties, Party party) {
        return parties.size() == 1 && parties.contains(party);
    }
}
