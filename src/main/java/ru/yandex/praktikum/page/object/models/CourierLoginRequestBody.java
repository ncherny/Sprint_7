package ru.yandex.praktikum.page.object.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourierLoginRequestBody {
    private String login;
    private String password;
}
