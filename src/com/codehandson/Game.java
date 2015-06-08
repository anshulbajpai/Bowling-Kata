package com.codehandson;

public class Game {

    private int[][] frames = new int[10][3];

    private int currentFrameIndex = 0;
    private int throwIndex = 0;

    public Game pins(int pins) {
        frames[currentFrameIndex][throwIndex++] = pins;
        if (pins == 10) {
            throwIndex++;
        }
        if (throwIndex == 2 && !(currentFrameIndex == 9)) {
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
            int[] currentFrame = frames[frameIndex];
            int throw1 = currentFrame[0];
            int throw2 = currentFrame[1];
            if (throw1 == 10) {
                int result;
                if (frameIndex == 9) {
                    result = currentFrame[2];
                } else {
                    int[] nextFrame = frames[frameIndex + 1];
                    result = nextFrame[0] + nextFrame[1];
                }
                score += result;
            } else if (throw1 + throw2 == 10) {
                int result;
                if (frameIndex == 9) {
                    result = currentFrame[2];
                } else {
                    int[] nextFrame = frames[frameIndex + 1];
                    result = nextFrame[0];
                }
                score += result;
            }
            score += throw1 + throw2;
        }
        return score;
    }

}
