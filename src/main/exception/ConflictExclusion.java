package main.exception;

public class ConflictExclusion extends RuntimeException{

    public ConflictExclusion(String conflict){
        super(conflict);
    }

}
