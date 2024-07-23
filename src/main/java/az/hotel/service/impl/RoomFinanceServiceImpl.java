package az.hotel.service.impl;
import az.hotel.dto.request.ReqRoomFinance;
import az.hotel.dto.response.*;
import az.hotel.entity.Customer;
import az.hotel.entity.PaymentMethod;
import az.hotel.entity.RoomsFinance;
import az.hotel.entity.Rooms;
import az.hotel.enums.EnumAvailableStatus;
import az.hotel.enums.EnumRoomsAvaibility;
import az.hotel.exception.CustomerException;
import az.hotel.exception.ExceptionConstant;
import az.hotel.exception.RoomsExpection;
import az.hotel.repository.CustomerRepository;
import az.hotel.repository.PaymentMethodRepository;
import az.hotel.repository.RoomFinanceRepository;
import az.hotel.repository.RoomsRepository;
import az.hotel.service.RoomFinanceService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RoomFinanceServiceImpl implements RoomFinanceService {
    private final RoomFinanceRepository roomFinanceRepository;
    private final CustomerRepository customerRepository;
    private final RoomsRepository roomsRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    public Response<RoomFinanceResp> addRoomFinance(ReqRoomFinance reqRoomFinance) {
        Response<RoomFinanceResp> response = new Response<>();
        try {
            Long customerId = reqRoomFinance.getCustomerId();
            Long paymentMethodId = reqRoomFinance.getPaymentMethodId();
            Long roomId = reqRoomFinance.getRoomsId();
            if (customerId == null || paymentMethodId == null || roomId == null) {
                throw new CustomerException("Invalid data", ExceptionConstant.INVALID_REQUEST_DATA);
            }
            Customer customer = customerRepository.findByIdAndActivity(customerId, EnumAvailableStatus.ACTIVE.getValue());
            if (customer == null) {
                throw new CustomerException("Customer Not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            Rooms rooms = roomsRepository.findRoomsByIdAndAvaible(roomId, EnumRoomsAvaibility.IS_AVAIBLE.getValue());
            if (rooms == null) {
                throw new RoomsExpection("no rooms found", ExceptionConstant.NO_ROOMS_DETAILS);
            }
            PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId).get();
            if (paymentMethod == null) {
                throw new CustomerException("invalid payment method", ExceptionConstant.INVALID_REQUEST_DATA);
            }
            RoomsFinance roomsFinance = RoomsFinance.builder()
                    .rooms(rooms)
                    .customer(customer)
                    .paymentMethod(paymentMethod)
                    .totalPayment(reqRoomFinance.getTotalPayment())
                    .rePayment(reqRoomFinance.getRePayment())
                    .debt(reqRoomFinance.getDebt())
                    .build();
            roomFinanceRepository.save(roomsFinance);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (CustomerException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }
        return response;
    }

    @Override
    public Response<RoomFinanceResp> updateRoomFinance(ReqRoomFinance reqRoomFinance) {
        Response<RoomFinanceResp> response = new Response<>();
        try {
            Long roomFinanceId = reqRoomFinance.getId();
            if (roomFinanceId == null) {
                throw new CustomerException("Room finance record not found", ExceptionConstant.ROOM_FINANCE_NOT_FOUND);
            }

            RoomsFinance roomsFinance = roomFinanceRepository.findById(roomFinanceId)
                    .orElseThrow(() -> new CustomerException("Room finance record not found", ExceptionConstant.ROOM_FINANCE_NOT_FOUND));

            Long customerId = reqRoomFinance.getCustomerId();
            Long paymentMethodId = reqRoomFinance.getPaymentMethodId();
            Long roomId = reqRoomFinance.getRoomsId();

            if (customerId == null || paymentMethodId == null || roomId == null) {
                throw new CustomerException("Invalid data", ExceptionConstant.INVALID_REQUEST_DATA);
            }

            Customer customer = customerRepository.findByIdAndActivity(customerId, EnumAvailableStatus.ACTIVE.getValue());
            if (customer == null) {
                throw new CustomerException("Customer Not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }

            Rooms rooms = roomsRepository.findRoomsByIdAndAvaible(roomId, EnumRoomsAvaibility.IS_AVAIBLE.getValue());
            if (rooms == null) {
                throw new RoomsExpection("No rooms found", ExceptionConstant.NO_ROOMS_DETAILS);
            }

            PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId)
                    .orElseThrow(() -> new CustomerException("Invalid payment method", ExceptionConstant.INVALID_REQUEST_DATA));

            roomsFinance.setRooms(rooms);
            roomsFinance.setCustomer(customer);
            roomsFinance.setPaymentMethod(paymentMethod);
            roomsFinance.setTotalPayment(reqRoomFinance.getTotalPayment());
            roomsFinance.setRePayment(reqRoomFinance.getRePayment());
            roomsFinance.setDebt(reqRoomFinance.getDebt());
            roomFinanceRepository.save(roomsFinance);
            RoomFinanceResp roomFinanceResp = convertRoomFinance(roomsFinance);
            response.setT(roomFinanceResp);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (CustomerException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }
        return response;
    }

    @Override
    public Response<List<RoomFinanceResp>> getAllRoomFinance() {
        Response<List<RoomFinanceResp>> response = new Response<>();
        try {
            List<RoomsFinance> roomsFinances = roomFinanceRepository.findAll();
            if (roomsFinances.isEmpty()) {
                throw new CustomerException("No rooms found", ExceptionConstant.NO_ROOMS_DETAILS);
            }
            List<RoomFinanceResp> roomFinanceResps =roomsFinances.stream().map(this::convertRoomFinance).toList();
            response.setT(roomFinanceResps);
            response.setStatus(RespStatus.getSuccessMessage());
        }catch (CustomerException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }
        return response;
    }

    @Override
    public Response<RoomFinanceResp> getAllFinanceByCustomerId(Long id) {
        Response<RoomFinanceResp> response = new Response<>();
        try {
            RoomsFinance roomsFinance=roomFinanceRepository.findRoomFinanceByCustomerId(id);
            if (roomsFinance == null) {
                throw new CustomerException("No rooms found", ExceptionConstant.NO_ROOMS_DETAILS);
            }
            RoomFinanceResp roomFinanceResp=convertRoomFinance(roomsFinance);
            response.setT(roomFinanceResp);
            response.setStatus(RespStatus.getSuccessMessage());

        }catch (CustomerException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }
        return response;
    }

    @Override
    public Response<List<RoomFinanceResp>> getAllRoomFinanceByPaymentId(Long id) {
        Response<List<RoomFinanceResp>> response = new Response<>();
        try {
            if (id == null) {
                throw new CustomerException("Invalid payment id", ExceptionConstant.INVALID_REQUEST_DATA);
            }
            List<RoomsFinance> roomsFinances=roomFinanceRepository.findRoomsFinanceByPaymentMethodId(id);
            if (roomsFinances.isEmpty()) {
                throw new CustomerException("No rooms found", ExceptionConstant.NO_ROOMS_DETAILS);
            }
            List<RoomFinanceResp>roomFinanceResps=roomsFinances.stream().map(this::convertRoomFinance).toList();
            response.setT(roomFinanceResps);
            response.setStatus(RespStatus.getSuccessMessage());
        }catch (CustomerException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }
        return response;
    }

    private RoomFinanceResp convertRoomFinance(RoomsFinance roomsFinance) {
        CustomerResp customerResp = CustomerResp.builder().
                name(roomsFinance.getCustomer().getName()).
                surname(roomsFinance.getCustomer().getSurname()).
                dob(roomsFinance.getCustomer().getDob()).
                build();
        RoomsResp roomsResp = RoomsResp.builder()
                .roomsType(roomsFinance.getRooms().getRoomsType())
                .numberOfBed(roomsFinance.getRooms().getNumberOfBed())
                .priceForDay(roomsFinance.getRooms().getPriceForDay())
                .build();
        PaymentMethodResp paymentMethodResp = PaymentMethodResp.builder()
                .method(roomsFinance.getPaymentMethod().getMethod())
                .build();
        return RoomFinanceResp.builder()
                .totalPayment(roomsFinance.getTotalPayment())
                .rePayment(roomsFinance.getRePayment())
                .debt(roomsFinance.getDebt())
                .roomsResp(roomsResp)
                .customerResp(customerResp)
                .paymentMethodResp(paymentMethodResp)
                .build();
    }

}