package com.fitengineer.librarysystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fitengineer.librarysystem.entity.Rent;
import com.fitengineer.librarysystem.entity.UserEntry;
import com.fitengineer.librarysystem.repository.RentRepo;
import com.fitengineer.librarysystem.repository.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

public class RentReminderService {
    @Autowired
    private RentRepo rentRepo;
    @Autowired 
    private EmailService emailService;
    @Autowired
    private UserRepo userRepo;

    @Scheduled(cron = "0 0 9 * * ?", zone = "Asia/Kolkata")
    public void sendoverdateEmail(){
        LocalDateTime sevendaysago = LocalDateTime.now().minusDays(7);

        List<Rent> rents = rentRepo.findByRentDateBeforeAndEmailSent(sevendaysago, false);
        
        for(Rent rent : rents){
            try{
            UserEntry user = userRepo.findById(rent.getUserId().toString()).orElseThrow(() -> new RuntimeException("User not found "));

            emailService.sendoverduemail(user.getEmail(), rent.getBookname(), user.getUsername());
            rent.setEmailSent(true);
            rentRepo.save(rent);
                
            }
            catch(Exception e){
                log.error("Failed to send email for rent id {}: {} ",rent.getUserId() ,e.getMessage());
            }
        }
    }



}
