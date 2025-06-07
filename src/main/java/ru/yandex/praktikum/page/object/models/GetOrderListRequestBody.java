package ru.yandex.praktikum.page.object.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderListRequestBody {
    private Integer courierId;
    private String nearestStation;
    private Integer limit;
    private Integer page;
}
