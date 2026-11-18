
package com.vms.backend.modules.vehicles.controller;

import com.vms.backend.modules.vehicles.dto.VehicleRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import com.vms.backend.common.response.ApiResponse;
import com.vms.backend.modules.vehicles.dto.VehicleResponse;
import com.vms.backend.modules.vehicles.service.VehicleService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // GET: Retrieve all vehicles
    @GetMapping
    public ApiResponse<List<VehicleResponse>> getAllVehicles() {

        List<VehicleResponse> vehicles = vehicleService.getAllVehicles();

        return ApiResponse.success(
                "Vehicles retrieved successfully.",
                vehicles);
    }

    // POST: Register a new vehicle
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<VehicleResponse> createVehicle(
            @Valid @RequestBody VehicleRequest request) {

        VehicleResponse vehicle = vehicleService.createVehicle(request);

        return ApiResponse.success(
                "Vehicle registered successfully.",
                vehicle);
    }

    // PUT: Update an existing vehicle
    @PutMapping("/{id}")
    public ApiResponse<VehicleResponse> updateVehicle(
            @PathVariable Long id,
            @Valid @RequestBody VehicleRequest request) {

        VehicleResponse vehicle = vehicleService.updateVehicle(id, request);

        return ApiResponse.success(
                "Vehicle updated successfully.",
                vehicle);
    }

    // DELETE: Remove a vehicle
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteVehicle(@PathVariable Long id) {

        vehicleService.deleteVehicle(id);

        return ApiResponse.success(
                "Vehicle deleted successfully.",
                null);
    }
}
