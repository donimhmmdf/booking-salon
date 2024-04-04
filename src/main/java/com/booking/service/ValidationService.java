package com.booking.service;

import java.util.List;
import java.util.Scanner;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class ValidationService {

  // Buatlah function sesuai dengan kebutuhan
  public String validateInput(Scanner scanner, String regex) {
    String input;
    do {
      input = scanner.nextLine();
      if (!input.matches(regex)) {
        System.out.println("Invalid input. Please enter again: ");
      }
    } while (!input.matches(regex));
    return input;
  }

  public boolean validateCustomerId(String customerId, List<Person> listPerson) {
    boolean isDataExist = false;
    for (Person person : listPerson) {
        if (person instanceof Customer) {
            isDataExist = listPerson.stream().anyMatch(data -> data.getId().equalsIgnoreCase(customerId));
        }
    }
    return isDataExist;
  }

  public boolean validateEmployeeId(String employeeId, List<Person> listPerson){
    boolean isDataExist = false;
    for (Person person : listPerson) {
        if (person instanceof Employee) {
            isDataExist = listPerson.stream().anyMatch(data -> data.getId().equalsIgnoreCase(employeeId));
        }
    }
    return isDataExist;
  }
  public boolean validateServiceId(String serviceId, List<Service> listService){
    boolean isDataExist = false;
    isDataExist = listService.stream().anyMatch(data -> data.getServiceId().equalsIgnoreCase(serviceId));
    return isDataExist;
  }
  public boolean validateReservationId(String reservationId, List<Reservation> listReservation){
    boolean isDataExist = false;
    isDataExist = listReservation.stream().anyMatch(data -> data.getReservationId().equalsIgnoreCase(reservationId));
    return isDataExist;
  }
  public boolean validateWorkstage(String choice, List<Reservation> listReservation){
    boolean isDataFinish = false;
    isDataFinish = listReservation.stream().anyMatch(data -> !data.getWorkstage().equalsIgnoreCase("In Process"));
    return isDataFinish;
  }
}
