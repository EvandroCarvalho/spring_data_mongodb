package com.spring_mongodb.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Task {

    @Id
    private String id;
    @NonNull
    private String name;

}
