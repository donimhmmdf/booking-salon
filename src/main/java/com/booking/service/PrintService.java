package com.booking.service;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import java.util.List;

public class PrintService {

  public static void printMenu(String title, String[] menuArr) {
    int num = 1;
    System.out.println(title);
    for (int i = 0; i < menuArr.length; i++) {
      if (i == (menuArr.length - 1)) {
        num = 0;
      }
      System.out.println(num + ". " + menuArr[i]);
      num++;
    }
  }

  public String printServices(List<Service> serviceList) {
    String result = "";
    // Bisa disesuaikan kembali
    for (Service service : serviceList) {
      result += service.getServiceName() + ", ";
    }
    return result;
  }

  public void showServices(List<Service> serviceList) {
    int num = 1;
    System.out.println(
      "+============================================================+"
    );
    System.out.printf(
      "| %-4s | %-10s | %-20s | %-15s |\n",
      "No",
      "ID",
      "Nama",
      "Harga"
    );
    System.out.println(
      "+============================================================+"
    );
    for (Service service : serviceList) {
      System.out.printf(
        "| %-4s | %-10s | %-20s | %-15s |\n",
        num++,
        service.getServiceId(),
        service.getServiceName(),
        service.getPrice()
      );
    }
    System.out.println(
      "+============================================================+"
    );
  }

  public void showRecentReservation(List<Reservation> reservationList) {
    int num = 1;
    System.out.println(
      "+===========================================================================================================================================+"
    );
    System.out.printf(
      "| %-4s | %-10s | %-15s | %-50s | %-15s | %-15s | %-10s |\n",
      "No.",
      "ID",
      "Nama Customer",
      "Service",
      "Biaya Service",
      "Pegawai",
      "Workstage"
    );
    System.out.println(
      "+===========================================================================================================================================+"
    );
    for (Reservation reservation : reservationList) {
      if (
        reservation.getWorkstage().equalsIgnoreCase("Waiting") ||
        reservation.getWorkstage().equalsIgnoreCase("In process")
      ) {
        System.out.printf(
          "| %-4s | %-10s | %-15s | %-50s | %-15s | %-15s | %-10s |\n",
          num++,
          reservation.getReservationId(),
          reservation.getCustomer().getName(),
          printServices(reservation.getServices()),
          reservation.getReservationPrice(),
          reservation.getEmployee().getName(),
          reservation.getWorkstage()
        );
      }
    }
    System.out.println(
      "+===========================================================================================================================================+"
    );
  }

  public void showAllCustomer(List<Person> personList) {
    int num = 1;
    System.out.println(
      "+=========================================================================================+"
    );
    System.out.printf(
      "| %-8s | %-8s | %-11s | %-15s | %-15s | %-15s |\n",
      "No.",
      "ID",
      "Nama",
      "Alamat",
      "Membership",
      "Uang"
    );
    System.out.println(
      "+=========================================================================================+"
    );
    for (Person person : personList) {
      if (person instanceof Customer) {
        System.out.printf(
          "| %-8s | %-8s | %-11s | %-15s | %-15s | %-15s |\n",
          num++,
          ((Customer) person).getId(),
          ((Customer) person).getName(),
          ((Customer) person).getAddress(),
          ((Customer) person).getMember().getMembershipName(),
          ((Customer) person).getWallet()
        );
      }
    }
    System.out.println(
      "+=========================================================================================+"
    );
  }

  public void showAllEmployee(List<Person> personList) {
    int num = 1;
    System.out.println(
      "+=======================================================================+"
    );
    System.out.printf(
      "| %-8s | %-8s | %-11s | %-15s | %-15s |\n",
      "No",
      "ID",
      "Nama",
      "Alamat",
      "Pengalaman"
    );
    System.out.println(
      "+=======================================================================+"
    );
    for (Person person : personList) {
      if (person instanceof Employee) {
        System.out.printf(
          "| %-8s | %-8s | %-11s | %-15s | %-15s |\n",
          num++,
          ((Employee) person).getId(),
          ((Employee) person).getName(),
          ((Employee) person).getAddress(),
          ((Employee) person).getExperience()
        );
      }
    }
    System.out.println(
      "+=======================================================================+"
    );
  }

  public void showHistoryReservation(List<Reservation> reservationList) {
    int num = 1;
    int totalKeuntungan = 0;
    System.out.println(
      "+================================================================================================================================+"
    );
    System.out.printf(
      "| %-8s | %-8s | %-15s | %-50s | %-15s | %-15s |\n",
      "No",
      "ID",
      "Nama Customer",
      "Service",
      "Total Biaya",
      "Workstage"
    );
    System.out.println(
      "+================================================================================================================================+"
    );
    for (Reservation reservation : reservationList) {
      if (reservation.getWorkstage().equalsIgnoreCase("Finish")) {
        totalKeuntungan += reservation.getReservationPrice();
        System.out.printf(
          "| %-8s | %-8s | %-15s | %-50s | %-15s | %-15s |\n",
          num++,
          reservation.getReservationId(),
          reservation.getCustomer().getName(),
          printServices(reservation.getServices()),
          reservation.getReservationPrice(),
          reservation.getWorkstage()
        );
      }
    }
    System.out.println(
      "+================================================================================================================================+"
    );
    System.out.printf(
      "| %-90s | %-33s | %n",
      "Total Keuntungan",
      "RP." + totalKeuntungan
    );
    System.out.println(
      "+================================================================================================================================+"
    );
  }

  public void showReservation(List<Reservation> reservationList) {
    int num = 1;
    System.out.println(
      "+==============================================================================================================+"
    );
    System.out.printf(
      "| %-8s | %-8s | %-15s | %-50s | %-15s |\n",
      "No",
      "ID",
      "Nama Customer",
      "Service",
      "Total Biaya"
    );
    System.out.println(
      "+==============================================================================================================+"
    );
    for (Reservation reservation : reservationList) {
      if (reservation.getWorkstage().equalsIgnoreCase("In Process")) {
        System.out.printf(
          "| %-8s | %-8s | %-15s | %-50s | %-15s |\n",
          num++,
          reservation.getReservationId(),
          reservation.getCustomer().getName(),
          printServices(reservation.getServices()),
          reservation.getReservationPrice()
        );
      }
    }
    System.out.println(
      "+==============================================================================================================+"
    );
  }
}
