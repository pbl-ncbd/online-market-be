package com.example.onlinemarketbe.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateItemRequest {
    private Integer itemId;
    private Integer quantity;
    private Integer typeId;
}
