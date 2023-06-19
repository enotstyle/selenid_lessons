package api.homework.model.list_users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListUsers {

    private Integer page;

    @JsonProperty("per_page")
    private Integer perPage;

    private Integer total;

    @JsonProperty("total_pages")
    private Integer totalPages;

    private SupportUsers support;
    private ArrayList<DataUsers> data;


}

