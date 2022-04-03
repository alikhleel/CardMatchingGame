package models;

public class Card {
    private int id;
    private String imagePath;
    private boolean open;

    public Card(int id, String imagePath) {
        this.id = id;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return card.id == id;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
