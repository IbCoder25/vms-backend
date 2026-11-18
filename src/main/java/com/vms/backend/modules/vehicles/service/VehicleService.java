package com.vms.backend.modules.vehicles.service;

import com.vms.backend.modules.vehicles.dto.VehicleRequest;
import com.vms.backend.modules.vehicles.dto.VehicleResponse;
import com.vms.backend.modules.vehicles.entity.Vehicle;
import com.vms.backend.modules.vehicles.repository.VehicleRepository;
import com.vms.backend.common.exception.BusinessException;
import com.vms.backend.common.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // READ: Get all vehicles
    public List<VehicleResponse> getAllVehicles() {

        return vehicleRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // CREATE: Register a new vehicle
    public VehicleResponse createVehicle(VehicleRequest request) {

        String vehicleNumber = request.getVehicleNumber().trim();

        boolean alreadyExists = vehicleRepository
                .existsByVehicleNumberIgnoreCase(vehicleNumber);

        if (alreadyExists) {
            throw new BusinessException("Vehicle number already exists.");
        }

        Vehicle vehicle = toEntity(request);
        vehicle.setVehicleNumber(vehicleNumber);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return toResponse(savedVehicle);
    }

    // UPDATE: Modify an existing vehicle
    public VehicleResponse updateVehicle(Long id, VehicleRequest request) {

        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Vehicle not found with ID: " + id));

        String newVehicleNumber = request.getVehicleNumber().trim();

        boolean numberUsedByAnotherVehicle = vehicleRepository
                .existsByVehicleNumberIgnoreCase(newVehicleNumber);

        if (numberUsedByAnotherVehicle
                && !existingVehicle.getVehicleNumber()
                        .equalsIgnoreCase(newVehicleNumber)) {

            throw new BusinessException("Vehicle number already exists.");
        }

        existingVehicle.setVehicleNumber(newVehicleNumber);
        existingVehicle.setCompany(request.getCompany());
        existingVehicle.setType(request.getType());
        existingVehicle.setBrand(request.getBrand());
        existingVehicle.setModel(request.getModel());
        existingVehicle.setStatus(request.getStatus());

        Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);

        return toResponse(updatedVehicle);
    }

    // DELETE: Remove a vehicle
    public void deleteVehicle(Long id) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Vehicle not found with ID: " + id));

        vehicleRepository.delete(vehicle);
    }

    // Convert VehicleRequest into Vehicle entity
    private Vehicle toEntity(VehicleRequest request) {

        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleNumber(request.getVehicleNumber());
        vehicle.setCompany(request.getCompany());
        vehicle.setType(request.getType());
        vehicle.setBrand(request.getBrand());
        vehicle.setModel(request.getModel());
        vehicle.setStatus(request.getStatus());

        return vehicle;
    }

    // Convert Vehicle entity into VehicleResponse
    private VehicleResponse toResponse(Vehicle vehicle) {

        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getVehicleNumber(),
                vehicle.getCompany(),
                vehicle.getType(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getStatus()
        );
    }
}