package com.example.ChallengeApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {

    private ChallengeService challengeService;

    public ChallengeController(ChallengeService chService) { // dependency injection works here
        this.challengeService = chService;
    }

    @GetMapping //("/challenges")
    public List<Challenge> getAllChallenges() { // add entity response here
        return challengeService.getAllChallenges();
    }

    @PostMapping //("/challenges")
    public ResponseEntity<String> addChallenge(@RequestBody Challenge ch) {
        boolean isChallengeAdded = challengeService.addChallenge(ch);
        if(isChallengeAdded) {
            return new ResponseEntity("Challenge added Success", HttpStatus.OK);
        } else {
            return new ResponseEntity("Challenge added failed", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{month}")
    public ResponseEntity<Challenge> getChallenges(@PathVariable String month) {
        Challenge ch =  challengeService.getChallenges(month);
        if(ch != null) {
            return new ResponseEntity<>(ch, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable Long id, @RequestBody Challenge updatedCh) {
        boolean isChallengeUpdated = challengeService.updateChallenge(id, updatedCh);
        if(isChallengeUpdated){
            return new ResponseEntity("Challenge update Success", HttpStatus.OK);
        } else {
            return new ResponseEntity("Challenge update failed", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> deleteChallenge(@PathVariable Long id) {
        boolean isChallengeDeleted = challengeService.deleteChallenge(id);
        if(isChallengeDeleted){
            return new ResponseEntity("Challenge delete Success", HttpStatus.OK);
        } else {
            return new ResponseEntity("Challenge delete failed", HttpStatus.NOT_FOUND);
        }
    }
}
