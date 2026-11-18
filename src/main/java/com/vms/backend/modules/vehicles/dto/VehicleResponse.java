
package com.vms.backend.modules.vehicles.dto;

public class VehicleResponse {

    private Long id;
    private String vehicleNumber;
    private String company;
    private String type;
    private String brand;
    private String model;
    private String status;

    public VehicleResponse() {
    }

    public VehicleResponse(
            Long id,
            String vehicleNumber,
            String company,
            String type,
            String brand,
            String model,
            String status
    ) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.company = company;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getCompany() {
        return company;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getStatus() {
        return status;
    }
}
