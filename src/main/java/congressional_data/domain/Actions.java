package congressional_data.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
public class Actions {
    @JacksonXmlElementWrapper(localName = "item")
    public List<ActionItem> actionItem;
}
