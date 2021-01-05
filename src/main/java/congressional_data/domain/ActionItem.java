package congressional_data.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(namespace = "item")
public class ActionItem {
    public String actionDate;
    public String links;
    public String text;
    public String type;
    public String actionCode;
    public String sourceSystem;
    public String committees;
}
