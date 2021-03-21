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
        "birthday",
        "gender",
        "name",
        "natid",
        "salary",
        "tax"
})
public class HeroRecord {
    @JsonProperty("birthday")
    private String birthday;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("name")
    private String name;
    @JsonProperty("natid")
    private String natid;
    @JsonProperty("salary")
    private String salary;
    @JsonProperty("tax")
    private String tax;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("birthday")
    public String getBirthday() {
        return birthday;
    }

    @JsonProperty("birthday")
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("natid")
    public String getNatid() {
        return natid;
    }

    @JsonProperty("natid")
    public void setNatid(String natid) {
        this.natid = natid;
    }

    @JsonProperty("salary")
    public String getSalary() {
        return salary;
    }

    @JsonProperty("salary")
    public void setSalary(String salary) {
        this.salary = salary;
    }

    @JsonProperty("tax")
    public String getTax() {
        return tax;
    }

    @JsonProperty("tax")
    public void setTax(String tax) {
        this.tax = tax;
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
