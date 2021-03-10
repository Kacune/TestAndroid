package app.kacunagi.youruchet;

/**
 * Класс объекта Счет
 */
public class Score {
    private int id;
    private String name;
    private float sum;
    private  String currency;

    /**
     * Конструктор счета
     * @param id - Идентификатор счета
     * @param name - Наименование счета
     * @param sum - Сумма счета
     * @param currency - Валюта счета
     */
    public Score(int id, String name, float sum, String currency){
        this.id = id;
        this.name = name;
        this.sum = sum;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return name+"   "+sum;
    }

    public float getSum() {
        return sum;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }
}
