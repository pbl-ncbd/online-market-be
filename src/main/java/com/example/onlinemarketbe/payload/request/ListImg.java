package com.example.onlinemarketbe.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@Data
public class ListImg {
    private MultipartFile[] fileImg;
}
