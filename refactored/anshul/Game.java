package anshul;

public class Game {

    private int[][] frames = new int[10][3];

    private int currentFrameIndex = 0;
    private int throwIndex = 0;

    public Game pins(int pins) {
        frames[currentFrameIndex][throwIndex++] = pins;
        if (isStrike(pins)) {
            throwIndex++;
        }
        if (isFrameOver()) {
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
            score += calculateFrameScore(frameIndex);
        }
        return score;
    }

    private int calculateFrameScore(int frameIndex) {
        int[] currentFrame = frames[frameIndex];
        int throw1 = currentFrame[0];
        int throw2 = currentFrame[1];
        return throw1 + throw2 + calculateFrameBonus(frameIndex);
    }

    private int calculateFrameBonus(int frameIndex) {
        int[] currentFrame = frames[frameIndex];
        int throw1 = currentFrame[0];
        int throw2 = currentFrame[1];
        if (isStrike(throw1)) {
            return bonusForStrike(frameIndex);
        } else if (isSpare(throw1, throw2)) {
            return bonusForSpare(frameIndex);
        }
        return 0;
    }

    private int bonusForSpare(int frameIndex) {
        if (isLastFrame(frameIndex)) {
            return frames[frameIndex][2];
        } else {
            int[] nextFrame = frames[frameIndex + 1];
            return nextFrame[0];
        }
    }

    private int bonusForStrike(int frameIndex) {
        if (isLastFrame(frameIndex)) {
            return frames[frameIndex][2];
        } else {
            int[] nextFrame = frames[frameIndex + 1];
            return nextFrame[0] + nextFrame[1];
        }
    }

    private boolean isLastFrame(int frameIndex) {
        return frameIndex == 9;
    }

    private boolean isSpare(int throw1, int throw2) {
        return throw1 + throw2 == 10;
    }

    private boolean isStrike(int pins) {
        return pins == 10;
    }

    private boolean isFrameOver() {
        return throwIndex == 2 && !(currentFrameIndex == 9);
    }

}
