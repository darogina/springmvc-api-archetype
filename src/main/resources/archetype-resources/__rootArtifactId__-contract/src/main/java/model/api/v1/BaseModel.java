#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model.api.v1;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import ${package}.model.api.ApiModel;
import org.joda.time.DateTime;

public abstract class BaseModel implements ApiModel {

    public static final String API_VERSION = "v1";

    private int version = 0;
    @JsonSerialize(using = DateTimeSerializer.class)
    private DateTime createDate;
    @JsonSerialize(using = DateTimeSerializer.class)
    private DateTime lastUpdate;
    private String createdBy;
    private String changedBy;
    private String uuid;
    private String apiVersion = API_VERSION;

    public BaseModel() {}

    public BaseModel(int version, DateTime createDate, DateTime lastUpdate, String createdBy, String changedBy, String uuid) {
        this.version = version;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
        this.createdBy = createdBy;
        this.changedBy = changedBy;
        this.uuid = uuid;
        this.apiVersion = API_VERSION;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public DateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(DateTime createDate) {
        this.createDate = createDate;
    }

    public DateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(DateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getApiVersion() {
        return apiVersion;
    }

}