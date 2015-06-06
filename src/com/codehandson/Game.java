package com.codehandson;

public class Game {

    private int[][] frames = new int[10][3];

    private int currentFrameIndex = 0;
    private int throwIndex = 0;

    public Game pins(int pins) {
        frames[currentFrameIndex][throwIndex++] = pins;
        if(isSpare(pins)){
            throwIndex++;
        }
        if(isFrameComplete()){
            throwIndex = 0;
            currentFrameIndex++;
        }
        return this;
    }

    public int score() {
        return scoreTillFrame(10);
    }

    public int scoreTillFrame(int frameNumber) {
        int score = 0;
        for (int frameIndex = 0; frameIndex < frameNumber; frameIndex++) {
            int[] frame = frames[frameIndex];
            int throw1 = frame[0];
            int throw2 = frame[1];
            if(isSpare(throw1)){
                if(isLastFrame(frameIndex)){
                    score += frame[2];
                }else{
                    score += frames[frameIndex + 1][0] + frames[frameIndex + 1][1];
                }
            }
            else if(isStrike(throw1, throw2)){
                if(isLastFrame(frameIndex)){
                    score += frame[2];
                }else{
                    score += frames[frameIndex + 1][0];
                }
            }
            score += throw1 + throw2;
        }
        return score;
    }

    private boolean isLastFrame(int frameIndex) {
        return frameIndex == 9;
    }

    private boolean isStrike(int throw1, int throw2) {
        return throw1 + throw2 == 10;
    }

    private boolean isSpare(int pins) {
        return pins == 10;
    }

    private boolean isFrameComplete() {
        return throwIndex == 2 && !isLastFrame(currentFrameIndex);
    }

}
