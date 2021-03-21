package helper;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "natid",
        "name",
        "relief"
})
public class CommuteTaxRelief {
    @JsonProperty("natid")
    private String natid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("relief")
    private String relief;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("natid")
    public String getNatid() {
        return natid;
    }

    @JsonProperty("natid")
    public void setNatid(String natid) {
        this.natid = natid;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("relief")
    public String getRelief() {
        return relief;
    }

    @JsonProperty("relief")
    public void setRelief(String relief) {
        this.relief = relief;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
