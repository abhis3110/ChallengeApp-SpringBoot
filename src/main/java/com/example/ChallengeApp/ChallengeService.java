package com.example.ChallengeApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {
    // Provide List of all challenges
    // Manage all operation on list like add , delete

    //private List<Challenge> challenges =  new ArrayList<>();

    private Long nextId = 1L;

    @Autowired
    ChallengeRepository challengeRepository;

    public ChallengeService() {
        //Challenge ch1 = new Challenge(1L, "January", "Learn new language");
        //this.challenges.add(ch1);
    }

    public List<Challenge> getAllChallenges() {
        //return challenges;
        return challengeRepository.findAll();
    }

    public boolean addChallenge(Challenge ch) {
        if(ch != null) {
            ch.setId(nextId++);
            challengeRepository.save(ch); //challenges.add(ch);
            return true;
        } else {
            return false;
        }
    }

    public Challenge getChallenges(String month) {
        Optional<Challenge> ch = challengeRepository.findByMonthIgnoreCase(month);
        return ch.orElse(null);
        /*for(Challenge ch : challenges) {
            if(ch.getMonth().equalsIgnoreCase(month)){
                return ch;
            }
        }
        return null;*/
    }


    public boolean updateChallenge(Long id, Challenge updatedCh) {
        Optional<Challenge> ch = challengeRepository.findById(id);
        if(ch.isPresent()) {
            Challenge challengeToUpdate = ch.get(); // object supposed to update here
            challengeToUpdate.setMonth(updatedCh.getMonth());
            challengeToUpdate.setDescription(updatedCh.getDescription());
            challengeRepository.save(challengeToUpdate);
            return true;
        }
        return false;


      /*  for(Challenge ch : challenges) {
            if(ch.getId().equals(id)){
                ch.setMonth(updatedCh.getMonth());
                ch.setDescription(updatedCh.getDescription());
                return true;
            }
        }
        return false;*/
    }

    public boolean deleteChallenge(Long id) {
        //return challenges.removeIf(ch->ch.getId().equals(id));
        Optional<Challenge> ch = challengeRepository.findById(id);
        if(ch.isPresent()) {
            challengeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
