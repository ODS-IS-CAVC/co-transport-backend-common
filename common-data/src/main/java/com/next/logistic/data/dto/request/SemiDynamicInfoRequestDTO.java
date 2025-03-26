package com.next.logistic.data.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * セミダイナミック情報リクエストDTOクラス。
 *
 * @author Next Logistics
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false)
public class SemiDynamicInfoRequestDTO {

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime targetTime;

    private List<RequestInfoDTO> requestInfo;

}
