package com.booking.service;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationService {

  private static PrintService printService = new PrintService();
  private static ValidationService validationService = new ValidationService();

  public void createReservation(
    Scanner input,
    List<Reservation> reservationList,
    List<Person> personList,
    List<Service> serviceList
  ) {
    String customerId, employeeId, serviceId, choice = "";
    List<String> selectedServiceId = new ArrayList<>();
    boolean checkCustomer, checkEmployee, checkService, backToSubMenu = false;
    do {
      printService.showAllCustomer(personList);
      do {
        System.out.println("Silahkan Masukan Customer Id:");
        customerId = validationService.validateInput(input, "[a-zA-Z0-9-]+");
        checkCustomer =
          validationService.validateCustomerId(customerId, personList);
        if (!checkCustomer) {
          System.out.println("Customer yang dicari tidak tersedia.");
        }
      } while (!checkCustomer);

      printService.showAllEmployee(personList);
      do {
        System.out.println("Silahkan Masukan Employee Id:");
        employeeId = validationService.validateInput(input, "[a-zA-Z0-9-]+");
        checkEmployee =
          validationService.validateEmployeeId(employeeId, personList);
        if (!checkEmployee) {
          System.out.println("Employee yang dicari tidak tersedia.");
        }
      } while (!checkEmployee);

      printService.showServices(serviceList);
      do {
        do {
          System.out.println("Silahkan Masukan Service Id:");
          serviceId = validationService.validateInput(input, "[a-zA-Z0-9-]+");
          checkService =
            validationService.validateServiceId(serviceId, serviceList);
          if (!checkService) {
            System.out.println("Service yang dicari tidak tersedia.");
          }
          if (selectedServiceId.contains(serviceId)) {
            System.out.println("Service sudah dipilih.");
          } else if (selectedServiceId.size() != serviceList.size()) {
            selectedServiceId.add(serviceId);
          }
        } while (!checkService);

        System.out.println("Ingin pilih service yang lain (Y/T)?");
        choice = validationService.validateInput(input, "[ytYT]+");
      } while (choice.equalsIgnoreCase("Y"));

      reservationProses(
        customerId,
        employeeId,
        selectedServiceId,
        reservationList,
        personList,
        serviceList
      );
    } while (backToSubMenu);
  }

  public void getCustomerByCustomerId() {}

  public void editReservationWorkstage() {}

  // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
  public void addAnotherService(List<Service> serviceList, String serviceId) {}

  public void reservationProses(
    String customerId,
    String employeeId,
    List<String> selectedServiceId,
    List<Reservation> reservationList,
    List<Person> personList,
    List<Service> serviceList
  ) {
    Customer listCustomer = null;
    Employee listEmployee = null;
    List<Service> selectedServices = new ArrayList<>();
    String reservationId;
    int totalBayar = 0;
    int idNumber = 1;

    for (Person person : personList) {
      if (person.getId().equalsIgnoreCase(customerId)) {
        listCustomer = (Customer) person;
      } else if (person.getId().equalsIgnoreCase(employeeId)) {
        listEmployee = (Employee) person;
      }
    }
    for (Service service : serviceList) {
      for (String selectService : selectedServiceId) {
        if (service.getServiceId().equalsIgnoreCase(selectService)) {
          selectedServices.add(service);
        }
      }
    }
    for (int i = 0; i < reservationList.size(); i++) {
        idNumber++;
    }
    reservationId = "Rsv-" + String.format("%02d", idNumber);
    reservationList.add(
      new Reservation(
        reservationId,
        listCustomer,
        listEmployee,
        selectedServices,
        "In Process"
      )
    );
    System.out.println("Booking Berhasil!");
    for (Reservation reservation : reservationList) {
      if (reservation.getReservationId().equalsIgnoreCase(reservationId)) {
        totalBayar += reservation.getReservationPrice();
      }
    }
    System.out.println("Total Biaya Booking: Rp." + totalBayar);
  }

  public void finishOrCancelReservation(
    Scanner input,
    List<Reservation> reservationList
  ) {
    String reservationId, choice;
    boolean checkReservation = false;
    printService.showReservation(reservationList);
    if (reservationList.size() != 0) {
      do {
        System.out.println("Silahkan Masukan Reservation Id:");
        reservationId = validationService.validateInput(input, "[a-zA-Z0-9-]+");
        checkReservation =
          validationService.validateReservationId(
            reservationId,
            reservationList
          );
        if (!checkReservation) {
          System.out.println("Reservation yang dicari tidak tersedia.");
        }
      } while (!checkReservation);
      do {
        System.out.println("Selesaikan reservasi(Finish/Cancel):");
        choice = validationService.validateInput(input, "[a-zA-Z]+");
        if (
          !choice.equalsIgnoreCase("finish") &&
          !choice.equalsIgnoreCase("cancel")
        ) {
          System.out.println("Input hanya bisa Finish atau Cancel.");
        }
      } while (
        !choice.equalsIgnoreCase("finish") && !choice.equalsIgnoreCase("cancel")
      );
      for (Reservation reservation : reservationList) {
        if (reservation.getReservationId().equalsIgnoreCase(reservationId)) {
          reservation.setWorkstage(choice);
          System.out.println(
            "Reservasi dengan id " +
            reservation.getReservationId() +
            " sudah " +
            choice
          );
        }
      }
    }
  }
}
