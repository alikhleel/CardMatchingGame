package models;


import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Player implements java.io.Serializable {
    private static Player instance;
    private final ArrayList<Integer> listMistakes;
    private final ArrayList<Integer> listTimer;

    private Player() {
        Player player = readData();
        if (player == null) {
            this.listMistakes = new ArrayList<>();
            this.listTimer = new ArrayList<>();
        } else {
            this.listMistakes = player.listMistakes;
            this.listTimer = player.listTimer;
        }
    }

    public static Player getInstance() {
        if (instance == null)
            instance = new Player();
        return instance;
    }

    private static Player readData() {
        try (
                ObjectInputStream objectInputStream = new ObjectInputStream(
                        new FileInputStream("player.dat"))
        ) {
            return (Player) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }

    private static void saveData(Player player) {
        try (
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                        new FileOutputStream("player.dat"))
        ) {
            objectOutputStream.writeObject(player);
            instance = readData();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void addScore(int mistakes, int seconds) {
        this.listMistakes.add(mistakes);
        this.listTimer.add(seconds);
        this.listMistakes.sort(Comparator.comparingInt(o -> o));
        saveData(this);
    }

    public ArrayList<Integer> getListMistakes() {
        return listMistakes;

    }

    public ArrayList<Integer> getListTimer() {
        return listTimer;
    }

    @Override
    public String toString() {
        return "Player{" +
                "listMistakes=" + listMistakes +
                ", listTimer=" + listTimer +
                '}';
    }
}

