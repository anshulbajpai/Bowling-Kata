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
        return throw1(frameIndex) + throw2(frameIndex) + bonus(frameIndex);
    }

    private int bonus(int frameIndex) {
        int throw1 = throw1(frameIndex);
        int throw2 = throw2(frameIndex);
        if (isStrike(throw1)) {
            return strikeBonus(frameIndex);
        } else if (isSpare(throw1, throw2)) {
            return spareBonus(frameIndex);
        }
        return 0;
    }

    private int spareBonus(int frameIndex) {
        if (isLastFrame(frameIndex)) {
            return throw3(frameIndex);
        }
        return throw1(frameIndex + 1);
    }

    private int strikeBonus(int frameIndex) {
        if (isLastFrame(frameIndex)) {
            return throw3(frameIndex);
        }
        return throw1(frameIndex + 1) + throw2(frameIndex + 1);
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
        return throwIndex == 2 && !isLastFrame(currentFrameIndex);
    }

    private int throw1(int frameIndex) {
        return getThrow(frameIndex, 0);
    }

    private int throw2(int frameIndex) {
        return getThrow(frameIndex, 1);
    }

    private int throw3(int frameIndex) {
        return getThrow(frameIndex, 2);
    }

    private int getThrow(int frameIndex, int throwIndex) {
        return frames[frameIndex][throwIndex];
    }


}
