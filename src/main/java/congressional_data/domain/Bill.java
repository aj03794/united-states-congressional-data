package congressional_data.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Bill {
    public int billNumber;
    public String createDate;
    public String updateDate;
    public String originChamber;
    public String billType;
    public String introducedDate;
    public int congress;
    public String constitutionalAuthorityStatementText;
    public int recordedVotes;
    public String committees;
    public String committeeReports;
    public String relatedBills;
    public String actions;
    @JacksonXmlElementWrapper(localName = "sponsors")
    public List<Item> sponsors;
    @JacksonXmlElementWrapper(localName = "cosponsors")
    public List<Item> cosponsors;
    public String cboCostEstimates;
    public String laws;
    public String notes;
    public String policyArea;
    public String subjects;
    public String summaries;
    public String title;
    public String titles;
    public String amendments;
    public String textVersions;
    public String latestAction;
    public String calendarNumbers;
    public String version;
}
