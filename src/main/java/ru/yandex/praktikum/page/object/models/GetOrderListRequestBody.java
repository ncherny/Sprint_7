package ru.yandex.praktikum.page.object.models;

public class GetOrderListRequestBody {
    private Integer courierId;
    private String nearestStation;
    private Integer limit;
    private Integer page;

    public GetOrderListRequestBody(Integer courierId, String nearestStation, Integer limit, Integer page) {
        this.courierId = courierId;
        this.nearestStation = nearestStation;
        this.limit = limit;
        this.page = page;
    }

    public GetOrderListRequestBody() {
    }

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public String getNearestStation() {
        return nearestStation;
    }

    public void setNearestStation(String nearestStation) {
        this.nearestStation = nearestStation;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
