package com.buaa.CliDB.response;

import com.buaa.CliDB.exception.BaseException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBuilder {

    private int status;

    @NonNull private String msg;

    private Object data;

    public ResponseBuilder(BaseException e) {
        this.status = e.getStatus();
        this.msg = e.getMessage();
        this.data = null;
    }

    public ResponseBuilder(ResponseStatusAndInfos responseStatusAndInfos, Object data) {
        this.status = responseStatusAndInfos.getStatus();
        this.msg = responseStatusAndInfos.getMsg();
        this.data = data;
    }

}
