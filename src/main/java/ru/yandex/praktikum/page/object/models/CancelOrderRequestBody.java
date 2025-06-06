package ru.yandex.praktikum.page.object.models;

public class CancelOrderRequestBody {
    private Integer track;

    public CancelOrderRequestBody(Integer track) {
        this.track = track;
    }

    public CancelOrderRequestBody() {
    }

    public Integer getTrack() {
        return track;
    }

    public void setTrack(Integer track) {
        this.track = track;
    }
}
