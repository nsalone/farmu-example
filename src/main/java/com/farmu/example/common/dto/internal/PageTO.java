package com.farmu.example.common.dto.internal;

import com.farmu.example.common.dto.enums.PageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
@Value
@Builder
@AllArgsConstructor
public class PageTO<T> {

    private PageType type;
    private List<T> elements;


}