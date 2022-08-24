package gamingplatform.game;

abstract class Game {

    protected String name;
    protected int numberOfPlayers;
    protected int age;
    protected String description;
    protected String rules;
    protected String demoVideo;
    protected int estimatedTime;
    protected String help;
    protected String fitnessLevel;
    protected String difficulty;

    abstract void start();
    abstract void end();

}
