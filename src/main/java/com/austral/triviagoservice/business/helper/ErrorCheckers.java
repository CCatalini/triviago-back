package com.austral.triviagoservice.business.helper;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.presentation.dto.QuizFilter;

import java.time.LocalDate;

public class ErrorCheckers {
    //This is a helper class to check Quiz filter conditions

    public boolean checkDates(LocalDate from, LocalDate to){
        //If from is after to, returns true
        if(from != null && to != null){return from.isAfter(to);}
        return false; //if does not have this parameters, there is no need to check
    }

    public boolean isFirstGreater(Double first, Double second){
        if(first != null && second != null) {return first > second;}
        return false;
    }

    public void checkQuizFilter(QuizFilter filter) throws InvalidContentException {
        //checks valid quiz filter
        if(this.checkDates(filter.getDateFrom(), filter.getDateTo())){throw new InvalidContentException("From date shouldn´t be greater than To date");}

        Integer first = filter.getMinQuestion();
        Integer second = filter.getMaxQuestion();
        if(first != null && second != null) {
            if (this.isFirstGreater((double) first, (double) second)) {
                throw new InvalidContentException("MaxQuestion shouldn´t be greater than MinQuiestion");
            }
        }

        if(this.isFirstGreater(filter.getMinRating(), filter.getMaxRating())){throw new InvalidContentException("MinRating shouldn´t be greater than MaxRating");}
    }

    public static void checkRate(Integer rate) throws InvalidContentException {
        if(rate < 1 || rate > 5){throw  new InvalidContentException("Invalid rate. Value should be between 1 and 5");}
    }
}
